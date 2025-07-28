const userForm = document.getElementById('studentForm');
const tbody = document.querySelector('#dataTable tbody');
const filterInput = document.getElementById('search');
const stateDropdown = document.getElementById('state');
const cityDropdown = document.getElementById('city');
const phoneError = document.getElementById('phoneError');
const branchError = document.getElementById('branchError');
const languageError = document.getElementById('languageError');

let entries = [];

 function fetchStates() {
  
    const response =  fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
                        .then(response=>response.json())
                        .then(states=>{
                          console.log("hi wow"+states.response);
                          console.log(typeof(states));
                          //  states = JSON.stringify(states);
                          var  stcode=states.response.slice(1,states.response.length-1).split(",");
                          // states=states.slice(1,-1);

                          // console.log(states);
                          // var st=states.split(",");
                          // console.log(st)
                          stateDropdown.innerHTML = '<option value="">Select State</option>';
                          // st=st.slice(2,-1);
    stcode.forEach(state => {
      console.log(state);
      state=state.split(":");
      const option = document.createElement('option');
      if(state[0]=="response"){
        console.log(state[0]+"ijijij")
        state=state[1].split(",");
      }
      option.value = ""+state[1];
      option.textContent = ""+state[0];
      stateDropdown.appendChild(option);
    });})
        .catch(error=>console.log(error+"error loading states"));
  //   console.log(response);
  //   console.log("hi hello ");
  //   if (!response.ok) throw new Error('Failed to fetch states');
  //   const states = await response.json();
  //   stateDropdown.innerHTML = '<option value="">Select State</option>';
  //   states.forEach(state => {
  //     const option = document.createElement('option');
  //     option.value = state.stateCode;
  //     option.textContent = state.stateName;
  //     stateDropdown.appendChild(option);
  //   });
  // } catch (error) {
  //   console.error('Error fetching states:', error);
  //   stateDropdown.innerHTML = '<option value="">Error loading states</option>';
  // }
}

 function fetchCities(stateCode) {
  stateCode=stateCode.slice(1,-1);
  stateCode=stateCode.slice(0,stateCode.length)
    console.log("response"+stateCode);
    const response =  fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`)
                        .then(response=>response.json())
                        .then(cities=>{
                          console.log("hi hello how are u"+cities.response);
                        var city=cities.response.slice(1,cities.response.length-1).split(",");
                          cityDropdown.innerHTML = '<option value="">Select City</option>';
    city.forEach(city => {
      const option = document.createElement('option');
      city=city.split(":");
      option.value = ""+city[1];
      option.textContent = ""+city[0];
      cityDropdown.appendChild(option);
                        })}).catch(error=>console.log("error "+error))
                        
  //   if (!response.ok) throw new Error('Failed to fetch cities');
  //   const cities = await response.json();
  //   cityDropdown.innerHTML = '<option value="">Select City</option>';
  //   cities.forEach(city => {
  //     const option = document.createElement('option');
  //     option.value = city.cityCode;
  //     option.textContent = city.cityName;
  //     cityDropdown.appendChild(option);
  //   });
  // } catch (error) {
  //   console.error('Error fetching cities:', error);
  //   cityDropdown.innerHTML = '<option value="">Error loading cities</option>';
  // }
}

fetchStates();

stateDropdown.addEventListener('change', () => {
  const selectedState = stateDropdown.value;
  cityDropdown.innerHTML = '<option value="">Select City</option>';
  if (selectedState) {
    fetchCities(selectedState);
  }
  validateForm();
});

userForm.addEventListener('input', validateForm);

function validateForm() {
  const { name, age, email, phone } = getFieldValues();
  const branch = userForm.querySelector('input[name="branch"]:checked');
  const languages = userForm.querySelectorAll('.language:checked');
  const state = stateDropdown.value;
  const city = cityDropdown.value;

  const phoneValid = /^\d{10}$/.test(phone);
  phoneError.textContent = phoneValid || !phone ? '' : 'Enter a valid 10-digit phone number';

  branchError.textContent = branch ? '' : 'Select a branch';

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
