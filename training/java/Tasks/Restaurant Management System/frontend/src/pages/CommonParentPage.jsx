import React, { useContext, useEffect } from "react";
import { Link, useNavigate, Outlet } from "react-router-dom";
import { logout } from "../services/authService";
import { UserContext } from "../utilities/UserContext";
import { useDispatch } from "react-redux";
import { setEmployees } from "../utilities/redux/slices/employeeSlice";
import api from "../config/axiosConfig";
import { setOrders } from "../utilities/redux/slices/orderSlice";
import { setItemCategories } from "../utilities/redux/slices/constantSlice";
import { setItems } from "../utilities/redux/slices/itemSlice";
import { getCategories } from "../services/itemService";
import { setLoading } from "../utilities/redux/slices/loadingSlice";
import Swal from "sweetalert2";

export default function AdminDashboard() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const userContext = useContext(UserContext);

  const isAdmin = userContext?.user?.role === "ROLE_ADMIN";
  const isStaff = userContext?.user?.role === "ROLE_STAFF";

  const logoutHandler = () => {
    if(confirm("Are you sure you want to logout?")) {
      dispatch(setLoading(true));
      logout();
      dispatch(setLoading(false));
      Swal.fire({
        icon: "success",
        title: "Success!",
        text: "User Logged out successfully.",
        timer: 2000,
        showConfirmButton: false,
      });
      navigate("/");
    }
  };

  useEffect(() => {
    async function fetchData() {
      const [itemsRes, ordersRes /*, waitersRes*/] = await Promise.all([
        api.get("/api/items/all"),
        api.get("/api/orders/allOrders"),
        // api.get("/api/waiters/available"),
      ]);

      getCategories()
        .then((res) => dispatch(setItemCategories(res)))
        .catch(() => dispatch(setItemCategories([])));
      dispatch(setItems(itemsRes.data.data));
      dispatch(setOrders(ordersRes.data.data));
      // dispatch(setWaiters(waitersRes.data.data));
      if (isAdmin) {
        const empRes = await api.get("/api/employees");
        dispatch(setEmployees(empRes.data.data));
      }
    }

    fetchData();
  }, [isAdmin]);

  return (
    <div className="d-flex flex-column vh-100">
      <nav className="navbar navbar-expand-lg navbar-light bg-light px-4 border-bottom">
        <span className="navbar-brand fw-bold">
          {isAdmin && <span>Admin</span>}
          {isStaff && <span>Staff</span>} Dashboard ( Welcome{" "}
          {userContext?.user?.sub} )
        </span>
        <div className="ms-auto">
          <button className="btn btn-primary" onClick={logoutHandler}>
            Logout
          </button>
        </div>
      </nav>

      <div className="d-flex flex-grow-1">
        <div className="bg-light border-end p-3" style={{ width: "220px" }}>
          <h5 className="fw-bold mb-4">Menu</h5>
          <ul className="nav flex-column gap-2">
            {isAdmin && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/admin" ? "fw-bold" : ""
                  }`}
                  to="/admin"
                >
                  Dashboard
                </Link>
              </li>
            )}
            {isAdmin && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/admin/employee-management"
                      ? "fw-bold"
                      : ""
                  }`}
                  to="/admin/employee-management"
                >
                  Employee Management
                </Link>
              </li>
            )}
            {isAdmin && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/admin/item-management"
                      ? "fw-bold"
                      : ""
                  }`}
                  to="/admin/item-management"
                >
                  Item Management
                </Link>
              </li>
            )}

            {isStaff && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/staff" ? "fw-bold" : ""
                  }`}
                  to="/staff"
                >
                  Item Availability Management
                </Link>
              </li>
            )}

            {isStaff && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/staff/take-orders" ? "fw-bold" : ""
                  }`}
                  to="/staff/take-orders"
                >
                  Take Orders
                </Link>
              </li>
            )}

            {isStaff && (
              <li className="nav-item">
                <Link
                  className={`nav-link text-dark ${
                    location.pathname === "/staff/order-management"
                      ? "fw-bold"
                      : ""
                  }`}
                  to="/staff/order-management"
                >
                  Order Management
                </Link>
              </li>
            )}
          </ul>
        </div>

        <div className="flex-grow-1 p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
