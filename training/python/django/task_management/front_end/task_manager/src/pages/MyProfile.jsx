import { useState } from "react";
import api from '../api/axios';
import '../MyProfile.css';

export default function MyProfile() {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({ ...storedUser });

    function handleEdit(e) {
        e.preventDefault();
        setIsEditing(true);
    }

    function handleChange(e) {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    }

    async function handleSave() {
        try {
            const response = await api.put('profile/', formData); 
            localStorage.setItem("user", JSON.stringify(response.data)); 
            setIsEditing(false);
        } catch (error) {
            console.error("Failed to update user:", error);
            alert("Update failed");
        }
    }

    return (
        <div className="MyProfile">
            <>

            <div>
                <div>
                    <div>
                        <h2>My Profile</h2>
                    </div>
                    <div>
                        <strong>username:  </strong>   {formData.username}
                    </div>
                    <div>
                        <strong>first_name:  </strong>  
                                
                        {isEditing ? (
                            <input
                                name="first_name"
                                value={formData.first_name}
                                onChange={handleChange}
                            />
                        ) : (
                            formData.first_name
                        )}
                    </div>
                    <div>
                        <strong>last_name: </strong>
                        {isEditing ? (
                            <input
                                name="last_name"
                                value={formData.last_name}
                                onChange={handleChange}
                            />
                        ) : (
                            formData.last_name
                        )}
                    </div>
                    <div>
                        <strong>email: </strong>
                        {formData.email}
                    </div>
                    <div>
                        <strong>role: </strong>
                        {formData.role}
                    </div>
                    <div>
                        {isEditing ? (
                            <button onClick={handleSave}>Save</button>
                        ) : (
                            <button onClick={handleEdit}>Edit</button>
                        )}
                    </div>
                </div>
            </div>
            </>
        </div>
    );
}
