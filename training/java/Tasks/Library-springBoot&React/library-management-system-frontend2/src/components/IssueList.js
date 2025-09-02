import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

const IssueList = () => {
	const navigate = useNavigate();
	const [issues, setIssues] = useState([]);

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/issues/allIssues")
			.then((res) => setIssues(res.data))
			.catch((err) => console.error(err));
	}, []);

	return (
		<div class="container text-center">
			<h2 className="mb-3">Issue Book / Return Book</h2>
			<div className="mb-2">
			<Button variant="outline-primary" onClick={() => navigate(`/library/issues/issue`)}>Issue Book</Button>{' '}
			<Button variant="outline-warning" onClick={() => navigate(`/library/issues/return`)}>Return Book</Button>
			</div>
			<div style={{ maxHeight: "500px", overflowY: "auto" }}>
			<Table striped bordered hover size="sm">
				<thead style={{ position: "sticky", top: 0, backgroundColor: "#f8f9fa", zIndex: 1 }}>
					<tr>
						<th>Issue ID</th>
						<th>Member ID</th>
						<th>Book ID</th>
						<th>Status</th>
						<th>Issue Date</th>
						<th>Return Date</th>
					</tr>
				</thead>
				<tbody>
					{issues.map((r, index) => (
						<tr key={index}>
							<td>{r.issueId}</td>
								<td>{r.memberId}</td>
								<td>{r.bookId}</td>
								<td>
									{r.status === "I" ? "Issued" : r.status === "R" ? "Returned" : r.status}
								</td>
								<td>{r.issueDate}</td>
								<td>{r.returnDate}</td>
						</tr>
					))}
					
				</tbody>
			</Table>
			</div>
		</div>						
	);
};

export default IssueList;
