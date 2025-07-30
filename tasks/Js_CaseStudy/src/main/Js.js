document.addEventListener('DOMContentLoaded', () => {

  const stateToCities = {
    "Andhra Pradesh": ["Visakhapatnam", "Machilipatnam", "Vijayawada", "Guntur", "Tirupati"],
    "Maharashtra": ["Mumbai", "Pune", "Nagpur", "Nashik", "Thane", "Aurangabad"],
    "Karnataka": ["Bengaluru", "Mysore", "Mangalore", "Hubli", "Belgaum"],
    "Tamil Nadu": ["Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Salem", "Erode"],
    "Delhi": ["New Delhi", "Dwarka", "Rohini", "Karol Bagh"],
    "West Bengal": ["Kolkata", "Howrah", "Durgapur", "Siliguri", "Asansol"],
    "Uttar Pradesh": ["Lucknow", "Kanpur", "Varanasi", "Agra", "Noida", "Ghaziabad"],
    "Bihar": ["Patna", "Gaya", "Bhagalpur", "Muzaffarpur"],
    "Punjab": ["Amritsar", "Ludhiana", "Jalandhar", "Patiala"],
    "Haryana": ["Gurgaon", "Faridabad", "Panipat", "Karnal"],
    "Rajasthan": ["Jaipur", "Jodhpur", "Udaipur", "Bikaner", "Ajmer"],
    "Odisha": ["Bhubaneswar", "Cuttack", "Rourkela", "Berhampur"],
    "Gujarat": ["Ahmedabad", "Surat", "Vadodara", "Rajkot"],
    "Kerala": ["Thiruvananthapuram", "Kochi", "Kozhikode", "Thrissur"],
    "Madhya Pradesh": ["Indore", "Bhopal", "Gwalior", "Jabalpur"],
    "Chhattisgarh": ["Raipur", "Bhilai", "Durg", "Bilaspur"],
    "Jharkhand": ["Ranchi", "Jamshedpur"]
  };

  const stateSelect = document.getElementById('state');
  const citySelect = document.getElementById('city');
  const form = document.getElementById('registrationForm');
  const tableBody = document.querySelector('#dataTable tbody');

  const nameWarning = document.getElementById('name-warning');
  const ageWarning = document.getElementById('age-warning');
  const emailWarning = document.getElementById('email-warning');
  const phoneWarning = document.getElementById('phone-warning');

  stateSelect.addEventListener('change', () => {
    const selectedState = stateSelect.value;
    citySelect.innerHTML = '<option value="">--Select City--</option>';
    if (selectedState && stateToCities[selectedState]) {
      stateToCities[selectedState].forEach(city => {
        const option = document.createElement('option');
        option.value = city;
        option.textContent = city;
        citySelect.appendChild(option);
      });
    }
  });

  function validateName(name) {
    const trimmedName = name.trim();
    return trimmedName.length > 3 && /^[A-Za-z\s]+$/.test(trimmedName);
  }

  function validateAge(age) {
    const numAge = Number(age);
    return numAge >= 0;
  }
  function validateEmail(email) {
    const trimmedEmail = email.trim();
    const emailRegex = /^[^\s@]{3,}@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(trimmedEmail);
  }

  function validatePhone(phone) {
    return /^[0-9]{10}$/.test(phone);
  }

  function clearWarnings() {
    nameWarning.style.display = 'none';
    ageWarning.style.display = 'none';
    emailWarning.style.display='none';
    phoneWarning.style.display = 'none';
  }

  function getSelectedBranch() {
    const branchRadios = document.querySelectorAll('input[name="branch"]');
    for (const radio of branchRadios) {
      if (radio.checked) return radio.value;
    }
    return "";
  }

  function getSelectedLanguages() {
    const langCheckboxes = document.querySelectorAll('input[name="languages"]:checked');
    return Array.from(langCheckboxes).map(cb => cb.value);
  }

  function isDuplicateEntry(data) {
    const rows = tableBody.querySelectorAll('tr');
    return Array.from(rows).some(row => {
      const cells = row.querySelectorAll('td');
      return (
        cells[0].textContent === data.name &&
        cells[3].textContent === data.phone
      );
    });
  }

  function addTableRow(data) {
    const tr = document.createElement('tr');

    tr.innerHTML = `
      <td>${data.name}</td>
      <td>${data.age}</td>
      <td>${data.email}</td>
      <td>${data.phone}</td>
      <td>${data.branch}</td>
      <td>${data.languages.join(", ")}</td>
      <td>${data.state}</td>
      <td>${data.city}</td>
      <td>
        <button class="edit-btn">Edit</button>
        <button class="delete-btn">Delete</button>
      </td>
    `;

    tr.querySelector('.edit-btn').addEventListener('click', () => {
      populateFormForEdit(tr);
    });

    tr.querySelector('.delete-btn').addEventListener('click', () => {
      if (confirm("Are you sure you want to delete this entry?")) {
        tr.remove();
      }
    });

    tableBody.appendChild(tr);
    tableBody.scrollIntoView({ behavior: 'smooth' });
  }

  function populateFormForEdit(row) {
    const cells = row.querySelectorAll('td');
    document.getElementById('name').value = cells[0].textContent;
    document.getElementById('age').value = cells[1].textContent;
    document.getElementById('email').value = cells[2].textContent;
    document.getElementById('phone').value = cells[3].textContent;

    const branch = cells[4].textContent;
    document.querySelectorAll('input[name="branch"]').forEach(rb => {
      rb.checked = (rb.value === branch);
    });

    const langs = cells[5].textContent.split(",").map(s => s.trim());
    document.querySelectorAll('input[name="languages"]').forEach(cb => {
      cb.checked = langs.includes(cb.value);
    });

    document.getElementById('state').value = cells[6].textContent;
    const event = new Event('change');
    stateSelect.dispatchEvent(event);

    document.getElementById('city').value = cells[7].textContent;

    row.remove();
  }

  function clearForm() {
    form.reset();
    citySelect.innerHTML = '<option value="">--Select City--</option>';
  }

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    clearWarnings();

    const name = form.name.value.trim();
    const age = form.age.value.trim();
    const email = form.email.value.trim();
    const phone = form.phone.value.trim();
    const branch = getSelectedBranch();
    const languages = getSelectedLanguages();
    const state = form.state.value;
    const city = form.city.value;

    let valid = true;

    if (!validateName(name)) {
      nameWarning.textContent = "Please enter a valid name [ letters(no.of characters >3) and spaces only ].";
      nameWarning.style.display = 'block';
      valid = false;
    }


    if (!validateEmail(email)) {
          emailWarning.textContent = "please type a vallid mail ID";
          emailWarning.style.display = 'block';
          valid = false;
        }
    if (!validateAge(age)) {
      ageWarning.textContent = "Age must be positive integer";
      ageWarning.style.display = 'block';
      valid = false;
    }
    if (!validatePhone(phone)) {
      phoneWarning.textContent = "Phone number must be exactly 10 digits.";
      phoneWarning.style.display = 'block';
      valid = false;
    }
    if (!branch) {
      alert("Please select a branch.");
      valid = false;
    }
    if (languages.length === 0) {
      alert("Please select at least one language.");
      valid = false;
    }
    if (!state) {
      alert("Please select a state.");
      valid = false;
    }
    if (!city) {
      alert("Please select a city.");
      valid = false;
    }

    const formData = {
      name, age, email, phone, branch, languages, state, city,
      timestamp: new Date().toLocaleString()
    };

    if (!valid) return;

    if (isDuplicateEntry(formData)) {
      alert("Duplicate entry detected.");
      return;
    }

    addTableRow(formData);
    clearForm();
  });

  // FILTER TOGGLE
  const filterToggleBtn = document.getElementById('filterToggle');
  const filterRow = document.getElementById('filterRow');
  const filterContainer = document.getElementById('filterContainer');

  filterToggleBtn.addEventListener('click', () => {
    if (filterRow.style.display === 'none') {
      filterRow.style.display = '';
      filterContainer.style.display = 'block';
      filterToggleBtn.textContent = '❌ Close Filter';
    } else {
      filterRow.style.display = 'none';
      filterContainer.style.display = 'none';
      filterToggleBtn.textContent = '🔍 Filter';
      clearFilters();
      filterTable();
    }
  });

  const filterInputs = filterRow.querySelectorAll('input.filter-input');
  filterInputs.forEach(input => {
    input.addEventListener('input', filterTable);
  });

  const filterInputMain = document.getElementById('filterInput');
  filterInputMain.addEventListener('input', filterTable);

  function clearFilters() {
    filterInputs.forEach(input => input.value = '');
    filterInputMain.value = '';
  }

  function filterTable() {
    const filterValues = Array.from(filterInputs).map(input => input.value.toLowerCase());
    const mainFilter = filterInputMain.value.toLowerCase();
    const rows = tableBody.querySelectorAll('tr');

    rows.forEach(row => {
      const cells = row.querySelectorAll('td');
      let match = true;
      for (let i = 0; i < filterValues.length; i++) {
        if (filterValues[i] && !cells[i].textContent.toLowerCase().includes(filterValues[i])) {
          match = false;
          break;
        }
      }

      if (match && mainFilter) {
        const rowText = row.textContent.toLowerCase();
        if (!rowText.includes(mainFilter)) {
          match = false;
        }
      }

      row.style.display = match ? '' : 'none';
    });
  }

});









