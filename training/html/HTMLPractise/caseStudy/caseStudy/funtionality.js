// Hardcoded state/city data
        const stateCityData = {
            "Telangana": ["Hyderabad", "Warangal", "Nizamabad"],
            "Maharashtra": ["Mumbai", "Pune", "Nagpur"],
            "Karnataka": ["Bengaluru", "Mysuru", "Mangaluru"],
            "Tamil Nadu": ["Chennai", "Coimbatore", "Madurai"]
        };

        $(document).ready(function () {
            let data = [];

           
            Object.keys(stateCityData).forEach(state => {
                $('#state').append(`<option value="${state}">${state}</option>`);
            });

           
            $('#state').on('change', function () {
                let state = $(this).val();
                let $city = $('#city');
                $city.html('<option value="">Select City</option>');
                if (state && stateCityData[state]) {
                    stateCityData[state].forEach(city => {
                        $city.append(`<option value="${city}">${city}</option>`);
                    });
                }
                $('#cityError').text('');
                $('#addBtn').prop('disabled', !validateForm());
            });

            
            $('#dataForm').on('input change', 'input, select', function () {
                $('#addBtn').prop('disabled', !validateForm());
            });

          
            function validateForm() {
                let valid = true;
                $('.error').text('');
                let name = $('#name').val().trim();
                let age = parseInt($('#age').val());
                let email = $('#email').val().trim();
                let phone = $('#phone').val().trim();
                let branch = $('input[name="branch"]:checked').val();
                let languages = $('input[name="languages"]:checked');
                let state = $('#state').val();
                let city = $('#city').val();

                if (!name) { $('#nameError').text('Name required'); valid = false; }
                if (!age || age <= 0) { $('#ageError').text('Enter valid age'); valid = false; }
                if (!email || !/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) { $('#emailError').text('Invalid email'); valid = false; }
                if (!phone || !/^\d{10}$/.test(phone)) { $('#phoneError').text('Enter 10-digit phone'); valid = false; }
                if (!branch) { $('#branchError').text('Select branch'); valid = false; }
                if (languages.length === 0) { $('#languagesError').text('Select at least one language'); valid = false; }
                if (!state) { $('#stateError').text('Select state'); valid = false; }
                if (!city) { $('#cityError').text('Select city'); valid = false; }
                return valid;
            }

           
            $('#dataForm').on('submit', function (e) {
                e.preventDefault();
                if (!validateForm()) return;
                let entry = {
                    name: $('#name').val().trim(),
                    age: $('#age').val(),
                    email: $('#email').val().trim(),
                    phone: $('#phone').val().trim(),
                    branch: $('input[name="branch"]:checked').val(),
                    languages: $('input[name="languages"]:checked').map(function () { return this.value; }).get().join(', '),
                    state: $('#state').val(),
                    city: $('#city').val()
                };
                data.push(entry);
                renderTable();
                this.reset();
                $('#city').html('<option value="">Select City</option>');
                $('#addBtn').prop('disabled', true);
            });

           
            function renderTable(filter = '') {
                let filtered = data.filter(row => Object.values(row).some(val => val.toLowerCase().includes(filter.toLowerCase())));
                let $tbody = $('#dataTable tbody');
                $tbody.empty();
                if (filtered.length === 0) {
                    $('#noResults').show();
                } else {
                    $('#noResults').hide();
                    filtered.forEach((row, idx) => {
                        let tr = $(`
                            <tr class="added-row">
                                <td>${row.name}</td>
                                <td>${row.age}</td>
                                <td>${row.email}</td>
                                <td>${row.phone}</td>
                                <td>${row.branch}</td>
                                <td>${row.languages}</td>
                                <td>${row.state}</td>
                                <td>${row.city}</td>
                                <td><button class="deleteBtn" data-idx="${idx}">Delete</button></td>
                            </tr>
                        `);
                        $tbody.append(tr);
                    });
                }
                $('#entryCount').text(`Entries: ${filtered.length}`);
            }

          
            $('#dataTable').on('click', '.deleteBtn', function () {
                let idx = $(this).data('idx');
                if (confirm('Are you sure you want to delete this entry?')) {
        
                    let $row = $(this).closest('tr');
                    $row.addClass('removed-row');
                    setTimeout(() => {
                        data.splice(idx, 1);
                        renderTable($('#search').val());
                    }, 400);
                }
            });

           
            $('#search').on('input', function () {
                renderTable($(this).val());
            });

            renderTable();
        });