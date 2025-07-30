import React, { useState } from "react";

function OrderItemssView(){
console.log("came into OrderItemssView")
const [OrderItemsList,setOrderItemsList]=useState([])

const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/Web_World/order_items/");
    if (!response.ok) {
      throw new Error("Failed to fetch");
    }

    const data = await response.json(); 
    setOrderItemsList(data)
    console.log("authors data:", data);
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};
const columns = ['id','quantity','order','book'];



 return (<>
<button className="submit-button1" onClick={handleClick}>get all Ordered Items details</button>



{OrderItemsList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '300px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { OrderItemsList.map((OI) => <tr key={OI.id}> <td >{OI.id}</td><td >{OI.quantity}</td><td >{OI.order}</td><td >{OI.book}</td>
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}export default OrderItemssView;