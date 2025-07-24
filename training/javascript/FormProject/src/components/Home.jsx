import React from "react";
import { useNavigate } from "react-router-dom";
import Record from "./Record";
import "./Home.css";


const Home = ({ records, setRecords, config }) => {
  const navigate = useNavigate();
  const deleteRecord = (id) => {
    setRecords(records.filter((rec) => rec.id !== id));
  };
  const filtered=records
  // const filtered = config.uniquePhone
  //   ? Array.from(new Map(records.map(r => [r.phone, r])).values())
  //   : records;

  return (
    <div>
      <h1>Records</h1>
      <table border="1">
        <thead>
          <tr>
            <th>Name</th><th>Phone</th><th>Email</th><th>Department</th><th>Join Date</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filtered.slice(0, config.maxRecords).map((record) => (
            <Record
              key={record.id}
              record={record}
              onEdit={(id) => navigate(`/edit/${id}`)}
              onDelete={deleteRecord}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Home;