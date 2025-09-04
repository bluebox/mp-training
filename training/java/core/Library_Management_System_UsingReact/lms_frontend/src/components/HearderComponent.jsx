import React from 'react'

const HearderComponent = () => {
    return (
        <div>
            <nav className="navbar navbar-dark" style={{ backgroundColor: '#4B0082' }}>
                <div className="container-fluid d-flex justify-content-center align-items-center">
                    <img src="./src/assets/lmslogo.png" width="50px" />
                    <h2 className="ms-2" style={{ color: 'white' }}>Library Management System</h2>
                </div>
            </nav>
        </div>
    )
}

export default HearderComponent