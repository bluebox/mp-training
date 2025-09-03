import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/authService";
import { useDispatch } from "react-redux";
import { setLoading } from "../utilities/redux/slices/loadingSlice";

export function LoginForm() {
  const dispatch = useDispatch();
  const [userDetails, setUserDetails] = useState({
    username: null,
    password: null,
  });
  const [errorName, setErrorName] = useState(false);
  const [errorPass, setErrorPass] = useState(false);

  const navigate = useNavigate();

  const handleUsernameChange = (e) => {
    const value = e.target.value;
    setUserDetails({ ...userDetails, username: value });
    setErrorName(!/^[a-zA-Z0-9_]{3,50}$/.test(value));
  };

  const handlePasswordChange = (e) => {
    const value = e.target.value;
    setUserDetails({ ...userDetails, password: value });
    setErrorPass(!/^.{5,50}$/.test(value));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!errorName && !errorPass && userDetails.username && userDetails.password) {
      localStorage.clear();
      dispatch(setLoading(true));
      await login(userDetails);
      dispatch(setLoading(false));
      if (localStorage.getItem("token")) {
        const role = localStorage.getItem("role");
        if (role === "ROLE_ADMIN") {
          navigate("/admin");
        } else if (role === "ROLE_STAFF") {
          navigate("/staff");
        } else {
          alert("Unknown role, cannot navigate.");
        }
      }
    }
  };

  return (
    <div className="container vh-100 d-flex align-items-center">
      <div className="row w-100">
        <div className="col-md-6 d-flex justify-content-center align-items-center bg-primary text-white">
          <h1>Restaurant Management System</h1>
        </div>

        <div className="col-md-6 d-flex justify-content-center align-items-center">
          <form className="w-75" onSubmit={handleSubmit}>
            <h2 className="mb-4">Login</h2>

            <div className="mb-3">
              <label className="form-label">Username</label>
              <input
                type="text"
                className={`form-control ${errorName ? "is-invalid" : ""}`}
                value={userDetails.username}
                onChange={handleUsernameChange}
                placeholder="Enter your username"
              />
              {errorName && (
                <div className="invalid-feedback">
                  Username must be 3-50 characters long (alphanumeric &
                  underscore).
                </div>
              )}
            </div>

            <div className="mb-3">
              <label className="form-label">Password</label>
              <input
                type="password"
                className={`form-control ${errorPass ? "is-invalid" : ""}`}
                value={userDetails.password}
                onChange={handlePasswordChange}
                placeholder="Enter your password"
              />
              {errorPass && (
                <div className="invalid-feedback">
                  Password must be 5-50 characters long.
                </div>
              )}
            </div>

            <button type="submit" className="btn btn-primary w-100">
              Log In
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
