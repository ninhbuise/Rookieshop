import React from 'react';

import FormInput from '../form-input/form-input.component';

import { SignUpContainer, SignUpTitle } from './sign-up.styles';
import { Button } from 'reactstrap';

class SignUp extends React.Component {
    constructor() {
        super();

        this.state = {
            firstname: '',
            lastname: '',
            email: '',
            password: '',
            confirmPassword: ''
        };
    }

    handleSubmit = async event => {
        event.preventDefault();

        const { firstname, lastname, email, password, confirmPassword } = this.state;

        if (password !== confirmPassword) {
            alert("passwords don't match");
            return;
        }
    };

    handleChange = event => {
        const { name, value } = event.target;

        this.setState({ [name]: value });
    };

    render() {
        const { firstname, lastname, email, password, confirmPassword } = this.state;
        return (
            <SignUpContainer>
                <SignUpTitle>I do not have a account</SignUpTitle>
                <span>Sign up with your email and password</span>
                <form className='sign-up-form' onSubmit={this.handleSubmit}>
                    <FormInput
                        type='text'
                        name='firstname'
                        value={firstname}
                        onChange={this.handleChange}
                        label='First Name'
                        required
                    />
                    <FormInput
                        type='text'
                        name='lastname'
                        value={lastname}
                        onChange={this.handleChange}
                        label='Last Name'
                        required
                    />
                    <FormInput
                        type='email'
                        name='email'
                        value={email}
                        onChange={this.handleChange}
                        label='Email'
                        required
                    />
                    <FormInput
                        type='password'
                        name='password'
                        value={password}
                        onChange={this.handleChange}
                        label='Password'
                        required
                    />
                    <FormInput
                        type='password'
                        name='confirmPassword'
                        value={confirmPassword}
                        onChange={this.handleChange}
                        label='Confirm Password'
                        required
                    />
                    <Button type='submit'>SIGN UP</Button>
                </form>
            </SignUpContainer>
        );
    }
}

export default SignUp;