import React from "react";
import { useNavigate } from "react-router-dom";

const ReportsHome = () => {
	const navigate = useNavigate();

	return (
		<div style={styles.page}>
			<button style={styles.backBtn} onClick={() => navigate("/library")}>
				 Back
			</button>
			<div style={styles.container}>
				<h1 style={styles.heading}> Reports</h1>
				<button
					style={styles.btn}
					onClick={() => navigate("/library/reports/overdueRecords")}
				>
					 List of Overdue Books
				</button>

				<button
					style={styles.btn}
					onClick={() => navigate("/library/reports/categoryCount")}
				>
					 Count of Books per Category
				</button>

				<button
					style={styles.btn}
					onClick={() => navigate("/library/reports/activeIssuedRecords")}
				>
					 Members with Active Issued Books
				</button>
			</div>
		</div>
	);
};

const styles = {
	page: {
		backgroundSize: "cover",
		backgroundPosition: "center",
		minHeight: "100vh",
		display: "flex",
		justifyContent: "center",
		alignItems: "center",
		position: "relative",
	},
	backBtn: {
		position: "absolute",
		top: "15px",
		left: "15px",
		padding: "6px 12px",
		backgroundColor: "rgba(255,255,255,0.8)",
		color: "#0d47a1",
		fontSize: "14px",
		fontWeight: "bold",
		border: "none",
		borderRadius: "6px",
		cursor: "pointer",
		zIndex: 10,
	},
	container: {
		backgroundColor: "rgba(255,255,255,0.95)",
		padding: "25px 40px",
		borderRadius: "12px",
		boxShadow: "0 6px 12px rgba(0,0,0,0.3)",
		textAlign: "center",
		width: "360px",
	},
	heading: {
		fontSize: "22px",
		color: "#0d47a1",
		marginBottom: "20px",
		borderBottom: "2px solid #90caf9",
		display: "inline-block",
		paddingBottom: "8px",
	},
	btn: {
		display: "block",
		margin: "10px auto",
		padding: "12px 20px",
		fontSize: "16px",
		fontWeight: "bold",
		borderRadius: "8px",
		border: "none",
		cursor: "pointer",
		backgroundColor: "#3498db",
		color: "white",
		transition: "0.3s",

	},
};

export default ReportsHome;
