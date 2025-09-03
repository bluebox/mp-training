import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import api from "./api/axios";

export default function PurchaseDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [details, setDetails] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;

  useEffect(() => {
    setLoading(true);
    api
      .get(`/purchase/${id}`)
      .then((res) => {
        setDetails(res.data || []);
        setLoading(false);
        setCurrentPage(1);
      })
      .catch((err) => {
        console.error("Error fetching purchase details:", err);
        setDetails([]);
        setLoading(false);
      });
  }, [id]);

  const totalPages = Math.ceil(details.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentDetails = details.slice(startIndex, startIndex + itemsPerPage);

  return (
    <div style={containerStyle}>
      <button onClick={() => navigate(-1)} style={backButtonStyle}>
        ← Back
      </button>

      <h2 style={titleStyle}>Purchase Item Details</h2>

      {loading ? (
        <p>Loading...</p>
      ) : details.length === 0 ? (
        <p>No details found for purchase ID: {id}</p>
      ) : (
        <>
          <div style={tableWrapperStyle}>
            <table style={tableStyle}>
              <thead>
                <tr style={tableHeaderRowStyle}>
                  <th style={thStyle}>Batch ID</th>
                  <th style={thStyle}>Purchase ID</th>
                  <th style={thStyle}>Product ID</th>
                  <th style={thStyle}>Quantity</th>
                  <th style={thStyle}>Taxable Amount</th>
                  <th style={thStyle}>GST Amount</th>
                  <th style={thStyle}>Total Amount</th>
                  <th style={thStyle}>Expiry Date</th>
                </tr>
              </thead>
              <tbody>
                {currentDetails.map((item, index) => (
                  <tr key={index} style={tableRowStyle}>
                    <td style={tdStyle}>{item.batchId}</td>
                    <td style={tdStyle}>{item.purchaseId}</td>
                    <td style={tdStyle}>{item.productId}</td>
                    <td style={tdStyle}>{item.quantity}</td>
                    <td style={tdStyle}>₹ {item.taxableAmount.toFixed(2)}</td>
                    <td style={tdStyle}>₹ {item.gstAmount.toFixed(2)}</td>
                    <td style={tdStyle}>₹ {item.totalAmount.toFixed(2)}</td>
                    <td style={tdStyle}>{item.expiry}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          <div style={paginationContainerStyle}>
            <button
              onClick={() => setCurrentPage((p) => Math.max(p - 1, 1))}
              disabled={currentPage === 1}
              style={{
                ...paginationButtonStyle,
                backgroundColor: currentPage === 1 ? "#ccc" : "#00bcd4",
              }}
            >
              ⬅ Previous
            </button>
            <span style={pageInfoStyle}>
              Page {currentPage} of {totalPages}
            </span>
            <button
              onClick={() => setCurrentPage((p) => Math.min(p + 1, totalPages))}
              disabled={currentPage === totalPages}
              style={{
                ...paginationButtonStyle,
                backgroundColor: currentPage === totalPages ? "#ccc" : "#4caf50",
              }}
            >
              Next ➡
            </button>
          </div>
        </>
      )}
    </div>
  );
}



const containerStyle = {
  maxWidth: "100%",
  margin: "30px auto",
  padding: "30px",
  background: "#fefefe",
  boxShadow: "0 0 12px rgba(0,0,0,0.1)",
  borderRadius: "16px",
  fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
  position: "relative",
};

const backButtonStyle = {
  backgroundColor: "#ff9800",
  color: "#fff",
  border: "none",
  padding: "10px 16px",
  borderRadius: "25px",
  fontWeight: "600",
  cursor: "pointer",
  boxShadow: "0 4px 10px rgba(255, 152, 0, 0.3)",
  position: "fixed",
  top: 10,
  left: 10,
  zIndex: 1000,
};

const titleStyle = {
  fontSize: "1.8rem",
  fontWeight: "700",
  color: "#673ab7",
  marginBottom: "20px",
  marginTop: 0,
  paddingLeft: 80,
};

const tableWrapperStyle = {
  overflowX: "auto",
  borderRadius: "8px",
};

const tableStyle = {
  width: "100%",
  minWidth: "900px",
  borderCollapse: "collapse",
  backgroundColor: "#fff",
  borderRadius: "10px",
};

const tableHeaderRowStyle = {
  backgroundColor: "#7c4dff",
  color: "#fff",
};

const thStyle = {
  padding: "12px 18px",
  textAlign: "left",
  fontWeight: "600",
  fontSize: "14px",
};

const tdStyle = {
  padding: "12px 18px",
  fontSize: "14px",
  borderBottom: "1px solid #e0e0e0",
  color: "#555",
};

const tableRowStyle = {
  backgroundColor: "#f9f9f9",
};

const paginationContainerStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  gap: "15px",
  marginTop: "25px",
  flexWrap: "wrap",
};

const paginationButtonStyle = {
  padding: "10px 20px",
  borderRadius: "20px",
  border: "none",
  color: "#fff",
  fontWeight: "600",
  cursor: "pointer",
};

const pageInfoStyle = {
  fontWeight: "600",
  fontSize: "16px",
  color: "#444",
};

const noDataStyle = {
  padding: "20px",
  textAlign: "center",
  fontStyle: "italic",
  fontWeight: "500",
  color: "#999",
};
