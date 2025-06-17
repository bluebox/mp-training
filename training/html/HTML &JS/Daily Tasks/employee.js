function add(){
    let eName=document.getElementById("name").value;
    let age=document.getElementById("Age").value;
    let email=document.getElementById("email").value;
    let phno=document.getElementById("phno").value;
    for(let i=0;i<localStorage.length;i++){
        let index=localStorage.key(i);
        let emp=JSON.parse(localStorage.getItem(index));
        if(emp.Email==email){
            alert("The email "+email+" is already existed");
            return;
        }
    }
    for(let i=0;i<localStorage.length;i++){
        let index=localStorage.key(i);
        let emp=JSON.parse(localStorage.getItem(index));
        if(emp.Phoneno===phno){
            alert("This phone number is already existed");
            return;
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
    let city=document.getElementById("city").value;
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
}