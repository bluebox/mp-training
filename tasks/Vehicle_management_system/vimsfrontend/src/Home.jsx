import './App.css';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Home() {
    const [contacts, setContacts] = useState(false);
    const [Data, setData] = useState([]);
    const navigate = useNavigate();
    const location = useLocation();

    // Get logged-in user info from login redirect
    const loggedInUser = location.state?.user || null;

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/UserAuth/reviews');
                setData(response.data);
            } catch (err) {
                alert(err);
            }
        };
        fetchData();
    }, []);

    function handleContacts() {
        setContacts(!contacts);
    }

    function handleClick(e) {
        const type = e.target.alt.split(" ")[0];
        navigate(`/ViewInsurances/${type}`);
    }

    return (
        <div className="Home">
            <div className="Navbar">
                <span className="logo">V.I.M.S</span>
                <ul className="right2">
                    <span className="right1" onClick={handleContacts}>ContactUs</span>
                    {loggedInUser
                        ? <span className="right1">Hello, {loggedInUser.userFirstname}</span>
                        : <span className="right1"><Link to="/login">Login</Link></span>
                    }
                </ul>
            </div>

            <div className="Body">
                <div className="AboutUS">
                    <p></p>
                </div>

                <div className="ViewInsurancePolicy">
                    <h2>All Available Insurance Types</h2>
                    <img src="/scooter.png" onClick={handleClick} className="2wheeler" alt="2 wheeler" />
                    <img src="/3wheeler.png" onClick={handleClick} className="3wheeler" alt="3 wheeler" />
                    <img src="/car.jpg" onClick={handleClick} className="4wheeler" alt="4 wheeler" />
                    <img src="/8wheeler.jpg" onClick={handleClick} className="8wheeler" alt="8 wheeler" />
                </div>

                <div className="UserReviews">
                    <h3>Our Happy Customers</h3>
                    <div id="Container">
                        {Data.length > 0 && Data.map((review, index) => (
                            <div key={index}>
                                {review}
                            </div>
                        ))}
                    </div>
                </div>
            </div>

            {contacts &&
                <div id="Contacts" onMouseLeave={handleContacts}>
                    <h3>Contact US</h3>
                    <p>VIMS.Support@Gmail.com</p>
                    <p>012435678978</p>
                </div>
            }
        </div>
    );
}

export default Home;
