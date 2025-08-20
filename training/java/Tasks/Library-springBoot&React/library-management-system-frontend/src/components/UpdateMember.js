import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const UpdateMember = () => {
	const navigate = useNavigate();
	const [members, setMembers] = useState([]);
	const [searchTerm, setSearchTerm] = useState("");
	const [suggestions, setSuggestions] = useState([]);
	const [memberId, setMemberId] = useState(null);
	const [memberName, setMemberName] = useState("");
	const [memberMail, setMemberMail] = useState("");
	const [mobileNo, setMobileNo] = useState("");
	const [gender, setGender] = useState("");
	const [memberAddress, setMemberAddress] = useState("");
	const [message, setMessage] = useState(null);
	const [messageType, setMessageType] = useState("");

	useEffect(() => {
		axios
			.get("http://localhost:8080/library/members/allMembers")
			.then((res) => setMembers(res.data))
			.catch((err) => console.error(err));
	}, []);

	const handleSearch = (e) => {
		const term = e.target.value.toLowerCase();
		setSearchTerm(term);
		if (term.trim() === "") {
			setSuggestions([]);
			return;
		}
		const filtered = members.filter(
			(m) =>
				m.memberName.toLowerCase().includes(term) ||
				m.memberMail.toLowerCase().includes(term)
		);
		setSuggestions(filtered);
	};

	const selectMember = (member) => {
		setMemberId(member.memberId);
		setMemberName(member.memberName);
		setMemberMail(member.memberMail);
		setMobileNo(member.mobileNo);
		setGender(member.gender);
		setMemberAddress(member.memberAddress);
		setSearchTerm(`${member.memberName} - ${member.memberMail}`);
		setSuggestions([]);
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		if (!memberId) {
			setMessage("❌ Please select a valid member to update!");
			setMessageType("error");
			return;
		}

		axios
			.put(`http://localhost:8080/library/members/update/${memberId}`, {
				memberName: memberName.trim(),
				memberMail: memberMail.trim(),
				mobileNo: mobileNo.trim(),
				gender,
				memberAddress: memberAddress.trim(),
			})
			.then((res) => {
				setMessage(`✅ Member "${res.data.memberName}" updated successfully!`);
				setMessageType("success");
			})
			.catch((err) => {
				console.error(err);
				const backendMessage =
					err.response?.data?.message || "Failed to update member. Please try again.";
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
						whiteSpace: "pre-line",
					}}
				>
					{message}
				</div>
			)}

			<div style={styles.card}>
				<h2 style={styles.heading}> Update Member</h2>

				<form onSubmit={handleSubmit} style={styles.form}>
					<label style={styles.label}>Search Member:</label>
					<input
						type="text"
						value={searchTerm}
						onChange={handleSearch}
						placeholder="Search by name or email"
						style={styles.input}
					/>
					{suggestions.length > 0 && (
						<div style={styles.suggestionBox}>
							{suggestions.map((m) => (
								<div
									key={m.memberId}
									onClick={() => selectMember(m)}
									style={styles.suggestionItem}
								>
									{m.memberName} - {m.memberMail}
								</div>
							))}
						</div>
					)}

					<label style={styles.label}>Name:</label>
					<input
						type="text"
						value={memberName}
						onChange={(e) => setMemberName(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Email:</label>
					<input
						type="email"
						value={memberMail}
						onChange={(e) => setMemberMail(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Mobile No:</label>
					<input
						type="text"
						value={mobileNo}
						onChange={(e) => setMobileNo(e.target.value)}
						required
						style={styles.input}
					/>

					<label style={styles.label}>Gender:</label>
					<div style={{ display: "flex", gap: "20px", marginBottom: "15px" }}>
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

					<label style={styles.label}>Address:</label>
					<input
						type="text"
						value={memberAddress}
						onChange={(e) => setMemberAddress(e.target.value)}
						required
						style={styles.input}
					/>

					<button type="submit" style={styles.button}>
						 Update Member
					</button>
				</form>

				<div style={styles.buttonGroup}>
					<button
						onClick={() => navigate("/library/members")}
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
		maxWidth: "450px",
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

export default UpdateMember;
