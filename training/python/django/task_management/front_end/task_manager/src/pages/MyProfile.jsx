import { useState } from "react";
import axios from "axios"; 

export default function MyProfile() {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({ ...storedUser });
    const access = localStorage.getItem("access");

    function handleEdit() {
        setIsEditing(true);
    }

    function handleChange(e) {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    }

    async function handleSave() {
        try {
            const response = await axios.put('http://localhost:8000/api/profile/', formData, {
                headers: {
                    Authorization: 'Bearer '+access,
                }
            }); 
            localStorage.setItem("user", JSON.stringify(response.data)); 
            setIsEditing(false);
        } catch (error) {
            console.error("Failed to update user:", error);
            alert("Update failed");
        }
    }

    return (
        <div className="MyProfile">
            <table>
                <tbody>
                    <tr>
                        <th>username</th>
                        <td>
                            {formData.username}
                        </td>
                    </tr>
                    <tr>
                        <th>first_name</th>
                        <td>
                            {isEditing ? (
                                <input
                                    name="first_name"
                                    value={formData.first_name}
                                    onChange={handleChange}
                                />
                            ) : (
                                formData.first_name
                            )}
                        </td>
                    </tr>
                    <tr>
                        <th>last_name</th>
                        <td>
                            {isEditing ? (
                                <input
                                    name="last_name"
                                    value={formData.last_name}
                                    onChange={handleChange}
                                />
                            ) : (
                                formData.last_name
                            )}
                        </td>
                    </tr>
                    <tr>
                        <th>email</th>
                        <td>
                            {formData.email}
                        </td>
                    </tr>
                    <tr>
                        <th>role</th>
                        <td>{formData.role}</td> 
                    </tr>
                    <tr>
                        <td colSpan="2">
                            {isEditing ? (
                                <button onClick={handleSave}>Save</button>
                            ) : (
                                <button onClick={handleEdit}>Edit</button>
                            )}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}
