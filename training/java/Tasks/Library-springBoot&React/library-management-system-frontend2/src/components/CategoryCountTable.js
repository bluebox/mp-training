import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CategoryCountTable = () => {
	const navigate = useNavigate();
	const [data, setData] = useState({});

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/reports/categoryCount")
			.then((res) => setData(res.data))
			.catch((err) => console.error(err));
	}, []);

	return (
		<div style={styles.page}>
			<div style={styles.card}>
				<h2 style={styles.heading}> Count of Books per Category</h2>

				<table style={styles.table}>
					<thead>
						<tr>
							<th style={styles.th}>Category</th>
							<th style={styles.th}>Count</th>
						</tr>
					</thead>
					<tbody>
						{Object.entries(data).map(([category, count], index) => (
							<tr
								key={category}
								style={{
									backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
								}}
							>
								<td style={styles.td}>{category}</td>
								<td style={styles.td}>{count}</td>
							</tr>
						))}
					</tbody>
				</table>


			</div>
		</div>
	);
};

const styles = {
	page: {
		display: "flex",
		justifyContent: "center",
		alignItems: "flex-start",
		backgroundSize: "cover",
		backgroundPosition: "center",
		padding: "20px",
		position: "relative",
	},


	card: {
		backgroundColor: "rgba(255, 255, 255, 0.95)",
		padding: "30px",
		borderRadius: "16px",
		boxShadow: "0 6px 18px rgba(0,0,0,0.25)",
		maxWidth: "600px",
		width: "100%",
		textAlign: "center",
		overflowX: "auto",
	},
	heading: {
		marginBottom: "20px",
		fontSize: "22px",
		fontWeight: "bold",
		color: "#2c3e50",
	},
	table: {
		width: "100%",
		borderCollapse: "collapse",
		marginBottom: "20px",
	},
	th: {
		border: "1px solid #ccc",
		padding: "10px",
		backgroundColor: "#f2f2f2",
		fontWeight: "bold",
	},
	td: {
		border: "1px solid #ccc",
		padding: "10px",
	},
	buttonGroup: {
		display: "flex",
		justifyContent: "space-between",
	},
	secondaryButton: {
		padding: "10px 15px",
		border: "none",
		borderRadius: "8px",
		backgroundColor: "#2980b9",
		color: "white",
		cursor: "pointer",
		fontSize: "14px",
		transition: "0.3s",
	},
};

export default CategoryCountTable;
