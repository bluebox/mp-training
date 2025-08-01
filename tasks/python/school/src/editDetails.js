// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { ALLSTUDENTS, STUDENTDETAILS } from "./urls";
// import { useNavigate } from "react-router-dom";
// // import './App.css'
// function EditDetails({ userId }) {
//     const [data, setData] = useState(null);
//     const [submit,setSubmit] = useState(false);
//     const navigator = useNavigate() 
//     const fetchData =async ()=>{
//         let null_data_trigger = false
//         let res = null
//         try{
//             res = await customAXIOS(STUDENTDETAILS, { id: userId }, "get", null,navigator)
//             setData(res);
            
//         }catch(ex){
//             console.log("Error in fetching data");
//             if(res === null){
//                 alert("Details not found. Please create")
//                 setSubmit(true)
//                 null_data_trigger = true
//             }
//             console.log(res);
//             if(null_data_trigger){
//                 const fetch_student = await customAXIOS(ALLSTUDENTS,{id:userId},'get',null,navigator)
//                 if(fetch_student &&fetch_student.length>0){
//                     const fallback_Data = {}
//                     fallback_Data.Name = fetch_student[0].Name
//                     console.log(fallback_Data)
//                     console.log(fetch_student)
//                 }else{
//                     alert("No student found. please try after some time");
//                 }
//             }
//         }
//         console.log("data",data)
//     }
//     useEffect(() => {
//         fetchData()
//     }, [userId]);

//     const handleChange = (e) => {
//         const { name, value } = e.target;
//         setData(prev => ({
//             ...prev,
//             [name]: value
//         }));
//         console.log(data);
//     };

//     const handleSubmit = (e)=>{
//         e.preventDefault();
//         console.log(data)
//         if(submit){
//             customAXIOS(STUDENTDETAILS,{id:userId},"put",data,navigator)
//             .then(data=>{
//                 alert("details edited successfully")
                
//             })
//             .catch(err=>{
//                 console.log("Error:",err)
//             })
//         }else{
//             data['id'] = userId
//             customAXIOS(STUDENTDETAILS,null,"post",data,navigator)
//             .then(data=>{
//                 alert("details edited successfully")
                
//             })
//             .catch(err=>{
//                 console.log("Error:",err)
//             })
//         }
//         navigator("/studentDetails")
//     }


//     // if (!data) return <p>Loading...</p>;

//     return (
//         <div>
//             <h2>Edit Student Details</h2>
//             <form onSubmit={handleSubmit}>
//                     <label>Name: </label>
//                     <input type="text" name="name" value={data?.Name || ''} onChange={handleChange} required/>

//                     <label>Age: </label>
//                     <input type="number" name="age"  value={data?.Age || ''} onChange={handleChange} required/>
            
//                     <label>Father's Name: </label>
//                     <input type="text" name="FatherName" value={data?.FatherName || ''} onChange={handleChange} required/>
            
//                     <label>Father's Age: </label>
//                     <input type="number" name="FatherAge"  value={data?.FatherAge || ''} onChange={handleChange} required/>
            
//                     <label>Mother's Name: </label>
//                     <input type="text" name="MotherName" value={data?.MotherName || ''} onChange={handleChange} required/>
    
//                     <label>Mother's Age: </label>
//                     <input type="number" name="MotherAge"  value={data?.MotherAge || ''} onChange={handleChange} required/>
            
//                     <label>Address: </label>
//                     <input type="text" name="address" value={data?.address || ''} onChange={handleChange} required/>

//                     <label>Phone No.: </label>
//                     <input type="text" name="phoneNo" value={data?.phoneNo || ''} onChange={handleChange} required/>

//                     <button type="submit" className="submit-buttons">Update</button>
//             </form>
//         </div>
//     );
// }

// export default EditDetails;


import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLSTUDENTS, STUDENTDETAILS } from "./urls";
import { useNavigate } from "react-router-dom";
import Student from "./students";

function EditDetails({ userId }) {
    const [data, setData] = useState(null);
    const [submit, setSubmit] = useState(false);
    const navigator = useNavigate();

    useEffect(() => {
    const fetchData = async () => {
        try {
            const res = await customAXIOS(STUDENTDETAILS, { id: userId }, "get", null, navigator);
            setData(res);
            setSubmit(false); // existing data, so we will update (PUT)
        } catch (error) {
            console.warn("Student detail not found, falling back to ALLSTUDENTS");
            alert("Details not found. Please create.");
            setSubmit(true); // we will create new entry (POST)

            try {
                const fetch_student = await customAXIOS(ALLSTUDENTS, { id: userId }, "get", null, navigator);
                if (fetch_student && fetch_student.length > 0) {
                    const fallback = fetch_student[0];
                    const fallback_Data = {
                        Name: fallback.Name,
                        Age: '',
                        FatherName: '',
                        FatherAge: '',
                        MotherName: '',
                        MotherAge: '',
                        address: '',
                        phoneNo: ''
                    };
                    setData(fallback_Data);
                } else {
                    alert("No student found in fallback. Please try again later.");
                }
            } catch (fallbackErr) {
                console.error("Error fetching fallback student data", fallbackErr);
                alert("Something went wrong while fetching fallback data.");
            }
        }
    };

    if (userId) {
        fetchData();
    }
}, [userId, navigator]);


    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = { ...data, Student: userId };
        delete payload.Name
        const method = submit ? "post" : "put";

        customAXIOS(STUDENTDETAILS, method === "put" ? { id: userId } : null, method, payload, navigator)
            .then(() => {
                alert("Details saved successfully");
                navigator("/studentDetails");
            })
            .catch((err) => {
                console.error("Error:", err);
                alert("Failed to save details.");
            });
    };

    if (!data) return <p>Loading...</p>;

    return (
        <div>
            <h2>Edit Student Details</h2>
            <form onSubmit={handleSubmit}>
                <label>Name: </label>
                <input type="text" name="Name" value={data.Name || ""} onChange={handleChange} required />

                <label>Age: </label>
                <input type="number" name="Age" value={data.Age || ""} onChange={handleChange} required />

                <label>Father's Name: </label>
                <input type="text" name="FatherName" value={data.FatherName || ""} onChange={handleChange} required />

                <label>Father's Age: </label>
                <input type="number" name="FatherAge" value={data.FatherAge || ""} onChange={handleChange} required />

                <label>Mother's Name: </label>
                <input type="text" name="MotherName" value={data.MotherName || ""} onChange={handleChange} required />

                <label>Mother's Age: </label>
                <input type="number" name="MotherAge" value={data.MotherAge || ""} onChange={handleChange} required />

                <label>Address: </label>
                <input type="text" name="address" value={data.address || ""} onChange={handleChange} required />

                <label>Phone No.: </label>
                <input type="text" name="phoneNo" value={data.phoneNo || ""} onChange={handleChange} required />

                <button type="submit" className="submit-buttons">Update</button>
            </form>
        </div>
    );
}

export default EditDetails;
