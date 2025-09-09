import { useEffect, useState } from "react";
import { fetchAllManagers, fetchEmployees } from "../../service/adminService";

const ViewMembers=()=> {

    const [managers,setManagers] = useState([]);
    const [employees,setEmployees] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    useEffect(()=>{
        const loadUsers=async()=>{
            try{
                const managers = await fetchAllManagers();
                setManagers(managers);

                const employees = await fetchEmployees(); 
                setEmployees(employees);
            }catch(err){
                alert(err);
            } finally{
                setIsLoading(false);
            }
        }
        loadUsers();
    },[])
    
    if(isLoading)  return (<div>loading....</div>);
    
    return(
        <div className="container mt-5">
            <div className="mb-5"> 
                <h1>Member List</h1>
            </div>
            <h3>Managers</h3>
            <table className="table table-striped">
                <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Manager Name</th>
                    </tr>
                </thead>
                <tbody>
                    {managers.map((manager)=>(
                    <tr>
                        <td>{manager.id}</td>
                        <td>{manager.username}</td>
                    </tr>
                    ))}
                </tbody>
            </table>

            <h3>Employees</h3>
            <table className="table table-striped">
                <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Employee Name</th>
                        <th>Manager Name</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee)=>(
                    <tr>
                        <td>{employee.id}</td>
                        <td>{employee.username}</td>
                        <td>{managers.find(m=>m.id===employee.manager_id).username}</td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ViewMembers;