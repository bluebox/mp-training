document.addEventListener('DOMContentLoaded', () => {
    const stateDropdown = document.getElementById('stateSelect');
    const cityDropdown = document.getElementById('citySelect');
    const addButton = document.getElementById('addBtn');
    const formElement = document.getElementById('dataForm');
    const tableBody = document.querySelector('#dataTable tbody');
    const searchInput = document.getElementById('searchBox');
    const countDisplay = document.getElementById('entryCount');

    function loadStates() {
        fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
            .then(resp => resp.json())
            .then(data => {
                const statesArray = data.response.slice(1, -1).split(',');
                stateDropdown.innerHTML = '<option value="">Select State</option>';
                statesArray.forEach(item => {
                    const [name, code] = item.split(':');
                    const opt = document.createElement('option');
                    opt.value = code.slice(1, -1);
                    opt.textContent = name;
                    stateDropdown.appendChild(opt);
                });
            })
            .catch(err => console.error('Error fetching states:', err));
    }

    function loadCities(stateCode) {
        console.log("Fetching for:", stateCode);
        const url = `http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`;
        console.log(url);

        fetch(url)
            .then(resp => resp.json())
            .then(data => {
                const cityList = data.response.slice(1, -1).split(',');
                cityDropdown.innerHTML = '<option value="">Select City</option>';
                cityList.forEach(city => {
                    const parts = city.split(':');
                    const opt = document.createElement('option');
                    opt.value = parts[1].slice(1, -1);
                    opt.textContent = parts[0];
                    cityDropdown.appendChild(opt);
                });
            })
            .catch(err => console.error('Error fetching cities:', err));
    }

    stateDropdown.addEventListener('change', () => {
        const code = stateDropdown.value;
        if (code) loadCities(code);
    });

    function isFormValid() {
        const name = document.getElementById('name').value.trim();
        const age = parseInt(document.getElementById('age').value);
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const selectedBranch = document.querySelector('input[name="branch"]:checked');
        const selectedLanguages = document.querySelectorAll('input[type="checkbox"]:checked');
        const selectedState = stateDropdown.value;
        const selectedCity = cityDropdown.value;

        const valid = name && age > 0 && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
            && /^\d{10}$/.test(phone) && selectedBranch && selectedLanguages.length > 0
            && selectedState && selectedCity;

        addButton.disabled = !valid;
        return valid;
    }

    formElement.addEventListener('input', isFormValid);
    formElement.addEventListener('change', isFormValid);

    formElement.addEventListener('submit', (e) => {
        e.preventDefault();
        if (!isFormValid()) return;

        const name = document.getElementById('name').value;
        const age = document.getElementById('age').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const branch = document.querySelector('input[name="branch"]:checked').value;
        const languages = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(cb => cb.value).join(', ');
        const stateName = stateDropdown.options[stateDropdown.selectedIndex].text;
        const cityName = cityDropdown.value;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td>
            <td>${branch}</td><td>${languages}</td><td>${stateName}</td><td>${cityName}</td>
            <td><button class='deleteBtn'>Delete</button></td>
        `;
        row.style.display = 'none';
        tableBody.appendChild(row);
        setTimeout(() => row.style.display = 'table-row', 100);

        formElement.reset();
        addButton.disabled = true;
        updateEntryCount();
    });

    tableBody.addEventListener('click', (e) => {
        if (e.target.classList.contains('deleteBtn')) {
            if (confirm('Delete this entry?')) {
                const tr = e.target.closest('tr');
                tr.style.opacity = '0';
                setTimeout(() => {
                    tr.remove();
                    updateEntryCount();
                }, 300);
            }
        }
    });

    searchInput.addEventListener('input', () => {
        const keyword = searchInput.value.toLowerCase();
        let anyMatch = false;

        Array.from(tableBody.rows).forEach(row => {
            const visible = row.innerText.toLowerCase().includes(keyword);
            row.style.display = visible ? '' : 'none';
            if (visible) anyMatch = true;
        });

        if (!anyMatch && !document.getElementById('noResult')) {
            const tr = document.createElement('tr');
            tr.id = 'noResult';
            tr.innerHTML = `<td colspan="9">No results found</td>`;
            tableBody.appendChild(tr);
        } else if (anyMatch) {
            const existing = document.getElementById('noResult');
            if (existing) existing.remove();
        }

        updateEntryCount();
    });

    function updateEntryCount() {
        const visible = Array.from(tableBody.rows).filter(row => row.style.display !== 'none' && row.id !== 'noResult');
        countDisplay.textContent = `Total Entries: ${visible.length}`;
    }

    loadStates();
});