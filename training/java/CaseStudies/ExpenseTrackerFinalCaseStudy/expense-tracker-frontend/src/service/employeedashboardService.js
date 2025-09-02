const baseURL = "http://localhost:8080";

export const fetchCategorySpendByMonth = async () => {
    
    

    const response = await fetch(
        `${baseURL}/api/employee/get-expense-per-category`,
        {
            method: "GET",
            credentials:"include",
            headers: {
                "Content-Type": "application/json",
            },
        }
    );

    if (!response.ok) {
        throw new Error("Could not fetch category spend by month");
    }

    return await response.json();
};
