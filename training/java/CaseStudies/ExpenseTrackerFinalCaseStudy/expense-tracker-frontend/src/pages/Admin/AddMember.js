import { useEffect, useState } from "react";
import { addNewUser, fetchAllManagers } from "../../service/adminService";

const AddMember = ()=>{
    const [memberDetails,setMemberDetails] = useState({
        'username':'',
        'password':'',
        'role_id':'0',
        'manager_id':'0'
    })
    const [isNewEmployee,setIsNewEmployee] = useState(false);
    const [managers,SetManagers] = useState([]);


    useEffect(()=>{
        const loadManagers=async()=>{
            fetchAllManagers()
                    .then((res)=>SetManagers(res))
                    .catch((err)=>console.log(err));
        }
        loadManagers();
    },[])

    const handleChange=(e)=>{
        const { name, value } = e.target;
        if(name == "role_id" ){
            setIsNewEmployee(value=="3")
        }
        setMemberDetails(prev => ({
            ...prev,
            [name]: value
        }));
    }

    const clearForm=()=>{
            setMemberDetails({
            'username':'',
            'password':'',
            'role_id':'0',
            'manager_id':'0'
        })
        setIsNewEmployee(false);
    }
    
    const handleSubmit=(e)=>{
        e.preventDefault();

        addNewUser(memberDetails)
            .then((res)=>alert(res.message))
            .then(clearForm)
            .catch((err)=>{alert(err)})

        console.log("Trying to add member : " , memberDetails);
    }

    return (
        <div>
            <form onSubmit={handleSubmit} className="container mt-5" style={{ maxWidth: '400px' }}>
            <h2 className="mb-3">Add a new User</h2>
            
            <div className="mb-3">
                <label htmlFor="username" className="form-label">Username</label>
                <input type="text" id="username" name="username" value={memberDetails.username} onChange={handleChange} className="form-control" />
            </div>

            <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password  " name="password" id="password" value={memberDetails.password} onChange={handleChange} className="form-control" />
            </div>

            <div className="mb-3">
                <label htmlFor="role" className="form-label">Select Role</label>
                    <select
                    id="role_id"
                    name="role_id"
                    onChange={handleChange}
                    value={memberDetails.role_id}
                    required
                    className="form-select"
                    >
                    <option value="" >Choose a Role</option>
                    <option  value="3">Employee</option>
                    <option  value="2">Manager</option>
                </select>
            </div>
            {isNewEmployee && 
            
            <div className="mb-3">
                <label htmlFor="manager_id" className="form-label">Manager ID</label>
                

                <select id="manager_id" name="manager_id" value={memberDetails.manager_id} onChange={handleChange}  className="form-select">
                    <option value="" >Assign a manager</option>
                    {managers.map(manager=>(
                        <option key={manager.id} value={manager.id}>
                            {manager.id} - {manager.username} 
                        </option>
                    ))}
                </select>
            </div>
            
            }
            <button type="submit" className="btn btn-primary w-100">Submit</button>
        </form>
        </div>
    )
}

export default AddMember;