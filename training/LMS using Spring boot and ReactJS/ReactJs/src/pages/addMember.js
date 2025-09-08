import { useRef } from 'react'

function AddMember() {
  const nameRef = useRef();
  const emailRef = useRef();
  const addressRef = useRef();
  const phoneNumberRef = useRef();
  const validate = (name, email, phoneNumber, gender, address) => {
    if (!name) {
      alert("Member name can't be null")
      return false;
    }
    const nameTrimmed = name.trim();
    if (nameTrimmed.length < 2 || nameTrimmed.length > 100) {
      alert("Member name must be between 2 and 100 characters")
      return false;
    }
    if (!phoneNumber || isNaN(phoneNumber)) {
      alert("Phone number can't be null");
      return false;
    }
    const phoneNum = Number(phoneNumber);
    if (phoneNum < 1000000000 || phoneNum > 9999999999) {
      alert("Phone number must have exactly 10 digits");
      return false;
    }
    if (!gender) {
      alert("Gender can't be null");
      return false;
    }
    if (!address) {
      alert("Address can't be null");
      return false;
    }
    const addressTrimmed = address.trim();
    if (addressTrimmed.length < 2 || addressTrimmed.length > 500) {
      alert("Address must be 2 to 500 characters");
      return false;
    }
    const emailRegEx = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z.]{2,}$/;
    if (!email || !emailRegEx.test(email)) {
      alert("Email should be in valid format");
      return false;
    }
    return true;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const name = nameRef.current.value;
    const email = emailRef.current.value;
    const gender = document.querySelector('input[name="gender"]:checked')?.value || "";
    const address = addressRef.current.value;
    const phoneNumber = phoneNumberRef.current.value;

    if (!validate(name, email, phoneNumber, gender, address)) {
      return;
    }
    try {
      const res = await fetch("http://localhost:8080/api/members/addMember", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, phoneNumber, gender, address })
      });

      // const statusCode = res.status;
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (res.ok) {
        alert("Member added successfully!");
        nameRef.current.value = "";
        emailRef.current.value = "";
        phoneNumberRef.current.value = "";
        addressRef.current.value = "";
        const checkedGender = document.querySelector('input[name="gender"]:checked');
        checkedGender.checked = false;
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const handleBack = () => {
    window.location.href = '/';
  };

  return (
    <div className="form-container">
      <h2>Add Member</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            required
            ref={nameRef}
            onInvalid={e => e.target.setCustomValidity('name is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>

        <div className="form-row">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            required
            ref={emailRef}
            onInvalid={e => e.target.setCustomValidity('email is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>

        <div className="form-row">
          <label htmlFor="phoneNumber">Phone Number:</label>
          <input
            type="number"
            id="phoneNumber"
            name="phoneNumber"
            required
            ref={phoneNumberRef}
            onInvalid={e => e.target.setCustomValidity('phone number is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>

        <div className="form-row">
          <label>Gender:</label>
          <div className="gender-options">
            <label>
              <input
                type="radio"
                name="gender"
                value="MALE"
                required
                onInvalid={e => e.target.setCustomValidity('gender is required')}
                onInput={e => e.target.setCustomValidity('')}
              /> Male
            </label>
            <label>
              <input
                type="radio"
                name="gender"
                value="FEMALE"
                onInvalid={e => e.target.setCustomValidity('gender is required')}
                onInput={e => e.target.setCustomValidity('')}
              /> Female
            </label>
            <label>
              <input
                type="radio"
                name="gender"
                value="OTHER"
                onInvalid={e => e.target.setCustomValidity('gender is required')}
                onInput={e => e.target.setCustomValidity('')}
              /> Other
            </label>
          </div>
        </div>

        <div className="form-row">
          <label htmlFor="address">Address:</label>
          <input
            type="text"
            id="address"
            name="address"
            required
            ref={addressRef}
            onInvalid={e => e.target.setCustomValidity('address is required')}
            onInput={e => e.target.setCustomValidity('')}
          />
        </div>
        <div className="button-row">
          <button type="submit" className="add-button">Add Member</button>
          <button type="button" onClick={handleBack} className="back-button">Back to Main Menu</button>
        </div>
      </form>
    </div>
  )
}

export default AddMember
