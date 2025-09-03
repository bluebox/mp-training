import React from "react";
import { useNavigate } from "react-router-dom";

export default function MainPage() {
  const navigate = useNavigate();

  return (
    <div className="page">
      <div className="card">
        
        <h1 className="title">STORE STOCK MANAGEMENT SYSTEM</h1>
        <button onClick={() => navigate("/suppliers")} className="btn blue">Suppliers And Products</button>
        <button onClick={() => navigate("/purchase")} className="btn green">Purchase product</button>
        <button onClick={() => navigate("/store_stock")} className="btn blue">View all purchases</button>
         <button onClick={() => navigate("/products_stock")} className="btn green">Products Stock</button>
        
        
      </div>
    </div>
  );
}
