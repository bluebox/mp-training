$(document).ready(function () {
  const stateUrl = "http://192.168.0.73:32114/partner/get-states?countryCode=IN";
  const cityUrl = "http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=";
  let editingRow = null;


  $.getJSON(stateUrl, function (data) {
    try {
      const states = JSON.parse(data.response);
      $.each(states, (state, code) => {
        $('#state').append(`<option value="${code}">${state}</option>`);
      });
    } catch (error) {
      console.error("Error parsing states:", error);
      loadFallbackStates();
    }
  }).fail(function () {
    loadFallbackStates();
  });


  $('#state').change(function () {
    const stateCode = $(this).val();
    $('#city').empty().append('<option value="">Select</option>');
    if (stateCode) {
      $.getJSON(`${cityUrl}${stateCode}`, function (data) {
        try {
          const cities = JSON.parse(data.response);
          $.each(cities, (city, code) => {
            $('#city').append(`<option value="${city}">${city}</option>`);
          });
        } catch (error) {
          loadFallbackCities(stateCode);
        }
      }).fail(function () {
        loadFallbackCities(stateCode);
      });
    }
  });


  $('#dataForm input, #dataForm select').on('input change', validateForm);

  function showError(inputId, message) {
    $(`#${inputId}Error`).text(message);
  }

  function clearError(inputId) {
    $(`#${inputId}Error`).text('');
  }

  function isEmailUnique(email) {
    let unique = true;
    $('#dataTable tbody tr').each(function () {
      if ($(this).children('td').eq(2).text() === email) {
        unique = false;
        return false;
      }
    });
    return unique;
  }

  function isPhoneUnique(phone) {
    let unique = true;
    $('#dataTable tbody tr').each(function () {
      if ($(this).children('td').eq(3).text() === phone) {
        unique = false;
        return false;
      }
    });
    return unique;
  }

  function validateForm() {
    let isValid = true;

    const name = $('#name').val().trim();
    const age = parseInt($('#age').val());
    const email = $('#email').val().trim();
    const phone = $('#phone').val().trim();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[type="checkbox"]:checked').length;
    const state = $('#state').val();
    const city = $('#city').val();

    
    if (!/^[a-zA-Z ]{3,}$/.test(name)) {
      showError('name', 'Enter a valid name (min 3 letters)');
      isValid = false;
    } else {
      clearError('name');
    }


    if (!(age >= 1 && age <= 99)) {
      showError('age', 'Enter a valid age (1-99)');
      isValid = false;
    } else {
      clearError('age');
    }


    if (!/\S+@\S+\.\S+/.test(email)) {
      showError('email', 'Enter a valid email address');
      isValid = false;
    } else if (!isEmailUnique(email) && !editingRow) {
      showError('email', 'Email already exists');
      isValid = false;
    } else {
      clearError('email');
    }


    if (!/^\d{10}$/.test(phone)) {
      showError('phone', 'Enter a valid 10-digit phone number');
      isValid = false;
    } else if (!isPhoneUnique(phone) && !editingRow) {
      showError('phone', 'Phone number already exists');
      isValid = false;
    } else {
      clearError('phone');
    }

    if (!branch || !languages || !state || !city) {
      isValid = false;
    }

    $('#addBtn').prop('disabled', !isValid);
  }

 
  $('#dataForm').submit(function (e) {
    e.preventDefault();

    const name = $('#name').val();
    const age = $('#age').val();
    const email = $('#email').val();
    const phone = $('#phone').val();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[type="checkbox"]:checked').map(function () { return this.value; }).get().join(', ');
    const stateText = $('#state option:selected').text();
    const city = $('#city').val();

    const rowHtml = `
      <tr>
        <td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td>
        <td>${branch}</td><td>${languages}</td><td>${stateText}</td><td>${city}</td>
        <td class="actions">
          <button class="edit">Edit</button>
          <button class="delete">Delete</button>
        </td>
      </tr>`;

    if (editingRow) {
      editingRow.html($(rowHtml).html());
      editingRow = null;
    } else {
      $('#dataTable tbody').append(rowHtml);
    }

    $('#dataForm')[0].reset();
    $('#addBtn').prop('disabled', true);
    updateEntryCount();
  });

  
  $('#dataTable tbody').on('click', '.delete', function () {
    if (confirm("Are you sure to delete this entry?")) {
      $(this).closest('tr').fadeOut(300, function () {
        $(this).remove();
        updateEntryCount();
      });
    }
  });

  $('#dataTable tbody').on('click', '.edit', function () {
    editingRow = $(this).closest('tr');
    const data = editingRow.children('td').map(function () {
      return $(this).text();
    }).get();

    $('#name').val(data[0]);
    $('#age').val(data[1]);
    $('#email').val(data[2]);
    $('#phone').val(data[3]);
    $(`input[name="branch"][value="${data[4]}"]`).prop('checked', true);

    $('input[type="checkbox"]').prop('checked', false);
    data[5].split(', ').forEach(lang => {
      $(`input[type="checkbox"][value="${lang}"]`).prop('checked', true);
    });

    $('#state option').filter(function () {
      return $(this).text() === data[6];
    }).prop('selected', true).change();

    setTimeout(() => {
      $('#city').val(data[7]);
    }, 500);

    $('#addBtn').prop('disabled', false);
  });

  $('#search').on('keyup', function () {
    const value = $(this).val().toLowerCase();
    let visibleCount = 0;
    $('#dataTable tbody tr').each(function () {
      const rowText = $(this).text().toLowerCase();
      const match = rowText.includes(value);
      $(this).toggle(match);
      if (match) visibleCount++;
    });
    $('#entryCount').text(`Total Entries: ${visibleCount}`);
    if (visibleCount === 0) {
      $('#dataTable tbody').html('<tr><td colspan="9">No results found</td></tr>');
    }
  });


  $('.column-search').on('keyup', function () {
    const colIndex = $(this).data('col');
    const value = $(this).val().toLowerCase();

    $('#dataTable tbody tr').each(function () {
      const cellText = $(this).children('td').eq(colIndex).text().toLowerCase();
      const match = cellText.includes(value);
      $(this).toggle(match);
    });

    const visibleRows = $('#dataTable tbody tr:visible').length;
    $('#entryCount').text(`Total Entries: ${visibleRows}`);
  });

  function updateEntryCount() {
    const count = $('#dataTable tbody tr').length;
    $('#entryCount').text(`Total Entries: ${count}`);
  }


  function loadFallbackStates() {
    const fallbackStates = {
      "TELANGANA": "TG",
      "KERALA": "KL",
      "KARNATAKA": "KA",
      "MAHARASHTRA": "MH",
      "ANDHRA PRADESH": "AP"
    };
    $.each(fallbackStates, (state, code) => {
      $('#state').append(`<option value="${code}">${state}</option>`);
    });
  }


  function loadFallbackCities(stateCode) {
    const fallbackCities = {
      "TG": ["Hyderabad", "Warangal", "Nizamabad", "Karimnagar"],
      "AP": ["Vijayawada", "Visakhapatnam", "Guntur", "Tirupati"]
    };
    const cities = fallbackCities[stateCode] || ["City1", "City2"];
    $.each(cities, function (i, city) {
      $('#city').append(`<option value="${city}">${city}</option>`);
    });
  }
});
