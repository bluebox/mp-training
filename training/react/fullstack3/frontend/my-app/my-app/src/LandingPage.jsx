// LandingPage.jsx

import { useState } from 'react';
import { Link } from 'react-router-dom';
import UserCompnent from './UserComponent';


function LandingPage() {
    const [viewUser, setViewUser] = useState(null)
    const [viewAdmin, setViewAdmin] = useState(null)
    const userView = async () => {
        setViewUser(true);
        setViewAdmin(null);
    }
    const adminView = async () => {
        setViewAdmin(true)
        setViewUser(null)
    }
    return (
        <div id="landing">

            <h1>Welcome to Windfire MicroFinance Services</h1>
            <div id="role">

                <button style={{ marginRight: '20px' }} onClick={userView}> User</button>
                <button onClick={adminView}> Admin</button>
            </div>

            {viewUser && (
                // <button style={{ marginRight: '20px' }}>View People</button>
                <div style={{ marginLeft: '-200px' }}>
                    <Link to="/create-customer">
                        <button>Enroll as Customer</button>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to="/loans">
                        <button>Available Loans</button>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to="/data">
                        <button>My Loan Schedule</button>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to="/payments">
                        <button>Manage payments</button>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to="/show-member">
                        <button>View Member</button>
                    </Link>




                    {/* <UserCompnent /> */}
                </div>
            )}

            {viewAdmin && (
                <div style={{ marginLeft: '-200px' }}>
                    <Link to="/issue-loan">
                        <button>Issue Loan</button>
                    </Link>
                    <br></br>
                    <br></br>

                    <Link to="/make-payment">
                        <button>Make Payment</button>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to="/create-loan">
                        <button>Create Loan plan</button>
                    </Link>
                     <br></br>
                    <br></br>
                     <Link to="/show-members">
                        <button>Show Members</button>
                    </Link>
                </div>
            )}



        </div>
    );
}

export default LandingPage;
