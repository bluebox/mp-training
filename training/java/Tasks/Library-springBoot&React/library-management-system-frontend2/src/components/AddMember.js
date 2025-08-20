import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
				navigate("/library/members/view");
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
				<h2 style={styles.heading}> Add a New Member</h2>

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
					<label style={styles.label}>Name:</label>
					<input
						placeholder="Enter member name"
						value={memberName}
						onChange={(e) => setMemberName(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Email:</label>
					<input
						type="email"
						placeholder="Enter email"
						value={memberMail}
						onChange={(e) => setMemberMail(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Mobile No:</label>
					<input
						placeholder="Enter mobile number"
						value={mobileNo}
						onChange={(e) => setMobileNo(e.target.value)}
						required
						style={styles.input}
					/>

					<div style={{ textAlign: "left", marginBottom: "15px" }}>
						<label style={styles.label}>Gender:</label>
						<div style={{ display: "flex", gap: "20px", marginTop: "5px" }}>
							<label>
								<input
									type="radio"
									name="gender"
									value="M"
									checked={gender === "M"}
									onChange={(e) => setGender(e.target.value)}
									required
								/>{" "}
								Male
							</label>
							<label>
								<input
									type="radio"
									name="gender"
									value="F"
									checked={gender === "F"}
									onChange={(e) => setGender(e.target.value)}
								/>{" "}
								Female
							</label>
						</div>
					</div>

					<label style={styles.label}>Address:</label>
					<input
						placeholder="Enter member address"
						value={memberAddress}
						onChange={(e) => setMemberAddress(e.target.value)}
						required
						style={styles.input}
					/>

					<button type="submit" style={styles.button}>
						Add Member
					</button>
				</form>

				
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
		maxWidth: "450px",
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

export default AddMember;
