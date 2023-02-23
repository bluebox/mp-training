import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
function Header() {
  const [ searchData, setSearchData] = useState({
    'search':'',
  });
  const teacherLoginStatus = localStorage.getItem('teacherLoginStatus');
  const userLoginStatus = localStorage.getItem('userLoginStatus');

  useEffect(() => {
    document.title = "Main Home Page";
  });

  const handleChange=(e) =>{
    console.log(e.target.value);
    setSearchData({...searchData, [e.target.name]:e.target.value
    });
  } 
  
  const searchCourse = () => {
    console.log(10);
    window.location.href='/search/' + searchData.search;
  }
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <Link className="navbar-brand" to="/">Start Learning</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav ms-auto">
            <form className="d-flex">
              <input className="form-control me-2" name="search" onChange={handleChange} type="search" placeholder="Search for a Course" aria-label="Search"/>
                <button  onClick={searchCourse} className="btn btn-outline-success" type="button">Search</button>
            </form>

            <Link className="nav-link active" aria-current="page" to="/">Home</Link>
            <Link className="nav-link" to="/all-courses">Courses</Link>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Instructor
              </a>
              <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                {teacherLoginStatus != 'true' &&
                  <>
                    <li><Link className="dropdown-item" to="/master-login">Login</Link></li>
                    <li><Link className="dropdown-item" to="/master-register">Register</Link></li>
                  </>
                }
                {teacherLoginStatus != 'false' &&
                  <>
                    <li><Link className="dropdown-item" to="/master-dashboard">Dashboard</Link></li>
                    <li><Link className="dropdown-item" to="/master-logout">Logout</Link></li>
                  </>
                }
              </ul>
            </li>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                User
              </a>
              <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                {userLoginStatus !== 'true' &&
                  <>
                    <li><Link className="dropdown-item" to="/user-login">Login</Link></li>
                    <li><Link className="dropdown-item" to="/user-register">Register</Link></li>
                  </>
                }
                {userLoginStatus !== 'false' &&
                  <>
                    <li><Link className="dropdown-item" to="/user-dashboard">Dashboard</Link></li>
                    <li><Link className="dropdown-item" to="/user-logout">Logout</Link></li>
                  </>
                }
              </ul>
            </li>
          </div>
        </div>
      </div>
    </nav>
  )
}

export default Header;