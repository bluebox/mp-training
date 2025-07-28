// script.js (Final Version Using Provided Local API)
document.addEventListener('DOMContentLoaded', () => {
    const stateSelect = document.getElementById('stateSelect');
    const citySelect = document.getElementById('citySelect');
    const addBtn = document.getElementById('addBtn');
    const form = document.getElementById('dataForm');
    const tableBody = document.querySelector('#dataTable tbody');
    const searchBox = document.getElementById('searchBox');
    const entryCount = document.getElementById('entryCount');

    function fetchStates() {
        fetch("http://192.168.0.73:32114/partner/get-states?countryCode=IN")
            .then(res => {
                if (!res.ok) throw new Error("Network response was not ok");
                return res.json();
            })
            .then(states => {
                console.log("States data:", states);
                if (!Array.isArray(states)) {
                    alert("Unexpected response format for states");
                    return;
                }
                states.forEach(state => {
                    const stateCode = state.stateCode || state.code;
                    const stateName = state.stateName || state.name;
                    if (stateCode && stateName) {
                        const opt = document.createElement('option');
                        opt.value = stateCode;
                        opt.textContent = stateName;
                        stateSelect.appendChild(opt);
                    }
                });
            })
            .catch(error => {
                console.error("Failed to load states:", error);
                alert("Could not load states. Check your network or API response.");
            });
    }

    function fetchCities(stateCode) {
        citySelect.innerHTML = '<option value="">Select City</option>';
        fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`)
            .then(res => {
                if (!res.ok) throw new Error("Network response was not ok");
                return res.json();
            })
            .then(cities => {
                console.log("Cities data:", cities);
                if (!Array.isArray(cities)) {
                    alert("Unexpected response format for cities");
                    return;
                }
                cities.forEach(city => {
                    const cityName = city.cityName || city.name;
                    if (cityName) {
                        const opt = document.createElement('option');
                        opt.value = cityName;
                        opt.textContent = cityName;
                        citySelect.appendChild(opt);
                    }
                });
            })
            .catch(error => {
                console.error("Failed to load cities:", error);
                alert("Could not load cities. Check your network or API response.");
            });
    }

    stateSelect.addEventListener('change', () => {
        const stateCode = stateSelect.value;
        if (stateCode) fetchCities(stateCode);
    });

    function validateForm() {
        const name = document.getElementById('name').value.trim();
        const age = parseInt(document.getElementById('age').value);
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const branch = document.querySelector('input[name="branch"]:checked');
        const languages = document.querySelectorAll('input[type="checkbox"]:checked');
        const state = stateSelect.value;
        const city = citySelect.value;

        const isValid = name && age > 0 && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) && /^\d{10}$/.test(phone) &&
            branch && languages.length > 0 && state && city;

        addBtn.disabled = !isValid;
        return isValid;
    }

    form.addEventListener('input', validateForm);
    form.addEventListener('change', validateForm);

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        const name = document.getElementById('name').value;
        const age = document.getElementById('age').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const branch = document.querySelector('input[name="branch"]:checked').value;
        const languages = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(cb => cb.value).join(', ');
        const state = stateSelect.options[stateSelect.selectedIndex].text;
        const city = citySelect.value;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td>
            <td>${branch}</td><td>${languages}</td><td>${state}</td><td>${city}</td>
            <td><button class='deleteBtn'>Delete</button></td>
        `;

        row.style.display = 'none';
        tableBody.appendChild(row);
        setTimeout(() => row.style.display = 'table-row', 100);

        form.reset();
        addBtn.disabled = true;
        updateCount();
    });

    tableBody.addEventListener('click', (e) => {
        if (e.target.classList.contains('deleteBtn')) {
            if (confirm('Are you sure you want to delete this row?')) {
                const row = e.target.closest('tr');
                row.style.opacity = '0';
                setTimeout(() => {
                    row.remove();
                    updateCount();
                }, 300);
            }
        }
    });

    searchBox.addEventListener('input', () => {
        const term = searchBox.value.toLowerCase();
        let found = false;

        Array.from(tableBody.rows).forEach(row => {
            const match = row.innerText.toLowerCase().includes(term);
            row.style.display = match ? '' : 'none';
            if (match) found = true;
        });

        updateCount();

        if (!found && !document.getElementById('noResult')) {
            const noResult = document.createElement('tr');
            noResult.id = 'noResult';
            noResult.innerHTML = `<td colspan="9">No results found</td>`;
            tableBody.appendChild(noResult);
        } else if (found) {
            const existing = document.getElementById('noResult');
            if (existing) existing.remove();
        }
    });

    function updateCount() {
        const visibleRows = Array.from(tableBody.rows).filter(row => row.style.display !== 'none');
        entryCount.textContent = `Total Entries: ${visibleRows.length}`;
    }

    fetchStates();
});