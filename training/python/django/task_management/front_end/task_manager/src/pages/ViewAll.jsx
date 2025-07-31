import api from '../api/axios';
import { useEffect, useState } from "react"

export default function ViewAll(){

    const [users, setUsers] =useState([]);
    const [count, setCount] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
    const fetchData = async () => {
      try{
        const response = await api.get('all/profiles/?page='+currentPage);
        setUsers(response.data.results);
        if(currentPage===1){
          if(response.data.results.length){
            setCount(Math.ceil(response.data.count/response.data.results.length));
          }
          else{
            setCount(1);
          }
        }
      }
      catch{
        console.log("error in retrieving tasks");
      }
    }
    fetchData();
  }, [currentPage]);

  const handleNextPage =() => {
    if(currentPage <= count){
      setCurrentPage(prevPage => prevPage + 1);
    }
  };

  const handlePrevPage =() => {
    if(currentPage >= 1){
      setCurrentPage(prevPage => prevPage - 1);
    }
  };

  return (
      <div>
          <table>
              <tr>
                  <th>Username</th>
                  <th>Email</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Role</th>
              </tr>
              {users.map((user) =>(
              <tr key={user.id}>
                  <td>{user.username}</td>
                  <td>{user.email}</td>
                  <td>{user.first_name}</td>
                  <td>{user.last_name}</td>
                  <td>{user.role}</td>
              </tr>
            ))}
          </table>
          <div>
            <button onClick={handlePrevPage} disabled={currentPage === 1}>Prev</button>
            <span>Page {currentPage} of {count}</span>
            <button onClick={handleNextPage} disabled={currentPage === count}>Next</button>
          </div>
      </div>
  )
}