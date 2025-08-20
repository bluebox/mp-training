import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const UpdateBook = () => {
	const navigate = useNavigate();
	const [books, setBooks] = useState([]);
	const [searchTerm, setSearchTerm] = useState("");
	const [suggestions, setSuggestions] = useState([]);
	const [bookId, setBookId] = useState(null);
	const [title, setTitle] = useState("");
	const [author, setAuthor] = useState("");
	const [category, setCategory] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");

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

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/books/allBooks")
			.then((res) => setBooks(res.data))
			.catch((err) => console.error(err));
	}, []);

	const handleSearch = (e) => {
		const term = e.target.value.toLowerCase();
		setSearchTerm(term);
		if (term.trim() === "") {
			setSuggestions([]);
			return;
		}
		const filtered = books.filter(
			(b) =>
				b.title.toLowerCase().includes(term) ||
				b.author.toLowerCase().includes(term)
		);
		setSuggestions(filtered);
	};

	const selectBook = (book) => {
		console.log("Selected book:", book);
		setBookId(book.bookId);
		setTitle(book.title);
		setAuthor(book.author);
		setCategory(book.category);
		setSearchTerm(`${book.title} - ${book.author}`);
		setSuggestions([]);
	};

	const validateInput = (value, type) => {
		let clean = value.replace(/\s+/g, " ");
		if (type === "title") clean = clean.replace(/[^a-zA-Z0-9 ]/g, "");
		if (type === "author") clean = clean.replace(/[^a-zA-Z ]/g, "");
		return clean.substring(0, 60);
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		if (!bookId) {
			setMessage("❌ Please select a valid book to update!");
			setMessageType("error");
			return;
		}
		console.log("Submitting update:", {
			title: title.trim(),
			author: author.trim(),
			category
		});

		axios
			.put(`http://localhost:8080/library/books/update/${bookId}`, {
				title: title.trim(),
				author: author.trim(),
				category,
			})
			.then((res) => {
				console.log("Update response:", res);
				if (res && res.data) {
					setMessage(`✅ Book "${res.data.title}" updated successfully!`);
					setMessageType("success");
				} else {
					setMessage("✅ Book updated successfully!");
					setMessageType("success");
				}
			})
			.catch((err) => {
				console.error(err);
				const backendMessage =
					err.response?.data?.message || "Failed to update book. Please try again.";
				setMessage(`❌ ${backendMessage}`);
				setMessageType("error");
			});
	};

	return (
		<div style={styles.page}>
			{message && (
				<div
					style={{
						position: "absolute",
						top: "10%",
						backgroundColor: messageType === "success" ? "#c8e6c9" : "#ffcdd2",
						color: messageType === "success" ? "#2e7d32" : "#c62828",
						padding: "10px 20px",
						borderRadius: "8px",
						boxShadow: "0 4px 10px rgba(0,0,0,0.2)",
					}}
				>
					{message}
				</div>
			)}

			<div style={styles.card}>
				<h2 style={styles.heading}> Update Book</h2>

				<form onSubmit={handleSubmit} style={styles.form}>
					<label style={styles.label}>Search Book:</label>
					<input
						type="text"
						value={searchTerm}
						onChange={handleSearch}
						placeholder="Search by title or author"
						style={styles.input}
					/>
					{suggestions.length > 0 && (
						<div style={styles.suggestionBox}>
							{suggestions.map((b) => (
								<div
									key={b.bookId}
									onClick={() => selectBook(b)}
									style={styles.suggestionItem}
								>
									{b.title} - {b.author}
								</div>
							))}
						</div>
					)}

					<label style={styles.label}>Title:</label>
					<input
						type="text"
						value={title}
						onChange={(e) => setTitle(validateInput(e.target.value, "title"))}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Author:</label>
					<input
						type="text"
						value={author}
						onChange={(e) =>
							setAuthor(validateInput(e.target.value, "author"))
						}
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
						 Update Book
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
		maxWidth: "400px",
		width: "100%",
		textAlign: "center",
	},
	heading: { marginBottom: "20px", fontSize: "22px", fontWeight: "bold" },
	form: { display: "flex", flexDirection: "column", gap: "15px" },
	label: { textAlign: "left", fontSize: "14px", fontWeight: "bold" },
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
	},
	buttonGroup: { marginTop: "15px", display: "flex", justifyContent: "space-between" },
	suggestionBox: {
		background: "white",
		border: "1px solid #ccc",
		borderRadius: "6px",
		maxHeight: "150px",
		overflowY: "auto",
		marginTop: "2px",
	},
	suggestionItem: { padding: "6px 10px", cursor: "pointer" },
};

export default UpdateBook;
