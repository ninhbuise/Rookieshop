import React from 'react'
import { get } from "../../HttpHelper";
import { Table } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/scss/styles.scss';

import './admin-board.scss'
import crown from '../../assets/crown.svg'

class AdminBoard extends React.Component {
  constructor(props) {
    super(props)
    const roles = JSON.parse(localStorage.getItem('roles'))
    if (roles !== null)
      roles.map(role => {
        if (role !== "ROLE_ADMIN")
          this.props.history.push('/')
      })
    else this.props.history.push('/')
    this.state = {
      users: [],
    }
  }

  async componentDidMount() {
    await get('/api/v1/users')
      .then(response => {
        this.setState({
          users: response.data
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
                <MenuItem>Add User</MenuItem>
                <MenuItem onClick={() => this.handleLogout()} >Logout</MenuItem>
              </SubMenu>
            </Menu>
          </ProSidebar>
        </div>
        <Table>
          <thead>
            <tr>
              <th>#</th>
              <th>UID</th>
              <th>Username</th>
              <th>Role</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {
              this.state.users.map((user, index) => {
                { console.log(user) }
                return (
                  <tr>
                    <th scope="row">{index + 1}</th>
                    <td>{user.id}</td>
                    <td>{user.username}</td>
                    <td>{user.roles[0].name}</td>
                    <td>{user.status}</td>
                  </tr>
                )
              })
            }
          </tbody>
        </Table>
      </div>
    )
  }
}

export default withRouter(AdminBoard)