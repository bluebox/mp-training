import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Col, Row, Button, Card, Container, Alert } from "react-bootstrap";

const IssueRecord = () => {
	const navigate = useNavigate();
	const [members, setMembers] = useState([]);
	const [books, setBooks] = useState([]);
	const [selectedMemberId, setSelectedMemberId] = useState("");
	const [selectedBookId, setSelectedBookId] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");

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
		axios
			.post("http://localhost:8080/library/issues/issue", {
				memberId: selectedMemberId,
				bookId: selectedBookId,
			})
			.then((res) => {
				setMessage("✅ Book issued successfully!");
				setMessageType("success");
				setSelectedMemberId("");
				setSelectedBookId("");
				setTimeout(() => {
					navigate("/library/issues/allIssues");
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
					setMessage("❌ Failed to issue book. Please try again.");
				}
				setMessageType("danger");
			});
	};

	return (
		<Container className="d-flex justify-content-center align-items-center">
			<Card className="shadow-lg p-4" style={{ maxWidth: "500px", width: "100%" }}>
				<h2 className="mb-3"> Issue Book</h2>

				{message && (
					<Alert variant={messageType} className="fw-bold text-center">
						{message}
					</Alert>
				)}
				<Form onSubmit={handleSubmit}>
					<Form.Group className="mb-3" as={Col} controlId="formSelectMember">
						<Form.Label>Select Member</Form.Label>
						<Form.Control
							as="select"
							value={selectedMemberId}
							onChange={(e) => setSelectedMemberId(e.target.value)}
							required
						>
							<option value="">Select Member</option>
							{members.map((m) => (
								<option key={m.memberId} value={m.memberId}>
									{m.memberName}
								</option>
							))}
						</Form.Control>
					</Form.Group>

					<Form.Group className="mb-3" as={Col} controlId="formSelectBook">
						<Form.Label>Select Book</Form.Label>
						<Form.Control
							as="select"
							value={selectedBookId}
							onChange={(e) => setSelectedBookId(e.target.value)}
							required
						>
							<option value="">Select Book</option>
							{books.map((b) => (
								<option key={b.bookId} value={b.bookId}>
									{b.title} - {b.author}
								</option>
							))}
						</Form.Control>
					</Form.Group>
					<Row>
						<Col className="d-grid">
							<Button variant="outline-primary" type="submit">
								Issue Book
							</Button>
						</Col>
					</Row>
				</Form>
			</Card>
		</Container>

	);
};

export default IssueRecord;
