import ReactDOM from "react-dom/client";
import {BrowserRouter,Routes,Route} from "react-router-dom";
import AddBooks from "./Component/addBook";
import ShowBooks from "./Component/showBook";
import Main from "./Component/home";

export default function App(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>}></Route>
                <Route path="/add" element={<AddBooks/>}></Route>
                <Route path="/show" element={<ShowBooks/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}
const root=ReactDOM.createRoot(document.getElementById("root"));
root.render(<App/>);