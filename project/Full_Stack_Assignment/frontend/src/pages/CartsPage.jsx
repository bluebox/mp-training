import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

import {
  getCartItems,
  removeCartItem,
  clearCart,
  checkoutCart,
} from "../services/CartService";

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const discount = 0.0;
  const [address, setAddress] = useState("");
  const { currentUser } = useAuth();
  const customerId = currentUser.userId;
  const navigate = useNavigate();

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    try {
      setLoading(true);
      const res = await getCartItems(customerId);

      let items = [];
      if (Array.isArray(res.data)) {
        items = res.data;
      } else if (res.data && Array.isArray(res.data.items)) {
        items = res.data.items;
      }

      setCartItems(items);
    } catch (error) {
      console.error("Failed to load cart", error);
      alert("Failed to load cart.");
    } finally {
      setLoading(false);
    }
  };

  const handleRemoveItem = async (cartItemId) => {
    try {
      await removeCartItem(cartItemId);
      loadCart();
    } catch (error) {
      console.error("Failed to remove item", error);
      alert("Failed to remove item.");
    }
  };

  const handleClearCart = async () => {
    try {
      await clearCart(customerId);
      loadCart();
    } catch (error) {
      console.error("Failed to clear cart", error);
      alert("Failed to clear cart.");
    }
  };

  const handleCheckout = async () => {
    try {
      if (!address.trim()) {
        alert("Please provide a shipping address.");
        return;
      }
      const checkoutData = { address };
      const response = await checkoutCart(customerId, checkoutData);

      if (response.data && response.data.orderId) {
        alert(
          `Checkout successful! Your order ID is ${response.data.orderId}. Redirecting to Orders page.`
        );
      } else {
        alert("Checkout successful! Redirecting to Orders page.");
      }

      navigate("/orders");
    } catch (error) {
      console.error("Checkout failed", error);
      alert("Checkout failed.");
    }
  };

  const calculateTotals = () => {
    const subtotal = cartItems.reduce(
      (total, item) => total + item.price * item.quantity,
      0
    );
    const salesTax = subtotal * 0.1;
    const grandTotal = subtotal + salesTax - discount;
    return { subtotal, salesTax, grandTotal };
  };

  const { subtotal, salesTax, grandTotal } = calculateTotals();

  return (
    <div className="container my-5 text-light bg-dark p-4 rounded shadow">
      <h1 className="mb-4">My Cart</h1>

      {loading ? (
        <p>Loading...</p>
      ) : cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div className="row gy-4">
          {cartItems.map((item) => (
            <div key={item.cartItemId} className="col-md-6">
              <div className="card bg-secondary text-white">
                <div className="card-body">
                  <h5 className="card-title">{item.productName}</h5>
                  <p className="card-text mb-1">
                    <strong>Price:</strong> ₹{item.price.toFixed(2)}
                  </p>
                  <p className="card-text mb-1">
                    <strong>Quantity:</strong> {item.quantity}
                  </p>
                  <p className="card-text">
                    <strong>Total:</strong> ₹
                    {(item.price * item.quantity).toFixed(2)}
                  </p>
                  <button
                    className="btn btn-danger btn-sm mt-2"
                    onClick={() => handleRemoveItem(item.cartItemId)}
                  >
                    Remove
                  </button>
                </div>
              </div>
            </div>
          ))}

          {/* Totals */}
          <div className="col-12 mt-4">
            <div className="card bg-dark border-light text-white">
              <div className="card-body">
                <div className="d-flex justify-content-between mb-2">
                  <span>Subtotal:</span>
                  <span>₹{subtotal.toFixed(2)}</span>
                </div>
                <div className="d-flex justify-content-between mb-2">
                  <span>Sales Tax (10%):</span>
                  <span>₹{salesTax.toFixed(2)}</span>
                </div>
                <div className="d-flex justify-content-between mb-2">
                  <span>Discount:</span>
                  <span>-₹{discount.toFixed(2)}</span>
                </div>
                <hr className="border-light" />
                <div className="d-flex justify-content-between fs-5 fw-bold">
                  <span>Grand Total:</span>
                  <span>₹{grandTotal.toFixed(2)}</span>
                </div>
              </div>
            </div>
          </div>

          {/* Address Input */}
          <div className="col-12 mt-4">
            <label htmlFor="addressInput" className="form-label">
              Shipping Address
            </label>
            <input
              id="addressInput"
              type="text"
              className="form-control bg-dark text-white border-secondary"
              placeholder="Enter shipping address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
          </div>

          {/* Action Buttons */}
          <div className="col-12 mt-4 d-flex gap-3">
            <button
              className="btn btn-outline-warning"
              onClick={handleClearCart}
            >
              Clear Cart
            </button>
            <button className="btn btn-success" onClick={handleCheckout}>
              Checkout
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartPage;
