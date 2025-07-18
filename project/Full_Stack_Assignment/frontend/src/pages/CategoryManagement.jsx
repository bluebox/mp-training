import React, { useState } from "react";
import { Container, Tabs, Tab } from "react-bootstrap";
import CreateCategoryRequestForm from "../components/category/CreateCategoryRequestForm";
import ViewCategoryRequests from "../components/category/ViewCategoryRequests";
import ApproveCategoryRequests from "../components/category/ApproveCategoryRequest";
import ViewCategories from "../components/ViewCategories";
import { useAuth } from "../context/AuthContext";

const CategoryManagement = () => {
  const [key, setKey] = useState("create");
  const {  currentUser } = useAuth();

  return (
    <Container className="mt-4">
      <h2>Category Management</h2>
      <Tabs
        id="category-tabs"
        activeKey={key}
        onSelect={(k) => setKey(k)}
        className="mb-3"
        justify
      >
        {currentUser?.role === "ROLE_CATEGORY_EXE" && (
        <Tab eventKey="create" title="Create Request">
          <CreateCategoryRequestForm />
        </Tab>
        )}

        <Tab eventKey="viewRequests" title="View Requests">
          <ViewCategoryRequests />
        </Tab>

        {currentUser?.role === "ROLE_MANAGER" && (
        <Tab eventKey="approveRequests" title="Approve Requests">
          <ApproveCategoryRequests />
        </Tab>
        )}

        <Tab eventKey="viewCategories" title="View Categories">
          <ViewCategories />
        </Tab>
      </Tabs>
    </Container>
  );
};

export default CategoryManagement;
