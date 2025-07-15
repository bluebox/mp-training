import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const GameConfig = () => {
  const navigate = useNavigate();
  const [config, setConfig] = useState({});
  const [numberOfQuestions, setNumberOfQuestions] = useState("");
  const [timer, setTimer] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  // useEffect(() => {
  //   if (config.noQuestions !== undefined) setNumberOfQuestions(config.noQuestions.toString());
  //   if (config.timeAllocated !== undefined) setTimer(config.timeAllocated.toString());
  // }, [config]);

  useEffect(() => {
    if (success) {
      const timer = setTimeout(() => setSuccess(""), 5000);
      return () => clearTimeout(timer);
    }
  }, [success]);

  useEffect(() => {
    if (error) {
      const timer = setTimeout(() => setError(""), 5000);
      return () => clearTimeout(timer);
    }
  }, [error]);

   const handleNumberChange = (e) => {
    const value = e.target.value;
    const numberPattern = /^[0-9]+$/; 
    if (value==="" || numberPattern.test(value)) {
      setNumberOfQuestions(value); 
    }
  };

  const handleTimerChange = (e) => {
    const value = e.target.value;
    const numberPattern = /^[0-9]+$/;
    if (value==="" || numberPattern.test(value)) {
      setTimer(value); 
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    const parsedQuestions = parseInt(numberOfQuestions, 10);
    const parsedTimer = parseInt(timer, 10);

    const updatedConfig = {
      noQuestions: parsedQuestions,
      timeAllocated: parsedTimer,
      modifiedBy: "Admin",
    };

    try {
      await axios.put("http://localhost:8080/api/game-config/set-questions-and-timer", updatedConfig);
      const response = await axios.get("http://localhost:8080/api/game-config/get-game-configuration");
      setConfig(response.data);
      setSuccess("Configuration updated successfully!");
    } catch (err) {
      if (err.response && err.response.data && err.response.data.message) {
        setError(err.response.data.message);
      } else {
        setError("Error saving configuration to backend.");
      }
      console.error(err);
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/game-config/get-game-configuration")
      .then((response) => {
        setConfig(response.data);
      })
      .catch((error) => {
        console.error("Error fetching configuration:", error);
      });
  }, []);

  const handleBackToHome = () => {
    navigate('/admin/home');
  };

  return (
    <div className="container">
      <div className="section">
        <h2 className="main-heading">Game Configurations</h2>
        <table className="table">
          <thead>
            <tr>
              <th>Number of Questions</th>
              <th>Timer (seconds)</th>
              {/* <th>Updated By</th>
              <th>Updated At</th> */}
            </tr>
          </thead>
          <tbody>           
            <tr style={{ color: 'black' }}>
              <td>{config.noQuestions}</td>
              <td>{config.timeAllocated}</td>
              {/* <td>{config.modifiedBy}</td>
              <td>{config.modifiedAt}</td> */}
            </tr>
          </tbody>
        </table>
      </div>

      <div className="section">
        <h4 className="sub-heading">Update Game Configurations</h4>
        {error && <div className="alert error">{error}</div>}
        {success && <div className="alert success">{success}</div>}
        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>Number of Questions</label>
            <input
              type="text"
              maxLength="2"
              minLength="0"
              value={numberOfQuestions}
              onChange={handleNumberChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Timer (seconds)</label>
            <input
            maxLength="5"
            minLength="0"
              type="text"
              value={timer}
              onChange={handleTimerChange}
              required
            />
          </div>
          <button type="submit" className="btn">Save Configuration</button>
        </form>
      </div>
      <a onClick={handleBackToHome} style={{ cursor: 'pointer' }}>Back to home</a>
    </div>
  );
};

export default GameConfig;