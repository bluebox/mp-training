import { useEffect } from 'react';
import {  NavLink, useNavigate } from 'react-router-dom';
import { logout } from '../../service/loginService';

const Header = ({role,setRole}) => {
  const navigate = useNavigate();
  
  
  useEffect(()=>{
    const currentRole = localStorage.getItem("role");
    setRole(currentRole);
  },[navigate])
 
  const handleLogOut = async () => {
    localStorage.clear()
    localStorage.setItem("role","ROLE_ANONYMOUS")
    
    await logout();

    setRole("ROLE_ANONYMOUS");
    navigate('/login')
  }

  const getPath = (item) => {
    const paths = {
      'LOGIN': '/login',
      'DASHBOARD': '/dashboard',

      'MY SUBMISSIONS': '/my-submissions',
      'ADD EXPENSE':'/add-submission',
      
      'VIEW SUBMISSIONS': '/view-submissions',
      
      'ADD MEMBER': '/add-member',
      'VIEW MEMBERS':'/view-members',
    };
    return paths[item];
  };

  return (
    <nav>
      <div className="text-light bg-dark p-3 d-flex justify-content-around align-items-center">
        <h2 className='mb-0'>Expense Tracker</h2>
      

      {role === 'ROLE_EMPLOYEE' && (
         <div className='d-flex gap-3 ms-2 me-2'>
          {['DASHBOARD', 'MY SUBMISSIONS','ADD EXPENSE'].map(item => (
            <div key={item} >
              <NavLink
                to={getPath(item)}
                end={item === 'HOME'}
                style={{ textDecoration: 'none', color: 'white' }}
              >
                {item}
              </NavLink>
            </div>
          ))}
          
        </div>
      )}

      {role === 'ROLE_MANAGER' && (
         <div className='d-flex gap-3 ms-2 me-2 align-items-center'>
          {['DASHBOARD', 'VIEW SUBMISSIONS'].map(item => (
            <div key={item} style={{  }}>
              <NavLink
                to={getPath(item)}
                end={item === 'HOME'}
                style={{ textDecoration: 'none', color: 'white' }}
              >
                {item}
              </NavLink>
            </div>
          ))}
        </div>
      )}

      {role === 'ROLE_ADMIN' && (
        <div className='d-flex gap-3 ms-2 me-2'>
          {['DASHBOARD', 'ADD MEMBER','VIEW MEMBERS'].map(item => (
            <div key={item} style={{ marginBottom: '6px' }}>
              <NavLink
                to={getPath(item)}
                end={item === 'HOME'}
                style={{ textDecoration: 'none', color: 'white' }}
              >
                {item}
              </NavLink>
            </div>
          ))}
          
        </div>
      )}
      { role!=="ROLE_ANONYMOUS" &&
        <div>
            <span
              onClick={handleLogOut}
              style={{ cursor: 'pointer', color: 'red' }}
            >
              SIGNOUT
            </span>
        </div>
      } 
       </div>
      
    </nav>
  );
};

export default Header;