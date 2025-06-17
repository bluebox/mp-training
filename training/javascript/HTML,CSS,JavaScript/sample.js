function validateForm(event){
  event.preventDefault(); 

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const contact = document.getElementById("contact").value.trim();
  const email = document.getElementById("email").value.trim();
  const state = document.getElementById("state").value;
  const city = document.getElementById("city").value;
  const branch=document.getElementById("branch").value;
 


  var langSelected=document.querySelectorAll('input[name="lang"]:checked');
  const selectedLanguages = Array.from(langSelected).map(cb => cb.value);

  if (langSelected.length === 0) {
    alert("Please select at least one language.");
    return;
  }

 
  const tableBody = document.querySelector("#userTable tbody");
  const row = document.createElement("tr");

  row.innerHTML = `
    <td>${name}</td>
    <td>${age}</td>
    <td>${email}</td>
    <td>${contact}</td>
    <td>${branch}</td>
    <td>${selectedLanguages}</td>
    <td>${state}</td>
    <td>${city}</td>
    <td><button onclick="deleteRow(this)">Delete</button></td>
  `;
   tableBody.appendChild(row);
  document.getElementById("userdata").reset();
}

function deleteRow(btn) {
  const row = btn.parentNode.parentNode;
  row.remove();
}

            
           