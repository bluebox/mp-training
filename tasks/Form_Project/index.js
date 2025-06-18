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
  event.target.reset();  

}
var form_data=document.getElementById("form_schema");
form_data.addEventListener("submit",insert_form)




