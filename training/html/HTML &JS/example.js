
var s={
    name : "Bhanu",
    class : "BTech"
}
s.name="Srinu";
console.log(s);
localStorage.setItem(s.name,s);

function fun(){
    const x=document.querySelector('input[name="branch"]:checked');
    alert(x);
    if(x){
        console.log(x.value);
    }
    else{
        console.log("branch not found");
    }
}
function fun1(){
    let x=document.querySelectorAll('input[name="lang"]:checked');
    
    let l=[];
    x.forEach(element => {
        l.push(element.value);
    });
    console.log(l);
}

function f2(){
    x=document.querySelectorAll('input[name="branch"]');
    x.forEach((a)=>{
        alert(a.value,document.querySelector('input[name="branch"]:checked'));
    });
    document.getElementById("cse").checked=true;
}
function update(i){
    emp=JSON.parse(localStorage.getItem(i));
    ename=document.getElementById("name");
    ename.value=emp.Name;
    age=document.getElementById("Age");
    age.value=emp.Age;
    email=document.getElementById("email");
    email.value=emp.Email;
    phno=document.getElementById("phno");
    phno.value=emp.Phoneno;
    document.querySelectorAll('input[name="branch"]').forEach((x)=>{
        if(x.value==emp.Branch){
            x.checked=true;
        }
    });
}

let eName=document.getElementById("name").value;
    if(eName==""){
        alert("Name needed");
        return;
    }
    let age=document.getElementById("Age").value;
    if(age==undefined){
        alert("Age is required");
        return;
    }
    if(age<15 || age>30){
        alert("Give a valid number");
        return;
    }
    let email=document.getElementById("email").value;
    if(email==""){
        alert("Email is required");
        return;
    }
    let phno=document.getElementById("phno").value;
    if(email==""){
        alert("Phno is required");
        return;
    }
    let update=false;
    for(let i=0;i<localStorage.length;i++){
        let index=localStorage.key(i);
        let emp=JSON.parse(localStorage.getItem(index));
        if(emp.Email==email){
            if(confirm("This email is already existed. Do you want to update the data?")){
                update=true;
            }
            else{
                return;
            }
        }
    }
    for(let i=0;i<localStorage.length;i++){
        let index=localStorage.key(i);
        let emp=JSON.parse(localStorage.getItem(index));
        if(emp.Phoneno===phno){
            if(update==false){
                alert("This phone number is already existed");
                return;
            }
        }
    }
    let branch=document.querySelector('input[name="branch"]:checked');
    if(branch==undefined){
        alert("Branch is not defined");
        return;
    }
    let c=document.querySelectorAll('input[name="lang"]:checked');
    let languages=[];
    c.forEach(element => {
        languages.push(element.value);
    });
    if(languages.length<=0){
        alert("Provide atleast a single language");
        return;
    }
    let state=document.getElementById("state").value;
    let city=document.getElementById("citySelect").value;
    let employee={
        Name : eName,
        Age : age,
        Email : email,
        Phoneno : phno,
        Branch : branch.value,
        Languages : languages,
        State : state,
        City : city
    }
    localStorage.setItem(email,JSON.stringify(employee));
    alert("Name : "+eName+"\nAge : "+age+"\nEmail : "+email+"\nPhone no : "+phno+"\nBranch : "+branch+"\nLanguages : "+languages+"\nState : "+state+"\nCity : "+city);
    alert(localStorage.getItem(email))
    alert(JSON.parse(localStorage.getItem(email)).Name+" is registered");
    document.querySelector("form").reset();
    render();