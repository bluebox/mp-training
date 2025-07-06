import { useNavigate } from "react-router-dom";

function AddIssue(){
  const navigate=useNavigate();
  const IssueData=(event)=>{
    event.preventDefault();
    const form=event.target;
    const formData=new FormData(form);
    const formobj=Object.fromEntries(formData.entries());
    fetch("http://localhost:8000/issueBook/add", {
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
    alert("Issue added successfully!");
    navigate("/showIssue")
  })
  .catch((error) => {
    console.error("Error:", error);
  });
  }
  
  return (
    <form onSubmit={IssueData}>
      <label htmlFor='issueId'>Issue ID : </label>
      <input type='number' id='issueId' name='issueId'/><br></br>
      <label htmlFor='bookId'>Book ID : </label>
      <input type='number' id='bookId' name='bookId'/><br></br>
      <label htmlFor='memberId'>Member ID : </label>
      <input type='number' id='memberId' name='memberId'/><br></br>
      <input type='hidden' id='statusRec' name='statusRec' defaultValue={"I"}/><br></br>
      <input type='submit'/>
    </form>
    
  );
}
export default AddIssue;