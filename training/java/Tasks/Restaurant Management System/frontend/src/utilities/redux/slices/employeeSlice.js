import { createSlice } from "@reduxjs/toolkit";

const employeeSlice=createSlice({
    name: "employees",
    initialState: {
        employees: [],
        activeEmployeeCount: 0
    },
    reducers: {
        setEmployees: (state, action) => {
            state.employees = action.payload;
            state.activeEmployeeCount = action.payload.filter(emp => emp.status === "ACTIVE").length;
        },
        addEmployee: (state, action) => {
            state.employees.push(action.payload);
            state.activeEmployeeCount += 1;
        },
        updateEmployee: (state, action) => {
            const index = state.employees.findIndex(emp => emp.empId === action.payload.empId);
            if (index !== -1) {
                state.employees[index] = action.payload;
            }
        },
        deleteEmployee: (state, action) => {
            const index = state.employees.findIndex(emp => emp.empId === action.payload);
            if (index !== -1) {
                state.employees[index] = {...state.employees[index], status: 'INACTIVE', leaving_date: new Date().toISOString().split('T')[0]};
                state.activeEmployeeCount -= 1;
            }
        }
    }
})

export const { setEmployees, addEmployee, updateEmployee, deleteEmployee } = employeeSlice.actions;
export default employeeSlice.reducer;