import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function IssueMembercomponent() {
  const navigate=new useNavigate();
  const [membersdata, setmembersdata] = useState([]);
  const [error, setError] = useState(null);
  const fetchmembersdata = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8095/Reports/isuuemembers",
        { withCredentials: true }
      );
      setmembersdata(response.data);
      console.log(response.data);
    } catch (err) {
      console.error("Error fetching members", err);
      setError("Failed to fetch members.");
    }
  };

  useEffect(() => {
    fetchmembersdata();
  }, []);

   const handleback=()=>{
    navigate("/Reports");
  }

  return (
    <div>
      <h2>Members Table</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <table >
        <thead>
          <tr>
            <th>Memberid</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {membersdata.map((item) => (
            <tr key={item.memberid}>
              <td>{item.memberid}</td>
              <td>{item.name}</td>
              <td>{item.email}</td>
              <td>{item.mobile}</td>
              <td>{item.gender}</td>
              <td>{item.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={handleback}>Back</button>
    </div>
  );
}

export default IssueMembercomponent;
