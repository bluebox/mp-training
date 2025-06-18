// Configutation : to setup apiURL and apiKey
const config = {
    apiUrl: 'https://api.countrystatecity.in/v1/countries',
    apiKey: 'NHhvOEcyWk50N2Vna3VFTE00bFp3MjFKR0ZEOUhkZlg4RTk1MlJlaA=='
  };
  
  // Select element : for country,state,city (based on selection from the drop down display lists)
  const countrySelect = document.querySelector('.country');
  const stateSelect = document.querySelector('.state');
  const citySelect = document.querySelector('.city');
  
  // Initialize 
  window.onload = () => {
    initializeDropdowns();
    bindFormEvents();
    bindTableEvents();
  };
  
  //  API : need to pass url here then fetches the data by hitting browser using GET and converts the result to JSON format
  function fetchFromApi(endpoint) {
    return fetch(endpoint, {
      headers: { "X-CSCAPI-KEY": config.apiKey }
    }).then(res => res.json());
  }
  
  // DropDown Loaders : 
  function initializeDropdowns() {
    loadCountries();
    countrySelect.addEventListener('change', loadStates);
    stateSelect.addEventListener('change', loadCities);
  }

  //once you open the browser the data should be displayed in countries(disable state and city initially)
  function loadCountries() {
    fetchFromApi(config.apiUrl).then(data => {
      populateSelect(countrySelect, data, 'name', 'iso2');
      countrySelect.value = 'IN'; // default value india
      loadStates(); // this will help us to load the states for india
    });
  
    disableSelect(stateSelect);
    disableSelect(citySelect);
  }
  
  // here we load the states based on country and populate the fetched data to the state dropdown
  function loadStates() {
    const countryCode = countrySelect.value;
    resetSelect(stateSelect, 'Select State');
    resetSelect(citySelect, 'Select City');
    enableSelect(stateSelect);
    disableSelect(citySelect);
  
    fetchFromApi(`${config.apiUrl}/${countryCode}/states`).then(data => {
      populateSelect(stateSelect, data, 'name', 'iso2');
    });
  }
  // here we load the cities based on state and populate the fetched data to the city dropdown
  function loadCities() {
    const countryCode = countrySelect.value;
    const stateCode = stateSelect.value;
    resetSelect(citySelect, 'Select City');
    enableSelect(citySelect);
  
    fetchFromApi(`${config.apiUrl}/${countryCode}/states/${stateCode}/cities`).then(data => {
      populateSelect(citySelect, data, 'name', 'name');
    });
  }
  
  // used to populate the data into the required dropdown 
  function populateSelect(selectEl, dataList, textKey, valueKey) {
    dataList.forEach(item => {
      const option = document.createElement('option');
      option.value = item[valueKey];
      option.textContent = item[textKey];
      selectEl.appendChild(option);
    });
  }
  
  function resetSelect(selectEl, defaultText) {
    selectEl.innerHTML = `<option value="">${defaultText}</option>`;
  }
  
  function disableSelect(selectEl) {
    selectEl.disabled = true;
    selectEl.style.pointerEvents = 'none';
  }
  
  function enableSelect(selectEl) {
    selectEl.disabled = false;
    selectEl.style.pointerEvents = 'auto';
  }
  
  // form validation 
  function isFormValid() {
    const name = $('#name').val().trim();
    const age = parseInt($('#age').val());
    const email = $('#email').val().trim();
    const phone = $('#phone').val().trim();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[name="language"]:checked');
    const state = $('.state').val();
    const city = $('.city').val();
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
  
    return name && age > 0 && emailPattern.test(email) && phone.length === 10 &&
           branch && languages.length > 0 && state && city;
  }
  // if valid then create row and add it to table else display the error message
  function bindFormEvents() {
    $('#dataForm input, .state, .city').on('change keyup', toggleAddButton);
  
    $('#dataForm').on('submit', function (e) {
      e.preventDefault();
      if (!isFormValid()) {
        $('#formError').text('Please fill all fields correctly.');
        return;
      }
      $('#formError').text('');
      addRowToTable();
      $('#dataForm')[0].reset();
      $('#addBtn').prop('disabled', true);
    });
  }
  
  function toggleAddButton() {
    $('#addBtn').prop('disabled', !isFormValid());
  }
  
  // for filtering and deleting 
  function bindTableEvents() {
    $('#searchInput').on('keyup', filterTable);
    $(document).on('click', '.deleteBtn', deleteRow);
  }

  // adding row to table
  function addRowToTable() {
    const name = $('#name').val().trim();
    const age = $('#age').val();
    const email = $('#email').val().trim();
    const phone = $('#phone').val().trim();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[name="language"]:checked').map(function () {
      return this.value;
    }).get().join(', ');
    const country = $('.country option:selected').text();
    const state = $('.state option:selected').text();
    const city = $('.city option:selected').text();
  
    const row = $(`
      <tr>
        <td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td>
        <td>${branch}</td><td>${languages}</td><td>${country}</td><td>${state}</td><td>${city}</td>
        <td><button class="deleteBtn">Delete</button></td>
      </tr>
    `);
  
    $('#dataTable tbody').append(row);
    updateEntryCount();
  }
  
  //deleting ( once delete button is clicked then this function get called using table events)
  function deleteRow() {
    if (confirm('Are you sure you want to delete this entry?')) { // cancel to close and ok to continue deleting 
      const row = $(this).closest('tr');
      row.addClass('fade-out');
      setTimeout(() => {
        row.remove();
        updateEntryCount();
      }, 400);
    }
  }
  
  function filterTable() {
    const query = $(this).val().toLowerCase(); //query on what use wants to search
    let visibleCount = 0;
  
    $('#dataTable tbody tr').each(function () {
      const match = $(this).text().toLowerCase().includes(query); // traverse each row and check on which row this query word matches
      $(this).toggle(match); // if match then display or else hide
      if (match) visibleCount++; // keeps count of matching count
    });
  
    $('#noResults').toggle(visibleCount === 0); // if visiblecnt is zero then no maching so it should display no results
    $('#entryCount').text(`Entries: ${visibleCount}`); // displays the count
  }
  
  function updateEntryCount() {
    const count = $('#dataTable tbody tr:visible').length; // counts the visible rows 
    $('#entryCount').text(`Entries: ${count}`); //update the count
    $('#noResults').toggle(count === 0); // if count is zero then display no results
  }
  