import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import api from "./api/axios";

export default function ProductsStock() {
  const [stockData, setStockData] = useState([]);
  const [search, setSearch] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;
  const navigate = useNavigate();

  useEffect(() => {
    api
      .get("/products/product-stock")
      .then((res) => {
        if (Array.isArray(res.data)) {
          setStockData(res.data);
        } else {
          setStockData([]);
        }
      })
      .catch((err) => {
        console.error("Error fetching product stock:", err);
        setStockData([]);
      });
  }, []);

  
  const filteredData = stockData.filter((item) =>
    (item.productId + item.name)
      .toLowerCase()
      .includes(search.trim().toLowerCase())
  );

  const totalPages = Math.ceil(filteredData.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentStock = filteredData.slice(startIndex, startIndex + itemsPerPage);

  return (
    <div style={containerStyle}>
      <div style={topBarStyle}>
        <button onClick={() => navigate(-1)} style={backButtonStyle}>
          ⬅ Back
        </button>
        <h2 style={titleStyle}>Product Stock</h2>
      </div>

      <div style={searchContainerStyle}>
        <input
          type="text"
          placeholder=" Search by ID or Name"
          value={search}
          onChange={(e) => {
            setSearch(e.target.value);
            setCurrentPage(1); 
          }}
          style={searchInputStyle}
        />
        <button
          onClick={() => setSearch("")}
          disabled={!search}
          style={{
            ...clearButtonStyle,
            backgroundColor: search ? "#f44336" : "#eee",
            color: search ? "#fff" : "#aaa",
            cursor: search ? "pointer" : "not-allowed",
          }}
        >
          ✕
        </button>
      </div>

      <div style={tableContainerStyle}>
        <table style={tableStyle}>
          <thead>
            <tr>
              <th style={thStyle}>ID</th>
              <th style={thStyle}>Name</th>
              <th style={thStyle}>Total Quantity</th>
            </tr>
          </thead>
          <tbody>
            {currentStock.length > 0 ? (
              currentStock.map((item, index) => (
                <tr key={item.productId || index}>
                  <td style={tdStyle}>{item.productId}</td>
                  <td style={tdStyle}>{item.name}</td>
                  <td style={tdStyle}>{item.totalQty}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="3" style={noDataStyle}>
                  No stock data found.
                </td>
              </tr>
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
  maxWidth: "600px",
  margin: "40px auto",
  padding: "20px",
  background: "#ffffff",
  borderRadius: "12px",
  boxShadow: "0 6px 16px rgba(0, 0, 0, 0.1)",
  fontFamily: "'Segoe UI', sans-serif",
};

const topBarStyle = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  marginBottom: "20px",
};

const backButtonStyle = {
  backgroundColor: "#ff9800",
  color: "#fff",
  border: "none",
  padding: "8px 16px",
  borderRadius: "20px",
  fontWeight: "bold",
  cursor: "pointer",
};

const titleStyle = {
  fontSize: "20px",
  fontWeight: "700",
  color: "#673ab7",
};

const searchContainerStyle = {
  display: "flex",
  justifyContent: "center",
  marginBottom: "20px",
};

const searchInputStyle = {
  padding: "8px 12px",
  fontSize: "14px",
  border: "2px solid #673ab7",
  borderRight: "none",
  borderRadius: "20px 0 0 20px",
  outline: "none",
  width: "250px",
};

const clearButtonStyle = {
  padding: "0 12px",
  border: "none",
  borderRadius: "0 20px 20px 0",
  fontSize: "16px",
  fontWeight: "bold",
};

const tableContainerStyle = {
  overflowX: "auto",
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse",
  tableLayout: "fixed",
  textAlign: "left",
};

const thStyle = {
  backgroundColor: "#7c4dff",
  color: "#fff",
  padding: "10px",
  fontSize: "14px",
  fontWeight: "600",
  borderBottom: "2px solid #673ab7",
  width: "33.33%", 
};

const tdStyle = {
  padding: "10px",
  fontSize: "14px",
  color: "#333",
  backgroundColor: "#fff",
  borderBottom: "1px solid #ddd",
  wordWrap: "break-word",
};

const noDataStyle = {
  padding: "20px",
  textAlign: "center",
  fontStyle: "italic",
  fontWeight: "500",
  color: "#999",
  backgroundColor: "#fff",
};

const paginationContainerStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  marginTop: "20px",
  gap: "20px",
  flexWrap: "wrap",
};

const paginationButtonStyle = {
  padding: "8px 16px",
  borderRadius: "20px",
  border: "none",
  color: "#fff",
  fontWeight: "600",
  cursor: "pointer",
};

const pageInfoStyle = {
  fontWeight: "600",
  fontSize: "14px",
  color: "#444",
};
