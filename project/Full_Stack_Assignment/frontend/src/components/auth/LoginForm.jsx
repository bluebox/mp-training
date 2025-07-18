import React, { useState } from "react";
import { Form, Button, Container, Row, Col, Alert } from "react-bootstrap";
import { useNavigate } from "react-router";
import { useAuth } from "../../context/AuthContext";
import FormField from "../FormField";
import { loginUser } from "../../services/AuthService";

const LoginForm = () => {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [response, setResponse] = useState({ success: null, message: "" });
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      const res = await loginUser(formData,"http://localhost:8081");
      setResponse({ success: true, message: "Login successful!" });

      login(res.data.user, res.data.token);

      setTimeout(() => {
        navigate("/");
      }, 500);
    } catch (error) {
      setResponse({
        success: false,
        message: error.response?.data?.message || "Invalid email or password.",
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
          <Col md={12}>
            <FormField
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
            />
          </Col>
        </Row>

        <Row className="mb-3">
          <Col md={12}>
            <FormField
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
            />
          </Col>
        </Row>

        <Button
          variant="primary"
          type="submit"
          className="mt-3"
          disabled={isLoading}
        >
          {isLoading ? "Logging in..." : "Login"}
        </Button>

        <div className="mt-3">
          Don't have an account? <a href="/signup">Sign up here</a>
        </div>
      </Form>
    </Container>
  );
};

export default LoginForm;
