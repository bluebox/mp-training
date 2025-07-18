import { Link } from "react-router-dom";

function ManageClaim(){
    return (
        <div>
            <Link to="/claim/show"><button>Show Claims</button></Link>&nbsp;&nbsp;
            <Link to="/claim/request"><button>Show All requested Claims</button></Link>
        </div>
    )
}
export default ManageClaim;