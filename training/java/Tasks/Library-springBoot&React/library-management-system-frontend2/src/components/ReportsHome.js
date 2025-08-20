import React from "react";
import { Outlet } from "react-router-dom";
import ReportsHeader from "./ReportsHeader";

const ReportsHome = () => {
  return (
    <div style={{ padding: "20px" }}>
      <ReportsHeader /> {/* Always visible */}
      <div style={{ marginTop: 0 }}>
        <Outlet /> {/* renders the selected report */}
      </div>
    </div>
  );
};

export default ReportsHome;
