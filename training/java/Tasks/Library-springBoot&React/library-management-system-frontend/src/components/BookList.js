import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const BookList = () => {
	const navigate = useNavigate();
	const [books, setBooks] = useState([]);

	const fetchBooks = () => {
		axios
			.get("http://localhost:8080/library/books/allBooks")
			.then((res) => setBooks(res.data))
			.catch((err) => console.error(err));
	};

	const deleteBook = (id) => {
		if (window.confirm("Are you sure you want to delete this book?")) {
			axios
				.delete(`http://localhost:8080/library/books/delete/${id}`)
				.then(() => fetchBooks())
				.catch((err) => console.error(err));
		}
	};

	useEffect(() => {
		fetchBooks();
	}, []);

	return (
		<div style={styles.page}>
			<div style={styles.card}>
				<h2 style={styles.heading}> Book List</h2>

				<table style={styles.table}>
					<thead>
						<tr>
							<th style={styles.th}>ID</th>
							<th style={styles.th}>Title</th>
							<th style={styles.th}>Author</th>
							<th style={styles.th}>Category</th>
							<th style={styles.th}>Status</th>
							<th style={styles.th}>Availability</th>
							<th style={styles.th}>Action</th>
						</tr>
					</thead>
					<tbody>
						{books.map((book, index) => (
							<tr
								key={book.bookId}
								style={{
									backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
								}}
							>
								<td style={styles.td}>{book.bookId}</td>
								<td style={styles.td}>{book.title}</td>
								<td style={styles.td}>{book.author}</td>
								<td style={styles.td}>{book.category}</td>
								<td style={styles.td}>{book.status}</td>
								<td style={styles.td}>{book.availability}</td>
								<td style={styles.td}>
									<button
										style={styles.deleteButton}
										onClick={() => deleteBook(book.bookId)}
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

export default BookList;
