import React from 'react'
import { withRouter } from 'react-router-dom';
import Carousel from 'react-gallery-carousel';
import StarRatings from 'react-star-ratings'
import Price from 'react-price';
import { Button, ButtonGroup } from 'reactstrap';

import 'react-gallery-carousel/dist/index.css';
import './ProductDetail.scss';
import Header from "../header/Header";

class ProductDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      item: [],
      images: [],
      sizes: [],
      sizeSelected: 0,
      colors: [],
      colorSelected: 0,
      number: 0,
      value: 1
    };
  }

  componentDidMount() {
    const id = new URLSearchParams(window.location.search).get(`id`);
    fetch("http://localhost:8080/api/shop/product/" + id)
      .then(res => res.json())
      .then((result) => {
          if(result.status === 400){ //Bad request
            this.props.history.push('/')
            return
          } 
            
          this.setState({
            isLoaded: true,
            item: result
          });
          result.images.map(img => {
            this.setState({
              images: [...this.state.images, { src: img.url }]
            })
          });
          result.sizes.map(size => {
            this.setState({
              sizes: [...this.state.sizes, { id: size.id, size_name: size.size_name }]
            })
          });
          result.colors.map(color => {
            this.setState({
              colors: [...this.state.colors, { id: color.id, color_name: color.color_name }]
            })
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )

  }

  Buy() {
    const cart = localStorage.getItem('cart') === null ? [] : JSON.parse(localStorage.getItem('cart'))
    var isExit = false
    //find product in cart and in
    cart.map((i) => {
      if (i.id === this.state.item.id && 
          i.size_id === this.state.sizes[this.state.sizeSelected].id &&
          i.color_id === this.state.colors[this.state.colorSelected].id) {
        i.amount += this.state.value
        localStorage.setItem('cart', JSON.stringify(cart))
        isExit = true
      }
    })
    if (!isExit) {
      //if not add product to cart and save to localstorage
      console.log(this.state.sizes)
      const item = {
        id: this.state.item.id,
        name: this.state.item.name,
        price: this.state.item.price,
        url: this.state.item.images[0].url,
        size_id: this.state.sizes.length > 0 ? this.state.sizes[this.state.sizeSelected].id : 0,
        color_id:  this.state.colors.length > 0 ? this.state.colors[this.state.colorSelected].id : 0,
        amount: this.state.value,
      }
      cart.push(item)
      localStorage.setItem('cart', JSON.stringify(cart))
    }
    this.props.history.push('/checkout')
  }

  render() {
    const item = this.state.item
    return (
      <div>
        <Header/>
        <div class="page-heading products-heading header-text">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <div class="text-content">
                  <h4>new arrivals</h4>
                  <h2>sixteen products</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="products-detail">
          <div class="container">
            <div class="product-item">
              <div class="row align-items-start">
                <div class="col-5">
                  <Carousel images={this.state.images}
                    style={{
                      backgroundColor: "#fff",
                      width: '100%',
                      height: 560,
                      objectFit: 'cover'
                    }}
                    hasMediaButton={false}
                    hasIndexBoard={false}
                  />
                </div>
                <div class="col-7">
                  <div class="product-title">
                    {item.name}
                  </div>
                  <div class="product-rating">
                    <StarRatings class="star-ratings"
                      rating={4.5}
                      starRatedColor="#f33f3f"
                      changeRating={this.changeRating}
                      starDimension="20px"
                      numberOfStars={5}
                      name='rating'
                    />
                    <span>Reviews (12) | Sold 98</span>
                  </div>
                  <div class="price">
                    <small>Sale: </small>
                    <Price cost={item.price} currency="$" />
                    <div class="flex-container">
                      <div class="flex-child">
                        {this.state.colors[this.state.colorSelected] === undefined ? '' :
                          <div>
                            <p>Color: {this.state.colors[this.state.colorSelected].color_name}</p>
                            <ButtonGroup>
                              {this.state.colors.map(color => (
                                <Button outline color="primary" onClick={() => this.setState({ colorSelected: this.state.colors.indexOf(color) })} >
                                  {color.color_name}
                                </Button>
                              ))}
                            </ButtonGroup>
                          </div>
                        }
                      </div>
                      <div class="flex-child">
                        {this.state.sizes[this.state.sizeSelected] === undefined ? '' :
                          <div>
                            <p>Size: {this.state.sizes[this.state.sizeSelected].size_name}</p>
                            <ButtonGroup>
                              {this.state.sizes.map(size => (
                                <Button outline color="primary" onClick={() => this.setState({ sizeSelected: this.state.sizes.indexOf(size) })} >
                                  {size.size_name}
                                </Button>
                              ))}
                            </ButtonGroup>
                          </div>
                        }
                      </div>
                    </div>
                  </div>
                  <p class="amount">Amount</p>
                  <div class="number-input">
                    <button onClick={() => this.setState({ value: this.state.value <= 1 ? this.state.value : this.state.value - 1 })}></button>
                    <input class="quantity" min="1" name="quantity" value={this.state.value} type="number" />
                    <button onClick={() => this.setState({ value: this.state.value + 1 })} class="plus"></button>
                  </div>
                  <div className="bt-buy col-md-6">
                    <Button color="danger" size="lg" onClick={() => this.Buy()}>BUY</Button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(ProductDetail)