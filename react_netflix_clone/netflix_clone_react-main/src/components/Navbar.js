import React from 'react'
import logo from "../resources/netflix_logo.png"
import "../styles/navbar.css"
function Navbar() {
  return (
    <>
    <nav className='navbar'>
        <img src={logo} alt="not loaded"/>
        <h3>Username</h3>
    </nav>
    </>
  )
}

export default Navbar