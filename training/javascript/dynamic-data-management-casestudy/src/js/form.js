import {clearErrors,isValidEmail,isValidPhone,showError,showSuccessMessage} from './utils.js';
import { addEntry,tableData ,renderTable} from './table.js';


var states = [];
var cities = [];
var editingIndex = -1;

function setEditingIndex(index){
    editingIndex = index;
}

function handleFormSubmit(e) {
    e.preventDefault();
    
    if (!validateForm()) {
        return;
    }

    if (editingIndex !== -1) {
        // Update existing entry
        updateEntry();
    } else {
        // Add new entry
        const formData = {
            name: document.getElementById('name').value.trim(),
            age: document.getElementById('age').value,
            email: document.getElementById('email').value.trim(),
            phone: document.getElementById('phone').value.trim(),
            branch: document.querySelector('input[name="branch"]:checked').value,
            languages: Array.from(document.querySelectorAll('input[name="languages"]:checked'))
                           .map(cb => cb.value).join(', '),
            state: document.getElementById('state').selectedOptions[0].textContent,
            city: document.getElementById('city').value
        };

        addEntry(formData);
        
        console.log("formdata added to database")
        // Clear form
        document.getElementById('data-entry-form').reset();
        document.getElementById('city').innerHTML = '<option value="">Select City</option>';
        
        // Show success message
        showSuccessMessage('Enty added successfully!');
    }
}


function validateForm() {
    const name = document.getElementById('name').value.trim();
    const age = document.getElementById('age').value;
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const branch = document.querySelector('input[name="branch"]:checked');
    const languages = document.querySelectorAll('input[name="languages"]:checked');
    const state = document.getElementById('state').value;
    const city = document.getElementById('city').value;

    clearErrors();

    let isValid = true;

    if (!name) {
        showError('name-error', 'Name is required');
        isValid = false;
    }

    if (!age || age <= 0) {
        showError('age-error', 'Age must be a positive number');
        isValid = false;
    }

    if (!email || !isValidEmail(email)) {
        showError('email-error', 'Valid email is required');
        isValid = false;
    }

    if (!phone || !isValidPhone(phone)) {
        showError('phone-error', 'Valid 10-digit phone number is required');
        isValid = false;
    }

    if (!branch) {
        showError('branch-error', 'Please select a branch');
        isValid = false;
    }

    if (languages.length === 0) {
        showError('languages-error', 'Please select at least one language');
        isValid = false;
    }

    if (!state) {
        showError('state-error', 'Please select a state');
        isValid = false;
    }

    if (!city) {
        showError('city-error', 'Please select a city');
        isValid = false;
    }

    return isValid;
}



function updateEntry() {
    if (editingIndex === -1) return;
    
    const formData = {
        name: document.getElementById('name').value.trim(),
        age: document.getElementById('age').value,
        email: document.getElementById('email').value.trim(),
        phone: document.getElementById('phone').value.trim(),
        branch: document.querySelector('input[name="branch"]:checked').value,
        languages: Array.from(document.querySelectorAll('input[name="languages"]:checked'))
                       .map(cb => cb.value).join(', '),
        state: document.getElementById('state').selectedOptions[0].textContent,
        city: document.getElementById('city').value
    };
    
    tableData[editingIndex] = formData;
    renderTable();
    
    // Reset editing state
    editingIndex = -1;
    updateFormButtons(false);
    
    // Clear form
    document.getElementById('data-entry-form').reset();
    document.getElementById('city').innerHTML = '<option value="">Select City</option>';
    
    showSuccessMessage('Entry updated successfully!');
}


// API Service Functions
function fetchStates() {

    var headers = new Headers();
    headers.append("X-CSCAPI-KEY", "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==");
    var requestOptions = {
        method: 'GET',
        headers: headers,
        redirect: 'follow'
    };

    
    fetch("https://api.countrystatecity.in/v1/countries/IN/states", requestOptions)
        .then(response => response.text())
        .then(result => {
            states = JSON.parse(result);
            console.log(states);
            populateStateDropdown();
        })
        .catch(error => console.log('error', error));

    console.log("This also happened");
    
    
}


async function fetchCities(stateCode) {

    var headers = new Headers();
    headers.append("X-CSCAPI-KEY", "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==");
    var requestOptions = {
        method: 'GET',
        headers: headers,
        redirect: 'follow'
    };
    
    console.log("State selected:" + stateCode);
    fetch(`https://api.countrystatecity.in/v1/countries/IN/states/${stateCode}/cities`, requestOptions)
        .then(response => response.text())
        .then(result=> {
            cities = JSON.parse(result)
            populateCityDropdown();
        })
        .catch(error => console.log('error fetching cities', error));
        
    
}

function populateStateDropdown() {

    const stateSelect = document.getElementById('state');
    stateSelect.innerHTML = '<option value="">Select State</option>';
    console.log(typeof(states));
    states.forEach(state => {
        const option = document.createElement('option');
        option.value = state.iso2 || state.name;
        option.textContent = state.name;
        
        stateSelect.appendChild(option);
    });
}

function populateCityDropdown() {
    const citySelect = document.getElementById('city');
    citySelect.innerHTML = '<option value="">Select City</option>';
    
    console.log("trying to populate cities");
    cities.forEach(city => {
        const option = document.createElement('option');
        option.value = city.name;
        option.textContent = city.name;
        citySelect.appendChild(option);
    });
}



function updateFormButtons(isEditing) {
    const submitButton = document.querySelector('#data-entry-form button[type="submit"]');
    const formButtonsContainer = document.getElementById('form-buttons');
    
    if (isEditing) {
        submitButton.textContent = 'Update Entry';
        submitButton.style.backgroundColor = '#f39c12';
        
        // Add cancel button if it doesn't exist
        if (!document.getElementById('cancel-edit-btn')) {
            const cancelButton = document.createElement('button');
            cancelButton.type = 'button';
            cancelButton.id = 'cancel-edit-btn';
            cancelButton.textContent = 'Cancel Edit';
            cancelButton.style.cssText = `
                background-color: #e74c3c;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
                margin-left: 10px;
            `;
            cancelButton.onclick = cancelEdit;
            formButtonsContainer.appendChild(cancelButton);
        }
    } else {
        submitButton.textContent = 'Add Entry';
        submitButton.style.backgroundColor = '#4CAF50';
        
        // Remove cancel button
        const cancelButton = document.getElementById('cancel-edit-btn');
        if (cancelButton) {
            cancelButton.remove();
        }
    }
}

function cancelEdit() {
    editingIndex = -1;
    updateFormButtons(false);
    
    // Clear form
    document.getElementById('data-entry-form').reset();
    document.getElementById('city').innerHTML = '<option value="">Select City</option>';
    
    showSuccessMessage('Edit cancelled');
}


document.addEventListener('DOMContentLoaded', function() {
    
    console.log("Event listener added for form ")
    const form = document.getElementById('data-entry-form');
    form.addEventListener('submit', handleFormSubmit);
        
    const stateSelect = document.getElementById('state');

    stateSelect.addEventListener('change', function() {
        console.log("State change detected");
        const selectedState = this.value;
        if (selectedState) {
            fetchCities(selectedState);
        } else {
            document.getElementById('city').innerHTML = '<option value="">Select City</option>';
        }
    });
    
    addEntry({
        name: 'Tulasidhar',
        age: '21',
        email: 'tulasidharmulakaluri@gmail.com',
        phone: '6301147790',
        branch: 'CSE',
        languages: 'English',
        state: 'AndhraPradesh',
        city: 'Vijaywada'
    })

    renderTable();
    fetchStates();
});



export {setEditingIndex,fetchCities ,updateFormButtons, states, cities }