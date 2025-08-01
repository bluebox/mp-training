import React, { useState } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { loginSuccess } from "./authSlice.js";
import { useNavigate } from "react-router-dom";
import {useFormik} from "formik";
import * as yup from "yup";
import { LoginSchema } from "./authSlice.js";
// const LoginForm = () => {
//   const dispatch = useDispatch();
//   const navigate = useNavigate();
//   const [form, setForm] = useState({ username: "", password: "" });
//   const [error, setError] = useState(null);

//   const handleChange = (e) =>
//     setForm({ ...form, [e.target.name]: e.target.value });

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setError(null);

//     try {
//       const res = await axios.post("http://127.0.0.1:8000/api/token/", form);
//       const { access, refresh } = res.data;

//       const profile = await axios.get("http://127.0.0.1:8000/employee/profile/", {
//         headers: { Authorization: `Bearer ${access}` },
//       });

//       const { emp_id, emp_name } = profile.data.data;
//       const role = profile.data.data.role || "employee";

//       dispatch(
//         loginSuccess({
//           access,
//           refresh,
//           role,
//           username: emp_name || form.username,
//         })
//       );

//       if (role === "ceo") navigate("/ceo/dashboard");
//       else if (role === "hr") navigate("/hr/dashboard");
//       else if (role === "manager") navigate("/manager/dashboard");
//       else navigate("/employee/profile");
//     } catch (err) {
//       console.error(err);
//       setError("Invalid credentials or server error.");
//     }
//   };
//   return (
//     <div className="max-w-md mx-auto bg-white shadow p-6 rounded-xl mt-10">
//       <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>
//       {error && <div className="text-red-600 mb-4 text-center">{error}</div>}

//       <form onSubmit={handleSubmit} className="space-y-4">
//         <div>
//           <label className="block font-semibold">Username</label>
//           <input
//             name="username"
//             value={form.username}
//             onChange={handleChange}
//             required
//             className="w-full border px-3 py-2 rounded"
//           />
//         </div>
//         <div>
//           <label className="block font-semibold">Password</label>
//           <input
//             name="password"
//             type="password"
//             value={form.password}
//             onChange={handleChange}
//             required
//             className="w-full border px-3 py-2 rounded"
//           />
//         </div>
//         <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
//           Login
//         </button>
//       </form>
//     </div>
//   );
// };

// export default LoginForm;



const LoginForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState(null);

  const {values,handleChange,handleBlur}=useFormik({
    initialValues:{
      username:"",
      password:"",
    },
    validationSchema:LoginSchema,

  });



//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setError(null);

//     try {
//       const res = await axios.post("http://127.0.0.1:8000/api/token/", form);
//       const { access, refresh } = res.data;

//       const profile = await axios.get("http://127.0.0.1:8000/employee/profile/", {
//         headers: { Authorization: `Bearer ${access}` },
//       });

//       const { emp_id, emp_name } = profile.data.data;
//       const role = profile.data.data.role || "employee";

//       dispatch(
//         loginSuccess({
//           access,
//           refresh,
//           role,
//           username: emp_name || form.username,
//         })
//       );

//       if (role === "ceo") navigate("/ceo/dashboard");
//       else if (role === "hr") navigate("/hr/dashboard");
//       else if (role === "manager") navigate("/manager/dashboard");
//       else navigate("/employee/profile");
//     } catch (err) {
//       console.error(err);
//       setError("Invalid credentials or server error.");
//     }
//   };

//   return (
//     <div className="max-w-md mx-auto bg-white shadow p-6 rounded-xl mt-10">
//       <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>
//       {error && <div className="text-red-600 mb-4 text-center">{error}</div>}

//       <form onSubmit={handleSubmit} className="space-y-4">
//         <div>
//           <label className="block font-semibold">Username</label>
//           <input
//             name="username"
//             value={form.username}
//             onChange={handleChange}
//             required
//             className="w-full border px-3 py-2 rounded"
//           />
//         </div>
//         <div>
//           <label className="block font-semibold">Password</label>
//           <input
//             name="password"
//             type="password"
//             value={form.password}
//             onChange={handleChange}
//             required
//             className="w-full border px-3 py-2 rounded"
//           />
//         </div>
//         <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
//           Login
//         </button>
//       </form>
//     </div>
//   );
// };

// export default LoginForm;
}