import { tableData, renderTable, updateEntryCount } from "./table.js";


var columnFilters = {
    name: '',
    age: '',
    email: '',
    phone: '',
    branch: '',
    languages: '',
    state: '',
    city: ''
};


function setupSearch() {
    const columnSearches = document.querySelectorAll('.column-search');
    columnSearches.forEach(searchInput => {
        searchInput.addEventListener('input', (e) => {
            const column = e.target.dataset.column;
            const value = e.target.value.toLowerCase().trim();
            
            if (column && columnFilters.hasOwnProperty(column)) {
                columnFilters[column] = value;
                applyAllFilters();
            }
        });
    });

    const clearButton = document.getElementById('clear-filters');
    if (clearButton) {
        clearButton.addEventListener('click', clearAllFilters);
    }
}


function clearAllFilters() {
    Object.keys(columnFilters).forEach(key => {
        columnFilters[key] = '';
    });
    const columnSearches = document.querySelectorAll('.column-search');
    columnSearches.forEach(input => {
        input.value = '';
    });
    renderTable(tableData);
    updateEntryCount(tableData.length);
}

function applyAllFilters() {
    const filteredData = applyColumnFiltersToData(tableData);
    renderTable(filteredData);
    updateEntryCount(filteredData.length);
}


function applyColumnFiltersToData(data) {

    return data.filter(entry => {
        return Object.keys(columnFilters).every(column => {
            const filterValue = columnFilters[column];
            if (!filterValue) return true; 
            
            if (!entry.hasOwnProperty(column)) {
                console.warn(`Entry missing column: ${column}`);
                return false;
            }
            
            const entryValue = entry[column].toString().toLowerCase();
            return entryValue.includes(filterValue);
        });
    });
}

document.addEventListener('DOMContentLoaded' , function() {
    setupSearch();
});
