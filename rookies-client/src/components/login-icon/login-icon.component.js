import React from 'react';
import { withRouter } from 'react-router-dom';

import './login-icon.styles.scss';
import { ReactComponent as LoginIcon } from '../../assets/login.svg';

class CartIcon extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        const user = JSON.parse(localStorage.getItem('user'))
        return (
            <div className='cart-icon' >
                <LoginIcon className='login-icon' />
                <span className='user-name'> {user ? user.name : 'LOGIN'} </span>
            </div>
        )
    }
}

export default withRouter(CartIcon)
