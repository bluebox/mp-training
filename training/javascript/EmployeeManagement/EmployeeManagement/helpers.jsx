  import axiosInstance from './src/api/axiosInstance'
  export const fetchEmployees = async () => {
    try {
      const res = await axiosInstance.get("/employee/list/");
      return res.data.results || [];
    } catch (err) {
      console.error("Error fetching employees", err);
    }
  };