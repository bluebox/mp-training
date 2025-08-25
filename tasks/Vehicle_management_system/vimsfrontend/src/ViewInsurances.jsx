import React, { useEffect, useState } from 'react';
import './App.css';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { useCookies } from 'react-cookie';

function ViewInsurances() {
  const { type } = useParams();
  let [Data, setData] = useState([]);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();
  const [cookies] = useCookies(['userData']); 

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/insurance/getInsurances/${type}`);
        setData(response.data);
      } catch (err) {
        setMessage(err);
      }
    };
    fetchData();
  }, [type]);

  function handleApply() {
    if (cookies.userData && cookies.userData.token) {
      alert("You can proceed to apply!"); 
    } else {
      alert("You need to login first!");
      navigate("/login");
    }
  }

  return (
    <div className='view'>
      <h1>Vehicle Insurances for {type} wheeler</h1>
      {Data.length > 0 ? (
        <table className="viewInsurances">
          <thead>
            <tr>
              <th>InsuranceId</th>
              <th>Insurance</th>
              <th>ValidityPeriod</th>
              <th>InsuranceAmount</th>
              <th>EMI</th>
              <th>NCBPercentage</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {Data.map((i) => (
              <tr key={i.insuranceId}>
                <td>{i.insuranceId}</td>
                <td>{i.periodLength} months</td>
                <td>{i.validPeriod} years</td>
                <td>{i.insuranceAmount}</td>
                <td>{i.monthlyEMI}</td>
                <td>{i.ncb}%</td>
                <td>
                  <button onClick={handleApply}>Apply</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading data or no data available...</p>
      )}
    </div>
  );
}

export default ViewInsurances;
