import React, { useEffect, useState, useRef, useCallback } from "react";
import { getApprovedProducts } from "../services/productApi";

const ApprovedProductsTable = ({ refresh }) => {
  const [allProducts, setAllProducts] = useState([]);
  const [visibleProducts, setVisibleProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 2;

  const observerRef = useRef();
  useEffect(() => {
    getApprovedProducts().then((res) => {
      setAllProducts(res);
      setCurrentPage(1);
      setVisibleProducts(res.slice(0, pageSize));
    });
  }, [refresh]);

  const lastRowRef = useCallback(
    (node) => {
      if (observerRef.current) observerRef.current.disconnect();
      observerRef.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting) {
          loadMore();
        }
      });
      if (node) observerRef.current.observe(node);
    },
    [visibleProducts]
  );

  const loadMore = () => {
    const nextPage = currentPage + 1;
    const nextProducts = allProducts.slice(0, nextPage * pageSize);
    if (nextProducts.length > visibleProducts.length) {
      setVisibleProducts(nextProducts);
      setCurrentPage(nextPage);
    }
  };

  return (
    <div
      className="card bg-dark text-white p-3 shadow"
      style={{ maxHeight: "500px", overflowY: "auto" }}
    >
      <h4>Approved Products</h4>
      <table className="table table-dark table-striped table-bordered mt-3">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Rating</th>
            <th>Created At</th>
          </tr>
        </thead>
        <tbody>
          {visibleProducts.map((p, index) => {
            const isLast = index === visibleProducts.length - 1;
            return (
              <tr key={p.productId} ref={isLast ? lastRowRef : null}>
                <td>{p.productName}</td>
                <td>{p.description}</td>
                <td>{p.price}</td>
                <td>{p.quantity}</td>
                <td>{p.averageRating}</td>
                <td>{new Date(p.createdAtDate).toLocaleString()}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default ApprovedProductsTable;
