function step1(callback) {
    setTimeout(() => {
        console.log("Step 1 completed");
        callback();
    }, 1000);
}

function step2(callback) {
    setTimeout(() => {
        console.log("Step 2 completed");
        callback();
    }, 1000);
}

function step3(callback) {
    setTimeout(() => {
        console.log("Step 3 completed");
        callback();
    }, 1000);
}

// step1(() => {
//     step2(() => {
//         step3(() => {
//             console.log("All steps completed");
//         });
//     });
// });
function divide(a,b,mul){
    if(b==0)
        mul(new Error("Cannot devide by zero"),null);
    else
        mul(null,a/b);
}
function error(error,result){
    if(error){
        console.log(error.message);
    }
    else
    console.log(result);
}
//divide(10,0,error);
var headers = new Headers();
headers.append("X-CSCAPI-KEY", "https://api.countrystatecity.in/");

var requestOptions = {
  method: 'GET',
  headers: headers,
  redirect: 'follow'
};
// function fetch(callback) {
//         fetch("https://api.countrystatecity.in/v1/countries/IN/states")
//         .then(response => response.json())
//         .then(data => callback(data))
//         .catch(error => console.error("Error:", error));
// }

function handle(data) {
    data.forEach(e => {
        console.log("Fetched Data:", e);
    });
    
}

async function states(){
    let apiKey="https://api.countrystatecity.in/";
    let resp= await fetch("https://api.countrystatecity.in/v1/countries/IN/states",{
        headers: {
          'X-CSCAPI-KEY': apiKey
        }
    });
    let data=await resp.json();
    handle(data);
}
window.onload=states;

//fetch(handle);