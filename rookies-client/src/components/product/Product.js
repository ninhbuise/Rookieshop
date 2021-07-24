import react from 'react'
import { get } from "../../HttpHelper";
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
    get('/api/shop')
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result.data
          });
        })
      .catch(error => {
        console.log(error)
      })
  }

  render() {
    const items = this.state.items;
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
                  // console.log(item),
                  <ProductItem key={item.id} item={item} />
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