import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ActiveIssuedBooksTable = () => {
  const navigate = useNavigate();
  const [records, setRecords] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/library/reports/activeIssuedRecords")
      .then((res) => setRecords(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.heading}> Active Issued Books</h2>

        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>Member ID</th>
              <th style={styles.th}>Member Name</th>
              <th style={styles.th}>Book ID</th>
              <th style={styles.th}>Book Title</th>
              <th style={styles.th}>Issue Date</th>
            </tr>
          </thead>
          <tbody>
            {records.map((r, index) => (
              <tr
                key={index}
                style={{
                  backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
                }}
              >
                <td style={styles.td}>{r.memberId}</td>
                <td style={styles.td}>{r.memberName}</td>
                <td style={styles.td}>{r.bookId}</td>
                <td style={styles.td}>{r.bookTitle}</td>
                <td style={styles.td}>{r.issueDate}</td>
              </tr>
            ))}
          </tbody>
        </table>

        
      </div>
    </div>
  );
};

const styles = {
	page: {
	    display: "flex",
	    justifyContent: "center",
	    alignItems: "flex-start",
	    backgroundSize: "cover",
	    backgroundPosition: "center",
	    padding: "20px",
	    position: "relative",
	},

  card: {
    backgroundColor: "rgba(255, 255, 255, 0.95)",
    padding: "30px",
    borderRadius: "16px",
    boxShadow: "0 6px 18px rgba(0,0,0,0.25)",
    maxWidth: "1000px",
    width: "100%",
    textAlign: "center",
    overflowX: "auto",
  },
  heading: {
    marginBottom: "20px",
    fontSize: "22px",
    fontWeight: "bold",
    color: "#2c3e50",
  },
  table: {
  		width: "100%",
  		borderCollapse: "collapse",
  		marginBottom: "20px",
  	},
  	th: {
  		border: "1px solid #ccc",
  		padding: "10px",
  		backgroundColor: "#f2f2f2",
  		fontWeight: "bold",
  	},
  	td: {
  		border: "1px solid #ccc",
  		padding: "10px",
  	},
  buttonGroup: {
    display: "flex",
    justifyContent: "space-between",
  },
  secondaryButton: {
    padding: "10px 15px",
    border: "none",
    borderRadius: "8px",
    backgroundColor: "#2980b9",
    color: "white",
    cursor: "pointer",
    fontSize: "14px",
    transition: "0.3s",
  },
};

export default ActiveIssuedBooksTable;
