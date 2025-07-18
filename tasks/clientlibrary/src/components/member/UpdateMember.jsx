import React, { useState } from "react";

const UpdateMember = () => {
  const [formData, setFormData] = useState({
    memberId: "",
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/api/members/update", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error("Failed to update member");
      }
      setMessage("Member updated successfully!");
      setFormData({
        memberId: "",
        name: "",
        email: "",
        mobile: "",
        gender: "",
        address: "",
      });
    } catch (error) {
      console.error(error);
      setMessage("Failed to update member.");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="grid grid-cols-1 md:grid-cols-2 gap-x-4 gap-y-3 mt-10 p-6"
    >
      <label htmlFor="memberId" className="font-medium">
        Member ID
      </label>
      <input
        id="memberId"
        name="memberId"
        type="number"
        value={formData.memberId}
        onChange={handleChange}
        required
        pattern="\\d+"
        placeholder="Enter Member ID"
        className="p-2 border rounded"
      />

      <label htmlFor="name" className="font-medium">
        Name
      </label>
      <input
        id="name"
        name="name"
        type="text"
        value={formData.name}
        onChange={handleChange}
        required
        placeholder="Enter Name"
        className="p-2 border rounded"
      />

      <label htmlFor="email" className="font-medium">
        Email
      </label>
      <input
        id="email"
        name="email"
        type="email"
        value={formData.email}
        onChange={handleChange}
        required
        placeholder="Enter Email"
        className="p-2 border rounded"
      />

      <label htmlFor="mobile" className="font-medium">
        Mobile
      </label>
      <input
        id="mobile"
        name="mobile"
        type="number"
        value={formData.mobile}
        onChange={handleChange}
        required
        pattern="\\d+"
        placeholder="Enter Mobile Number"
        className="p-2 border rounded"
      />

      <label htmlFor="gender" className="font-medium">
        Gender
      </label>
      <select
        id="gender"
        name="gender"
        value={formData.gender}
        onChange={handleChange}
        required
        className="p-2 border rounded"
      >
        <option value="" disabled>
          Select Gender
        </option>
        <option value="M">Male</option>
        <option value="F">Female</option>
      </select>

      <label htmlFor="address" className="font-medium">
        Address
      </label>
      <textarea
        id="address"
        name="address"
        value={formData.address}
        onChange={handleChange}
        required
        placeholder="Enter Address"
        className="p-2 border rounded"
        rows={3}
      />

      <div className="col-span-2 text-center mt-2 font-semibold ">
        {message}
      </div>

      <button
        type="submit"
        className="col-span-2 bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition cursor-pointer"
      >
        Update Member
      </button>
    </form>
  );
};

export default UpdateMember;
