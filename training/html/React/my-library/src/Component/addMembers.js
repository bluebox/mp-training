import { useNavigate } from "react-router-dom";

function AddMembers(){
  const navigate=useNavigate();
    const MemberData=(event)=>{
      event.preventDefault();
      const form=event.target;
      const formData=new FormData(form);
      const formobj=Object.fromEntries(formData.entries());
      fetch("http://localhost:8000/member/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formobj),
    })
    .then((response) => {
      if (!response.ok) throw new Error("Failed to fetch data");
      return response.text();
    })
    .then(() => {
      alert("Member added successfully!");
      navigate("/showMembers")
    })
    .catch((error) => {
      console.error("Error:", error);
    });
  }

  return (
    <form onSubmit={MemberData}>
      <label htmlFor='memberId'>Member ID : </label>
      <input type='number' id='memberId' name='memberId'/><br></br>
      <label htmlFor='name'>Name  : </label>
      <input type='text' id='name' name='name'/><br></br>
      <label htmlFor='email'>Email : </label>
      <input type='text' id='email' name='email'/><br></br>
      <label htmlFor='mobile'>Phone no : </label>
      <input type='text' id='mobile'name='mobile'/><br></br>
      <label htmlFor='gender'>Gender : </label>
      <select name='gender' id='gender'>
        <option hidden>Select your gender</option>
        <option value="M">Male</option>
        <option value="F">Female</option>
      </select>  
      <br></br>
      <label htmlFor='address'>Address : </label>
      <input type='text' name='address' id='address'/><br></br>
      <input type='submit'/>
    </form>
  );
}
export default AddMembers;