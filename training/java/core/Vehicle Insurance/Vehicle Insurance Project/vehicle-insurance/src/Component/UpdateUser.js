import { useNavigate, useParams } from "react-router-dom";

function UpdateUser() {
    const nav=useNavigate();
    const { username } = useParams();
    const userData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    if(formobj.newPassword!==formobj.confirmPassword){
      alert("Passwords doesn't match");
    }
    else{
      fetch(`http://localhost:8000/user/updatePassword?username=${username}&oldPassword=${formobj.oldPassword}&password=${formobj.newpassword}&updatedBy=${formobj.passwordUpdatedBy}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        credentials:"include"
      })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        return response.text();
      })
      .then((data) => {
        console.log(data.text());
        nav("/users/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
    }
  };

  return (
    <form onSubmit={userData}>
        <table>
            <tbody>
                <tr>
                    <td><input type="text" id="username" name="username" value={username} style={{visibility:"hidden"}}/></td>
                </tr>
                <tr>
                    <td><label htmlFor=" oldPassword">Old Password : </label></td>
                    <td><input type="text" id="oldPassword" name="oldPassword"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="newPassword">New Password : </label></td>
                    <td><input type="text" id="newPassword" name="newPassword"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="confirmPassword">Confirm Password : </label></td>
                    <td><input type="text" id="confirmPassword" name="confirmPassword"/></td>
                </tr>
                <tr>
                    <td><input type="text" id="passwordUpdatedBy" name="passwordUpdatedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                </tr>
                <tr>
                    <td colSpan={2}><input type="submit" value={"submit"}/></td>
                </tr>
            </tbody>
        </table>
    </form>
  );
}

export default UpdateUser;
