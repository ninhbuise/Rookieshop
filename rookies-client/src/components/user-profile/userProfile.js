import React from 'react'
import { Button, Table, TableHead, TableRow, TableCell, TableBody, Box, Typography } from '@material-ui/core';
import Collapse from '@material-ui/core/Collapse';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import IconButton from '@material-ui/core/IconButton';
import { withRouter } from 'react-router-dom';
import { get, put } from "../../HttpHelper";
import firebase from "../../firebase/firebase"

import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator';
import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import Moment from 'moment';
import edit from '../../assets/edit.svg'
import Header from "../header/Header";

import 'react-pro-sidebar/dist/scss/styles.scss';
import './userProfile.scss'
import crown from '../../assets/crown.svg'
import sidebar_img from '../../assets/sidebar-image.jpg'
import { Alert } from 'reactstrap';

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
      isShowProFile: true,
      image: null,
      progress: 0,
      height: 0,
      downloadURL: null,
      isChangeAvatar: false,
      avatar: '',
      inputOpenFileRef: React.createRef(),
      user_id: 0,
      orders: null,
      email: '',
      first_name: '',
      last_name: '',
      phone: '',
      city: '',
      district: '',
      ward: '',
      street: '',
      open: [false]
    }
  }

  componentDidMount() {
    const height = document.getElementById('cart').clientHeight;
    this.setState({ height: height });
    this.getCustomer()
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
              orders: response.data.orders,
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
            Alert(error)
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

  handleOpen(index) {
    const { open } = this.state;
    open.splice(index, 1, !open[index])
    this.setState({ open: [...open] });
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
                <div class="col-md-3">
                  <ProSidebar
                    style={{
                      height: this.state.height
                    }}
                    image={sidebar_img}
                  >
                    <Menu iconShape="square">
                      <MenuItem icon={<img src={crown} />}>User info</MenuItem><hr></hr>
                      <SubMenu title="Setting" icon="</>">
                        <MenuItem onClick={() => this.setState({ isShowProFile: true })} >User profile</MenuItem>
                        <MenuItem onClick={() => this.setState({ isShowProFile: false })} >Check out</MenuItem>
                        <MenuItem onClick={() => this.handleLogout()} >Logout</MenuItem>
                      </SubMenu>
                    </Menu>
                  </ProSidebar>
                </div>
                <div class="col-md-9">
                  {this.state.isShowProFile ?
                    <div class="card" id="cart">
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
                    :
                    (this.state.orders.length === 0 || this.state.orders === null) ?
                      <div>
                        <p class='no-item'>Oops you don't have any order, go to <a href="/">shop</a> and get one for you</p>
                      </div>
                      :
                      <div className="overflow-auto" style={{height: '680px'}}>
                        <Typography variant="h5" gutterBottom component="div">
                          Orders info
                        </Typography>
                        <Table aria-label="simple table" className="mw-95">
                          <TableHead>
                            <TableRow>
                              <TableCell />
                              <TableCell>OID</TableCell>
                              <TableCell>Status</TableCell>
                              <TableCell width='140px'>Created</TableCell>
                              <TableCell>Phone</TableCell>
                              <TableCell>Address</TableCell>
                            </TableRow>
                          </TableHead>
                          <TableBody>
                            {
                              Moment().format('LL'),
                              Moment.locale('en'),
                              this.state.orders.map((order, index) => {
                                return [
                                  <TableRow key={order.id}>
                                    <TableCell>
                                      <IconButton aria-label="expand row" size="small" onClick={() => { this.handleOpen(index) }}>
                                        {this.state.open[index] ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                                      </IconButton>
                                    </TableCell>
                                    <TableCell component="th" scope="row">{order.id}</TableCell>
                                    <TableCell >{order.status}</TableCell>
                                    <TableCell >{Moment(order.created).format('MMM Do yyyy')}</TableCell>
                                    <TableCell >{order.phone}</TableCell>
                                    <TableCell >{order.address.city + ", " +
                                      order.address.district + ", " +
                                      order.address.ward + ", " +
                                      order.address.street
                                    }
                                    </TableCell>
                                  </TableRow>,
                                  <TableRow>
                                    <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                                      <Collapse in={this.state.open[index]} timeout="auto" unmountOnExit>
                                        <Box margin={1}>
                                          <Typography variant="h7" gutterBottom component="div">
                                            Order detail
                                          </Typography>
                                          <Table size="small" aria-label="purchases" class="mini-table">
                                            <TableHead>
                                              <TableRow>
                                                <TableCell>ODT_ID</TableCell>
                                                <TableCell width='430px'>Product Name</TableCell>
                                                <TableCell>Price ($)</TableCell>
                                                <TableCell>Amount</TableCell>
                                                <TableCell>Total price ($)</TableCell>
                                              </TableRow>
                                            </TableHead>
                                            <TableBody>
                                              {
                                                order.orderDetails.map(detail => (
                                                  <TableRow key={detail.id}>
                                                    <TableCell component="th" scope="row">
                                                      {detail.id}
                                                    </TableCell>
                                                    <TableCell>{detail.product.name}</TableCell>
                                                    <TableCell>{detail.product.price}</TableCell>
                                                    <TableCell>{detail.amount}</TableCell>
                                                    <TableCell>
                                                      {Math.round(detail.amount * detail.product.price * 100) / 100}
                                                    </TableCell>
                                                  </TableRow>
                                                ))
                                              }
                                            </TableBody>
                                          </Table>
                                        </Box>
                                      </Collapse>
                                    </TableCell>
                                  </TableRow>
                                ]
                              })
                            }
                          </TableBody>
                        </Table>
                      </div>
                  }
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

