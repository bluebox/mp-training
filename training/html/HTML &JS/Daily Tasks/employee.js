let listOfPhno=[];
function add(){
    let eName=document.getElementById("name").value;
    let age=document.getElementById("age").value;
    let email=document.getElementById("email").value;
    let phno=document.getElementById("phno").value;
    if(!listOfPhno.find((x)=>{
        return x==phno.value;
    })!=undefined){
        listOfPhno.push(phno.v);
    }
}