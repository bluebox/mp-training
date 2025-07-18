import React, { useState, useEffect } from "react";
import { Overlay, Popover, Form, InputGroup, Button } from "react-bootstrap";
import { addItemToCart } from "../services/CartService";
import { useAuth } from "../context/AuthContext";
import { makeRequest } from "../services/AxiosTemplate";
import { getAllCategories } from "../services/CategoryService";

// Generate a stable random image per product using Picsum
const getRandomImage = (seed) => `https://picsum.photos/seed/${seed}/400/300`;

const PAGE_SIZE = 6; // Number of products per page

const Home = () => {
  const { currentUser } = useAuth();

  const [categories, setCategories] = useState([
    { categoryId: 0, categoryName: "All" },
  ]);
  const [selectedCategoryId, setSelectedCategoryId] = useState(0);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);

  // Product detail popover state
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [showPopover, setShowPopover] = useState(false);
  const [target, setTarget] = useState(null);

  // Cart state
  const [cartItems, setCartItems] = useState({});
  const [addingToCart, setAddingToCart] = useState({});

  useEffect(() => {
  const fetchCategories = async () => {
    try {
      const data = await getAllCategories();
      if (Array.isArray(data)) {
        setCategories([{ categoryId: 0, categoryName: "All" }, ...data]);
      }
    } catch (err) {
      console.error("Error fetching categories:", err);
    }
  };

  fetchCategories();
}, []);

  useEffect(() => {
    const fetchProducts = async () => {
      setLoading(true);
      setCurrentPage(1); // Reset to first page on category change

      try {
        const url =
          selectedCategoryId === 0
            ? "/api/product-approval/products"
            : "/api/category-product-mappings/category/:id/products";

        const pathVars =
          selectedCategoryId === 0 ? {} : { id: selectedCategoryId };

        const response = await makeRequest("GET", url, pathVars);

        let productList = [];
        if (Array.isArray(response)) {
          productList = response;
        } else if (response.success && Array.isArray(response.data)) {
          productList = response.data;
        }

        setProducts(productList);
      } catch (error) {
        console.error("Failed to fetch products:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, [selectedCategoryId]);

  // Filter products based on search query
  useEffect(() => {
    if (searchQuery.trim() === "") {
      setFilteredProducts(products);
    } else {
      const filtered = products.filter((product) =>
        product.productName.toLowerCase().includes(searchQuery.toLowerCase())
      );
      setFilteredProducts(filtered);
      setCurrentPage(1); // Reset to first page when searching
    }
  }, [searchQuery, products]);

  // Pagination logic
  const totalPages = Math.ceil(filteredProducts.length / PAGE_SIZE);
  const paginatedProducts = filteredProducts.slice(
    (currentPage - 1) * PAGE_SIZE,
    currentPage * PAGE_SIZE
  );

  const handlePrev = () => setCurrentPage((p) => Math.max(1, p - 1));
  const handleNext = () => setCurrentPage((p) => Math.min(totalPages, p + 1));
  const handlePageClick = (page) => setCurrentPage(page);

  // Close popover
  const handlePopoverClose = () => {
    setShowPopover(false);
  };

  // Cart functionality
  const handleAddToCartClick = (productId) => {
    setAddingToCart((prev) => ({
      ...prev,
      [productId]: true,
    }));
    setCartItems((prev) => ({
      ...prev,
      [productId]: 1,
    }));
  };

  const handleQuantityChange = (productId, change) => {
    setCartItems((prev) => {
      const newQuantity = Math.max(1, (prev[productId] || 1) + change);
      return {
        ...prev,
        [productId]: newQuantity,
      };
    });
  };

  const handleConfirmAddToCart = async (product) => {
    try {
      const quantity = cartItems[product.productId] || 1;
      console.log(currentUser.userId, product.productId, quantity);

      await addItemToCart(
        currentUser.userId,
        product.productId,
        quantity,
        product.price,
        product.productName
      );

      // Reset states after adding to cart
      setAddingToCart((prev) => ({
        ...prev,
        [product.productId]: false,
      }));

      // You might want to show a success message here
      alert(`Added ${quantity} ${product.productName}(s) to cart!`);
    } catch (error) {
      console.error("Error adding item to cart:", error);
      alert("Failed to add item to cart. Please try again.");
    }
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const clearSearch = () => {
    setSearchQuery("");
  };

  return (
    <div
      style={{
        fontFamily: "Arial, sans-serif",
        background: "#f5f5f5",
        minHeight: "100vh",
      }}
    >
      <main style={{ maxWidth: 1200, margin: "2rem auto", padding: "0 1rem" }}>
        {/* Search Bar */}
        <div style={{ marginBottom: "2rem" }}>
          <InputGroup className="mb-3">
            <Form.Control
              placeholder="Search products..."
              value={searchQuery}
              onChange={handleSearchChange}
              aria-label="Search products"
              style={{
                borderRadius: "4px 0 0 4px",
                padding: "0.6rem 1rem",
                border: "1px solid #ccc",
              }}
            />
            {searchQuery && (
              <Button
                variant="outline-secondary"
                onClick={clearSearch}
                style={{
                  borderLeft: "none",
                  borderRadius: "0",
                }}
              >
                ✕
              </Button>
            )}
            <Button
              variant="primary"
              style={{
                background: "#3498db",
                borderColor: "#3498db",
                borderRadius: searchQuery ? "0 4px 4px 0" : "0 4px 4px 0",
              }}
            >
              Search
            </Button>
          </InputGroup>
        </div>

        {/* Category Filter */}
        <div
          style={{
            marginBottom: "2rem",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <label
            htmlFor="category-filter"
            style={{ marginRight: 8, fontWeight: "bold" }}
          >
            Filter by Category:
          </label>
          <select
            id="category-filter"
            value={selectedCategoryId}
            onChange={(e) => setSelectedCategoryId(Number(e.target.value))}
            style={{
              padding: "0.4rem 1rem",
              borderRadius: 4,
              border: "1px solid #ccc",
            }}
          >
            {categories.map((cat) => (
              <option key={cat.categoryId} value={cat.categoryId}>
                {cat.categoryName}
              </option>
            ))}
          </select>
        </div>

        {/* Products List */}
        {loading ? (
          <div style={{ textAlign: "center", color: "#888", fontSize: 18 }}>
            Loading products...
          </div>
        ) : (
          <>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                gap: "2rem",
              }}
            >
              {paginatedProducts.map((product) => (
                <div
                  key={product.productId}
                  style={{
                    background: "#fff",
                    borderRadius: 8,
                    boxShadow: "0 2px 8px rgba(0,0,0,0.07)",
                    padding: "1rem",
                    textAlign: "center",
                    position: "relative",
                  }}
                >
                  <img
                    src={getRandomImage(product.productId)}
                    alt={product.productName}
                    style={{
                      width: "100%",
                      height: 180,
                      objectFit: "cover",
                      borderRadius: 6,
                    }}
                  />
                  <h3 style={{ margin: "1rem 0 0.5rem 0", color: "#222" }}>
                    {product.productName}
                  </h3>
                  <p
                    style={{
                      color: "#27ae60",
                      fontWeight: "bold",
                      fontSize: "1.1rem",
                    }}
                  >
                    ₹{product.price}
                  </p>
                  <div
                    style={{
                      display: "flex",
                      gap: "8px",
                      justifyContent: "center",
                    }}
                  >
                    <button
                      style={{
                        marginTop: "0.5rem",
                        padding: "0.5rem 1rem",
                        background: "#3498db",
                        color: "#fff",
                        border: "none",
                        borderRadius: 4,
                        cursor: "pointer",
                        fontWeight: "bold",
                      }}
                      onClick={(e) => {
                        setSelectedProduct(product);
                        setShowPopover(!showPopover);
                        setTarget(e.target);
                      }}
                    >
                      View Details
                    </button>
                    {addingToCart[product.productId] ? (
                      <div
                        style={{
                          marginTop: "0.5rem",
                          display: "flex",
                          gap: "8px",
                          alignItems: "center",
                        }}
                      >
                        <button
                          onClick={() =>
                            handleQuantityChange(product.productId, -1)
                          }
                          style={{
                            padding: "0.25rem 0.5rem",
                            background: "#222",
                            color: "#fff",
                            border: "none",
                            borderRadius: 4,
                            cursor: "pointer",
                            fontWeight: "bold",
                          }}
                        >
                          -
                        </button>
                        <span style={{ margin: "0 8px" }}>
                          {cartItems[product.productId] || 1}
                        </span>
                        <button
                          onClick={() =>
                            handleQuantityChange(product.productId, 1)
                          }
                          style={{
                            padding: "0.25rem 0.5rem",
                            background: "#222",
                            color: "#fff",
                            border: "none",
                            borderRadius: 4,
                            cursor: "pointer",
                            fontWeight: "bold",
                          }}
                        >
                          +
                        </button>
                        <button
                          onClick={() => handleConfirmAddToCart(product)}
                          style={{
                            padding: "0.25rem 0.5rem",
                            background: "#27ae60",
                            color: "#fff",
                            border: "none",
                            borderRadius: 4,
                            cursor: "pointer",
                            fontWeight: "bold",
                          }}
                        >
                          Confirm
                        </button>
                      </div>
                    ) : (
                      <button
                        onClick={() => handleAddToCartClick(product.productId)}
                        style={{
                          marginTop: "0.5rem",
                          padding: "0.5rem 1rem",
                          background: "#222",
                          color: "#fff",
                          border: "none",
                          borderRadius: 4,
                          cursor: "pointer",
                          fontWeight: "bold",
                        }}
                      >
                        Add to Cart
                      </button>
                    )}
                  </div>
                </div>
              ))}
            </div>
            {/* Pagination Controls */}
            {totalPages > 1 && (
              <div
                style={{
                  display: "flex",
                  justifyContent: "center",
                  margin: "2rem 0 0 0",
                  gap: 8,
                }}
              >
                <button
                  onClick={handlePrev}
                  disabled={currentPage === 1}
                  style={{
                    padding: "0.5rem 1rem",
                    borderRadius: 4,
                    border: "1px solid #ccc",
                    background: currentPage === 1 ? "#eee" : "#fff",
                    cursor: currentPage === 1 ? "not-allowed" : "pointer",
                  }}
                >
                  Prev
                </button>
                {Array.from({ length: totalPages }, (_, i) => (
                  <button
                    key={i + 1}
                    onClick={() => handlePageClick(i + 1)}
                    style={{
                      padding: "0.5rem 1rem",
                      borderRadius: 4,
                      border: "1px solid #ccc",
                      background: currentPage === i + 1 ? "#3498db" : "#fff",
                      color: currentPage === i + 1 ? "#fff" : "#222",
                      cursor: "pointer",
                    }}
                  >
                    {i + 1}
                  </button>
                ))}
                <button
                  onClick={handleNext}
                  disabled={currentPage === totalPages}
                  style={{
                    padding: "0.5rem 1rem",
                    borderRadius: 4,
                    border: "1px solid #ccc",
                    background: currentPage === totalPages ? "#eee" : "#fff",
                    cursor:
                      currentPage === totalPages ? "not-allowed" : "pointer",
                  }}
                >
                  Next
                </button>
              </div>
            )}
          </>
        )}
      </main>

      {/* Product Detail Popover */}
      {selectedProduct && (
        <Overlay
          show={showPopover}
          target={target}
          placement="auto"
          container={document.body}
          rootClose
          onHide={handlePopoverClose}
        >
          <Popover id="product-popover" style={{ maxWidth: "400px" }}>
            <Popover.Header as="h3">
              {selectedProduct.productName}
            </Popover.Header>
            <Popover.Body>
              <img
                src={getRandomImage(selectedProduct.productId)}
                alt={selectedProduct.productName}
                style={{
                  width: "100%",
                  height: "350px",
                  objectFit: "cover",
                  borderRadius: 4,
                  marginBottom: 10,
                }}
              />
              {selectedProduct.description && (
                <p style={{ fontSize: "0.9rem", marginBottom: "8px" }}>
                  {selectedProduct.description}
                </p>
              )}
              <p style={{ fontSize: "0.9rem", marginBottom: "4px" }}>
                <strong>Price:</strong> ₹{selectedProduct.price}
              </p>

              {selectedProduct.quantity && (
                <p style={{ fontSize: "0.9rem", marginBottom: "4px" }}>
                  <strong>Quantity:</strong> {selectedProduct.quantity}
                </p>
              )}
              <p style={{ fontSize: "0.9rem", marginBottom: "4px" }}>
                <strong>Rating:</strong> {selectedProduct.averageRating}
              </p>
              <div style={{ display: "flex", gap: 10, marginTop: "10px" }}>
                {addingToCart[selectedProduct.productId] ? (
                  <>
                    <div
                      style={{
                        display: "flex",
                        flex: 1,
                        alignItems: "center",
                        justifyContent: "space-between",
                      }}
                    >
                      <button
                        onClick={() =>
                          handleQuantityChange(selectedProduct.productId, -1)
                        }
                        style={{
                          padding: "0.25rem 0.5rem",
                          background: "#222",
                          color: "#fff",
                          border: "none",
                          borderRadius: 4,
                          cursor: "pointer",
                          fontSize: "0.9rem",
                        }}
                      >
                        -
                      </button>
                      <span style={{ margin: "0 8px" }}>
                        {cartItems[selectedProduct.productId] || 1}
                      </span>
                      <button
                        onClick={() =>
                          handleQuantityChange(selectedProduct.productId, 1)
                        }
                        style={{
                          padding: "0.25rem 0.5rem",
                          background: "#222",
                          color: "#fff",
                          border: "none",
                          borderRadius: 4,
                          cursor: "pointer",
                          fontSize: "0.9rem",
                        }}
                      >
                        +
                      </button>
                    </div>
                    <button
                      onClick={() => {
                        handleConfirmAddToCart(selectedProduct);
                        handlePopoverClose();
                      }}
                      style={{
                        flex: 1,
                        padding: "0.5rem",
                        background: "#27ae60",
                        color: "#fff",
                        border: "none",
                        borderRadius: 4,
                        cursor: "pointer",
                        fontSize: "0.9rem",
                      }}
                    >
                      Confirm
                    </button>
                  </>
                ) : (
                  <>
                    <button
                      onClick={() =>
                        handleAddToCartClick(selectedProduct.productId)
                      }
                      style={{
                        flex: 1,
                        padding: "0.5rem",
                        background: "#222",
                        color: "#fff",
                        border: "none",
                        borderRadius: 4,
                        cursor: "pointer",
                        fontSize: "0.9rem",
                      }}
                    >
                      Add to Cart
                    </button>
                    <button
                      onClick={handlePopoverClose}
                      style={{
                        flex: 1,
                        padding: "0.5rem",
                        background: "#ddd",
                        color: "#222",
                        border: "none",
                        borderRadius: 4,
                        cursor: "pointer",
                        fontSize: "0.9rem",
                      }}
                    >
                      Close
                    </button>
                  </>
                )}
              </div>
            </Popover.Body>
          </Popover>
        </Overlay>
      )}

      <footer
        style={{
          background: "#222",
          color: "#fff",
          textAlign: "center",
          padding: "1rem",
          marginTop: "2rem",
        }}
      >
        &copy; {new Date().getFullYear()} E-Commerce App All rights reserved.
      </footer>
    </div>
  );
};

export default Home;
