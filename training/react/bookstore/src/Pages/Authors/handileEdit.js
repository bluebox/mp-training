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
            const response=await fetch("http://127.0.0.1:8000/bookStore/books/"+String(book.id))
            const data=await response.json()
            setDescription(data.content)
            const id=data.id
    }
    useEffect( () =>{
        loadDescription()
    },[])


    const handleSave = async () =>{
      const response1=await fetch("http://127.0.0.1:8000/bookStore/books/"+String(id),{method:"PATCH",headers: {
        'Content-Type': 'application/json','Authorization':'Bearer ' + localStorage.getItem('access')
      },
      body: JSON.stringify({content:book_content})})

      if (response1.status === 401 ||
      response1.status === 403 ){
          alert("your session was expired")
      localStorage.removeItem('access')
      localStorage.removeItem('refresh')
      navigate('/')
      }
      navigate('/AuthorsPage',{state:username})
    }

    return (<>
    <input className="inputs-gap-handleEdit" type="text" value={book_content} name="content" placeholder="content" onChange={(e) => setDescription(e.target.value)}/> <br/>
    <button className='edit-button-handleEdit' onClick={handleSave} >save</button>
    
    </>)
}