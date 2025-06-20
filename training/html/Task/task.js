let persons = [];
let editIndex = null;

function updateTable() {
    const table = document.querySelector('.t1');
    while (table.rows.length > 2) {
        table.deleteRow(2);
    }
    let n=persons.length
    document.getElementById("entries").innerHTML="Entries : "+n;
    persons.forEach((person, idx) => {
        const row = table.insertRow();
        row.innerHTML = `
            <td>${person.name}</td>
            <td>${person.email}</td>
            <td>${person.age}</td>
            <td>${person.phone}</td>
            <td>${person.branch}</td>
            <td>${person.languages.join(',')}</td>
            <td>${person.state || ''}</td>
            <td>${person.city || ''}</td>
            <td>
                <button onclick="editPerson(${idx})">Edit</button>
                <button onclick="deletePerson(${idx})">Delete</button>
            </td>
        `;
    });
}

// filter the person data
function filterTable(colIndex, query) {
    const table = document.querySelector('.t1');
    const rows = Array.from(table.rows).slice(2); 
    query = query.toLowerCase();

    rows.forEach(row => {
        const cellText = row.cells[colIndex].textContent.toLowerCase();
        if (cellText.includes(query)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

// fetching data from api's
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('myForm');
    const stateSelect = document.getElementById('state');
    const citySelect = document.getElementById('city');

    fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
        .then(response => response.json())
        .then(data => {
            if (data.responseStatus === "SUCCESS") {
                const states = JSON.parse(data.response);
                Object.entries(states).forEach(([stateName, stateCode]) => {
                    const option = document.createElement('option');
                    option.value = stateCode;
                    option.textContent = stateName;
                    stateSelect.appendChild(option);
                });
            } 
            else {
                alert("Failed to load the state!");
            }
        })
        .catch(err => console.error("Error fetching states:", err));

    stateSelect.addEventListener('change', function() {
        const selectedStateCode = this.value;
        citySelect.innerHTML = '<option value="">Select City</option>'; 

        if (!selectedStateCode) return;

        fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${selectedStateCode}`)
            .then(response => response.json())
            .then(data => {
                if (data.responseStatus === "SUCCESS") {
                    const cities = JSON.parse(data.response);
                    Object.entries(cities).forEach(([cityName, cityCode]) => {
                        const option = document.createElement('option');
                        option.value = cityName;
                        option.textContent = cityName;
                        citySelect.appendChild(option);
                    });
                } 
                else {
                    alert("Failed to load the cities!");
                }
            })
            .catch(err => console.error("Error fetching cities:", err));
    });

    // form submission
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value.trim();
        const email = document.getElementById('email').value.trim();
        const age = document.getElementById('age').value.trim();
        const phone = document.getElementById('phone').value.trim();

        let branch = '';
        if (document.getElementById('cse').checked) 
            branch = 'CSE';
        else if (document.getElementById('ece').checked) 
            branch = 'ECE';
        else if (document.getElementById('eee').checked) 
            branch = 'EEE';

        const languages = [];
        if (document.getElementById('english').checked) 
            languages.push('English');
        if (document.getElementById('hindi').checked) 
            languages.push('Hindi');
        if (document.getElementById('telugu').checked) 
            languages.push('Telugu');
        if (document.getElementById('french').checked) 
            languages.push('French');

        const state = stateSelect.options[stateSelect.selectedIndex]?.text || '';
        const city = citySelect.options[citySelect.selectedIndex]?.text || '';

        // Validation of input fields in the form
        let errorMsg = '';
        const nameRegexp = /^[A-Za-z ]+$/;
        const gmailRegexp = /^[a-z0-9]\S+@gmail\.com$/;

        if (!name || !email || !age || !phone) {
            errorMsg = 'All fields are required.';
        } 
        else if (!nameRegexp.test(name)) {
            errorMsg = 'Name must contain only alphabetic characters.';
        } 
        else if (!gmailRegexp.test(email)) {
            errorMsg = 'Email must be a lowercase and valid Gmail address (must end with @gmail.com).';
        } 
        else if (!(Number(age) > 0 )) {
            errorMsg = 'Age must be a Positive Number!.';
        } 
        else if (!/^\d{10}$/.test(phone)) {
            errorMsg = 'Phone number must be exactly 10 digits.';
        } 
        else if (!branch) {
            errorMsg = 'Please select a branch.';
        } 
        else if (languages.length === 0) {
            errorMsg = 'Please select at least one language.';
        } 
        else if (!stateSelect.value) {
            errorMsg = 'Please select a state.';
        } 
        else if (!citySelect.value) {
            errorMsg = 'Please select a city.';
        }

        if (errorMsg) {
            alert(errorMsg);
            return;
        }

        const formData = {
            name,
            email,
            age,
            phone,
            branch,
            languages,
            state,
            city
        };

        if (editIndex !== null) {
            persons[editIndex] = formData;
            editIndex = null;
        } else {
            persons.push(formData);
        }

        updateTable();
        form.reset();
        citySelect.innerHTML = '<option value="">Select City</option>'; 
        form.querySelector('button[type=submit]').textContent = 'Submit';
    });
});

// Delete Person Data
function deletePerson(idx) {
    persons.splice(idx, 1);
    updateTable();
}

// Update Person Data
function editPerson(idx) {
    const person = persons[idx];
    document.getElementById('name').value = person.name;
    document.getElementById('email').value = person.email;
    document.getElementById('age').value = person.age;
    document.getElementById('phone').value = person.phone;

    document.getElementById('cse').checked = person.branch === 'CSE';
    document.getElementById('ece').checked = person.branch === 'ECE';
    document.getElementById('eee').checked = person.branch === 'EEE';

    document.getElementById('english').checked = person.languages.includes('English');
    document.getElementById('hindi').checked = person.languages.includes('Hindi');
    document.getElementById('telugu').checked = person.languages.includes('Telugu');
    document.getElementById('french').checked = person.languages.includes('French');

    const stateSelect = document.getElementById('state');
    const citySelect = document.getElementById('city');

    const statesOptions = Array.from(stateSelect.options);
    const stateOption = statesOptions.find(opt => opt.text === person.state);
    if (stateOption) {
        stateSelect.value = stateOption.value;

        fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateOption.value}`)
            .then(res => res.json())
            .then(data => {
                citySelect.innerHTML = '<option value="">Select City</option>';
                if (data.responseStatus === "SUCCESS") {
                    const cities = JSON.parse(data.response);
                    Object.entries(cities).forEach(([cityName, cityCode]) => {
                        const option = document.createElement('option');
                        option.value = cityName;
                        option.textContent = cityName;
                        citySelect.appendChild(option);
                    });
                    citySelect.value = person.city;
                }
            })
            .catch(err => {
                console.error("Error fetching cities on edit:", err);
                citySelect.innerHTML = '<option value="">Select City</option>';
            });
    } 
    else {
        stateSelect.value = '';
        citySelect.innerHTML = '<option value="">Select City</option>';
    }

    editIndex = idx;
    document.querySelector('#myForm button[type=submit]').textContent = 'Update';
}

