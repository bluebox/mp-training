import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./HomePage.css";

export default function UpdateMemberPage() {
  const navigate = useNavigate();
  const { id } = useParams(); 
  const [member, setMember] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "MALE", 
    address: "",
  });

  const [loading, setLoading] = useState(true);
  const [allMembers, setAllMembers] = useState([]);

  useEffect(() => {
    const fetchMember = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/members/memberbyid/${id}`);
        const data = response.data;

        setMember({
          name: data.name || "",
          email: data.email || "",
          mobile: data.mobile || "",
          gender: data.gender === "FEMALE" ? "FEMALE" : "MALE", 
          address: data.address || "",
        });

        const allResp = await axios.get("http://localhost:8080/members/all");
        setAllMembers(allResp.data);

        setLoading(false);
      } catch (error) {
        console.error(error);
        alert("Failed to load member data.");
        setLoading(false);
      }
    };

    fetchMember();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMember((prev) => ({ ...prev, [name]: value }));//copyes the value of prevstate and ut into in name value
  };

  const handleUpdate = async (e) => {
    e.preventDefault();

    if (!member.name || !member.email || !member.mobile || !member.address) {
      alert("Please fill all fields.");
      return;
    }

    const emailExists = allMembers.some(
      (m) => m.email === member.email && m.Memberid !== parseInt(id)
    );
    if (emailExists) {
      alert("This email is already used by another member.");
      return;
    }

    try {
      const payload = { ...member };

      await axios.post(`http://localhost:8080/members/updatemember/${id}`, payload);
      alert("Member updated successfully!");
      navigate("/members");
    } catch (error) {
      console.error(error.response || error);
      alert("Failed to update member");
    }
  };

  if (loading) {
    return <p>Loading member details...</p>;
  }

  return (
    <div className="content">
      <h2>Update Member</h2>
      <form onSubmit={handleUpdate}>
        <div>
          <label>Name: </label>
          <input type="text" name="name" value={member.name} onChange={handleChange} required />
        </div>
        <div>
          <label>Email: </label>
          <input type="email" name="email" value={member.email} onChange={handleChange} required />
        </div>
        <div>
          <label>Mobile: </label>
          <input type="text" name="mobile" value={member.mobile} onChange={handleChange} required />
        </div>
        <div>
          <label>Gender: </label>
          <select name="gender" value={member.gender} onChange={handleChange} required>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
          </select>
        </div>
        <div>
          <label>Address: </label>
          <input type="text" name="address" value={member.address} onChange={handleChange} required />
        </div>
        <div style={{ marginTop: "20px" }}>
          <button type="submit">Update Member</button>
          <button type="button" onClick={() => navigate("/members")} style={{ marginLeft: "10px" }}>
            Back
          </button>
        </div>
      </form>
    </div>
  );
}
