import { useNavigate, useParams } from "react-router-dom";

function UpdateMembers() {
    const nav=useNavigate();
    const { memberId } = useParams();
    const MemberData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    formobj.memberId=Number(memberId);
    fetch(`http://localhost:8000/member/update`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formobj)
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to update book");
        return response.text();
      })
      .then((data) => {
        alert("Member updated successfully!");
        nav("/showMembers");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <form onSubmit={MemberData}>
      <input type='number' id='memberId' name='memberId' defaultValue={memberId} hidden/>
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

export default UpdateMembers;
