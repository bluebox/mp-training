const form = document.getElementById("dataForm");
const addBtn = document.getElementById("addBtn");
const stateSelect = document.getElementById("state");
const citySelect = document.getElementById("city");
const tableBody = document.querySelector("#dataTable tbody");
const entryCount = document.getElementById("entryCount");

document.querySelectorAll(".col-search").forEach(input => {
  input.addEventListener("input", handleColumnFilter);
});

function handleColumnFilter() {
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

fetch("https://countriesnow.space/api/v0.1/countries/states", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ country: "India" })
})
  .then(res => res.json())
  .then(data => {
    const states = data.data.states;
    states.forEach(state => {
      const option = document.createElement("option");
      option.value = state.name;
      option.textContent = state.name;
      stateSelect.appendChild(option);
    });
  });

stateSelect.addEventListener("change", function () {
  citySelect.innerHTML = '<option value="">Select City</option>';
  citySelect.disabled = true;
  if (stateSelect.value) {
    citySelect.disabled = false;
    fetchCities(stateSelect.value);
  }
});

function fetchCities(state) {
  fetch("https://countriesnow.space/api/v0.1/countries/state/cities", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ country: "India", state: state })
  })
    .then(res => res.json())
    .then(data => {
      const cities = data.data;
      cities.forEach(city => {
        const option = document.createElement("option");
        option.value = city;
        option.textContent = city;
        citySelect.appendChild(option);
      });
    });
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
    /^[A-Za-z\s]+$/.test(name) && 
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
      <td><button class="deleteBtn">Delete</button></td>
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
  }
});

function updateCount() {
  const count = [...tableBody.querySelectorAll("tr")].filter(row => row.length > 0).length;
  entryCount.textContent = count;
}