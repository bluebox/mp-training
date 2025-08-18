import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function BookComponent() {
  const navigate = useNavigate();
  const [booksdata, setbooksdata] = useState([]);
  const [error, setError] = useState(null);

  const fetchbooksdata = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8095/Book/viewbook",
        { withCredentials: true }
      );
      setbooksdata(response.data);
    } catch (err) {
      console.error("Error fetching books", err);
      setError("Failed to fetch books.");
    }
  };
  const handleaddbook=()=>{
    navigate("/addbook");
  }
  const handleupdatebook=()=>{
    navigate("/updatebook");
  }



  useEffect(() => {
    fetchbooksdata();
  }, []);
   const handleback=()=>{
    navigate("/");
  }

  return (
    <div>
      <h2>Books Table</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <table >
        <thead>
          <tr>
            <th>Bookid</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Status</th>
            <th>Availability</th>
          </tr>
        </thead>
        <tbody>
          {booksdata.map((item) => (
            <tr key={item.bookid}>
              <td>{item.bookid}</td>
              <td>{item.title}</td>
              <td>{item.author}</td>
              <td>{item.category}</td>
              <td>{item.status}</td>
              <td>{item.availability}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div><button onClick={handleaddbook}>Add Book</button></div>
      <div><button onClick={handleback}>Back</button></div>
      <div><button onClick={handleupdatebook}>Update Book</button></div>
    </div>
  );
}

export default BookComponent;
