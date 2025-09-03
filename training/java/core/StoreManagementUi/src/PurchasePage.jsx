import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import debounce from "lodash/debounce";
import api from "./api/axios";

export default function PurchaseForm() {
  const navigate = useNavigate();

  const [supplierQuery, setSupplierQuery] = useState("");
  const [productQuery, setProductQuery] = useState("");

  const [filteredSuppliers, setFilteredSuppliers] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);

  const [selectedSupplier, setSelectedSupplier] = useState(null);
  const [selectedProduct, setSelectedProduct] = useState(null);

  const [isProductSelected, setIsProductSelected] = useState(false);

  const [quantity, setQuantity] = useState("");
  const [taxableAmount, setTaxableAmount] = useState("");
  const [gstAmount, setGstAmount] = useState("");
  const [totalAmount, setTotalAmount] = useState(0);
  const [expiryDate, setExpiryDate] = useState("");

  
  const [createdBy, setCreatedBy] = useState("");

  useEffect(() => {
    const username = localStorage.getItem("username");
    if (username) {
      setCreatedBy(username);
    }
  }, []);


  const [cart, setCart] = useState([]);
  const [isSupplierLocked, setIsSupplierLocked] = useState(false);
  const [editIndex, setEditIndex] = useState(null);

  const [formErrors, setFormErrors] = useState({});
  const [purchaseMessage, setPurchaseMessage] = useState("");
  const [supplierChangeWarning, setSupplierChangeWarning] = useState(false);



  
  const fetchSuppliers = useCallback(
    debounce(async (q) => {
      if (!q.trim()) return setFilteredSuppliers([]);
      try {
        const { data } = await api.get(`/suppliers?searchKey=${q}`);
        setFilteredSuppliers(data);
      } catch {
        setFilteredSuppliers([]);
      }
    }, 300),
    []
  );

  const fetchProducts = useCallback(
    debounce(async (q) => {
      if (!q.trim()) return setFilteredProducts([]);
      try {
        const { data } = await api.get(`/products?searchKey=${q}`);
        setFilteredProducts(data);
      } catch {
        setFilteredProducts([]);
      }
    }, 300),
    []
  );

  useEffect(() => {
    if (!isSupplierLocked && supplierQuery) fetchSuppliers(supplierQuery);
    else setFilteredSuppliers([]);
  }, [supplierQuery, isSupplierLocked, fetchSuppliers]);

  useEffect(() => {
    if (isProductSelected) return;
    if (productQuery) fetchProducts(productQuery);
    else setFilteredProducts([]);
  }, [productQuery, isProductSelected, fetchProducts]);

  useEffect(() => {
    const qty = parseFloat(quantity) || 0;
    const tax = parseFloat(taxableAmount) || 0;
    const gst = parseFloat(gstAmount) || 0;
    setTotalAmount(qty * (tax + gst));
  }, [quantity, taxableAmount, gstAmount]);

  const resetForm = () => {
    setSelectedProduct(null);
    setProductQuery("");
    setQuantity("");
    setTaxableAmount("");
    setGstAmount("");
    setTotalAmount(0);
    setExpiryDate("");
    setEditIndex(null);
    setFormErrors({});
    setIsProductSelected(false);
  };

  const clearFormCompletely = () => {
    setSelectedSupplier(null);
    setSupplierQuery("");
    // setCreatedBy("");
    setCart([]);
    setIsSupplierLocked(false);
    resetForm();
    setSupplierChangeWarning(false);
    setPurchaseMessage("");
  };

  const validateForm = () => {
    const errors = {};
    if (!selectedSupplier) errors.supplier = "Supplier is required.";
    if (!selectedProduct) errors.product = "Product is required.";
    if (!quantity || !/^\d+$/.test(quantity) || +quantity <= 0) {
  errors.quantity = "Quantity must be a whole number greater than 0.";
}

    if (taxableAmount === "" || +taxableAmount < 0) errors.taxableAmount = "Valid taxable amount is required.";
    if (gstAmount === "" || +gstAmount < 0) errors.gstAmount = "Valid GST amount is required.";
    if (!expiryDate) errors.expiryDate = "Expiry date is required.";
    setFormErrors(errors);
    return !Object.keys(errors).length;
  };

  const addToCart = () => {
    setPurchaseMessage("");
    if (!validateForm()) return;
    const item = {
      supplier: selectedSupplier,
      product: selectedProduct,
      quantity: +quantity,
      taxableAmount: +taxableAmount,
      gstAmount: +gstAmount,
      totalAmount,
      expiryDate,
    };
    if (editIndex !== null) {
      const updated = [...cart];
      updated[editIndex] = item;
      setCart(updated);
    } else {
      setCart((prev) => [...prev, item]);
      setIsSupplierLocked(true);
      setSupplierChangeWarning(false);
    }
    resetForm();
  };

  const handleEdit = (idx) => {
    const item = cart[idx];
    setSelectedProduct(item.product);
    setProductQuery(`${item.product.productId} - ${item.product.name}`);
    setQuantity(String(item.quantity));
    setTaxableAmount(String(item.taxableAmount));
    setGstAmount(String(item.gstAmount));
    setExpiryDate(item.expiryDate);
    setEditIndex(idx);
    setFormErrors({});
    setPurchaseMessage("");
    setIsProductSelected(false);
  };

  const handleDelete = (idx) => {
    const updated = cart.filter((_, i) => i !== idx);
    setCart(updated);
    if (editIndex === idx) resetForm();
    if (!updated.length) setIsSupplierLocked(false);
    setPurchaseMessage("");
  };

  const handlePurchase = async () => {
    setPurchaseMessage("");
    if (!selectedSupplier) return setPurchaseMessage("Please select a supplier.");
    if (!cart.length) return setPurchaseMessage("Your cart is empty.");
    if (!createdBy.trim()) return setPurchaseMessage("Please enter your name.");

    const totalTaxable = cart.reduce((sum, i) => sum + i.taxableAmount * i.quantity, 0);
    const totalGST = cart.reduce((sum, i) => sum + i.gstAmount * i.quantity, 0);
    const total = cart.reduce((sum, i) => sum + i.totalAmount, 0);

    const payload = {
      supplierId: selectedSupplier.supplierId,
      totalTaxableAmount: totalTaxable,
      totalGst: totalGST,
      totalAmount: total,
      createdBy: createdBy.trim(),
      pd: cart.map((i) => ({
        productId: i.product.productId,
        quantity: i.quantity,
        taxableAmount: i.taxableAmount,
        gstAmount: i.gstAmount,
        totalAmount: i.totalAmount,
        expiry: i.expiryDate,
      })),
    };

    try {
      const itemCount = cart.length;
      const res = await api.post("/purchase", payload);
      if ([200, 201].includes(res.status)) {
        clearFormCompletely();
        setPurchaseMessage(`✅ Purchased successfully (${itemCount} item${itemCount > 1 ? "s" : ""}).`);
      } else {
        setPurchaseMessage("Purchase failed. Try again.");
      }
    } catch(err) {
             console.error(err);
      alert(err.response.data.message)
      
    }
  };

  const handleSupplierSelect = (s) => {
    if (isSupplierLocked) return;
    setSelectedSupplier(s);
    setSupplierQuery(`${s.supplierId} - ${s.name}`);
    setFilteredSuppliers([]);
    setFormErrors((p) => ({ ...p, supplier: undefined }));
    setPurchaseMessage("");
  };

  const handleProductSelect = (p) => {
    setSelectedProduct(p);
    setProductQuery(`${p.productId} - ${p.name}`);
    setFilteredProducts([]);
    setFormErrors((prev) => ({ ...prev, product: undefined }));
    setPurchaseMessage("");
    setIsProductSelected(true);
  };

  return (
    <div style={styles.container}>
      <div style={styles.topBar}>
  <div>
    <button onClick={() => navigate(-1)} style={styles.backButton}>
      ← Back
    </button>
  </div>
  <h2 style={styles.heading}>Purchase Form</h2>
  <div style={{ width: 80 }}></div> 
</div>

<div style={styles.gridContainer}>
      {/* <div style={styles.inputGroup}>
        <label style={styles.label}>Created By</label>
        <input
          type="text"
          value={createdBy}
          onChange={(e) => setCreatedBy(e.target.value)}
          style={styles.input}
          placeholder="Enter your name"
          readOnly
        />
      </div> */}


       
<div style={{ ...styles.inputGroup, position: "relative" }}>
  <label style={styles.label}>Supplier</label>
  <div style={styles.inputWithButton}>
    <input
      type="text"
      value={supplierQuery}
      disabled={isSupplierLocked}
      onChange={(e) => {
        setSupplierQuery(e.target.value);
        setSelectedSupplier(null);
        setSupplierChangeWarning(false);
      }}
      placeholder="Search by ID or name..."
      style={{ ...styles.input, paddingRight: isSupplierLocked ? 30 : 10 }}
      autoComplete="off"
    />
    {isSupplierLocked && (
      <button
        onClick={() => {
          setIsSupplierLocked(false);
          setSelectedSupplier(null);
          setSupplierQuery("");
          setPurchaseMessage("");
          setSupplierChangeWarning(true);
        }}
        style={styles.clearButton}
        title="Change Supplier"
      >
        ❌
      </button>
    )}
  </div>
  {formErrors.supplier && <div style={styles.error}>{formErrors.supplier}</div>}
  {filteredSuppliers.length > 0 && (
    <ul style={styles.dropdown}>
      {filteredSuppliers.map((s) => (
        <li
          key={s.supplierId}
          style={styles.dropdownItem}
          onClick={() => handleSupplierSelect(s)}
        >
          {s.supplierId} - {s.name}
        </li>
      ))}
    </ul>
  )}
</div>


<div style={{ ...styles.inputGroup, position: "relative" }}>
  <label style={styles.label}>Product</label>
  <input
    type="text"
    value={productQuery}
    onChange={(e) => {
      setProductQuery(e.target.value);
      setSelectedProduct(null);
      setIsProductSelected(false);
    }}
    placeholder="Search by ID or name..."
    style={styles.input}
    autoComplete="off"
  />
  {formErrors.product && <div style={styles.error}>{formErrors.product}</div>}
  {!isProductSelected && filteredProducts.length > 0 && (
    <ul style={styles.dropdown}>
      {filteredProducts.map((p) => (
        <li
          key={p.productId}
          style={styles.dropdownItem}
          onClick={() => handleProductSelect(p)}
        >
          {p.productId} - {p.name}
        </li>
      ))}
    </ul>
  )}
</div>


        <div style={styles.inputGroup}>
          <label style={styles.label}>Quantity</label>
          <input
            type="number"
            min="1"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            style={styles.input}
          />
          {formErrors.quantity && <div style={styles.error}>{formErrors.quantity}</div>}
        </div>

        <div style={styles.inputGroup}>
          <label style={styles.label}>Taxable Amount</label>
          <input
            type="number"
            min="0"
            value={taxableAmount}
            onChange={(e) => setTaxableAmount(e.target.value)}
            style={styles.input}
          />
          {formErrors.taxableAmount && <div style={styles.error}>{formErrors.taxableAmount}</div>}
        </div>

        <div style={styles.inputGroup}>
          <label style={styles.label}>GST Amount</label>
          <input
            type="number"
            min="0"
            value={gstAmount}
            onChange={(e) => setGstAmount(e.target.value)}
            style={styles.input}
          />
          {formErrors.gstAmount && <div style={styles.error}>{formErrors.gstAmount}</div>}
        </div>

        <div style={styles.inputGroup}>
          <label style={styles.label}>Total Amount</label>
          <input type="number" value={totalAmount.toFixed(2)} disabled style={styles.input} />
        </div>

        <div style={styles.inputGroup}>
          <label style={styles.label}>Expiry Date</label>
          <input
            type="date"
            value={expiryDate}
            min={new Date().toISOString().slice(0, 10)}
            onChange={(e) => setExpiryDate(e.target.value)}
            // customInput={<input readOnly />}
            style={styles.input}
          />
          {formErrors.expiryDate && <div style={styles.error}>{formErrors.expiryDate}</div>}
        </div>
      </div>

      <button onClick={addToCart} style={styles.addButton}>
        {editIndex !== null ? "Update Cart" : "Add to Cart"}
      </button>

      {supplierChangeWarning && (
        <div style={styles.warningBox}>
          <p style={{ marginBottom: 8 }}>
            ⚠️ Changing the supplier won't clear the cart. Make sure the cart items are valid for the new supplier or else clear the form.
          </p>
          <button onClick={clearFormCompletely} style={styles.clearFormButton}>
            Clear Form
          </button>
        </div>
      )}

      <div style={{ marginTop: 30 }}>
        <h3>Cart Items</h3>
        {cart.length === 0 && <p>Your cart is empty.</p>}
        {cart.length > 0 && (
          <table style={styles.table}>
            <thead>
              <tr>
                <th>Product ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Taxable</th>
                <th>GST</th>
                <th>Total</th>
                <th>Expiry</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {cart.map((item, idx) => (
                <tr key={idx}>
                  <td>{item.product.productId}</td>
                  <td>{item.product.name}</td>
                  <td>{item.quantity}</td>
                  <td>{item.taxableAmount}</td>
                  <td>{item.gstAmount}</td>
                  <td>{item.totalAmount.toFixed(2)}</td>
                  <td>{item.expiryDate}</td>
                  <td>
                    <button onClick={() => handleEdit(idx)} style={styles.tableButton}>
                      Edit
                    </button>
                    <button onClick={() => handleDelete(idx)} style={styles.tableButtonDelete}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {purchaseMessage && <p style={styles.purchaseMessage}>{purchaseMessage}</p>}

      <button onClick={handlePurchase} style={styles.purchaseButton}>
        Purchase
      </button>    </div>
  );
}

const styles = {
  container: {
    maxWidth: 900,
    margin: "auto",
    padding: 20,
    fontFamily: "Arial, sans-serif",
  },
   backButtonContainer: {
    display: "flex",
    justifyContent: "flex-start",
    marginBottom: "10px",
  },
  backButton: {
    backgroundColor: "#ff9800",
    color: "#fff",
    border: "none",
    padding: "10px 16px",
    borderRadius: "25px",
    fontWeight: "600",
    cursor: "pointer",
    boxShadow: "0 4px 10px rgba(255, 152, 0, 0.3)",
  },
  heading: {
    marginBottom: 20,
    textAlign: "center",
  },
  gridContainer: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(180px, 1fr))",
    gap: 15,
    marginBottom: 15,
  },
  inputGroup: {
    display: "flex",
    flexDirection: "column",
  },
  label: {
    marginBottom: 6,
    fontWeight: "bold",
  },
  input: {
    padding: 8,
    fontSize: 14,
    borderRadius: 4,
    border: "1px solid #ccc",
  },
  error: {
    color: "red",
    fontSize: 12,
    marginTop: 4,
  },
  inputWithButton: {
    position: "relative",
    display: "flex",
  },
  clearButton: {
    position: "absolute",
    right: 4,
    top: 4,
    bottom: 4,
    border: "none",
    backgroundColor: "transparent",
    fontSize: 18,
    cursor: "pointer",
    color: "#b30000",
    fontWeight: "bold",
    padding: "0 8px",
    userSelect: "none",
  },
  addButton: {
    padding: "10px 16px",
    fontSize: 16,
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    borderRadius: 5,
    cursor: "pointer",
    marginTop: 10,
  },
  topBar: {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  marginBottom: 20,
},

  warningBox: {
    marginTop: 20,
    padding: 16,
    border: "1px solid orange",
    backgroundColor: "#fff7e6",
    color: "#b36b00",
    borderRadius: 4,
    fontSize: 14,
  },
  clearFormButton: {
    marginTop: 8,
    padding: "8px 16px",
    backgroundColor: "#e60000",
    color: "white",
    border: "none",
    borderRadius: 4,
    cursor: "pointer",
    fontWeight: "bold",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
  },
  tableButton: {
    marginRight: 8,
    padding: "4px 8px",
    cursor: "pointer",
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    borderRadius: 3,
  },
  tableButtonDelete: {
    padding: "4px 8px",
    cursor: "pointer",
    backgroundColor: "#dc3545",
    color: "white",
    border: "none",
    borderRadius: 3,
  },
  purchaseMessage: {
    marginTop: 15,
    fontWeight: "bold",
    color: "green",
  },
  purchaseButton: {
    marginTop: 25,
    padding: "12px 20px",
    fontSize: 18,
    backgroundColor: "green",
    color: "white",
    border: "none",
    borderRadius: 5,
    cursor: "pointer",
  },
dropdown: {
  position: "absolute",
  top: "100%",
  left: 0,
  right: 0,
  zIndex: 100,
  backgroundColor: "white",
  border: "1px solid #ccc",
  maxHeight: 150,
  overflowY: "auto",
  listStyle: "none",
  margin: 0,
  padding: 0,
  boxShadow: "0 2px 5px rgba(0,0,0,0.15)",
  borderRadius: 4,
},
dropdownItem: {
  padding: "8px 12px",
  cursor: "pointer",
  borderBottom: "1px solid #eee",
},
dropdownItemHover: {
  backgroundColor: "#f0f0f0",
},

};

