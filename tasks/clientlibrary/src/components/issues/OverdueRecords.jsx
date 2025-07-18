import React, { useEffect, useState } from "react";

const OverdueRecords = () => {
  const [records, setRecords] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRecords = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/issues/overdue",
          {
            method: "GET",
            credentials: "include",
          }
        );
        if (!response.ok) throw new Error("Failed to fetch overdue records");
        const responseData = await response.json();
        setRecords(responseData.data);
      } catch {
        setError("Failed to fetch overdue records.");
      } finally {
        setLoading(false);
      }
    };
    fetchRecords();
  }, []);

  return (
    <div>
      <h3>Overdue Records</h3>
      {loading ? (
        <div>Loading...</div>
      ) : error ? (
        <div className="text-red-600">{error}</div>
      ) : (
        <table className>
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
                <tr key={record.issueId}>
                  <td className="px-4 py-3">{record.issueId}</td>
                  <td className="px-4 py-3">{record.bookId}</td>
                  <td className="px-4 py-3">{record.memberId}</td>
                  <td className="px-4 py-3">{record.issueDate}</td>
                  <td className="px-4 py-3">
                    {record.returnDate ? record.returnDate : "-"}
                  </td>
                  <td className="px-4 py-3">{record.status}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" className="text-center py-2">
                  No overdue records found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default OverdueRecords;
