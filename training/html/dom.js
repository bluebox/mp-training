// const { json } = require("stream/consumers");

document.addEventListener("DOMContentLoaded", function () {
        let userId = 0;
        let selectedUserId = null;
        let alterSelect = false;
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

        document.getElementById("state").addEventListener("change", async function () {
            const cities = await getCities(); 
            const citySelect = document.getElementById("city");
            citySelect.innerHTML = "";

            if (cities) {
                for (const city in cities) {
                    const option = document.createElement("option");
                    option.value = city;
                    option.text = city;
                    citySelect.appendChild(option);
                }
            }
        });



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

            // Remove all previous validation messages
            form.querySelectorAll(".invalid").forEach(el => el.classList.remove("invalid"));
            form.querySelectorAll(".error-msg").forEach(el => el.remove());

            // Helper to show error
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

            

            if (!data.phone  ||data.phone.toString().length !== 10) {
                showError("phone", "Phone No. must be exactly 10 digits.");
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

            
            if (!data.city || data.city.trim() === "") {
                showError("city", "Please select a city.");
            }

            return isValid;
        }


        function getFormData() {
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
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

            Object.values(user).filter((_, i) => i !== 0).forEach(val => {
                const cell = document.createElement("td");
                cell.textContent = val;
                row.appendChild(cell);
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

                selectedUserId = user.id;
                alterSelect = true;
                row.remove(); 
            });

            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "Delete";
            deleteBtn.classList.add("action-btn");
            deleteBtn.addEventListener("click", function () {
                const index = users.findIndex(u => u.id === user.id);
                if (index !== -1) {
                    users.splice(index, 1);
                    renderTable();
                }
            });

            actionCell.appendChild(editBtn);
            actionCell.appendChild(deleteBtn);
            row.appendChild(actionCell);

            tableBody.appendChild(row);
        }

        form.addEventListener("submit", function (e) {
            if(!alterSelect){
                e.preventDefault();
                const { data, langs } = getFormData();

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

                var user;
                for(var usr of users)
                {
                    if(usr.id === selectedUserId)
                    {
                        user = usr;
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
                    renderTable();
                    form.reset();
                }
                alterSelect = false;
            }
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
        
            const filteredData = users.filter(user => {
                return (
                    user.name.toLowerCase().startsWith(name) &&
                    user.age.toString().startsWith(age) &&
                    user.email.toLowerCase().startsWith(email) &&
                    user.phone.toString().startsWith(phone) &&
                    user.branch.toLowerCase().startsWith(branch) &&
                    user.langs.toLowerCase().includes(lang) &&
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
