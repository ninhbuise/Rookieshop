import react from 'react'

import ProductItem from '../product-item/Product-item'

class Product extends react.Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
      }
    
    componentDidMount() {
        fetch("https://mocki.io/v1/2327fed4-54cc-4eae-b7e0-38380e78364c")
            .then(res => res.json())
            .then(
            (result) => {
                this.setState({
                isLoaded: true,
                items: result
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
        const { error, isLoaded, items } = this.state;
        return (
            <body>
                <div class="banner header-text">
                    <div class="owl-banner owl-carousel">
                        <div class="banner-item-01">
                            <div class="text-content">
                            <h4>Best Offer</h4>
                            <h2>New Arrivals On Sale</h2>
                            </div>
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
                                        <li class="active" data-filter="*">All Products</li>
                                        <li data-filter=".des">Featured</li>
                                        <li data-filter=".dev">Flash Deals</li>
                                        <li data-filter=".gra">Last Minute</li>
                                    </ul>
                                    </div>
                                </div>
                                {items.map(item => (
                                    // {console.log(item.id)}
                                    <ProductItem key={item.id} item={item}/>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        )
    }
}

export default Product