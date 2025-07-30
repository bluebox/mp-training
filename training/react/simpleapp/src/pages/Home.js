import React, { useEffect, useState } from "react";
import {useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from "react-redux";
import { deleteCustomer } from "./redux_files/customers";
function Welcome(){

  const navigate = useNavigate();
    const dispatch = useDispatch();
const [localData,setData]=useState([]);


 const customerList = useSelector((state) => state.Myformdata);
 const columns = ['name','age','email','Gender','address']


const handleDelete = (id) => {
dispatch(deleteCustomer(id));
};

const handleEdit = (user) => {
    navigate('/Registration', { state: { user } });
}



return (
<div className='home-body'>
<h1 style={{textAlign:'center'}}>Welcome to our store</h1>


    {customerList.length ===0 ?<p>no data found</p>:
    (
    (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '100px' ,position:'center'}}>

        <thead>
            <tr><td><b>SI.no</b></td>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { customerList.map(( customer,index) => <tr key={customer.id}> <td>{index+1}</td>{columns.map( (field) => (<td key={field}> {customer[field]}</td>))}
                            {<td><button className='nav-button' onClick={() => handleDelete(customer.id)}>Delete</button><button className='edit-button' onClick={() => handleEdit(customer)} >Edit</button></td>}</tr>)}
        </tbody>
    </table>)
    )
    }

</div>)


}export default Welcome;