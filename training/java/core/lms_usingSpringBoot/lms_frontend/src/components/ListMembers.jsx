import React, { useState, useEffect } from 'react'
import { listMembers } from '../services/MemberServices'
import { useNavigate } from 'react-router-dom'
const ListMembers = () => {
    const [members, setMembers] = useState([])
const navigate = useNavigate();
    const handleUpdateClick = (id) => {
        navigate(`/updatemember/${id}`); 
    };

    useEffect(() => {
        listMembers().then((response) => {
            setMembers(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])
    return (
        <div className='container'>
            <h2 className='text-center'>List Of Members</h2>
            <div style={{ maxHeight: '500px', overflowY: 'auto' }}>
                <table className='books-table'>
                    <thead>
                        <tr>
                            <th>Member Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile No</th>
                            <th>Gender</th>
                            <th>Address</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            members.map(member =>
                                <tr key={member.member_Id}>
                                    <td>{member.member_Id}</td>
                                    <td>{member.member_Name}</td>
                                    <td>{member.email}</td>
                                    <td>{member.mobile_No}</td>
                                    <td>{member.gender}</td>
                                    <td>{member.address}</td>
                                    <td><button  style={{backgroundColor: '#4B0082', color: 'white' }} onClick={() => handleUpdateClick(member.member_Id)}>update</button></td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>

    )
}

export default ListMembers