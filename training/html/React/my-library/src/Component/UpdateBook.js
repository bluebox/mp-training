import { useNavigate, useParams } from "react-router-dom";

function UpdateBooks() {
    const nav=useNavigate();
    const { bookId } = useParams();
    const BooksData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    formobj.bookId=Number(bookId);
    fetch(`http://localhost:8000/books/update`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formobj)
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to update book");
        return response.text();
      })
      .then((data) => {
        alert("Book updated successfully!");
        nav("/showBooks");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <form onSubmit={BooksData}>
      <input type="hidden" name="bookId" value={bookId}/>
      <label htmlFor='name'>Title:</label>
      <input type='text' id='name' name='name'/><br />

      <label htmlFor='author'>Author:</label>
      <input type='text' id='author' name='author'/><br />

      <label htmlFor='category'>Category:</label>
      <input type='text' id='category' name='category'/><br />

      <label htmlFor='bookStatus'>Status:</label>
      <input type='text' id='bookStatus' name='bookStatus'/><br />

      <label htmlFor='availability'>Availability:</label>
      <input type='text' id='availability' name='availability'/><br />

      <input type="submit" value="Update Book" />
    </form>
  );
}

export default UpdateBooks;
