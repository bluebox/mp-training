import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function LogoutPage() {
    const nav=useNavigate();
  useEffect(() => {
    fetch("http://localhost:8000/login?logout", {
        method: "POST",
        credentials: "include"
    })
    .then((res)=>{
        if(!res.ok){
            throw new Error("Failed to fetch the data");
        }
        return res.json();
    })
    .then(()=>{
        localStorage.clear();
        console.log("logout is done");
        nav("/");
    })
    .catch((err)=>{
        console.log("Error occured"+err.getMessage);
    })
  },[nav]);
  return (
    <div>
        <h1>Log Out is done</h1>
    </div>
  );
}