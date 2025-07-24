import axios from "axios";
import { BASEURL, REFRESH } from "./urls";

async function customAXIOS(url, params, method = 'get', body,navigate) {
    if(method === "login")
    {
        console.log("in login")
        // const creds =body
        const response = await fetch("http://127.0.0.1:8000/api/token/",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(body)
        })
        const data =await response.json() 
        console.log(data)
        localStorage.setItem("jwtAccessToken",data.access)
        localStorage.setItem("jwtRefreshToken",data.refresh)
        console.log(localStorage.getItem("jwtAccessToken"))
        console.log(localStorage.getItem("jwtRefreshToken"))
        return data
    }
    const api = axios.create({
        baseURL: BASEURL,
    });
    
    const Atoken = localStorage.getItem("jwtAccessToken");
    const Rtoken = localStorage.getItem("jwtRefreshToken");

    let config = {
        headers: {
            Authorization: `Bearer ${Atoken}`,
        },
        params: params || {},
    };

    try {
        if (method.toLowerCase() === "get") {
            const res = await api.get(url, config);
            return res.data;
        } else if (method.toLowerCase() === "post") {
            const res = await api.post(url, body, config);
            return res.data;
        }else if (method.toLowerCase() === "put") {
            const res = await api.put(url, body, config);
            return res.data;
        } else if (method.toLowerCase() === "delete") {
            const res = await api.delete(url, config);
            return res.data;
        }

    } catch (error) {
        
        if (error.response && error.response.status === 401 && Rtoken) {
            try {
                const refreshRes = await api.post(REFRESH, { refresh: Rtoken });
                const newAccessToken = refreshRes.data.access;
                localStorage.setItem("jwtAccessToken", newAccessToken);

                config.headers.Authorization = `Bearer ${newAccessToken}`;
                if (method.toLowerCase() === "get") {
                    const res = await api.get(url, config);
                    return res.data;
                } else if (method.toLowerCase() === "post") {
                    const res = await api.post(url, body, config);
                    return res.data;
                }
            } catch (refreshError) {
                console.error("Token refresh failed:", refreshError);
                navigate("/logout")
            }
        } else {
            console.error("API error:", error);
            throw error;
        }
    }
}

export default customAXIOS 