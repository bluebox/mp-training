import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AddBook = ({ onBookAdded }) => {
	const [title, setTitle] = useState("");
	const [author, setAuthor] = useState("");
	const [category, setCategory] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");
	const navigate = useNavigate();

	const categories = [
		"Fiction",
		"Non Fiction",
		"Mystery",
		"Romance",
		"Science Fiction",
		"Fantasy",
		"Horror",
		"Thriller"
	];

	const handleSubmit = (e) => {
		e.preventDefault();
		axios
			.post("http://localhost:8080/library/books/add", {
				title,
				author,
				category,
			})
			.then((res) => {
				setMessage(`✅ Book "${res.data.title}" added successfully!`);
				setMessageType("success");
				if (onBookAdded) onBookAdded();
				setTitle("");
				setAuthor("");
				setCategory("");
			})
			.catch((err) => {
				console.error(err);
				if (err.response && err.response.data && err.response.data.errors) {
					const messages = err.response.data.errors
						.map(e => `${e.field}: ${e.defaultMessage}`)
						.join("\n");
					setMessage(`❌ ${messages}`);
				} else {
					setMessage("❌ Failed to add member. Please try again.");
				}
				setMessageType("error");
			});
	};

	return (
		<div style={styles.page}>
			<div style={styles.card}>
				<h2 style={styles.heading}> Add a New Book</h2>
				{message && (
					<div
						style={{
							marginBottom: "15px",
							padding: "10px",
							borderRadius: "8px",
							fontWeight: "bold",
							color: "white",
							backgroundColor: messageType === "success" ? "#2ecc71" : "#e74c3c",
						}}
					>
						{message}
					</div>
				)}

				<form onSubmit={handleSubmit} style={styles.form}>
					<label style={styles.label}>Title:</label>
					<input
						placeholder="Enter title"
						value={title}
						onChange={(e) => setTitle(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Author:</label>
					<input
						placeholder="Enter author"
						value={author}
						onChange={(e) => setAuthor(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Category:</label>
					<select
						value={category}
						onChange={(e) => setCategory(e.target.value)}
						required
						style={styles.input}
					>
						<option value="">Select Category</option>
						{categories.map((cat, i) => (
							<option key={i} value={cat}>
								{cat}
							</option>
						))}
					</select>

					<button type="submit" style={styles.button}>
						Add Book
					</button>
				</form>

				<div style={styles.buttonGroup}>
					<button
						onClick={() => navigate("/library/books")}
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
		height: "100vh",
		display: "flex",
		justifyContent: "center",
		alignItems: "center",
		backgroundSize: "cover",
		backgroundPosition: "center",
		padding: "20px",
	},
	card: {
		backgroundColor: "rgba(255, 255, 255, 0.95)",
		padding: "30px",
		borderRadius: "16px",
		boxShadow: "0 6px 18px rgba(0,0,0,0.25)",
		maxWidth: "400px",
		width: "100%",
		textAlign: "center",
	},
	heading: {
		marginBottom: "20px",
		fontSize: "22px",
		fontWeight: "bold",
		color: "#2c3e50",
	},
	form: {
		display: "flex",
		flexDirection: "column",
		gap: "15px",
	},
	label: {
		textAlign: "left",
		fontSize: "14px",
		fontWeight: "bold",
		color: "#2c3e50",
	},
	input: {
		padding: "12px",
		borderRadius: "8px",
		border: "1px solid #ccc",
		fontSize: "15px",
		outline: "none",
	},
	button: {
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
		marginTop: "15px",
		display: "flex",
		justifyContent: "space-between",
	},
};

export default AddBook;
