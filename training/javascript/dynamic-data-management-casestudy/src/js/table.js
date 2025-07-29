import {setEditingIndex,fetchCities ,states, cities , updateFormButtons} from "./form.js";
import { showSuccessMessage } from "./utils.js";

let tableData = [];


function addEntry(entry) {
    tableData.push(entry);
    renderTable();
    updateEntryCount();
}

function renderTable(data = tableData) {
    const tableBody = document.querySelector('#data-table tbody');
    tableBody.innerHTML = '';
    console.log("Trying to render table");
    
    if (data.length === 0) {
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.colSpan = 10;
        cell.textContent = 'No results found';
        cell.style.textAlign = 'center';
        row.appendChild(cell);
        tableBody.appendChild(row);
        return;
    }

    data.forEach((entry, index) => {
        const row = document.createElement('tr');
        row.style.animation = 'fadeIn 0.3s ease-in';

        const originalIndex = tableData.indexOf(entry);
        
        Object.values(entry).forEach(value => {
            const cell = document.createElement('td');
            cell.textContent = value;
            row.appendChild(cell);
        });

        const actionsCell = document.createElement('td');
        
        const updateButton = document.createElement('button');
        updateButton.textContent = 'Update';
        updateButton.classList.add('update-btn');
        updateButton.style.cssText = `
            background-color: #f39c12;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
            margin-right: 5px;
            font-size: 12px;
        `;
        updateButton.onclick = () => editEntry(originalIndex);
        
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('delete-btn');
        deleteButton.style.cssText = `
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
            font-size: 12px;
        `;
        deleteButton.onclick = () => deleteEntry(originalIndex);
        
        actionsCell.appendChild(updateButton);
        actionsCell.appendChild(deleteButton);
        row.appendChild(actionsCell);

        tableBody.appendChild(row);
    });
}



function editEntry(index) {
    setEditingIndex(index);
    const entry = tableData[index];
    
    //taknig all the data of that row and populating into the form
    document.getElementById('name').value = entry.name;
    document.getElementById('age').value = entry.age;
    document.getElementById('email').value = entry.email;
    document.getElementById('phone').value = entry.phone;
    
    const branchRadio = document.querySelector(`input[name="branch"][value="${entry.branch}"]`);
    if (branchRadio) {
        branchRadio.checked = true;
    }
    
    const languageCheckboxes = document.querySelectorAll('input[name="languages"]');
    languageCheckboxes.forEach(checkbox => {
        checkbox.checked = entry.languages.includes(checkbox.value);
    });
    
    const stateSelect = document.getElementById('state');
    const matchingState = states.find(state => state.name === entry.state);
    if (matchingState) {
        stateSelect.value = matchingState.iso2 || matchingState.name;
        fetchCities(stateSelect.value).then(() => {
            document.getElementById('city').value = entry.city;
        });
    }
    
    updateFormButtons(true);
    
    showSuccessMessage('Entry loaded for editing');
}

function updateEntryCount(count = tableData.length) {
    const countElement = document.getElementById('entry-count');
    if (countElement) {
        countElement.textContent = `Total entries: ${count}`;
    }
}


function deleteEntry(index) {
    if (confirm("Are you sure you want to delete this entry?")) {
        tableData.splice(index, 1); 
        updateEntryCount();
        renderTable();
    }
}




export {addEntry,tableData ,renderTable , updateEntryCount}