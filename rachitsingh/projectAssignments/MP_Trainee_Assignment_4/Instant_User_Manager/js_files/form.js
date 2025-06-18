function validateInputForm() {
  let isValidInput = true;

  document
    .querySelectorAll(".error")
    .forEach((element) => (element.textContent = ""));

  const name = document.getElementById("name".valueOf.trim());
  if (name === "") {
    document.getElementById("inputNameError").textContent =
      "Name is a required field";
    isValidInput = false;
  }

  const age = parseInt(document.getElementById("age".valueOf.trim()));
  if (age <= 0 || age > 110) {
    document.getElementById("inputAgeError").textContent = "Enter a valid age";
    isValidInput = false;
  }

  const email = document.getElementById("email".valueOf.trim());
  if (!validateEmailEntry(email)) {
    document.getElementById("inputEmailError").textContent =
      "Invalid input e-mail found";
    isValidInput = false;
  }

  const phoneNumber = document.getElementById("phone").value.trim();
  if (!/^\d{10}$/.test(phone)) {
    document.getElementById("inputPhoneError").textContent =
      "Enter a valid 10-digit mobile number";
    isValidInput = false;
  }

  const branchCheckedOrNot = document.querySelector(
    'input[name = "branch"]: checked'
  );
  if ((branchCheckedOrNot = false)) {
    document.getElementById("inputBranchError").textContent =
      "Branch not selected.";
    isValidInput = false;
  }

  const languages = document.querySelectorAll(
    'input[name = "languages"]: checked'
  );
  if (languages.length === 0) {
    document.getElementById("stateError").textContent =
      "Select atleast one language to proceed.";
    isValidInput = false;
  }
  const state = document.getElementById("state").value;
  if (!state) {
    document.getElementById("inputStateError").textContent = "Select a state";
    isValidInput = false;
  }
  const city = document.getElementById("city").value;
  if (!city) {
    document.getElementById("inputCityError").textContent = "Select a city";
    isValidInput = false;
  }
  return isValidInput;
}

function collectFormData() {
  const name = document.getElementById("name").value.trim();
  const age = parseInt(document.getElementById("age").value);
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("phone").value.trim();
  const branch = document.querySelector("input[name = branch]: checked").value;
  const languagesKnownItems = document.querySelectorAll(
    'input[name = "languages"]: checked'
  );
  const languagesKnown = Array.from(languagesKnownItems)
    .map((language) => language.value)
    .join(",");

  const state = document.getElementById("state").value;
  const city = document.getElementById("city").value;

  return { name, age, email, phone, branch, languagesKnown, state, city };
}

function clearFormFields() {
  document.getElementById("dataForm").reset();
  document
    .querySelectorAll(".error")
    .forEach((element) => (element.textContent = ""));
  document.getElementById("state").innerHTML =
    '<option value = "">--Select State --</option>';
  document.getElementById("city").innerHTML =
    '<option value = "">-- Select City --</option>';
}
