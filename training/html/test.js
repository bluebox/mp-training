const { Console } = require("console");
const fetch = require("node-fetch");
url1 = "http://192.168.0.73:32114/partner/get-states?countryCode=IN";
url = "http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=RJ";
export async function getStates() {    
    try{
        const response = await fetch(url1);
        if(!response.ok)
        {
            console.log("Error");   
            throw new Error(response.status);
        }
        console.log("no error")
        const json = await response.json();
        console.log(json);
        res = JSON.parse(json.response);
        // console.log(res["RAJASTHAN"]);
        return Object.keys(res);
        // for(const r of Object.keys(res)){
        //     console.log(r);
        // }
    }catch(e)
    {
        console.error(e.message);
    }
}
getStates();