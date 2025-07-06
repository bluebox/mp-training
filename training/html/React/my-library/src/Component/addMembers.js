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
      <input type='number' id='memberId' name='memberId'/>
      <label htmlFor='name'>Name  : </label>
      <input type='text' id='name' name='name'/>
      <label htmlFor='email'>Email : </label>
      <input type='text' id='email' name='email'/>
      <label htmlFor='mobile'>Phone no : </label>
      <input type='text' id='mobile'name='mobile'/>
      <label htmlFor='gender'>Gender : </label>
      <input type='text' name='gender' id='gender'/>
      <label htmlFor='address'>Address : </label>
      <input type='text' name='address' id='address'/>
      <input type='submit'/>
    </form>
  );
}
export default AddMembers;