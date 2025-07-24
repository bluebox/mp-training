import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";

function Register() {
    const { id } = useParams();
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
    const [states, setStates] = useState({});
    const [selectedStatePseudo, setSelectedStatePseudo] = useState("");
    const [cities, setCities] = useState({});
    const [loading, setLoading] = useState(false);
    // const [stateTrigger, setStateTrigger] = useState(false);
    const [alter,setAlter] = useState("");

    useEffect(() => {
        fetch("http://192.168.0.73:32114/partner/get-states?countryCode=IN")
            .then(res => res.json())
            .then(d => {
                const resp = JSON.parse(d["response"]);
                setStates(resp);
                // setStateTrigger(true);
            })
            .catch(err => {
                console.error("Error fetching states:", err);
                setStates({});
            });
    }, []);

    // useEffect(() => {
    //     setTimeout(()=>{
    //         console.log("delay")
    //     },0);
    //     if (!selectedStatePseudo) return;

    //     setLoading(true);
    //     fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${selectedStatePseudo}`)
    //         .then(res => res.json())
    //         .then(data => {
    //             const resp = JSON.parse(data["response"] || "{}");
    //             setCities(resp || {});
    //         })
    //         .catch(err => {
    //             console.error("Error fetching cities", err);
    //             setCities({});
    //         })
    //         .finally(() => setLoading(false));
    // }, [stateTrigger && selectedStatePseudo]);


    useEffect(() => {
        if (!selectedStatePseudo) return;

        setLoading(true);
        fetch(`http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${selectedStatePseudo}`)
            .then(res => res.json())
            .then(data => {
            const resp = JSON.parse(data["response"] || "{}");
            setCities(resp || {});
            })
            .catch(err => {
            console.error("Error fetching cities", err);
            setCities({});
            })
            .finally(() => {setLoading(false); setAlter(false)});
    }, [alter || selectedStatePseudo]);


    useEffect(() => {
        if (!id || !Object.keys(states).length){
            return;
        }

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
            console.log(parsedData.state)
            console.log(selectedStatePseudo)
            setSelectedStatePseudo(parsedData.state); 
            setSelectedCity(parsedData.city);
            console.log(selectedStatePseudo);
            if (parsedData.name.length < 4) {
                setNameError("Name must be at least 4 characters");
            }

        }
    }, [id, states]); 


    const handleName = (e) => {
        const value = e.target.value;
        setName(value);
        setNameError(value.length < 4 ? "Name must be at least 4 characters" : "");
    };

    const handlePhone = (e) => {
        const value = e.target.value;
        setPhone(value);
        setPhoneError(/^\d{10}$/.test(value) ? "" : "Phone must be exactly 10 digits");
    };

    const handleLanguageChange = (e) => {
        const { value, checked } = e.target;
        setLanguages(prev => checked ? [...prev, value] : prev.filter(lang => lang !== value));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!name || !email || !phone || !age || !branch || languages.length === 0 || !selectedState ) {
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

        const uid = id || localStorage.length + 1;
        localStorage.setItem(uid.toString(), JSON.stringify(formData));
        alert("Form Submitted");
        setName("");
        setEmail("");
        setPhone("");
        setAge("");
        setBranch("");
        setLanguages([]);
        setSelectedState("");
        setSelectedStatePseudo("");
        setSelectedCity("");
        setCities({});
        // setStateTrigger(false);
    };

    const handleStateChange = (e) => {
        const selectedStateValue = e.target.value;
        setSelectedState(selectedStateValue);
        // console.log(selectedStateValue);
        console.log(selectedStatePseudo);
        for (let pseudo in states) {
            if (pseudo === selectedStateValue) {
                setSelectedStatePseudo(pseudo);
                break;
            }
        }
        console.log(selectedStatePseudo);
    };

    const isDisabled = loading || !Object.keys(cities).length;

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label htmlFor="name"><p>Name<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="name" type="text" value={name} required onChange={handleName} />
                {nameError && <p className="error">{nameError}</p>}
                <label htmlFor="email"><p>Email<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="email" type="email" value={email} required onChange={e => setEmail(e.target.value)} />

                <label htmlFor="phone"><p>Phone No.<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="phone" type="text" value={phone} required onChange={handlePhone} />
                {phoneError && <p className="error">{phoneError}</p>}

                <label htmlFor="age"><p>Age<sup style={{ color: "red" }}>*</sup></p></label>
                <input id="age" type="number" value={age} required onChange={e => setAge(e.target.value)} />

                <label><p>Branch<sup style={{ color: "red" }}>*</sup></p></label>
                {["CSE", "ECE", "EEE", "IT"].map(b => (
                    <label key={b}>
                        <input type="radio" name="branch" value={b} checked={branch === b} onChange={e => setBranch(e.target.value)} /> {b}
                    </label>
                ))}

                <label><p>Languages<sup style={{ color: "red" }}>*</sup></p></label>
                {["TELUGU", "ENGLISH", "HINDI"].map(lang => (
                    <label key={lang}>
                        <input type="checkbox" value={lang} checked={languages.includes(lang)} onChange={handleLanguageChange} /> {lang}
                    </label>
                ))}

                <label htmlFor="state"><p>State<sup style={{ color: "red" }}>*</sup></p></label>
                <select id="state" value={selectedState} onChange={handleStateChange}>
                    <option value="">Select State</option>
                    {Object.keys(states).map(state => (
                        <option key={state} value={state}>{state}</option>
                    ))}
                </select>

                <label htmlFor="city"><p>City<sup style={{ color: "red" }}>*</sup></p></label>
                {console.log(isDisabled,loading,Object.keys(cities).length)}
                <select id="city" disabled={isDisabled} value={selectedCity} onChange={e => setSelectedCity(e.target.value)} className={isDisabled ? "disabled" : ""}>
                    <option value="">Select City</option>
                    {Object.keys(cities).map(city => (
                        // console.log("cities : ",city)
                        <option key={city} value={cities[city]}>{cities[city]}</option>
                    ))}
                </select>

                <br />
                <button type="submit" className="submit-buttons">Submit</button>
            </form>
        </div>
    );
}

export default Register;
