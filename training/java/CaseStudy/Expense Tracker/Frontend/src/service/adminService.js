const baseURL = "http://localhost:8080";


export const fetchAllManagers = async () => {
    const response = await fetch(`${baseURL}/api/admin/get-managers`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        }
    });
    if (!response.ok) {
        throw new Error('Could not fetch managers');
    }
    return await response.json();
};


export const addNewUser = async (user) =>{
    
    const response = await fetch(baseURL+'/api/admin/add-user' , {
        method: "POST",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify(user)
    });
    
    if(!response.ok){
        throw new Error('Could not add a new user...');
    }
    
    return await response.json();
}


export const fetchExpenses = async (filters = {}) => {
    
    const { employeeId = 0, managerId = 0, categoryId = 0, month = 0 } = filters;
    
    const queryParams = new URLSearchParams({
        employeeId,
        managerId,
        categoryId,
        month
    }).toString();

    const response = await fetch(`${baseURL}/api/admin/expenses?${queryParams}`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        },
        
    });

    if (!response.ok) {
        throw new Error('Could not fetch expenses');
    }
    return await response.json();
};




export const fetchEmployees = async (filters = {}) => {
    const response = await fetch(`${baseURL}/api/admin/employees?`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error('Could not fetch employees');
    }
    return await response.json();
};



export const fetchTotalExpense = async () => {
    const response = await fetch(`${baseURL}/api/admin/get-total-expense`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error('Could not fetch total expense');
    }
    return await response.json();
};

export const fetchTotalExpensePerCategory = async () => {
    const response = await fetch(`${baseURL}/api/admin/get-expense-per-category`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error('Could not fetch total expense');
    }
    return await response.json();
};