import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { deleteRecord } from "../Saveit/recordsSlice";
import Record from "./Record";
import { useNavigate } from "react-router-dom";
import "./Home.css";

const Home = () => {
  const records = useSelector(state => state.records);
  const config = useSelector(state => state.config);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleDelete = (id) => {
    dispatch(deleteRecord(id));
  };

  const filtered = config.uniquePhone
    ? Array.from(new Map(records.map(r => [r.phone, r])).values())
    : records;

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
              onDelete={handleDelete}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default Home;





