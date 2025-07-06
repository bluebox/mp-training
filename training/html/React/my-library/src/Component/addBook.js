import { useNavigate } from "react-router-dom";

function AddBooks(){
  const navigate=useNavigate();
  const BooksData=(event)=>{
    event.preventDefault();
    const form=event.target;
    const formData=new FormData(form);
    const formobj=Object.fromEntries(formData.entries());
    fetch("http://localhost:8000/books/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(formobj),
  })
  .then((response) => {
    if (!response.ok) throw new Error("Failed to add book");
    return response.text();
  })
  .then((data) => {
    alert("Book added successfully!");
    navigate("/showBooks")
  })
  .catch((error) => {
    console.error("Error:", error);
  });
  }
  
  return (
    <form onSubmit={BooksData}>
      <label htmlFor='bookId'>Book ID : </label>
      <input type='number' id='bookId' name='bookId'/><br></br>
      <label htmlFor='name'>Title  : </label>
      <input type='text' id='name' name='name'/><br></br>
      <label htmlFor='author'>Author : </label>
      <input type='text' id='author' name='author'/><br></br>
      <label htmlFor='category'>Category : </label>
      <input type='text' id='category'name='category'/><br></br>
      <label htmlFor='bookStatus' hidden>Status : </label>
      <input type='text' defaultValue={"A"} name='bookStatus' id='bookStatus' hidden/>
      <label htmlFor='availability' hidden>Availability : </label>
      <input type='text' defaultValue={"A"} name='availability' id='availability' hidden/>
      <input type='submit'/>
    </form>
    
  );
}
export default AddBooks;