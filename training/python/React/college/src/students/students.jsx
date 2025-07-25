import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
function TableRow({ values,resource,pk }) {

    const navigate = useNavigate();
    function handleEdit(){
        navigate(("/create"+resource), { state: { values:values,resource:resource } });
    };

    function handleDelete(){
        try {
                axios.delete("http://127.0.0.1:8000/college/"+resource+'/'+values[pk]+'/');
                console.log('Delete successful:');
                window.location.reload();
            } 
            catch (error) {
                console.error('Error deleting item:', error);
            }
        }
  return (
    <tr>
      {
      Object.entries(values).map(([key, value]) => (
        <td key={key}>{value}</td>
      ))}
      <td><button onClick={(e)=>{handleEdit()}}>Edit</button></td>
      <td><button onClick={(e)=>{handleDelete()}}>Delete</button></td>
    </tr>
  );
}

export default function Table({ resource, fields, pk }) {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get(("http://127.0.0.1:8000/college/"+resource+'/'))
      .then((res) => {
        setData(res.data);
        console.log(res.data);
      })
      .catch((err) => {
        console.error("Error fetching all students:", err.message);
      });
  }, []);

  return (
    <div>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            {fields.map((field) => (
              <th key={field}>{field}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((student, index) => (
            <TableRow key={index} values={student} resource={resource} pk={pk}/>
          ))}
        </tbody>
      </table>
    </div>
  );
}