import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

const MemberList = () => {
	const navigate = useNavigate();
	const [members, setMembers] = useState([]);

	const fetchMembers = () => {
		axios
			.get("http://localhost:8080/library/members/allMembers")
			.then((res) => setMembers(res.data))
			.catch((err) => console.error(err));
	};

	const deleteMember = (id) => {
		if (window.confirm("Are you sure you want to delete this member?")) {
			axios
				.delete(`http://localhost:8080/library/members/delete/${id}`)
				.then(() => fetchMembers())
				.catch((err) => console.error(err));
		}
	};

	useEffect(() => {
		fetchMembers();
	}, []);

	return (
		<div class="container text-center">
			<h2 className="mb-3"> Member Management</h2>
			<div className="mb-2">
			<Button variant="outline-success" onClick={() => navigate("/library/members/add")}>Add Member</Button>{' '}
			</div>
			<Table striped bordered hover size="sm">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Gender</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					{members.map((member, index) => (
						<tr key={member.memberId}>
							<td>{member.memberId}</td>
							<td>{member.memberName}</td>
							<td>{member.memberMail}</td>
							<td>{member.mobileNo}</td>
							<td> {member.gender === "F" ? "Female" : member.gender === "M" ? "Male" : member.gender} </td>
							<td>{member.memberAddress}</td>
							<td>
								<Button variant="outline-primary" onClick={() => navigate(`/library/members/update/${member.memberId}`)}>Update</Button>{' '}
								<Button variant="outline-danger" onClick={() => deleteMember(member.memberId)}>Delete</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
		</div>
	);
};

export default MemberList;
