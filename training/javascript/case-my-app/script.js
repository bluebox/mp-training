$(document).ready(function () {
  let stateList = {};
  let editingIndex = -1;
  let tableData = [];

  $.ajax({
    url: 'http://192.168.0.73:32114/partner/get-states?countryCode=IN',
    method: 'GET',
    success: function(res) {
      let stateObj = res;
      if (res.response) {
        stateObj = JSON.parse(res.response);
      }
      $('#state').empty().append('<option value="">Select State</option>');
      for (const [stateName, stateCode] of Object.entries(stateObj)) {
        stateList[stateCode] = stateName;
        $('#state').append(`<option value="${stateCode}">${stateName}</option>`);
      }
    },
    error: function() {
      alert('Failed to load states. Please check your network or API.');
    }
  });

  $('#state').on('change', function () {
    const stateCode = $(this).val();
    $('#city').html('<option value="">Select City</option>');
    if (stateCode) {
      $.ajax({
        url: `http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`,
        method: 'GET',
        success: function(res) {
          let cityObj = res;
          if (res.response) {
            cityObj = JSON.parse(res.response);
          }
          const added = new Set();
          for (const [cityName, cityCode] of Object.entries(cityObj)) {
            if (!added.has(cityName)) {
              $('#city').append(`<option value="${cityName}">${cityName}</option>`);
              added.add(cityName);
            }
          }
        }
      });
    }
  });

  function renderTable(data) {
    const tbody = $('#dataTable tbody');
    tbody.empty();
    if (data.length === 0) {
      $('#noResults').removeClass('hidden');
      return;
    }
    $('#noResults').addClass('hidden');
    data.forEach((row, idx) => {
      tbody.append(`
        <tr>
          <td>${row.name}</td>
          <td>${row.age}</td>
          <td>${row.email}</td>
          <td>${row.phone}</td>
          <td>${row.branch}</td>
          <td>${row.languages.join(', ')}</td>
          <td>${row.state}</td>
          <td>${row.city}</td>
          <td>
            <button class="editBtn" data-idx="${idx}">Edit</button>
            <button class="deleteBtn" data-idx="${idx}">Delete</button>
          </td>
        </tr>
      `);
    });
  }

  $('#dataTable').on('click', '.editBtn', function() {
    editingIndex = $(this).data('idx');
    const row = tableData[editingIndex];
    $('#name').val(row.name);
    $('#age').val(row.age);
    $('#email').val(row.email);
    $('#phone').val(row.phone);
    $(`input[name="branch"][value="${row.branch}"]`).prop('checked', true);
    $('.lang').prop('checked', false);
    row.languages.forEach(lang => {
      $(`.lang[value="${lang}"]`).prop('checked', true);
    });
    $('#state').val(row.state).trigger('change');
    setTimeout(() => {
      $('#city').val(row.city);
    }, 100);
    $('#addBtn').text('Update');
  });

  $('#dataForm').submit(function (e) {
    e.preventDefault();
    $('.error').text('');
    let valid = true;

    let name = $('#name').val().trim();
    let age = parseInt($('#age').val().trim());
    let email = $('#email').val().trim();
    let phone = $('#phone').val().trim();
    let branch = $('input[name="branch"]:checked').val();
    let languages = [];
    $('.lang:checked').each(function () { languages.push($(this).val()); });
    let stateCode = $('#state').val();
    let stateText = stateCode ? stateList[stateCode] : "";
    let city = $('#city').val();

    if (!name) { $('#nameError').text('Name required'); valid = false; }
    if (!age || age <= 0) { $('#ageError').text('Age must be positive'); valid = false; }
    if (!email || !/^\S+@\S+\.\S+$/.test(email)) { $('#emailError').text('Invalid email'); valid = false; }
    if (!/^\d{10}$/.test(phone)) { $('#phoneError').text('Phone must be 10 digits'); valid = false; }
    if (!branch) { $('#branchError').text('Select a branch'); valid = false; }
    if (languages.length === 0) { $('#langError').text('Select at least one language'); valid = false; }
    if (!stateCode) { $('#stateError').text('Select state'); valid = false; }
    if (!city) { $('#cityError').text('Select city'); valid = false; }

    // Unique email and phone check
    let duplicate = tableData.some((row, idx) => {
      if (editingIndex > -1 && idx === editingIndex) return false;
      return row.email === email || row.phone === phone;
    });
    if (duplicate) {
      if (tableData.some((row, idx) => row.email === email && (editingIndex === -1 || idx !== editingIndex))) {
        $('#emailError').text('Email must be unique');
      }
      if (tableData.some((row, idx) => row.phone === phone && (editingIndex === -1 || idx !== editingIndex))) {
        $('#phoneError').text('Phone must be unique');
      }
      valid = false;
    }

    if (!valid) return;

    let rowData = {
      name,
      age,
      email,
      phone,
      branch,
      languages,
      state: stateText,
      city
    };

    if (editingIndex > -1) {
      tableData[editingIndex] = rowData;
      editingIndex = -1;
      $('#addBtn').text('Add');
    } else {
      tableData.push(rowData);
    }
    renderTable(filteredTableData());
    $('#dataForm')[0].reset();
    updateCount();
  });

  $('#dataTable').on('click', '.deleteBtn', function () {
    if (confirm('Are you sure you want to delete this row?')) {
      let idx = $(this).data('idx');
      tableData.splice(idx, 1);
      renderTable(filteredTableData());
    }
  });

  $('#searchInput').on('keyup', function () {
    let value = $(this).val().toLowerCase();
    let rows = $('#dataTable tbody tr');
    let visibleRows = 0;

    rows.each(function () {
      let rowText = $(this).text().toLowerCase();
      if (rowText.includes(value)) {
        $(this).show();
        visibleRows++;
      } else {
        $(this).hide();
      }
    });

    $('#noResults').toggle(visibleRows === 0);
    updateCount();
  });

  $('.col-search').on('input', function() {
    renderTable(filteredTableData());
  });

  function filteredTableData() {
    let filtered = tableData.slice();
    $('.col-search').each(function() {
      const col = $(this).data('col');
      const val = $(this).val().toLowerCase();
      if (val) {
        filtered = filtered.filter(row => {
          const keys = ['name','age','email','phone','branch','languages','state','city'];
          let cell = row[keys[col]];
          if (Array.isArray(cell)) cell = cell.join(', ');
          return String(cell).toLowerCase().includes(val);
        });
      }
    });
    return filtered;
  }

  function updateCount() {
    let visible = $('#dataTable tbody tr:visible').length;
    $('#count').text(`Total: ${visible}`);
  }
});
