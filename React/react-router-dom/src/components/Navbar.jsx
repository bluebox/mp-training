import React from 'react'
import { Link, NavLink } from 'react-router-dom'

const Navbar = () => {
  return (
    <div className='bg-blue-500 text-white px-6 py-4 shadow-md sticky top-0'>
      <ul className='flex space-x-6'>
        <li>
          <NavLink to="/" className={({isActive }) =>isActive ? 'hover:text-white font-bold text-black bg-blue-700 p-2 rounded-md' : 'text-black font-bold hover:text-white'}>
            Home
        </NavLink>
        </li>
        <li>
             <NavLink to='/create' className={({isActive})=>isActive?'hover:text-white font-bold text-black bg-blue-700 p-2 rounded-md':'text-black font-bold hover:text-white'}>Create</NavLink>
        </li>
      </ul>
    </div>
  )
}

export default Navbar
