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
    console.log(JSON.stringify(formobj),formobj.regNum);
    fetch(`http://10.129.241.187:8000/vehicle/update?vehicleId=${vehicleId}&regNum=${formobj.regNum}&updatedBy=${formobj.updatedBy}`, {
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
        alert(data);
        nav("/vehicle/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return(
    <form onSubmit={VehicleData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center",position:"fixed"}}>
        <table>
            <thead>
                <tr><td colSpan={2}><h1>Update Vehicle Data</h1></td></tr>
            </thead>
            <tbody>
                <tr>
                    <td><label htmlFor="regNum">Registration Number</label></td>
                    <td><input type="text" id="regNum" name="regNum"/></td>
                </tr>
                <tr>
                    <td><input type="text" id="updatedBy" name="updatedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
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
