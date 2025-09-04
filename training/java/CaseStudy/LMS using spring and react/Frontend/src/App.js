import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Header from './components/common/Header';
import Dashboard from './pages/Dashboard';
import Books from './pages/Books';
import Members from './pages/Members';
import Reports from './pages/Reports';
import Transactions from './pages/Transactions';

import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/main.css';
import './styles/components.css';
import './styles/dashboard.css';
import './styles/header.css';
import './styles/responsive.css';

function App() {
  return (
    <Router>
      <div className="App fade-in">
        <Header />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/books" element={<Books />} />
            <Route path="/members" element={<Members />} />
            <Route path="/transactions" element={<Transactions />} />
            <Route path="/reports" element={<Reports />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
