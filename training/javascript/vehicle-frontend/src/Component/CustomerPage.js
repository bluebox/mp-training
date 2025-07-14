import UserRegistration from "./PersonalDetails";
import User from "./User";
import VehicleDetails from "./VehicleDetails";

function CustomerPage(){
    return(
        <div style={{padding:"200px", margin:"500px"}}>
            <div style={{textAlign:"center"}}><h1>Customer Details</h1></div>
            <UserRegistration/>
            <div style={{textAlign:"center"}}><h1>Vehicle Details</h1></div>
            <VehicleDetails/>
            <div style={{textAlign:"center"}}><h1>User Details</h1></div>
            <User/>
        </div>
    );
}
export default CustomerPage;