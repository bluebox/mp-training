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
  .then(() => {
    alert("Issue added succesfully");
    navigate("/showIssue")
  })
  .catch((error) => {
    console.error("Error:", error);
  });
  }
  let date = new Date();
  let today = date.toISOString().split("T")[0];
  let returnDate = new Date(date);
  returnDate.setDate(date.getDate() + 7);
  let returnDateStr = returnDate.toISOString().split("T")[0];
  return (
    <form onSubmit={IssueData}>
      <label htmlFor='issueId'>Issue ID : </label>
      <input type='number' id='issueId' name='issueId'/><br></br>
      <label htmlFor='bookId'>Book ID : </label>
      <input type='number' id='bookId' name='bookId'/><br></br>
      <label htmlFor='memberId'>Member ID : </label>
      <input type='number' id='memberId' name='memberId'/><br></br>
      <input type='text' id='statusrec' name='statusrec' value={'I'}/><br></br>
      <input type='text' id='issueDate' name='issueDate' value={today} hidden/><br></br>
      <input type='text' id='returnDate' name='returnDate' value={returnDateStr} hidden/><br></br>
      <input type='submit'/>
    </form>
    
  );
}
export default AddIssue;