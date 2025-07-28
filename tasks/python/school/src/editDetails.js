import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { STUDENTDETAILS } from "./urls";
import { useNavigate } from "react-router-dom";
// import './App.css'
function EditDetails({ userId }) {
    const [data, setData] = useState(null);
    const navigator = useNavigate() 
    useEffect(() => {
        customAXIOS(STUDENTDETAILS, { id: userId }, "get", null,navigator)
            .then(res => {
                setData(res);
                console.log(res);
            })
            .catch(err => {
                console.log("Error:", err);
            });
    }, [userId]);

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
        customAXIOS(STUDENTDETAILS,{id:userId},"put",data,navigator)
        .then(data=>{
            alert("details edited successfully")
            
        })
        .catch(err=>{
            console.log("Error:",err)
        })
        navigator("/studentDetails")
    }


    if (!data) return <p>Loading...</p>;

    return (
        <div>
            <h2>Edit Student Details</h2>
            <form onSubmit={handleSubmit}>
                    <label>Name: </label>
                    <input type="text" name="name" value={data.Name || ''} onChange={handleChange} />

                    <label>Age: </label>
                    <input type="number" name="age"  value={data.Age || ''} onChange={handleChange} />
            
                    <label>Father's Name: </label>
                    <input type="text" name="FatherName" value={data.FatherName || ''} onChange={handleChange} />
            
                    <label>Father's Age: </label>
                    <input type="number" name="FatherAge"  value={data.FatherAge || ''} onChange={handleChange} />
            
                    <label>Mother's Name: </label>
                    <input type="text" name="MotherName" value={data.MotherName || ''} onChange={handleChange} />
    
                    <label>Mother's Age: </label>
                    <input type="number" name="MotherAge"  value={data.MotherAge || ''} onChange={handleChange} />
            
                    <label>Address: </label>
                    <input type="text" name="address" value={data.address || ''} onChange={handleChange} />

                    <label>Phone No.: </label>
                    <input type="text" name="phoneNo" value={data.phoneNo || ''} onChange={handleChange} />

                    <button type="submit" className="submit-buttons">Update</button>
            </form>
        </div>
    );
}

export default EditDetails;
