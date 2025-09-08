import React, { useEffect, useRef, useState } from 'react'
import { useParams } from 'react-router-dom';

function UpdateMember() {
  const { memberID } = useParams();
  const nameRef = useRef(null);
  const emailRef = useRef(null);
  const addressRef = useRef(null);
  const phoneNumberRef = useRef(null);
  const [gender, setGender] = useState('');

  useEffect(() => {
    const fetchMember = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/members/viewMembers", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ memberID })
        });

        if (!res.ok) throw new Error(`Error: ${res.text}`);
        
        const data = await res.json();

        if (data.length === 0) {
          alert("member not found");
        } else {
          const member = data[0];
          nameRef.current.value = member.name;
          emailRef.current.value = member.email;
          phoneNumberRef.current.value = member.phoneNumber;
          addressRef.current.value = member.address;
          setGender(member.gender);
        }
      } catch (err) {
        alert("Error: " + err.message);
      }
    };

    fetchMember();
  }, [memberID]);

  useEffect(() => {
    if (gender) {
      const radioButton = document.querySelector(`input[name="gender"][value="${gender}"]`);
      if (radioButton) radioButton.checked = true;
    }
  }, [gender]);

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
    const emailRegEx = /^[a-zA-Z0-9.]+@[a-zA-Z]+\.[a-zA-Z.]{2,}$/;
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
    const selectedGender = document.querySelector('input[name="gender"]:checked')?.value || "";
    const address = addressRef.current.value;
    const phoneNumber = phoneNumberRef.current.value;
    if (!validate(name, email, phoneNumber, selectedGender, address)) {
      return;
    }
    try {
      const res = await fetch("http://localhost:8080/api/members/updateMember", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ memberID, name, email, phoneNumber, gender: selectedGender, address })
      });

      const statusCode = res.status;
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }


      if (res.ok) {
        alert("Member updated successfully!");
        handleBack();
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  const handleBack = () => {
    window.location.href = '/viewMembers';
  };

  return (
    <div className="form-container">
      <h2>Update Member</h2>
      <form onSubmit={handleSubmit}>
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
        <div className="button-row">
          <input type="submit" value="Update Member" />
          <button type="button" className="back-button" onClick={handleBack}>Go Back</button>
        </div>
      </form>
    </div>
  )
}

export default UpdateMember
