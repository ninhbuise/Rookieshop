import React from 'react'
import { Link } from 'react-router-dom';

class ProductItem extends React.Component {
    render() {
        const item = this.props.item;
        return (
            <div class="col-lg-4 col-md-4 all des">
                <div class="product-item">
                    <Link to={{pathname: `/${item.title}`, search: `?id=${item.id}`}}>
                        <img src={item.url} alt=""/>
                    </Link>
                    {/* <Link to={location => ({ ...location, pathname: `/${item.title}?id=${item.id}` })} >
                        <img src={item.url} alt=""/>
                    </Link> */}
                    <div class="down-content">
                        <h4>Tittle goes here</h4>
                        <h6>$18.25</h6>
                        <p>{item.title}</p>
                        <span>Reviews (12)</span>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProductItem
