import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function ViewMembers() {
    const [members, setMembers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchMembers();
    }, []);

    const fetchMembers = async () => {
        try {
            const response = await axios.get("http://localhost:8080/members");
            setMembers(response.data);
        } catch (error) {
            alert("Failed to fetch Members.");
        }
    };

    const updateMember = (member) => {
        navigate("/members/update", { state: { member } });
    };

    const getGenderLabel = (status) => {
        if (status === "M") return "Male";
        if (status === "F") return "Female";
        return status;
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
            <div className="bg-white p-4 rounded shadow" style={{ width: "70%" }}>
                <Link to="/members" className="btn btn-danger mb-3">
                    Back to Dashboard
                </Link>

                <h3 className="fw-bold text-center mb-4">All Members</h3>

                <table className="table table-bordered table-striped">
                    <thead className="table-light">
                        <tr>
                            <th>Member ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Gender</th>
                            <th>Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {members.length > 0 ? (
                            members.map((member) => (
                                <tr key={member.memberId}>
                                    <td>{member.memberId}</td>
                                    <td>{member.name}</td>
                                    <td>{member.email}</td>
                                    <td>{member.mobile}</td>
                                    <td>{getGenderLabel(member.gender)}</td>
                                    <td>{member.address}</td>
                                    <td>
                                        <button
                                            className="btn btn-primary btn-sm me-2"
                                            onClick={() => updateMember(member)}
                                        >
                                            Update Member
                                        </button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="7" className="text-center">
                                    No Members available.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
