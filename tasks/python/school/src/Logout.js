import { useNavigate } from "react-router-dom";

function Logout({ setLogin }) {
  const navigate = useNavigate();

  setLogin(false)
  localStorage.removeItem('jwtAccessToken')
  localStorage.removeItem('jwtRefreshToken')
  localStorage.removeItem('access')
  localStorage.setItem('isLogin','false')
  navigate('/login');
}
export default Logout