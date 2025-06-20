window.onload=function(){
    render();
    loadStates();
}
function loadStates(){
    state = document.getElementById("state");
    state.innerHTML = "";
    opt=document.createElement("option");
    opt.textContent="Select A state";
    opt.style.visibility="hidden";
    state.appendChild(opt);
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
            state.textContent = "Failed to load states.";
        }
    })
    .catch(err => {
        console.error("Error fetching states:", err);
        state.textContent = "Error loading states.";
    });
}
function loadCities(){
    city = document.getElementById("city");
    city.innerHTML = "";
    city.disabled=false;
    opt=document.createElement("option");
    opt.textContent="Select A city";
    opt.style.visibility="hidden";
    city.appendChild(opt);
    st=document.getElementById('state').value;
    fetch('http://192.168.0.73:32114/partner/get-cities-for-state?stateCode='+st)
    .then(response => response.json())
    .then(data => {
        if(data.responseStatus === "SUCCESS"){
            const cities = JSON.parse(data.response);
            Object.entries(cities).forEach(([name, code]) => {
                const opt = document.createElement("option");
                opt.value = code;
                opt.textContent = name;
                city.appendChild(opt);
            });
        } else {
            city.textContent = "Failed to load states.";
        }
    })
    .catch(err => {
        console.error("Error fetching states:", err);
        city.textContent = "Error loading states.";
    });
}

function add(){
    let eName=document.getElementById("name").value;
    if(eName==""){
        alert("Name needed");
        return;
    }
    let age=document.getElementById("Age").value;
    if(age==undefined || age==0){
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
    if(phno==0){
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
    document.querySelectorAll('input[name="lang"]').forEach((x)=>{
        if(emp.Languages.includes(x.value)){
            x.checked=true;
        }
    });
    document.getElementById("state").value=emp.State;
    document.getElementById("city").value=emp.City;
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
    x.style.width="800px";
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
        x.style.bord
        er="3px solid";
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
        y.id=emp.Email;
        y.onclick = (()=>{
            localStorage.removeItem(emp.Email);
            render();
        });
        y.style.padding="5px";
        x.appendChild(y);
        c.appendChild(x);
        tab.appendChild(c);
    }
    fill.appendChild(tab);
}