import { Link, useNavigate } from "react-router-dom";

function Main(){
  const nav=useNavigate();
 
    nav("/login");
  
  return(
    <div>
      <Link to="/showBooks"><button>Show Books</button></Link>
      <Link to="/showMembers"><button>Show Members</button></Link>
      <Link to="/showIssue"><button>Show Issues</button></Link>
    </div> 
  )
}
export default Main;