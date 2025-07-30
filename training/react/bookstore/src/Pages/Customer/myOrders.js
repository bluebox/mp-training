import React, { useEffect ,useState} from "react";
import { useLocation, useNavigate } from "react-router-dom";


function MyOrders(){

    const [ordersList ,setbooksList]=useState( [])
    const location = useLocation();
    const navigate=useNavigate()
    const username = location.state;
    // const username=temp['username']
    
    useEffect ( ()=>{
      console.log(username)
        const func =async () => {
         try {
          console.log("username from Myorders",username)
        const response = await fetch("http://127.0.0.1:8000/bookStore/orders/?username="+username)
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
    
        const data = await response.json();
        setbooksList(data)
        console.log("books data:", data);
        
    
      } catch (error) {
        console.error("Fetch error:", error);
      }}
      func()  
    },[]
    
    )
const handleLogOut = (e) => {
    localStorage.removeItem('access');
  localStorage.removeItem('refresh');
  localStorage.removeItem('role')
  navigate("/")
}

    return (<>

          <nav className="nav-link">
     <button className='nav-button' onClick={() => navigate('/CustomerPage',{state:username})}>Home</button>
       <button className='nav-button'  onClick={handleLogOut}>LogOut</button>
</nav>
    <table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '100px' ,position:'center'}}>
        <thead>
            <tr>
                <th>Order Id</th><th>Order date</th><th>book Id</th><th>Quantity</th>
               
            </tr>
            </thead>
            <tbody>
           { Array.isArray(ordersList) && ordersList.map((order) => (
             <tr key={order.id}>
            <td>{order.id}</td><td>{order.order_date}</td><td>{order.book}</td><td>{order.quantity}</td>
           </tr>))}
        
        </tbody>

    </table>


</>
    )
}export default MyOrders;