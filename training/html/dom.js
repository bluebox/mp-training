document.addEventListener("DOMContentLoaded",function(){
    class user{
        constructor(name,age,email,phone,branch,langs,state,city)
        {
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

    var users = []; 

    document.getElementById("userForm").addEventListener("submit",
        function(e){
            e.preventDefault();
            const form = e.target;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            const lang = [];
            form.querySelectorAll('input[name="language"]:checked').forEach(element => {
                lang.push(element.value);
            });

            const newObj = new user(
                data.name,
                data.age,
                data.email,
                data.phone,
                data.branch,
                lang,
                data.state,
                data.city
            );
            console.log(newObj);
            users.push(newObj);
            let row = document.createElement("td");
            row.
        }
    )
});

