import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import debounce from "lodash/debounce";
import api from "./api/axios";

export default function ViewSuppliers() {
  const [filteredSuppliers, setFilteredSuppliers] = useState([]);
  const [search, setSearch] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;
  const navigate = useNavigate();

  
  const fetchSuppliers = useCallback(
    debounce((searchKey) => {
      const url = searchKey.trim()
        ? `/suppliers?searchKey=${(searchKey)}`
        : "/suppliers";

      api
        .get(url)
        .then((res) => {
          setFilteredSuppliers(res.data);
          setCurrentPage(1);
        })
        .catch((err) => {
          console.error("Error fetching suppliers:", err);
          setFilteredSuppliers([]);
        });
    }, 400),
    []
  );

 
  useEffect(() => {
    fetchSuppliers(search);
  }, [search]);

 
  useEffect(() => {
    fetchSuppliers("");
  }, []);

  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentSuppliers = filteredSuppliers.slice(
    startIndex,
    startIndex + itemsPerPage
  );
  const totalPages = Math.ceil(filteredSuppliers.length / itemsPerPage);

  return (
    <div style={containerStyle}>
      <div style={topBarStyle}>
        <button onClick={() => navigate(-1)} style={backButtonStyle}>
          ⬅ Back
        </button>
        <h2 style={titleStyle}>View All Suppliers</h2>
      </div>

      <div style={searchContainerStyle}>
        <input
          type="text"
          placeholder="Search by name or Id"
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

      <div style={tableWrapperStyle}>
        <table style={tableStyle}>
          <thead>
            <tr style={tableHeaderRowStyle}>
              <th style={thStyle}>ID</th>
              <th style={thStyle}>Name</th>
              <th style={thStyle}>Email</th>
              <th style={thStyle}>Mobile</th>
              <th style={thStyle}>Gender</th>
              <th style={thStyle}>City</th>
              <th style={thStyle}>State</th>
              <th style={thStyle}>Country</th>
              <th style={thStyle}>Address</th>
              <th style={thStyle}>Created By</th>
              <th style={thStyle}>Created At</th>
            </tr>
          </thead>
          <tbody>
            {currentSuppliers.length > 0 ? (
              currentSuppliers.map((supplier) => (
                <tr key={supplier.supplierId} style={tableRowStyle}>
                  <td style={tdStyle}>{supplier.supplierId}</td>
                  <td style={tdStyle}>{supplier.name}</td>
                  <td style={tdStyle}>{supplier.email}</td>
                  <td style={tdStyle}>{supplier.mobile}</td>
                  <td style={tdStyle}>{supplier.gender==="M" ? "Male":supplier.gender==="F" ?"Female":supplier.gender}</td>
                  <td style={tdStyle}>{supplier.city}</td>
                  <td style={tdStyle}>{supplier.state}</td>
                  <td style={tdStyle}>{supplier.country}</td>
                  <td style={tdStyle}>{supplier.address}</td>
                  <td style={tdStyle}>{supplier.createdBy}</td>
                  <td style={tdStyle}>
                    {new Date(supplier.createdAt).toLocaleDateString()}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="11" style={noDataStyle}>
                  No suppliers found.
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
  maxWidth: "100%",
  margin: "30px auto",
  padding: "30px",
  background: "#fefefe",
  boxShadow: "0 0 12px rgba(0,0,0,0.1)",
  borderRadius: "16px",
  fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
};

const topBarStyle = {
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  flexWrap: "wrap",
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
};

const titleStyle = {
  fontSize: "1.8rem",
  fontWeight: "700",
  color: "#673ab7",
  marginTop: "10px",
  textShadow: "0 0 4px rgba(103, 58, 183, 0.2)",
};

const searchContainerStyle = {
  display: "flex",
  justifyContent: "center",
  margin: "20px 0",
  gap: "6px",
};

const searchInputStyle = {
  padding: "12px 16px",
  fontSize: "16px",
  border: "2px solid #673ab7",
  borderRadius: "25px 0 0 25px",
  outline: "none",
  flex: 1,
  maxWidth: "400px",
};

const clearButtonStyle = {
  padding: "0 20px",
  border: "none",
  borderRadius: "0 25px 25px 0",
  fontSize: "18px",
  fontWeight: "bold",
  transition: "0.3s",
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
