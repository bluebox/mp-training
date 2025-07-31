import { useEffect, useState } from "react"
import api from "../api/axios";

export default function AssignToTeam(){
//profiles/all  teams/ members/
    const [users, setUsers] = useState([]);
    const [teams, setTeams] = useState([]);
    const [formData, setFormData] = useState({
        user: "",
        team: "",
        role: ""
    });

    const handleChange = (e) => {
         setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post("members/",formData);
            console.log(response.data);
            alert("Team assigned sucessfully");
        }
        catch (err) {
            alert("Error assigning failed.");
        }
        
        setFormData({
            user: "",
            team: "",
            role: ""
        });
  };


    useEffect(() => {
        const fetchMembers = async (e)=>{
            try{
                const response = await api.get('profiles/all/');
                // console.log(response.data);
                setUsers(...users, response.data);
            }
            catch(err){
                console.log("error fetching members",err);
            }
        }
        const fetchTeams = async (e)=>{

            try{
                const response = await api.get('teams/');
                setTeams(response.data);
            }
            catch(err){
                console.log("error fetching teams", err);
            }
        }

        fetchMembers();
        fetchTeams();
        // eslint-disable-next-line
    },[]);
    return (
        <div>
            <form>
                <div>
                    <label>team</label>
                    <select name="team" value={formData.team} onChange={handleChange} required>
                    <option value="">Select a team</option>
                    {Array.isArray(teams) && teams.map((team) => (
                        <option key={team.id} value={team.id}>
                        {team.name}
                        </option>
                    ))}
                    </select>
                </div>
                <div>
                    <label>member</label>
                    <select name="user" value={formData.user} onChange={handleChange} required>
                    <option value="">Select a member</option>
                    {Array.isArray(users) &&
                        (users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                            )))}
                    </select>

                </div>
                <div>
                    <label>Role</label>
                    <input name="role" value={formData.role} onChange={handleChange} required/>
                </div>

                <button type="submit" onClick={handleSubmit}>Assign</button>
            </form>
        </div>
    );
}