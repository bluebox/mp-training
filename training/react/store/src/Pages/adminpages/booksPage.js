import React, {useEffect, useState}from "react";
import { useNavigate } from "react-router-dom";




export default function BooksPage(){

    const [booksList,setbooksList]=useState([])
    const navigate=useNavigate();
    const [get_book,setGet]=useState(false)


    const handleAddBook =() =>{
    navigate('/BookAddingForm',{state:{from:'admin'}})
}



const handleGetBooks =async (e) =>{
    setGet(true)
      try {
        const response = await fetch("http://127.0.0.1:8000/store/books/");
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
    
        const data = await response.json();
        setbooksList(data)
        
    
      } catch (error) {
        console.error("Fetch error:", error);
      }
     
    };

    const columns = ['id','title','author','content','price'];

    const handleEdit = (book) =>{
        navigate('/BookAddingForm', {state:{'book':book,from:'admin'}});
    }


useEffect(( )=>{
handleGetBooks()
},[])


const handleSeaerch = (e)  => {
  e.preventDefault();
  try {
    if (e.target.value === "") {
      handleGetBooks();
      return;
    }
    const a=booksList.filter((book) => book.title.toLowerCase().includes(e.target.value.toLowerCase()));
    setbooksList(a);
    
  } catch (error) {}
}


return (
    <>
    <nav className="nav-link">
    <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
    <button className='nav-button'  onClick={handleAddBook} >add book</button>
    </nav>
    <input className='filter-box' type="text" placeholder="Search by title" onChange={handleSeaerch}/>
    {get_book? 
    (booksList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="10" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '100px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { booksList.map(( book) => <tr key={book}> <td key={book.id}>{book.id}</td><td key={book.title}>{book.title}</td><td key={book.author}>{book.author__username}</td><td key={book.content}>{book.content}</td><td key={book.price}>{book.price}</td>
       {<td><button className='edit-button' onClick={() => handleEdit(book)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>)
  ) : <></>
    }</>

)
}