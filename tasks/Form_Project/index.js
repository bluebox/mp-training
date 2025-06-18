var table_vector = document.getElementById("Database");
var form_vector=document.getElementById("form_schema");

function delete_func(phone_id){
  document.getElementById(phone_id).remove();

}
function edit_func(phone_id){
     var retrieved_vector=document.getElementById(phone_id).querySelectorAll('td');

     var field=document.getElementById("Name");
     field.setAttribute("value",retrieved_vector[0].getAttribute("value"));
     
     var field=document.getElementById("Age");
     field.setAttribute("value",retrieved_vector[1].getAttribute("value"));     
     
     var field=document.getElementById("Email");
     field.setAttribute("value",retrieved_vector[2].getAttribute("value"));     
     
     var field=document.getElementById("Number");
     field.setAttribute("value",retrieved_vector[3].getAttribute("value"));    

    const branchValue = retrieved_vector[4].getAttribute("value");
    document.querySelectorAll("input[name='Branch']").forEach(radio => {
        radio.checked = (radio.value === branchValue);
    });

    const langs = retrieved_vector[5].getAttribute("value").split(",").map(lang => lang.trim());
    document.querySelectorAll("#Lang input[type='checkbox']").forEach(checkbox => {
        checkbox.checked = langs.includes(checkbox.nextSibling.textContent.trim());
    });

    document.getElementById("State").value = retrieved_vector[6].getAttribute("value");
    document.getElementById("City").value = retrieved_vector[7].getAttribute("value");
}
     

function insert_row(record_array){
  if(document.getElementById(record_array[3])==null){
        var row_vector = document.createElement("tr");
        row_vector.setAttribute("id",record_array[3])

        for(var i=0;i<record_array.length;i++){
          var row_item=document.createElement('td');
          row_item.innerText=`${record_array[i]}`;
          row_item.setAttribute("value",record_array[i])
          row_vector.append(row_item);
        }
        var edit=document.createElement('button');
        edit.innerHTML="Edit";
        edit.setAttribute("id",record_array[3])
        // edit.addEventListener("click",`edit_func`)
        // edit.myvar=edit.getAttribute("id");
        edit.setAttribute("onclick",`edit_func('${edit.getAttribute("id")}')`);
        row_vector.append(edit)
        var dele=document.createElement('button');
        dele.innerHTML="Delete";
        dele.setAttribute("id",record_array[3])
        dele.setAttribute("onclick",`delete_func('${dele.getAttribute("id")}')`);
        row_vector.append(dele)
        row_vector.style.textAlign="center";
        table_vector.append(row_vector);}
  else{
    var data_vals=document.getElementById(record_array[3]).querySelectorAll('td');
    for(var i=0;i<8;i++){
      data_vals[i].innerText=`${record_array[i]}`
      data_vals[i].setAttribute("value",record_array[i])
    }

  }

}

function insert_form(event){
  event.preventDefault();
  let record=[];
  record.push(event.target.Name_1.value);
  record.push(event.target.Age.value);
  record.push(event.target.Email.value);
  record.push(event.target.mobile.value);
  record.push(event.target.Branch.value);


  const selectedLangs = [];
  const langCheckboxes = event.target.querySelectorAll('#Lang input[type="checkbox"]');
  langCheckboxes.forEach(cb => {
    if (cb.checked) {
      selectedLangs.push(cb.nextSibling.textContent.trim()); 
    }
  });


  record.push(selectedLangs.join(", ")); 
  record.push(event.target.State.value)
  record.push(event.target.City.value)
  insert_row(record)
  // event.target.reset();  

}
var form_data=document.getElementById("form_schema");
form_data.addEventListener("submit",insert_form)




var searchObj=document.getElementById("search").querySelectorAll('input');

searchObj.forEach(
  (element)=>{
    element.addEventListener("input",filter_rows);

  }
);
var changes={"N_S":"","A_S":"","E_S":"","P_S":"","B_S":"","L_S":"","S_S":"","C_S":""};




function filter_rows(event) {
    changes[event.target.name] = event.target.value.trim().toLowerCase();

    const rows = document.querySelectorAll("#Database tr");
    
    for (let i = 2; i < rows.length; i++) {
        const cells = rows[i].querySelectorAll("td");
        let visible = true;

        if (changes["N_S"] && !cells[0].innerText.toLowerCase().includes(changes["N_S"])) visible = false;
        if (changes["A_S"] && !cells[1].innerText.toLowerCase().includes(changes["A_S"])) visible = false;
        if (changes["E_S"] && !cells[2].innerText.toLowerCase().includes(changes["E_S"])) visible = false;
        if (changes["P_S"] && !cells[3].innerText.toLowerCase().includes(changes["P_S"])) visible = false;
        if (changes["B_S"] && !cells[4].innerText.toLowerCase().includes(changes["B_S"])) visible = false;
        if (changes["L_S"] && !cells[5].innerText.toLowerCase().includes(changes["L_S"])) visible = false;
        if (changes["S_S"] && !cells[6].innerText.toLowerCase().includes(changes["S_S"])) visible = false;
        if (changes["C_S"] && !cells[7].innerText.toLowerCase().includes(changes["C_S"])) visible = false;

        rows[i].style.display = visible ? "" : "none";
    }
}

window.onload = () => {
  const stateSelect = document.getElementById("State");
  const citySelect = document.getElementById("City");
  let states = {};

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
};



