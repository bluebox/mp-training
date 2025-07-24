import "./StudentData.css";
import { useNavigate } from "react-router-dom";

function TableRow({ index, student }) {
    const navigate = useNavigate();

    const handleEdit = () => {
        navigate("/create", { state: { student } });
    };

    const handleDelete = (id) => {
        const data = JSON.parse(localStorage.getItem('students')) || [];
        const index = data.findIndex(s => s.id === id);
        if (index !== -1) {
            data.splice(index, 1);
            localStorage.setItem('students', JSON.stringify(data));
            alert("Record was deleted");
        }
        window.location.reload(); 
    };

    return (
        <tr className="mytable">
            <td className="mytable">{index}</td>
            <td className="mytable">{student.name}</td>
            <td className="mytable">{student.age}</td>
            <td className="mytable">{student.email}</td>
            <td className="mytable">{student.phoneNumber}</td>
            <td className="mytable">{student.gender}</td>
            <td className="mytable">{student.branch}</td>
            <td className="mytable">{student.language.join(', ')}</td>
            <td className="mytable">{student.state}</td>
            <td className="mytable">{student.city}</td>
            <td className="mytable"><button onClick={handleEdit}>Edit</button></td>
            <td className="mytable"><button onClick={() => handleDelete(student.id)}>Delete</button></td>
        </tr>
    );
}


function StudentData(){

    const data = JSON.parse(localStorage.getItem("students")) || [];
    if(data.length === 0){
        alert("No records found")
        return <></>
    }
    return (<>
        <table className="mytable">
            <thead>
                <th className="mytable">S.No</th>
                <th className="mytable">Name</th>
                <th className="mytable">Age</th>
                <th className="mytable">Email</th>
                <th className="mytable">Phone Number</th>
                <th className="mytable">Gender</th>
                <th className="mytable">Branch</th>
                <th className="mytable">Languages</th>
                <th className="mytable">State</th>
                <th className="mytable">City</th>
                <th className="mytable" colSpan={2}>Actions</th>
                
            </thead>
            <tbody className="mytable">
                {
                data.map(function (value, index){
                    return <TableRow index = {index+1} student={value}/>
                })
                }
            </tbody>
        </table>
    </>
    )
}

export default StudentData;

