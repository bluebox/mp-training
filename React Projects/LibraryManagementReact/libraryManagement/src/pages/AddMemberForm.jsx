import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';

const AddMemberForm = ({isEditMode = false}) => {

  const {memberId} = useParams();

  const [data , setData] = useState({
      name : "",
      email : "",
      mobile : "",
      gender : "MALE",
      address : "",
    });  

    const [loading , setLoading] = useState(true);
    const [error , setError] = useState(null);
  
    const handleChange = (event) => {
      event.preventDefault();
      const { name , value } = event.target;
      setData({...data , [name] : value})
    }
    
    if(isEditMode === true && memberId){
          
          useEffect(()=>{
              const fetchData = async () => {
    
                  console.log("inside the fetch data...")
                  try{
                      const response = await fetch(`http://localhost:8080/members/list?id=${memberId}`);
                      if(!response.ok){
                          throw new Error(`http error status : ${response.status}`)
          
                      }
                      const res_data = await response.json();
                      setData(res_data);
                      console.log(res_data);
                  }catch(err){
                      setError(err);
                      console.log(err);
                  }
                  finally{
                      setLoading(false);
                  }
              };
          
              fetchData();
            }, [isEditMode , memberId])
    
    }


    const handleSubmit = async (event) =>{
       event.preventDefault();
       console.log('Form data submitted:', data);
       const url = isEditMode ? `http://localhost:8080/members/update/${memberId}` : `http://localhost:8080/members/add`
       try {
        const response = await fetch(url, {
          method : isEditMode ? "PUT" : "POST",
          headers : {
            'Content-Type' : "application/json"
          },
          body : JSON.stringify(data)
  
  
        })
  
        if (!response.ok){
          throw new Error(`http error , Status ${response.status}`)
        }
  
        const res = await response.json();
        console.log(res);
       } catch (error) {
          console.error("error : " , error);
       }
    }
  
    return (
  
      <>
  
      <h1>Add Member Form</h1>
      <br />
  
      <form action="" onSubmit={handleSubmit}>
  
          <label htmlFor="name">Name :</label>
          <input type="text" name="name" id="name" placeholder='enter name' value={data.name} onChange={handleChange} required/>
  
          <br></br>
          <br></br>
  
          <label htmlFor="email">Email :</label>
          <input type="email" name="email" id="email" placeholder="enter your email" value={data.email} onChange={handleChange}/>
  
          <br></br>
          <br></br>
  
          <label htmlFor="mobile">Mobile :</label>
          <input type="number" name="mobile" id="mobile" placeholder='enter your mobile' value={data.mobile} onChange={handleChange}/>
  
          <br></br>
          <br></br>
  
          <label htmlFor="gender">Gender :</label>
          <select name="gender" id="gender"  value={data.gender} onChange={handleChange}>
  
              <option value="MALE">MALE</option>
              <option value="FEMALE">FEMALE</option>
  
          </select>
  
          <br></br>
          <br></br>

          <label htmlFor="address">Address :</label>
          <input type="text" name="address" id="address" placeholder='enter your address' value={data.address} onChange={handleChange} required/>
  
          
        <br />
        <br />
          <input type="submit" />
  
      </form>
     
      </>
     
     
  
    )
}

export default AddMemberForm
