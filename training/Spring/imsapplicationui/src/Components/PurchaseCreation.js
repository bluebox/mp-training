import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PurchaseCreation = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [products, setProducts] = useState([]);
  const [orderItems, setOrderItems] = useState([]);
  const [orderDiscount, setOrderDiscount] = useState(0);
  const [editIndex, setEditIndex] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState(null);
  const [currentItemSupplier, setCurrentItemSupplier] = useState('');
  const [selectedProduct, setSelectedProduct] = useState('');
  const [quantity, setQuantity] = useState(1);
  const [unitCost, setUnitCost] = useState(0);
  const [productCost, setProductCost] = useState(0);

  useEffect(() => {
    axios.post('http://localhost:8080/creation/suppliers', {})
      .then(res => setSuppliers(Array.isArray(res.data) ? res.data : []))
      .catch(err => {
        console.error('Error fetching suppliers:', err);
        setError('Failed to fetch suppliers');
      });
  }, []);

  useEffect(() => {
    const preload = localStorage.getItem('fromLowStock');
    if (preload) {
      const parsed = JSON.parse(preload);
      parsed.forEach(item => {
        axios.post('http://localhost:8080/creation/suppliers-products-cost', {
          supplier: item.supplier,
          product: item.product
        }).then(res => {
          const cost = res.data;
          const newItem = {
            ...item,
            productCost: item.productQuantity * cost
          };
          setOrderItems(prev => [...prev, newItem]);
        });
      });
      localStorage.removeItem('fromLowStock');
    }
  }, []);

  const fetchProducts = (supplier) => {
    axios.post('http://localhost:8080/creation/suppliers-products', [supplier, ''])
      .then(res => setProducts(Array.isArray(res.data) ? res.data : []))
      .catch(err => {
        console.error('Error fetching products:', err);
        setError('Failed to fetch products');
      });
  };

  const fetchProductCost = async (supplier, product) => {
    try {
      const res = await axios.post('http://localhost:8080/creation/suppliers-products-cost', {
        supplier, product
      });
      return res.data;
    } catch (err) {
      console.error('Error fetching cost:', err);
      setError('Cost fetch failed');
      return 0;
    }
  };

  useEffect(() => {
    const updateCost = async () => {
      if (currentItemSupplier && selectedProduct && quantity > 0) {
        const cost = await fetchProductCost(currentItemSupplier, selectedProduct);
        setUnitCost(cost);
        setProductCost(cost * quantity);
      }
    };
    updateCost();
  }, [currentItemSupplier, selectedProduct, quantity]);

  const resetInputs = () => {
    setCurrentItemSupplier('');
    setSelectedProduct('');
    setQuantity(1);
    setUnitCost(0);
    setProductCost(0);
    setSearchTerm('');
    setProducts([]);
    setEditIndex(null);
  };

  const handleAddOrUpdateItem = () => {
    const item = {
      orderDetailsId: 0,
      orderId: 0,
      supplier: currentItemSupplier,
      product: selectedProduct,
      productQuantity: quantity,
      productCost: productCost
    };

    if (editIndex !== null) {
      const updatedItems = [...orderItems];
      updatedItems[editIndex] = item;
      setOrderItems(updatedItems);
    } else {
      setOrderItems([...orderItems, item]);
    }

    resetInputs();
  };

  const handleEdit = (index) => {
    const item = orderItems[index];
    setCurrentItemSupplier(item.supplier);
    setSelectedProduct(item.product);
    setQuantity(item.productQuantity);
    setEditIndex(index);
    fetchProducts(item.supplier);
  };

  const handleDelete = (index) => {
    const updatedItems = orderItems.filter((_, i) => i !== index);
    setOrderItems(updatedItems);
    if (editIndex === index) resetInputs();
  };

  const subtotal = orderItems.reduce((acc, item) => acc + item.productCost, 0);
  const totalAfterDiscount = subtotal * (1 - orderDiscount / 100);

  const handleSubmitOrder = () => {
    if (!orderItems.length) {
      setError('Add at least one product to submit');
      return;
    }

    const fullOrder = {
      orders: {
        orderId: 0,
        orderDate: new Date(),
        orderCost: totalAfterDiscount,
        orderDiscount: orderDiscount,
        orderStatus: 'CREATED'
      },
      orderProductDetails: orderItems
    };

    axios.post('http://localhost:8080/creation/purchase-created', fullOrder)
      .then(res => {
        setMessage(res.data.message || 'Order Created Successfully!');
        setOrderItems([]);
        setOrderDiscount(0);
        resetInputs();
      })
      .catch(err => {
        console.error('Error submitting order:', err);
        setError('Order creation failed');
      });
  };

  const filteredProducts = products.filter(product =>
    product.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div style={{ padding: '20px' }}>
      <h2>Purchase Creation</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {message && <p style={{ color: 'green' }}>{message}</p>}

      <label>Supplier:</label>
      <select
        value={currentItemSupplier}
        onChange={(e) => {
          const supplier = e.target.value;
          setCurrentItemSupplier(supplier);
          fetchProducts(supplier);
          setSelectedProduct('');
        }}
      >
        <option value="">--Select Supplier--</option>
        {suppliers.map((s, i) => (
          <option key={i} value={s}>{s}</option>
        ))}
      </select>

      <label>Search Product:</label>
      <input
        type="text"
        placeholder="Search..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <select
        value={selectedProduct}
        onChange={(e) => setSelectedProduct(e.target.value)}
      >
        <option value="">--Select Product--</option>
        {filteredProducts.map((p, i) => (
          <option key={i} value={p}>{p}</option>
        ))}
      </select>

      <label>Quantity:</label>
      <input
        type="number"
        min="1"
        value={quantity}
        onChange={(e) => setQuantity(Number(e.target.value))}
      />

      <label>Unit Cost:</label>
      <input type="number" value={unitCost.toFixed(2)} readOnly />

      <label>Total Cost:</label>
      <input type="number" value={productCost.toFixed(2)} readOnly />

      <button
        disabled={!currentItemSupplier || !selectedProduct || !quantity}
        onClick={handleAddOrUpdateItem}
      >
        {editIndex !== null ? 'Update Item' : 'Add Item'}
      </button>

      <h3>Order Items</h3>
      {orderItems.length > 0 && (
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>Product</th>
              <th>Supplier</th>
              <th>Quantity</th>
              <th>Cost</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {orderItems.map((item, i) => (
              <tr key={i}>
                <td>{item.product}</td>
                <td>{item.supplier}</td>
                <td>{item.productQuantity}</td>
                <td>{item.productCost.toFixed(2)}</td>
                <td>
                  <button onClick={() => handleEdit(i)}>Edit</button>
                  <button onClick={() => handleDelete(i)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
          <tfoot>
            <tr>
              <td colSpan="3" align="right">Subtotal:</td>
              <td colSpan="2">{subtotal.toFixed(2)}</td>
            </tr>
            <tr>
              <td colSpan="3" align="right">Order Discount (%):</td>
              <td colSpan="2">
                <input
                  type="number"
                  min="0"
                  value={orderDiscount}
                  onChange={(e) => setOrderDiscount(Number(e.target.value))}
                />
              </td>
            </tr>
            <tr>
              <td colSpan="3" align="right">Total After Discount:</td>
              <td colSpan="2"><strong>{totalAfterDiscount.toFixed(2)}</strong></td>
            </tr>
          </tfoot>
        </table>
      )}

      <br />
      <button onClick={handleSubmitOrder} disabled={!orderItems.length}>
        Submit Order
      </button>
    </div>
  );
};

export default PurchaseCreation;
