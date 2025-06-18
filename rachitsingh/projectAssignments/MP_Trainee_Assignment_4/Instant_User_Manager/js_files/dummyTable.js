// table.js

function addTableRow(entry) {
  const tableBody = document.querySelector("#dataTable tbody");

  const row = document.createElement("tr");
  row.classList.add("fade-in");

  row.innerHTML = `
      <td>${entry.name}</td>
      <td>${entry.age}</td>
      <td>${entry.email}</td>
      <td>${entry.phone}</td>
      <td>${entry.branch}</td>
      <td>${entry.languages}</td>
      <td>${entry.state}</td>
      <td>${entry.city}</td>
      <td><button class="delete-btn">Delete</button></td>
    `;

  // Append to table
  tableBody.appendChild(row);

  // Attach delete event
  row.querySelector(".delete-btn").addEventListener("click", function () {
    const confirmDelete = confirm(
      "Are you sure you want to delete this entry?"
    );
    if (confirmDelete) {
      row.classList.add("fade-out");
      setTimeout(() => {
        row.remove();
        updateEntryCount();
        showOrHideNoResults();
      }, 300);
    }
  });

  updateEntryCount();
  showOrHideNoResults();
}

function filterTable(query) {
  const rows = document.querySelectorAll("#dataTable tbody tr");
  let visibleCount = 0;

  rows.forEach((row) => {
    const cells = Array.from(row.children);
    const match = cells.some((cell) =>
      cell.textContent.toLowerCase().includes(query)
    );

    if (match) {
      row.style.display = "";
      visibleCount++;
    } else {
      row.style.display = "none";
    }
  });

  document.getElementById("entryCount").textContent = `${visibleCount} entr${
    visibleCount === 1 ? "y" : "ies"
  } shown`;
  showOrHideNoResults();
}

function updateEntryCount() {
  const visibleRows = Array.from(
    document.querySelectorAll("#dataTable tbody tr")
  ).filter((row) => row.style.display !== "none");
  document.getElementById("entryCount").textContent = `${
    visibleRows.length
  } entr${visibleRows.length === 1 ? "y" : "ies"} shown`;
}

function showOrHideNoResults() {
  const visibleRows = Array.from(
    document.querySelectorAll("#dataTable tbody tr")
  ).filter((row) => row.style.display !== "none");
  document
    .getElementById("noResults")
    .classList.toggle("hidden", visibleRows.length !== 0);
}
