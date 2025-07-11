import React from 'react'
import { Link } from 'react-router-dom'

const AdminDashBoard = () => {
  return (
    <div>
      <h1>Welcome back , Admin</h1>
        <Link className="btn btn-dark" to="/events">All Events</Link> <br /><br />
        <Link className="btn btn-dark" to="/event">Event By Id</Link> <br /><br />
        <Link className="btn btn-dark" to="/addEvent">Create Event</Link> <br /><br />
        <Link className="btn btn-dark" to="/updateEvent">Update Event</Link> <br /><br />
        <Link className="btn btn-dark" to="/users">All Users</Link> <br /><br />
        <Link className="btn btn-dark" to="/">Home</Link> <br /><br />

    </div>
  )
}

export default AdminDashBoard
