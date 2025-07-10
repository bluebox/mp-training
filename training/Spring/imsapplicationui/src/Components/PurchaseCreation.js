import React , { useEffect,useState } from 'react';
import axios from 'axios';

const PurchaseCreation = () =>{
    const [ suppliers , setSuppliers ] = useState([]);
    const [ selectedSupplier , setSelectedSupplier ] = useState('');
    const [ products , setProducts ] = useState([]);
    const [ selectedProduct , setSelectedProduct ] = useState(null);
    const [ quantity , setQuantity ] = useState(1);
    const [ orderDetails,setOrderDetails ] = useState(null);


//Getiing All Suppliers
useEffect( () =>{
    axios.post('http://localhost:8080/creation/suppliers',{})
    .then(res => setSuppliers(res.data))
    .catch(err => console.log("some Error Occured in fetching suppliers : ",err))
},[])

useEffect( () =>{
    console.log("showing Suppliers")
    console.log(suppliers)
},[suppliers])

//getting products
const fetchProducts = (selectedSupplier) => {
    axios.post('http://localhost:8080/creation/suppliers-products',[selectedSupplier,""])
    .then(res => setProducts(res.data))
    .catch(err => console.log("some Error Occured in fetching products : ",err))
};

//update Quantity
useEffect(() => {
    if(selectedProduct && selectedSupplier){
        const cost = selectedProduct.price*quantity;
        setOrderDetails({
            supplier:selectedSupplier,
            product:selectedProduct,
            productQuantity : quantity,
            productCost:cost
        });
    }
},[selectedProduct,quantity,selectedSupplier]);

return(
    <>
    <label> Select Supplier  : </label>
    <select value={selectedSupplier} onChange={
        e => {
            const supplierName = e.target.value;
            setSelectedSupplier(supplierName);
            setSelectedProduct(null);
            setQuantity(1);
            setOrderDetails(null);
            fetchProducts(supplierName);
        }}>
            <option value="">--Select--</option>
            {suppliers != null && suppliers.map((s, index) => {
                const [name, id] = s.split(' - ');
                return (
                    <option key={id || index} value={s}>{s}</option>
                );
        })}
    </select>
    <label>    Select product  : </label>
    {
        (
            <>
            <select value={selectedProduct} onChange={ e => {
                const product = e.target.value;
                setSelectedProduct(product);
                setQuantity(1);
            }}>
                <option value="">--Select Product--</option>

                {products.map(p=>(
                    <option key={p} value={p}>{p}</option>
                ))}
            </select>
            </>

        )
    }
    </>
)
}

export default PurchaseCreation;