import axios from "axios";
const REST_API_BASE_URL = 'http://localhost:8080/';

export const issuebooks = async (issueData) => {
    let response = null;
    if (!issueData.bookId || !issueData.memberId) {
        throw "missing data. Please fill all the fields!"
    } else {
        try {
            response = await axios.post(`${REST_API_BASE_URL}issuebook`,issueData)
            return response
        } catch (error) {
            throw error.response.data
        }
    }
}
export const returnbooks = async (returnbook) => {
    let response = null;
    if (!returnbook.issueId) {
        throw "missing data. Please fill all the fields!"
    } else {
        try {
            response = await axios.post(`${REST_API_BASE_URL}returnBook`,returnbook)
            return response
        } catch (error) {
            throw error.response.data
        }
    }
}

export const getRecords = () => {
    return axios.get(REST_API_BASE_URL + "getIssuedrecords");
}

export const getmemberBooks = async (memberId) => {
    let response = null;
    if(!memberId){
        throw "missing memberId!"
    }
    else{
        try{
            response = await axios.post(`${REST_API_BASE_URL}getMemberBooks`,memberId)
            return response
        }
        catch (error) {
            throw error.response.data
        }
    }
}

export const memberBooks = async (memberId) => {
    let response = null;
    if(!memberId){
        throw "missing memberId!"
    }
    else{
        try{
            response = await axios.post(`${REST_API_BASE_URL}memberBooks`,memberId)
            return response
        }
        catch (error) {
            throw error.response.data
        }
    }
}

export const bookMembers = async (bookData) => {
    let response = null;
    if(!bookData){
        throw "missing book_Id!"
    }
    else{
        try{
            response = await axios.post(`${REST_API_BASE_URL}bookMembers`,bookData)
            return response
        }
        catch (error) {
            throw error.response.data
        }
    }
}