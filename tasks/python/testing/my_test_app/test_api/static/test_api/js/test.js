// const fetch = require('node-fetch');
async function get_users(){
            url = "http://127.0.0.1:8000/Users/"
            try{
                const response = await fetch(url);
                if(!response.ok){
                    console.log("Error in fetching users")
                }
                let json = await response.json();
                let res = JSON.parse(json.response);
                return res
            }catch(e)
            {
                console.error(e);
            }
        }
get_users().then(user =>{
    console.log(user);
})
