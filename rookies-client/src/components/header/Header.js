import react from 'react'
import { withRouter } from 'react-router-dom';

import CartIcon from '../cart-icon/cart-icon.component'
import LoginIcon from '../login-icon/login-icon.component'

class Header extends react.Component {

  handleClick() {
    const token = localStorage.getItem('token');
    token === null ?
      this.props.history.push('/signin') :
      this.props.history.push('/profile')
  }

  render() {
    return (
      <header style={{
        position: "sticky",
        top: 0
      }}>
        <nav class="navbar navbar-expand-lg">
          <div class="container">
            <a class="navbar-brand" href="/"><h2>Sixteen <em>Clothing</em></h2></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Our Products</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">About Us</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Contact Us</a>
                </li>
              </ul>
            </div>
            <div onClick={() => this.handleClick()}>
             <LoginIcon />
            </div>
            <div onClick={() => this.props.history.push('/checkout')}> 
              <CartIcon /> 
            </div>
          </div>
        </nav>
      </header>
    )
  }
}

export default withRouter(Header)