import React, { useState, useEffect } from 'react';
import { useCookies } from 'react-cookie';
import axios from "axios";

function Insurance() {
  const [Data, setData] = useState([]); 
  const [message, setmessage] = useState("");
  const [cookies] = useCookies(['userData']);
  const [add, setAdd] = useState(false);
  const [update, setUpdate] = useState(false);

  const now = new Date();
  const isoDate = now.toISOString().slice(0, 10);
  let today = isoDate;

  const [form, setform] = useState({
    insuranceId: 0,
    periodLength: 0,
    insuranceAmount: 0,
    vehicleType: "",
    monthlyEMI: 0,
    insuranceStatus: "ACTIVE",
    validPeriod: 0,
    ncb: 0,
    modifiedBy: null,
    modifiedDate: null,
    createdBy: cookies.userData?.username?.userId,
    createdDate: today
  });

  // Fetch insurance data
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          'http://localhost:8080/api/insurance/getInsurances',
          {
            headers: {
              'Authorization': `Bearer ${cookies.userData.token}`
            }
          }
        );
        console.log("Fetched data:", response.data); 
        setData(Array.isArray(response.data) ? response.data : []);
      } catch (err) {
        console.error("Fetch error:", err);
        alert("Failed to load insurances");
        setmessage(err.message);
      }
    };
    fetchData();
  }, [cookies.userData.token]);

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setform((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  function handleAdd() {
    setAdd(!add);
    setUpdate(false);
  }

  function handleUpdate(rowData) {
    setform({
      insuranceId: rowData.insuranceId,
      periodLength: rowData.periodLength,
      insuranceAmount: rowData.insuranceAmount,
      vehicleType: rowData.vehicleType,
      monthlyEMI: rowData.monthlyEMI,
      insuranceStatus: rowData.insuranceStatus,
      validPeriod: rowData.validPeriod,
      ncb: rowData.ncb,
      modifiedBy: cookies.userData?.username?.userId,
      modifiedDate: today,
      createdBy: rowData.createdBy,
      createdDate: rowData.createdDate,
    });
    setUpdate(true); 
    setAdd(false);
  }

  // Add insurance
  async function handleAddSubmit(e) {
    e.preventDefault();

    const insurance = {
      insuranceId: 0, // new record
      periodLength: Number(form.periodLength),
      insuranceAmount: Number(form.insuranceAmount),
      vehicleType: form.vehicleType,
      monthlyEMI: Number(form.monthlyEMI),
      insuranceStatus: form.insuranceStatus,
      validPeriod: Number(form.validPeriod),
      ncb: Number(form.ncb),
      modifiedBy: null,
      modifiedDate: null,
      createdBy: Number(form.createdBy),
      createdDate: form.createdDate
    };

       try {
      const response = await axios.post(
        'http://localhost:8080/api/insurance/Createinsurance',
        payload,
        {
          headers: {
            'Authorization': `Bearer ${cookies.userData.token}`,
            "Content-Type": "application/json"
          }
        }
      );
      alert(response.data);

      // Refresh data
      const refreshed = await axios.get("http://localhost:8080/api/insurance/getInsurances", {
        headers: { 'Authorization': `Bearer ${cookies.userData.token}` }
      });
      setData(Array.isArray(refreshed.data) ? refreshed.data : []);
      setAdd(false); 
    } catch (err) {
      console.error(err);
      alert("Failed to add insurance");
      setmessage(err.message);
    }
  }

  // Update insurance
  async function handleUpdateSubmit(e) {
    e.preventDefault();

    const insurance = {
      insuranceId: Number(form.insuranceId),
      periodLength: Number(form.periodLength),
      insuranceAmount: Number(form.insuranceAmount),
      vehicleType: form.vehicleType,
      monthlyEMI: Number(form.monthlyEMI),
      insuranceStatus: form.insuranceStatus,
      validPeriod: Number(form.validPeriod),
      ncb: Number(form.ncb),
      modifiedBy: Number(form.modifiedBy),
      modifiedDate: form.modifiedDate,
      createdBy: Number(form.createdBy),
      createdDate: form.createdDate
    };
    try {
      const response = await axios.post(
        'http://localhost:8080/api/insurance/UpdatePolicy',
        payload,
        {
          headers: {
            'Authorization': `Bearer ${cookies.userData.token}`,
            "Content-Type": "application/json"
          }
        }
      );
      alert(response.data);

      const refreshed = await axios.get("http://localhost:8080/api/insurance/getInsurances", {
        headers: { 'Authorization': `Bearer ${cookies.userData.token}` }
      });
      setData(Array.isArray(refreshed.data) ? refreshed.data : []);
      setUpdate(false);
    } catch (err) {
      console.error(err);
      alert("Failed to update insurance");
      setmessage(err.message);
    }
  }

  // Change status (active/inactive)
  async function handleInactive(e) {
    e.preventDefault();
    alert("!! Changing status of policy !!");
    const sentid = e.target.getAttribute("attr");

    try {
      const response = await axios.post(
        `http://localhost:8080/api/insurance/DeactivateInsurancePolicy/${sentid}`,
        {},
        {
          headers: {
            'Authorization': `Bearer ${cookies.userData.token}`,
            "Content-Type": "application/json"
          }
        }
      );
      alert(response.data);

      const refreshed = await axios.get("http://localhost:8080/api/insurance/getInsurances", {
        headers: { 'Authorization': `Bearer ${cookies.userData.token}` }
      });
      setData(Array.isArray(refreshed.data) ? refreshed.data : []);
    } catch (err) {
      console.error(err);
      alert("Failed to deactivate insurance");
      setmessage(err.message);
    }
  }

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Insurance Policies</h2>

      {Array.isArray(Data) && Data.length > 0 ? (
        <table style={styles.table}>
          <thead>
            <tr>
              <th>InsuranceId</th>
              <th>Insurance</th>
              <th>ValidityPeriod</th>
              <th>Vehicle type</th>
              <th>Insurance Status</th>
              <th>InsuranceAmount</th>
              <th>EMI</th>
              <th>NCB%</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {Data.map((i) => (
              <tr key={i.insuranceId}>
                <td>{i.insuranceId}</td>
                <td>{i.periodLength} months</td>
                <td>{i.validPeriod} years</td>
                <td>{i.vehicleType}</td>
                <td>
                  <select
                    style={styles.select}
                    value={i.insuranceStatus}
                    attr={i.insuranceId}
                    onChange={handleInactive}
                  >
                    <option value="">-- select --</option>
                    {i.insuranceStatus === "INACTIVE"
                      ? <option value="INACTIVE">Inactive</option>
                      : <option value="ACTIVE">Active</option>}
                  </select>
                </td>
                <td>{i.insuranceAmount}</td>
                <td>{i.monthlyEMI}</td>
                <td>{i.ncb}%</td>
                <td>
                  <button style={styles.button} onClick={() => handleUpdate(i)}>Update</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading data or no data available yet...</p>
      )}

      <button style={styles.addButton} onClick={handleAdd}>+ Add Insurance</button>

      {(add || update) && (
        <form
          style={styles.form}
          onSubmit={add ? handleAddSubmit : handleUpdateSubmit}
        >
          {add && <h4>Create Insurance Form</h4>}
          {update && <h4>Update Insurance Form</h4>}

          {update && (
            <input
              style={styles.input}
              onChange={handleChange}
              type="number"
              value={form.insuranceId}
              name="insuranceId"
              placeholder="Insurance ID"
              required
            />
          )}

          <input
            style={styles.input}
            type="number"
            onChange={handleChange}
            value={form.periodLength}
            name="periodLength"
            placeholder="Enter period length (months)"
            required
          />
          <input
            style={styles.input}
            type="number"
            onChange={handleChange}
            value={form.insuranceAmount}
            name="insuranceAmount"
            placeholder="Enter insurance amount"
            required
          />
          <select
            style={styles.input}
            value={form.vehicleType}
            name="vehicleType"
            onChange={handleChange}
            required
          >
            <option value="">-- select vehicle type --</option>
            <option value="TWO">Two Wheeler</option>
            <option value="THREE">Three Wheeler</option>
            <option value="FOUR">Four Wheeler</option>
            <option value="EIGHT">Eight Wheeler</option>
          </select>

          <input
            style={styles.input}
            type="number"
            onChange={handleChange}
            value={form.monthlyEMI}
            name="monthlyEMI"
            placeholder="Enter monthly EMI"
            required
          />
          <input
            style={styles.input}
            type="number"
            onChange={handleChange}
            value={form.validPeriod}
            name="validPeriod"
            placeholder="Enter validity (years)"
            required
          />
          <input
            style={styles.input}
            type="number"
            onChange={handleChange}
            value={form.ncb}
            name="ncb"
            placeholder="Enter NCB percentage"
            required
          />

          <input
            style={styles.submitButton}
            type="submit"
            value={add ? "Add Insurance" : "Update Insurance"}
          />
        </form>
      )}
    </div>
  );
}

const styles = {
  container: { padding: "20px", fontFamily: "Arial, sans-serif", backgroundColor: "#f9f9f9", minHeight: "100vh" },
  title: { textAlign: "center", marginBottom: "20px", color: "#333" },
  table: { width: "100%", borderCollapse: "collapse", marginBottom: "20px", backgroundColor: "#fff", borderRadius: "10px", overflow: "hidden", boxShadow: "0px 4px 15px rgba(0,0,0,0.1)" },
  select: { padding: "5px", borderRadius: "6px", border: "1px solid #ccc" },
  button: { padding: "6px 12px", border: "none", borderRadius: "8px", backgroundColor: "cornflowerblue", color: "#fff", cursor: "pointer" },
  addButton: { padding: "10px 20px", backgroundColor: "green", color: "white", border: "none", borderRadius: "10px", cursor: "pointer", display: "block", margin: "10px auto" },
  form: { backgroundColor: "#fff", padding: "20px", borderRadius: "15px", boxShadow: "0px 8px 25px rgba(0,0,0,0.2)", maxWidth: "500px", margin: "20px auto", display: "flex", flexDirection: "column" },
  input: { padding: "10px", margin: "8px 0", borderRadius: "8px", border: "1px solid #ccc", fontSize: "14px" },
  submitButton: { padding: "12px", backgroundColor: "cornflowerblue", color: "#fff", border: "none", borderRadius: "8px", fontSize: "16px", fontWeight: "bold", cursor: "pointer", marginTop: "10px" }
};

export default Insurance;

