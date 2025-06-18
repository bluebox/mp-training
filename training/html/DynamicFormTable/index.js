
async function getStates(country) {
      const response = await fetch("https://countriesnow.space/api/v0.1/countries/states", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ country: country })
      });

      const result = await response.json();
       const dropDown = $("#state");
        dropDown.empty(); // Clear previous options

      if (!result.error) {
        console.log("data fetch successfully")
       

        dropDown.append('<option value="">--Select State--</option>'); // Default option

        result.data.states.forEach(state => {
          dropDown.append(`<option value="${state.name}">${state.name}</option>`);
        });
      }

      else{
         console.log("data fetch failed")
        dropDown.append('<option value="">--Select State--</option>');
      }
}

async function getCities(country, state) {
      const response = await fetch("https://countriesnow.space/api/v0.1/countries/state/cities", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ country, state })
      });

      const result = await response.json();

      if (!result.error) {
        const $cityDropdown = $("#city");
        $cityDropdown.empty().append('<option value="">--Select City--</option>');

        result.data.forEach(city => {
          $cityDropdown.append(`<option value="${city}">${city}</option>`);
        });
      }
    }


// Example: Load states of India on page load
$(document).ready(function () {
    getStates("India");

     $("#state").change(function () {
        const selectedState = $(this).val();
        if (selectedState) {
          getCities("India", selectedState);
        } else {
          $("#city").empty().append('<option value="">--Select City--</option>');
        }
    });
});



$(document).ready(function () {
  const table = $('#userTable').DataTable({
    scrollY: '300px',
    scrollCollapse: true,
    paging: false,
    dom: 'lrtip'  // Remove default search box
  });

  // Custom search for Name column
  $('#customSearch').on('keyup', function () {
    table.columns(0).search(this.value).draw(); // column 0 = Name
  });

  // Form input validations
  $('#name').on('input', function () {
    this.value = this.value.replace(/[^a-zA-Z\s]/g, '').replace(/\s{2,}/g, ' ');
  });
  $('#age').on('input', function () {
    this.value = this.value.replace(/\D/g, '').slice(0, 3);
  });
  $('#phone').on('input', function () {
    this.value = this.value.replace(/\D/g, '').slice(0, 10);
  });

  // Submit handler
  $('#userForm').submit(function (e) {
    e.preventDefault();
    const name = $('#name').val().trim();
    const age = parseInt($('#age').val());
    const email = $('#email').val();
    const phone = $('#phone').val();
    const state = $('#state option:selected').text();
    const city = $('#city option:selected').text();
    const editIndex = $('#editIndex').val();

    if (!name || !email || !phone || age < 1 || age > 120 || phone.length !== 10 || city === "") {
      alert('Please enter valid details.');
      return;
    }

    const rowData = [
      name, age, email, phone, state, city,
      `<button class="btn btn-update">Update</button>`,
      `<button class="btn btn-delete">Delete</button>`
    ];

    if (editIndex !== "") {
      table.row(editIndex).data(rowData).draw();
    } else {
      table.row.add(rowData).draw();
    }

    $('#userForm')[0].reset();
    $('#editIndex').val('');
    $('#state').val("2");
    $('#city').html(`
      <option value="">--Select City--</option>
    `);
  });

  // Delete row
  $('#userTable tbody').on('click', '.btn-delete', function () {
    table.row($(this).parents('tr')).remove().draw();
  });

  // Update row
  $('#userTable tbody').on('click', '.btn-update',async function () {
    const row = table.row($(this).parents('tr'));
    const data = row.data();
    $('#editIndex').val(row.index());
    $('#name').val(data[0]);
    $('#age').val(data[1]);
    $('#email').val(data[2]);
    $('#phone').val(data[3]);
    $('#state option').filter(function () {
      return $(this).text() === data[4];
    }).prop('selected' , true);

    // Only proceed if a state is selected
    if (data[4]) {
        // Load cities for the selected state
        await getCities("India", data[4]);
        
        // Now set the city selection after cities are loaded
        $('#city').val($('#city option').filter(function () {
            return $(this).text() === data[5];
        }).val());
    }
    /*
    $('#city option').filter(function ()
        {
            return $(this).text() === data[5];
        }
        ).prop('selected' , true);*/
  });
});
