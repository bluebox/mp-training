import React from 'react'
import { Link } from 'react-router-dom'

const StudentDashBoard = () => {
  return (
    <div>
       <h1>Welcome back , Student</h1>
        <Link className="btn btn-dark" to="/events">All Events</Link> <br /><br />
        <Link className="btn btn-dark" to="/event">Event By Id</Link> <br /><br />
        <Link className="btn btn-dark" to="/">Home</Link> <br /><br />
    </div>
  )
}

export default StudentDashBoard
