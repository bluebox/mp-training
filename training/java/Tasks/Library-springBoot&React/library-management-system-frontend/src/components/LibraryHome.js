import React from "react";
import { useNavigate } from "react-router-dom";

const LibraryHome = () => {
	const navigate = useNavigate();

	return (
		<div
			style={{
				backgroundSize: "cover",
				backgroundPosition: "center",
				minHeight: "100vh",
				display: "flex",
				justifyContent: "center",
				alignItems: "center",
			}}
		>
			<div
				style={{
					backgroundColor: "rgba(255, 255, 255, 0.85)",
					padding: "40px",
					borderRadius: "15px",
					textAlign: "center",
					boxShadow: "0px 4px 15px rgba(0,0,0,0.3)",
				}}
			>
				<h1 style={{ marginBottom: "20px", color: "#2c3e50" }}>
					Library Management System
				</h1>
				<div
					style={{
						display: "flex",
						flexDirection: "column",
						gap: "15px",
						width: "250px",
						margin: "0 auto",
					}}
				>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/books")}
					>
						 Book Management
					</button>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/members")}
					>
						 Member Management
					</button>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/issues")}
					>
						 Issue / Return Books
					</button>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/reports")}
					>
						 Reports
					</button>
				</div>
			</div>
		</div>
	);
};

const buttonStyle = {
	padding: "12px 20px",
	fontSize: "16px",
	fontWeight: "bold",
	borderRadius: "8px",
	border: "none",
	cursor: "pointer",
	backgroundColor: "#3498db",
	color: "white",
	transition: "0.3s",
};

export default LibraryHome;
