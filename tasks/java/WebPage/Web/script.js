
const form = document.getElementById("studentForm");
const stateSelect = document.getElementById("state");
const citySelect = document.getElementById("city");
const tableBody = document.getElementById("tableBody");

fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
.then(response => response.json())
.then(data => {
    if (data.responseStatus === "SUCCESS" && data.response) {

        states = JSON.parse(data.response);
        Object.keys(states).forEach(stateName => {
            const opt = document.createElement("option");
            opt.value = stateName;
            opt.textContent = stateName;
            stateSelect.appendChild(opt);
        });
    } else {
        console.error("Failed to get states", data);
    }
})
.catch(error => {
    console.error("Error fetching states:", error);
});

stateSelect.addEventListener("change", () => {
  const selectedStateCode = states[stateSelect.value];
  citySelect.innerHTML = '<option value="">Select City</option>';

  if (!selectedStateCode) return;

  fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${selectedStateCode}`)
      .then(response => response.json())
      .then(data => {
          if (data.responseStatus === "SUCCESS" && data.response) {
              const cities = JSON.parse(data.response);

              Object.keys(cities).forEach(cityName => {
                  const opt = document.createElement("option");
                  opt.value = cityName;
                  opt.textContent = cityName;
                  citySelect.appendChild(opt);
              });
          } else {
              console.error("Failed to get cities", data);
          }
      })
      .catch(error => {
          console.error("Error fetching cities:", error);
      });
});

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const email = document.getElementById("email").value.trim();
  const mobile = document.getElementById("mobile").value.trim();
  const state = stateSelect.value;
  const city = citySelect.value;
  const branch = document.querySelector('input[name="branch"]:checked');
  
  const languages = document.querySelectorAll('input[name="languagesKnown"]:checked'
  );


  if (!name || !age || !email || !mobile || !branch || languages.length === 0 || !state || !city
  ) {
    alert("Please fill all fields correctly.");
    return;
  }

  if (!mobile.match(/^[0-9]{10}$/)) {
    alert("Phone number must be 10 digits.");
    return;
  }

  if (!(/^[0-9a-zA-Z_\.%+-]+@[0-9a-zA-Z]+\.[a-zA-z]+[[\.a-zA-Z]+]*$/).test(email)) {
    alert("Invalid email format.");
    return;
  }

  languagesKnown =""
  for(let i=0;i<languages.length;i++){
    languagesKnown += languages[i].value;
    if(i < languages.length-1){
      languagesKnown += ", ";
    }
  }

  const studentObj = {
    name,
    age,
    email,
    mobile,
    branch: branch.value,
    languages: languagesKnown,
    state,
    city,
  };

  addToTable(studentObj);
  form.reset();
  citySelect.innerHTML = '<option value="">Select City</option>';
});

function addToTable(data) {
  const row = document.createElement("tr");
  row.innerHTML = `
      <td>${data.name}</td>
      <td>${data.age}</td>
      <td>${data.email}</td>
      <td>${data.mobile}</td>
      <td>${data.branch}</td>
      <td>${data.languages}</td>
      <td>${data.state}</td>
      <td>${data.city}</td>
      <td>
        <button onclick="editRow(this)">Edit</button>
        <button onclick="deleteRow(this)">Delete</button>
      </td>
    `;
  tableBody.appendChild(row);
}

function deleteRow(btn) {
  if (confirm("Are you sure you want to delete this record?")) {
    btn.closest("tr").remove();
  }
}

function editRow(btn) {
  const row = btn.closest("tr");
  const cells = row.querySelectorAll("td");

  document.getElementById("name").value = cells[0].textContent;
  document.getElementById("age").value = cells[1].textContent;
  document.getElementById("email").value = cells[2].textContent;
  document.getElementById("mobile").value = cells[3].textContent;

  const branchVal = cells[4].textContent;
  document.querySelectorAll('input[name="branch"]').forEach((r) => {
    r.checked = r.value === branchVal;
  });

  const langList = cells[5].textContent.split(",").map((l) => l.trim());
  document.querySelectorAll('input[name="languagesKnown"]').forEach((c) => {
    c.checked = langList.includes(c.value);
  });

  const stateVal = cells[6].textContent;
  const cityVal = cells[7].textContent;

  stateSelect.value = stateVal;
  stateSelect.dispatchEvent(new Event("change"));
  setTimeout(() => {
    citySelect.value = cityVal;
  }, 100);

  row.remove(); 
}

function searchTable(colIndex, input) {
  const filter = input.value.toLowerCase();
  const rows = document.querySelectorAll("#tableBody tr");

  rows.forEach((row) => {
    const cell = row.children[colIndex];
    if (cell) {
      const text = cell.textContent.toLowerCase();
      row.style.display = text.includes(filter) ? "" : "none";
    }
  });
}
