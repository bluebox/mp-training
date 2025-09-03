import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import api from "./api/axios";

export default function StoreStock() {
  const [purchases, setPurchases] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;
  const navigate = useNavigate();

  useEffect(() => {
    api
      .get("/purchase")
      .then((res) => setPurchases(res.data))
      .catch((err) => {
        console.error("Error fetching purchases:", err);
        setPurchases([]);
      });
  }, []);

  const totalPages = Math.ceil(purchases.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentPurchases = purchases.slice(startIndex, startIndex + itemsPerPage);

  return (
    <div style={containerStyle}>
      <button onClick={() => navigate(-1)} style={backButtonStyle}>
        ← Back
      </button>

      <h2 style={titleStyle}>Store Stock — Purchases</h2>

      <div style={tableWrapperStyle}>
        <table style={tableStyle}>
          <thead>
            <tr style={tableHeaderRowStyle}>
              <th style={thStyle}>Purchase ID</th>
              <th style={thStyle}>Supplier ID</th>
              <th style={thStyle}>Created By</th>
              <th style={thStyle}>Total Taxable Amount</th>
              <th style={thStyle}>Total GST</th>
              <th style={thStyle}>Total Amount</th>
              <th style={thStyle}>Purchase Date</th>
              <th style={thStyle}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {currentPurchases.length === 0 ? (
              <tr>
                <td colSpan={8} style={noDataStyle}>
                  No purchases found.
                </td>
              </tr>
            ) : (
              currentPurchases.map((p) => (
                <tr key={p.purchaseId || p.id} style={tableRowStyle}>
                  <td style={tdStyle}>{p.purchaseId || p.id}</td>
                  <td style={tdStyle}>{p.supplierId}</td>
                  <td style={tdStyle}>{p.createdBy}</td>
                  <td style={tdStyle}>
                    ₹ {p.totalTaxableAmount?.toFixed(2) ?? "-"}
                  </td>
                  <td style={tdStyle}>₹ {p.totalGst?.toFixed(2) ?? "-"}</td>
                  <td style={tdStyle}>₹ {p.totalAmount?.toFixed(2) ?? "-"}</td>
                  <td style={tdStyle}>{p.purchaseDate || p.createdAt || "-"}</td>
                  <td style={tdStyle}>
                    <button
                      style={buttonStyle}
                      onClick={() =>
                        navigate(`/purchases/${p.purchaseId || p.id}`)
                      }
                    >
                      View Details
                    </button>
                  </td>
                </tr>
              ))
            )}
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
  minWidth: "1100px",
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

const noDataStyle = {
  padding: "20px",
  textAlign: "center",
  fontStyle: "italic",
  fontWeight: "500",
  color: "#999",
};

const buttonStyle = {
  padding: "6px 12px",
  backgroundColor: "#5a2a83",
  color: "#fff",
  border: "none",
  borderRadius: 4,
  cursor: "pointer",
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
