import React from 'react';

import './login-icon.styles.scss';
import { ReactComponent as LoginIcon } from '../../assets/login.svg';
import Cookies from 'universal-cookie';

const cookies = new Cookies();
const username = cookies.get('username');

const CartIcon = () => (
    <div className='cart-icon'>
        <LoginIcon className='login-icon' />
        <span className='user-name'> { username === undefined ? 'LOGIN' : username } </span>
    </div>
)

export default (CartIcon);
    