import React, { useEffect, useState } from "react";
import State from "./StateCities";
import { useLocation, useParams } from "react-router-dom";

function Register() {
    const location = useLocation();
    const {id} = useParams();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [age, setAge] = useState("");
    const [branch, setBranch] = useState("");
    const [languages, setLanguages] = useState([]);
    const [selectedState, setSelectedState] = useState("");
    const [selectedCity, setSelectedCity] = useState("");
    const [nameError, setNameError] = useState("");
    const [phoneError, setPhoneError] = useState("");

    useEffect(() => {
        const data = localStorage.getItem(id);
        if (data) {
            const parsedData = JSON.parse(data);
            setName(parsedData.name);
            setEmail(parsedData.email);
            setPhone(parsedData.phone);
            setAge(parsedData.age);
            setBranch(parsedData.branch);
            setLanguages(parsedData.languages || []);
            setSelectedState(parsedData.state);
            setSelectedCity(parsedData.city);
            setNameError(parsedData.name.length < 4 ? "Name must be at least 4 characters" : "");
        }
    }, [id]);

    // useEffect(()=>{
    //     fetch("http://192.168.0.73:32114/partner/get-states?countryCode=IN")
    //     .then(res => res.json())
    //     .then(d =>{
    //         let l = JSON.parse(d);
    //         console.log(l);
    // })
    // },[]

    // )

    const handleName = (e) => {
        const value = e.target.value;
        if (value.length < 4) {
            setNameError("Name must be at least 4 characters");
        } else {
            setName(value);
            setNameError("");
        }
    };

    const handlePhone = (e) => {
        const value = e.target.value;
        if (!/^\d{10}$/.test(value)) {
            setPhoneError("Phone must be exactly 10 digits");
        } else {
            setPhone(value);
            setPhoneError("");
        }
    };

    const handleLanguageChange = (e) => {
        const { value, checked } = e.target;
        setLanguages((prev) =>
            checked ? [...prev, value] : prev.filter((lang) => lang !== value)
        );
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!name || !email || !phone || !age || !branch || languages.length === 0 || !selectedState) {
            alert("Please fill all required fields");
            return;
        }

        const formData = {
            name,
            email,
            phone,
            age,
            branch,
            languages,
            state: selectedState,
            city: selectedCity,
        };

        const uid = id ? id : localStorage.length + 1;
        localStorage.setItem(uid.toString(), JSON.stringify(formData));
        alert("Form Submitted");
        setName("");
        setEmail("");
        setPhone("");
        setAge("");
        setBranch("");
        setLanguages([]);
        setSelectedState("");
        setSelectedCity("");

        
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label htmlFor="name" className="form"><p>Name<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="name" type="text" value={name} name="name" required onChange={handleName} />
                {nameError && <p className="error">{nameError}</p>}

                <label htmlFor="email" className="form"><p>Email<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="email" name="email" value={email} type="email" required onChange={(e) => setEmail(e.target.value)} />

                <label htmlFor="phone" className="form"><p>Phone No.<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="phone" name="phone" type="text" value={phone} required onChange={handlePhone} />
                {phoneError && <p className="error">{phoneError}</p>}

                <label htmlFor="age" className="form"><p>Age<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="age" name="age" type="number" value={age} required onChange={(e) => setAge(e.target.value)} />

                <label htmlFor="branch" className="form"><p>Branch<sup style={{ color: "red" }}>*</sup></p></label>
                <div>
                    {["CSE", "ECE", "EEE", "IT"].map((b) => (
                        <label key={b}>
                            <input
                                type="radio"
                                name="branch"
                                value={b}
                                checked={branch === b}
                                onChange={(e) => setBranch(e.target.value)}
                            />{" "}
                            {b}
                        </label>
                    ))}
                </div>

                <label htmlFor="lang" className="form"><p>Languages<sup style={{ color: "red" }}>*</sup></p></label>
                <div>
                    {["TELUGU", "ENGLISH", "HINDI"].map((lang) => (
                        <label key={lang}>
                            <input
                                type="checkbox"
                                name="lang"
                                value={lang}
                                checked={languages.includes(lang)}
                                onChange={handleLanguageChange}
                            />{" "}
                            {lang}
                        </label>
                    ))}
                </div>

                <State
                    selectedCity={selectedCity}
                    selectedStatePseudo={selectedState}
                    setSelectedStatePseudo={setSelectedState}
                    setSelectedCity={setSelectedCity}
                />

                <>

                </>



                <br />
                <button type="submit" className="submit-buttons">Submit</button>
            </form>
        </div>
    );
}

export default Register;
