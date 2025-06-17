
$(document).ready(function () {
    // Dummy Data
    const dummyStates = [
        { id: 'KA', name: 'Karnataka', cities: ['Bengaluru', 'Mysuru', 'Mangaluru'] },
        { id: 'MH', name: 'Maharashtra', cities: ['Mumbai', 'Pune', 'Nagpur'] },
        { id: 'DL', name: 'Delhi', cities: ['New Delhi', 'Dwarka', 'Rohini'] },
        { id: 'TN', name: 'Tamil Nadu', cities: ['Chennai', 'Coimbatore', 'Madurai'] }
    ];

    let entries = [];

    const $form = $('#dataForm');
    const $addBtn = $('#addBtn');
    const $tableBody = $('#dataTable tbody');
    const $search = $('#searchInput');
    const $entryCount = $('#entryCount');
    const $state = $('#state');
    const $city = $('#city');

    function populateStates() {
        $state.empty().append('<option value="">Select State</option>');
        dummyStates.forEach(st => {
            $state.append(`<option value="${st.id}">${st.name}</option>`);
        });
    }

    function populateCities(stateId) {
        $city.empty().append('<option value="">Select City</option>');
        const state = dummyStates.find(st => st.id === stateId);
        if (state) {
            state.cities.forEach(city => {
                $city.append(`<option value="${city}">${city}</option>`);
            });
        }
    }

    function validateForm() {
        let valid = true;
    
        const name = $('#name').val().trim();
        if (!name) {
            showError('name', 'Name is required.');
            valid = false;
        } else {
            showError('name', '');
        }
        
        const age = $('#age').val().trim();
        if (!age || isNaN(age) || Number(age) <= 0) {
            showError('age', 'Enter a valid positive age.');
            valid = false;
        } else {
            showError('age', '');
        }
        const email = $('#email').val().trim();
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email || !emailPattern.test(email)) {
            showError('email', 'Enter a valid email.');
            valid = false;
        } else {
            showError('email', '');
        }
        const phone = $('#phone').val().trim();
        if (!/^\d{10}$/.test(phone)) {
            showError('phone', 'Enter a valid 10-digit phone number.');
            valid = false;
        } else {
            showError('phone', '');
        }
        if (!$('input[name="branch"]:checked').val()) {
            showError('branch', 'Select a branch.');
            valid = false;
        } else {
            showError('branch', '');
        }
        if ($('input[name="languages[]"]:checked').length === 0) {
            showError('languages', 'Select at least one language.');
            valid = false;
        } else {
            showError('languages', '');
        }
        if (!$state.val()) {
            showError('state', 'Select a state.');
            valid = false;
        } else {
            showError('state', '');
        }
        if (!$city.val()) {
            showError('city', 'Select a city.');
            valid = false;
        } else {
            showError('city', '');
        }
        return valid;
    }

    function showError(field, message) {
        $(`#${field}Error`).text(message);
    }

    $form.on('submit', function (e) {
        e.preventDefault();
        $addBtn.prop('disabled', true);
        if (!validateForm()) {
            $addBtn.prop('disabled', false);
            return;
        }
        const entry = {
            name: $('#name').val().trim(),
            age: $('#age').val().trim(),
            email: $('#email').val().trim(),
            phone: $('#phone').val().trim(),
            branch: $('input[name="branch"]:checked').val(),
            languages: $('input[name="languages[]"]:checked').map(function () { return this.value; }).get().join(', '),
            state: $state.find('option:selected').text(),
            city: $city.val()
        };
        entries.push(entry);
        renderTable();
        $form[0].reset();
        $addBtn.prop('disabled', false);
        $city.html('<option value="">Select City</option>');
        showError('name', '');
        showError('age', '');
        showError('email', '');
        showError('phone', '');
        showError('branch', '');
        showError('languages', '');
        showError('state', '');
        showError('city', '');
    });

    function getSearchFilters() {
        return {
            name: $('#searchName').val().toLowerCase(),
            age: $('#searchAge').val().toLowerCase(),
            email: $('#searchEmail').val().toLowerCase(),
            phone: $('#searchPhone').val().toLowerCase(),
            branch: $('#searchBranch').val().toLowerCase(),
            languages: $('#searchLanguages').val().toLowerCase(),
            state: $('#searchState').val().toLowerCase(),
            city: $('#searchCity').val().toLowerCase(),
        };
    }

    function filterEntries() {
        const filters = getSearchFilters();
        return entries.filter(entry => {
            return (!filters.name || entry.name.toLowerCase().includes(filters.name)) &&
                (!filters.age || entry.age.toLowerCase().includes(filters.age)) &&
                (!filters.email || entry.email.toLowerCase().includes(filters.email)) &&
                (!filters.phone || entry.phone.toLowerCase().includes(filters.phone)) &&
                (!filters.branch || entry.branch.toLowerCase().includes(filters.branch)) &&
                (!filters.languages || entry.languages.toLowerCase().includes(filters.languages)) &&
                (!filters.state || entry.state.toLowerCase().includes(filters.state)) &&
                (!filters.city || entry.city.toLowerCase().includes(filters.city));
        });
    }

    $('.col-search').on('input', function () {
        renderTable(filterEntries());
    });

    function renderTable(filtered = null) {
        const data = filtered !== null ? filtered : filterEntries();
        $tableBody.empty();
        if (data.length === 0) {
            $tableBody.append('<tr><td colspan="8" style="text-align:center;">No results found</td></tr>');
        } else {
            data.forEach((entry, idx) => {
                $tableBody.append(`
          <tr data-idx="${idx}" class="added">
            <td>${entry.name}</td>
            <td>${entry.age}</td>
            <td>${entry.email}</td>
            <td>${entry.phone}</td>
            <td>${entry.branch}</td>
            <td>${entry.languages}</td>
            <td>${entry.state}</td>
            <td>${entry.city}</td>
            <td><button class="delete-btn">Delete</button></td>
          </tr>
        `);
            });
        }
        $entryCount.text(`Entries shown: ${data.length}`);
    }

    $tableBody.on('click', '.delete-btn', function () {
        const $row = $(this).closest('tr');
        const idx = $row.data('idx');
        if (confirm('Are you sure you want to delete this entry?')) {
            $row.addClass('removed');
            setTimeout(() => {
                entries.splice(idx, 1);
                renderTable();
            }, 500);
        }
    });

    $state.on('change', function () {
        populateCities($(this).val());
    });

    populateStates();
    $city.html('<option value="">Select City</option>');
    renderTable();
}); 