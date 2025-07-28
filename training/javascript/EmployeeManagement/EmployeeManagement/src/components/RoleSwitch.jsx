import React from "react";
import { useSelector } from "react-redux";

const RoleSwitch = ({ allowedRoles = [], children, fallback = null }) => {
  const { user } = useSelector((state) => state.auth);
  if (!user || !allowedRoles.includes(user.role)) {
    return fallback;
  }
  return <>{children}</>;
};

export default RoleSwitch;
