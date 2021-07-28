import React from 'react'
import { get } from "../../HttpHelper";
import { Button, Table } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import Carousel from 'react-gallery-carousel';


import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle'
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';

import './shop.scss'
import crown from '../../assets/crown.svg'
import research from '../../assets/research.svg'
import 'react-pro-sidebar/dist/scss/styles.scss';

class Shop extends React.Component {
  constructor(props) {
    super(props)
    const roles = JSON.parse(localStorage.getItem('roles'))
    if (roles !== null)
      roles.map(role => {
        if (role !== "ROLE_STORE")
          this.props.history.push('/')
      })
    else this.props.history.push('/')
    this.state = {
      products: [],
      sizes: [],
      colors: [],
      types: [],
      ishow: false
    }
  }

  componentDidMount() {
    //get shop's product list
    get('/api/v1/shop/products')
      .then(response => {
        this.setState({
          products: response.data
        })
      })
      .catch(error => {
        console.error(error)
      })
    //get product types
    get('/api/shop/type')
      .then(response => {
        this.setState({
          types: response.data
        })
      })
      .catch(error => {
        console.error(error)
      })
    //get product colors
    get('/api/shop/color')
      .then(response => {
        this.setState({
          colors: response.data
        })
      })
      .catch(error => {
        console.error(error)
      })
    //get product sizes
    get('/api/shop/size')
      .then(response => {
        this.setState({
          sizes: response.data
        })
      })
      .catch(error => {
        console.error(error)
      })
  }

  handleLogout() {
    localStorage.removeItem('cart')
    localStorage.removeItem('roles')
    localStorage.removeItem('user')
    localStorage.removeItem('token')
    this.props.history.push('/')
  }

  render() {
    return (
      <div class="app">
        <div className='sidebar'>
          <ProSidebar >
            <Menu iconShape="square">
              <MenuItem icon={<img src={crown} />}>Dashboard</MenuItem><hr></hr>
              <SubMenu title="Setting" icon="</>">
                <MenuItem onClick={() => this.setState({ ishow: true })}>Add new product</MenuItem>
                <MenuItem onClick={() => this.handleLogout()} >Logout</MenuItem>
              </SubMenu>
            </Menu>
          </ProSidebar>
        </div>
        <div class="table-wrapper-scroll-y my-custom-scrollbar">
          <Table class="table table-bordered table-striped mb-0">
            <thead>
              <tr>
                <th>#</th>
                <th>PID</th>
                <th className="col-md-1">Image</th>
                <th className="col-md-3">Product name</th>
                <th>Type</th>
                <th>Size</th>
                <th>Color</th>
                <th>Price</th>
                <th>Quantity</th>
                <th><img style={{ height: 30 }} src={research} /></th>
              </tr>
            </thead>
            <tbody>
              {
                this.state.products.map((product, index) => {
                  const images = []
                  const sizes = []
                  const colors = []
                  product.images.map(img => {
                    images.push({ src: img.url })
                  });
                  product.colors.map(color => {
                    colors.push("[" + color.color_name + "] ")
                  });
                  product.sizes.map(size => {
                    sizes.push("[" + size.size_name + "] ")
                  });
                  return (
                    <tr>
                      <th scope="row">{index + 1}</th>
                      <td>{product.id}</td>
                      <td>
                        <Carousel images={images}
                          style={{
                            backgroundColor: "#fff",
                            autoPlay: true,
                            infiniteLoop: true,
                            width: '200px',
                            height: '200px',
                            objectFit: 'cover'
                          }}
                          hasMediaButton={false}
                          hasIndexBoard={false}
                        />
                      </td>
                      <td>{product.name}</td>
                      <td>{product.productType.product_type}</td>
                      <td>{sizes}</td>
                      <td>{colors}</td>
                      <td>{product.price} $</td>
                      <td>{product.quantity}</td>
                      <td><img style={{
                        height: 30,
                        cursor: 'pointer'
                      }} src={research} /></td>
                    </tr>
                  )
                })
              }
            </tbody>
          </Table>
          <Dialog open={this.state.ishow} aria-labelledby="form-dialog-title">
            <DialogTitle id="form-dialog-title">New Product!</DialogTitle>
            <DialogContent>
              <DialogContentText>
                Product info
              </DialogContentText>
              <ValidatorForm
                ref="form"
                onSubmit={() => this.setState({ ishow: false })}
                noValidate={true}
              >
                <TextValidator
                  value=''
                  validators={['required']}
                  errorMessages={['this field is required']}
                  onChange={(event) => {
                    this.setState({

                    })
                  }}
                  label="Name"
                  type="text" />
                <FormControlLabel
                  control={<Checkbox name="antoine" />}
                  label="Antoine Llorca"
                />
                <Checkbox
                  value="checkedA"
                  inputProps={{ 'aria-label': 'Checkbox A' }}
                />
                {/* <Select
                  open={open}
                  onClose={handleClose}
                  onOpen={handleOpen}
                  value={age}
                  onChange={handleChange}
                >
                  <MenuItem value="">
                    <em>None</em>
                  </MenuItem>
                  <MenuItem value={10}>Ten</MenuItem>
                  <MenuItem value={20}>Twenty</MenuItem>
                  <MenuItem value={30}>Thirty</MenuItem>
                </Select> */}
                <DialogActions>
                  <Button onClick={() => this.setState({ ishow: false })} >
                    Cancel
                  </Button>
                  <Button type="submit" color="primary">
                    Submit
                  </Button>
                </DialogActions>
              </ValidatorForm>
            </DialogContent>
          </Dialog>
        </div>
      </div>
    )
  }
}

export default withRouter(Shop)