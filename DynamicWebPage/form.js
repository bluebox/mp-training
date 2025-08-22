$(document).ready(function () {
  const stateSelect = $('#stateSelect');
  const citySelect = $('#citySelect');
  const tableBody = $('#dataTable tbody');
  const searchInput = $('#searchInput');
  const entryCount = $('#entryCount');
  const noResults = $('#noResults');

  // Fetch States on page load
  fetchStates();

  function fetchStates() {
    fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
      .then(response => response.json())
      .then(data => {
        const states = data.response.slice(1, data.response.length - 1).split(",");
        stateSelect.html('<option value="">--Select State--</option>');
        states.forEach(state => {
          const [name, code] = state.split(":");
          const option = `<option value="${code.trim().replace(/"/g, '')}">${name.trim().replace(/"/g, '')}</option>`;
          stateSelect.append(option);
        });
      })
      .catch(error => {
        console.error("Error loading states:", error);
        stateSelect.html('<option value="">Failed to load states</option>');
      });
  }

  // Fetch Cities when state is selected
  stateSelect.on("change", function () {
    const stateCode = $(this).val();
    if (!stateCode) {
      citySelect.html('<option value="">--Select City--</option>');
      return;
    }

    fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`)
      .then(response => response.json())
      .then(data => {
        const cities = data.response.slice(1, data.response.length - 1).split(",");
        citySelect.html('<option value="">--Select City--</option>');
        cities.forEach(city => {
          const [name, code] = city.split(":");
          const option = `<option value="${code.trim().replace(/"/g, '')}">${name.trim().replace(/"/g, '')}</option>`;
          citySelect.append(option);
        });
      })
      .catch(error => {
        console.error("Error loading cities:", error);
        citySelect.html('<option value="">Failed to load cities</option>');
      });
  });

  // Handle form submission
  $('#dataForm').on("submit", function (e) {
    e.preventDefault();

    const name = $('#name').val();
    const age = $('#age').val();
    const email = $('#email').val();
    const phone = $('#phone').val();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('.lang:checked').map(function () {
      return $(this).val();
    }).get().join(', ');
    const state = $('#stateSelect option:selected').text();
    const city = $('#citySelect option:selected').text();

    if (!branch || !languages || !state || !city) {
      alert('Please fill all required fields.');
      return;
    }

    const row = `
      <tr>
        <td>${name}</td>
        <td>${age}</td>
        <td>${email}</td>
        <td>${phone}</td>
        <td>${branch}</td>
        <td>${languages}</td>
        <td>${state}</td>
        <td>${city}</td>
        <td><button class="deleteBtn">Delete</button></td>
      </tr>
    `;
    tableBody.append(row);
    updateEntryCount();
    this.reset();
  });

  // Delete row
  tableBody.on("click", ".deleteBtn", function () {
    $(this).closest("tr").remove();
    updateEntryCount();
  });

  // Live search
  searchInput.on("keyup", function () {
    const value = $(this).val().toLowerCase();
    let matchCount = 0;

    tableBody.find("tr").each(function () {
      const rowText = $(this).text().toLowerCase();
      if (rowText.includes(value)) {
        $(this).show();
        matchCount++;
      } else {
        $(this).hide();
      }
    });

    noResults.toggle(matchCount === 0);
    updateEntryCount();
  });

  // Update entry count
  function updateEntryCount() {
    const count = tableBody.find("tr:visible").length;
    entryCount.text(`Total Entries: ${count}`);
  }
});
