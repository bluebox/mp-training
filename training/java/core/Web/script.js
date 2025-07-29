$(document).ready(function () {
  const $form = $("#dataForm");
  const $addBtn = $("#addBtn");
  const $state = $("#state");
  const $city = $("#city");
  const $tableBody = $("#dataTable tbody");
  const $entryCount = $("#entryCount");

  $.ajax({
    url: "https://countriesnow.space/api/v0.1/countries/states",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify({ country: "India" }),
    success: function (response) {
      if (response && Array.isArray(response.data.states)) {
        response.data.states.forEach(state => {
          $state.append(`<option value="${state.name}">${state.name}</option>`);
        });
      }
    }
  });

  $state.on("change", function () {
    const stateName = $(this).val();
    $city.empty().append('<option value="">Select City</option>').prop("disabled", true);

    if (stateName) {
      $.ajax({
        url: "https://countriesnow.space/api/v0.1/countries/state/cities",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({ country: "India", state: stateName }),
        success: function (response) {
          if (response && Array.isArray(response.data) && response.data.length > 0) {
            response.data.forEach(city => {
              $city.append(`<option value="${city}">${city}</option>`);
            });
            $city.prop("disabled", false);
          } else {
            $city.append('<option disabled>No cities available</option>');
          }
        },
        error: function () {
          $city.append('<option disabled>Error loading cities</option>');
        }
      });
    }

    validateForm();
  });

  $form.on("input change", validateForm);

  function validateForm() {
    const name = $.trim($("#name").val());
    const age = Number($("#age").val());
    const email = $("#email").val();
    const phone = $("#phone").val();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[name="languages"]:checked').length;
    const state = $state.val();
    const city = $city.val();

    const isValid =
      /^[A-Za-z\s]+$/.test(name) &&
      age > 0 &&
      /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) &&
      /^\d{10}$/.test(phone) &&
      branch &&
      languages > 0 &&
      state &&
      city;

    $addBtn.prop("disabled", !isValid);
  }

  $form.on("submit", function (e) {
    e.preventDefault();

    const name = $("#name").val();
    const age = $("#age").val();
    const email = $("#email").val();
    const phone = $("#phone").val();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[name="languages"]:checked')
      .map(function () { return this.value; })
      .get()
      .join(", ");
    const stateText = $state.find("option:selected").text();
    const cityText = $city.find("option:selected").text();

    const $newRow = $(`
      <tr>
        <td>${name}</td>
        <td>${age}</td>
        <td>${email}</td>
        <td>${phone}</td>
        <td>${branch}</td>
        <td>${languages}</td>
        <td>${stateText}</td>
        <td>${cityText}</td>
        <td><button class="deleteBtn">Delete</button></td>
      </tr>
    `);

    $tableBody.append($newRow);
    $form[0].reset();
    $city.prop("disabled", true);
    $addBtn.prop("disabled", true);
    updateEntryCount();
  });

  $tableBody.on("click", ".deleteBtn", function () {
    if (confirm("Are you sure you want to delete this entry?")) {
      $(this).closest("tr").remove();
      updateEntryCount();
    }
  });

  function updateEntryCount() {
    const count = $tableBody.find("tr:visible").length;
    $entryCount.text(`${count} entries`);
  }

  $('#search').on('input', function () {
    const searchTerm = $(this).val().toLowerCase();

    let visibleCount = 0;

    $tableBody.find('tr').each(function () {
      const rowText = $(this).text().toLowerCase();
      const isMatch = rowText.indexOf(searchTerm) > -1;
      $(this).toggle(isMatch);
      if (isMatch) visibleCount++;
    });

    $entryCount.text(`${visibleCount} entries`);
  });
});
