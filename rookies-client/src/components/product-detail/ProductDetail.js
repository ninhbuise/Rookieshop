import React from 'react'
import Carousel from 'react-gallery-carousel';
import StarRatings from 'react-star-ratings'
import Price from 'react-price';
import { Button, ButtonGroup } from 'reactstrap';

import 'react-gallery-carousel/dist/index.css';
import './ProductDetail.scss';

class ProductDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            item: [],
            images: [],
            sizes: [], 
            cSelected: [],
            setCSelected: [],
            rSelected: ['M', 'L', 'XL'],
            setRSelected: 0,
            number: 0,
            value: 1
        };
    }
    
    componentDidMount() {
        const id = new URLSearchParams(window.location.search).get(`id`);
        fetch("http://localhost:8080/api/shop/product/" + id)
            .then(res => res.json())
            .then(
            (result) => {
                this.setState({
                    isLoaded: true,
                    item: result
                });
                result.images.map(img => {
                    this.setState({
                        images: [...this.state.images, {src: img.url}]
                    })
                });
                result.sizes.map(size => {
                    this.setState({
                        sizes: [...this.state.sizes, {id: size.id, size_name: size.size_name}]
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

    render() {
        const item = this.state.item
        return (
            <div>
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
                                        style={{backgroundColor: "#fff",
                                            width: '100%',
                                            height: 560,
                                            objectFit: 'cover'
                                        }}
                                        hasMediaButton = {false}
                                        hasIndexBoard = {false}
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
                                        <Price cost={item.price} currency="$"/>
                                        {this.state.sizes[this.state.setRSelected] === undefined ? '' : 
                                            <div>
                                                <p>Size: {this.state.sizes[this.state.setRSelected].size_name}</p>
                                                <ButtonGroup>
                                                    {this.state.sizes.map(size => (
                                                        <Button outline color="primary" onClick={() => this.setState({setRSelected: this.state.sizes.indexOf(size)})} >
                                                            {size.size_name}
                                                        </Button>
                                                    ))}
                                                </ButtonGroup>
                                            </div>
                                        }
                                    </div>
                                    <p class="amount">Amount</p>
                                    <div class="number-input">
                                        <button onClick={() => this.setState({value: this.state.value <= 1 ?  this.state.value : this.state.value - 1})}></button>
                                        <input class="quantity" min="1" name="quantity" value={this.state.value} type="number"/>
                                        <button onClick={() => this.setState({value: this.state.value + 1})} class="plus"></button>
                                    </div>
                                    <div class="bt-buy">
                                        <Button color="danger" size="lg" style={{width: '360px'}}>BUY</Button>
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

export default ProductDetail