document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('dataForm');
  const stateSelect = document.getElementById('stateSelect');
  const citySelect = document.getElementById('citySelect');
  const addBtn = document.getElementById('addBtn');
  const tableBody = document.querySelector('#dataTable tbody');
  const searchBox = document.getElementById('searchBox');
  const entryCount = document.getElementById('entryCount');
  const clearAllBtn = document.getElementById('clearAllBtn');

  
  function fetchStates() {
    fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
      .then(res => res.json())
      .then(data => {
        const cleaned = data.response.slice(1, -1);
        const entries = cleaned.split(',');

        entries.forEach(pair => {
          const [name, code] = pair.split(':');
          const stateName = name.replace(/"/g, '').trim();
          const stateCode = code.replace(/"/g, '').trim();

          const option = document.createElement('option');
          option.value = stateCode;
          option.textContent = stateName;
          stateSelect.appendChild(option);
        });
      })
      .catch(err => {
        console.error('Failed to fetch states:', err);
        alert('Could not load states');
      });
  }

  function fetchCities(stateCode) {
    citySelect.innerHTML = '<option value="">Select City</option>';
    if (!stateCode) return;

    fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`)
      .then(res => res.json())
      .then(data => {
        const cleaned = data.response.slice(1, -1);
        const entries = cleaned.split(',');

        entries.forEach(pair => {
          const [name, code] = pair.split(':');
          const cityName = name.replace(/"/g, '').trim();
          const cityCode = code.replace(/"/g, '').trim();

          const option = document.createElement('option');
          option.value = cityCode;
          option.textContent = cityName;
          citySelect.appendChild(option);
        });

        citySelect.disabled = false;
      })
      .catch(err => {
        console.error('Failed to fetch cities:', err);
        alert('Could not load cities');
      });
  }

  stateSelect.addEventListener('change', () => {
    fetchCities(stateSelect.value);
  });

  form.addEventListener('input', () => {
    addBtn.disabled = !form.checkValidity();
  });

  form.addEventListener('submit', e => {
    e.preventDefault();

    const name = document.getElementById('name').value.trim();
    const age = document.getElementById('age').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const branch = form.querySelector('input[name="branch"]:checked')?.value || '';
    const languages = Array.from(form.querySelectorAll('input[type="checkbox"]:checked')).map(cb => cb.value).join(', ');
    const state = stateSelect.options[stateSelect.selectedIndex].textContent;
    const city = citySelect.options[citySelect.selectedIndex].textContent;

    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${name}</td><td>${age}</td><td>${email}</td><td>${phone}</td>
      <td>${branch}</td><td>${languages}</td><td>${state}</td><td>${city}</td>
      <td><button class="deleteBtn">Delete</button></td>
    `;

    tableBody.appendChild(row);
    form.reset();
    citySelect.disabled = true;
    addBtn.disabled = true;
    updateCount();
  });

  tableBody.addEventListener('click', e => {
    if (e.target.classList.contains('deleteBtn')) {
      e.target.closest('tr').remove();
      updateCount();
    }
  });

  clearAllBtn.addEventListener('click', () => {
    tableBody.innerHTML = '';
    updateCount();
  });

  searchBox.addEventListener('input', () => {
    const value = searchBox.value.toLowerCase();
    Array.from(tableBody.rows).forEach(row => {
      row.style.display = Array.from(row.cells).some(cell =>
        cell.textContent.toLowerCase().includes(value)
      ) ? '' : 'none';
    });
  });

  function updateCount() {
    entryCount.textContent = `Total Entries: ${tableBody.rows.length}`;
  }

  fetchStates();
});
