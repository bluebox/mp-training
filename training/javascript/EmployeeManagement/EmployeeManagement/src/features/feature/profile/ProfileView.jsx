import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchUserProfile,
  updateUserProfile,
  clearProfileMessages,
} from "./profileSlice";
import LocationDropdowns from "../location/LocationDropdowns";

const ProfileView = () => {
  const dispatch = useDispatch();
  const { profile, loading, updateMessage, error } = useSelector((state) => state.profile);

  const [form, setForm] = useState({
    emp_name: "",
    dob: "",
    dept: "",
    address: {
      country: "",
      state: "",
      city: "",
    },
  });

  useEffect(() => {
    dispatch(fetchUserProfile());
  }, [dispatch]);

  useEffect(() => {
    if (profile) {
      setForm({
        emp_name: profile.emp_name || "",
        dob: profile.dob || "",
        dept: profile.dept || "",
        address: {
          country: profile.address_details?.country || "",
          state: profile.address_details?.state || "",
          city: profile.address_details?.city || "",
        },
      });
    }
  }, [profile]);

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(updateUserProfile(form));
  };

  return (
    <div className="max-w-xl mx-auto bg-white p-6 shadow rounded-xl space-y-4">
      <h2 className="text-xl font-bold text-center">My Profile</h2>

      {loading ? (
        <p className="text-center">Loading...</p>
      ) : (
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block">Employee Name</label>
            <input
              type="text"
              value={form.emp_name}
              onChange={(e) => setForm({ ...form, emp_name: e.target.value })}
              className="w-full border px-3 py-2 rounded"
            />
          </div>

          <div>
            <label className="block">Date of Birth</label>
            <input
              type="date"
              value={form.dob}
              onChange={(e) => setForm({ ...form, dob: e.target.value })}
              className="w-full border px-3 py-2 rounded"
            />
          </div>

          <div>
            <label className="block">Department ID</label>
            <input
              type="number"
              value={form.dept}
              onChange={(e) => setForm({ ...form, dept: e.target.value })}
              className="w-full border px-3 py-2 rounded"
            />
          </div>

          <LocationDropdowns
            form={form.address}
            setForm={(address) => setForm({ ...form, address })}
          />

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded"
          >
            Update Profile
          </button>
        </form>
      )}

      {updateMessage && (
        <p className="text-green-600 text-center">{updateMessage}</p>
      )}
      {error && (
        <p className="text-red-600 text-center">{error}</p>
      )}
    </div>
  );
};

export default ProfileView;
