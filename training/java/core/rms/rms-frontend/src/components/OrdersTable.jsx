import { useEffect, useState } from "react";
import api from "../config/axiosConfig";
import { useSelector,useDispatch } from "react-redux";
import { updateOrderStatus } from "../utilities/redux/slices/orderSlice";

export default function OrdersTable() {
  // const [orders, setOrders] = useState([]);
  const dispatch=useDispatch();
  const orders=useSelector((store) => store.orders);
  const [statuses] = useState(["Pending", "Cancelled", "Completed"]);
  const [currentPage, setCurrentPage] = useState(1);
  const [filterStatus, setFilterStatus] = useState("");
  const [filterDate, setFilterDate] = useState("");

  const itemsPerPage = 7;

  // useEffect(() => {
  //   fetchOrders();
  // }, []);

  // const fetchOrders = async () => {
  //   try {
  //     const res = await api.get("/api/orders/allOrders");
  //     if (res.data.success) setOrders(res.data.data);
  //   } catch (err) {
  //     console.error("Error fetching orders:", err);
  //   }
  // };

  const handleStatusChange = async (orderId, newStatus) => {
    try {
      await api.put(
        `/api/orders/updateStatus?orderId=${orderId}&status=${newStatus}`
      );
      // setOrders((prev) =>
      //   prev.map((order) =>
      //     order.orderId === orderId ? { ...order, status: newStatus } : order
      //   )
      // );
      dispatch(updateOrderStatus({orderId, newStatus}));
    } catch (err) {
      console.error("Error updating status:", err);
    }
  };

  const getAvailableStatuses = (currentStatus) => {
    if (currentStatus === "Pending") {
      return statuses.filter((s) => s !== "Pending");
    }
    return [];
  };

  const filteredOrders = orders
    .filter((order) => !filterStatus || order.status === filterStatus)
    .filter((order) => !filterDate || order.orderDate.startsWith(filterDate));

  const indexOfLast = currentPage * itemsPerPage;
  const indexOfFirst = indexOfLast - itemsPerPage;
  const currentOrders = filteredOrders.slice(indexOfFirst, indexOfLast);
  const totalPages = Math.ceil(filteredOrders.length / itemsPerPage);

  return (
    <div className="container mt-4">
      <h2 className="mb-3">Orders Table</h2>
      <div className="d-flex justify-content-between mb-2">
        <span>
          Total Records: <strong>{filteredOrders.length}</strong>
        </span>

        <div className="mb-3 d-flex align-items-center gap-2">
          <label htmlFor="filterDate" className="form-label mb-0">
            Filter by Date:
          </label>
          <input
            type="date"
            className="form-control w-auto"
            value={filterDate}
            onChange={(e) => setFilterDate(e.target.value)}
          />
        </div>

        <div className="mb-3 d-flex align-items-center gap-2">
          <label htmlFor="filterStatus" className="form-label mb-0">
            Filter by Status:
          </label>
          <select
            id="filterStatus"
            className="form-select w-auto"
            value={filterStatus}
            onChange={(e) => setFilterStatus(e.target.value)}
          >
            <option value="">All</option>
            {statuses.map((status) => (
              <option key={status} value={status}>
                {status}
              </option>
            ))}
          </select>
        </div>
        <span>
          Page {currentPage} of {totalPages || 1}
        </span>
      </div>

      <table className="table table-striped">
        <thead className="table-dark">
          <tr>
            <th>Order ID</th>
            <th>Customer ID</th>
            <th>Waiter ID</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Change Status</th>
          </tr>
        </thead>
        <tbody>
          {currentOrders.length > 0 ? (
            currentOrders.map((order) => {
              const availableStatuses = getAvailableStatuses(order.status);
              return (
                <tr key={order.orderId}>
                  <td>{order.orderId}</td>
                  <td>{order.customerId}</td>
                  <td>{order.waiterId}</td>
                  <td>{order.orderDate}</td>
                  <td>₹{order.amount}</td>
                  <td>{order.status}</td>
                  <td>
                    {availableStatuses.length > 0 ? (
                      <select
                        className="form-select w-auto"
                        onChange={(e) =>
                          handleStatusChange(order.orderId, e.target.value)
                        }
                      >
                        <option value="">Select</option>
                        {availableStatuses.map((status) => (
                          <option key={status} value={status}>
                            {status}
                          </option>
                        ))}
                      </select>
                    ) : (
                      "-"
                    )}
                  </td>
                </tr>
              );
            })
          ) : (
            <tr>
              <td colSpan="8" className="text-center">
                No records available
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <nav>
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 1 ? "disabled" : ""}`}>
            <button
              className="page-link"
              onClick={() => setCurrentPage(currentPage - 1)}
            >
              Previous
            </button>
          </li>
          {[...Array(totalPages).keys()].map((num) => (
            <li
              key={num + 1}
              className={`page-item ${currentPage === num + 1 ? "active" : ""}`}
            >
              <button
                className="page-link"
                onClick={() => setCurrentPage(num + 1)}
              >
                {num + 1}
              </button>
            </li>
          ))}
          <li
            className={`page-item ${
              currentPage === totalPages ? "disabled" : ""
            }`}
          >
            <button
              className="page-link"
              onClick={() => setCurrentPage(currentPage + 1)}
            >
              Next
            </button>
          </li>
        </ul>
      </nav>
    </div>
  );
}
