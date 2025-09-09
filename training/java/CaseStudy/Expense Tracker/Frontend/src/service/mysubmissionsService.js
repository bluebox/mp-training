const baseURL = "http://localhost:8080";

export const fetchMySubmissions = async () => {
  const response = await fetch(`${baseURL}/api/employee/get-expenses`, {
    method: "GET",
    credentials:"include",
    headers: {
      "Content-Type": "application/json"
    }
  });
  if (!response.ok) {
    throw new Error('Could not fetch your expense submissions');
  }
  return await response.json();
};


export const updateMySubmission = async (updatedSubmission) =>{
  console.log("trying to update expense with data: " + JSON.stringify(updatedSubmission));
  
  const response = await fetch(`${baseURL}/api/employee/update-expense`,{
    method:"PUT",
    credentials:"include",
    headers:{
        "Content-type":"application/json"  
    },
    body:JSON.stringify(updatedSubmission)
  });
  if(!response.ok){
    throw new Error('Could not update your expense');
  }
  return await response.json();
}


export const deleteMySubmissoin = async (expense) =>{
  const response = await fetch(`${baseURL}/api/employee/delete-expense/${expense.id}`,{
    method:"DELETE",
    credentials:"include",
    headers:{
        "Content-type":"application/json" 
    },
  });
  if(!response.ok){
    throw new Error('Could not delete your expense');
  }

  return await response.json();
}