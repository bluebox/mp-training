import React, { useState } from "react";

function BooksView(){
console.log("came into booksView")
const [booksList,setbooksList]=useState([])

const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/Web_World/books/");
    if (!response.ok) {
      throw new Error("Failed to fetch");
    }

    const data = await response.json();
    setbooksList(data)
    console.log("books data:", booksList);
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};
const columns = ['id','title','price'];



 return (<>
<button className="submit-button1" onClick={handleClick}>get all books</button>



{booksList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '300px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { booksList.map(( book) => <tr key={book}> <td key={book.id}>{book.id}</td><td key={book.title}>{book.title}</td><td key={book.price}>{book.price}</td>
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}export default BooksView;