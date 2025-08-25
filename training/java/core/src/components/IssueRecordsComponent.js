import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function IssueRecordsComponent() {
  const navigate=new useNavigate();
  const [issuerecordsdata, setissuerecordsdata] = useState([]);
  const [error, setError] = useState(null);

  const fetchissuerecordsdata = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8095/issuerecords/viewallissuerecords",
        { withCredentials: true }
      );
      setissuerecordsdata(response.data);
    } catch (err) {
      console.error("Error fetching issue records", err);
      setError("Failed to fetch issue records.");
    }
  };
   const handleissuebook=()=>{
    navigate("/issuebook");
  }
  const handlereturnbook=()=>{
    navigate("/returnbook");
  }

  useEffect(() => {
    fetchissuerecordsdata();
  }, []);
  const handleback=()=>{
    navigate("/");
  }

  return (
    <div>
      <h2>Issue Records Table</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <table >
        <thead>
          <tr>
            <th>IssueId</th>
            <th>BookId</th>
            <th>MemberId</th>
            <th>Status</th>
            <th>IssueDate</th>
            <th>ReturnDate</th>
          </tr>
        </thead>
        <tbody>
          {issuerecordsdata.map((item) => (
            <tr key={item.issueid}>
              <td>{item.issueid}</td>
              <td>{item.bookid}</td>
              <td>{item.memberid}</td>
              <td>{item.status}</td>
              <td>{item.issuedate}</td>
              <td>{item.returndate}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div><button onClick={handleissuebook}>Issue Book</button></div>
      <div><button onClick={handleback}>Back</button></div>
      <div><button onClick={handlereturnbook}>Return Book</button></div>
    </div>
  );
}

export default IssueRecordsComponent;
