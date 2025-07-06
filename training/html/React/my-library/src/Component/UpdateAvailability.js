import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateAvailability(){
    const nav=useNavigate();
    const {bookId}=useParams();
    useEffect(()=>{
        fetch(`http://localhost:8000/books/updateAvailability?bookId=${bookId}`,{
            method:"PUT",
            headers: { "Content-Type": "application/json" }
        })
        .then((response) => {
            if (!response.ok) throw new Error("Failed to update book");
            return response.text();
        })
        .then(() => {
            alert("Book updated successfully!");
            nav("/showBooks");
        })
        .catch((error) => {
            console.error("Error:", error);
        });
    },[bookId,nav]);
    return <p>Updated successfully</p>
}

export default UpdateAvailability;