import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { add_user,update_user } from "./actions";
import { useNavigate } from "react-router-dom";
function Register() {
    const { id } = useParams();
    const [edit,setEdit] = useState(false);
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
    const dispatch = useDispatch();
    const navigate = useNavigate();

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

    const users = useSelector((state)=>state.users)

    useEffect(() => {
        if (!id) {
            setEdit(false);
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
            setNameError("");
            setPhoneError("");
        }
    }   , [id]);


    useEffect(() => {
        
        if (!id || !Object.keys(states).length){
            return;
        }

        const data = users.find(u=>u.id===parseInt(id))
        if (data) {
            // const parsedData = JSON.parse(data);
            setEdit(true);
            setName(data.name);
            setEmail(data.email);
            setPhone(data.phone);
            setAge(data.age);
            setBranch(data.branch);
            setLanguages(data.languages || []);
            setSelectedState(data.state);
            console.log(data.state)
            console.log(selectedStatePseudo)
            setSelectedStatePseudo(data.state); 
            setSelectedCity(data.city);
            console.log(selectedStatePseudo);
            if (data.name.length < 3) {
                setNameError("Name must be at least 3 characters");
            }

        }
    }, [id, users,states]); 


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
        // if(Object.keys(cities).length ===0)
        // {
        //     setSelectedCity("none");
        //     console.log("none");
        // }
        // console.log(selectedCity);
        const formData = {
            name,
            email,
            phone,
            age,
            branch,
            languages,
            state: selectedState,
            city: Object.keys(cities).length ===0?"NONE":selectedCity,
        };
        console.log(formData)
        // const uid = id || localStorage.length + 1;
        // localStorage.setItem(uid.toString(), JSON.stringify(formData));
        if(edit){
            dispatch(update_user(parseInt(id),formData))
        }else{
            dispatch(add_user(formData))
        }
        
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
        setEdit(false);
        setCities({});
        navigate("/");
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
