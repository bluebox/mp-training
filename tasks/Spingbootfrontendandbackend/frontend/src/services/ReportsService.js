// src/services/ReportsService.js
import api from "../api/axios";

export const getBooksPerCategory = () => api.get("/reports/bookspercategory");
export const getActiveMembers = () => api.get("/reports/activemembers");
export const getOverdueBooks = () => api.get("/reports/overduebooks");
