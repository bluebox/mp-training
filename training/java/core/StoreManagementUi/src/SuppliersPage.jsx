import React from "react";
import { useNavigate } from "react-router-dom";

export default function SuppliersPage() {
  const navigate = useNavigate();

  const handleBack = () => {
    navigate(-1);
  };

  const handleAddSupplier = () => {
    navigate("/add-supplier");
  };

  const handleAddProduct = () => {
    navigate("/add-product");
  };

  const handleViewSuppliers = () => {
    navigate("/view-suppliers");
  };

  const handleViewProducts = () => {
    navigate("/view-products");
  };

  return (
    <div style={{ padding: "20px", maxWidth: "800px", margin: "0 auto", fontFamily: "Arial, sans-serif" }}>
      <button
        onClick={handleBack}
        style={{
          position: "absolute",  
          top: "20px",           
          left: "20px",          
          padding: "10px 15px",
          backgroundColor: "#f0b222ff",
          border: "1px solid #ddd",
          borderRadius: "20px",
          fontSize: "16px",
          cursor: "pointer",
          color: "#fff",
         
        }}
       // onMouseEnter={(e) => e.target.style.backgroundColor = "#ddd"}
       // onMouseLeave={(e) => e.target.style.backgroundColor = "#f0f0f0"}
      >
        ← Back
      </button>
      
      <h2 style={{ fontSize: "24px", marginBottom: "20px", color: "#333", marginTop: "50px" }}>Supplier And Product management</h2>
      
      <div style={{ marginTop: "20px" }}>
        <button
          onClick={handleAddSupplier}
          style={{
            padding: "12px 20px",
            backgroundColor: "#007bff",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            fontSize: "16px",
            cursor: "pointer",
            transition: "background-color 0.3s ease, transform 0.2s ease",
            marginRight: "10px"
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = "#0056b3"}
          onMouseLeave={(e) => e.target.style.backgroundColor = "#007bff"}
          onMouseDown={(e) => e.target.style.transform = "translateY(1px)"}
          onMouseUp={(e) => e.target.style.transform = "translateY(0)"}
        >
          Add Supplier
        </button>
        <button
          onClick={handleAddProduct}
          style={{
            padding: "12px 20px",
            backgroundColor: "#007bff",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            fontSize: "16px",
            cursor: "pointer",
            transition: "background-color 0.3s ease, transform 0.2s ease"
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = "#0056b3"}
          onMouseLeave={(e) => e.target.style.backgroundColor = "#007bff"}
          onMouseDown={(e) => e.target.style.transform = "translateY(1px)"}
          onMouseUp={(e) => e.target.style.transform = "translateY(0)"}
        >
          Add Product
        </button>
      </div>
      <div style={{ marginTop: "20px" }}>
        <button
          onClick={handleViewSuppliers}
          style={{
            padding: "12px 20px",
            backgroundColor: "#28a745",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            fontSize: "16px",
            cursor: "pointer",
            transition: "background-color 0.3s ease, transform 0.2s ease",
            marginRight: "10px"
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = "#218838"}
          onMouseLeave={(e) => e.target.style.backgroundColor = "#28a745"}
          onMouseDown={(e) => e.target.style.transform = "translateY(1px)"}
          onMouseUp={(e) => e.target.style.transform = "translateY(0)"}
        >
          View All Suppliers
        </button>
        <button
          onClick={handleViewProducts}
          style={{
            padding: "12px 20px",
            backgroundColor: "#28a745",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            fontSize: "16px",
            cursor: "pointer",
            transition: "background-color 0.3s ease, transform 0.2s ease"
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = "#218838"}
          onMouseLeave={(e) => e.target.style.backgroundColor = "#28a745"}
          onMouseDown={(e) => e.target.style.transform = "translateY(1px)"}
          onMouseUp={(e) => e.target.style.transform = "translateY(0)"}
        >
          View All Products
        </button>
      </div>
    </div>
  );
}
