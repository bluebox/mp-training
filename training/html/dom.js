

let statesJson = null;
document.addEventListener("DOMContentLoaded", function () {
        let userId = 0;
        let selectedUserId = null;
        let alterSelect = false;
        let submitSelect = false;
        
        class User {
            constructor(name, age, email, phone, branch, langs, state, city) {
                this.id = ++userId;
                this.name = name;
                this.age = age;
                this.email = email;
                this.phone = phone;
                this.branch = branch;
                this.langs = langs;
                this.state = state;
                this.city = city;
            }
        }
        async function getCities(){

            const statecode = document.getElementById("state");
            // if(statecode.value === null)
            // {

            // }
            console.log(statecode);

            url = "http://192.168.0.73:32114/partner/get-cities-for-state?stateCode="+statecode.value;
            try{
                var response = await fetch(url);
                if(!response.ok)
                {
                    console.log("Error");
                    throw new Error(response.status)
                }
                json = await response.json();
                console.log(json);
                res = JSON.parse(json.response);
                return res;
            }catch(e)
            {
                console.error(e);
            }
        }
        async function citiesFetch() {
            console.log("Value", this.value)
            if(this.value !== ""){
                console.log("in cities");
                const cities = await getCities(); 
                const citySelect = document.getElementById("city");
                citySelect.innerHTML = "";
                console.log(this.value);
                if (cities) {
                    for (const city in cities) {
                        const option = document.createElement("option");
                        option.value = city;
                        option.text = city;
                        citySelect.appendChild(option);
                    }
                }
            }else{
                console.log("jashgdjkas")
                document.getElementById("city").selectedIndex = 0;
            }
        }

        document.getElementById("state").addEventListener("change", citiesFetch);



        // getCities().then(cities =>{
        //     if(cities){
        //         for(const city of cities){
        //             const ele = document.createElement("option");
        //             ele.value = cities[city];
        //             ele.text = city;
        //             document.getElementById("cities").appendChild(ele);
        //         }
        //     }
        // });

        async function getStates() {  
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
                res = JSON.parse(json.response);
                // console.log(res["RAJASTHAN"]);
                // return Object.keys(res);
                return res
                // for(const r of Object.keys(res)){
                //     console.log(r);
                // }
            }catch(e)
            {
                console.log("Error 1");
                console.error(e.message);
            }
        }

        getStates().then(states =>{
            if(states){
                statesJson = states;
                for(const key in states)
                {
                    const ele = document.createElement("option");
                    ele.value = states[key];
                    ele.text = key;

                    document.getElementById("state").appendChild(ele);
                }
            }
        });



        const users = [];
        const form = document.getElementById("userForm");
        const tableBody = document.querySelector("table tbody");
        const alterBtn = document.getElementById("alter");

        function validateForm(data, lang) {
            let isValid = true;

            
            form.querySelectorAll(".invalid").forEach(el => el.classList.remove("invalid"));
            form.querySelectorAll(".error-msg").forEach(el => el.remove());

            
            function showError(inputId, message) {
                const input = form.querySelector(`#${inputId}`);
                input.classList.add("invalid");

                const error = document.createElement("p");
                error.textContent = message;
                error.classList.add("error-msg");
                error.style.color = "red";
                error.style.fontSize = "0.7em";

                input.parentElement.appendChild(error);
                isValid = false;
            }

            
            if (!data.name.trim() || data.name.length < 3) {
                showError("name", "Name should be at least 3 characters.");
            }

            if(data.name.match(/\d/) !== null)
            {
                showError("name","Name should not have any digits");
            }

            
            if (!data.age || isNaN(data.age)|| !/^\d+$/.test(data.age) || data.age <= 0 || data.age > 100) {
                showError("age", "Enter a valid number age between (1-100).");
            }

    
            if (!data.email || !/^\S+@\S+\.\S+$/.test(data.email)) {
                showError("email", "Enter a valid email address.");
            }
            if(chechMail(data.email))
            {
                showError("email","Email already Taken. Please use another");
            }

            function checkPhone(phone)
            {
                if(!alterSelect)
                {
                    for(let usr of users)
                    {
                        if(usr.phone.toString() === phone.toString())
                        {
                            return true;
                        }
                    }
                }else{
                    for(let usr of users)
                    {
                        if(usr.phone.toString() === phone.toString() && usr.id !== selectedUserId)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }

            function chechMail(mail)
            {
                if(!alterSelect)
                {
                    for(let usr of users)
                    {
                        if(usr.email === mail)
                        {
                            return true;
                        }
                    }
                }else{
                    for(let usr of users)
                    {
                        if(usr.email === mail && usr.id !== selectedUserId)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }

            if (!data.phone  ||data.phone.toString().length !== 10) {
                showError("phone", "Phone No. must be exactly 10 digits.");
            }
            if(checkPhone(data.phone))
            {
                showError("phone","Phone No. already in use. Please use another Phone No.");
            }

            if(!/^\d+$/.test(data.phone))
            {
                showError("phone","Phone No. must not have any characters");
            }
            
            if (!data.branch) {
                form.querySelectorAll('input[name="branch"]').forEach(rb => rb.classList.add("invalid"));
                isValid = false;
            }

            
            if (lang.length === 0) {
                const langLabel = form.querySelector(".form-group .check");
                const langGroup = form.querySelector('.check');
                const error = document.createElement("p");
                error.textContent = "Please select at least one language.";
                error.classList.add("error-msg");
                error.style.color = "red";
                error.style.fontSize = "0.7em";
                langGroup.appendChild(error);

                form.querySelectorAll('input[name="language"]').forEach(cb => cb.classList.add("invalid"));
                isValid = false;
            }

            
            if (!data.state || data.state.trim() === "") {
                showError("state", "Please select a state.");
            }

            if(document.getElementById("city").options.length !== 0)
            {
                if (!data.city || data.city.trim() === "") {
                    showError("city", "Please select a city.");
                }
            }else
            {
                data.city = data.state;
            }

            return isValid;
        }

    
        function getFormData() {
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            console.log(formData);
            // data.state = formData.state.text;
            const langs = [];
            form.querySelectorAll('input[name="language"]:checked').forEach(el => langs.push(el.value));
            return { data, langs };
        }

        function renderTable() {
            tableBody.innerHTML = "";
            users.forEach(user => addToTable(user));
        }

        function addToTable(user) {
            const row = document.createElement("tr");
            console.log(Object.values(user));
            Object.values(user).filter((_, i) => i !== 0).forEach((val,i) => {
                if(i !== 6){
                    const cell = document.createElement("td");
                    cell.textContent = val;
                    row.appendChild(cell);
                }else{
                    console.log(i,val);
                    const cell = document.createElement("td");
                    let sel = document.getElementById("state");
                    let opt = sel.querySelectorAll('option');
                    for(let j = 0;j<opt.length;j++)
                    {
                        if(opt[j].value === val)
                        {
                            cell.textContent = opt[j].text;
                            row.appendChild(cell);
                            break;
                        }
                    }
                    
                }
            });

            const actionCell = document.createElement("td");

            

            const editBtn = document.createElement("button");
            editBtn.textContent = "Alter";
            editBtn.classList.add("action-btn");
            editBtn.addEventListener("click", function () {
                form.name.value = user.name;
                form.age.value = user.age;
                form.email.value = user.email;
                form.phone.value = user.phone;
                form.state.value = user.state;
                form.city.value = user.city;


                form.querySelectorAll('input[name="branch"]').forEach(rb => {
                    rb.checked = rb.value === user.branch;
                });

                const langSet = new Set(user.langs.split(","));
                form.querySelectorAll('input[name="language"]').forEach(cb => {
                    cb.checked = langSet.has(cb.value);
                });

                // Object.assign(alterUserInfo,user);

                selectedUserId = user.id;
                alterSelect = true;
                if(alterSelect)
                {
                    cancelBtn.style.display = "block";
                }
                // row.remove(); 
            });

            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "Delete";
            deleteBtn.classList.add("action-btn");
            deleteBtn.addEventListener("click", function () {
                const index = users.findIndex(u => u.id === user.id);
                if (index !== -1) {
                    if(confirm("Do You want to delete?"))
                    {
                        users.splice(index, 1);
                        renderTable();
                    }else{

                    }
                }
            });


            actionCell.appendChild(editBtn);
            actionCell.appendChild(deleteBtn);
            row.appendChild(actionCell);

            tableBody.appendChild(row);
        }

        const cancelBtn = document.createElement("button");
        cancelBtn.textContent = "Cancel";
        cancelBtn.classList.add("submit-buttons");
        cancelBtn.addEventListener("click",function(e){
            form.reset();
            form.querySelectorAll(".invalid").forEach(el => el.classList.remove("invalid"));
            form.querySelectorAll(".error-msg").forEach(el => el.remove());
            document.getElementById("city").value = -1;
            renderTable();
            cancelBtn.style.display = "none";  
        });
        cancelBtn.style.display = "none";
        document.getElementById("userForm").appendChild(cancelBtn);

        

        form.addEventListener("submit", function (e) {
            if(!alterSelect){
                e.preventDefault();
                const {data,langs} = getFormData();
                if (!validateForm(data, langs)) return;

                const newUser = new User(
                    data.name,
                    data.age,
                    data.email,
                    data.phone,
                    data.branch,
                    langs.join(","),
                    data.state,
                    data.city
                );
                users.push(newUser);
                renderTable();
                form.reset();
                
            }else{
                e.preventDefault();
                const {data,langs} = getFormData();
                if (!validateForm(data, langs)) return;

                let user;
                let tempUser;

                // users.forEach(
                //     (u,i)=>{
                //         if(users[i].id === selectedUserId)
                //         {
                //             tempUser = users[i];
                //         }
                //     }
                // )

                for(var usr of users)
                {
                    if(usr.id === selectedUserId)
                    {
                        user = usr;
                        tempUser = new User(usr.name,usr.age,usr.email,usr.phone,usr.branch,usr.langs,usr.state,usr.city);
                    }
                }
                if (user) {


                    user.name = data.name;
                    user.age = data.age;
                    user.email = data.email;
                    user.phone = data.phone;
                    user.branch = data.branch;
                    user.langs = langs.join(",");
                    user.state = data.state;
                    user.city = data.city;

                    selectedUserId = null;
                    if(confirm("Do you want to apply changes"))
                    {
                        
                    }else{
                        console.log("tmep :",tempUser);
                        Object.assign(user,tempUser);
                    }
                    renderTable();
                    
                }
                alterSelect = false;
                form.reset();
                cancelBtn.style.display = "none";
            }
            document.getElementById("city").value = -1;
        });

        // alterBtn.addEventListener("click", function (e) {
            
        // });
        
        function filterUsers() {
            const name = document.getElementById("name-search").value.toLowerCase();
            const age = document.getElementById("age-search").value.toLowerCase();
            const email = document.getElementById("email-search").value.toLowerCase();
            const phone = document.getElementById("phone-search").value.toLowerCase();
            const branch = document.getElementById("branch-search").value.toLowerCase();
            const lang = document.getElementById("lang-search").value.toLowerCase();
            const state = document.getElementById("state-search").value.toLowerCase();
            const city = document.getElementById("city-search").value.toLowerCase();
        
            if (
                !name && !age && !email && !phone &&
                !branch && !lang && !state && !city
            ) {
                renderTable();
                return;
            }
            
            function check(langSet,lang)
            {
                for(var l of langSet)
                {
                    if(l.startsWith(lang))
                    {
                        return true;
                    }
                }
                return false;
            }
            const filteredData = users.filter(user => {
                langSet = user.langs.split(",");
                langSet.forEach((u,index)=>{
                    langSet[index] = u.toLowerCase();
                })
                console.log(langSet);
                return (
                    user.name.toLowerCase().startsWith(name) &&
                    user.age.toString().startsWith(age) &&
                    user.email.toLowerCase().startsWith(email) &&
                    user.phone.toString().startsWith(phone) &&
                    user.branch.toLowerCase().startsWith(branch) &&
                    check(langSet,lang) &&
                    user.state.toLowerCase().startsWith(state) &&
                    user.city.toLowerCase().startsWith(city)
                );
            });
        
            tableBody.innerHTML = "";
            filteredData.forEach(user => addToTable(user));
        }
        

        const fields = document.querySelectorAll(".search").forEach(input =>{
            input.addEventListener("input",filterUsers);
        })
    });
