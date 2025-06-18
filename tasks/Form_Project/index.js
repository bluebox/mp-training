var table_vector = document.getElementById("Database");
var form_vector=document.getElementById("form_schema");

function insert_row(record_array){
        var row_vector = document.createElement("tr");

        for(var i=0;i<record_array.length;i++){
          var row_item=document.createElement('td');
          row_item.innerText=`${record_array[i]}`;
          row_vector.append(row_item);
        }
        var edit=document.createElement('button');
        edit.innerHTML="Edit";
        row_vector.append(edit)
        var dele=document.createElement('button');
        dele.innerHTML="Delete";
        row_vector.append(dele)
        row_vector.style.textAlign="center";
        table_vector.append(row_vector);
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
  record.push(event.target.City.value)
  record.push(event.target.State.value)
  insert_row(record)
  event.target.reset();


}
var form_data=document.getElementById("form_schema");
form_data.addEventListener("submit",insert_form)


