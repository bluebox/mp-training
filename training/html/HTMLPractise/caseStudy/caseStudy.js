let userData = [];
let form = document.getElementById("userData");
let tableBody = document.getElementById("data-entry");

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const formdata = new FormData(this);
  let formuser = {};

  // Get regular input values
  for (let [key, value] of formdata.entries()) {
    // If key already exists (like checkboxes), turn into array
    if (formuser[key]) {
      if (Array.isArray(formuser[key])) {
        formuser[key].push(value);
      } else {
        formuser[key] = [formuser[key], value];
      }
    } else {
      formuser[key] = value;
    }
  }

  // Ensure checkbox values (Language) are stringified for display
  if (Array.isArray(formuser.Language)) {
    formuser.Language = formuser.Language.join(", ");
  }

  userData.push(formuser);

  // Create and append row
  let row = document.createElement("tr");
  row.innerHTML = `
    <td>${formuser.Name || ""}</td>
    <td>${formuser.Age || ""}</td>
    <td>${formuser.Email || ""}</td>
    <td>${formuser.Branch || ""}</td>
    <td>${formuser.Language || ""}</td>
    <td>${formuser.State || ""}</td>
    <td>${formuser.City || ""}</td>
  `;

  tableBody.appendChild(row);

  // Reset form after submit
  this.reset();
});
