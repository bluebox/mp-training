// src/components/shared/LocationDropdowns.jsx
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchCountries,
  fetchStates,
  fetchCities,
  resetStatesAndCities,
  resetCities,
} from "../../redux/locationSlice";

const LocationDropdowns = ({ form, setForm }) => {
  const dispatch = useDispatch();
  const { countries, states, cities } = useSelector((state) => state.location);

  useEffect(() => {
    dispatch(fetchCountries());
  }, [dispatch]);

  const handleCountryChange = (e) => {
    const country = e.target.value;
    setForm({ ...form, country, state: "", city: "" });
    dispatch(resetStatesAndCities());
    if (country) dispatch(fetchStates(country));
  };

  const handleStateChange = (e) => {
    const state = e.target.value;
    setForm({ ...form, state, city: "" });
    dispatch(resetCities());
    if (form.country && state) {
      dispatch(fetchCities({ country: form.country, state }));
    }
  };

  return (
    <div className="space-y-4">
      <div>
        <label className="block text-sm font-semibold">Country</label>
        <select
          value={form.country || ""}
          onChange={handleCountryChange}
          className="w-full border px-3 py-2 rounded"
        >
          <option value="">Select Country</option>
          {countries.map((c) => (
            <option key={c} value={c}>
              {c}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label className="block text-sm font-semibold">State</label>
        <select
          value={form.state || ""}
          onChange={handleStateChange}
          className="w-full border px-3 py-2 rounded"
        >
          <option value="">Select State</option>
          {states.map((s) => (
            <option key={s} value={s}>
              {s}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label className="block text-sm font-semibold">City</label>
        <select
          value={form.city || ""}
          onChange={(e) => setForm({ ...form, city: e.target.value })}
          className="w-full border px-3 py-2 rounded"
        >
          <option value="">Select City</option>
          {cities.map((c) => (
            <option key={c} value={c}>
              {c}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
};

export default LocationDropdowns;
