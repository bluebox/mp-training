
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
        window.location.reload(); // Refresh table after deletion
    };

    return (
        <tr>
            <td>{index}</td>
            <td>{student.name}</td>
            <td>{student.age}</td>
            <td>{student.email}</td>
            <td>{student.phoneNumber}</td>
            <td>{student.gender}</td>
            <td>{student.branch}</td>
            <td>{student.language.join(', ')}</td>
            <td>{student.state}</td>
            <td><button onClick={handleEdit}>Edit</button></td>
            <td><button onClick={() => handleDelete(student.id)}>Delete</button></td>
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
        <table style={{border: '1px solid black', borderCollapse: 'collapse', margin: '10px'} }>
            <thead>
                <th>S.No</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Gender</th>
                <th>Branch</th>
                <th>Languages</th>
                <th>State</th>
            </thead>
            <tbody style={{border: '1px solid black', borderCollapse: 'collapse'} }>
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

