import axios from "axios";
import { useState } from "react";

export default function AddItemForm() {
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    status: "ACTIVE",
    available: "YES",
    category: "DESSERT", // enum value
    username: "sathee",
    password: "123",
  });
  const [file, setFile] = useState(null);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = new FormData();
    form.append(
      "item",
      JSON.stringify({
        name: formData.name,
        description: formData.description,
        price: formData.price,
        category: formData.category,
      })
    );

    if (file) {
      form.append("file", file);
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/items/addItem",
        form
      );
      console.log("Success:", response.data);
    } catch (error) {
      console.error("Error:", error.response?.data || error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        name="name"
        placeholder="Item Name"
        onChange={handleChange}
      />
      <input
        type="text"
        name="description"
        placeholder="Description"
        onChange={handleChange}
      />
      <input
        type="number"
        name="price"
        placeholder="Price"
        onChange={handleChange}
      />
      <select name="category" onChange={handleChange}>
        <option value="STARTER">Starter</option>
        <option value="MAIN_COURSE">Main Course</option>
        <option value="DESSERT">Dessert</option>
        <option value="BEVERAGE">Beverage</option>
        <option value="SNACK">Snack</option>
        <option value="SALAD">Salad</option>
        <option value="SOUP">Soup</option>
      </select>
      <input type="file" onChange={handleFileChange} />
      <button type="submit">Add Item</button>
    </form>
  );
}
