// script.js

let records = [];
let editIndex = null;

const stateSelect = document.getElementById('state');
const citySelect = document.getElementById('city');

let statesData = [];

async function loadStates() {
    try {
        const response = await fetch('https://corsproxy.io/?https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/states.json');

        //const response = await fetch('https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/states.json');
        const states = await response.json();
        // Filter for India
        statesData = states.filter(state => state.country_code === 'IN');

        statesData.forEach(state => {
            const option = document.createElement('option');
            option.value = state.id;
            option.textContent = state.name;
            stateSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading states:', error);
    }
}

async function loadCities(stateId) {
    citySelect.innerHTML = '<option value="">Select</option>';
    try {
        const response = await fetch('https://corsproxy.io/?https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/cities.json');

        // const response = await fetch('https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/cities.json');
        const cities = await response.json();
        const filtered = cities.filter(city => city.state_id === stateId);
        filtered.forEach(city => {
            const option = document.createElement('option');
            option.value = city.name;
            option.textContent = city.name;
            citySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading cities:', error);
    }
}

stateSelect.addEventListener('change', e => {
    loadCities(parseInt(e.target.value));
});

// Form submit
const form = document.getElementById('userForm');
const tableBody = document.querySelector('#dataTable tbody');
const searchField = document.getElementById('searchField');
const searchInput = document.getElementById('searchInput');
const noRecords = document.getElementById('noRecords');

form.addEventListener('submit', function (e) {
    e.preventDefault();

    const name = document.getElementById('name').value.trim();
    const age = document.getElementById('age').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const branch = document.querySelector('input[name="branch"]:checked');
    const languageElems = document.querySelectorAll('input[name="languages"]:checked');
    const stateId = stateSelect.value;
    const city = citySelect.value;
    const stateName = stateSelect.options[stateSelect.selectedIndex]?.textContent;

    if (!name || !/^[A-Za-z]+( [A-Za-z]+)*$/.test(name)) {
        alert('Name must contain only letters and single spaces between words. No leading/trailing/multiple spaces or special characters allowed.');
        return;
    }
    if (!branch || languageElems.length === 0 || !stateId || !city) {
        alert('Please fill all fields and select at least one language, branch, state, and city.');
        return;
    }

    const languages = Array.from(languageElems).map(el => el.value).join(', ');

    const data = { name, age, email, phone, branch: branch.value, languages, state: stateName, city };

    if (editIndex !== null) {
        records[editIndex] = data;
        editIndex = null;
    } else {
        records.push(data);
    }

    form.reset();
    citySelect.innerHTML = '<option value="">Select</option>';
    renderTable();
});

function renderTable() {
    tableBody.innerHTML = '';
    let filtered = records;
    const filterField = searchField.value;
    const filterValue = searchInput.value.toLowerCase();

    if (filterField && filterValue) {
        filtered = records.filter(row => row[filterField].toLowerCase().startsWith(filterValue));
    }

    noRecords.style.display = filtered.length ? 'none' : 'block';

    filtered.forEach((row, index) => {
        const tr = document.createElement('tr');
        Object.values(row).forEach(value => {
            const td = document.createElement('td');
            td.textContent = value;
            tr.appendChild(td);
        });

        const actionsTd = document.createElement('td');
        actionsTd.innerHTML = `
      <span class="actions" onclick="editRow(${index})">✏️</span>
      <span class="actions" onclick="deleteRow(${index})">🗑️</span>
    `;
        tr.appendChild(actionsTd);
        tableBody.appendChild(tr);
    });
}

function editRow(index) {
    const data = records[index];
    document.getElementById('name').value = data.name;
    document.getElementById('age').value = data.age;
    document.getElementById('email').value = data.email;
    document.getElementById('phone').value = data.phone;
    document.querySelector(`input[name="branch"][value="${data.branch}"]`).checked = true;
    document.querySelectorAll('input[name="languages"]').forEach(cb => cb.checked = data.languages.includes(cb.value));

    const selectedState = [...stateSelect.options].find(opt => opt.text === data.state);
    if (selectedState) {
        stateSelect.value = selectedState.value;
        loadCities(parseInt(selectedState.value)).then(() => {
            citySelect.value = data.city;
        });
    }

    editIndex = index;
}

function deleteRow(index) {
    if (confirm('Are you sure you want to delete this record?')) {
        records.splice(index, 1);
        renderTable();
    }
}

searchInput.addEventListener('input', renderTable);
searchField.addEventListener('change', renderTable);

window.editRow = editRow;
window.deleteRow = deleteRow;

loadStates();
