import { Link } from "react-router-dom";
function Main(){
    return (
        <div>
            <Link to={"/user/login"}><button>Admin</button></Link>
            <button>User</button>
        </div>
    )
}
export default Main;