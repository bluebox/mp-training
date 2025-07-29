import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { useLocation, useNavigate } from "react-router-dom";
import { TEACHERS } from "./urls";

function EditTeacherDetails({access}){
    const [data,setData] = useState(null)
    const navigator = useNavigate()
    const { state } = useLocation();
    const userId = state?.userId;

    useEffect(()=>{
        console.log("Edit route : ",TEACHERS+String(userId)+"/")
        if(userId === undefined){
            setData(null)
        }else{
            customAXIOS(TEACHERS+String(userId)+"/",null,"get",null,navigator)
            .then(res=>{
                console.log("Result::",res)
                if(res!= null)
                    setData(res)
                else
                    alert("Error in fetching details...")

            })
            .catch(ex=>{
                console.log("Error or no record found");
                alert("Error or no record found");
            })
            console.log(data)
        }
    },[userId])

    if(!data){
        if(userId !== undefined)
            return <p>loading...</p>
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData(prev => ({
            ...prev,
            [name]: value
        }));
        console.log(data);
    };
    const handleSubmit = (e)=>{
        e.preventDefault();
        console.log(data)
        if(userId !== undefined){
            customAXIOS(TEACHERS+String(userId)+"/",null,"put",data,navigator)
            .then(data=>{
                alert("details edited successfully")
                
            })
            .catch(err=>{
                console.log("Error:",err)
                alert("Data not submited")
            })
            navigator(access==='admin'?"/allTeachers":"/teacherDetails")
        }else{
            customAXIOS(TEACHERS,null,'post',data,navigator)
            .then(res=>{
                alert("Teacher Regestered successfully!!\n username:user_"+res.user+"\npassword: password123");
            })
            .catch(err=>{
                alert("Registration Failed");
                console.log("Error:",err);
            })
            navigator('/allTeachers')
        }
    }
    return(
        <div>
            <h2>Edit Details</h2>
            <form onSubmit={handleSubmit}>
                {userId !== undefined &&
                    <label>User Id:
                    <input type="text" name="user" value={data===null?'':data.user||''} readOnly onChange={handleChange}></input></label>
                }
                    <label>Name: </label>
                    <input type="text" name="Name" value={data ===null?'':data.Name || ''} onChange={handleChange} />

                    <label>Level: </label>
                    <input type="text" name="level"  value={data===null?'':data.level || ''} onChange={handleChange} />
                {userId !== undefined &&
                    <label>Joining Date(YYYY-MM-DD)
                        <input type="text"  name="joining_date" value={data===null?'':data.joining_date || ''} readOnly onChange={handleChange} />
                    </label>
                }
                    <label>Experience in years</label>
                    <input type="number" name="experience"value={data===null?'':data.experience||''} onChange={handleChange}/>

                    <button type="submit" className="submit-buttons">{userId===undefined?'Submit':'Update'}</button>
            </form>
        </div>
    )
}
export default EditTeacherDetails