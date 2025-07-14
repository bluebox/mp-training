import { useNavigate, useParams } from "react-router-dom";

function UpdateUser() {
    const nav=useNavigate();
    const { username } = useParams();
    const userData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    formobj.username=Number(username);
    console.log(username);
    fetch(`http://localhost:8080/user/updatePassword?username=${username}&password=${formobj.password}&passwordUpdatedBy=${formobj.passwordUpdatedBy}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      credentials:"include"
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        return response.text();
      })
      .then((data) => {
        console.log("User details updated successfully!");
        nav("/users/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <form onSubmit={userData}>
        <table>
            <tbody>
                <tr>
                    <td><label htmlFor="username">User name : </label></td>
                    <td><input type="text" id="username" name="username"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="password">Password : </label></td>
                    <td><input type="text" id="password" name="password"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="passwordUpdatedBy">Password Updated By : </label></td>
                    <td><input type="text" id="passwordUpdatedBy" name="passwordUpdatedBy"/></td>
                </tr>
                <tr>
                    <td colSpan={2}><input type="submit"/></td>
                </tr>
            </tbody>
        </table>
    </form>
  );
}

export default UpdateUser;
