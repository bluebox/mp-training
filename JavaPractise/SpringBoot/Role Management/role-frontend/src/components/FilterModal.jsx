
import React, { useState } from "react";
import { Modal, Button, Input, Select, DatePicker, Tag, Divider } from "antd";
import { CloseCircleOutlined } from "@ant-design/icons";
import "../styles/FilterModal.css";

const { RangePicker } = DatePicker;

const groupedFields = {
  "Basic Info": [
    { key: "userId", label: "User ID", type: "text" },
    { key: "firstName", label: "First Name", type: "text" },
    { key: "lastName", label: "Last Name", type: "text" },
    { key: "username", label: "Username", type: "text" },
    { key: "age", label: "Age", type: "text" },
    { key: "gender", label: "Gender", type: "select", options: ["Male", "Female"] },
    { key: "empId", label: "Emp ID", type: "text" },
    { key: "mobile", label: "Mobile", type: "text" },
  ],
  "Contact": [
    { key: "email", label: "Email", type: "text" },
    { key: "state", label: "State", type: "text" },
    { key: "city", label: "City", type: "text" },
    { key: "status", label: "Status", type: "select", options: ["Active", "Inactive"] },
  ],
  "Dates": [
    { key: "createdRange", label: "Created Range", type: "range" },
    { key: "updatedRange", label: "Updated Range", type: "range" },
  ],
};

export default function FilterModal({ open, onClose, filters, setFilters }) {
  const [activeFields, setActiveFields] = useState(Object.keys(filters));

  const addField = (key) => {
    if (!activeFields.includes(key)) setActiveFields([...activeFields, key]);
  };

  const removeField = (key) => {
    setActiveFields(activeFields.filter((f) => f !== key));
    const next = { ...filters };
    delete next[key];
    setFilters(next);
  };

  return (
    <Modal title="Filter Users" open={open} onCancel={onClose} footer={null} width={700}>
      
      {Object.entries(groupedFields).map(([section, fields]) => (
        <div key={section}>
          <Divider orientation="left">{section}</Divider>
          <div className="field-chips">
            {fields.map((f) => (
              <Tag
                key={f.key}
                className={activeFields.includes(f.key) ? "chip active" : "chip"}
                onClick={() => addField(f.key)}
              >
                {f.label}
              </Tag>
            ))}
          </div>
        </div>
      ))}

      <div className="field-inputs">
        {activeFields.map((key) => {
          const field = Object.values(groupedFields).flat().find((f) => f.key === key);
          if (!field) return null;

          if (field.type === "text") {
            return (
              <div key={key} className="filter-item">
                <span>{field.label}</span>
                <Input
                  value={filters[key] || ""}
                  onChange={(e) => setFilters({ ...filters, [key]: e.target.value })}
                  suffix={<CloseCircleOutlined className="clear-icon" onClick={() => removeField(key)} />}
                />
              </div>
            );
          }

          if (field.type === "select") {
            return (
              <div key={key} className="filter-item">
                <span>{field.label}</span>
                <Select
                  value={filters[key]}
                  onChange={(val) => setFilters({ ...filters, [key]: val })}
                  options={field.options.map((o) => ({ value: o, label: o }))}
                  allowClear
                  style={{ flex: 1 }}
                />
                <CloseCircleOutlined className="clear-icon" onClick={() => removeField(key)} />
              </div>
            );
          }

          if (field.type === "range") {
            return (
              <div key={key} className="filter-item">
                <span>{field.label}</span>
                <RangePicker
                  value={filters[key]}
                  onChange={(val) => {
                    if (val && val[0] && val[1] && val[1].isBefore(val[0], "day")) {
                      val = [val[1], val[0]];
                    }
                    setFilters({ ...filters, [key]: val });
                  }}
                  style={{ flex: 1 }}
                />
                <CloseCircleOutlined className="clear-icon" onClick={() => removeField(key)} />
              </div>
            );
          }
          return null;
        })}
      </div>

      <div className="filter-actions">
        <Button
          onClick={() => {
            setFilters({});
            setActiveFields([]);
          }}
        >
          Clear
        </Button>
        <Button type="primary" onClick={onClose}>
          Apply
        </Button>
      </div>
    </Modal>
  );
}
