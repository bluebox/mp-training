import { useState } from "react";
import axios from "axios";
import { Link } from 'react-router-dom';

function Payments(){
   
return (
    <div>
        <Link to="/view-payment">
        <button>View Payments</button>
         </Link>
        <br></br>
        <br></br>
         <Link to="/make-payment">
        <button>Make Payment</button>
         </Link>
    </div>
)
}
export default Payments