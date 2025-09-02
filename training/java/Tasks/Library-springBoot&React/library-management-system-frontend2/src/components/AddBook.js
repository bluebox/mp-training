import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Col, Row, Button, Card, Container, Alert } from "react-bootstrap";

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
		"Thriller",
	];

	const validateInput = (value, type) => {
		let clean = value.replace(/\s+/g, " ");
		if (type === "title") clean = clean.replace(/[^a-zA-Z0-9 ]/g, "");
		if (type === "author") clean = clean.replace(/[^a-zA-Z ]/g, "");
		return clean.substring(0, 60);
	};

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
				setTimeout(() => {
					navigate("/library/books/view");
				}, 2000);
			})
			.catch((err) => {
				console.error(err);
				if (err.response && err.response.data && err.response.data.errors) {
					const messages = err.response.data.errors
						.map((e) => `${e.field}: ${e.defaultMessage}`)
						.join("\n");
					setMessage(`❌ ${messages}`);
				} else {
					setMessage("❌ Failed to add book. Please try again.");
				}
				setMessageType("danger");
			});
	};

	return (
		<Container className="d-flex justify-content-center align-items-center">
			<Card className="shadow-lg p-4" style={{ maxWidth: "500px", width: "100%" }}>
				<h2 className="mb-3"> Add a New Book</h2>

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
							onChange={(e) => setTitle(validateInput(e.target.value,"title"))}
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
								Add Book
							</Button>
						</Col>
					</Row>
				</Form>
			</Card>
		</Container>
	);
};

export default AddBook;
