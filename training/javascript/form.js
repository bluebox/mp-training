document.getElementById("fn").addEventListener("click",()=>{
    const name=prompt("Enter First Name");
    if(name.trim()!==''){
        document.getElementById("fname").value=name;
    }
});
document.getElementById("ln").addEventListener("click",()=>{
    const name=prompt("Enter Last Name");
    if(name.trim()!==""){
        document.getElementById("lname").value=name;
    }
})
document.getElementById("em").addEventListener("click",()=>{
    const name=prompt("Enter Last Name");
    if(name.trim()!==""){
        document.getElementById("email").value=name;
    }
})
document.getElementById("mn").addEventListener("click",()=>{
    const name=prompt("Enter Last Name");
    if(name.trim()!==""){
        document.getElementById("mnumber").value=name;
    }
});
function sub(){
    let table=document.createAttribute("table");
    let alldata=document.getElementsByClassName("tables");

    alldata.appendChild(table);
}