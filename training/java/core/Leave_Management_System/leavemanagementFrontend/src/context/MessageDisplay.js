import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import AdminDashboard from "./components/AdminDashboard";
import ManagerDashboard from "./components/ManagerDashboard";
import EmployeeDashboard from "./components/EmployeeDashboard";
import LoginPage from "./pages/Login";
import { MessageProvider } from "./context/MessageContext";
import { AuthProvider, useAuth } from "./context/AuthContext";


const MessageDisplay = () => {
  const { message, messageType } = useAuth().useMessage ? useAuth().useMessage() : { message: '', messageType: '' };
  return (
    message && (
      <div
        style={{
          position: "fixed",
          top: "20px",
          right: "20px",
          padding: "12px 20px",
          borderRadius: "6px",
          color: "#fff",
          backgroundColor: messageType === "error" ? "#e74c3c" : "#2ecc71",
          zIndex: 1000,
        }}
      >
        {message}
      </div>
    )
  );
};


const RequireAuth = ({ allowedRoles, children }) => {
  const { user, isAuthReady } = useAuth();
  const role = user?.role;

  if (!isAuthReady) {
    return <div>Loading...</div>;
  }

  if (!role) {
    return <Navigate to="/" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(role)) {
    return <Navigate to="/" replace />;
  }

  return children;
};

function App() {
  return (
    <Router>
      <AuthProvider>
        <MessageProvider>
         
          <MessageDisplay />

          <Routes>
            <Route path="/" element={<LoginPage />} />

            <Route
              path="/admindashboard/*"
              element={
                <RequireAuth allowedRoles={["ROLE_ADMIN"]}>
                  <AdminDashboard />
                </RequireAuth>
              }
            />

            <Route
              path="/managerdashboard/*"
              element={
                <RequireAuth allowedRoles={["ROLE_MANAGER"]}>
                  <ManagerDashboard />
                </RequireAuth>
              }
            />

            <Route
              path="/employeedashboard/*"
              element={
                <RequireAuth allowedRoles={["ROLE_EMPLOYEE", "ROLE_MANAGER", "ROLE_ADMIN"]}>
                  <EmployeeDashboard />
                </RequireAuth>
              }
            />
          </Routes>
        </MessageProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
