$(document).ready(function() {
    const stateApiUrl = 'http://192.168.0.73:32114/partner/get-states?countryCode=IN';
    const cityApiBaseUrl = 'http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=';
    let uniqueId = 0;

    function isDuplicate(email, phone, editRowId) {
        let duplicateFound = false;
        $('#data-table-body tr').each(function() {
            const currentRowId = $(this).attr('id');
            if (editRowId && editRowId === currentRowId) return;
            if ($(this).find('td:eq(2)').text() === email || $(this).find('td:eq(3)').text() === phone) {
                duplicateFound = true;
                return false;
            }
        });
        return duplicateFound;
    }

    function updateScrollableState() {
        const rowCount = $('#data-table-body tr').length;
        const $tableContainer = $('.scroll-body');
        if (rowCount > 2) {
            $tableContainer.addClass('scrollable');
        } else {
            $tableContainer.removeClass('scrollable');
        }
    }

    $.ajax({
        url: stateApiUrl,
        success: function(data) {
            const statesObject = JSON.parse(data.response);
            const $stateDropdown = $('#state');
            for (const stateName in statesObject) {
                $stateDropdown.append(`<option value="${statesObject[stateName]}">${stateName}</option>`);
            }
        },
        error: function() { alert('Could not fetch states.'); }
    });

    $('#state').on('change', function() {
        const stateCode = $(this).val();
        const $cityDropdown = $('#city');
        $cityDropdown.html('<option value="">-- Select City --</option>');
        if (stateCode) {
            $.ajax({
                url: `${cityApiBaseUrl}${stateCode}`,
                success: function(data) {
                    const citiesObject = JSON.parse(data.response);
                    for (const cityName in citiesObject) {
                        $cityDropdown.append(`<option value="${cityName}">${cityName}</option>`);
                    }
                },
                error: function() { alert('Could not fetch cities.'); }
            });
        }
    });

    $('#data-form').on('submit', function(event) {
        event.preventDefault();
        $('.error-message').remove();
        let isValid = true;
        const name = $('#name').val().trim();
        if (!/^[a-zA-Z\s]+$/.test(name)) {
            isValid = false;
            $('#name').after('<span class="error-message">Name must contain only letters and spaces.</span>');
        }
        const age = parseInt($('#age').val().trim(), 10);
        if (isNaN(age) || age < 1 || age > 120) {
            isValid = false;
            $('#age').after('<span class="error-message">Please enter a valid age (1-120).</span>');
        }
        const email = $('#email').val().trim();
        if (!/^\S+@\S+\.\S+$/.test(email)) { isValid = false; $('#email').after('<span class="error-message">Enter a valid email.</span>'); }
        const phone = $('#phone').val().trim();
        if (!/^\d{10}$/.test(phone)) { isValid = false; $('#phone').after('<span class="error-message">Phone must be 10 digits.</span>'); }
        const editRowId = $('#edit-row-index').val();
        if (isDuplicate(email, phone, editRowId)) { isValid = false; $('#email').after('<span class="error-message">This email or phone number already exists.</span>'); }
        const branch = $('input[name="branch"]:checked').val();
        if (!branch) { isValid = false; $('input[name="branch"]').closest('.input-grid').after('<span class="error-message">Select a branch.</span>'); }
        const languages = $('input[name="language"]:checked').map(function() { return $(this).val(); }).get();
        if (languages.length === 0) { isValid = false; $('input[name="language"]').closest('.input-grid').after('<span class="error-message">Select at least one language.</span>'); }
        const state = $('#state').val();
        if (!state) { isValid = false; $('#state').after('<span class="error-message">Select a state.</span>'); }
        const city = $('#city').val();
        if (!city) { isValid = false; $('#city').after('<span class="error-message">Select a city.</span>'); }

        if (isValid) {
            if (editRowId) {
                const $rowToUpdate = $('#' + editRowId);
                $rowToUpdate.find('td:eq(0)').text(name);
                $rowToUpdate.find('td:eq(1)').text(age);
                $rowToUpdate.find('td:eq(2)').text(email);
                $rowToUpdate.find('td:eq(3)').text(phone);
                $rowToUpdate.find('td:eq(4)').text(branch);
                $rowToUpdate.find('td:eq(5)').text(languages.join(', '));
                $rowToUpdate.find('td:eq(6)').text($('#state option:selected').text());
                $rowToUpdate.find('td:eq(7)').text(city);
            } else {
                const rowHtml = `<tr id="row-${uniqueId++}"><td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td><td>${branch}</td><td>${languages.join(', ')}</td><td>${$('#state option:selected').text()}</td><td>${city}</td><td class="actions"><button class="edit-btn">Edit</button><button class="delete-btn">Delete</button></td></tr>`;
                $('#data-table-body').append(rowHtml).find('tr:last').addClass('new-row-animation');
            }
            resetForm();
        }
    });

    $('#data-table-body').on('click', '.edit-btn', function() {
        const $row = $(this).closest('tr');
        $('#name').val($row.find('td:eq(0)').text());
        $('#age').val($row.find('td:eq(1)').text());
        $('#email').val($row.find('td:eq(2)').text());
        $('#phone').val($row.find('td:eq(3)').text());
        $(`input[name="branch"][value="${$row.find('td:eq(4)').text()}"]`).prop('checked', true);
        const languages = $row.find('td:eq(5)').text().split(', ');
        $('input[name="language"]').prop('checked', false);
        languages.forEach(lang => { $(`input[name="language"][value="${lang}"]`).prop('checked', true); });
        const stateName = $row.find('td:eq(6)').text();
        const stateValue = $(`#state option`).filter(function() { return $(this).html() == stateName; }).val();
        $('#state').val(stateValue).trigger('change');
        setTimeout(() => { $('#city').val($row.find('td:eq(7)').text()); }, 200);
        $('#edit-row-index').val($row.attr('id'));
        $('#add-btn').text('Update Entry').addClass('update-mode');
        window.scrollTo(0, 0);
    });

    $('#data-table-body').on('click', '.delete-btn', function() {
        if (confirm('Are you sure you want to delete this entry?')) {
            $(this).closest('tr').fadeOut(400, function() {
                $(this).remove();
                updateEntryCount();
                handleNoResults();
                updateScrollableState(); // Check scroll state after deleting
            });
        }
    });

    function resetForm() {
        $('#data-form')[0].reset();
        $('#edit-row-index').val('');
        $('#add-btn').text('Add Entry').removeClass('update-mode');
        filterTable();
    }
    
    // NEW: Updated filter logic for new header structure
    function filterTable() {
        const columnTerms = $('.column-search').map(function() { return $(this).val().toLowerCase(); }).get();
        $('#data-table-body tr').each(function() {
            const $row = $(this);
            let isVisible = true;
            $row.find('td').each(function(index) {
                if (columnTerms[index] && !$(this).text().toLowerCase().includes(columnTerms[index])) {
                    isVisible = false;
                    return false;
                }
            });
            $row.toggle(isVisible);
        });
        updateEntryCount();
        handleNoResults();
    }

    $('thead').on('keyup', '.column-search', filterTable);

    function updateEntryCount() {
        const totalCount = $('#data-table-body tr').length;
        const visibleCount = $('#data-table-body tr:visible').length;
        $('#entry-count').text(`Showing ${visibleCount} of ${totalCount} entries.`);
    }

    function handleNoResults() {
        const totalCount = $('#data-table-body tr').length;
        const visibleCount = $('#data-table-body tr:visible').length;
        $('#no-results').toggle(totalCount > 0 && visibleCount === 0);
    }
    
    // Initial check on page load
    updateScrollableState();
});