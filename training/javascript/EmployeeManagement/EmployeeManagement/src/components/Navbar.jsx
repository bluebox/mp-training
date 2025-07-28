import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../../redux/authSlice";
import { Link } from "react-router-dom";

const Navbar = () => {
  const dispatch = useDispatch();
  const { isAuthenticated, user } = useSelector((state) => state.auth);

  const handleLogout = () => dispatch(logout());

  const role = user?.role;

  const roleLinks = {
    employee: [
      { label: "My Profile", path: "/profile" },
    ],
    manager: [
      { label: "My Profile", path: "/profile" },
      { label: "Manage Teams", path: "/manage-teams" },
    ],
    hr: [
      { label: "My Profile", path: "/profile" },
      { label: "Manage Employees", path: "/manage-employees" },
      { label: "Manage Salary", path: "/manage-salary" },
      { label: "Designations", path: "/designations" },
    ],
    ceo: [
      { label: "All Employees", path: "/employees" },
      { label: "Departments", path: "/departments" },
      { label: "Designations", path: "/designations" },
    ],
  };

  const sharedLinks = [
    ...(roleLinks[role] || []),
    { label: "Logout", path: "/", action: handleLogout },
  ];

  return (
    <nav className="bg-gray-900 text-white p-4 shadow flex justify-between items-center">
      <h1 className="text-xl font-bold">Employee Dashboard</h1>
      {isAuthenticated && (
        <div className="space-x-4">
          {sharedLinks.map((link) =>
            link.action ? (
              <button
                key={link.label}
                onClick={link.action}
                className="hover:text-yellow-400"
              >
                {link.label}
              </button>
            ) : (
              <Link key={link.label} to={link.path} className="hover:text-yellow-400">
                {link.label}
              </Link>
            )
          )}
        </div>
      )}
    </nav>
  );
};

export default Navbar;
