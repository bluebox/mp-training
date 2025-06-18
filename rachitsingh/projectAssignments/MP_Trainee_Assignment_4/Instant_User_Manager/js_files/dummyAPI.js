// api.js

const apiKey = "YOUR_API_KEY"; // Replace with your actual key
const baseURL = "https://api.countrystatecity.in/v1";

$(document).ready(function () {
  fetchStates();

  // When state changes, fetch cities
  $("#state").on("change", function () {
    const stateCode = $(this).val();
    if (stateCode) {
      fetchCities(stateCode);
    } else {
      $("#city").html('<option value="">-- Select City --</option>');
    }
  });
});

// Fetch all states in India
function fetchStates() {
  $.ajax({
    url: `${baseURL}/countries/IN/states`,
    method: "GET",
    headers: {
      "X-CSCAPI-KEY": apiKey,
    },
    success: function (states) {
      const stateDropdown = $("#state");
      stateDropdown
        .empty()
        .append('<option value="">-- Select State --</option>');
      states.forEach((state) => {
        stateDropdown.append(
          `<option value="${state.iso2}">${state.name}</option>`
        );
      });
    },
    error: function () {
      alert("Failed to fetch states. Please check your API key.");
    },
  });
}

// Fetch cities of a specific state
function fetchCities(stateCode) {
  $.ajax({
    url: `${baseURL}/countries/IN/states/${stateCode}/cities`,
    method: "GET",
    headers: {
      "X-CSCAPI-KEY": apiKey,
    },
    success: function (cities) {
      const cityDropdown = $("#city");
      cityDropdown
        .empty()
        .append('<option value="">-- Select City --</option>');
      cities.forEach((city) => {
        cityDropdown.append(
          `<option value="${city.name}">${city.name}</option>`
        );
      });
    },
    error: function () {
      alert("Failed to fetch cities. Please check your API key.");
    },
  });
}
