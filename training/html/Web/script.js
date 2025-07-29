$(document).ready(function() {
    const API_KEY = 'ME95TTJOZERmVG9nb3hKNFNtcEJIWHRNMmNqNzdqVFRNclFnTHhRNw==';

    let rowBeingEdited = null;

    // Function to fetch and populate countries
    function fetchCountries() {
        $.ajax({
            url: "https://api.countrystatecity.in/v1/countries",
            method: "GET",
            headers: {
                "X-CSCAPI-KEY": API_KEY
            },
            success: function(data) {
                const $countrySelect = $('#country');
                $countrySelect.empty().append('<option value="">Select Country</option>');

                data.forEach(country => {
                    $countrySelect.append(`<option value="${country.iso2}">${country.name}</option>`);
                });

                // // Pre-select India if it exists
                // const indiaOption = $countrySelect.find('option[value="IN"]');
                // if (indiaOption.length) {
                //     $countrySelect.val('IN').trigger('change'); 
                // }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error fetching countries:", textStatus, errorThrown);
                $('#formMessage').text('Error loading countries. Please check your API key and network connection.').show();
            }
        });
    }

    // Function to fetch states based on country ISO2 code
    function fetchStates(countryIso2) {
        if (!countryIso2) {
            $('#state').empty().append('<option value="">Select State</option>');
            $('#city').empty().append('<option value="">Select City</option>');
            return;
        }

        $.ajax({
            url: `https://api.countrystatecity.in/v1/countries/${countryIso2}/states`,
            method: "GET",
            headers: {
                "X-CSCAPI-KEY": API_KEY
            },
            success: function(data) {
                const $stateSelect = $('#state');
                $stateSelect.empty().append('<option value="">Select State</option>');

                data.forEach(state => {
                    $stateSelect.append(`<option value="${state.iso2}">${state.name}</option>`);
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error fetching states:", textStatus, errorThrown);
                $('#formMessage').text('Error loading states.').show();
            }
        });
    }

    // Function to fetch cities based on country and state ISO2 codes
    function fetchCities(countryIso2, stateIso2) {
        if (!countryIso2 || !stateIso2) { 
            $('#city').empty().append('<option value="">Select City</option>');
            return;
        }

        $.ajax({
            url: `https://api.countrystatecity.in/v1/countries/${countryIso2}/states/${stateIso2}/cities`,
            method: "GET",
            headers: {
                "X-CSCAPI-KEY": API_KEY
            },
            success: function(data) {
                const $citySelect = $('#city');
                $citySelect.empty().append('<option value="">Select City</option>');

                data.forEach(city => {
                    $citySelect.append(`<option value="${city.name}">${city.name}</option>`);
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error fetching cities:", textStatus, errorThrown);
                $('#formMessage').text('Error loading cities.').show();
            }
        });
    }

    // Initial fetch for countries when the page loads
    fetchCountries();

    // Event listener for country dropdown change
    $('#country').on('change', function() {
        const selectedCountryIso2 = $(this).val();
        fetchStates(selectedCountryIso2);
        $('#city').empty().append('<option value="">Select City</option>'); 
    });

    // Event listener for state dropdown change
    $('#state').on('change', function() {
        const selectedStateIso2 = $(this).val();
        const selectedCountryIso2 = $('#country').val();
        if (selectedStateIso2 && selectedCountryIso2) {
            fetchCities(selectedCountryIso2, selectedStateIso2);
        } else {
            $('#city').empty().append('<option value="">Select City</option>'); 
        }
    });


    $('#studentForm').on('submit', function(event) {
        event.preventDefault(); 

        const firstName = $('#firstname').val().trim();
        const lastName = $('#lastname').val().trim();
        const age = $('#age').val();
        const email = $('#email').val().trim();
        const phoneNumber = $('#phonenumber').val().trim();
        const branch = $('input[name="branch"]:checked').val();

        const languages = [];
        $('input[name="language"]:checked').each(function() {
            languages.push($(this).val());
        });

        const countryText = $('#country option:selected').text(); 
        const countryIso2 = $('#country').val();
        const stateText = $('#state option:selected').text();
        const stateIso2 = $('#state').val();
        const cityText = $('#city option:selected').text();


        // Clear previous error messages and hide the message label
        $('#formMessage').text('').hide();

        let isValid = true;
        let errorMessages = [];

        // Validation checks
        if (!firstName) {
            errorMessages.push('First Name is required.');
            isValid = false;
        }
        if (!lastName) {
            errorMessages.push('Last Name is required.');
            isValid = false;
        }
        if (!age || isNaN(age) || parseInt(age) <= 0) {
                    errorMessages.push('Age must be a positive number.');
            isValid = false;
        }
        if(age>100){
            errorMessages.push('Age must be less than or equal to 100.');
            isValid = false;
        }
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email || !emailRegex.test(email)) {
            errorMessages.push('Please enter a valid email address.');
            isValid = false;
        }
        const phoneNumberRegex = /^\d{10}$/;
        if (!phoneNumber || !phoneNumberRegex.test(phoneNumber)) {
            errorMessages.push('Phone number must be 10 digits.');
            isValid = false;
        }
        if (!branch) {
            errorMessages.push('Branch must be selected.');
            isValid = false;
        }
        if (languages.length === 0) {
            errorMessages.push('At least one language must be selected.');
            isValid = false;
        }
        if (!countryText || countryText === "Select Country") {
            errorMessages.push('Country must be selected.');
            isValid = false;
        }
        if (!stateText || stateText === "Select State") {
            errorMessages.push('State must be selected.');
            isValid = false;
        }
        if (!cityText || cityText === "Select City") {
            errorMessages.push('City must be selected.');
            isValid = false;
        }

        // If validation fails, display messages and stop
        if (!isValid) {
            $('#formMessage').text('Please correct the following: ' + errorMessages.join(' ')).show();
            return;
        }

        // Determine if adding new or updating existing row
        if (rowBeingEdited) {
            // Update the existing row
            const $row = $(rowBeingEdited);
            $row.children().eq(0).text(`${firstName} ${lastName}`); 
            $row.children().eq(1).text(age);
            $row.children().eq(2).text(email);
            $row.children().eq(3).text(phoneNumber);
            $row.children().eq(4).text(branch.toUpperCase());
            $row.children().eq(5).text(languages.join(', '));
            $row.children().eq(6).text(countryText);
            $row.children().eq(7).text(stateText);
            $row.children().eq(8).text(cityText);

            // Reset the edit state
            rowBeingEdited = null;
            $('#submitButton').text('Submit');
        } else {
            const newRow = `
                <tr>
                    <td>${firstName} ${lastName}</td>
                    <td>${age}</td>
                    <td>${email}</td>
                    <td>${phoneNumber}</td>
                    <td>${branch.toUpperCase()}</td>
                    <td>${languages.join(', ').toUpperCase()}</td>
                    <td>${countryText}</td>
                    <td>${stateText}</td>
                    <td>${cityText}</td>
                    <td>
                        <button class="edit-button">Edit</button>
                        <button class="delete-button">Delete</button>
                    </td>
                </tr>
            `;
            $('#dataTable tbody').append(newRow);
        }

        // Clear the form fields
        $('#studentForm')[0].reset();
        $('input[name="branch"]').prop('checked', false);
        $('input[name="language"]').prop('checked', false);
        $('#country').val('').trigger('change');

        console.log({
            firstName, lastName, age, email, phoneNumber,
            branch, languages, countryText, stateText, cityText
        });
    });

    // Handle delete button click
    $('#dataTable tbody').on('click', '.delete-button', function() {
        if (confirm('Are you sure you want to delete this entry?')) {
            $(this).closest('tr').remove();
            $('#search').trigger('keyup');
        }
    });

    // Handle edit button click
    $('#dataTable tbody').on('click', '.edit-button', function() {
        const $row = $(this).closest('tr');
        rowBeingEdited = $row[0];

        // Get data from the clicked row
        const cells = $row.children();
        const fullName = cells.eq(0).text().split(' ');
        const firstName = fullName[0];
        const lastName = fullName.slice(1).join(' ');
        const age = cells.eq(1).text();
        const email = cells.eq(2).text();
        const phoneNumber = cells.eq(3).text();
        const branch = cells.eq(4).text().toLowerCase();
        const languages = cells.eq(5).text().split(', ').map(lang => lang.trim().toLowerCase());
        const countryText = cells.eq(6).text();
        const stateText = cells.eq(7).text();
        const cityText = cells.eq(8).text();

        // Populate the form fields
        $('#firstname').val(firstName);
        $('#lastname').val(lastName);
        $('#age').val(age);
        $('#email').val(email);
        $('#phonenumber').val(phoneNumber);

        // Populate Branch radio buttons
        $('input[name="branch"]').prop('checked', false);
        $(`input[name="branch"][value="${branch}"]`).prop('checked', true);

        // Populate Languages checkboxes
        $('input[name="language"]').prop('checked', false);
        languages.forEach(lang => {
            $(`input[name="language"][value="${lang}"]`).prop('checked', true);
        });

        // Populate Country, State, City dropdowns dynamically
        $.ajax({
            url: "https://api.countrystatecity.in/v1/countries",
            method: "GET",
            headers: { "X-CSCAPI-KEY": API_KEY },
            success: function(countriesData) {
                const $countrySelect = $('#country');
                $countrySelect.empty().append('<option value="">Select Country</option>');
                let selectedCountryIso2 = '';

                countriesData.forEach(c => {
                    $countrySelect.append(`<option value="${c.iso2}">${c.name}</option>`);
                    if (c.name === countryText) {
                        selectedCountryIso2 = c.iso2;
                    }
                });
                $countrySelect.val(selectedCountryIso2);

                if (selectedCountryIso2) {
                    $.ajax({
                        url: `https://api.countrystatecity.in/v1/countries/${selectedCountryIso2}/states`,
                        method: "GET",
                        headers: { "X-CSCAPI-KEY": API_KEY },
                        success: function(statesData) {
                            const $stateSelect = $('#state');
                            $stateSelect.empty().append('<option value="">Select State</option>');
                            let selectedStateIso2 = '';

                            statesData.forEach(s => {
                                $stateSelect.append(`<option value="${s.iso2}">${s.name}</option>`);
                                if (s.name === stateText) {
                                    selectedStateIso2 = s.iso2;
                                }
                            });
                            $stateSelect.val(selectedStateIso2);

                            if (selectedStateIso2) {
                                $.ajax({
                                    url: `https://api.countrystatecity.in/v1/countries/${selectedCountryIso2}/states/${selectedStateIso2}/cities`,
                                    method: "GET",
                                    headers: { "X-CSCAPI-KEY": API_KEY },
                                    success: function(citiesData) {
                                        const $citySelect = $('#city');
                                        $citySelect.empty().append('<option value="">Select City</option>');
                                        citiesData.forEach(c => {
                                            $citySelect.append(`<option value="${c.name}">${c.name}</option>`);
                                        });
                                        $citySelect.val(cityText);
                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {
                                        console.error("Error fetching cities for edit:", textStatus, errorThrown);
                                        $('#formMessage').text('Error loading cities for edit.').show();
                                    }
                                });
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error("Error fetching states for edit:", textStatus, errorThrown);
                            $('#formMessage').text('Error loading states for edit.').show();
                        }
                    });
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error fetching countries for edit:", textStatus, errorThrown);
                $('#formMessage').text('Error loading countries for edit.').show();
            }
        });


        // Change submit button text
        $('#submitButton').text('Update');
    });

    // Search functionality
    $('#search').on('keyup', function() {
        const searchText = $(this).val().toLowerCase();
        let hasResults = false;

        $('#dataTable tbody tr').each(function() {
            const rowText = $(this).text().toLowerCase();
            if (rowText.includes(searchText)) {
                $(this).show();
                hasResults = true;
            } else {
                $(this).hide();
            }
        });

        if (hasResults) {
            $('#noResultsMessage').hide();
        } else {
            $('#noResultsMessage').show();
        }
    });
});