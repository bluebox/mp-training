import React, { useState } from "react";
import { Form, Button, Container, Row, Col, Alert } from "react-bootstrap";
import { useNavigate } from "react-router";
import { registerUser } from "../../services/AuthService";
import FormField from "../FormField";

const SignupForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    fullName: "",
    email: "",
    phone: "",
    gender: "",
    age: "",
    password: "",
    confirmPassword: "",
  });

  const [response, setResponse] = useState({ success: null, message: "" });
  const [isLoading, setIsLoading] = useState(false);
  const [passwordError, setPasswordError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));

    if (
      (name === "confirmPassword" || name === "password") &&
      formData.confirmPassword
    ) {
      if (
        (name === "password" && value !== formData.confirmPassword) ||
        (name === "confirmPassword" && value !== formData.password)
      ) {
        setPasswordError("Passwords do not match");
      } else {
        setPasswordError("");
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }

    setIsLoading(true);

    try {
      await registerUser({
        username: formData.username,
        fullName: formData.fullName,
        email: formData.email,
        phone: formData.phone,
        gender: formData.gender,
        age: parseInt(formData.age, 10),
        password: formData.password,
      }, "http://localhost:8081");

      setResponse({
        success: true,
        message: "Registration successful! You can now login.",
      });

      setTimeout(() => {
        navigate("/login");
      }, 2000);
    } catch (error) {
      setResponse({
        success: false,
        message:
          error.response?.data?.message ||
          "Registration failed. Please try again.",
      });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Container className="mt-5">
      {response.success !== null && (
        <Alert variant={response.success ? "success" : "danger"}>
          {response.message}
        </Alert>
      )}

      <Form onSubmit={handleSubmit}>
        <Row className="mb-3">
          <Col md={6}>
            <FormField
              label="Username"
              name="username"
              type="text"
              value={formData.username}
              onChange={handleChange}
              required
            />
          </Col>
          <Col md={6}>
            <FormField
              label="Full Name"
              name="fullName"
              type="text"
              value={formData.fullName}
              onChange={handleChange}
              required
            />
          </Col>
        </Row>

        <Row className="mb-3">
          <Col md={6}>
            <FormField
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </Col>
          <Col md={6}>
            <FormField
              label="Phone"
              name="phone"
              type="text"
              value={formData.phone}
              onChange={handleChange}
              required
            />
          </Col>
        </Row>

        <Row className="mb-3">
          <Col md={6}>
            <Form.Group controlId="gender">
              <Form.Label>Gender</Form.Label>
              <Form.Select
                name="gender"
                value={formData.gender}
                onChange={handleChange}
                required
              >
                <option value="">Select Gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
                <option value="O">Other</option>
              </Form.Select>
            </Form.Group>
          </Col>
          <Col md={6}>
            <FormField
              label="Age"
              name="age"
              type="number"
              value={formData.age}
              onChange={handleChange}
              required
            />
          </Col>
        </Row>

        <Row className="mb-3">
          <Col md={6}>
            <FormField
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </Col>
          <Col md={6}>
            <FormField
              label="Confirm Password"
              name="confirmPassword"
              type="password"
              value={formData.confirmPassword}
              onChange={handleChange}
              required
            />
            {passwordError && (
              <div className="text-danger mt-1">{passwordError}</div>
            )}
          </Col>
        </Row>

        <Button
          variant="primary"
          type="submit"
          className="mt-3"
          disabled={isLoading || passwordError}
        >
          {isLoading ? "Signing up..." : "Sign Up"}
        </Button>

        <div className="mt-3">
          Already have an account? <a href="/login">Login here</a>
        </div>
      </Form>
    </Container>
  );
};

export default SignupForm;
