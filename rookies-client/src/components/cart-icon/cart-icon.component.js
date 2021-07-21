import React from 'react';

import './cart-icon.styles.scss';
import { ReactComponent as ShoppingIcon } from '../../assets/add-to-basket.svg';

const CartIcon = ({ itemCount }) => (
    <div className='cart-icon'>
        <ShoppingIcon className='shopping-icon' />
        <span className='item-count'> { itemCount } </span>
    </div>
)

export default (CartIcon);
    