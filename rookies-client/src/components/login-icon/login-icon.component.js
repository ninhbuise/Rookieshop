import React from 'react';

import './login-icon.styles.scss';
import { ReactComponent as LoginIcon } from '../../assets/login.svg';

const user = JSON.parse(localStorage.getItem('user'))

const CartIcon = () => (
    <div className='cart-icon'>
        <LoginIcon className='login-icon' />
        <span className='user-name'> { user === null ? 'LOGIN' : user.name } </span>
    </div>
)

export default (CartIcon);
    