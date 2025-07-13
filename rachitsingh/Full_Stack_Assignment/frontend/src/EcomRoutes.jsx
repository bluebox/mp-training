import React, { Suspense } from "react";
import { Route, Routes } from "react-router";
import CreateMappingRequest from "./pages/CreateMappingRequest";
import OrdersPage from "./modules/orders/pages/OrdersPage";
const Home = React.lazy(() => import("./pages/Home"));
const ViewMappingRequests = React.lazy(() =>
  import("./pages/ViewMappingRequests")
);

const EcomRoutes = () => {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/create-mapping-request"
          element={<CreateMappingRequest />}
        />
        <Route
          path="/view-mapping-requests"
          element={<ViewMappingRequests />}
        />
        <Route path="/orders" element={<OrdersPage />} />
      </Routes>
    </Suspense>
  );
};

export default EcomRoutes;
