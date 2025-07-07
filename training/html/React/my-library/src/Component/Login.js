import { useNavigate } from "react-router-dom";

function Login(){
    const nav=useNavigate();
    const updateLogin=((event)=>{
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formobj=Object.fromEntries(formData.entries());
        fetch("http://localhost:8000/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(formobj)
        })
        .then((res)=>{
            if(!res.ok) throw new Error("Data can't be fetched");
            return res.text();
        })
        .then(()=>{
            alert("Login is successful");
            localStorage.setItem("login",formobj);
            nav("/");
        })
        .catch((error)=>{
            alert("Error occured");
        })
    })
    return(
        <form onSubmit={updateLogin}>
            
            <label htmlFor="username">User Name : </label>
            <input type="text" id="username" name="username"/>
            <label htmlFor="password">Password : </label>
            <input type="text" id="password" name="password"/>
            <input type="submit"/>
        </form>
    )
}

export default Login;