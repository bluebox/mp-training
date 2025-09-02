import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Form, Col, Row, Button, Card, Container, Alert } from "react-bootstrap";

const UpdateMember = () => {
	const navigate = useNavigate();
	const { id } = useParams();
	const [memberName, setMemberName] = useState("");
	const [memberMail, setMemberMail] = useState("");
	const [mobileNo, setMobileNo] = useState("");
	const [gender, setGender] = useState("");
	const [memberAddress, setMemberAddress] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");

	useEffect(() => {
		axios
			.get(`http://localhost:8080/library/members/${id}`)
			.then((res) => {
				const member = res.data;
				setMemberName(member.memberName);
				setMemberMail(member.memberMail);
				setMobileNo(member.mobileNo);
				setGender(member.gender);
				setMemberAddress(member.memberAddress);
			})
			.catch((err) => {
				console.error(err);
				setMessage("❌ Failed to load member details.");
				setMessageType("error");
			});
	}, [id]);

	const handleSubmit = (e) => {
		e.preventDefault();
		axios
			.put(`http://localhost:8080/library/members/update/${id}`, {
				memberName: memberName.trim(),
				memberMail: memberMail.trim(),
				mobileNo: mobileNo.trim(),
				gender,
				memberAddress: memberAddress.trim(),
			})
			.then((res) => {
				setMessage(`✅ Member "${res.data.memberName}" updated successfully!`);
				setMessageType("success");
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
					setMessage("❌ Failed to update member. Please try again.");
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
								Update Member
							</Button>
						</Col>
					</Row>
				</Form>
			</Card>
		</Container>

	);
};

export default UpdateMember;
