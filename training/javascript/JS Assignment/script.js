const statesAndCities = {
  "AP": { name: "Andhra Pradesh", cities: ["Vijayawada", "Visakhapatnam"] },
  "TG": { name: "Telangana", cities: ["Hyderabad", "Warangal"] },
  "MH": { name: "Maharashtra", cities: ["Mumbai", "Pune"] }
};

const form = document.getElementById("dataForm");
const tableBody = document.querySelector("#dataTable tbody");
const searchInput = document.getElementById("searchInput");
const stateSelect = document.getElementById("state");
const citySelect = document.getElementById("city");
const entryCount = document.getElementById("entryCount");

// Load state options
for (const code in statesAndCities) {
  const opt = document.createElement("option");
  opt.value = code;
  opt.textContent = statesAndCities[code].name;
  stateSelect.appendChild(opt);
}

// Update cities
stateSelect.addEventListener("change", () => {
  citySelect.innerHTML = '<option value="">Select City</option>';
  const selectedState = statesAndCities[stateSelect.value];
  if (selectedState) {
    selectedState.cities.forEach(city => {
      const opt = document.createElement("option");
      opt.value = city;
      opt.textContent = city;
      citySelect.appendChild(opt);
    });
  }
});

// Form validation helper
function showError(id, message) {
  document.getElementById(id).textContent = message;
}
function clearErrors() {
  document.querySelectorAll(".error").forEach(e => e.textContent = "");
}

// Validate form
function validateForm() {
  clearErrors();
  let valid = true;

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("phone").value.trim();
  const branch = document.querySelector("input[name='branch']:checked");
  const languageNodes = document.querySelectorAll("input[name='languages']:checked");
  const state = stateSelect.value;
  const city = citySelect.value;

  if (!name) { showError("nameError", "Name is required."); valid = false; }
  if (!age || age <= 0 || age>120) { showError("ageError", "Enter valid positive age."); valid = false; }
  if (!email || !email.includes("@")) { showError("emailError", "Invalid email."); valid = false; }
  if (!phone || phone.length !== 10 || isNaN(phone)) { showError("phoneError", "Enter 10-digit phone number."); valid = false; }
  if (!branch) { showError("branchError", "Select a branch."); valid = false; }
  if (languageNodes.length === 0) { showError("languageError", "Select at least one language."); valid = false; }
  if (!state) { showError("stateError", "Select a state."); valid = false; }
  if (!city) { showError("cityError", "Select a city."); valid = false; }

  return valid;
}

// Handle submit
form.addEventListener("submit", function (e) {
  e.preventDefault();
  if (!validateForm()) return;

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("phone").value.trim();
  const branch = document.querySelector("input[name='branch']:checked").value;
  const languages = [...document.querySelectorAll("input[name='languages']:checked")].map(l => l.value).join(", ");
  const state = stateSelect.options[stateSelect.selectedIndex].text;
  const city = citySelect.value;

  const row = document.createElement("tr");
  row.innerHTML = `
    <td>${name}</td>
    <td>${age}</td>
    <td>${email}</td>
    <td>${phone}</td>
    <td>${branch}</td>
    <td>${languages}</td>
    <td>${state}</td>
    <td>${city}</td>
    <td><button onclick="deleteRow(this)">Delete</button></td>
  `;
  tableBody.appendChild(row);
  updateCount();
  form.reset();
  citySelect.innerHTML = '<option value="">Select City</option>';
});

// Delete row
function deleteRow(btn) {
  if (confirm("Delete this entry?")) {
    btn.closest("tr").remove();
    updateCount();
  }
}

// Search rows
searchInput.addEventListener("input", () => {
  const term = searchInput.value.toLowerCase();
  let matchCount = 0;

  tableBody.querySelectorAll("tr").forEach(row => {
    const visible = [...row.children].some(td => td.textContent.toLowerCase().includes(term));
    row.style.display = visible ? "" : "none";
    if (visible) matchCount++;
  });

  entryCount.textContent = matchCount === 0 ? "No results found." : `Total Entries: ${matchCount}`;
});

// Count
function updateCount() {
  entryCount.textContent = `Total Entries: ${tableBody.children.length}`;
}
