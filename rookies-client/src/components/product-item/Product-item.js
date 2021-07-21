import React from 'react'
import { Link } from 'react-router-dom';
import './Product-item.scss'

class ProductItem extends React.Component {
    render() {
        const item = this.props.item;
        return (
            <div class="col-lg-4 col-md-4 all des">
                <div class="product-item">
                    <Link to={{pathname: `/${item.name}`, search: `?id=${item.id}`}}>
                        <img src={item.images[0].url} alt="" class="image-product"/>
                    </Link>
                    <div class="down-content">
                        <h4>{item.productType.product_type}</h4>
                        <h6>${item.price}</h6>
                        <p>{item.name}</p>
                        <span>Reviews (12)</span>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProductItem
