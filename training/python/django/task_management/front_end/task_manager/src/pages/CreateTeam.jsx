import { useState } from "react"
import api from "../api/axios";

export default function CreateTeam(){
    const [formData, setFormData] = useState({
        name: "",
        description: ""
    });

    const handleChange = (e) => {
         setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post("teams/",formData);
            console.log(response.data);
            alert("Team created sucessfully");
        }
        catch (err) {
            alert("Error, creating failed.");
        }
        
        setFormData({
            name: "",
            description: ""
        });
  };


    return (
        <div>
            <form>
                <div>
                    <label>Team name</label>
                    <input name="name" value={formData.name} onChange={handleChange} required/>
                </div>
                
                <div>
                    <label>description</label>
                    <input name="description" value={formData.description} onChange={handleChange} required/>
                </div>
                <button type="submit" onClick={handleSubmit}>create</button>
            </form>
        </div>
    )
}