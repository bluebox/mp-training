import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const MemberList = () => {
	const navigate = useNavigate();
	const [members, setMembers] = useState([]);

	const fetchMembers = () => {
		axios
			.get("http://localhost:8080/library/members/allMembers")
			.then((res) => setMembers(res.data))
			.catch((err) => console.error(err));
	};

	const deleteBook = (id) => {
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
		<div style={styles.page}>
			<div style={styles.card}>
				<h2 style={styles.heading}> Members List</h2>

				<table style={styles.table}>
					<thead>
						<tr>
							<th style={styles.th}>ID</th>
							<th style={styles.th}>Name</th>
							<th style={styles.th}>Email</th>
							<th style={styles.th}>Mobile</th>
							<th style={styles.th}>Gender</th>
							<th style={styles.th}>Address</th>
							<th style={styles.th}>Action</th>
						</tr>
					</thead>
					<tbody>
						{members
							.map((member, index) => (
								<tr
									key={member.memberId}
									style={{
										backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
									}}
								>
									<td style={styles.td}>{member.memberId}</td>
									<td style={styles.td}>{member.memberName}</td>
									<td style={styles.td}>{member.memberMail}</td>
									<td style={styles.td}>{member.mobileNo}</td>
									<td style={styles.td}>
									{member.gender === "F" ? "Female" : member.gender === "M" ? "Male" : member.gender}
									</td>
									<td style={styles.td}>{member.memberAddress}</td>
									<td style={styles.td}>
										<button
											style={styles.deleteButton}
											onClick={() => deleteBook(member.memberId)}
										>
											 Delete
										</button>
									</td>
								</tr>
							))}
					</tbody>
				</table>

				<div style={styles.buttonGroup}>
					<button
						onClick={() => navigate("/library/members")}
						style={styles.secondaryButton}
					>
						 Back
					</button>
					<button
						onClick={() => navigate("/library")}
						style={styles.secondaryButton}
					>
						 Home
					</button>
				</div>
			</div>
		</div>
	);
};

const styles = {
	page: {
		minHeight: "100vh",
		display: "flex",
		justifyContent: "center",
		alignItems: "center",
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
		maxWidth: "900px",
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
	deleteButton: {
		padding: "6px 12px",
		border: "none",
		borderRadius: "6px",
		backgroundColor: "#e74c3c",
		color: "white",
		cursor: "pointer",
		fontSize: "14px",
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

export default MemberList;
