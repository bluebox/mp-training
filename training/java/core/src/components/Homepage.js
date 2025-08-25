
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import axios from 'axios';

function Homepage(){
  const navigate = useNavigate();
    const handlebooksnavigate=()=>{
        navigate('/viewbooks');
    }
const handlememberssnavigate=()=>{
        navigate('/viewallmembers');
    }
    const handleissuerecordssnavigate=()=>{
        navigate('/issuerecords');
    }
    const handlereportsnavigate=()=>{
        navigate('/Reports');
    }
    const handlelogout=async ()=>{
        // navigate('http:localhost/8095/logout');
       // window.location.replace('http:localhost/8095/logout'); 
        try {
      const response = await axios.get(
        "http://localhost:8095/logout",
        { withCredentials: true }
      );
       Object.keys(Cookies.get()).forEach(cookieName => {
            Cookies.remove(cookieName);
        });



    }catch(err){
        console.log("error in logging out",err);
    }

    }
    
    


    return <>
    <div>
    <h1>Library Management System</h1>
    <div><button onClick={handlebooksnavigate}>Books</button></div>
    <div><button onClick={handlememberssnavigate}>Members</button></div>
    <div><button onClick={handleissuerecordssnavigate}>Issue Records</button></div>
    <div><button onClick={handlereportsnavigate}>Reports</button></div>
    {/* <div><button onClick={handlelogout}>Logout page</button></div> */}
    </div>


    </>

    
}
export default Homepage;