import api from '../api/axios';
import { useEffect, useState } from "react"

export default function ViewAll(){
    const [count, setCount] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("");
    const [isFilter, setIsFilter] = useState(false);
    const [filteredUsers, setFilteredUsers] = useState([]);

    useEffect(() => {
    const fetchData = async () => {
      try{
        if(username==="" && email==="" && role===""){
          setIsFilter(false);
        }
        const response = isFilter? await api.get("all/profiles/?page="+currentPage+'&'+(username===""?"":"username="+username)+'&'+(email===""?"":"email="+email)
                                      +'&'+(role===""?"":"role="+role)) :await api.get('all/profiles/?page='+currentPage);

        setFilteredUsers(response.data.results);
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
    // eslint-disable-next-line
  }, [currentPage]);

  const handleFilter = async () => {
    setCurrentPage(1);
    setIsFilter(true);
    const response = await api.get("all/profiles/?page="+currentPage+'&'+(username===""?"":"username="+username)+'&'+(email===""?"":"email="+email)
                                      +'&'+(role===""?"":"role="+role));
    setFilteredUsers(response.data.results);
    if(currentPage===1){
      if(response.data.results.length){
        setCount(Math.ceil(response.data.count/response.data.results.length));
      }
      else{
        setCount(1);
      }
    }
  }

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

  const handleChange = (e) => {

    if(e.target.name === "username"){
      setUsername(e.target.value);
    }
    else if(e.target.name === "email"){
      setEmail(e.target.value);
    }
    else{
      setRole(e.target.value);
    }
  }

  return (
      <div>
        <div>
          <input type="text" value={username} placeholder=" username" name="username" onChange={handleChange} />
        
          <input type="text" value={email} placeholder="email" name="email" onChange={handleChange} />

          <select value={role} name="role" onChange={handleChange} >
            <option value="">select </option>
            <option name="role" value="lead">lead</option>
            <option name="role" value="admin">admin</option>
            <option name="role" value="member">member</option>
          </select>

          <button onClick={handleFilter}>filter</button>
        </div>


          <table>
              <tr>
                  <th>Username</th>
                  <th>Email</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Role</th>
              </tr>
              {filteredUsers.map((user) =>(
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
  );
}