import React from "react";
import { Outlet } from "react-router-dom";
import ReportsHeader from "./ReportsHeader";

const ReportsHome = () => {
  return (
    <div>
      <ReportsHeader /> 
      <div style={{ marginTop: 0 }}>
        <Outlet /> 
      </div>
    </div>
  );
};

export default ReportsHome;
