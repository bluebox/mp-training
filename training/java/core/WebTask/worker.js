function handleSubmit(event) {
  event.preventDefault();
  if (!validateForm()) {
    return;
  }
  const name = document.getElementById('nameField').value;
  const age = document.getElementById('ageField').value;
  const email = document.getElementById('emailField').value;
  const phone = document.getElementById('mobileField').value;

  const branch = document.querySelector('input[name="branch"]:checked')?.value || '';

  const languages = Array.from(document.querySelectorAll('input[name="language"]:checked'))
    .map(checkbox => checkbox.value)
    .join(', ');

  // const state = document.getElementById('stateName').value;
  // const city = document.getElementById('cityName').value;
  const state = document.getElementById('stateSelect').value;
  const city = document.getElementById('citySelect').value;

  const table = document.querySelector('.ourTable');
  const rows = table.rows;
  for (let i = 1; i < rows.length; i++) {
    if (rows[i].cells.length < 2) {
      continue;
    }
    const rowEmail = rows[i].cells[2].textContent.trim();
    const rowPhone = rows[i].cells[3].textContent.trim();
    if (rowEmail.toLowerCase() === email.toLowerCase()) {
      alert("Duplicate email found. Please enter a unique email.");
      return;
    }
    if (rowPhone === phone) {
      alert("Duplicate mobile number found. Please enter a unique mobile number.");
      return;
    }
  }

  const newRow = table.insertRow(-1);

  newRow.innerHTML = `
    <td>${name}</td>
    <td>${age}</td>
    <td>${email}</td>
    <td>${phone}</td>
    <td>${branch}</td>
    <td>${languages}</td>
    <td>${state}</td>
    <td>${city}</td>
    <td>
    <button class="edit" onclick="editRow(this)">Edit</button>
    <button class="delete" onclick="deleteRow(this)">delete</button></td>`;

  document.querySelector('.form').reset();
  document.getElementById('citySelect').disabled = true;

}

function deleteRow(button) {
  const row = button.closest('tr');
  // Boolean res=confirm("Confirm to delete");
  var result = confirm("Delete this record?Press Yes to confirm");
  if (result) {
    row.remove();
    alert("Deleted");
    console.log("Record deletion confirmed. Proceeding with deletion...");
  } else {
    alert("Deletion cancelld")
    console.log("Record deletion cancelled.");
  }


}

// Searching for specific data of HTML table

window.addEventListener('DOMContentLoaded', () => {
  const searchInput = document.getElementById('searchBx');
  const columnSelect = document.getElementById('srcCol');
  const table = document.getElementById('tble');

  searchInput.addEventListener('input', () => {
    const searchText = searchInput.value.toLowerCase();
    const columnIndex = parseInt(columnSelect.value);
    const tableRows = table.querySelectorAll('tbody tr');

    tableRows.forEach((row, i) => {
      const cells = row.querySelectorAll('td');
      const cell = cells[columnIndex];
      if (!cell) return;

      const cellText = cell.textContent.toLowerCase();
      const match = cellText.includes(searchText);

      row.classList.toggle('hide', !match);

      row.style.setProperty('--delay', i / 25 + 's');
    });

    const visibleRows = Array.from(tableRows).filter(row => !row.classList.contains('hide'));
    visibleRows.forEach((row, i) => {
      row.style.backgroundColor = (i % 2 === 0) ? 'transparent' : '#0000000b';
    });
  });
});



// ZjlSb3RYVEU3dXQyM0tlNHEyRG9PdTdQQVJVaXpMUjlHOHVjS291cg==
//editing

function editRow(button) {
  const row = button.closest('tr');
  const cells = row.querySelectorAll('td');
  document.getElementById('nameField').value = cells[0].textContent;
  document.getElementById('ageField').value = cells[1].textContent;
  document.getElementById('emailField').value = cells[2].textContent;
  document.getElementById('mobileField').value = cells[3].textContent;
  const branchValue = cells[4].textContent;
  document.querySelectorAll('input[name="branch"]').forEach(rb => {
    rb.checked = rb.value === branchValue;
  });
  const languages = cells[5].textContent.split(',').map(s => s.trim());
  document.querySelectorAll('input[name="language"]').forEach(cb => {
    cb.checked = languages.includes(cb.value);
  });
  document.getElementById('stateSelect').value = cells[6].textContent;
  document.getElementById('citySelect').value = cells[7].textContent;
  var result = confirm("Want to Edit?Press Yes to confirm");
  if (result) {
    row.remove();
    alert("You can edit now");
    console.log("Record deletion confirmed. Proceeding with deletion...");
  } else {
    // alert("editing cancelld")
    console.log("Record deletion cancelled.");
  }


  // row.remove();
}

//input validaton
function validateForm() {
  const name = document.getElementById('nameField').value.trim();
  const age = document.getElementById('ageField').value.trim();
  const email = document.getElementById('emailField').value.trim();
  const phone = document.getElementById('mobileField').value.trim();
  const branch = document.querySelector('input[name="branch"]:checked');
  const languages = document.querySelectorAll('input[name="language"]:checked');
  const state = document.getElementById('stateSelect').value.trim();
  const city = document.getElementById('citySelect').value.trim();
  const namePattern = /^[a-zA-Z ]*$/;
  if (!name || String(name).length < 4 || !namePattern.test(name)) {
    alert("Please enter your name Should be only alphabets.");
    return false;
  }

  if (!age || isNaN(age) || Number(age) <= 0 || Number(age) > 100) {
    alert("Please enter a valid age.");
    return false;
  }

  const emailPattern = /^[a-zA-Z][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!email || !emailPattern.test(email)) {
    alert("Please enter a valid email address.");
    return false;
  }
  const phonePattern = /^[0-9]{10}$/;
  if (!phone || !phonePattern.test(phone)) {
    alert("Please enter a valid phone number (10 digits).");
    return false;
  }

  if (!branch) {
    alert("Please select a branch.");
    return false;
  }

  if (languages.length === 0) {
    alert("Please select at least one language.");
    return false;
  }

  if (!state) {
    alert("Please enter the state.");
    return false;
  }

  if (!city) {
    alert("Please enter the city.");
    return false;
  }

  return true;
}

const API_KEY = atob('ZjlSb3RYVEU3dXQyM0tlNHEyRG9PdTdQQVJVaXpMUjlHOHVjS291cg==');

const stateSelect = document.getElementById('stateSelect');
const citySelect = document.getElementById('citySelect');

// states 
async function loadStates() {
  try {
    const response = await fetch('https://countriesnow.space/api/v0.1/countries/states', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ country: "India" })
    });

    const data = await response.json();
    if (data.error) throw new Error(data.msg);

    data.data.states.forEach(state => {
      const option = document.createElement('option');
      option.value = state.name;
      option.textContent = state.name;
      stateSelect.appendChild(option);
    });
  } catch (error) {
    console.error('Error :', error);
  }
}

loadStates();

stateSelect.addEventListener('change', async () => {
  citySelect.innerHTML = '<option value="">Select City</option>';
  citySelect.disabled = true;

  const selectedState = stateSelect.value;
  if (!selectedState) return;

  try {
    const response = await fetch('https://countriesnow.space/api/v0.1/countries/state/cities', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ country: "India", state: selectedState })
    });

    const data = await response.json();
    if (data.error) throw new Error(data.msg);

    data.data.forEach(city => {
      const option = document.createElement('option');
      option.value = city;
      option.textContent = city;
      citySelect.appendChild(option);
    });

    citySelect.disabled = false;
  } catch (error) {
    console.error('Errorin cities', error);
  }
});

//live errors
const usernameInput = document.getElementById('nameField');
const usernameError = document.getElementById('username-error');

usernameInput.addEventListener('input', () => {
  console.log("hloooo")
  if (usernameInput.checkValidity()) {
    usernameError.textContent = '';
  } else {
    usernameError.textContent = 'Username must be more than 3 chars and only alphabets';
  }
});
//age
const ageInput = document.getElementById('ageField');
const ageError = document.getElementsByClassName('error-message')[1];
ageInput.addEventListener('input', () => {
  const age = parseInt(ageInput.value);
  if (!ageInput.value || age < 1 || age > 99) {
    ageError.textContent = 'Age must be between 1 and 99';
  } else {
    ageError.textContent = '';
  }
});

//Email
const emailInput = document.getElementById('emailField');
const emailError = document.getElementsByClassName('error-message')[2];
emailInput.addEventListener('input', () => {
  const emailPattern = /^[a-zA-Z][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailPattern.test(emailInput.value)) {
    emailError.textContent = 'Enter a valid email address';
  } else {
    emailError.textContent = '';
  }
});
//Phone
const phoneInput = document.getElementById('mobileField');
const phoneError = document.getElementsByClassName('error-message')[3];

phoneInput.addEventListener('input', () => {
  const phonePattern = /^[0-9]{10}$/;
  if (!phonePattern.test(phoneInput.value)) {
    phoneError.textContent = 'Phone number must be exactly 10 digits';
  } else {
    phoneError.textContent = '';
  }
});
