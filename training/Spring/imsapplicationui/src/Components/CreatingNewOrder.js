import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

function CreatingNewOrder() {
  const { stockId } = useParams();
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
  const [loading, setLoading] = useState(false);

  // Fetch initial data
  useEffect(() => {
    setLoading(true);

    axios.post('http://localhost:8080/user-stock/fetch-product-by-name', { stockId: parseInt(stockId) })
      .then(res => {
        if (res.data && res.data.productName && res.data.supplier) {
          setFormData({
            orders: { orderStatus: 'CREATED', orderDate: new Date() },
            orderProductDetails: [{
              orderDetailsId: 0,
              orderId: 0,
              supplier: res.data.supplier,
              product: res.data.productName,
              productQuantity: res.data.maxQuantity || 1,
              productCost: 0
            }]
          });
        } else {
          setError('Invalid product data');
        }
      })
      .catch(err => {
        console.error('Fetch product error:', err);
        setError('Failed to load product details');
      });

    axios.post('http://localhost:8080/creation/suppliers', {})
      .then(res => setSuppliers(Array.isArray(res.data) ? res.data : []))
      .catch(err => {
        console.error('Fetch suppliers error:', err);
        setError('Failed to load suppliers');
      });

    if (showStock) {
      axios.post('http://localhost:8080/user-stock/low-stock', {})
        .then(res => setLowStock(Array.isArray(res.data) ? res.data : []))
        .catch(err => {
          console.error('Fetch low-stock error:', err);
          setError('Failed to load low-stock items');
        });
    }

    const fromLowStock = localStorage.getItem('fromLowStock');
    if (fromLowStock) {
      const parsed = JSON.parse(fromLowStock);
      setFormData(prev => ({
        ...prev,
        orderProductDetails: parsed.map(item => ({
          orderDetailsId: 0,
          orderId: 0,
          supplier: item.supplier,
          product: item.productName || item.product,
          productQuantity: item.productQuantity,
          productCost: item.productCost
        }))
      }));
      localStorage.removeItem('fromLowStock');
    }

    setLoading(false);
  }, [stockId, showStock]);

  // Fetch supplier's product list
  useEffect(() => {
    if (currentItem.supplier) {
      axios.post('http://localhost:8080/creation/suppliers-products', [currentItem.supplier, ''])
        .then(res => setProducts(Array.isArray(res.data) ? res.data : []))
        .catch(err => {
          console.error('Fetch supplier products error:', err);
          setError('Failed to load products');
        });
    }
  }, [currentItem.supplier]);

  // Fetch unit cost when product changes
  useEffect(() => {
    if (currentItem.supplier && currentItem.product) {
      axios.post('http://localhost:8080/creation/suppliers-products-cost', {
        supplier: currentItem.supplier,
        product: currentItem.product
      }).then(res => {
        const cost = typeof res.data === 'number' ? res.data : parseFloat(res.data);
        setCurrentItem(prev => ({
          ...prev,
          unitCost: cost,
          totalCost: cost * prev.quantity
        }));
      }).catch(err => {
        console.error('Fetch product cost error:', err);
        setError('Failed to load product cost');
      });
    }
  }, [currentItem]);

  // Recalculate total cost if quantity changes
  useEffect(() => {
    setCurrentItem(prev => ({
      ...prev,
      totalCost: prev.unitCost * prev.quantity
    }));
  }, [currentItem.quantity]);

  const handleInput = (field, value) => {
    setCurrentItem(prev => ({
      ...prev,
      [field]: field === 'quantity' ? Math.max(1, Number(value)) : value
    }));
  };

  const handleAddOrUpdate = () => {
    const item = {
      orderDetailsId: 0,
      orderId: 0,
      supplier: currentItem.supplier,
      product: currentItem.product,
      productQuantity: currentItem.quantity,
      productCost: currentItem.totalCost
    };

    const duplicateIndex = formData.orderProductDetails.findIndex(entry =>
      entry.supplier === item.supplier && entry.product === item.product
    );

    if (duplicateIndex !== -1 && editIndex === null) {
      alert('This product is already added for this supplier.');
      return;
    }

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
          orderId: 0,
          supplier,
          product,
          productQuantity: quantity,
          productCost: total
        };
        setFormData(prev => ({
          ...prev,
          orderProductDetails: [...prev.orderProductDetails, item]
        }));
      })
      .catch(err => {
        console.error('Fetch cost error:', err);
        setError('Failed to fetch product cost');
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
    if (!formData.orderProductDetails.length) {
      setError('Please add at least one product before submitting the order.');
      return;
    }

    const subtotal = formData.orderProductDetails.reduce((sum, item) => sum + item.productCost, 0);
    const payload = {
      orders: {
        orderId: 0,
        orderDate: new Date().toISOString(),
        orderCost: subtotal * (1 - orderDiscount / 100),
        orderDiscount: orderDiscount,
        orderStatus: 'CREATED'
      },
      orderProductDetails: formData.orderProductDetails
    };

    axios.post('http://localhost:8080/creation/purchase-created', payload)
      .then((res) => {
        alert(res.data.message || 'Order created successfully!');
        navigate('/all-orders');
      })
      .catch(err => {
        console.error('Order creation error:', err);
        if (err.response) {
          setError(`Order creation failed: ${err.response.data.message || 'Unknown error'}`);
        } else {
          setError('Order creation failed: Network error');
        }
      });
  };

  const subtotal = formData.orderProductDetails.reduce((sum, item) => sum + item.productCost, 0);
  const totalAfter = subtotal * (1 - orderDiscount / 100);
  const showLowStock = showStock && lowStock.length > 0;

  return (
    <div className='container mt-5'>
      <h2>Create Purchase Order</h2>
      {error && <div className='alert alert-danger'>{error}</div>}
      {loading && <div>Loading...</div>}

      <div className='card p-4'>
        <h4>Add Product</h4>
        <div className='row mb-3'>
          <div className='col'>
            <label>Supplier</label>
            <select className='form-control' value={currentItem.supplier} onChange={e => handleInput('supplier', e.target.value)}>
              <option value=''>Select Supplier</option>
              {suppliers.map(sup => <option key={sup} value={sup}>{sup}</option>)}
            </select>
          </div>
          <div className='col'>
            <label>Product</label>
            <select className='form-control' value={currentItem.product} onChange={e => handleInput('product', e.target.value)}>
              <option value=''>Select Product</option>
              {products.map(prod => <option key={prod} value={prod}>{prod}</option>)}
            </select>
          </div>
          <div className='col'>
            <label>Quantity</label>
            <input type='number' className='form-control' value={currentItem.quantity} onChange={e => handleInput('quantity', e.target.value)} />
          </div>
          <div className='col'>
            <label>Unit Cost</label>
            <input type='text' className='form-control' value={currentItem.unitCost} disabled />
          </div>
          <div className='col'>
            <label>Total</label>
            <input type='text' className='form-control' value={currentItem.totalCost.toFixed(2)} disabled />
          </div>
        </div>
        <button className='btn btn-primary' onClick={handleAddOrUpdate}>
          {editIndex !== null ? 'Update Product' : 'Add Product'}
        </button>
      </div>

      <div className='card p-4 mt-4'>
        <h4>Order Summary</h4>
        <table className='table table-bordered'>
          <thead>
            <tr>
              <th>Supplier</th>
              <th>Product</th>
              <th>Qty</th>
              <th>Cost</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {formData.orderProductDetails.map((item, index) => (
              <tr key={index}>
                <td>{item.supplier}</td>
                <td>{item.product}</td>
                <td>{item.productQuantity}</td>
                <td>{item.productCost.toFixed(2)}</td>
                <td>
                  <button className='btn btn-sm btn-info' onClick={() => handleEdit(index)}>Edit</button>
                  <button className='btn btn-sm btn-danger ml-2' onClick={() => handleDelete(index)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <div className='mt-3'>
          <label>Discount (%)</label>
          <input type='number' className='form-control' value={orderDiscount} onChange={e => setOrderDiscount(parseFloat(e.target.value) || 0)} />
        </div>

        <div className='mt-3'>
          <h5>Subtotal: ₹{subtotal.toFixed(2)}</h5>
          <h5>Total after Discount: ₹{totalAfter.toFixed(2)}</h5>
        </div>

        <button className='btn btn-success mt-3' onClick={handleSubmit}>Submit Purchase Order</button>
      </div>

      {showLowStock && (
        <div className='card mt-4 p-4'>
          <h4>Low Stock Items</h4>
          {lowStock.map((item, idx) => (
            <div key={idx} className='mb-2'>
              <strong>{item.productName}</strong> (max {item.maxQuantity}) - {item.supplier}
              <div className='d-flex align-items-center mt-2'>
                <input
                  type='number'
                  className='form-control mr-2'
                  placeholder='Qty'
                  value={lowQty[item.stockId] || ''}
                  onChange={e => setLowQty(prev => ({ ...prev, [item.stockId]: Number(e.target.value) }))}
                />
                <button className='btn btn-sm btn-primary mr-2' onClick={() => handlePartialLowStock(item)}>Add Partial</button>
                <button className='btn btn-sm btn-secondary' onClick={() => handleFullLowStock(item)}>Add Full</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default CreatingNewOrder;
