import React from 'react';
import { withRouter } from 'react-router-dom';
import Cookies from 'universal-cookie';
import FormInput from '../form-input/form-input.component';

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
            error: ''
        };
    }

    handleSubmit = async event => {
        event.preventDefault();
        const cookies = new Cookies();
        const { username, password } = this.state;

        await fetch("http://localhost:8080/api/auth/signin", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({username: username, password: password})
        })
        .then(response => {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText)
                error.response = response
                this.setState({
                    error: 'Invalid usernmae or password'
                })
                throw error
            }
        })
        .then(res => res.json())
        .then(response => {
            try {
                cookies.set('token', response.token, { path: '/' });
                cookies.set('username', response.username, { path: '/' });
                cookies.set('roles', response.roles, { path: '/' });
                this.props.history.push('/');
            } catch (e) {
                return this.setState({
                    error: 'Invalid usernmae or password'
                })
            }
        })
        .catch(error => {
            // dispatch(loginUserFailure(error));
        })
    };

    handleChange = event => {
        const { value, name } = event.target;

        this.setState({ [name]: value });
    };

    render() {
        return (
            <SignInContainer>
                <SignInTitle>I already have an account</SignInTitle>
                <span>Sign in with your username and password</span>
                {this.state.error ?  <span className = "text-danger">*{this.state.error}</span>: ''}
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