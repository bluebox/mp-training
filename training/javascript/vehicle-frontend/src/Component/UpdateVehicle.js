import { useNavigate, useParams } from "react-router-dom";

function UpdateVehicle() {
    const nav=useNavigate();
    const { vehicleId } = useParams();
    const VehicleData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    const formDetails=JSON.stringify(formobj);
    fetch(`http://localhost:8080/vehicle/update?vehicleId=${vehicleId}&regNum=${formobj.regNum}&updatedBy=${formobj.updatedBy}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: formDetails,
      credentials:"include"
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        return response.text();
      })
      .then((data) => {
        console.log("Vehicle details updated successfully!");
        nav("/vehicle/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return(
        <form onSubmit={VehicleData}>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="regNum">Registration Number</label></td>
                        <td><input type="text" id="regNum" name="regNum"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="updatedBy">Created By : </label></td>
                        <td><input type="text" id="updatedBy" name="updatedBy"/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}

export default UpdateVehicle;
