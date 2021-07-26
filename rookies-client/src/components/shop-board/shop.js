import React from 'react'
import { get } from "../../HttpHelper";
import { Button, Table } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import Carousel from 'react-gallery-carousel';
import 'react-pro-sidebar/dist/scss/styles.scss';

import './shop.scss'
import crown from '../../assets/crown.svg'
import research from '../../assets/research.svg'

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
    }
  }

  async componentDidMount() {
    await get('/api/v1/shop/products')
      .then(response => {
        this.setState({
          products: response.data
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
                <MenuItem>Add new product</MenuItem>
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
                <th><img style={{height: 30}} src={research}/></th>
              </tr>
            </thead>
            <tbody>
              {
                this.state.products.map((product, index) => {
                  { console.log(product) }
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
                    sizes.push("[" + size.size_name+ "] ")
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
                        }} src={research}/></td>
                    </tr>
                  )
                })
              }
            </tbody>
          </Table>
        </div>
      </div>
    )
  }
}

export default withRouter(Shop)