import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

function EditOrderModel() {
  const { orderId } = useParams();
  const { data } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const showStock = new URLSearchParams(location.search).get('showStock') !== 'false';

  const [formData, setFormData] = useState({ orders: {}, orderProductDetails: [] });
  const [suppliers, setSuppliers] = useState([]);
  const [products, setProducts] = useState([]);
  const [currentItem, setCurrentItem] = useState({ supplier: '', product: '', quantity: 1, unitCost: 0, totalCost: 0 });
  const [editIndex, setEditIndex] = useState(null);
  const [orderDiscount, setOrderDiscount] = useState(0);
  const [lowStock, setLowStock] = useState([]);
  const [lowQty, setLowQty] = useState({});
  const [error, setError] = useState(null);

  useEffect(() => {
    if(orderId!=0){
      axios.post('http://localhost:8080/orders/view-order', { orderId: parseInt(orderId) })
      .then(res => {
        if (res.data.orders && Array.isArray(res.data.orderProductDetails)) {
          setFormData(res.data);
          setOrderDiscount(res.data.orders.orderDiscount || 0);
        }
      })
      .catch(() => setError('Failed to load order'));
    }else{
      setFormData(data);
      setOrderDiscount(0);
    }
    

    axios.post('http://localhost:8080/creation/suppliers', {})
      .then(res => setSuppliers(Array.isArray(res.data) ? res.data : []));

    if (showStock) {
      axios.post('http://localhost:8080/low-stock', {})
        .then(res => setLowStock(Array.isArray(res.data) ? res.data : []));
    }
  }, [orderId, showStock]);

  useEffect(() => {
    if (currentItem.supplier) {
      axios.post('http://localhost:8080/creation/suppliers-products', [currentItem.supplier, ''])
        .then(res => setProducts(Array.isArray(res.data) ? res.data : []));
    }
  }, [currentItem.supplier]);

  useEffect(() => {
    if (currentItem.supplier && currentItem.product) {
      axios.post('http://localhost:8080/creation/suppliers-products-cost', {
        supplier: currentItem.supplier,
        product: currentItem.product
      }).then(res => {
        const cost = typeof res.data === 'number' ? res.data : parseFloat(res.data);
        const total = cost * currentItem.quantity;
        setCurrentItem(prev => ({ ...prev, unitCost: cost, totalCost: total }));
      });
    }
  }, [currentItem.product, currentItem.quantity]);

  const handleInput = (field, value) => {
    setCurrentItem(prev => ({ ...prev, [field]: field === 'quantity' ? Number(value) : value }));
  };

  const handleAddOrUpdate = () => {
    const item = {
      orderDetailsId: 0,
      orderId: parseInt(orderId),
      supplier: currentItem.supplier,
      product: currentItem.product,
      productQuantity: currentItem.quantity,
      productCost: currentItem.totalCost
    };
    const updated = [...formData.orderProductDetails];
    if (editIndex !== null) updated[editIndex] = item;
    else updated.push(item);

    setFormData({ ...formData, orderProductDetails: updated });
    setCurrentItem({ supplier: '', product: '', quantity: 1, unitCost: 0, totalCost: 0 });
    setEditIndex(null);
    setProducts([]);
  };

  const handleEdit = index => {
    const item = formData.orderProductDetails[index];
    setCurrentItem({
      supplier: item.supplier,
      product: item.product,
      quantity: item.productQuantity,
      unitCost: item.productCost / item.productQuantity,
      totalCost: item.productCost
    });
    setEditIndex(index);
  };

  const handleDelete = index => {
    const updated = formData.orderProductDetails.filter((_, i) => i !== index);
    setFormData({ ...formData, orderProductDetails: updated });
    if (editIndex === index) setEditIndex(null);
  };

  const fetchCostAndAdd = (supplier, product, quantity) => {
    axios.post('http://localhost:8080/creation/suppliers-products-cost', { supplier, product })
      .then(res => {
        const cost = typeof res.data === 'number' ? res.data : parseFloat(res.data);
        const total = cost * quantity;
        const item = {
          orderDetailsId: 0,
          orderId: parseInt(orderId),
          supplier,
          product,
          productQuantity: quantity,
          productCost: total
        };
        setFormData(prev => ({
          ...prev,
          orderProductDetails: [...prev.orderProductDetails, item]
        }));
      });
  };

  const handlePartialLowStock = (item) => {
    const qty = lowQty[item.stockId];
    if (!qty || qty <= 0 || qty > item.maxQuantity) {
      alert(`Enter quantity (1 to ${item.maxQuantity})`);
      return;
    }
    fetchCostAndAdd(item.supplier, item.productName, qty);
  };

  const handleFullLowStock = (item) => {
    fetchCostAndAdd(item.supplier, item.productName, item.maxQuantity);
  };

  const handleSubmit = () => {
    const subtotal = formData.orderProductDetails.reduce((sum, item) => sum + item.productCost, 0);
    const payload = {
      orders: {
        ...formData.orders,
        orderId: parseInt(orderId),
        orderDiscount: orderDiscount,
        orderCost: subtotal * (1 - orderDiscount / 100),
        orderStatus: 'UPDATED',
      },
      orderProductDetails: formData.orderProductDetails
    };

    axios.post('http://localhost:8080/orders/edit-purchase', payload)
      .then(() => {
        alert('Order updated!');
        navigate('/all-orders');
      })
      .catch(() => alert('Update failed'));
  };

  const subtotal = formData.orderProductDetails.reduce((sum, item) => sum + item.productCost, 0);
  const totalAfter = subtotal * (1 - orderDiscount / 100);
  const showLowStockSection = showStock && lowStock.length > 0;

  return (
    <div style={{ padding: '20px' }}>
      <h2>Edit Order #{orderId}</h2>

      <h3>Add Item Manually</h3>
      <label>Supplier:</label>
      <select value={currentItem.supplier} onChange={e => handleInput('supplier', e.target.value)}>
        <option value="">--Select--</option>
        {suppliers.map((s, i) => <option key={i} value={s}>{s}</option>)}
      </select>

      <br />
      <label>Product:</label>
      <select value={currentItem.product} onChange={e => handleInput('product', e.target.value)}>
        <option value="">--Select--</option>
        {products.map((p, i) => <option key={i} value={p}>{p}</option>)}
      </select>

      <br />
      <label>Quantity:</label>
      <input
        type="number"
        value={currentItem.quantity}
        onChange={e => handleInput('quantity', e.target.value)}
      />

      <br />
      <label>Total Cost:</label>
      <input type="number" readOnly value={currentItem.totalCost.toFixed(2)} />

      <br />
      <button onClick={handleAddOrUpdate} disabled={!currentItem.product || !currentItem.supplier}>
        {editIndex !== null ? 'Update Item' : 'Add Item'}
      </button>

      {showLowStockSection && (
        <>
          <h3>Low‑Stock Products</h3>
          <table border={1} cellPadding={6}>
            <thead>
              <tr>
                <th>Product</th>
                <th>Supplier</th>
                <th>Qty</th>
                <th>Min</th>
                <th>Max</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {lowStock.map(item => (
                <tr key={item.stockId}>
                  <td>{item.productName}</td>
                  <td>{item.supplier}</td>
                  <td>{item.quantity}</td>
                  <td>{item.minQuantity}</td>
                  <td>{item.maxQuantity}</td>
                  <td>
                    <input
                      type="number"
                      min={1}
                      max={item.maxQuantity}
                      value={lowQty[item.stockId] || ''}
                      placeholder="Qty"
                      onChange={e =>
                        setLowQty(prev => ({
                          ...prev,
                          [item.stockId]: Number(e.target.value)
                        }))
                      }
                    />
                    <button onClick={() => handlePartialLowStock(item)}>Partial</button>
                    <button onClick={() => handleFullLowStock(item)}>Full</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}

      <h3>Order Items</h3>
      <table border={1} cellPadding={8}>
        <thead>
          <tr>
            <th>Product</th>
            <th>Supplier</th>
            <th>Qty</th>
            <th>Cost</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {formData.orderProductDetails.map((item, i) => (
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
            <td colSpan={3} align="right">Subtotal:</td>
            <td colSpan={2}>{subtotal.toFixed(2)}</td>
          </tr>
          <tr>
            <td colSpan={3} align="right">Discount (%):</td>
            <td colSpan={2}>
              <input
                type="number"
                value={orderDiscount}
                onChange={e => setOrderDiscount(Number(e.target.value))}
              />
            </td>
          </tr>
          <tr>
            <td colSpan={3} align="right">Total After Discount:</td>
            <td colSpan={2}><strong>{totalAfter.toFixed(2)}</strong></td>
          </tr>
        </tfoot>
      </table>

      <br />
      <button onClick={handleSubmit}>Submit Edits</button>
    </div>
  );
}

export default EditOrderModel;
