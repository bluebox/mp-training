$(document).ready(function () {
  let statesData = [];

  // ✅ Load states from local JSON instead of API
  $.getJSON('states.json', function (data) {
    statesData = data.IN;
    statesData.forEach(state => {
      $('#state').append(`<option value="${state.code}">${state.name}</option>`);
    });
  });

  // ✅ Load cities based on selected state
  $('#state').on('change', function () {
    const stateCode = $(this).val();
    $('#city').html('<option value="">Select City</option>');

    const selectedState = statesData.find(s => s.code === stateCode);
    if (selectedState) {
      selectedState.cities.forEach(city => {
        $('#city').append(`<option value="${city}">${city}</option>`);
      });
    }
  });

  // ✅ Rest is same: Add, Delete, Search
  $('#dataForm').on('submit', function (e) {
    e.preventDefault();

    const name = $('#name').val().trim();
    const age = $('#age').val();
    const email = $('#email').val().trim();
    const phone = $('#phone').val().trim();
    const branch = $('input[name="branch"]:checked').val();
    const languages = $('input[name="languages"]:checked').map(function () {
      return this.value;
    }).get();
    const state = $('#state option:selected').text();
    const city = $('#city option:selected').text();

    if (!name || !age || age <= 0 || !email || !phone || phone.length !== 10 || !branch || languages.length === 0 || state === 'Select State' || city === 'Select City') {
      alert('Please fill all fields correctly.');
      return;
    }

    const row = `<tr>
      <td>${name}</td>
      <td>${age}</td>
      <td>${email}</td>
      <td>${phone}</td>
      <td>${branch}</td>
      <td>${languages.join(', ')}</td>
      <td>${state}</td>
      <td>${city}</td>
      <td><button class="deleteBtn">Delete</button></td>
    </tr>`;

    $('#dataTable tbody').append(row);
    updateCount();
    this.reset();
  });

  $(document).on('click', '.deleteBtn', function () {
    if (confirm('Delete this entry?')) {
      $(this).closest('tr').remove();
      updateCount();
    }
  });

  $('#search').on('keyup', function () {
    const value = $(this).val().toLowerCase();
    let visible = 0;

    $('#dataTable tbody tr').each(function () {
      const match = $(this).text().toLowerCase().includes(value);
      $(this).toggle(match);
      if (match) visible++;
    });

    $('#noResults').toggle(visible === 0);
    $('#entryCount').text(`Showing ${visible} entries`);
  });

  function updateCount() {
    const count = $('#dataTable tbody tr').length;
    $('#entryCount').text(`Total Entries: ${count}`);
  }
});
