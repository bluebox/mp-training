// API configuration (for future use)
/*
const API_KEY = ''; // Replace with your API key from https://api.countrystatecity.in/
const API_BASE_URL = 'https://api.countrystatecity.in/v1';
*/

// Hardcoded data for states and cities
const statesAndCities = {
  AP: {
    name: "Andhra Pradesh",
    cities: ["Visakhapatnam", "Vijayawada", "Guntur", "Nellore", "Kurnool"],
  },
  TL: {
    name: "Telangana",
    cities: ["Hyderabad", "Warangal", "Nizamabad", "Karimnagar", "Khammam"],
  },
  KA: {
    name: "Karnataka",
    cities: ["Bangalore", "Mysore", "Hubli", "Mangalore", "Belgaum"],
  },
  TN: {
    name: "Tamil Nadu",
    cities: ["Chennai", "Coimbatore", "Madurai", "Salem", "Tiruchirappalli"],
  },
  MH: {
    name: "Maharashtra",
    cities: ["Mumbai", "Pune", "Nagpur", "Nashik", "Aurangabad"],
  },
};

$(document).ready(function () {
  // Load states on page load
  loadStates();

  // Form validation and submission
  const form = $("#dataForm");
  const submitBtn = $("#submitBtn");

  // Form validation on input change
  form.find("input, select").on("input change", function () {
    validateForm();
  });

  // Language checkbox validation
  $(".language-checkbox").on("change", function () {
    validateLanguages();
    validateForm();
  });

  // Handle state change
  $("#state").on("change", function () {
    const selectedState = $(this).val();
    if (selectedState) {
      loadCities(selectedState);
    } else {
      $("#city")
        .html('<option value="">Select City</option>')
        .prop("disabled", true);
    }
    validateForm();
  });

  // Form submission
  form.on("submit", function (e) {
    e.preventDefault();
    if (validateForm()) {
      addDataToTable();
      form[0].reset();
      $("#city")
        .html('<option value="">Select City</option>')
        .prop("disabled", true);
      submitBtn.prop("disabled", true);
    }
  });

  // Search functionality
  $("#searchInput").on("input", function () {
    const searchText = $(this).val().toLowerCase();
    filterTable(searchText);
  });
});

// Load states from hardcoded data
function loadStates() {
  const stateSelect = $("#state");
  stateSelect.html('<option value="">Select State</option>');

  Object.entries(statesAndCities).forEach(([code, data]) => {
    stateSelect.append(`<option value="${code}">${data.name}</option>`);
  });
}

// Load cities based on selected state from hardcoded data
function loadCities(stateCode) {
  const citySelect = $("#city");
  citySelect.html('<option value="">Select City</option>');

  if (statesAndCities[stateCode]) {
    statesAndCities[stateCode].cities.forEach((city) => {
      citySelect.append(`<option value="${city}">${city}</option>`);
    });
    citySelect.prop("disabled", false);
  } else {
    citySelect.prop("disabled", true);
  }
}

/* API-based functions for future use
function loadStatesFromAPI() {
    fetch(`${API_BASE_URL}/countries/IN/states`, {
        headers: {
            'X-CSCAPI-KEY': API_KEY
        }
    })
    .then(response => response.json())
    .then(states => {
        const stateSelect = $('#state');
        states.forEach(state => {
            stateSelect.append(`<option value="${state.iso2}">${state.name}</option>`);
        });
    })
    .catch(error => console.error('Error loading states:', error));
}

function loadCitiesFromAPI(stateCode) {
    const citySelect = $('#city');
    citySelect.html('<option value="">Loading cities...</option>').prop('disabled', true);
    
    fetch(`${API_BASE_URL}/countries/IN/states/${stateCode}/cities`, {
        headers: {
            'X-CSCAPI-KEY': API_KEY
        }
    })
    .then(response => response.json())
    .then(cities => {
        citySelect.html('<option value="">Select City</option>');
        cities.forEach(city => {
            citySelect.append(`<option value="${city.name}">${city.name}</option>`);
        });
        citySelect.prop('disabled', false);
    })
    .catch(error => {
        console.error('Error loading cities:', error);
        citySelect.html('<option value="">Error loading cities</option>');
    });
}
*/

// Validate form inputs
function validateForm() {
  let isValid = true;
  const form = $("#dataForm");

  // Reset previous validation
  form.find(".is-invalid").removeClass("is-invalid");

  // Validate name
  const name = $("#name").val().trim();
  if (!name) {
    $("#name").addClass("is-invalid");
    isValid = false;
  }

  // Validate age
  const age = $("#age").val();
  if (!age || age < 1) {
    $("#age").addClass("is-invalid");
    isValid = false;
  }

  // Validate email
  const email = $("#email").val().trim();
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email || !emailRegex.test(email)) {
    $("#email").addClass("is-invalid");
    isValid = false;
  }

  // Validate phone
  const phone = $("#phone").val().trim();
  const phoneRegex = /^\d{10}$/;
  if (!phone || !phoneRegex.test(phone)) {
    $("#phone").addClass("is-invalid");
    isValid = false;
  }

  // Validate branch
  if (!$('input[name="branch"]:checked').val()) {
    $('input[name="branch"]').addClass("is-invalid");
    isValid = false;
  }

  // Validate languages
  if (!validateLanguages()) {
    isValid = false;
  }

  // Validate state and city
  if (!$("#state").val()) {
    $("#state").addClass("is-invalid");
    isValid = false;
  }

  if (!$("#city").val()) {
    $("#city").addClass("is-invalid");
    isValid = false;
  }

  $("#submitBtn").prop("disabled", !isValid);
  return isValid;
}

// Validate language selection
function validateLanguages() {
  const languagesSelected = $(".language-checkbox:checked").length > 0;
  if (!languagesSelected) {
    $(".language-checkbox").addClass("is-invalid");
  } else {
    $(".language-checkbox").removeClass("is-invalid");
  }
  return languagesSelected;
}

// Add data to table
function addDataToTable() {
  const data = {
    name: $("#name").val().trim(),
    age: $("#age").val(),
    email: $("#email").val().trim(),
    phone: $("#phone").val().trim(),
    branch: $('input[name="branch"]:checked').val(),
    languages: $(".language-checkbox:checked")
      .map(function () {
        return $(this).val();
      })
      .get()
      .join(", "),
    state: $("#state option:selected").text(),
    city: $("#city").val(),
  };

  const row = `
        <tr class="fade-in">
            <td>${data.name}</td>
            <td>${data.age}</td>
            <td>${data.email}</td>
            <td>${data.phone}</td>
            <td>${data.branch}</td>
            <td>${data.languages}</td>
            <td>${data.state}</td>
            <td>${data.city}</td>
            <td>
                <button class="btn btn-danger btn-sm btn-delete" onclick="deleteRow(this)">
                    Delete
                </button>
            </td>
        </tr>
    `;

  $("#dataTableBody").append(row);
  updateSearchCount();
}

// Delete row from table
function deleteRow(button) {
  if (confirm("Are you sure you want to delete this entry?")) {
    const row = $(button).closest("tr");
    row.addClass("fade-out");
    setTimeout(() => {
      row.remove();
      updateSearchCount();
    }, 300);
  }
}

// Filter table based on search input
function filterTable(searchText) {
  let hasVisibleRows = false;

  $("#dataTableBody tr").each(function () {
    const row = $(this);
    const rowText = row.text().toLowerCase();

    if (rowText.includes(searchText)) {
      row.removeClass("d-none");
      hasVisibleRows = true;
    } else {
      row.addClass("d-none");
    }
  });

  $("#noDataMessage").toggleClass("d-none", hasVisibleRows);
  updateSearchCount();
}

// Update search results count
function updateSearchCount() {
  const totalRows = $("#dataTableBody tr").length;
  const visibleRows = $("#dataTableBody tr:not(.d-none)").length;

  if (totalRows === 0) {
    $("#searchCount").text("No entries");
  } else if (totalRows === visibleRows) {
    $("#searchCount").text(`Showing all ${totalRows} entries`);
  } else {
    $("#searchCount").text(
      `Showing ${visibleRows} out of ${totalRows} entries`
    );
  }
}
