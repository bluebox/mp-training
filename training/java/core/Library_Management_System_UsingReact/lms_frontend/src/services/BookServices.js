import axios from "axios";
const REST_API_BASE_URL = 'http://localhost:8080/';

export const listBooks = () => {
    return axios.get(REST_API_BASE_URL + "getbooks");
}

export const getBookById = (bookId) => {
    return axios.post(`${REST_API_BASE_URL}get/${bookId}`);
    
}

export const getAvailableBooks = () => {
    return axios.get(`${REST_API_BASE_URL}getAvailablebooks`);
}

export const updatebook = async (book_Id,bookData) => {
    let response = null;
    if (!(bookData.book_Title && bookData.book_Author && bookData.book_Category && bookData.book_Status && bookData.book_Availability && book_Id)) {
        throw new Error("Missing data. Please fill all the fields");
    } else {
        try {
            response = await axios.put(
                `${REST_API_BASE_URL}updatebook/${book_Id}`,
                bookData
            );
            return response;
        } catch (error) {
            throw error.response.data;
        }
    }
}

export const addbook = async (bookData) => {
    let response = null;
    if (!bookData.book_Title || !bookData.book_Author || !bookData.book_Category) {
        throw "missing data. Please fill all the fields!"
    } else {
        try {
            response = await axios.post(`${REST_API_BASE_URL}addbook?book_Title=${bookData.book_Title}&book_Author=${bookData.book_Author}&book_Category=${bookData.book_Category}`)
            return response
        } catch (error) {
            throw error.response.data
        }
    }

}