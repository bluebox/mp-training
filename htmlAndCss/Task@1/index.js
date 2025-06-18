const stateSelect = document.getElementById("state");
const citySelect = document.getElementById("city");

function populateStates() {
  stateSelect.innerHTML = `<option value="" disabled selected>Select State</option>`;

  Object.keys(states).forEach(state => {
    const option = document.createElement("option");
    option.value = state;
    option.textContent = state;
    stateSelect.appendChild(option);
  });
}

function populateCities(state) {
  citySelect.innerHTML = `<option value="" disabled selected>Select City</option>`;

  if (states[state]) {
    states[state].forEach(city => {
      const option = document.createElement("option");
      option.value = city;
      option.textContent = city;
      citySelect.appendChild(option);
    });
  }
}

  populateStates();
  if(document.querySelector("#state").value!="select state")
  document.getElementById("state").addEventListener("change", (e) => {
    populateCities(e.target.value);
  });

document.getElementById("userForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("pno").value.trim();
  const branch = document.querySelector("input[name='branch']:checked").value;
  const state = stateSelect.options[stateSelect.selectedIndex].text;
  const city = citySelect.value;

  let Languages = [];
  if (document.getElementById("telugu").checked) {
    Languages.push(document.getElementById("telugu").value);
  }
  if (document.getElementById("hindi").checked) {
    Languages.push(document.getElementById("hindi").value);
  }
  if (document.getElementById("english").checked) {
    Languages.push(document.getElementById("english").value);
  }

  const languages = Languages.join(", ");
  const tbody = document.querySelector("#dataTable tbody");
  const row = tbody.insertRow();

  [name, age, email, phone, languages, branch, state, city].forEach(data => {
    const cell = row.insertCell();
    cell.textContent = data;
  });

  const actionCell = row.insertCell();
  actionCell.innerHTML = `
     <div class="del">
         <button class="edit-btn">Edit</button>
         <button class="delete-btn">Delete</button>
     </div>
  `;
  actionCell.querySelector(".delete-btn").addEventListener("click", function () {
    if (confirm("Are you sure you want to delete this record?")) {
      row.remove();
    }
  });

  actionCell.querySelector(".edit-btn").addEventListener("click", function () {
    const cells = row.querySelectorAll("td");

    document.getElementById("name").value = cells[0].textContent;
    document.getElementById("age").value = cells[1].textContent;
    document.getElementById("email").value = cells[2].textContent;
    document.getElementById("pno").value = cells[3].textContent;

    const langs = cells[4].textContent.split(", ");
    document.getElementById("telugu").checked = langs.includes("Telugu");
    document.getElementById("hindi").checked = langs.includes("Hindi");
    document.getElementById("english").checked = langs.includes("English");

    const branchVal = cells[5].textContent;
    document.querySelector(`input[name='branch'][value='${branchVal}']`).checked = true;
    
    row.remove(); 
  });

  this.reset();
  citySelect.innerHTML = '<option value="" disabled selected>Loading...</option>';
});

const isFind=(s,t)=>{
   let a=s.length;
   let b=t.length;
   for(let i=0;i<Math.min(a,b);i++){
      if(s[i]!=t[i])return false;
   }
   return true;
}
document.querySelectorAll('.filter').forEach(input => {
  input.addEventListener('input', function () {
    const col = this.getAttribute('data-col');
    const filter = this.value.toLowerCase();
    const rows = document.querySelectorAll('#dataTable tbody tr');

    rows.forEach(row => {
      const cell = row.cells[col];
      if (cell) {
        row.style.display = isFind(cell.textContent.toLowerCase(),filter.toLowerCase()) ? '' : 'none';
      }
    });
  });
});