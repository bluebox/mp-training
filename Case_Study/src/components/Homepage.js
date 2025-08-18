
import { useNavigate } from 'react-router-dom';



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
    
    


    return <>
    <div>
    <h1>Library Management System</h1>
    <div><button onClick={handlebooksnavigate}>Books</button></div>
    <div><button onClick={handlememberssnavigate}>Members</button></div>
    <div><button onClick={handleissuerecordssnavigate}>Issue Records</button></div>
    <div><button onClick={handlereportsnavigate}>Reports</button></div>

    </div>


    </>

    
}
export default Homepage;