import React from 'react'
import Carousel from 'react-gallery-carousel';
import StarRatings from 'react-star-ratings'
import Price from 'react-price';
import { Button, ButtonGroup } from 'reactstrap';

import 'react-gallery-carousel/dist/index.css';
import './ProductDetail.scss';

const images = [
    {
        src: '/images/chup-anh-san-pham-nuoc-2.jpg'
    },
    {
        src: '/images/Chanel5_product_photography.jpg'
    },
    {
        src: '/images/HummingbirdSkincare_byProductPhotographer_JuliaMalinowska_13.jpg'
    },
    {
        src: '/images/photo-1615900119312-2acd3a71f3aa.jfif'
    },
    {
        src: '/images/Your-DIY-Product-Photography-Resource-Guide.jpg'
    }
]

class ProductDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cSelected: [],
            setCSelected: [],
            rSelected: 'M',
            setRSelected: null,
            number: 0,
            value: 1
        };
    }
    
    componentDidMount () {
        const script = document.createElement("script");
    
        script.src = "./src/bootstrap-input-spinner.js";
        script.async = true;
    
        document.body.appendChild(script);
    }

    render() {
        const id = new URLSearchParams(window.location.search).get(`id`);

        const onCheckboxBtnClick = (selected) => {
            const index = this.state.cSelected.indexOf(selected);
            if (index < 0) {
              this.state.cSelected.push(selected);
            } else {
                this.state.cSelected.splice(index, 1);
            }
            this.state.setCSelected([...this.state.cSelected]);
        }
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
                                    <Carousel images={images}
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
                                        Áo khoác nam chất kaki 2 lớp xịn xò Julido Store mẫu mới theo xu hướng thời trang Trend QA02
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
                                        <Price cost="93" currency="$"/>
                                        <p>Size: {this.state.rSelected}</p>
                                        <ButtonGroup>
                                            <Button outline color="primary" active={this.state.rSelected === 1}>L</Button>
                                            <Button outline color="primary" active={this.state.rSelected === 2}>M</Button>
                                            <Button outline color="primary" onClick={() => this.state.setRSelected(3)} active={this.state.rSelected === 3}>XL</Button>
                                        </ButtonGroup>
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