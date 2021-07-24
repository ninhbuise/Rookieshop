import React from 'react'
import Cookies from 'universal-cookie';
import { get } from "../../HttpHelper";

class AdminBoard extends React.Component {
    constructor(props) {
       
    }

    async componentDidMount() {
        await get('/api/v1/users')
        .then(response => {
            console.log(response)
        })
        .catch(error =>{
            console.error(error)
        })
    }

    render() {
        return (
            <div>

            </div>
        )
    }
}

export default AdminBoard