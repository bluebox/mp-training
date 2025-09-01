import api from "../config/axiosConfig";
import Swal from "sweetalert2";

export const login = async (userDetails) => {
  try {
    const res = await api.post("/auth/login", userDetails, {
      withCredentials: true,
    });
    const { token, role } = res.data.data;
    localStorage.setItem("token", token);
    localStorage.setItem("role", role);
    Swal.fire({
      icon: "success",
      title: "Success!",
      text: "User Login  Successfull.",
      timer: 2000,
      showConfirmButton: false,
    });
  } catch (error) {
    Swal.fire(
      "Failed!",
      "Login Failed!Please enter correct username or password",
      "error"
    );
  }
};
export const logout = async () => {
  try {
    const res = await api.post("/auth/logout", {}, { withCredentials: true });
    localStorage.removeItem("token");
    localStorage.removeItem("role");
  } catch (error) {
    alert("Logout failed");
    console.error("Logout failed:", error);
  }
};
