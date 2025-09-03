import "./App.css";
import { LoginForm } from "./pages/LoginForm";
import Employee from "./pages/Employee";
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
} from "react-router-dom";
import UserProvider from "./utilities/UserContext";
import AdminPage from "./pages/CommonParentPage";
import StaffPage from "./pages/CommonParentPage";
import Dashboard from "./pages/Dashboard";
import OrderForm from "./components/OrderForm";
import ItemsManagement from "./components/ItemsAvailability";
import OrdersTable from "./components/OrdersTable";
import ErrorPage from "./pages/ErrorPage";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import Item from "./components/Item";

function PrivateRoute({ children, roles }) {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  if (!token) {
    return <Navigate to="/" />;
  }

  if (roles && !roles.includes(role)) {
    return <Navigate to="/unauthorized" />;
  }
  return children;
}

export default function App() {
  const dispatch = useDispatch();

  useEffect(() => {}, [dispatch]);

  const appRouter = createBrowserRouter([
    {
      path: "/",
      element: <LoginForm />,
      errorElement: <ErrorPage />,
    },
    {
      path: "/admin",
      element: (
        <UserProvider>
          <AdminPage />
        </UserProvider>
      ),
      children: [
        {
          index: true,
          element: (
            <PrivateRoute roles={["ROLE_ADMIN"]}>
              <Dashboard />
            </PrivateRoute>
          ),
        },
        {
          path: "employee-management",
          element: (
            <PrivateRoute roles={["ROLE_ADMIN"]}>
              <Employee />
            </PrivateRoute>
          ),
        },
        {
          path: "item-management",
          element: (
            <PrivateRoute rol es={["ROLE_ADMIN"]}>
              <Item />
            </PrivateRoute>
          ),
        },
      ],
    },
    {
      path: "/staff",
      element: (
        <UserProvider>
          <StaffPage />
        </UserProvider>
      ),
      children: [
        {
          index: true,
          element: (
            <PrivateRoute roles={["ROLE_STAFF"]}>
              <ItemsManagement />
            </PrivateRoute>
          ),
        },
        {
          path: "take-orders",
          element: (
            <PrivateRoute roles={["ROLE_STAFF"]}>
              <OrderForm />
            </PrivateRoute>
          ),
        },
        {
          path: "order-management",
          element: (
            <PrivateRoute roles={["ROLE_STAFF"]}>
              <OrdersTable />
            </PrivateRoute>
          ),
        },
      ],
    },
  ]);
  return <RouterProvider router={appRouter} />;
}
