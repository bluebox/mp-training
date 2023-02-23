import React, { useState, useEffect } from "react";
import { Table } from 'react-bootstrap';
import { getInstructors } from './service/InstructorService';


const TestPage = () => {
    const [instructor, setInstructor] = useState([]);

    useEffect(() => {
        let mounted = true;
        getInstructors()
            .then(data => {
                if (mounted) {
                    setInstructor(data)
                }
            })
        return () => mounted = false;
    }, []);
    return (
        <div>
            <h3>this is test page</h3>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>E-mail</th>
                        <th>Password</th>
                        <th>Qualification</th>
                        <th>Designation</th>
                        <th>Contact</th>
                        <th>Skills</th>
                    </tr>
                </thead>
                <tbody>
                    {instructor.map((ins) =>
                        <tr key={ins.id}>
                            <td>{ins.id}</td>
                            <td>{ins.first_name}</td>
                            <td>{ins.last_name}</td>
                            <td>{ins.email}</td>
                            <td>{ins.password}</td>
                            <td>{ins.qualification}</td>
                            <td>{ins.designation}</td>
                            <td>{ins.mobile_no}</td>
                            <td>{ins.skills}</td>
                        </tr>
                    )}

                </tbody>
            </Table>
        </div>

    );
};

export default TestPage;