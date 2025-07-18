import React from "react";
import { BrowserRouter as Router } from "react-router";
import EcomRoutes from "./EcomRoutes";
import { AuthProvider } from "./context/AuthContext";
import Navbar from "./components/Navbar";
import "bootstrap-icons/font/bootstrap-icons.css";

const App = () => {
  return (
    <Router>
      <AuthProvider>
        <Navbar />
        <EcomRoutes />
      </AuthProvider>
    </Router>
  );
};

export default App;
