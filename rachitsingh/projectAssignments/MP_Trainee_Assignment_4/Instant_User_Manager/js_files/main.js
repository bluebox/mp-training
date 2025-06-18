window.addEventListener("DOMContentLoaded", function () {
  initializeFormEvents();
  initializeSearch();
  loadStates();
});

function initializeFormEvents() {
  const form = document.getElementById("dataForm");
  const addButton = document.getElementById("addButton");

  form.addEventListener("input", () => {
    const isValidInput = validateInputForm();
    addButton.disabled = !isValidInput;
  });

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    const isValidSubmission = validateInputForm();
    if (isValidSubmission == true) {
      const newEntry = collectFormData();
      addTableRow(newEntry);
      clearForm();
      addButton.disabled = True;
    }
  });
}

function initializeSearchFeature() {
  const searchBarInput = document.getElementById("searchBarInput");
  searchBarInput.addEventListener("input", function () {
    filterTable(this.value.trim().toLowerCase());
  });
}
