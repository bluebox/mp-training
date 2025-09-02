import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

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
		<div class="container text-center">
			<h2 className="mb-3"> Book Management</h2>
			<div className="mb-2">
			<Button variant="outline-success" onClick={() => navigate("/library/books/add")}>Add Book</Button>{' '}
			</div>
			<Table striped bordered hover size="sm">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>Category</th>
						<th>Status</th>
						<th>Availability</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					{books.map((book, index) => (
						<tr key={book.bookId}>
							<td>{book.bookId}</td>
							<td>{book.title}</td>
							<td>{book.author}</td>
							<td>{book.category}</td>
							<td>Active</td>
							<td>
								{book.availability === "I" ? "Issued" : book.availability === "A" ? "Available" : book.availability}
							</td>
							<td>
								<Button variant="outline-primary" onClick={() => navigate(`/library/books/update/${book.bookId}`)}>Update</Button>{' '}
								<Button variant="outline-danger" disabled={book.availability === "I"} onClick={() => deleteBook(book.bookId)}>Delete</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
		</div>
	);
};


export default BookList;
