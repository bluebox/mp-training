import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
		<div style={styles.page}>
			<div style={styles.card}>
				<h2 style={styles.heading}> Issue/ Return Record</h2>
				<div style={{ display: "flex", gap: "10px", justifyContent: "center", marginBottom: "20px" }}>
					<button
						style={styles.addButton}
						onClick={() => navigate("/library/issues/issue")}
					>
						Issue Book
					</button>
					<button
						style={styles.addButton}
						onClick={() => navigate("/library/issues/return")}
					>
						Return Book
					</button>
				</div>


				<table style={styles.table}>
					<thead>
						<tr>
							<th style={styles.th}>Issue ID</th>
							<th style={styles.th}>Member ID</th>
							<th style={styles.th}>Book ID</th>
							<th style={styles.th}>Status</th>
							<th style={styles.th}>Issue Date</th>
							<th style={styles.th}>Return Date</th>
						</tr>
					</thead>
					<tbody>
						{issues.map((r, index) => (
							<tr
								key={index}
								style={{
									backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
								}}
							>
								<td style={styles.td}>{r.issueId}</td>
								<td style={styles.td}>{r.memberId}</td>
								<td style={styles.td}>{r.bookId}</td>
								<td style={styles.td}>
									{r.status === "I" ? "Issued" : r.status === "R" ? "Returned" : r.status}
								</td>
								<td style={styles.td}>{r.issueDate}</td>
								<td style={styles.td}>{r.returnDate}</td>
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
		maxWidth: "1000px",
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
	addButton: {
		padding: "8px 16px",
		border: "none",
		borderRadius: "8px",
		backgroundColor: "#2ecc71",
		color: "white",
		cursor: "pointer",
		fontSize: "14px",
		marginBottom: "20px",
	},
};

export default IssueList;
