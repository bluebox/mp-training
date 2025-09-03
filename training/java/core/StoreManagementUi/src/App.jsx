import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';


import MainPage from './MainPage';
import PurchasePage from './PurchasePage';
import StoreStockPage from './StoreStockPage';
import SuppliersPage from './SuppliersPage';
import SuppliersForm from './SuppliersForm';
import ViewSuppliers from './ViewSuppliers';
import ViewProducts from './ViewProducts';
import ProductsForm from './ProductsForm';
import PurchaseDetails from './PurchaseDetails';
import ProductsStock from './ProductsStock';
import Login from './Login';


import LogoutButton from './LogoutButton';
import PrivateRoute from "./PrivateRoute";

function App() {
  return (
    <BrowserRouter>
     
      <LogoutButton />

      <Routes>
        <Route path="/" element={<PrivateRoute><MainPage /></PrivateRoute>} />
        <Route path="/suppliers" element={<PrivateRoute><SuppliersPage /></PrivateRoute>} />
        <Route path="/purchase" element={<PrivateRoute><PurchasePage /></PrivateRoute>} />
        <Route path="/store_stock" element={<PrivateRoute><StoreStockPage /></PrivateRoute>} />
        <Route path="/add-supplier" element={<PrivateRoute><SuppliersForm /></PrivateRoute>} />
        <Route path="/view-suppliers" element={<PrivateRoute><ViewSuppliers /></PrivateRoute>} />
        <Route path="/view-products" element={<PrivateRoute><ViewProducts /></PrivateRoute>} />
        <Route path="/add-product" element={<PrivateRoute><ProductsForm /></PrivateRoute>} />
        <Route path="/purchases/:id" element={<PrivateRoute><PurchaseDetails /></PrivateRoute>} />
        <Route path="/products_stock" element={<PrivateRoute><ProductsStock /></PrivateRoute>} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
