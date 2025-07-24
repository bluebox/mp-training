import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Configuration.css";


const Configurations = ({ config, setConfig }) => {
  const [localConfig, setLocalConfig] = useState(config);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setLocalConfig({
      ...localConfig,
      [name]: type === "checkbox" ? checked : parseInt(value)
    });
  };

const handleSave = () => {
  setConfig(localConfig);
  localStorage.setItem("config", JSON.stringify(localConfig)); 
  navigate("/");
};

  return (
    <div>
      <h1>Configurations</h1>
      <label>
        Max Records to Show:
        <input
          type="number"
          name="maxRecords"
          value={localConfig.maxRecords}
          onChange={handleChange}
        />
      </label>
      <label>
        Unique by Phone:
        <input
          type="checkbox"
          name="uniquePhone"
          checked={localConfig.uniquePhone}
          onChange={handleChange}
        />
      </label>
      <button onClick={handleSave}>Save</button>
    </div>
  );
};

export default Configurations;