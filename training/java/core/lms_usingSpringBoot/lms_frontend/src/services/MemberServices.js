import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/';

export const listMembers = () => {
    return axios.get(REST_API_BASE_URL+"getmembers");
}

export const getMemberById = (id) => {
    return axios.post(`${REST_API_BASE_URL}getmember/${id}`); 
}

export const addmember = async (memberData) => {
    let response = null;
    if (!memberData.member_Name || !memberData.email || !memberData.mobile_No || !memberData.gender ||!memberData.address) {
        throw "missing data. Please fill all the fields!"
    } else {
        try {
            response = await axios.post(`${REST_API_BASE_URL}addmember`,memberData)
            return response
        } catch (error) {
            throw error.response.data
        }
    }

}

export const updatemember = async (memberData) => {
    let response = null;
    if (!memberData.member_Name || !memberData.email || !memberData.mobile_No || !memberData.gender ||!memberData.address || !memberData.member_Id) {
        throw new Error("Missing data. Please fill all the fields");
    } else {
        try {
            response = await axios.put(
                `${REST_API_BASE_URL}updatemember`,
                memberData
            );
            return response;
        } catch (error) {
            throw error.response.data;
        }
    }
}