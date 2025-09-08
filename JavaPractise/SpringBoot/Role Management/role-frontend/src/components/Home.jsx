import React from "react";
import { useAuth } from "../context/AuthContext";

export default function Home() {
  const { user } = useAuth();

  return (
    <div>
      {!user ? (
        <>
          <h2>Welcome</h2>
          <p>Please Enroll or Login to continue.</p>
        </>
      ) : (
        <>
          <h2>Hello, {user.firstName || user.username}</h2>
        </>
      )}
    </div>
  );
}
