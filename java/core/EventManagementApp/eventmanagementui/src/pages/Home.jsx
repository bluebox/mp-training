import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {
  return (
    <div>
        <h1>Event Maangement System</h1>
        <Link className="btn btn-dark" to="/admin">Admin</Link> <br /><br />
        <Link className="btn btn-dark" to="/faculty">Faculty</Link><br /><br />
        <Link className="btn btn-dark" to="/student">Student</Link>
    </div>
  )
}

export default Home
