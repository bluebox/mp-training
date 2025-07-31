import { useEffect, useState } from "react"
import api from "../api/axios";

export default function NewProject(){

    const [teams, setTeams] = useState([]);
    const [formData, setFormData] =useState({
        name:"",
        description:"",
        team:""
    });

    useEffect(()=>{
        const fetch = async () => {
            const response = await api.get('teams/');
            setTeams(response.data);
        };
        fetch();
    },[]);

    const handleChange = (e) =>{
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    const handleSubmit =async (e) => {
        e.preventDefault();
        try {
            await api.post('projects/',formData);
            alert('project created sucessfully');
        } catch (error) {
            console.log("error while creating project ",error);
            alert('failed creating');
        }
        setFormData({
            name:"",
            description:"",
            team:""
        })
    }
    
    return (
        <div>
            <form onSubmit={handleSubmit}> 
                <div>
                    <label>name</label>
                    <input name="name" value={formData.name} onChange={handleChange} required/>
                </div>

                <div>
                    <label>Description</label>
                    <input name="description" value={formData.description} onChange={handleChange} required/>
                </div>

                <div>
                    <label>team</label>
                    <select name="team" value={formData.team} onChange={handleChange} required>
                        <option value="">Select a team</option>
                        {teams.map((team) => (
                            <option key={team.id} value={team.id}>
                                {team.name}
                            </option>))}
                    </select>
                </div>

                <div>
                     <button type="submit">Create Project</button>
                </div>
            </form>
        </div>
    )
}