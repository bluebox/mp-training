const baseURL = "http://localhost:8080"

export const fetchCategories = async () =>{
    const response = await fetch(baseURL+'/api/expenses/categories' , {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        }
    });
    
    if(!response.ok){
        throw new Error('Could not fetch categories');
    }
    return await response.json();
}

export const addExpense = async (formData) =>{

    const response = await fetch(baseURL+'/api/employee/addexpense' , {
        method: "POST",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify(formData)
    });
    
    if(!response.ok){
        const res = await response.json();
        throw new Error('Could not Add a new expense \n ' + res.message);
    }

    return await response.json();
}
