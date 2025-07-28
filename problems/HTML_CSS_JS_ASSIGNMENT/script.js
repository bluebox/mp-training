const form = document.getElementById('dataForm');
const addBtn = document.getElementById('addBtn');
const tableBody = document.querySelector('#dataTable tbody');
const searchInput = document.getElementById('searchInput');
const noResults = document.getElementById('noResults');
const entryCount = document.getElementById('entryCount');
const stateSelect = document.getElementById('state');
const citySelect = document.getElementById('city');

let dataList = [];

// Enable Add button manually based on field checks
form.addEventListener('input', () => {
  const name = document.getElementById('name').value.trim();
  const age = document.getElementById('age').value;
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const branch = form.querySelector('input[name="branch"]:checked');
  const languages = form.querySelectorAll('input[name="languages"]:checked');

  const isValid =
    name !== "" &&
    age !== "" &&
    email !== "" &&
    /^\d{10}$/.test(phone) &&
    branch &&
    languages.length > 0;

  addBtn.disabled = !isValid;
});

// Fetch and populate states on page load
function loadStates() {
  fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
    .then(response => response.json())
    .then(states => {
      // Clear and add default option
      stateSelect.innerHTML = '<option value="">Select State</option>';
      
      // Append each state as an option
      states.forEach(state => {
        const option = document.createElement('option');
        option.value = state.code;        // Use state code for value
        option.textContent = state.name;  // Show state name
        stateSelect.appendChild(option);
      });
    })
    .catch(error => {
      console.error('Error loading states:', error);
    });
}

// Fetch and populate cities based on selected state
function loadCities(stateCode) {
  fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`)
    .then(response => response.json())
    .then(cities => {
      citySelect.innerHTML = '<option value="">Select City</option>';
      
      cities.forEach(city => {
        const option = document.createElement('option');
        option.value = city.code;        // Use city code for value
        option.textContent = city.name;  // Show city name
        citySelect.appendChild(option);
      });
    })
    .catch(error => {
      console.error('Error loading cities:', error);
    });
}

// When state is changed, load corresponding cities
stateSelect.addEventListener('change', function () {
  const selectedStateCode = this.value;
  if (selectedStateCode) {
    loadCities(selectedStateCode);
  } else {
    citySelect.innerHTML = '<option value="">Select City</option>';
  }
});

// Load states on initial page load
document.addEventListener('DOMContentLoaded', loadStates);





// Handle form submission
form.addEventListener('submit', (e) => {
  e.preventDefault();
  
  const name = document.getElementById('name').value.trim();
  const age = +document.getElementById('age').value;
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const branch = form.querySelector('input[name="branch"]:checked')?.value;
  const languageNodes = form.querySelectorAll('input[name="languages"]:checked');
  const languages = Array.from(languageNodes).map(l => l.value);
  
  const state = stateSelect.selectedIndex > 0 ? stateSelect.options[stateSelect.selectedIndex].text : "Not Provided";
  const city = citySelect.selectedIndex > 0 ? citySelect.options[citySelect.selectedIndex].text : "Not Provided";

  const newRow = {
    name, age, email, phone, branch, languages: languages.join(", "), state, city
  };

  dataList.push(newRow);
  updateTable();
  form.reset();
  citySelect.innerHTML = '<option value="">Select City</option>';
  addBtn.disabled = true;
});

// Render table
function updateTable() {
  tableBody.innerHTML = "";
  const searchVal = searchInput.value.toLowerCase();
  let visibleRows = 0;
   
  if(searchVal.length==0){
    visibleRows = 0;
  }


  dataList.forEach((data, index) => {
    const row = document.createElement('tr');
    const values = Object.values(data).map(val => String(val).toLowerCase());

    if (values.some(val => val.includes(searchVal))) {
      row.classList.add('fade-in');
      row.innerHTML = `
        <td>${data.name}</td>
        <td>${data.age}</td>
        <td>${data.email}</td>
        <td>${data.phone}</td>
        <td>${data.branch}</td>
        <td>${data.languages}</td>
        <td>${data.state}</td>
        <td>${data.city}</td>
        <td><button onclick="deleteRow(${index})">Delete</button></td>
      `;
      tableBody.appendChild(row);
      visibleRows++;
    }
  });

  noResults.style.display = visibleRows === 0 && searchVal.length>0 ? 'block' : 'none';
  entryCount.textContent =visibleRows !== 0 ? `Total entries shown: ${visibleRows}`:"";
}

function deleteRow(index) {
  if (confirm("Are you sure you want to delete this entry?")) {
    dataList.splice(index, 1);
    updateTable();
  }
}
searchInput.addEventListener('input', updateTable);
