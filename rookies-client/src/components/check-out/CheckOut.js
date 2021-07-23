import React from 'react'
import { Link, withRouter } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import Price from 'react-price';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle'

import './CheckOut.scss'
import trash from '../../assets/trash.svg'

class CheckOut extends React.Component {
  constructor(props) {
    const cart = JSON.parse(localStorage.getItem('cart'))
    super(props);
    this.state = {
      cart: cart,
      open: false
    };
  }


  onChange(index, pres) {
    let amount = this.state.cart[index].amount
    if (pres === '+')
      amount++
    else
      amount--

    // 1. Make a shallow copy of the items
    let carts = [...this.state.cart];
    // 2. Make a shallow copy of the item you want to mutate
    let cart = { ...carts[index] };
    // 3. Replace the property you're intested in
    cart.amount = amount;
    // 4. Put it back into our array. N.B. we *are* mutating the array here, but that's why we made a copy first
    carts[index] = cart;
    // 5. Set the state to our new copy
    this.setState({ cart: carts });
    localStorage.setItem('cart', JSON.stringify(carts))
  }

  delete(index) {
    var carts = [...this.state.cart]
    carts.splice(index, 1)
    this.setState({ cart: carts })
    localStorage.setItem('cart', JSON.stringify(carts))
    this.setState({ open: false })
  }

  render() {
    let total = 0;
    return (
      <div>
        <div class="banner header-text">
          <div class="owl-banner owl-carousel">
            <div class="banner-item-02">
              <div class="text-content">
                <h4>Flash Deals</h4>
                <h2>Get your best products</h2>
              </div>
            </div>
          </div>
          <div class="products">
            <div class="latest-products">
              <div class="container">
                <div class="row grid">
                  <div class="col-md-12">
                    <div class="filters">
                      <ul>
                        <li class="active" data-filter="*">Check out your cart</li>
                      </ul>
                    </div>
                  </div>
                  {
                    (this.state.cart !== null && this.state.cart.length > 0) ?
                      <div>
                        <div className="row">
                          <div className="col-md-10">
                            <Table>
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th className="col-md-2">Product</th>
                                  <th className="col-md-4"></th>
                                  <th>Price</th>
                                  <th>Amount</th>
                                  <th>Total cost</th>
                                  <th><img src={trash} /></th>
                                </tr>
                              </thead>
                              <tbody>
                                {
                                  this.state.cart.map((item, index) => {
                                    total += item.price * item.amount
                                    return (
                                      <tr>
                                        <th scope="row">{index + 1}</th>
                                        <td><img src={item.url} class="small-img" /></td>
                                        <td className="product-name">{item.name}</td>
                                        <td><Price cost={item.price} currency="$" /></td>
                                        <td>
                                          <div class="number-input">
                                            <button onClick={() => this.onChange(index, '-')}>
                                            </button>
                                            <input class="quantity" min="1" name="quantity" value={item.amount} type="number" />
                                            <button onClick={() => this.onChange(index, '+')} class="plus"></button>
                                          </div>
                                        </td>
                                        <td><Price cost={(item.price * item.amount).toFixed(2)} currency="$" /></td>
                                        <td><Button color="link" onClick={() => this.setState({ open: true })}><img src={trash} /></Button></td>
                                        <Dialog
                                          open={this.state.open}
                                          onClose={() => this.setState({ open: false })}
                                          aria-labelledby="alert-dialog-title"
                                          aria-describedby="alert-dialog-description"
                                        >
                                          <DialogTitle id="alert-dialog-title">{"Warning!"}</DialogTitle>
                                          <DialogContent>
                                            <DialogContentText id="alert-dialog-description">
                                              Do you want to remove this product?
                                            </DialogContentText>
                                          </DialogContent>
                                          <DialogActions>
                                            <Button onClick={() => this.delete(index)} color="danger">
                                              Yes
                                            </Button>
                                            <Button onClick={() => this.setState({ open: false })} color="primary" autoFocus>
                                              No
                                            </Button>
                                          </DialogActions>
                                        </Dialog>
                                      </tr>
                                    )
                                  })
                                }
                              </tbody>
                            </Table>
                          </div>
                          <div className="col-md-2 order-info">
                            <Link to="#" className="link">Delivery to</Link>
                            <br></br>
                            <span>Nguyễn Ngọc Quỳnh Hương | 0339737498</span>
                            <p>Đà Lạt, P11, Tổ Lâm Văn Thạch</p>
                            <hr></hr>
                            <Table>
                              <tr>
                                <td></td>
                                <td></td>
                              </tr>
                              <tr>
                                <td>Costs</td>
                                <td className="price"><Price cost={total.toFixed(2)} currency="$" /></td>
                              </tr>
                              <tr>
                                <td>Discount</td>
                                <td className="price"><Price cost="0.00" currency="$" /><br></br></td>
                              </tr>
                              <tr className="total-tr">
                                <td>Total</td>
                                <td className="price"><Price cost={total.toFixed(2)} currency="$" /></td>
                              </tr>
                            </Table>
                            <Button color="danger">ORDER</Button>
                          </div>
                        </div>
                      </div> :
                      <p class='no-item'>Oops you don't have any purchase, go to <a href="/">shop</a> and get one for you</p>
                  }
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(CheckOut)