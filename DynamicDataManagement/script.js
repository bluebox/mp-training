document.addEventListener("DOMContentLoaded", function () {
  const stateSelect = document.getElementById("state");
  const citySelect = document.getElementById("city");
  const form = document.getElementById("dataForm");
  const addBtn = document.getElementById("addBtn");
  const searchInput = document.getElementById("search");
  const entryCount = document.getElementById("entryCount");
  const noResults = document.getElementById("noResults");
  const tableBody = document.querySelector("#dataTable tbody");

  let editingRow = null;

  // Load states from API
  function loadStates() {
    fetch("https://countriesnow.space/api/v0.1/countries/states", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ country: "India" }),
    })
      .then((res) => res.json())
      .then((data) => {
        if (!data.error && data.data.states) {
          stateSelect.innerHTML = '<option value="">Select State</option>';
          data.data.states.forEach((state) => {
            const option = document.createElement("option");
            option.value = state.name;
            option.textContent = state.name;
            stateSelect.appendChild(option);
          });
        } else {
          alert("Could not load states from API.");
        }
      })
      .catch(() => alert("Error loading states from API."));
  }

  // Load cities from API
  function loadCities(stateName) {
    if (!stateName) {
      citySelect.innerHTML = '<option value="">Select City</option>';
      return;
    }

    citySelect.innerHTML = '<option value="">Loading cities...</option>';

    fetch("https://countriesnow.space/api/v0.1/countries/state/cities", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ country: "India", state: stateName }),
    })
      .then((res) => res.json())
      .then((data) => {
        citySelect.innerHTML = '<option value="">Select City</option>';
        if (!data.error && Array.isArray(data.data)) {
          if (data.data.length === 0) {
            citySelect.innerHTML += '<option value="">No cities found</option>';
          } else {
            data.data.forEach((city) => {
              const option = document.createElement("option");
              option.value = city;
              option.textContent = city;
              citySelect.appendChild(option);
            });
          }
        } else {
          alert("Could not load cities from API.");
        }
      })
      .catch(() => {
        alert("Error loading cities from API.");
        citySelect.innerHTML = '<option value="">Select City</option>';
      });
  }

  // Validate form
  function validateForm() {
    const name = document.getElementById("name").value.trim();
    const age = parseInt(document.getElementById("age").value, 10);
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value;
    const branch = document.querySelector('input[name="branch"]:checked');
    const languages = document.querySelectorAll(
      'input[name="languages"]:checked'
    );
    const state = stateSelect.value;
    const city = citySelect.value;

    const isValid =
      name &&
      !isNaN(age) &&
      age > 0 &&
      email.includes("@") &&
      /^\d{10}$/.test(phone) &&
      branch &&
      languages.length > 0 &&
      state &&
      city;

    addBtn.disabled = !isValid;
  }

  // Add or update row in table
  function addRowToTable(data) {
    let tr = editingRow || document.createElement("tr");
    tr.classList.add("data-row");
    tr.innerHTML = `
      <td>${data.name}</td>
      <td>${data.age}</td>
      <td>${data.email}</td>
      <td>${data.phone}</td>
      <td>${data.branch}</td>
      <td>${data.languages}</td>
      <td>${data.state}</td>
      <td>${data.city}</td>
      <td>
        <button class="updateBtn">Update</button>
        <button class="deleteBtn">Delete</button>
      </td>
    `;

    if (!editingRow) {
      tr.style.display = "none";
      tableBody.appendChild(tr);
      setTimeout(() => (tr.style.display = ""), 100);
    }

    editingRow = null;
  }

  function updateEntryCount() {
    const visibleRows = Array.from(tableBody.rows).filter(
      (row) => row.style.display !== "none"
    );
    entryCount.textContent = `Entries Found: ${visibleRows.length}`;
  }

  // Event listeners
  stateSelect.addEventListener("change", () => {
    loadCities(stateSelect.value);
    validateForm();
  });

  citySelect.addEventListener("change", validateForm);
  form.querySelectorAll("input, select").forEach((el) => {
    el.addEventListener("input", validateForm);
    el.addEventListener("change", validateForm);
  });

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const age = document.getElementById("age").value;
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value;
    const branch = document.querySelector('input[name="branch"]:checked').value;
    const languageValues = Array.from(
      document.querySelectorAll('input[name="languages"]:checked')
    )
      .map((cb) => cb.value)
      .join(", ");
    const stateText = stateSelect.options[stateSelect.selectedIndex].text;
    const cityText = citySelect.options[citySelect.selectedIndex].text;

    addRowToTable({
      name,
      age,
      email,
      phone,
      branch,
      languages: languageValues,
      state: stateText,
      city: cityText,
    });

    updateEntryCount();
    form.reset();
    citySelect.innerHTML = '<option value="">Select City</option>';
    addBtn.disabled = true;
  });

  // Handle update/delete actions
  tableBody.addEventListener("click", function (e) {
    const row = e.target.closest("tr");

    if (e.target.classList.contains("deleteBtn")) {
      if (confirm("Are you sure to delete this entry?")) {
        row.remove();
        updateEntryCount();
      }
    }

    if (e.target.classList.contains("updateBtn")) {
      editingRow = row;
      const cells = row.children;

      document.getElementById("name").value = cells[0].textContent;
      document.getElementById("age").value = cells[1].textContent;
      document.getElementById("email").value = cells[2].textContent;
      document.getElementById("phone").value = cells[3].textContent;

      const branchVal = cells[4].textContent;
      document.querySelector(
        `input[name="branch"][value="${branchVal}"]`
      ).checked = true;

      const languagesVal = cells[5].textContent.split(", ");
      document.querySelectorAll('input[name="languages"]').forEach((cb) => {
        cb.checked = languagesVal.includes(cb.value);
      });

      const stateVal = cells[6].textContent;
      const cityVal = cells[7].textContent;

      stateSelect.value = stateVal;
      loadCities(stateVal);

      setTimeout(() => {
        citySelect.value = cityVal;
      }, 300); // Small delay to allow cities to load

      addBtn.disabled = false;
    }
  });

  // Global search across all columns
  searchInput.addEventListener("keyup", function () {
    const query = this.value.toLowerCase();
    let matchCount = 0;
    Array.from(tableBody.rows).forEach((row) => {
      const text = row.textContent.toLowerCase();
      const isMatch = text.includes(query);
      row.style.display = isMatch ? "" : "none";
      if (isMatch) matchCount++;
    });
    noResults.style.display = matchCount === 0 ? "block" : "none";
    updateEntryCount();
  });

  // Per-column filtering
  const columnSearchInputs = document.querySelectorAll("#columnSearch input");

  columnSearchInputs.forEach((input) => {
    input.addEventListener("keyup", function () {
      const filters = Array.from(columnSearchInputs).map((i) =>
        i.value.toLowerCase()
      );

      Array.from(tableBody.rows).forEach((row) => {
        let showRow = true;
        filters.forEach((filter, colIndex) => {
          if (
            filter &&
            !row.cells[colIndex].textContent.toLowerCase().includes(filter)
          ) {
            showRow = false;
          }
        });
        row.style.display = showRow ? "" : "none";
      });

      // Hide or show "No results"
      const visibleRows = Array.from(tableBody.rows).filter(
        (row) => row.style.display !== "none"
      );
      noResults.style.display = visibleRows.length === 0 ? "block" : "none";

      updateEntryCount();
    });
  });

  // Init
  loadStates();
  addBtn.disabled = true;
});
