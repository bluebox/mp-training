import React from "react";
import { Form } from "react-bootstrap";

const FormField = ({ label, name, type, value, onChange, required = true }) => {
  return (
    <Form.Group controlId={name}>
      <Form.Label>{label}</Form.Label>
      <Form.Control
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        required={required}
      />
    </Form.Group>
  );
};

export default FormField;
