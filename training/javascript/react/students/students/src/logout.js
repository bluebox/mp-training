import { useNavigate } from "react-router-dom";

function Logout({ setLogin }) {
  const navigate = useNavigate();

  setLogin(false)
  localStorage.removeItem('token')
  navigate('/login');
//   const handleLogout = () => {
//     setLogin(false);
//     navigate('/login');
//   };

//   return (
//     <button onClick={handleLogout} >
//       Logout
//     </button>
//   );
}
export default Logout