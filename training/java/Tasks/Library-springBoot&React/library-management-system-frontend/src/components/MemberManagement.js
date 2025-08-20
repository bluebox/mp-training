import React from "react";
import { useNavigate } from "react-router-dom";

const MemberManagement = () => {
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
				position: "relative",
			}}
		>
			<button
				onClick={() => navigate("/library")}
				style={{
					position: "absolute",
					top: "15px",
					left: "15px",
					padding: "6px 12px",
					backgroundColor: "rgba(255, 255, 255, 0.8)",
					color: "#0d47a1",
					fontSize: "14px",
					fontWeight: "bold",
					border: "none",
					borderRadius: "6px",
					cursor: "pointer",
					transition: "0.3s",
				}}
				onMouseOver={(e) => (e.target.style.backgroundColor = "#bbdefb")}
				onMouseOut={(e) =>
					(e.target.style.backgroundColor = "rgba(255, 255, 255, 0.8)")
				}
			>
				 Back
			</button>

			<div
				style={{
					backgroundColor: "rgba(255, 255, 255, 0.95)",
					padding: "25px 40px",
					borderRadius: "12px",
					boxShadow: "0 6px 12px rgba(0,0,0,0.3)",
					textAlign: "center",
					width: "360px",
				}}
			>
				<h1
					style={{
						fontSize: "22px",
						color: "#0d47a1",
						marginBottom: "20px",
						borderBottom: "2px solid #90caf9",
						display: "inline-block",
						paddingBottom: "8px",
					}}
				>
					 Member Management
				</h1>

				<div style={{ display: "flex", flexDirection: "column", gap: "12px" }}>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/members/add")}
					>
						 Add Member
					</button>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/members/update")}
					>
						  Update Member
					</button>
					<button
						style={buttonStyle}
						onClick={() => navigate("/library/members/view")}
					>
						 View All Members
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

export default MemberManagement;
