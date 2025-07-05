// import ReactDOM from 'react-dom/client';
// import { useState } from 'react';
function AddBooks(){
  // const [Map,setMap]=useState(new Map());
  // const [Books,setBooks]=useState({
  //   bookId:0,
  //   title:"",
  //   author:"",
  //   category:"",
  //   bookStatus:"",
  //   availability:""
  // });
  // const addData=(key,value)=>{
  //   setMap(prev=> new Map(prev).set(key,value));
  // };
  const BooksData=(event)=>{
    event.preventDefault();
    const form=event.target;
    const formData=new FormData(form);
    const formobj=Object.fromEntries(formData.entries());
    console.log(formobj);
    alert("Book ID : "+formobj.bookId+"\nBook Name : "+formobj.title+"\nAuthor : "+formobj.author+"\nCategory : "+formobj.category+"\nStatus :"+formobj.bookStatus);
    localStorage.setItem(formobj.bookId,JSON.stringify(formobj));
    const val=JSON.parse(localStorage.getItem(formobj.bookId));
    alert(val.title);
  }
  return (
    <form onSubmit={BooksData}>
      <label htmlFor='bookId'>Book ID : </label>
      <input type='number' id='bookId' name='bookId'/>
      <label htmlFor='title'>Title  : </label>
      <input type='text' id='title' name='title'/>
      <label htmlFor='author'>Author : </label>
      <input type='text' id='author' name='author'/>
      <label htmlFor='category'>Category : </label>
      <input type='text' id='category'name='category'/>
      <label htmlFor='bookStatus' hidden>Status : </label>
      <input type='text' defaultValue={"A"} name='bookStatus' id='bookStatus' hidden/>
      <label htmlFor='availability' hidden>Availability : </label>
      <input type='text' defaultValue={"A"} name='availability' id='availability' hidden/>
      <input type='submit'/>
    </form>
  );
}
export default AddBooks;