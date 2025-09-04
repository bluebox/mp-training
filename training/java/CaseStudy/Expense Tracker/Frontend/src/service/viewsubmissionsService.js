const baseURL = "http://localhost:8080";

export const fetchTeamSubmissions = async (status) => {
    let url = `${baseURL}/api/manager/expenses`;
    if (status) {
        url += `?status=${encodeURIComponent(status)}`;
    }
    const response = await fetch(url, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json",
        },
    });
    if (!response.ok) {
        throw new Error("Could not fetch team submissions");
    }
    return await response.json();
};

export const updateExpenseStatus = async ( expenseId, status, expense) => {
    const response = await fetch(`${baseURL}/api/manager/expenses/${expenseId}/${status.toLowerCase()}`, {
        method: "PUT",
        credentials:"include",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(expense),
    });
    if (!response.ok) {
        const error = await response.json();
        throw new Error(`Failed to ${status.toLowerCase()} expense : ${error.message} `);
    }
    return await response.json();
};
