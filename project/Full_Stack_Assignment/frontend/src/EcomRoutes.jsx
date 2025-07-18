import React, { Suspense } from "react";
import { Route, Routes } from "react-router";
import CreateCategoryMappingRequest from "./pages/CreateCategoryMappingRequest";
import OrdersPage from "./pages/OrdersPage";
import CategoryManagementPage from "./pages/CategoryManagement";
import CreateCategoryRequest from "./components/category/CreateCategoryRequest";
import ProtectedRoute from "./components/ProtectedRoute";
import CategoryMappingManagement from "./pages/CategoryMappingManagement";
import ProductRequestDashboard from "./pages/ProductRequestDashBoard";
import CartPage from "./pages/CartsPage";

const Home = React.lazy(() => import("./pages/Home"));
const ViewCategoryMappingRequests = React.lazy(() =>
  import("./pages/ViewCategoryMappingRequests")
);
const Login = React.lazy(() => import("./pages/Login"));
const Signup = React.lazy(() => import("./pages/Signup"));
const ProductApprovalDashboard = React.lazy(() =>
  import("./pages/ProductApprovalDashboard")
);
const ViewCategoryRequests = React.lazy(() =>
  import("./components/category/ViewCategoryRequests")
);
const ViewCategories = React.lazy(() => import("./components/ViewCategories"));
const ApproveCategoryRequests = React.lazy(() =>
  import("./components/category/ApproveCategoryRequest")
);

const EcomRoutes = () => {
  return (
    <Suspense fallback={<div className="text-white bg-dark">Loading...</div>}>
      <Routes>
        {/* Public Routes */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        {/* <Route path="/orders" element={<OrdersPage />} />
        <Route path="/product-approval" element={<ProductApprovalDashboard />} /> */}
        {/* Protected Routes for all authenticated users */}
        <Route element={<ProtectedRoute />}>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<CartPage />} />
          <Route path="/orders" element={<OrdersPage />} />
        </Route>

        {/* Protected Routes for Admin/Manager roles only */}
        <Route
          element={
            <ProtectedRoute
              allowedRoles={["ROLE_CATEGORY_EXE", "ROLE_MANAGER"]}
            />
          }
        >
          <Route
            path="category/dashboard"
            element={<CategoryManagementPage />}
          />

          {/*routes for mapping requests */}


          <Route
            path="categorymapping/dashboard"
            element={<CategoryMappingManagement />}
          />
        </Route>

        <Route element={<ProtectedRoute allowedRoles={["ROLE_MANAGER"]} />}>
          <Route path="/product-approval" element={<ProductApprovalDashboard />} />
        </Route>
         <Route element={<ProtectedRoute allowedRoles={["ROLE_PRODUCT_EXE"]} />}>
          <Route path="/product-requests" element={<ProductRequestDashboard />} />
        </Route>
      </Routes>
    </Suspense>
  );
};

export default EcomRoutes;
