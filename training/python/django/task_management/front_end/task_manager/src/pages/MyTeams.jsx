import { useEffect, useState } from "react"
import api from "../api/axios";

export default function MyTeams(){

    const [members, setMembers] = useState([]);

    useEffect(()=>{
        const fetch = async () => {
            try{
                const response = await api.get("members")
                setMembers(response.data.members);
            }
            catch(err){
                console.log("error fetching members");
            }
        }
        fetch();
    },[]);
    
    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Auth Role</th>
                        <th>Job Role</th>
                        <th>Joined At</th>
                    </tr>
                </thead>
                <tbody>
                    {Array.isArray(members) && members.map((member) => (
                        <tr key={member.id}>
                            <td>{member.user.username}</td>
                            <td>{member.user.email}</td>
                            <td>{member.user.first_name}</td>
                            <td>{member.role}</td>
                            <td>{new Date(member.joined_at).toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}