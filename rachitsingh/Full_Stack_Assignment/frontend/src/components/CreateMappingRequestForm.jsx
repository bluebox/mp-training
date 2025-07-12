import React, { useState } from "react";
import { Form, Button, Container, Row, Col, Alert } from "react-bootstrap";
import FormField from "../components/FormField";
import { createMappingRequest } from "../services/CategoryMappingService";

const CreateMappingRequestForm = () => {
  const [formData, setFormData] = useState({
    categoryId: "",
    productId: "",
    requestedBy: ""
  });

  const [response, setResponse] = useState({ success: null, message: "" });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createMappingRequest(formData);
      setResponse({ success: true, message: "Mapping request submitted successfully." });
      setFormData({ categoryId: "", productId: "", requestedBy: "" });
    } catch (error) {
      setResponse({
        success: false,
        message: error.response?.data?.message || "Something went wrong."
      });
    }
  };

  return (
    <Container className="mt-5">
      {response.success !== null && (
        <Alert variant={response.success ? "success" : "danger"}>{response.message}</Alert>
      )}

      <Form onSubmit={handleSubmit}>
        
          <Col md={4}>
            <FormField
              label="Category ID"
              name="categoryId"
              type="number"
              value={formData.categoryId}
              onChange={handleChange}
            />
          </Col>

          <Col md={4}>
            <FormField
              label="Product ID"
              name="productId"
              type="number"
              value={formData.productId}
              onChange={handleChange}
            />
          </Col>

          <Col md={4}>
            <FormField
              label="Requested By (User ID)"
              name="requestedBy"
              type="number"
              value={formData.requestedBy}
              onChange={handleChange}
            />
          </Col>


        <Button variant="primary" type="submit" className="mt-3">
          Submit Request
        </Button>
      </Form>
    </Container>
  );
};

export default CreateMappingRequestForm;
