import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Col, Row, Button, Card, Container, Alert } from "react-bootstrap";

const AddMember = ({ onMemberAdded }) => {
	const [memberName, setMemberName] = useState("");
	const [memberMail, setMemberMail] = useState("");
	const [mobileNo, setMobileNo] = useState("");
	const [gender, setGender] = useState("");
	const [memberAddress, setMemberAddress] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");
	const navigate = useNavigate();

	const handleSubmit = (e) => {
		e.preventDefault();
		axios
			.post("http://localhost:8080/library/members/add", {
				memberName,
				memberMail,
				mobileNo,
				gender,
				memberAddress,
			})
			.then((res) => {
				setMessage(`✅ Member "${res.data.memberName}" added successfully!`);
				setMessageType("success");
				if (onMemberAdded) onMemberAdded();
				setMemberName("");
				setMemberMail("");
				setMobileNo("");
				setGender("");
				setMemberAddress("");
				setTimeout(() => {
					navigate("/library/members/view");
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
					setMessage("❌ Failed to add member. Please try again.");
				}
				setMessageType("danger");
			});
	};

	return (
		<Container className="d-flex justify-content-center align-items-center">
			<Card className="shadow-lg p-4" style={{ maxWidth: "500px", width: "100%" }}>
				<h2 className="mb-3"> Add a New Member</h2>

				{message && (
					<Alert variant={messageType} className="fw-bold text-center">
						{message}
					</Alert>
				)}

				<Form onSubmit={handleSubmit}>
					<Form.Group className="mb-3" controlId="formMemberName">
						<Form.Label>Name</Form.Label>
						<Form.Control
							type="text"
							placeholder="Enter member name"
							value={memberName}
							onChange={(e) => setMemberName(e.target.value)}
							required
						/>
					</Form.Group>

					<Form.Group className="mb-3" controlId="formMemberEmail">
						<Form.Label>Email</Form.Label>
						<Form.Control
							type="email"
							placeholder="Enter email"
							value={memberMail}
							onChange={(e) => setMemberMail(e.target.value)}
							required
						/>
					</Form.Group>

					<Form.Group className="mb-3" controlId="formMemberMobile">
						<Form.Label>Mobile No</Form.Label>
						<Form.Control
							type="text"
							placeholder="Enter mobile number"
							value={mobileNo}
							onChange={(e) => setMobileNo(e.target.value)}
							required
						/>
					</Form.Group>

					<Form.Group className="mb-3">
						<Form.Label>Gender</Form.Label>
						<Col sm={10}>
							<Form.Check
								type="radio"
								label="Male"
								name="gender"
								value="M"
								checked={gender === "M"}
								onChange={(e) => setGender(e.target.value)}
								required
							/>
							<Form.Check
								type="radio"
								label="Female"
								name="gender"
								value="F"
								checked={gender === "F"}
								onChange={(e) => setGender(e.target.value)}
							/>
						</Col>
					</Form.Group>

					<Form.Group className="mb-3" controlId="formMemberAddress">
						<Form.Label>Address</Form.Label>
						<Form.Control
							type="text"
							placeholder="Enter member address"
							value={memberAddress}
							onChange={(e) => setMemberAddress(e.target.value)}
							required
						/>
					</Form.Group>

					<Row>
						<Col className="d-grid">
							<Button variant="outline-primary" type="submit">
								Add Member
							</Button>
						</Col>
					</Row>
				</Form>
			</Card>
		</Container>
	);
};

export default AddMember;
