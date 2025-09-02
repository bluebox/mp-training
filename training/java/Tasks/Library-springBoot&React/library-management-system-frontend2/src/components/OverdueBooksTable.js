import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';

const OverdueBooksTable = () => {
	const navigate = useNavigate();
	const [records, setRecords] = useState([]);

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/reports/overdueRecords")
			.then((res) => setRecords(res.data))
			.catch((err) => console.error(err));
	}, []);

	return (
		<div class="container text-center">
			<h2 className="mb-3">Overdue Books</h2>
			<Table striped bordered hover size="sm">
				<thead>
					<tr>
						<th>Member ID</th>
						<th>Member Name</th>
						<th>Book ID</th>
						<th>Book Title</th>
						<th>Issue Date</th>
					</tr>
				</thead>
				<tbody>
					{records.map((r, index) => (
						<tr key={index}>
							<td>{r.memberId}</td>
							<td>{r.memberName}</td>
							<td>{r.bookId}</td>
							<td >{r.bookTitle}</td>
							<td>{r.issueDate}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</div>
	);
};

export default OverdueBooksTable;
