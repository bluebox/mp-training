import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./components/Home";
import Create from "./components/Create";
import Configurations from "./components/Configurations";
import Record from "./components//Record";
import "./App.css";


function App() {
  const [records, setRecords] = useState([]);
  const [config, setConfig] = useState({ maxRecords: 5, uniquePhone: false });

  useEffect(() => {
    const stored = localStorage.getItem("records");
    const config = localStorage.getItem("config");
    if (stored) setRecords(JSON.parse(stored));
    if (config) setConfig(JSON.parse(config));
  }, []);

const [loaded, setLoaded] = useState(false);

useEffect(() => {
  const stored = localStorage.getItem("records");
  const config = localStorage.getItem("config");
  if (stored) setRecords(JSON.parse(stored));
  if (config) setConfig(JSON.parse(config));
  setLoaded(true); 
}, []);

useEffect(() => {
  if (loaded) {
    localStorage.setItem("records", JSON.stringify(records));
  }
}, [records, loaded]);


  useEffect(() => {
    localStorage.setItem("config", JSON.stringify(config));
  }, [config]);

  return (
    <Router>
      <nav>
        <Link to="/">Home</Link> | <Link to="/create">Create</Link> | <Link to="/configurations">Configurations</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home records={records} setRecords={setRecords} config={config} />} />
        <Route path="/create" element={<Create records={records} setRecords={setRecords} config={config} />} />
        <Route path="/edit/:id" element={<Create records={records} setRecords={setRecords} config={config} />} />
        <Route path="/configurations" element={<Configurations config={config} setConfig={setConfig} />} />
      </Routes>
    </Router>
  );
}

export default App;
