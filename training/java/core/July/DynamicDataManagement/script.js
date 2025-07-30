const data = []

const form = document.getElementById("dataForm");
const addBtn = document.getElementById("addBtn");
const stateSelect = document.getElementById("state");
const citySelect = document.getElementById("city");
const tableBody = document.getElementById("tbody");
const entryCount = document.getElementById("entryCount");

const response = fetch("http://192.168.0.73:32114/partner/get-states?countryCode=IN").then(res => res.json())
  .then(data =>{ 
    const states =JSON.parse(data.response)
   
    Object.keys(states).forEach(state =>{
    const option = document.createElement("option");
    option.value = states[state];
    option.textContent = state+"("+states[state]+")";
    stateSelect.appendChild(option);
    })
});

stateSelect.addEventListener("change", function () {
  citySelect.innerHTML = '<option value="">Select City</option>';
  citySelect.disabled = true;
  if (stateSelect.value) {
    citySelect.disabled = false;
    fetchCities(stateSelect.value);
  }
});

async function fetchCities(stateName) {
  try {
    const response = await fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateName}`);
    var data = await response.json();
    data = JSON.parse(data.response);
   
    Object.keys(data).forEach(city => {
      const option = document.createElement("option");
      option.value = city;
      option.textContent = city;
      citySelect.appendChild(option);
    });

  } catch (err) {
    console.error("Failed to fetch states:", err);
  }
}

document.querySelectorAll(".col-search").forEach(input => {
  input.addEventListener("input", handleFilter);
});

function handleFilter() {
  const searchTerms = Array.from(document.querySelectorAll(".col-search"))
    .map(input => input.value.toLowerCase());

  let visibleCount = 0;

  tableBody.querySelectorAll("tr").forEach(row => {
    const cells = row.querySelectorAll("td");
    let match = true;

    for (let i = 0; i < searchTerms.length; i++) {
      if (searchTerms[i] && !cells[i].textContent.toLowerCase().includes(searchTerms[i])) {
        match = false;
        break;
      }
    }

    row.style.display = match ? "" : "none";
    if (match) visibleCount++;
  });

  entryCount.textContent = visibleCount;
}

form.addEventListener("input", validateForm);
form.addEventListener("change", validateForm);

function validateForm() {
  const name = form.name.value.trim();
  const age = Number(form.age.value);
  const email = form.email.value;
  const phone = form.phone.value;
  const branch = form.querySelector('input[name="branch"]:checked');
  const languages = form.querySelectorAll('input[name="languages"]:checked');
  const state = stateSelect.value;
  const city = citySelect.value;
 
  const isValid =
    name &&
    age > 0 &&
    /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) &&
    /^\d{10}$/.test(phone) &&
    branch &&
    languages.length > 0 &&
    state &&
    city;
 
  addBtn.disabled = !isValid;
}

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const name = form.name.value.trim();
  const age = form.age.value;
  const email = form.email.value;
  const phone = form.phone.value;
  const branch = form.querySelector('input[name="branch"]:checked').value;
  const languages = Array.from(form.querySelectorAll('input[name="languages"]:checked'))
    .map(node => node.value)
    .join(", ");

  const stateText = stateSelect.options[stateSelect.selectedIndex].text;
  const cityText = citySelect.options[citySelect.selectedIndex].text;
 
  const tr = document.createElement("tr");
  tr.innerHTML = `
      <td>${name}</td>
      <td>${age}</td>
      <td>${email}</td>
      <td>${phone}</td>
      <td>${branch}</td>
      <td>${languages}</td>
      <td>${stateText}</td>
      <td>${cityText}</td>
      <td><button class="deleteBtn">Delete</button> <button class="updateBtn">Update</button></td>
    `;
    
  tr.classList.add("fade-in");
  tableBody.appendChild(tr);
  
  form.reset();
  citySelect.disabled = true;
  addBtn.disabled = true;
  updateCount();
});

tableBody.addEventListener("click", function (e) {
  if (e.target.classList.contains("deleteBtn")) {
    if (confirm("Are you sure you want to delete this entry?")) {
      const row = e.target.closest("tr");
      row.remove();
      updateCount();
    }
    updateEmptyMessage();
  }
});

tableBody.addEventListener("click", function (e) {
  if (e.target.classList.contains("updateBtn")) {
    const row = e.target.closest("tr");
    const cells = row.querySelectorAll("td");
    form.name.value = cells[0].textContent;
    form.age.value = cells[1].textContent;
    form.email.value = cells[2].textContent;
    form.phone.value = cells[3].textContent;
    form.querySelector(`input[name="branch"][value="${cells[4].textContent}"]`).checked = true;
    const languages = cells[5].textContent.split(", ");
    form.querySelectorAll('input[name="languages"]').forEach(input => {
      input.checked = languages.includes(input.value);
    });
    
    stateSelect.value = cells[6].textContent.split('(')[1].split(')')[0];
    citySelect.value = cells[7].textContent;
    citySelect.disabled = false;
    addBtn.disabled = false;

    row.remove();
    updateCount();
    updateEmptyMessage();
  }
});

function updateCount() {
  const count = [...tableBody.querySelectorAll("tr")].filter(row => row.length > 0).length;
  entryCount.textContent = count;
   const emptyMessage= document.querySelector(".noResults");
  emptyMessage.classList.toggle("hidden");
}

function updateEmptyMessage(){
  const count = [...tableBody.querySelectorAll("tr")].filter(row => row.length > 0).length;
 
}