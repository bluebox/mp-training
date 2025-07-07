import React , {useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';


const AddBookForm = ({isEditMode = false}) => {
  const {bookId} = useParams();
  
  const [data , setData] = useState({
    title : "",
    author : "",
    category : "",
    status : "ACTIVE",
    available : "AVAILABLE"
  });  
  const [loading , setLoading] = useState(true);
  const [error , setError] = useState(null);


  const handleChange = (event) => {
    event.preventDefault();
    const { name , value } = event.target;
    setData({...data , [name] : value})
  }

  if(isEditMode === true && bookId){
      
      useEffect(()=>{
          const fetchData = async () => {

              console.log("inside the fetch data...")
              try{
                  const response = await fetch(`http://localhost:8080/books/list?id=${bookId}`);
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
        }, [isEditMode , bookId])

  }

  const handleSubmit = async (event) =>{
     event.preventDefault();
     console.log('Form data submitted:', data);

     const url = isEditMode ? `http://localhost:8080/books/update/${bookId}` : `http://localhost:8080/books/add`
     try {
      const response = await fetch( url, {
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
      alert(res);
      console.log(res);
     } catch (error) {
        console.error("error : " , error);
     }
  }

  return (

    <>

    {isEditMode ? <div>book id : {bookId}</div> : ""}

    {!isEditMode ? <h1>Add Book Form</h1> : <h1>Update Book</h1>}
    <br />

    <form action="" onSubmit={handleSubmit}>

        <label htmlFor="title">Title :</label>
        <input type="text" name="title" id="title" placeholder='enterbook title' value={data.title} onChange={handleChange} required/>

        <br></br>
        <br></br>

        <label htmlFor="author">Author :</label>
        <input type="text" name="author" id="author" placeholder="enter author name" value={data.author} onChange={handleChange}/>

        <br></br>
        <br></br>

        <label htmlFor="category">Category :</label>
        <input type="text" name="category" id="category" placeholder='enter category name' value={data.category} onChange={handleChange}/>

        <br></br>
        <br></br>

        <label htmlFor="status">Status :</label>
        <select name="status" id="status"  value={data.status} onChange={handleChange}>

            <option value="ACTIVE">ACTIVE</option>
            <option value="INACTIVE">INACTIVE</option>

        </select>

        <br></br>
        <br></br>

        <label htmlFor="available">Available :</label>
        <select name="available" id="available"  value={data.available} onChange={handleChange}>

            <option value="AVAILABLE">AVAILABLE</option>
            <option value="ISSUED">ISSUED</option>

        </select>
        <br />
        <br />
        <input type="submit" />

    </form>
   
    </>
   
   

  )
}

export default AddBookForm
