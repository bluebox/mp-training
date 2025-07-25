import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import Home from "./components/Home";
import Create from "./components/Create";
import Configurations from "./components/Configurations";
import Record from "./components/Record";
import Login from "./components/Login";
import Logout from "./components/Logout";
import "./App.css";

function App() {
  const [records, setRecords] = useState([]);
  const [config, setConfig] = useState({ maxRecords: 5, uniquePhone: false });
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    const stored = localStorage.getItem("records");
    const config = localStorage.getItem("config");
    const loginState = localStorage.getItem("isLoggedIn");

    if (stored) setRecords(JSON.parse(stored));
    if (config) setConfig(JSON.parse(config));
    if (loginState === "true") setIsLoggedIn(true);
    setLoaded(true);
  }, []);

  useEffect(() => {
    if (loaded) localStorage.setItem("records", JSON.stringify(records));
  }, [records, loaded]);

  useEffect(() => {
    localStorage.setItem("config", JSON.stringify(config));
  }, [config]);

  useEffect(() => {
    localStorage.setItem("isLoggedIn", isLoggedIn);
  }, [isLoggedIn]);

  return (
    <Router>
      {isLoggedIn && (
        <nav>
          <Link to="/">Home</Link> | <Link to="/create">Create</Link> | <Link to="/configurations">Configurations</Link> | <Link to="/logout">Logout</Link>
        </nav>
      )}
      <Routes>
        {!isLoggedIn ? (
          <>
            <Route path="*" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
          </>
        ) : (
          <>
            <Route path="/" element={<Home records={records} setRecords={setRecords} config={config} />} />
            <Route path="/create" element={<Create records={records} setRecords={setRecords} config={config} />} />
            <Route path="/edit/:id" element={<Create records={records} setRecords={setRecords} config={config} />} />
            <Route path="/configurations" element={<Configurations config={config} setConfig={setConfig} />} />
            <Route path="/logout" element={<Logout setIsLoggedIn={setIsLoggedIn} />} />
            <Route path="/login" element={<Navigate to="/" />} />
          </>
        )}
      </Routes>
    </Router>
  );
}

export default App;

