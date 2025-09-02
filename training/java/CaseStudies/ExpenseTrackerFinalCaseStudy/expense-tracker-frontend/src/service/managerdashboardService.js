const baseURL = "http://localhost:8080";

export const fetchApprovedAmounts = async () => {
    const response = await fetch(`${baseURL}/api/manager/expenses/approvedAmounts`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json",
        },
    });
    if (!response.ok) {
        throw new Error("Could not fetch approved amounts");
    }
    return await response.json();
};

export const fetchEmployeeList = async () => {
    const response = await fetch(`${baseURL}/api/manager/expenses/employees`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json",
        },
    });
    if (!response.ok) {
        throw new Error("Could not fetch employee list");
    }
    return await response.json();
};

export const fetchCategoryWiseApproved = async () => {
    const response = await fetch(`${baseURL}/api/manager/expenses/categoryWiseApproved`, {
        method: "GET",
        credentials:"include",
        headers: {
            "Content-Type": "application/json",
        },
    });
    if (!response.ok) {
        throw new Error("Could not fetch category wise approved amounts");
    }
    return await response.json();
};
