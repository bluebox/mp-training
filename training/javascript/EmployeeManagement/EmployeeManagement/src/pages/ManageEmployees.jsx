

// import React, { useEffect, useState } from "react";
// import { useSelector } from "react-redux";
// import axiosInstance from "../api/axiosInstance";
// import { selectUser } from "../features/feature/auth/authSlice";
// import { CgTrash } from "react-icons/cg";
// import { fetchEmployees } from "../../helpers";

// const ManageEmployees = () => {
//   const user = useSelector(selectUser);
//   const [employees, setEmployees] = useState([]);
//   const [form, setForm] = useState({
//     emp_id: "",
//     username: "",
//     password: "",
//     emp_name: "",
//     dob: "",
//     dept: "",
//     role: "employee",
//     is_active: "True",
//   });

//   const handleAdd = async () => {
//     try {
//       await axiosInstance.post("/employee/create/", form);
//       await setEmployees(await fetchEmployees());
//     } catch (err) {
//       alert(err.response?.data?.message || "Failed to add employee");
//     }
//   };

//   const handleDelete = async (emp_id) => {
//     try {
//       await axiosInstance.delete("/employee/delete/", { data: { emp_id } });
//       await setEmployees( await fetchEmployees());
//     } catch (err) {
//       alert("Failed to delete employee");
//     }
//   };

//   useEffect(() => {
//     const restore_employees= async ()=>{

//       await setEmployees(await fetchEmployees());

//     }
//     restore_employees()
//   }, []);

//   const canManage = user?.role === "hr" || user?.role === "ceo";

//   return (
//     <div className="p-4">
//       <h2 className="text-xl font-semibold mb-4">Manage Employees</h2>

//       {canManage && (
//         <div className="mb-4 grid grid-cols-2 gap-2">
// {Object.keys(form).map((key) => {
//   if (key === "role") {
//     return (
//       <select
//         key={key}
//         value={form[key]}
//         onChange={(e) => setForm({ ...form, [key]: e.target.value })}
//         className="border p-2 rounded"
//       >
//         <option value="employee">Employee</option>
//         <option value="manager">Manager</option>
//         <option value="hr">HR</option>
//         <option value="ceo">CEO</option>
//       </select>
//     );
//   }
//   else if(key=="is_active"){
//       return (
//       <select
//         key={key}
//         value={form[key]}
//         onChange={(e) => setForm({ ...form, [key]: e.target.value })}
//         className="border p-2 rounded"
//       >
//         <option value="True">Active</option>
//         <option value="False">Inactive</option>
//       </select>
//     );

//   }
//   return (
//     <input
//       key={key}
//       type={key === "dob" ? "date" : "text"}
//       placeholder={key}
//       value={form[key]}
//       onChange={(e) => setForm({ ...form, [key]: e.target.value })}
//       className="border p-2 rounded"
//     />
//   );
// })}
//           <button onClick={handleAdd} className="col-span-2 bg-green-600 text-white py-2 rounded">
//             Add Employee
//           </button>
//         </div>
//       )}

//       <ul className="space-y-2">
//         {employees.map((e) => (
//           <li
//             key={e.emp_id}
//             className="flex justify-between items-center border-b py-2"
//           >
//             <span>
//               {e.emp_id} - {e.emp_name}
//             </span>
//             {canManage && (
//               <button
//                 onClick={() => handleDelete(e.emp_id)}
//                 className="text-red-500"
//               >
//                 <CgTrash />
//               </button>
//             )}
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default ManageEmployees;

import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axiosInstance from "../api/axiosInstance";
import { selectUser } from "../features/feature/auth/authSlice";
import { CgTrash } from "react-icons/cg";
import { fetchEmployees } from "../../helpers";
import { Table, Button } from "antd";

const ManageEmployees = () => {
  const user = useSelector(selectUser);
  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({
    emp_id: "",
    username: "",
    password: "",
    emp_name: "",
    dob: "",
    dept: "",
    role: "employee",
    is_active: "True",
  });

  const canManage = user?.role === "hr" || user?.role === "ceo";

  const handleAdd = async () => {
    try {
      await axiosInstance.post("/employee/create/", form);
      setEmployees(await fetchEmployees());
    } catch (err) {
      alert(err.response?.data?.message || "Failed to add employee");
    }
  };

  const handleDelete = async (emp_id) => {
    try {
      await axiosInstance.delete("/employee/delete/", { data: { emp_id } });
      setEmployees(await fetchEmployees());
    } catch (err) {
      alert("Failed to delete employee");
    }
  };

  useEffect(() => {
    const restoreEmployees = async () => {
      setEmployees(await fetchEmployees());
    };
    restoreEmployees();
  }, []);

  const columns = [
    {
      title: "Employee ID",
      dataIndex: "emp_id",
      key: "emp_id",
    },
    {
      title: "Username",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "Name",
      dataIndex: "emp_name",
      key: "emp_name",
    },
    {
      title: "Date of Birth",
      dataIndex: "dob",
      key: "dob",
    },
    {
      title: "Department",
      dataIndex: "dept",
      key: "dept",
    },
    {
      title: "Role",
      dataIndex: "role",
      key: "role",
    },
    {
      title: "Status",
      dataIndex: "is_active",
      key: "is_active",
      render: (text) => (text === 1 ? "Active" : "Inactive"),
    },
    canManage && {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Button
          danger
          icon={<CgTrash />}
          onClick={() => handleDelete(record.emp_id)}
          size="small"
        />
      ),
    },
  ].filter(Boolean); 

  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold mb-4">Manage Employees</h2>

      {canManage && (
        <div className="mb-4 grid grid-cols-2 gap-2">
          {Object.keys(form).map((key) => {
            if (key === "role") {
              return (
                <select
                  key={key}
                  value={form[key]}
                  onChange={(e) => setForm({ ...form, [key]: e.target.value })}
                  className="border p-2 rounded"
                >
                  <option value="employee">Employee</option>
                  <option value="manager">Manager</option>
                  <option value="hr">HR</option>
                  <option value="ceo">CEO</option>
                </select>
              );
            } else if (key === "is_active") {
              return (
                <select
                  key={key}
                  value={form[key]}
                  onChange={(e) => setForm({ ...form, [key]: e.target.value })}
                  className="border p-2 rounded"
                >
                  <option value="True">Active</option>
                  <option value="False">Inactive</option>
                </select>
              );
            }

            return (
              <input
                key={key}
                type={key === "dob" ? "date" : "text"}
                placeholder={key}
                value={form[key]}
                onChange={(e) => setForm({ ...form, [key]: e.target.value })}
                className="border p-2 rounded"
              />
            );
          })}
          <button
            onClick={handleAdd}
            className="col-span-2 bg-green-600 text-white py-2 rounded"
          >
            Add Employee
          </button>
        </div>
      )}

      <Table
        columns={columns}
        dataSource={employees}
        rowKey="emp_id"
        pagination={{ pageSize: 5 }}
        bordered
      />
    </div>
  );
};

export default ManageEmployees;
