import { useNavigate, useParams } from "react-router-dom";

function UpdateCustomer() {
    const nav=useNavigate();
    const { customerId } = useParams();
    const CustomerData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    formobj.customerId=Number(customerId);
    console.log(customerId);
    fetch(`http://localhost:8080/customer/update?customerId=${customerId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formobj),
      credentials:"include"
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        console.log(JSON.stringify(formobj));
        return response.text();
      })
      .then((data) => {
        console.log("Customer details updated successfully!");
        nav("/customer/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <form onSubmit={CustomerData}>
      <table>
            <tbody>
                <tr>
                    <td><input type="hidden" name="customerId" value={customerId}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="name">Customer name : </label></td>
                    <td><input type="text" id="name" name="name" required/></td>
                </tr>
                <tr>
                    <td><label htmlFor="email">Email : </label></td>
                    <td><input type="email" id="email" name="email"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="contact">Phone no : </label></td>
                    <td><input type="text" id="contact" name="contact" pattern="[5-9]{1}[0-9]{9}"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="gender">Gender : </label></td>
                    <td>
                        <select id="gender" name="gender">
                            <option hidden>Enter your gender</option>
                            <option value={"MALE"}>Male</option>
                            <option value={"FEMALE"}>Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label htmlFor="age">Age : </label></td>
                    <td><input type="number" name="age" min={18}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="occupation">Occupation : </label></td>
                    <td><input type="text" id="occupation" name="occupation"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="income">Income : </label></td>
                    <td><input type="number" id="income" name="income"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="address">Address : </label></td>
                    <td><input type="text" id="address" name="address"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="customerUpdatedBy">Customer Updated By : </label></td>
                    <td><input type="text" id="customerUpdatedBy" name="customerUpdatedBy"/></td>
                </tr>
                <tr>
                    <td colSpan={2}><input type="submit"/></td>
                </tr>
            </tbody>
        </table>
    </form>
  );
}

export default UpdateCustomer;
