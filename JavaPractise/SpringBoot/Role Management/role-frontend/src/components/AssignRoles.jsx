import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
export default function AssignRoles() {
  const { getAuthHeader } = useAuth();
  const { id } = useParams();
  const navigate = useNavigate();

  const [availableRoles, setAvailableRoles] = useState([]);
  const [assignedActiveRoles, setAssignedActiveRoles] = useState([]);
  const [selectedRoles, setSelectedRoles] = useState([]);
  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [cities, setCities] = useState([]);
  const [country, setCountry] = useState("");
  const [state, setState] = useState("");
  const [city, setCity] = useState("");
  const API_KEY = "NEpuUkZaTTFuTjNTTE5xeEpqNWJpamlyMGYxZGd3TlRDamVoWmNLMw==";
  const load = async () => {
  try {
    const [availRes, assignedRes] = await Promise.all([
      fetch(`http://localhost:8080/api/users/${id}/available-roles`, {
        headers: { "Content-Type": "application/json", ...getAuthHeader() },
      }),
      fetch(`http://localhost:8080/api/users/${id}/assigned-roles`, {
        headers: { "Content-Type": "application/json", ...getAuthHeader() },
      }),
    ]);

    if (!availRes.ok) throw new Error(await availRes.text());
    if (!assignedRes.ok) throw new Error(await assignedRes.text());

    const availJson = await availRes.json();
    const assignedJson = await assignedRes.json();

    setAvailableRoles(Array.isArray(availJson.data) ? availJson.data : []);
    setAssignedActiveRoles(Array.isArray(assignedJson.data) ? assignedJson.data : []);
    setSelectedRoles([]);
  } catch (err) {
    console.error(err);
    alert(err.message || "Error loading roles");
  }
};

  useEffect(() => {
    load();
  }, [id]);

  useEffect(() => {
    fetch("https://api.countrystatecity.in/v1/countries", {
      headers: { "X-CSCAPI-KEY": API_KEY },
    })
      .then((res) => res.json())
      .then((data) => setCountries(data))
      .catch((err) => console.error("Error loading countries:", err));
  }, []);

  useEffect(() => {
    if (!country) return;
    const selectedCountry = countries.find((c) => c.name === country);
    if (!selectedCountry) return;
    fetch(
      `https://api.countrystatecity.in/v1/countries/${selectedCountry.iso2}/states`,
      {
        headers: { "X-CSCAPI-KEY": API_KEY },
      }
    )
      .then((res) => res.json())
      .then((data) => setStates(data))
      .catch((err) => console.error("Error loading states:", err));
  }, [country]);
  useEffect(() => {
    if (!state) return;
    const selectedCountry = countries.find((c) => c.name === country);
    const selectedState = states.find((s) => s.name === state);
    if (!selectedCountry || !selectedState) return;
    fetch(
      `https://api.countrystatecity.in/v1/countries/${selectedCountry.iso2}/states/${selectedState.iso2}/cities`,
      {
        headers: { "X-CSCAPI-KEY": API_KEY },
      }
    )
      .then((res) => res.json())
      .then((data) => setCities(data))
      .catch((err) => console.error("Error loading cities:", err));
  }, [state]);
  const toggleLeft = (role) => {
    setSelectedRoles((prev) =>{
      // if (!prev.includes(role) && (assignedActiveRoles.length + prev.length) >= 3) {
      //   alert("Cannot possible to assign more than 3 roles .");
      //   return prev;
      // }
      return prev.includes(role) ? prev.filter((r) => r !== role) : [...prev, role]
    }
    );
  };
  const toggleRightSelected = (role) => {
    setSelectedRoles((prev) => prev.filter((r) => r !== role));
  };
  const handleSave = async () => {
    if (selectedRoles.length === 0) {
      alert("No new roles selected!");
      return;
    }
    if (!country || !state || !city) {
      alert("Please select Country, State and City.");
      return;
    }
    try {
        const payload = selectedRoles.map((role) => ({
        role,
        country,
        state,
        city,
    }));

      const res = await fetch(`http://localhost:8080/api/users/${id}/assign-roles`, {
        method: "POST",
        headers: { "Content-Type": "application/json", ...getAuthHeader() },
        body: JSON.stringify(payload), 
    });


      const contentType = res.headers.get("content-type") || "";
      let serverMsg = "";
      if (contentType.includes("application/json")) {
        const data = await res.json();
        serverMsg =
          data?.message ??
          `Inserted: ${data?.insertedCount ?? 0}, Reactivated: ${
            data?.reactivatedCount ?? 0
          }, Deactivated: ${data?.deactivatedCount ?? 0}`;
      } else {
        serverMsg = await res.text();
      }
      if (!res.ok) {
        alert(serverMsg || `Failed to save roles (${res.status})`);
        return;
      }
      alert(serverMsg || "Roles assigned successfully!");
      await load();
      setCountry("");
      setState("");
      setCity("");
      setStates([]);
      setCities([]);
    } catch (err) {
      console.error("Error saving roles:", err);
      alert("Error saving roles");
    }
  };

  const leftSelectableRoles = availableRoles.filter(
    (r) => !selectedRoles.includes(r)
  );

  return (
    <div style={{ padding: 20 }}>
      <h2>Assign Roles & Locations</h2>

      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 32 }}>
        <div>
          <h3>Available Roles</h3>
          {leftSelectableRoles.length === 0 ? (
            <p>None</p>
          ) : (
            leftSelectableRoles.map((role) => (
              <div key={role}>
                <label>
                  <input
                    type="checkbox"
                    checked={selectedRoles.includes(role)}
                    onChange={() => toggleLeft(role)}
                  />{" "}
                  {role}
                </label>
              </div>
            ))
          )}
        </div>

        <div>
          <h3>Already Assigned (Active)</h3>
          {assignedActiveRoles.length === 0 ? (
            <p>No active roles</p>
          ) : (
            <ul style={{ marginTop: 8 }}>
              {assignedActiveRoles.map((r) => (
                <li key={r}>
                  <label>
                    <input type="checkbox" checked disabled /> {r}
                  </label>
                </li>
              ))}
            </ul>
          )}

          <h3 style={{ marginTop: 24 }}>Selected To Assign</h3>
          {selectedRoles.length === 0 ? (
            <p>Nothing selected</p>
          ) : (
            <ul style={{ marginTop: 8 }}>
              {selectedRoles.map((r) => (
                <li key={`sel-${r}`}>
                  <label>
                    <input
                      type="checkbox"
                      checked
                      onChange={() => toggleRightSelected(r)}
                      title="Uncheck to move back to left"
                    />{" "}
                    {r}
                  </label>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>

      <hr />
      <h3>Location</h3>
      <div style={{ display: "flex", gap: 16, alignItems: "center" }}>
        <div>
          Country:&nbsp;
          <select
            value={country}
            onChange={(e) => {
              setCountry(e.target.value);
              setState("");
              setCity("");
              setStates([]);
              setCities([]);
            }}
          >
            <option value="">--Select Country--</option>
            {countries.map((c) => (
              <option key={c.iso2} value={c.name}>
                {c.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          State:&nbsp;
          <select
            value={state}
            onChange={(e) => {
              setState(e.target.value);
              setCity("");
              setCities([]);
            }}
            disabled={!country}
          >
            <option value="">--Select State--</option>
            {states.map((s) => (
              <option key={s.iso2} value={s.name}>
                {s.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          City:&nbsp;
          <select
            value={city}
            onChange={(e) => setCity(e.target.value)}
            disabled={!state}
          >
            <option value="">--Select City--</option>
            {cities.map((c) => (
              <option key={c.id} value={c.name}>
                {c.name}
              </option>
            ))}
          </select>
        </div>
      </div>

      <hr />
      <div style={{ display: "flex", gap: 12 }}>
        <button onClick={handleSave}>Save</button>
        <button onClick={() => navigate(`/disable-roles/${id}`)}>
          Go to Disable
        </button>
      </div>
    </div>
  );
}
