import { Link } from "react-router-dom";

function ManagePolicy(){
    return (
        <div>
            <Link to="/policy/show"><button>Show Policies</button></Link>&nbsp;&nbsp;
            <Link to="/policy/showRequests"><button>Show All requested Policies</button></Link>
        </div>
    )
}
export default ManagePolicy;