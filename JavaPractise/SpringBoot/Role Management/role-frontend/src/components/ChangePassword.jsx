import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function ChangePassword() {
  const [newPassword, setNewPassword] = useState("");
  const { user, login, isLoggedIn } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!isLoggedIn()) {
      alert("You have to be logged in to change password.");
      navigate("/login");
      return;
    }

    try {
      const authHeader = "Basic " + btoa(`${user.username}:${user.password}`);
      const params = new URLSearchParams();
      params.append("userId", user.id);
      params.append("newPassword", newPassword);
      const res = await fetch("http://localhost:8080/users/change-password", {
        method: "POST",
        headers: { 
          "Content-Type": "application/json",
          "Authorization": authHeader
        },
        body: JSON.stringify({
          userId: user.id,
          newPassword: newPassword,
        }),
      });

      const isJson = (res.headers.get("content-type") || "").includes("application/json");
      const payload = isJson
        ? await res.json()
        : { status: res.ok ? "success" : "error", message: await res.text() };

      if (!res.ok || payload.status === "error") {
        alert(payload.message || `Failed (${res.status})`);
        return;
      }

      login({ ...user, password: newPassword });

      alert("Password changed successfully");
      navigate("/profile");
    } catch (err) {
      console.error(err);
      alert("Something went wrong while changing password");
    }
  };

  return (
    <div>
      <h2>Change Password</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Employee ID: </label>
          <input type="text" value={user?.id || ""} readOnly />
        </div>

        <div>
          <label>New Password: </label>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
        </div>

        <button type="submit">Update Password</button>
      </form>
    </div>
  );
}
