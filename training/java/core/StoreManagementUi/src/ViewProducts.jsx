import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import debounce from "lodash/debounce";
import api from "./api/axios";

export default function ViewProducts() {
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;
  const navigate = useNavigate();

  const fetchProducts = useCallback(
    debounce((searchKey) => {
      const url = searchKey.trim()
        ? `/products?searchKey=${(searchKey)}`
        : "/products";

      api
        .get(url)
        .then((res) => {
          if (Array.isArray(res.data)) {
            setProducts(res.data);
            setCurrentPage(1);
          } else {
            setProducts([]);
          }
        })
        .catch((err) => {
          console.error("Error fetching products:", err);
          setProducts([]);
        });
    }, 400),
    []
  );

  useEffect(() => {
    fetchProducts(search);
  }, [search]);

  useEffect(() => {
    fetchProducts("");
  }, []);

  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentProducts = products.slice(startIndex, startIndex + itemsPerPage);
  const totalPages = Math.ceil(products.length / itemsPerPage);

  return (
    <div style={containerStyle}>
      <div style={topBarStyle}>
        <button onClick={() => navigate(-1)} style={backButtonStyle}>
          ⬅ Back
        </button>
        <h2 style={titleStyle}>View All Products</h2>
      </div>

      <div style={searchContainerStyle}>
        <input
          type="text"
          placeholder=" Search by ID or Name"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
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
              <th style={thStyle}>Status</th>
            </tr>
          </thead>
          <tbody>
            {currentProducts.length > 0 ? (
              currentProducts.map((product, index) => (
                <tr key={product.productId || index}>
                  <td style={tdStyle}>{product.productId}</td>
                  <td style={tdStyle}>{product.name}</td>
                  <td style={tdStyle}>
                    {product.status === "A"
                      ? "Active"
                      : product.status === "I"
                      ? "Inactive"
                      : product.status}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="3" style={noDataStyle}>
                  No products found.
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
