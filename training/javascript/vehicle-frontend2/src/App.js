import  {BrowserRouter, Route,Routes} from "react-router-dom";
import LoginPage from "./Component/LoginPage";
import AdminDashboard from "./Component/AdminDashboard";
import UserDashboard from "./Component/UserDashboard";
import NotAuthorized from "./NotAuthorized";
import ProtectedRoute from "./ProtectedRoute";
import UserRegistration from"./Component/PersonalDetails";
import ShowCustomers from "./Component/ShowCustomers";
import UpdateCustomer from "./Component/UpdateCustomer";
import ShowClaim from "./Component/ShowClaim";
import ShowPolicy from "./Component/ShowPolicy";
import ShowVehicle from "./Component/Vehicle";
import Claim from "./Component/Claim";
import Policy from "./Component/Policy";
import User from "./Component/User";
import VehicleDetails from "./Component/VehicleDetails";
import UpdateVehicle from "./Component/UpdateVehicle";
import ShowUser from "./Component/ShowUser";
import UpdateUser from "./Component/UpdateUser";
import ShowRequestedPolicy from "./Component/ShowRequest";
import ManagePolicy from "./Component/ManagePolicy";
import RenewPolicy from "./Component/RenewPolicy";
import UpdatePolicy from "./Component/UpdatePolicy";
import ManageClaim from "./Component/ManageClaim";
import ShowRequestedClaim from "./Component/ClaimRequests";
import UpdateClaim from "./Component/UpdateClaim";
import LogoutPage from "./Component/Logout";
export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/not-authorized" element={<NotAuthorized />} />

                <Route path="/admin"
                    element={
                        <ProtectedRoute roleRequired="ROLE_ADMIN">
                            <AdminDashboard />
                        </ProtectedRoute>
                    }
                />
                <Route path="/user"
                    element={
                        <ProtectedRoute roleRequired="ROLE_USER">
                            <UserDashboard />
                        </ProtectedRoute>
                    }
                />

            </Routes>
            <Routes>
                <Route path="/user/personal" element={<UserRegistration/>}></Route>
                <Route path="/user/vehicle" element={<VehicleDetails/>}></Route>
                <Route path="/user/userDetails" element={<User/>}></Route>
                <Route path="/user/policy" element={<Policy/>}></Route>
                <Route path="/user/claim" element={<Claim/>}></Route>
                <Route path="/customer/show" element={<ShowCustomers/>}></Route>
                <Route path="/vehicle/show" element={<ShowVehicle/>}></Route>
                <Route path="/customer/update/:customerId" element={<UpdateCustomer/>}></Route>
                <Route path="/policy/show" element={<ShowPolicy/>}></Route>
                <Route path="/claim/show" element={<ShowClaim/>}></Route>
                <Route path="/vehicle/update/:vehicleId" element={<UpdateVehicle/>}></Route>
                <Route path="/users/add" element={<User/>}></Route>
                <Route path="/users/show" element={<ShowUser/>}></Route>
                <Route path="/user/update/:username" element={<UpdateUser/>}></Route>
                <Route path="/policy/showRequests" element={<ShowRequestedPolicy />}></Route>
                <Route path="/policy/manage" element={<ManagePolicy/>}></Route>
                <Route path="/policy/renew/:policyId" element={<RenewPolicy/>}></Route>
                <Route path="/policy/update/:policyId" element={<UpdatePolicy/>}></Route>
                <Route path="/claim/manage" element={<ManageClaim/>}></Route>
                <Route path="/claim/request" element={<ShowRequestedClaim/>}></Route>
                <Route path="/claim/approve/:claimId" element={<UpdateClaim/>}></Route>
                <Route path="/logout" element={<LogoutPage/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

