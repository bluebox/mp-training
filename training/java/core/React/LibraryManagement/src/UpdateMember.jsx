import React, { useState } from "react";
import { useLocation, useNavigate, Link } from "react-router-dom";
import axios from "axios";

export default function UpdateMember() {
    const location = useLocation();
    const navigate = useNavigate();
    const { member } = location.state || {};

    const [name, setName] = useState(member?.name || "");
    const [email, setEmail] = useState(member?.email || "");
    const [mobile, setMobile] = useState(member?.mobile || "");
    const [gender, setGender] = useState(member?.gender || "");
    const [address, setAddress] = useState(member?.address || "");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const updatedMember = {
            memberId: member.memberId,
            name,
            email,
            mobile,
            gender,
            address,
        };

        const isUnchanged =
            name === member.name &&
            email === member.email &&
            mobile === member.mobile &&
            gender === member.gender &&
            address === member.address;

        if (isUnchanged) {
            alert("No changes made!");
            return;
        }

        if (!/^\d{10}$/.test(mobile)) {
            alert("Mobile number must be exactly 10 digits long.");
            return;
        }

        try {
            await axios.put(
                `http://localhost:8080/members/${member.memberId}`,
                updatedMember
            );
            alert("Member updated successfully!");
            navigate("/members/view");
        } catch (error) {
            alert("Failed to update member.");
        }
    };

    return (
        <div
            className="d-flex align-items-center justify-content-center"
            style={{
                minHeight: "100vh",
                backgroundImage: "url('/library.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
                backgroundAttachment: "fixed",
            }}
        >
            <div className="bg-white p-4 rounded shadow" style={{ width: "450px" }}>
                <h3 className="fw-bold text-center mb-4">Update Member</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">Member ID:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={member.memberId}
                            disabled
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Email:</label>
                        <input
                            type="email"
                            className="form-control"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Mobile:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={mobile}
                            onChange={(e) => setMobile(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Gender:</label>
                        <select
                            className="form-select"
                            value={gender}
                            onChange={(e) => setGender(e.target.value)}
                        >
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                        </select>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Address:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={address}
                            onChange={(e) => setAddress(e.target.value)}
                            required
                        />
                    </div>

                    <button type="submit" className="btn btn-primary w-100 mb-2">
                        Update Member
                    </button>
                    <Link to="/members/view" className="btn btn-danger w-100">
                        Back to Member List
                    </Link>
                </form>
            </div>
        </div>
    );
}
