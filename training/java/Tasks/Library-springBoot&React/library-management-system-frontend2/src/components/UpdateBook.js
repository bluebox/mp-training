import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Form, Col, Row, Button, Card, Container, Alert } from "react-bootstrap";

const UpdateBook = () => {
	const navigate = useNavigate();
	const { id } = useParams();
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
			.get(`http://localhost:8080/library/books/${id}`)
			.then((res) => {
				const book = res.data;
				setTitle(book.title);
				setAuthor(book.author);
				setCategory(book.category);
			})
			.catch((err) => {
				console.error(err);
				setMessage("❌ Failed to load book details.");
				setMessageType("error");
			});
	}, [id]);

	const validateInput = (value, type) => {
		let clean = value.replace(/\s+/g, " ");
		if (type === "title") clean = clean.replace(/[^a-zA-Z0-9 ]/g, "");
		if (type === "author") clean = clean.replace(/[^a-zA-Z ]/g, "");
		return clean.substring(0, 60);
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		console.log("Submitting update:", {
			title: title.trim(),
			author: author.trim(),
			category
		});

		axios
			.put(`http://localhost:8080/library/books/update/${id}`, {
				title: title.trim(),
				author: author.trim(),
				category,
			})
			.then((res) => {
				setMessage(`✅ Book "${res.data.title}" updated successfully!`);
				setMessageType("success");
				setTimeout(() => {
					navigate("/library/books/view");
				}, 2000);
			})
			.catch((err) => {
				if (err.response && err.response.data && err.response.data.errors) {
					const messages = err.response.data.errors
						.map((e) => `${e.field}: ${e.defaultMessage}`)
						.join("\n");
					setMessage(`❌ ${messages}`);
				} else {
					setMessage("❌ Failed to update book. Please try again.");
				}
				setMessageType("danger");
			});
	};

	return (
		<Container className="d-flex justify-content-center align-items-center">
			<Card className="shadow-lg p-4" style={{ maxWidth: "500px", width: "100%" }}>
				<h2 className="mb-3">Update Book</h2>

				{message && (
					<Alert variant={messageType} className="fw-bold text-center">
						{message}
					</Alert>
				)}

				<Form onSubmit={handleSubmit}>
					<Form.Group className="mb-3" controlId="formBookTitle">
						<Form.Label>Book Title</Form.Label>
						<Form.Control
							type="text"
							placeholder="Enter book title"
							value={title}
							onChange={(e) => setTitle(validateInput(e.target.value, "title"))}
							required
						/>
					</Form.Group>

					<Form.Group className="mb-3" controlId="formBookAuthor">
						<Form.Label>Author</Form.Label>
						<Form.Control
							type="text"
							placeholder="Enter author name"
							value={author}
							onChange={(e) => setAuthor(validateInput(e.target.value, "author"))}
							required
						/>
					</Form.Group>

					<Form.Group className="mb-3" as={Col} controlId="formGridCategory">
						<Form.Label>Category</Form.Label>
						<Form.Control
							as="select"
							value={category}
							onChange={(e) => setCategory(e.target.value)}
							required
						>
							<option value="">Select Category</option>
							{categories.map((cat, i) => (
								<option key={i} value={cat}>
									{cat}
								</option>
							))}
						</Form.Control>
					</Form.Group>

					<Row>
						<Col className="d-grid">
							<Button variant="outline-primary" type="submit">
								Update Book
							</Button>
						</Col>
					</Row>
				</Form>
			</Card>
		</Container>
	);
};

export default UpdateBook;
