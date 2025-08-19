import React, { useState, useEffect } from 'react'
import { getRecords } from '../services/IssueBookService'
const IssuedRecords = () => {
    const [records, setRecords] = useState([])

    useEffect(() => {
        getRecords().then((response) => {
            setRecords(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    return (
        <div className='container'>
            <h2 className='text-center'>List Of Issued Records</h2>
            <div style={{ maxHeight: '500px', overflowY: 'auto' }}>
                <table className='books-table'>
                    <thead>
                        <tr>
                            <th>Issue ID</th>
                            <th>Book ID</th>
                            <th>Member ID</th>
                            <th>Status</th>
                            <th>Issued Date</th>
                            <th>Return Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            records.map(record =>
                                <tr key={record.issueId}>
                                    <td>{record.issueId}</td>
                                    <td>{record.bookId}</td>
                                    <td>{record.memberId}</td>
                                    <td>{record.status}</td>
                                    <td>{record.issueDate}</td>
                                    <td>{(record.returnDate)?record.returnDate:"Not Yet Returned"}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>

        </div>


    )
}

export default IssuedRecords