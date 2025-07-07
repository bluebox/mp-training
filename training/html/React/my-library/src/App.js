import {BrowserRouter,Routes,Route} from "react-router-dom";
import AddBooks from "./Component/addBook";
import ShowBooks from "./Component/ShowBook"
import Main from "./Component/Main";
import AddMembers from "./Component/addMembers";
import ShowMembers from "./Component/ShowMembers";
import UpdateBooks from "./Component/UpdateBook";
import UpdateMembers from "./Component/UpdateMember";
import UpdateAvailability from "./Component/UpdateAvailability";
import ShowIssues from "./Component/ShowIssue";
import AddIssue from "./Component/addIssue";
import ReturnIssue from "./Component/returnIssue";
import Login from "./Component/Login";
export default function App(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>}></Route>
                <Route path="/addBooks" element={<AddBooks/>}></Route>
                <Route path="/showBooks" element={<ShowBooks/>}></Route>
                <Route path="/addMember" element={<AddMembers/>}></Route>
                <Route path="/ShowMembers" element={<ShowMembers/>}></Route>
                <Route path="/updateBooks/:bookId" element={<UpdateBooks/>}></Route>
                <Route path="/updateAvailability/:bookId" element={<UpdateAvailability/>}></Route>
                <Route path="/showMembers" element={<ShowMembers/>}></Route>
                <Route path="/addMember" element={<AddMembers/>}></Route>
                <Route path="/updateMember/:memberId" element={<UpdateMembers/>}></Route>
                <Route path="/showIssue" element={<ShowIssues/>}></Route>
                <Route path="/addIssue" element={<AddIssue/>}></Route>
                <Route path="/returnIssue/:issueId" element={<ReturnIssue/>}></Route>
                <Route path="/login" element={<Login/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}
