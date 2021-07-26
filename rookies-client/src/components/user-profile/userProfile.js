import React from 'react'
import { Button } from '@material-ui/core';
import { withRouter } from 'react-router-dom';
import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator';

import { get, put } from "../../HttpHelper";
import firebase from "../../firebase/firebase"
import edit from '../../assets/edit.svg'
import logout from '../../assets/logout.svg'
import Header from "../header/Header";

class UserProfile extends React.Component {
  constructor(props) {
    super(props);
    const roles = JSON.parse(localStorage.getItem('roles'))
    if (roles !== null)
      roles.map(role => {
        if (role !== "ROLE_CUSTOMER")
          this.props.history.push('/')
      })
    else this.props.history.push('/signin')
    this.state = {
      image: null,
      progress: 0,
      downloadURL: null,
      isChangeAvatar: false,
      avatar: '',
      inputOpenFileRef: React.createRef(),
      user_id: 0,
      email: '',
      first_name: '',
      last_name: '',
      phone: '',
      city: '',
      district: '',
      ward: '',
      street: '',
    }
    this.getCustomer();
  }

  getCustomer() {
    const roles = JSON.parse(localStorage.getItem('roles'))
    roles.map(role => {
      if (role === "ROLE_CUSTOMER") {
        //get customer data
        get('/api/v1/customer')
          .then(response => {
            this.setState({
              user_id: response.data.id,
              email: response.data.email,
              avatar: response.data.user.avatar.url,
              first_name: response.data.first_name,
              last_name: response.data.last_name,
              phone: response.data.phone,
              city: response.data.address.city,
              district: response.data.address.district,
              ward: response.data.address.ward,
              street: response.data.address.street
            })
          }).catch(error => {
            console.error(error)
          })
      }
    })
  }

  updateCustomer() {
    if (this.state.isChangeAvatar === true)
      this.handleUpload()
    put('/api/v1/customer', {
      "id": this.state.user_id,
      "first_name": this.state.first_name,
      "last_name": this.state.last_name,
      "phone": this.state.phone,
      "email": this.state.email,
      "address": {
        "city": this.state.city,
        "district": this.state.district,
        "ward": this.state.ward,
        "street": this.state.street
      }
    }).then(req => {
      if (req.status === 200)
        alert("Update profile success!")
    }).catch(err => {
      if (err.response !== undefined)
        alert(JSON.stringify(err.response.data))
    })
  }

  handleChange = (e) => {
    if (e.target.files[0]) {
      this.setState({
        image: e.target.files[0],
        isChangeAvatar: true
      })
      var file = e.target.files[0]
      var reader = new FileReader()
      reader.readAsDataURL(file)
      reader.onloadend = function (e) {
        this.setState({
          avatar: [reader.result]
        })
      }.bind(this);
    }
  }

  handleUpload() {
    // console.log(this.state.image);
    let file = this.state.image;
    var storage = firebase.storage();
    var storageRef = storage.ref();
    var uploadTask = storageRef.child('folder/' + file.name).put(file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) => {
        var progress = Math.round((snapshot.bytesTransferred / snapshot.totalBytes)) * 100
        this.setState({ progress })
      }, (error) => {
        throw error
      }, () => {
        // uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) =>{
        uploadTask.snapshot.ref.getDownloadURL().then((url) => {
          this.setState({
            downloadURL: url,
            isChangeAvatar: false
          })
          if (url !== null) {
            console.log(url)
            put('/api/v1/user-avatar', {
              "url": url
            })
          }
        })
        document.getElementById("file").value = null
      }
    )
  }

  handleLogout() {
    localStorage.removeItem('cart')
    localStorage.removeItem('roles')
    localStorage.removeItem('user')
    localStorage.removeItem('token')
    this.props.history.push('/')
  }

  render() {
    const inputFile = React.createRef();
    return (
      <div>
        <Header />
        <div class="products">
          <div class="latest-products">
            <div class="container">
              <div class="row grid">
                <div class="filters" />
                <div class="col-md-8">
                  <div class="card">
                    <div class="card-header card-header-primary">
                      <img src={this.state.downloadURL || this.state.avatar} class="rounded-circle" style={{ height: '160px', width: '160px' }} />
                      <input type="file" id="file" onChange={this.handleChange} ref={inputFile} style={{ display: 'none' }} />
                      <Button
                        onClick={() => inputFile.current.click()}
                        style={{
                          position: 'absolute',
                          left: '120px',
                          top: '120px'
                        }}
                      >
                        <img src={edit} />
                      </Button>
                      <Button variant="contained" color="secondary"
                        onClick={() => this.handleLogout()}
                        style={{
                          position: 'absolute',
                          right: '10px',
                          top: '10px'
                        }}>
                        Log out<img src={logout} style={{ width: '30px', marginLeft: "10px" }} ></img>
                      </Button>
                    </div>
                    <div class="card-body">
                      <ValidatorForm
                        ref="form"
                        onSubmit={() => this.updateCustomer()}
                        noValidate={true}
                      >
                        <div class="row">
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.email}
                              disabled
                              fullWidth
                              label="Username"
                              type="email" />
                          </div>
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.email}
                              fullWidth
                              disabled
                              label="Email"
                              type="email" />
                          </div>
                        </div>
                        <div class="col-md-4">
                          <TextValidator
                            value={this.state.phone}
                            onChange={(event) => this.setState({
                              phone: event.target.value,
                              first_name: this.state.first_name,
                              last_name: this.state.last_name,
                              city: this.state.city,
                              district: this.state.district,
                              ward: this.state.ward,
                              street: this.state.street
                            })}
                            fullWidth
                            validators={['required', 'matchRegexp:^$|[0-9]{9,11}']}
                            errorMessages={['this field is required', 'Phone mush match 9-11 digits number']}
                            label="Phone"
                            type="number" />
                        </div>
                        <div class="row">
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.first_name}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: event.target.value,
                                last_name: this.state.last_name,
                                city: this.state.city,
                                district: this.state.district,
                                ward: this.state.ward,
                                street: this.state.street
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="First name"
                              type="text" />
                          </div>
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.last_name}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: this.state.first_name,
                                last_name: event.target.value,
                                city: this.state.city,
                                district: this.state.district,
                                ward: this.state.ward,
                                street: this.state.street
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="Last name"
                              type="text" />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.city}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: this.state.first_name,
                                last_name: this.state.last_name,
                                city: event.target.value,
                                district: this.state.district,
                                ward: this.state.ward,
                                street: this.state.street
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="Province/City"
                              type="text" />
                          </div>
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.district}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: this.state.first_name,
                                last_name: this.state.last_name,
                                city: this.state.city,
                                district: event.target.value,
                                ward: this.state.ward,
                                street: this.state.street
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="District"
                              type="text" />
                          </div>
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.ward}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: this.state.first_name,
                                last_name: this.state.last_name,
                                city: this.state.city,
                                district: this.state.district,
                                ward: event.target.value,
                                street: this.state.street
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="Wards"
                              type="text" />
                          </div>
                          <div class="col-md-5">
                            <TextValidator
                              value={this.state.street}
                              onChange={(event) => this.setState({
                                phone: this.state.phone,
                                first_name: this.state.first_name,
                                last_name: this.state.last_name,
                                city: this.state.city,
                                district: this.state.district,
                                ward: this.state.ward,
                                street: event.target.value
                              })}
                              fullWidth
                              validators={['required']}
                              errorMessages={['this field is required']}
                              label="Street"
                              type="text" />
                          </div>
                        </div>
                        <div class="clearfix"></div>
                        <button type="submit" class="btn btn-primary"
                          style={{
                            marginTop: '30px',
                            fontStyle: 'italic',
                            float: 'right'
                          }}>
                          Update Profile</button>
                      </ValidatorForm>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div >
      </div>
    )
  }
}

export default withRouter(UserProfile)

