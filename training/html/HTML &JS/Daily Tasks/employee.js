window.onload=function(){
    render();
    loadStates();
}
function loadStates(){
    state = document.getElementById("state");
    state.innerHTML = "";
    fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
    .then(response => response.json())
    .then(data => {
        if(data.responseStatus === "SUCCESS"){
            const states = JSON.parse(data.response);
            Object.entries(states).forEach(([name, code]) => {
                const opt = document.createElement("option");
                opt.value = code;
                opt.textContent = name;
                state.appendChild(opt);
            });
            state.addEventListener('change',loadCities);
        } else {
            city.textContent = "Failed to load states.";
        }
    })
    .catch(err => {
        console.error("Error fetching states:", err);
        city.textContent = "Error loading states.";
    });
}
function loadCities(){
    city = document.getElementById("city");
    city.innerHTML = "";
    const sel = document.createElement("select");
    sel.id = "citySelect";
    st=document.getElementById('state').value;
    alert(st);
    fetch('http://192.168.0.73:32114/partner/get-cities-for-state?stateCode='+st)
    .then(response => response.json())
    .then(data => {
        if(data.responseStatus === "SUCCESS"){
            const cities = JSON.parse(data.response);
            Object.entries(cities).forEach(([name, code]) => {
                const opt = document.createElement("option");
                opt.value = code;
                opt.textContent = name;
                sel.appendChild(opt);
            });
            city.appendChild(sel);
        } else {
            city.textContent = "Failed to load states.";
        }
    })
    .catch(err => {
        console.error("Error fetching states:", err);
        city.textContent = "Error loading states.";
    });
}
let state=document.getElementById("state").value;

function add(){
    let eName=document.getElementById("name").value;
    let age=document.getElementById("Age").value;
    let email=document.getElementById("email").value;
    let phno=document.getElementById("phno").value;
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
    document.querySelector("form").reset();
    render();
}
function deletion(i){
    localStorage=localStorage.removeItem(i.trim());
    render();
}
function update(i){
    emp=localStorage.getItem(i);
    emp.Name=prompt("Enter the name");
    emp.Age=prompt("Enter the Age");
}
function render(){
    fill=document.getElementById("fill");
    fill.innerHTML="";
    tab=document.createElement("table");
    tab.style.border="3px solid";
    c=document.createElement("tr");
    x=document.createElement("th");
    x.style.border="3px solid";
    x.style.padding="3px";
    x.textContent="Name";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="Age";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="Email"
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="Phone No";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="Branch";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="Languages";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="State";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    x=document.createElement("th");
    x.textContent="City";
    x.style.border="3px solid";
    x.style.padding="3px";
    c.appendChild(x);
    tab.appendChild(c);
    for(let i=0;i<localStorage.length;i++){
        let index=localStorage.key(i);
        emp=JSON.parse(localStorage.getItem(index));
        c=document.createElement("tr");
        x=document.createElement("td");
        x.textContent=emp.Name;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.Age;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.Email;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.Phoneno;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.Branch;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.Languages;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.State;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.textContent=emp.City;
        x.style.border="3px solid";
        x.style.padding="3px";
        c.appendChild(x);
        x=document.createElement("td");
        x.style.border="3px solid";
        x.style.padding="3px";
        y=document.createElement("button");
        y.textContent="Edit";
        y.style.padding="5px";
        y.onclick=function(){
            update(emp.Email);
        };
        x.appendChild(y);
        y=document.createElement("button");
        y.textContent="Delete";
        y.onclick = function(){
            deletion(emp.Email);
        };
        y.style.padding="5px";
        x.appendChild(y);
        c.appendChild(x);
        tab.appendChild(c);
    }
    fill.appendChild(tab);
}