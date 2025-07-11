import {BrowserRouter,Routes,Route} from "react-router-dom";
import Main from "./Component/Home";
import LoginPage from "./Component/AdminLogin";
import UserRegistration from "./Component/PersonalDetails";
import VehicleDetails from "./Component/VehicleDetails";
import User from "./Component/User";
import Policy from "./Component/Policy";
import Claim from "./Component/Claim";
import ShowCustomers from "./Component/ShowCustomers";
import ShowVehicle from "./Component/Vehicle";
import UpdateCustomer from "./Component/UpdateCustomer";
export default function App(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>}></Route>
                <Route path="/user/personal" element={<UserRegistration/>}></Route>
                <Route path="/user/vehicle" element={<VehicleDetails/>}></Route>
                <Route path="/user/userDetails" element={<User/>}></Route>
                <Route path="/user/login" element={<LoginPage/>}></Route>
                <Route path="/user/policy" element={<Policy/>}></Route>
                <Route path="/user/claim" element={<Claim/>}></Route>
                <Route path="/customer/show" element={<ShowCustomers/>}></Route>
                <Route path="/vehicle/show" element={<ShowVehicle/>}></Route>
                <Route path="/customer/update" element={<UpdateCustomer/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}
