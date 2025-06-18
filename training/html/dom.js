import {getStates} from "test.js";
document.addEventListener("DOMContentLoaded", function () {
        let userId = 0;
        let selectedUserId = null;

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

        const states = getStates();
        console.log(states);
        states.forEach(e =>{
            const ele = document.createElement("option");
            ele.value = e;
            ele.text = e;
            document.getElementById("state").appendChild(ele);
        })

        const users = [];
        const form = document.getElementById("userForm");
        const tableBody = document.querySelector("table tbody");
        const alterBtn = document.getElementById("alter");

        function validateForm(data, lang) {
            let isValid = true;

            form.querySelectorAll(".invalid").forEach(el => el.classList.remove("invalid"));

            if (!data.name.trim()) {
                form.name.classList.add("invalid");
                isValid = false;
            }
            if (!data.age || isNaN(data.age) || data.age <= 0) {
                form.age.classList.add("invalid");
                isValid = false;
            }
            if (!data.email || !/^\S+@\S+\.\S+$/.test(data.email)) {
                form.email.classList.add("invalid");
                isValid = false;
            }
            if (!data.phone || data.phone.length !== 10) {
                form.phone.classList.add("invalid");
                isValid = false;
            }
            if (!data.branch) {
                form.querySelectorAll('input[name="branch"]').forEach(rb => rb.classList.add("invalid"));
                isValid = false;
            }
            if (lang.length === 0) {
                form.querySelectorAll('input[name="language"]').forEach(cb => cb.classList.add("invalid"));
                isValid = false;
            }
            if (!data.state.trim()) {
                form.state.classList.add("invalid");
                isValid = false;
            }
            if (!data.city.trim()) {
                form.city.classList.add("invalid");
                isValid = false;
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
        });

        alterBtn.addEventListener("click", function (e) {
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

        });
        
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
