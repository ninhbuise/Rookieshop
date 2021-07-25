import React from 'react'
import { Link, withRouter } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { post } from "../../HttpHelper";
import Price from 'react-price';

import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle'

import './CheckOut.scss'
import trash from '../../assets/trash.svg'

class CheckOut extends React.Component {
  constructor(props) {
    super(props);
    const cart = JSON.parse(localStorage.getItem('cart'))
    const roles = JSON.parse(localStorage.getItem('roles'))
    const user = JSON.parse(localStorage.getItem('user'))
    if (roles !== null)
      roles.map(role => {
        if (role !== "ROLE_CUSTOMER")
          this.props.history.push('/')
      })

    this.state = {
      cart: cart,
      open: false,
      showfrom: false,
      shownotice: false,
      notice: '',
      user: user === null ? [] : user,
      usertemp: []
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

  buy() {
    if (this.state.user.length === 0)
      this.props.history.push('/signin')
    const detail = []
    const address = {
      "city": this.state.user.city,
      "district": this.state.user.district,
      "ward": this.state.user.ward,
      "street": this.state.user.street
    }
    this.state.cart.map(cart => {
      detail.push({
        "product_id": cart.id,
        "amount": cart.amount,
        "size_id": cart.size_id,
        "color_id": cart.color_id
      })
    })
    const body = {
      "detailDTOs": detail,
      "phone": this.state.user.phone,
      "address": address

    }
    post('/api/shop/order', body)
      .then(response => {
        this.setState({ notice: "Order Success ^^", cart: [], shownotice: true })
        localStorage.removeItem('cart')
      })
      .catch(err => {
        this.setState({ notice: JSON.stringify(err.response.data), shownotice: true })
      })
  }

  render() {
    let total = 0
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
                            <Link onClick={() => {
                              if (this.state.user.length === 0)
                                this.props.history.push('/signin')
                              this.setState({ showfrom: true, usertemp: this.state.user })
                            }
                            } className="link">Delivery to</Link>
                            <Dialog open={this.state.showfrom} aria-labelledby="form-dialog-title">
                              <DialogTitle id="form-dialog-title">Delivery to</DialogTitle>
                              <DialogContent>
                                <DialogContentText>
                                  Update your new address here!
                                </DialogContentText>
                                <ValidatorForm
                                  ref="form"
                                  onSubmit={() => this.setState({ showfrom: false, user: this.state.usertemp })}
                                  noValidate={true} 
                                >
                                  <TextValidator
                                    style={{ margin: 12, width: 400 }}
                                    value={this.state.usertemp.phone}
                                    validators={['required', 'matchRegexp:^$|[0-9]{9,11}']}
                                    errorMessages={['this field is required', 'Phone mush match 9-11 digits number']}
                                    onChange={(event) => {
                                      this.setState({
                                        usertemp: {
                                          name: this.state.usertemp.name,
                                          phone: event.target.value,
                                          city: this.state.usertemp.city,
                                          district: this.state.usertemp.district,
                                          ward: this.state.usertemp.ward,
                                          street: this.state.usertemp.street
                                        }
                                      })
                                    }}
                                    label="Phone"
                                    type="number" />
                                  <TextValidator
                                    style={{ margin: 12, width: 400 }}
                                    value={this.state.usertemp.city}
                                    validators={['required']}
                                    errorMessages={['this field is required']}
                                    onChange={(event) => {
                                      this.setState({
                                        usertemp: {
                                          name: this.state.usertemp.name,
                                          phone: this.state.usertemp.phone,
                                          city: event.target.value,
                                          district: this.state.usertemp.district,
                                          ward: this.state.usertemp.ward,
                                          street: this.state.usertemp.street
                                        }
                                      })
                                    }}
                                    label="Province/City"
                                    type="text" />
                                  <TextValidator
                                    style={{ margin: 12, width: 400 }}
                                    value={this.state.usertemp.district}
                                    validators={['required']}
                                    errorMessages={['this field is required']}
                                    onChange={(event) => {
                                      this.setState({
                                        usertemp: {
                                          name: this.state.usertemp.name,
                                          phone: this.state.usertemp.phone,
                                          city: this.state.usertemp.city,
                                          district: event.target.value,
                                          ward: this.state.usertemp.ward,
                                          street: this.state.usertemp.street
                                        }
                                      })
                                    }}
                                    label="District"
                                    type="text" />
                                  <TextValidator
                                    style={{ margin: 12, width: 400 }}
                                    value={this.state.usertemp.ward}
                                    validators={['required']}
                                    errorMessages={['this field is required']}
                                    onChange={(event) => {
                                      this.setState({
                                        usertemp: {
                                          name: this.state.usertemp.name,
                                          phone: this.state.usertemp.phone,
                                          city: this.state.usertemp.city,
                                          district: this.state.usertemp.district,
                                          ward: event.target.value,
                                          street: this.state.usertemp.street
                                        }
                                      })
                                    }}
                                    label="Wards"
                                    type="text" />
                                  <TextValidator
                                    style={{ margin: 12, width: 400 }}
                                    value={this.state.usertemp.street}
                                    validators={['required']}
                                    errorMessages={['this field is required']}
                                    onChange={(event) => {
                                      this.setState({
                                        usertemp: {
                                          name: this.state.usertemp.name,
                                          phone: this.state.usertemp.phone,
                                          city: this.state.usertemp.city,
                                          district: this.state.usertemp.district,
                                          ward: this.state.usertemp.ward,
                                          street: event.target.value
                                        }
                                      })
                                    }}
                                    label="Street"
                                    type="text" />
                                  <DialogActions>
                                    <Button onClick={() => this.setState({ showfrom: false })} >
                                      Cancel
                                    </Button>
                                    <Button type="submit" color="primary">
                                      Submit
                                    </Button>
                                  </DialogActions>
                                </ValidatorForm>
                              </DialogContent>
                            </Dialog>
                            <br></br>
                            <span>{this.state.user.name} | {this.state.user.phone}</span>
                            <p>{this.state.user.city}, {this.state.user.district}, {this.state.user.ward}, {this.state.user.street}</p>
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
                            <Button color="danger" onClick={() => this.buy()}>ORDER</Button>
                          </div>
                        </div>
                      </div> :
                      <p class='no-item'>Oops you don't have any purchase, go to <a href="/">shop</a> and get one for you</p>
                  }
                  <Dialog
                    open={this.state.shownotice}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                  >
                    <DialogTitle id="alert-dialog-title">{"Notification"}</DialogTitle>
                    <DialogContent>
                      <DialogContentText id="alert-dialog-description">
                        {this.state.notice}
                      </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                      <Button onClick={() => this.setState({ shownotice: false })} color="primary" autoFocus>
                        Okay!
                      </Button>
                    </DialogActions>
                  </Dialog>
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