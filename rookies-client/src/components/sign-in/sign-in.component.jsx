import React from 'react';
import { withRouter } from 'react-router-dom';
import FormInput from '../form-input/form-input.component';
import { get, post } from "../../HttpHelper";

import {
  SignInContainer,
  SignInTitle
} from './sign-in.styles';
import { Button } from 'reactstrap';

class SignIn extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
      user: []
    };
    const token = localStorage.getItem('toekn')
    if(token != null)
      this.props.history.push('/')
  }

  handleSubmit = async event => {
    event.preventDefault();
    const { username, password } = this.state;

    await post("/api/auth/signin", {
      username: username,
      password: password
    })
    .then(response => {
      try {
        localStorage.setItem('token', response.data.token)
        localStorage.setItem('roles', JSON.stringify(response.data.roles))
      } catch (error) {
        return this.setState({
          error: error
        })
      }
    })
    .catch(() => {
      this.setState({
        error: 'Invalid usernmae or password'
      })
    })
    await this.saveCustomer();
  }

  handleChange = event => {
    const { value, name } = event.target;
    this.setState({ [name]: value });
  };

  saveCustomer() {
    const roles = JSON.parse(localStorage.getItem('roles'))
    if (roles != null)
      roles.map(role => {
        if (role === "ROLE_CUSTOMER") {
          //get customer data
          get('/api/v1/customer')
            .then(response => {
              localStorage.setItem('user', JSON.stringify({
                name: response.data.last_name + ' ' + response.data.first_name,
                phone: response.data.phone,
                city: response.data.address.city,
                district: response.data.address.district,
                ward: response.data.address.ward,
                street: response.data.address.street
              }),
                this.props.history.push('/'))
            }).catch(error => {
              console.error(error)
            });
        }
        else if (role === "ROLE_ADMIN") {
          this.props.history.push('/admin')
        } else this.props.history.push('/shop')
      })
  }

  render() {
    return (
      <SignInContainer>
        <SignInTitle>I already have an account</SignInTitle>
        <span>Sign in with your username and password</span>
        {this.state.error ? <span className="text-danger">*{this.state.error}</span> : ''}
        <form onSubmit={this.handleSubmit}>
          <FormInput
            name='username'
            type='text'
            handleChange={this.handleChange}
            value={this.state.username}
            label='Username'
            required
          />
          <FormInput
            name='password'
            type='password'
            value={this.state.password}
            handleChange={this.handleChange}
            label='Password'
            required
          />

          <Button color="danger" type='submit'> Sign in </Button>

        </form>
      </SignInContainer>
    );
  }
}

export default withRouter(SignIn);