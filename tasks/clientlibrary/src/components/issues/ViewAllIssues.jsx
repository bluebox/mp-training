import React, { useState, useEffect } from "react";
import Issue from "./Issue";

const ViewAllIssues = () => {
  const [records, setRecords] = useState([]);

  useEffect(() => {
    const fetchRecords = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/issues",
          {
            method: "GET",
            credentials: "include",
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch issue records");
        }
        const responseData = await response.json();
        setRecords(responseData.data);
      } catch (error) {
        console.error("Error fetching issue records:", error);
      }
    };
    fetchRecords();
  }, []);

  return (
    <div>
      <h3>All Issue Records</h3>
      <div>
        <table>
          <thead>
            <tr>
              <th className="px-4 py-3">Issue ID</th>
              <th className="px-4 py-3">Book ID</th>
              <th className="px-4 py-3">Member ID</th>
              <th className="px-4 py-3">Issue Date</th>
              <th className="px-4 py-3">Return Date</th>
              <th className="px-4 py-3">Status</th>
            </tr>
          </thead>
          <tbody>
            {records && records.length > 0 ? (
              records.map((record) => (
                <Issue key={record.issueId} record={record} />
              ))
            ) : (
              <tr>
                <td colSpan="6">No issued records found.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewAllIssues;
