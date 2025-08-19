import axios from "axios";
const REST_API_BASE_URL = 'http://localhost:8080/';

export const getOverdueBooks = () => {
    return axios.get(`${REST_API_BASE_URL}overduelist`);
}

export const getMemberStats = () => {
    return axios.get(`${REST_API_BASE_URL}memberstats`);
}

export const getBooksByCategory = () => {
    return axios.get(`${REST_API_BASE_URL}getbooksbyCategory`);
}