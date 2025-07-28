document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('dataForm');
  const addBtn = document.getElementById('addBtn');
  const stateSelect = document.getElementById('stateSelect');
  const citySelect = document.getElementById('citySelect');
  const tableBody = document.querySelector('#dataTable tbody');
  const clearAllBtn = document.getElementById('clearAllBtn');
  const exportBtn = document.getElementById('exportBtn');
  const searchBox = document.getElementById('searchBox');
  const entryCount = document.getElementById('entryCount');

  let editMode = false;
  let editRow = null;

  function validateForm() {
    const name = document.getElementById('name').value.trim();
    const age = parseInt(document.getElementById('age').value);
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const branch = document.querySelector('input[name="branch"]:checked');
    const languages = document.querySelectorAll('input[type="checkbox"]:checked');
    const state = stateSelect.value;
    const city = citySelect.value;

    const nameValid = /^[A-Za-z ]+$/.test(name);
    const ageValid = age >= 10 && age <= 100;
    const phoneValid = /^\d{10,12}$/.test(phone);
    const emailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

    const isValid = nameValid && ageValid && phoneValid && emailValid && branch && languages.length > 0 && state && city;
    addBtn.disabled = !isValid;
    return isValid;
  }

  form.addEventListener('input', validateForm);

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    const data = {
      name: document.getElementById('name').value,
      age: document.getElementById('age').value,
      email: document.getElementById('email').value,
      phone: document.getElementById('phone').value,
      branch: document.querySelector('input[name="branch"]:checked').value,
      languages: Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(cb => cb.value).join(', '),
      state: stateSelect.options[stateSelect.selectedIndex].text,
      city: citySelect.value
    };

    if (editMode && editRow) {
      updateRow(editRow, data);
      editMode = false;
      editRow = null;
    } else {
      addRow(data);
    }

    form.reset();
    addBtn.disabled = true;
    updateCount();
  });

  function addRow(data) {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${data.name}</td><td>${data.age}</td><td>${data.email}</td><td>${data.phone}</td>
      <td>${data.branch}</td><td>${data.languages}</td><td>${data.state}</td><td>${data.city}</td>
      <td>
        <button class='editBtn'>Edit</button>
        <button class='deleteBtn'>Delete</button>
      </td>
    `;
    tableBody.appendChild(row);
  }

  function updateRow(row, data) {
    row.innerHTML = `
      <td>${data.name}</td><td>${data.age}</td><td>${data.email}</td><td>${data.phone}</td>
      <td>${data.branch}</td><td>${data.languages}</td><td>${data.state}</td><td>${data.city}</td>
      <td>
        <button class='editBtn'>Edit</button>
        <button class='deleteBtn'>Delete</button>
      </td>
    `;
  }

  tableBody.addEventListener('click', (e) => {
    const row = e.target.closest('tr');
    if (e.target.classList.contains('deleteBtn')) {
      if (confirm('Delete this entry?')) {
        row.remove();
        updateCount();
      }
    } else if (e.target.classList.contains('editBtn')) {
      loadDataToForm(row);
      editMode = true;
      editRow = row;
    }
  });

  function loadDataToForm(row) {
    const cells = row.children;
    document.getElementById('name').value = cells[0].innerText;
    document.getElementById('age').value = cells[1].innerText;
    document.getElementById('email').value = cells[2].innerText;
    document.getElementById('phone').value = cells[3].innerText;

    document.querySelectorAll('input[name="branch"]').forEach(rb => {
      rb.checked = rb.value === cells[4].innerText;
    });

    document.querySelectorAll('input[type="checkbox"]').forEach(cb => {
      cb.checked = cells[5].innerText.includes(cb.value);
    });

    [...stateSelect.options].forEach(opt => {
      opt.selected = opt.text === cells[6].innerText;
    });

    citySelect.value = cells[7].innerText;
    addBtn.disabled = false;
  }

  clearAllBtn.addEventListener('click', () => {
    if (confirm('Clear all entries?')) {
      tableBody.innerHTML = '';
      updateCount();
    }
  });

  exportBtn.addEventListener('click', () => {
    const rows = Array.from(tableBody.rows).map(r => Array.from(r.cells).slice(0,8).map(c => c.innerText));
    let csv = 'Name,Age,Email,Phone,Branch,Languages,State,City\n' + rows.map(r => r.join(',')).join('\n');
    const blob = new Blob([csv], { type: 'text/csv' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'entries.csv';
    link.click();
  });

  searchBox.addEventListener('input', () => {
    const term = searchBox.value.toLowerCase();
    Array.from(tableBody.rows).forEach(row => {
      row.style.display = row.innerText.toLowerCase().includes(term) ? '' : 'none';
    });
    updateCount();
  });

  function updateCount() {
    const visible = Array.from(tableBody.rows).filter(r => r.style.display !== 'none');
    entryCount.textContent = `Total Entries: ${visible.length}`;
  }

  // Add initial demo row
  addRow({
    name: 'John Doe',
    age: 22,
    email: 'john@example.com',
    phone: '9876543210',
    branch: 'CSE',
    languages: 'English, Hindi',
    state: 'Karnataka',
    city: 'Bangalore'
  });
  updateCount();
});
