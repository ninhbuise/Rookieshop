import React from 'react'
import './sign-in-and-sign-up.styles.scss'
import SignInForm from '../../components/sign-in/sign-in.component'
import SignUpForm from '../../components/sign-up/sign-up.component'
import Header from "../header/Header";

export default function SignInAndSignUpPage() {
    return (
        <>
            <Header />
            <div className="sign-in-and-sign-up">
                <SignInForm />
                <SignUpForm />
            </div>
        </>
    )
}
