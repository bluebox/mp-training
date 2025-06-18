async function testApi() {
    url = "http://192.168.0.73:32114/partner/get-states?countryCode=IN";
    try{
        const response = await fetch(url);
        if(!response.ok)
        {
            console.log("Error");   
            throw new Error(response.status);
        }
        console.log("no error")
        const json = await response.json();
        console.log(json);
    }catch(e)
    {
        console.error(e.message);
    }
}
testApi()