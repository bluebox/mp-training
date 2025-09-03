import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import api from "../config/axiosConfig";
import { addOrder } from "../utilities/redux/slices/orderSlice";
import { useDispatch } from "react-redux";
import { setLoading } from "../utilities/redux/slices/loadingSlice";

export default function FoodSelection({ name, phone, waiterId }) {
  const [items, setItems] = useState([]);
  const [selectedItems, setSelectedItems] = useState({});
  const [reviewMode, setReviewMode] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    api
      .get("/api/items/availableItems")
      .then((res) => {
        if (res.data.success) {
          setItems(res.data.data);
        } else {
          console.error("Failed to fetch items:", res.data.message);
        }
      })
      .catch((err) => console.error("Error fetching items:", err));
  }, []);

  const handleAdd = (itemId) => {
    setSelectedItems((prev) => ({ ...prev, [itemId]: 1 }));
  };

  const handleIncrement = (itemId) => {
    setSelectedItems((prev) => ({ ...prev, [itemId]: prev[itemId] + 1 }));
  };

  const handleDecrement = (itemId) => {
    setSelectedItems((prev) => {
      if (prev[itemId] === 1) {
        const newItems = { ...prev };
        delete newItems[itemId];
        return newItems;
      }
      return { ...prev, [itemId]: prev[itemId] - 1 };
    });
  };

  const getPayload = () => {
    return Object.entries(selectedItems).map(([itemId, qty]) => {
      const item = items.find((i) => i.id === parseInt(itemId));
      return {
        itemId: parseInt(itemId),
        quantity: qty,
        price: item.price,
      };
    });
  };

  const handleFirstConfirm = async () => {
    if (Object.keys(selectedItems).length === 0) {
      Swal.fire("Oops!", "Select at least 1 item", "warning");
      return;
    }
    setReviewMode(true);
  };
  const handleFinalConfirm = async () => {
    try {
      const totalPrice = getTotalAmount(selectedItems);
      dispatch(setLoading(true));
      const response = await api.post("/api/orders/addOrder", {
        name,
        phone,
        waiterId,
        totalPrice,
        orderDetailsList: getPayload(),
      });
      dispatch(setLoading(false));
      const newOrder = response.data.data;
      dispatch(addOrder(newOrder));
      Swal.fire({
        icon: "success",
        title: "Order Placed Successfully!",
        text: "Your food order has been confirmed.",
        confirmButtonColor: "#3085d6",
      }).then(() => {
        navigate("/staff/order-management");
      });
    } catch (err) {
      console.error("Error placing order:", err.response?.data || err.message);
      Swal.fire(
        "Error!",
        "Something went wrong while placing the order",
        "error"
      );
    }
  };

  const getTotalAmount = (selectedItems) => {
    return Object.entries(selectedItems).reduce((sum, [itemId, qty]) => {
      const item = items.find((i) => i.id === parseInt(itemId));
      return sum + (item ? item.price * qty : 0);
    }, 0);
  };

  return (
    <div className="container mt-4">
      {!reviewMode ? (
        <>
          <h2 className="mb-4 text-center">Select Food Items</h2>
          <div className="row">
            {items.map((item) => {
              const quantity = selectedItems[item.id] || 0;
              return (
                <div key={item.id} className="col-md-4 mb-4">
                  <div className="card h-100 shadow">
                    <img
                      src={item.imageUrl}
                      className="card-img-top"
                      alt={item.name}
                      style={{ height: "200px", objectFit: "cover" }}
                    />
                    <div className="card-body d-flex flex-column">
                      <h5 className="card-title">{item.name}</h5>
                      <p className="card-text">{item.description}</p>
                      <p className="mb-2">
                        <strong>Price:</strong> ₹{item.price}
                      </p>

                      <div className="mt-auto d-flex align-items-center justify-content-between">
                        {quantity > 0 ? (
                          <div className="d-flex align-items-center gap-2">
                            <button
                              className="btn btn-sm btn-danger"
                              onClick={() => handleDecrement(item.id)}
                            >
                              -
                            </button>
                            <span>{quantity}</span>
                            <button
                              className="btn btn-sm btn-success"
                              onClick={() => handleIncrement(item.id)}
                            >
                              +
                            </button>
                          </div>
                        ) : (
                          <button
                            className="btn btn-primary btn-sm"
                            onClick={() => handleAdd(item.id, item.price)}
                          >
                            Add
                          </button>
                        )}
                      </div>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
          {Object.keys(selectedItems).length > 0 && (
            <div className="text-center mt-3">
              <button className="btn btn-success" onClick={handleFirstConfirm}>
                Confirm
              </button>
            </div>
          )}
        </>
      ) : (
        <>
          <h2 className="mb-4 text-center">Review Your Order</h2>
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Item</th>
                <th>Qty</th>
                <th>Price (₹)</th>
                <th>Subtotal (₹)</th>
              </tr>
            </thead>
            <tbody>
              {getPayload().map((item) => (
                <tr key={item.itemId}>
                  <td>{items.find((i) => i.id === item.itemId)?.name}</td>
                  <td>{item.quantity}</td>
                  <td>{item.price}</td>
                  <td>{item.price * item.quantity}</td>
                </tr>
              ))}
              <tr>
                <td colSpan="3" className="text-end fw-bold">
                  Total
                </td>
                <td className="fw-bold">₹{getTotalAmount(selectedItems)}</td>
              </tr>
            </tbody>
          </table>
          <div className="text-center mt-3">
            <button
              className="btn btn-secondary me-2"
              onClick={() => setReviewMode(false)}
            >
              Back
            </button>
            <button className="btn btn-success" onClick={handleFinalConfirm}>
              Confirm Order
            </button>
          </div>
        </>
      )}
    </div>
  );
}
