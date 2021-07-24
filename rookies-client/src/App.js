import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

import Header from "./components/header/Header";
import Product from "./components/product/Product";
import ProductDetail from "./components/product-detail/ProductDetail";
import SignInAndSignUpPage from "./components/sign-in-and-sign-up/sign-in-and-sign-up.component"
import CheckOut from "./components/check-out/CheckOut";
import AdminBoard from "./components/admin-board/admin-board";

import './App.css';

function App() {
 
  return (
    <BrowserRouter>
      <React.Fragment>
        <Header/>
        <Switch>
          <Route exact path="/">
            <Product/>
          </Route>
          <Route exact path="/admin">
            <AdminBoard />
          </Route>
          <Route exact path="/signin">
            <SignInAndSignUpPage />
          </Route>
          <Route exact path="/checkout">
            <CheckOut/>
          </Route> 
          <Route exact path="/:ProductName">
            <ProductDetail />
          </Route>
      </Switch>
      </React.Fragment>
    </BrowserRouter>
  );
}

export default App;
