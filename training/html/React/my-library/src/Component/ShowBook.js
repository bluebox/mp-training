import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Book(props){
    return (
    <tr>
        <td>{props.sample.bookId}</td>
        <td>{props.sample.name}</td>
        <td>{props.sample.author}</td>
        <td>{props.sample.category}</td>
        <td>{props.sample.status}</td>
        <td>{props.sample.availability}</td>
        <td><Link to={`/updateBooks/${props.sample.bookId}`}><button>Update</button></Link></td>
        <td><Link  to={`/updateAvailability/${props.sample.bookId}`}><button>Change Status</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.bookId)}>Delete</button></td>
    </tr>
    );
}
function ShowBooks(){
    const[books,setBooks]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/books/show", {
            method: "GET"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setBooks(data);
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    },[]);
    // for (let i = 0; i < localStorage.length; i++) {
    //     const key = localStorage.key(i);
    //     try {
    //         const item = JSON.parse(localStorage.getItem(key));
    //         if (item && item.bookId !== undefined) {
    //             books.push(item);
    //         }
    //     } catch (e) {
    //         console.warn(`Invalid JSON at key "${key}":`, e);
    //     }
    // }
    function DeleteBooks(bookId){
        fetch(`http://localhost:8000/books/delete?bookId=${bookId}`,{
        method:"DELETE",
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg);
        window.location.reload();
        });
    }
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Availability</th>
                    </tr>
                </thead>
                    <tbody>
                        {books.map((x)=>(<Book sample={x} onDelete={DeleteBooks}/>))}
                    </tbody>
            </table>
            <Link to="/addBooks"><button>Add Book</button></Link>
        </div>
    );
}
export default ShowBooks;
