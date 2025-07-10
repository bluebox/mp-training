import React from "react";
import { Routes, Route } from "react-router-dom";
import UpdateStatus from "./Components/UpdateStatus";
import FullOrderApproval from "./Components/FullOrderApproval";
function App() {
  return (
    <Routes>
      <Route path="/" element={<UpdateStatus />} />
      <Route path="/order/:orderId" element={<FullOrderApproval />} />
    </Routes>
  );
}

export default App;
