import React, { useState } from "react";
import { Container, Tabs, Tab } from "react-bootstrap";
import CreateMappingRequestForm from "../components/categorymapping/CreateMappingRequestForm";
import ViewCategoryMappingRequests from "./ViewCategoryMappingRequests";
import { useAuth } from "../context/AuthContext";

const CategoryMappingManagement = () => {
  const [key, setKey] = useState("create");
  const { currentUser } = useAuth();

  return (
    <Container className="mt-4">
      <h2>Category Mapping Management</h2>
      <Tabs
        id="category-mapping-tabs"
        activeKey={key}
        onSelect={(k) => setKey(k)}
        className="mb-3"
        justify
      >
        {currentUser?.role === "ROLE_CATEGORY_EXE" && (
          <Tab eventKey="create" title="Create Mapping Request">
            <CreateMappingRequestForm />
          </Tab>
        )}

        <Tab eventKey="viewRequests" title="View Mapping Requests">
          <ViewCategoryMappingRequests />
        </Tab>
      </Tabs>
    </Container>
  );
};

export default CategoryMappingManagement;
