import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ReturnRecord = () => {
	const navigate = useNavigate();
	const [members, setMembers] = useState([]);
	const [books, setBooks] = useState([]);
	const [selectedMemberId, setSelectedMemberId] = useState("");
	const [selectedBookId, setSelectedBookId] = useState("");
	const [message, setMessage] = useState(null);
	const [status, setStatus] = useState("");

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/members/allMembers")
			.then((res) => setMembers(res.data))
			.catch((err) => console.error(err));

		axios
			.get("http://localhost:8080/library/books/allBooks")
			.then((res) => setBooks(res.data))
			.catch((err) => console.error(err));
	}, []);

	const handleSubmit = (e) => {
		e.preventDefault();

		if (!selectedMemberId || !selectedBookId) {
			setMessage("❌ Please select both member and book!");
			setStatus("error");
			return;
		}

		axios
			.post("http://localhost:8080/library/issues/return", {
				memberId: selectedMemberId,
				bookId: selectedBookId,
			})
			.then((res) => {
				setMessage("✅ Book returned successfully!");
				setStatus("success");
				setSelectedMemberId("");
				setSelectedBookId("");
			})
			.catch((err) => {
				console.error(err);
				const backendMessage =
					err.response?.data?.message || "❌ Failed to return book. Please try again.";
				setMessage(backendMessage);
				setStatus("error");
			});
	};

	return (
		<div style={styles.page}>
			<div style={styles.card}>
				{message && (
					<div
						style={{
							...styles.popup,
							backgroundColor: status === "success" ? "#c8e6c9" : "#ffcdd2",
							color: status === "success" ? "#2e7d32" : "#c62828",
							display: "block",
						}}
					>
						{message}
					</div>
				)}

				<h2 style={styles.heading}> Return Book</h2>

				<form onSubmit={handleSubmit}>
					<label style={styles.label}>Select Member:</label>
					<select
						value={selectedMemberId}
						onChange={(e) => setSelectedMemberId(e.target.value)}
						required
						style={styles.select}
					>
						<option value=""> Select Member </option>
						{members.map((m) => (
							<option key={m.memberId} value={m.memberId}>
								{m.memberName}
							</option>
						))}
					</select>

					<label style={styles.label}>Select Book:</label>
					<select
						value={selectedBookId}
						onChange={(e) => setSelectedBookId(e.target.value)}
						required
						style={styles.select}
					>
						<option value=""> Select Book </option>
						{books.map((b) => (
							<option key={b.bookId} value={b.bookId}>
								{b.title} - {b.author}
							</option>
						))}
					</select>

					<button type="submit" style={styles.btn}>
						 Return Book
					</button>
				</form>

				<div style={styles.buttonGroup}>
					<button
						style={styles.secondaryButton}
						onClick={() => navigate("/library/issues")}
					>
						 Back
					</button>
					<button
						style={styles.secondaryButton}
						onClick={() => navigate("/library")}
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
		maxWidth: "600px",
		width: "100%",
		textAlign: "center",
	},
	heading: {
		marginBottom: "20px",
		fontSize: "22px",
		fontWeight: "bold",
		color: "#2c3e50",
	},
	label: {
		display: "block",
		marginTop: "12px",
		fontSize: "14px",
		fontWeight: "bold",
		color: "#333",
		textAlign: "left",
	},
	select: {
		width: "100%",
		padding: "8px",
		marginTop: "4px",
		fontSize: "14px",
		border: "1px solid #ccc",
		borderRadius: "6px",
		outline: "none",
		fontFamily: "inherit",
	},
	btn: {
		marginTop: "20px",
		padding: "10px",
		width: "100%",
		fontSize: "14px",
		borderRadius: "6px",
		border: "none",
		cursor: "pointer",
		backgroundColor: "#64b5f6",
		color: "white",
		transition: "background-color 0.3s ease",
		fontWeight: "bold",
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
	buttonGroup: {
		display: "flex",
		justifyContent: "space-between",
		marginTop: "15px",
	},
	popup: {
		position: "fixed",
		top: "30px",
		left: "50%",
		transform: "translateX(-50%)",
		padding: "12px 25px",
		fontSize: "13px",
		fontWeight: "bold",
		borderRadius: "8px",
		boxShadow: "0 4px 12px rgba(0,0,0,0.2)",
		zIndex: 9999,
	},
};

export default ReturnRecord;
