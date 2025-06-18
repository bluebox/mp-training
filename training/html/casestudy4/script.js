let records = [];
let editIndex = null;

const config = {
    apiUrl: 'https://api.countrystatecity.in/v1/countries',
    apiKey: 'NHhvOEcyWk50N2Vna3VFTE00bFp3MjFKR0ZEOUhkZlg4RTk1MlJlaA=='
};

const headers = {
    'X-CSCAPI-KEY': config.apiKey
};

const stateSelect = document.getElementById('state');
const citySelect = document.getElementById('city');
const totalCount = document.getElementById('recordCount');
const names = document.getElementById('name');
const form = document.getElementById('userForm');
const tableBody = document.querySelector('#dataTable tbody');
const searchField = document.getElementById('searchField');
const searchInput = document.getElementById('searchInput');
const noRecords = document.getElementById('noRecords');

let statesData = [];

async function loadStates() {
    try {
        const response = await fetch(`${config.apiUrl}/IN/states`, { headers });
        statesData = await response.json();

        statesData.forEach(state => {
            const option = document.createElement('option');
            option.value = state.iso2;
            option.textContent = state.name;
            stateSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading states:', error);
    }
}

async function loadCities(stateCode) {
    citySelect.innerHTML = '<option value="">Select</option>';
    try {
        const response = await fetch(`${config.apiUrl}/IN/states/${stateCode}/cities`, { headers });
        const cities = await response.json();

        cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city.name;
            option.textContent = city.name;
            citySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading cities:', error);
    }
}

// Input restrictions
["age", "phone"].forEach(id => {
    document.getElementById(id).addEventListener('keydown', e => {
        const key = e.key;
        if (!key.match(/[0-9]/) && key.length === 1) {
            e.preventDefault();
        }
    });
});

names.addEventListener('keydown', e => {
    const key = e.key;
    if (!key.match(/[a-zA-Z ]/) && key.length === 1) {
        e.preventDefault();
    }
});

names.addEventListener('change', e => {
    const value = e.target.value.trim();
    const pattern = /^[A-Za-z]+( [A-Za-z]+)*$/;
    const nameError = document.getElementById('nameError');
    if (value && !pattern.test(value)) {
        nameError.textContent = names.title;
        nameError.style.display = 'inline';
    } else {
        nameError.textContent = '';
        nameError.style.display = 'none';
    }
});

stateSelect.addEventListener('change', e => {
    loadCities(e.target.value);
});

form.addEventListener('submit', function (e) {
    e.preventDefault();

    const name = names.value.trim();
    const age = document.getElementById('age').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const branch = document.querySelector('input[name="branch"]:checked');
    const languageElems = document.querySelectorAll('input[name="languages"]:checked');
    const stateCode = stateSelect.value;
    const city = citySelect.value;
    const stateName = stateSelect.options[stateSelect.selectedIndex]?.textContent;

    if (!name || !/^[A-Za-z]+( [A-Za-z]+)*$/.test(name)) {
        alert('Name must contain only letters and single spaces between words.');
        return;
    }

    if (!branch || languageElems.length === 0 || !stateCode || !city) {
        alert('Please fill all fields and select at least one language, branch, state, and city.');
        return;
    }

    if (!age || age <= 0) {
        alert("Invalid age");
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

        // Highlight latest added row if it's at the end and not from edit
        if (index === records.length - 1 && editIndex === null) {
            tr.classList.add('highlight-green');
        }

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

    totalCount.textContent = `Showing ${filtered.length} of ${records.length} record(s)`;
}

function editRow(index) {
    const data = records[index];
    names.value = data.name;
    document.getElementById('age').value = data.age;
    document.getElementById('email').value = data.email;
    document.getElementById('phone').value = data.phone;
    document.querySelector(`input[name="branch"][value="${data.branch}"]`).checked = true;
    document.querySelectorAll('input[name="languages"]').forEach(cb => cb.checked = data.languages.includes(cb.value));

    const selectedState = [...stateSelect.options].find(opt => opt.textContent === data.state);
    if (selectedState) {
        stateSelect.value = selectedState.value;
        loadCities(selectedState.value).then(() => {
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

window.addEventListener('load', () => {
    form.reset();
    citySelect.innerHTML = '<option value="">Select</option>';
});



// let records = [];
// let editIndex = null;

// const config = {
//     apiUrl: 'https://api.countrystatecity.in/v1/countries',
//     apiKey: 'NHhvOEcyWk50N2Vna3VFTE00bFp3MjFKR0ZEOUhkZlg4RTk1MlJlaA=='
// };

// const headers = {
//     'X-CSCAPI-KEY': config.apiKey
// };

// const stateSelect = document.getElementById('state');
// const citySelect = document.getElementById('city');
// const totalCount = document.getElementById('recordCount');

// let statesData = [];

// async function loadStates() {
//     try {
//         const response = await fetch(`${config.apiUrl}/IN/states`, { headers });
//         statesData = await response.json();

//         statesData.forEach(state => {
//             const option = document.createElement('option');
//             option.value = state.iso2;
//             option.textContent = state.name;
//             stateSelect.appendChild(option);
//         });
//     } catch (error) {
//         console.error('Error loading states:', error);
//     }
// }

// async function loadCities(stateCode) {
//     citySelect.innerHTML = '<option value="">Select</option>';
//     try {
//         const response = await fetch(`${config.apiUrl}/IN/states/${stateCode}/cities`, { headers });
//         const cities = await response.json();

//         cities.forEach(city => {
//             const option = document.createElement('option');
//             option.value = city.name;
//             option.textContent = city.name;
//             citySelect.appendChild(option);
//         });
//     } catch (error) {
//         console.error('Error loading cities:', error);
//     }
// }

// // Restrict age and phone to numbers only
// ["age", "phone"].forEach(id => {
//     document.getElementById(id).addEventListener('keydown', e => {
//         const key = e.key;
//         if (!key.match(/[0-9]/) && key.length === 1) {
//             e.preventDefault();
//         }
//     });
// });

// // Restrict name to letters and space
// const names = document.getElementById('name');
// names.addEventListener('keydown', e => {
//     const key = e.key;
//     if (!key.match(/[a-zA-Z ]/) && key.length === 1) {
//         e.preventDefault();
//     }
// });

// // Name pattern check
// names.addEventListener('change', e => {
//     const value = e.target.value.trim();
//     const pattern = /^[A-Za-z]+( [A-Za-z]+)*$/;
//     const nameError = document.getElementById('nameError');
//     if (value && !pattern.test(value)) {
//         nameError.textContent = names.title;
//         nameError.style.display = 'inline';
//     } else {
//         nameError.textContent = '';
//         nameError.style.display = 'none';
//     }
// });

// stateSelect.addEventListener('change', e => {
//     loadCities(e.target.value);
// });

// const form = document.getElementById('userForm');
// const tableBody = document.querySelector('#dataTable tbody');
// const searchField = document.getElementById('searchField');
// const searchInput = document.getElementById('searchInput');
// const noRecords = document.getElementById('noRecords');

// form.addEventListener('submit', function (e) {
//     e.preventDefault();

//     const name = names.value.trim();
//     const age = document.getElementById('age').value.trim();
//     const email = document.getElementById('email').value.trim();
//     const phone = document.getElementById('phone').value.trim();
//     const branch = document.querySelector('input[name="branch"]:checked');
//     const languageElems = document.querySelectorAll('input[name="languages"]:checked');
//     const stateCode = stateSelect.value;
//     const city = citySelect.value;
//     const stateName = stateSelect.options[stateSelect.selectedIndex]?.textContent;

//     if (!name || !/^[A-Za-z]+( [A-Za-z]+)*$/.test(name)) {
//         alert('Name must contain only letters and single spaces between words.');
//         return;
//     }

//     if (!branch || languageElems.length === 0 || !stateCode || !city) {
//         alert('Please fill all fields and select at least one language, branch, state, and city.');
//         return;
//     }
//     if(!age || age<=0){
//         alert("invalid age")
//         return ;
//     }

//     const languages = Array.from(languageElems).map(el => el.value).join(', ');
//     const data = { name, age, email, phone, branch: branch.value, languages, state: stateName, city };

//     if (editIndex !== null) {
//         records[editIndex] = data;
//         editIndex = null;
//     } else {
//         records.push(data);
//     }

//     form.reset();
//     citySelect.innerHTML = '<option value="">Select</option>';
//     renderTable();
// });

// function renderTable() {
//     tableBody.innerHTML = '';
//     let filtered = records;
//     const filterField = searchField.value;
//     const filterValue = searchInput.value.toLowerCase();

//     if (filterField && filterValue) {
//         filtered = records.filter(row => row[filterField].toLowerCase().startsWith(filterValue));
//     }

//     noRecords.style.display = filtered.length ? 'none' : 'block';

//     filtered.forEach((row, index) => {
//         const tr = document.createElement('tr');
//         tr.classList.add('fade-in');
//         Object.values(row).forEach(value => {
//             const td = document.createElement('td');
//             td.textContent = value;
//             tr.appendChild(td);
//         });

//         const actionsTd = document.createElement('td');
//         actionsTd.innerHTML = `
//             <span class="actions" onclick="editRow(${index})">✏️</span>
//             <span class="actions" onclick="deleteRow(${index})">🗑️</span>
//         `;
//         tr.appendChild(actionsTd);
//         tableBody.appendChild(tr);
//          requestAnimationFrame(() => {
//         tr.classList.add('show'); // Will transition to visible
// });
//     });

//     // Show record count
//     totalCount.textContent = `Showing ${filtered.length} of ${records.length} record(s)`;
// }

// function editRow(index) {
//     const data = records[index];
//     names.value = data.name;
//     document.getElementById('age').value = data.age;
//     document.getElementById('email').value = data.email;
//     document.getElementById('phone').value = data.phone;
//     document.querySelector(`input[name="branch"][value="${data.branch}"]`).checked = true;
//     document.querySelectorAll('input[name="languages"]').forEach(cb => cb.checked = data.languages.includes(cb.value));

//     const selectedState = [...stateSelect.options].find(opt => opt.textContent === data.state);
//     if (selectedState) {
//         stateSelect.value = selectedState.value;
//         loadCities(selectedState.value).then(() => {
//             citySelect.value = data.city;
//         });
//     }

//     editIndex = index;
// }

// function deleteRow(index) {
//     if (confirm('Are you sure you want to delete this record?')) {
//         records.splice(index, 1);
//         renderTable();
//     }
// }

// searchInput.addEventListener('input', renderTable);
// searchField.addEventListener('change', renderTable);

// window.editRow = editRow;
// window.deleteRow = deleteRow;

// loadStates();

// window.addEventListener('load', () => {
//     form.reset();
//     citySelect.innerHTML = '<option value="">Select</option>';
//     submitBtn.disabled = true;
// });









