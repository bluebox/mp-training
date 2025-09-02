import React, { useEffect, useState } from "react";
import axios from "axios";
import Table from 'react-bootstrap/Table';

const CategoryCountTable = () => {
	const [data, setData] = useState({});

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/reports/categoryCount")
			.then((res) => setData(res.data))
			.catch((err) => console.error(err));
	}, []);

	return (
		<div class="container text-center">
			<h2 className="mb-3"> Count of Books per Category</h2>
			<Table striped bordered hover size="sm">
				<thead>
					<tr>
						<th>Catgeory</th>
						<th>Count</th>
					</tr>
				</thead>
				<tbody>
					{Object.entries(data).map(([category, count], index) => (
						<tr key={category}>
							<td>{category}</td>
							<td>{count}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</div>
	);
};

export default CategoryCountTable;
