import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/members";

export default function ViewMembers({ goBack, onUpdate }) {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const handleResponse = async (res) => {
    if (!res.ok) {
      let errData;
      try { 
        errData = await res.json(); 
      } catch {
        throw new Error(await res.text());
      }
      throw new Error(errData.message || "Request failed");
    }

    const contentType = res.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
      return res.json();
    } else {
      return res.text(); 
    }
  };

  const load = async () => {
    setLoading(true);
    try {
      const res = await fetch(`${BASE_URL}/all`);
      const data = await handleResponse(res);
      setMembers(data);
    } catch (e) {
      alert(e.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load() }, []);

  const remove = async (id) => {
    if (!window.confirm("Do you want to delete this member?")) return;
    try {
      const res = await fetch(`${BASE_URL}/deletemember/${id}`, { method: "PUT" });
      const msg = await handleResponse(res);
      alert(msg); 
      load(); 
    } catch (e) {
      alert(e.message);
    }
  };

  const formatDate = (d) => {
    if (!d) return "";
    return new Date(d).toLocaleString();
  };

  return (
    <div>
      <h3>All Members</h3>
      {loading ? <p>Loading members...</p> : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>Member ID</th><th>Name</th><th>Email</th><th>Mobile</th>
              <th>Gender</th><th>Address</th><th>Status</th>
              <th>Created By</th><th>Created At</th>
              <th>Updated By</th><th>Updated At</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {members.length === 0 ? (
              <tr><td colSpan="12" style={{ textAlign: "center" }}>No members found</td></tr>
            ) : members.map(m => (
              <tr key={m.memberId}>
                <td>{m.memberId}</td>
                <td>{m.name}</td>
                <td>{m.email}</td>
                <td>{m.mobile}</td>
                <td>{m.gender}</td>
                <td>{m.address}</td>
                <td>{m.status}</td>
                <td>{m.createdBy}</td>
                <td>{formatDate(m.createdAt)}</td>
                <td>{m.updatedBy}</td>
                <td>{formatDate(m.updatedAt)}</td>
                <td>
                  <button onClick={() => navigate(`/updatemember/${m.memberId}`)}>
                    Update
                  </button>
                  <button 
                    onClick={() => remove(m.memberId)} 
                    style={{ marginLeft: 8 }}
                    disabled={m.status !== "ACTIVE"}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <button onClick={goBack} style={{ marginTop: 10 }}>Back</button>
    </div>
  );
}
