import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./authors.css"



export default function Handlebookedit(){
    const location=useLocation()
    const navigate=useNavigate()
    const {book,username}=location.state;
    const [book_content,setDescription]=useState("")
    const id=book.id
    const temp=book.content

    console.log("came to  handle edit book")
    


    const loadDescription = async  () => {
            const response=await fetch("http://127.0.0.1:8000/store/books/"+book.id)
            const data=await response.json()
            setDescription(data.content)
            const id=data.id
    }
    useEffect( () =>{
        loadDescription()
    },[])


    const handleSave = async () =>{
      const response_access=await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))
      const response_access_json=await response_access.json()
      console.log("response_access_json",response_access_json)
      if (response_access_json.access ==="Session expired !!! please login again"){
        navigate('/')
        return;
      }
      const response1=await fetch("http://127.0.0.1:8000/store/books/"+String(id),{method:"PATCH",headers: {
        'Content-Type': 'application/json','Authorization':'Bearer ' + response_access_json.access
      },
      body: JSON.stringify({content:book_content})})

      
      navigate('/AuthorsPage',{state:username})
    }

    return (<>
    <nav className="nav-link">
     <button className='nav-button' onClick={() => navigate('/AuthorsPage',{state:username})}>Home</button>
</nav>

    <input className="inputs-gap-handleEdit" type="text" value={book_content} name="content" placeholder="content" onChange={(e) => setDescription(e.target.value)}/> <br/>
    <button className='edit-button-handleEdit' onClick={handleSave} >save</button>
    
    </>)
}