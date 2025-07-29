const form = document.getElementById('dataForm');
const addBtn = document.getElementById('addBtn');
const tableBody = document.querySelector('#dataTable tbody');
const searchInput = document.getElementById('searchInput');
const noResults = document.getElementById('noResults');
const entryCount = document.getElementById('entryCount');
const stateSelect = document.getElementById('state');
const citySelect = document.getElementById('city');
let dataList = [];

function fetchStates(){
         const res= fetch('http://192.168.0.73:32114/partner/get-states?countryCode=IN')
                    .then(res=>res.json())
                    .then(states=>{
                      console.log("states came " + states.response)
                      
                      var rohanstates= states.response.slice(1,states.response.length-1).split(",");
                      stateSelect.innerHTML='  <option value="">Select State</option>';


                      rohanstates.forEach(state => {
                        console.log(state);
                        state=state.split(":");
                        const option = document.createElement('option');
                       
                        option.value=""+state[1];
                        option.textContent=""+state[0];
                        stateSelect.appendChild(option);
                      });

                    }

                    )
                    .catch(error=>console.log(error+"error while loading states"));



}
    fetchStates();
          

       stateSelect.addEventListener('change',() => {
           const selectedstates= stateSelect.value;
           citySelect.innerHTML='<option value="">Select City</option>';
           if(selectedstates){
            fetchCity(selectedstates);

           }

       });


    function fetchCity(statecode){
          statecode=statecode.slice(1,-1);
          statecode=statecode.slice(0,statecode.length)
          

        const city=fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${statecode}`)
                  .then(city=>city.json())
                    .then(city=>{
                      console.log("city came " + city.response)
                      
                      var rohancity= city.response.slice(1,city.response.length-1).split(",");
                      citySelect.innerHTML=   '<option value="">Select City</option>';


                      rohancity.forEach(city => {
                        console.log(city);
                        city=city.split(":");
                        const option = document.createElement('option');
                       
                        option.value=""+city[1];
                        option.textContent=""+city[0];
                        citySelect.appendChild(option);
                      });

                    }

                    )
                    .catch(error=>console.log(error+"error while loading city"));
        



    }


   
// Enable Add button manually based on field checks
form.addEventListener('input', () => {
  const name = document.getElementById('name').value.trim();
  const age = document.getElementById('age').value;
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const branch = form.querySelector('input[name="branch"]:checked');
  const languages = form.querySelectorAll('input[name="languages"]:checked');

  const isValid =
    name !== "" &&
    age !== "" &&
    email !== "" &&
    /^\d{10}$/.test(phone) &&
    branch &&
    languages.length > 0;

  addBtn.disabled = !isValid;
});


// Handle form submission
form.addEventListener('submit', (e) => {
  e.preventDefault();
  const name = document.getElementById('name').value.trim();
  const age = +document.getElementById('age').value;
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const branch = form.querySelector('input[name="branch"]:checked')?.value;
  const languageNodes = form.querySelectorAll('input[name="languages"]:checked');
  const languages = Array.from(languageNodes).map(l => l.value);
  
  const state = stateSelect.selectedIndex > 0 ? stateSelect.options[stateSelect.selectedIndex].text : "Not Provided";
  const city = citySelect.selectedIndex > 0 ? citySelect.options[citySelect.selectedIndex].text : "Not Provided";

  const newRow = {
    name, age, email, phone, branch, languages: languages.join(", "), state, city
  };

  dataList.push(newRow);
  updateTable();
  form.reset();
  citySelect.innerHTML = '<option value="">Select City</option>';
  addBtn.disabled = true;
});

// Render table
function updateTable() {
  tableBody.innerHTML = "";
  const searchVal = searchInput.value.toLowerCase();
  let visibleRows = 0;

  dataList.forEach((data, index) => {
    const row = document.createElement('tr');
    
    // Convert all values to string and lowercase for matching
    const values = Object.values(data).map(val => String(val).toLowerCase());

    // Match with any column
    if (values.some(val => val.includes(searchVal))) {
      row.classList.add('fade-in');
      row.innerHTML = `
        <td>${data.name}</td>
        <td>${data.age}</td>
        <td>${data.email}</td>
        <td>${data.phone}</td>
        <td>${data.branch}</td>
        <td>${data.languages}</td>
        <td>${data.state}</td>
        <td>${data.city}</td>
        <td><button onclick="deleteRow(${index})">Delete</button></td>
      `;
      tableBody.appendChild(row);
      visibleRows++;
    }
  });
  noResults.style.display = visibleRows === 0 ? 'block' : 'none';
  entryCount.textContent = `Total entries shown: ${visibleRows}`;
}

// Delete row
function deleteRow(index) {
  if (confirm("Are you sure you want to delete this entry?")) {
    dataList.splice(index, 1);
    updateTable();
  }
}

// Search input handling
searchInput.addEventListener('input', updateTable);