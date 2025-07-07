import React from 'react'
import { Link, Outlet } from 'react-router-dom'

const Home = () => {
  return (
    
    <div>
      Home
      <Outlet/>

      <nav>
        <ul>
          
          <li><Link to="/books/add">Add Book</Link></li>
          <li><Link to="/books/list">View All books</Link></li>
          <li><Link to="/books/category">View Books Category count</Link></li>

        </ul>

        <ul>
          
          <li><Link to="/members/add">Add Member</Link></li>
          <li><Link to="/members/list">View All Members</Link></li>

        </ul>

        <ul>
          
          <li><Link to="/issues/issue">Issue Book</Link></li>
          <li><Link to="/issues/return">Return Book</Link></li>
          <li><Link to="/issues/list">View All Issued Records</Link></li>
          
        </ul>

      </nav>

    </div>
  )
}

export default Home
