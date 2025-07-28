const userForm = document.getElementById('studentForm');
const tbody = document.querySelector('#dataTable tbody');
const filterInput = document.getElementById('search');
const stateDropdown = document.getElementById('state');
const cityDropdown = document.getElementById('city');
const phoneError = document.getElementById('phoneError');
const branchError = document.getElementById('branchError');
const languageError = document.getElementById('languageError');

let entries = [];

// Fetch states from API
async function fetchStates() {
  try {
    const response = await fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN');
    if (!response.ok) throw new Error('Failed to fetch states');
    const states = await response.json();
    stateDropdown.innerHTML = '<option value="">Select State</option>';
    states.forEach(state => {
      const option = document.createElement('option');
      option.value = state.stateCode;
      option.textContent = state.stateName;
      stateDropdown.appendChild(option);
    });
  } catch (error) {
    console.error('Error fetching states:', error);
    stateDropdown.innerHTML = '<option value="">Error loading states</option>';
  }
}

// Fetch cities based on state code
async function fetchCities(stateCode) {
  try {
    const response = await fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`);
    if (!response.ok) throw new Error('Failed to fetch cities');
    const cities = await response.json();
    cityDropdown.innerHTML = '<option value="">Select City</option>';
    cities.forEach(city => {
      const option = document.createElement('option');
      option.value = city.cityCode;
      option.textContent = city.cityName;
      cityDropdown.appendChild(option);
    });
  } catch (error) {
    console.error('Error fetching cities:', error);
    cityDropdown.innerHTML = '<option value="">Error loading cities</option>';
  }
}

// Initialize states on page load
fetchStates();

// Populate city dropdown based on state selection
stateDropdown.addEventListener('change', () => {
  const selectedState = stateDropdown.value;
  cityDropdown.innerHTML = '<option value="">Select City</option>';
  if (selectedState) {
    fetchCities(selectedState);
  }
  validateForm();
});

// Validate on input
userForm.addEventListener('input', validateForm);

function validateForm() {
  const { name, age, email, phone } = getFieldValues();
  const branch = userForm.querySelector('input[name="branch"]:checked');
  const languages = userForm.querySelectorAll('.language:checked');
  const state = stateDropdown.value;
  const city = cityDropdown.value;

  // Phone validation
  const phoneValid = /^\d{10}$/.test(phone);
  phoneError.textContent = phoneValid || !phone ? '' : 'Enter a valid 10-digit phone number';

  // Branch validation
  branchError.textContent = branch ? '' : 'Select a branch';

  // Language validation
  languageError.textContent = languages.length > 0 ? '' : 'Select at least one language';

  const isValid = name && age && email && phoneValid && branch && languages.length > 0 && state && city;
  userForm.querySelector('button[type="submit"]').disabled = !isValid;
  return isValid;
}

userForm.addEventListener('submit', (e) => {
  e.preventDefault();
  if (!validateForm()) return;

  const { name, age, email, phone } = getFieldValues();
  const branch = userForm.querySelector('input[name="branch"]:checked').value;
  const languages = [...userForm.querySelectorAll('.language:checked')].map(el => el.value);
  const state = stateDropdown.options[stateDropdown.selectedIndex].text;
  const city = cityDropdown.options[cityDropdown.selectedIndex].text;

  const entry = {
    name,
    age: +age,
    email,
    phone,
    branch,
    languages: languages.join(', '),
    state,
    city
  };

  entries.push(entry);
  renderTable();
  resetForm();
});

function getFieldValues() {
  return {
    name: document.getElementById('name').value.trim(),
    age: document.getElementById('age').value,
    email: document.getElementById('email').value.trim(),
    phone: document.getElementById('phone').value.trim()
  };
}

function resetForm() {
  userForm.reset();
  cityDropdown.innerHTML = '<option value="">Select City</option>';
  phoneError.textContent = '';
  branchError.textContent = '';
  languageError.textContent = '';
  userForm.querySelector('button[type="submit"]').disabled = true;
}

function renderTable() {
  tbody.innerHTML = '';
  const searchText = filterInput.value.toLowerCase();

  entries.forEach((entry, idx) => {
    const values = Object.values(entry).map(v => String(v).toLowerCase());
    if (values.some(val => val.includes(searchText))) {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${entry.name}</td>
        <td>${entry.age}</td>
        <td>${entry.email}</td>
        <td>${entry.phone}</td>
        <td>${entry.branch}</td>
        <td>${entry.languages}</td>
        <td>${entry.state}</td>
        <td>${entry.city}</td>
        <td><button onclick="removeEntry(${idx})">Delete</button></td>
      `;
      tbody.appendChild(row);
    }
  });
}

function removeEntry(index) {
  if (confirm('Do you want to delete this entry?')) {
    entries.splice(index, 1);
    renderTable();
  }
}

filterInput.addEventListener('input', renderTable);