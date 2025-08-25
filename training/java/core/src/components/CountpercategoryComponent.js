
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function CountpercategoryComponent(){
  const navigate=new useNavigate();
    const [countdata, setcountdata] = useState([]);
  const [error, setError] = useState(null);
  const fetchcountdata = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8095/Reports/countpercategory",
        { withCredentials: true }
      );
      setcountdata(response.data);
      console.log(response.data);
    } catch (err) {
      console.error("Error fetching members", err);
      setError("Failed to fetch members.");
    }
  };

  useEffect(() => {
    fetchcountdata();
  }, []);
   const handleback=()=>{
    navigate("/Reports");
  }




    return  <div>
      <h2> Book Count Per Category  Table</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <table >
        <thead>
          <tr>
            <th>Category</th>
            <th>Book Count</th>
            
          </tr>
        </thead>
        <tbody>
          {Object.keys(countdata).map(k => (
    
        <tr key={k}>
            <td>{k}</td>
            <td>{countdata[k]}</td>
        </tr>
    ))}
        </tbody>
      </table>
      <div><button onClick={handleback}>Back</button></div>
      
    </div>

}
export default CountpercategoryComponent;