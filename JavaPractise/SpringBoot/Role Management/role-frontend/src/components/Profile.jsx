import React from "react";
import { useAuth } from "../context/AuthContext";

export default function Profile() {
  const { user } = useAuth();

  if (!user) {
    return (
      <div>
        <h2>Not logged in</h2>
        <p>Please login to view your profile.</p>
      </div>
    );
  }

  return (
    <div>
      <h2>Profile</h2>
      <table border="1" cellPadding="6" className="user-table">
        <tbody>
          <tr>
            <td>User ID</td>
            <td>{user.id}</td>
          </tr>
          <tr>
            <td>First Name</td>
            <td>{user.firstName}</td>
          </tr>
          <tr>
            <td>Last Name</td>
            <td>{user.lastName}</td>
          </tr>
          <tr>
            <td>Username</td>
            <td>{user.username}</td>
          </tr>
          <tr>
            <td>Roles</td>
            <td>
              {user.roles && user.roles.length > 0
                ? user.roles.join(", ")
                : "No roles assigned"}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}
