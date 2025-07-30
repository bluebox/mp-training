// import React, { useEffect,useState } from "react";
// import "./display.css"
// import { data, Link, useNavigate } from "react-router-dom";
// import customAXIOS from "./apis";
// import { STUDENTS, USERS } from "./urls";


// function Student(){
//     const [user,setUser] = useState([]);
//     const redirect = useNavigate()
//     // const users = useSelector((state)=>state.users)
// //local storage
//     // useEffect(()=>{
//     //     const localUsers = []
//     //     for(let i = 0;i<localStorage.length;i++)
//     //     {
//     //         const key = localStorage.key(i)
//     //         const data = localStorage.getItem(key)
//     //         try{
//     //             const parsedData = JSON.parse(data)
//     //             parsedData.id = key
//     //             localUsers.push(parsedData)
//     //         }catch(e)
//     //         {
//     //             console.error("Invalid json ",e);
//     //         }
//     //     }
//     //     setUser(localUsers)
//     // },[]
//     // );
    
//     // useEffect(()=>{
//     //     const localUsers = []
//     //     const users = useSelector((state)=>{
//     //         data = state.users
//     //         for(let i =0;i<data.length;i++){
//     //             formatData = {id:}
//     //             localUsers.push()
//     //         }
//     //     })
//     // })
//     const fetchUsers = async()=>{
//             try{
//                 const usrs = await customAXIOS(STUDENTS,null,"get",null,redirect)
//                 console.log(usrs)
//                 setUser(usrs||[]);
//                 for(let i in usrs){
//                     localStorage.setItem(i.user,i);
//                 }
//             }catch(err){
//                 console.log("users fetch failed ",err);
//             }
//     };
//     const refresh = ()=>{
//         fetchUsers();
//     };
//     useEffect(()=>{
//         fetchUsers();
//     },[])
//     const handleDelete = async (id)=>{//need to implement delete method too
//         // try{
//         //     await customAXIOS('${USERS}?{id}/',null,"delete",null,redirect)
//         //     setUser(user.filter((u)=>u.id !== id));
//         // }catch(err)
//         // {
//         //     console.log("Error in deleting",err);
//         // }
//         // localStorage.removeItem(id);
//         // dispatch(delete_user(id));
//         console.log("params : id",id);
//         try{
//             await customAXIOS(STUDENTS,{id:id},"delete",null,redirect)
//         }catch(error)
//         {
//             console.log("Error in delete",error)            
//         }
//         refresh()
//     }

//     const handleAlter = (id)=>{
//             <Link to="/studentRegister" state={{"id": id.toString()}}>Alter</Link>
//     }

//     return (
//         <div className="display-container">
//             <h2>Students</h2>
//             <table className="user-table">
//                 <thead>
//                     <tr>
//                         <th>Name</th>
//                         <th>Class Representative</th>
//                         <th>Attendance</th>
//                         <th>Status</th>
//                         <th>Class</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {user.map((u) => (
//                         <tr key={u.user}>
//                             <td>{u.Name}</td>
//                             <td>{u.is_class_representative?"Yes":"No"}</td>
//                             <td>{u.attendance}</td>
//                             <td>{u.status}</td>
//                             <td>{u.Class}</td>
//                             <td>
//                                 {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
//                                 <Link to={"/studentRegister"} state = {{id:u.user}}>
//                                     <button className="alter-btn">Alter</button>
//                                 </Link>
//                                 {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

//                                 <button className="delete-btn" onClick={() => handleDelete(u.user)}>Delete</button>
//                             </td>
//                         </tr>
//                     ))}
//                     {user.length === 0 && (
//                         <tr>
//                             <td colSpan="9" style={{ textAlign: "center" }}>No users found</td>
//                         </tr>
//                     )}
//                 </tbody>
//             </table>
//         </div>
//     );
// }
// export default Student

import React, { useEffect, useState } from "react";
import "./display.css";
import { Link, useNavigate } from "react-router-dom";
import customAXIOS from "./apis";
import { STUDENTS } from "./urls";

function Student() {
    const [user, setUser] = useState([]);
    const redirect = useNavigate();

    const fetchUsers =  () => {
        try {
            customAXIOS(STUDENTS, null, "get", null, redirect)
            .then(usrs=>{
                setUser(usrs||[])
                 
            })
            console.log("usrs",user);
            // setUser(usrs || []);
        } catch (err) {
            console.log("users fetch failed", err);
        }
    };

    const refresh = () => {
        fetchUsers();
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    const handleDelete = async (id) => {
        try {
            await customAXIOS(STUDENTS, { id: id }, "delete", null, redirect);
        } catch (error) {
            console.log("Error in delete", error);
        }
        refresh();
    };

    return (
        <div className="display-container">
            <h2>Students</h2>
            <button onClick={refresh} className="alter-btn">Refresh</button>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Class Representative</th>
                        <th>Attendance</th>
                        <th>Status</th>
                        <th>Class</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                    // console.log("user::",user)}
                    user.length>0 &&(user.map((u) => (
                        <tr key={u.user}>
                            <td>{u.Name}</td>
                            <td>{u.is_class_representative ? "Yes" : "No"}</td>
                            <td>{u.attendance}</td>
                            <td>{u.status}</td>
                            <td>{u.Class}</td>
                            <td>
                                <Link to={"/studentRegister"} state={{ id: u.user }}>
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                <button className="delete-btn" onClick={() => handleDelete(u.user)}>Delete</button>
                            </td>
                        </tr>
                    )))}
                    {user.length === 0 && (
                        <tr>
                            <td colSpan="6" style={{ textAlign: "center" }}>No users found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default Student;
