import { useNavigate } from "react-router-dom";

function Logout({ setLogin }) {
  const navigate = useNavigate();

  setLogin(false)
  localStorage.removeItem('token')
  navigate('/login');
}
export default Logout