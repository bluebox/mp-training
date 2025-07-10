import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PurchaseCreation = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [selectedSupplier, setSelectedSupplier] = useState('');
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState('');
  const [quantity, setQuantity] = useState(0);
  const [productCost, setProductCost] = useState(0);
  const [orderDetails, setOrderDetails] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Fetch all suppliers
  useEffect(() => {
    setLoading(true);
    axios
      .post('http://localhost:8080/creation/suppliers', {})
      .then((res) => {
        setSuppliers(Array.isArray(res.data) ? res.data : []);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error fetching suppliers:', err);
        setError('Failed to fetch suppliers');
        setLoading(false);
      });
  }, []);

  // Fetch products for selected supplier
  const fetchProducts = (selectedSupplier) => {
    setLoading(true);
    axios
      .post('http://localhost:8080/creation/suppliers-products', [
        selectedSupplier,
        '',
      ])
      .then((res) => {
        setProducts(Array.isArray(res.data) ? res.data : []);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error fetching products:', err);
        setError('Failed to fetch products');
        setLoading(false);
      });
  };

  // Fetch product cost
  const productCostFetching = async (supplier, product) => {
    try {
      const response = await fetch(
        'http://localhost:8080/creation/suppliers-products-cost',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ supplier, product }),
        }
      );

      if (!response.ok) {
        throw new Error(`Server error: ${response.status}`);
      }

      const data = await response.json();
      console.log("data", data);
      setProductCost(data);
      return data;
    } catch (err) {
      console.error('Error fetching product cost:', err);
      setError('Failed to fetch product cost');
      return 0;
    }
  };

  // Update order details
  useEffect(() => {
    const fetchCostAndSetOrder = async () => {
      if (selectedProduct && selectedSupplier) {
        setLoading(true);
        const infoString = `Selected: ${selectedSupplier} - ${selectedProduct}`;
        console.log('Info String:', infoString);

        const cost = await productCostFetching(selectedSupplier, selectedProduct);
        const totalCost = cost * quantity;

        setOrderDetails({
          supplier: selectedSupplier,
          product: selectedProduct,
          productQuantity: quantity,
          productCost: totalCost,
        });
        setLoading(false);
      }
    };

    fetchCostAndSetOrder();
  }, [selectedProduct, quantity, selectedSupplier]);

  const filteredProducts = products.filter((product) =>
    product.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <>
      {loading && <p>Loading...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <label>Select Supplier: </label>
      <select
        value={selectedSupplier}
        onChange={(e) => {
          const supplierName = e.target.value;
          setSelectedSupplier(supplierName);
          setSelectedProduct('');
          setQuantity(0);
          setOrderDetails(null);
          setSearchTerm('');
          setError(null);
          if (supplierName) fetchProducts(supplierName);
        }}
      >
        <option value="">--Select--</option>
        {Array.isArray(suppliers) &&
          suppliers.map((s, index) => {
            const [name, id] = s.split(' - ');
            return (
              <option key={id || index} value={s}>
                {s}
              </option>
            );
          })}
      </select>

      <label>Search and Select Product: </label>
      <input
        type="text"
        placeholder="Search product..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      <select
        value={selectedProduct}
        onChange={(e) => {
          const product = e.target.value;
          setSelectedProduct(product);
          setQuantity(1);
          setError(null);
        }}
      >
        <option value="">--Select Product--</option>
        {filteredProducts.map((product, index) => (
          <option key={index} value={product}>
            {product}
          </option>
        ))}
      </select>

      <label>Quantity: </label>
      <input
        type="number"
        value={quantity}
        onChange={(e) => {
          const value = Number(e.target.value);
          setQuantity(value >= 0 ? value : 0);
        }}
      />

      <label>Total Cost: </label>
      <input
        type="number"
        value={orderDetails?.productCost || 0}
        readOnly
      />
    </>
  );
};

export default PurchaseCreation;