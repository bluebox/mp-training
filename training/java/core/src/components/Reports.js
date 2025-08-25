import { useNavigate } from "react-router-dom";
function Reports(){
    const navigate=new useNavigate();
     const handleoverduebook=()=>{
    navigate("/overduebooks");
  }
  const handlecountbookpercategory=()=>{
    navigate("/countpercategory");
  }
   const handleissuemember=()=>{
    navigate("/issuemembers");
  }
  const handleback=()=>{
    navigate("/");
  }


    return <div>

        <div>
            <button onClick={handleoverduebook}>OverdueBooks</button>
        </div>
        <div>
            <button onClick={handlecountbookpercategory}>Count Books Per Category</button>
        </div>
        <div>
            <button onClick={handleissuemember}>Status Issue Members</button>
        </div>
        <div><button onClick={handleback}>Back</button></div>
    </div>
}
export default Reports;