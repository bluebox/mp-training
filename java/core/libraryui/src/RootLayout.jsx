import { Outlet } from "react-router-dom";
import Header from "./Components/Header";

function RootLayout(){
    return(
        <div>
            <Header />
            <div style={{minHeight:"100vh"}}>
                <Outlet/>
            </div>

        </div>
    )
}

export default RootLayout;