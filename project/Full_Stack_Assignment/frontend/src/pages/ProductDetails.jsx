import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ProductDetails = () => {
  const { id } = useParams(); 
  const [product, setProduct] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/product-approval/products/${id}`)
      .then((res) => res.json())
      .then((data) => {
        if (data.success && data.data) {
          setProduct(data.data);
        } else if (data.productName) {
          setProduct(data); 
        }
      })
      .catch((err) => console.error("Error fetching product:", err));
  }, [id]);

  if (!product) {
    return <div style={{ textAlign: "center", marginTop: "2rem" }}>Loading product details...</div>;
  }

  return (
    <div style={{ maxWidth: "800px", margin: "2rem auto", padding: "1rem" }}>
      <img
        src={`https://picsum.photos/seed/${product.id}/600/400`}
        alt={product.productName}
        style={{ width: "100%", borderRadius: "8px", marginBottom: "1rem" }}
      />
      <h1>{product.productName}</h1>
      <p style={{ fontSize: "1.2rem", color: "#555" }}>{product.description}</p>
      <p style={{ fontWeight: "bold", fontSize: "1.5rem", color: "#2ecc71" }}>
        ₹{product.price}
      </p>
      <p><strong>Brand:</strong> {product.brand}</p>
      <p><strong>Category:</strong> {product.categoryName}</p>
    </div>
  );
};

export default ProductDetails;
