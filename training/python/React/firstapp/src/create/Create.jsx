import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./Create.css";

function Create() {
    const location = useLocation();
    const navigate = useNavigate();

    const [formdata, setFormdata] = useState({
        id: "",
        name: "",
        age: "",
        email: "",
        phoneNumber: "",
        gender: "",
        branch: "",
        language: [],
        state: "",
    });

    useEffect(() => {
        if (location.state && location.state.student) {
            setFormdata(location.state.student);
        }
    }, [location]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;

        if (type === "checkbox" && name === "language") {
            setFormdata((prev) => {
                const updatedLanguages = checked
                    ? [...prev.language, value]
                    : prev.language.filter((lang) => lang !== value);
                return { ...prev, language: updatedLanguages };
            });
        } else {
            setFormdata((prev) => ({ ...prev, [name]: value }));
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (formdata.language.length === 0) {
            alert("Must select at least one language.");
            return;
        }

        const data = JSON.parse(localStorage.getItem("students")) || [];
        let updatedData;

        if (location.state?.student) {
            // Edit existing student
            updatedData = data.map((s) =>
                s.id === formdata.id ? formdata : s
            );
        } else {
            // Add new student
            const newId =
                data.length === 0 ? 1 : data[data.length - 1].id + 1;
            const newStudent = { ...formdata, id: newId };
            updatedData = [...data, newStudent];
        }

        localStorage.setItem("students", JSON.stringify(updatedData));
        alert("Form submitted successfully!");

        // Reset form and navigate to data page
        setFormdata({
            id: "",
            name: "",
            age: "",
            email: "",
            phoneNumber: "",
            gender: "",
            branch: "",
            language: [],
            state: "",
        });

        navigate("/data");
    };

    return (
        <div className="create-container">
            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                        <tr>
                            <td><label htmlFor="name">Name:</label></td>
                            <td>
                                <input type="text" id="name" name="name" placeholder="Enter Name" value={formdata.name} onChange={handleChange} required />
                            </td>
                        </tr>

                        <tr>
                            <td><label htmlFor="age">Age:</label></td>
                            <td>
                                <input type="number" id="age" name="age" min={1} max={100} placeholder="Enter Age" value={formdata.age} onChange={handleChange} required />
                            </td>
                        </tr>

                        <tr>
                            <td><label htmlFor="email">Email:</label></td>
                            <td>
                                <input type="email" id="email" name="email" placeholder="Enter Email" value={formdata.email} onChange={handleChange} required />
                            </td>
                        </tr>

                        <tr>
                            <td><label htmlFor="phoneNumber">Phone Number:</label></td>
                            <td>
                                <input type="tel" id="phoneNumber" name="phoneNumber" minLength={10} maxLength={10} placeholder="Enter Phone Number" value={formdata.phoneNumber} onChange={handleChange} required />
                            </td>
                        </tr>

                        <tr>
                            <td><label>Gender:</label></td>
                            <td>
                                <input type="radio" id="male" name="gender" value="male" checked={formdata.gender === "male"} onChange={handleChange} required />
                                <label htmlFor="male">Male</label>

                                <input type="radio" id="female" name="gender" value="female" checked={formdata.gender === "female"} onChange={handleChange} required />
                                <label htmlFor="female">Female</label>
                            </td>
                        </tr>

                        <tr>
                            <td><label htmlFor="branch">Branch:</label></td>
                            <td>
                                <select id="branch" name="branch" value={formdata.branch} onChange={handleChange} required>
                                    <option value="">Select Branch</option>
                                    <option value="CSE">CSE</option>
                                    <option value="ECE">ECE</option>
                                    <option value="EEE">EEE</option>
                                    <option value="MECH">MECH</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><label>Language:</label></td>
                            <td>
                                <input type="checkbox" id="Telugu" name="language" value="Telugu" checked={formdata.language.includes("Telugu")} onChange={handleChange} />
                                <label htmlFor="Telugu">Telugu</label>

                                <input type="checkbox" id="English" name="language" value="English" checked={formdata.language.includes("English")} onChange={handleChange} />
                                <label htmlFor="English">English</label>

                                <input type="checkbox" id="Hindi" name="language" value="Hindi" checked={formdata.language.includes("Hindi")} onChange={handleChange} />
                                <label htmlFor="Hindi">Hindi</label>
                            </td>
                        </tr>

                        <tr>
                            <td><label htmlFor="state">State:</label></td>
                            <td>
                                <select id="state" name="state" value={formdata.state} onChange={handleChange} required>
                                    <option value="">Select State</option>
                                    <option value="Andhra Pradesh">Andhra Pradesh</option>
                                    <option value="Telangana">Telangana</option>
                                    <option value="Karnataka">Karnataka</option>
                                    <option value="Tamil Nadu">Tamil Nadu</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td colSpan="2">
                                <button type="submit" style={{ marginRight: "10px" }}>
                                    {location.state?.student ? "Update" : "Submit"}
                                </button>
                                <button type="reset" onClick={() => setFormdata({
                                    id: "",
                                    name: "",
                                    age: "",
                                    email: "",
                                    phoneNumber: "",
                                    gender: "",
                                    branch: "",
                                    language: [],
                                    state: "",
                                })}>
                                    Reset
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
}

export default Create;
