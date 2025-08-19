import { Link, useNavigate } from "react-router-dom"

export default function Navbar() {

  const navigate=useNavigate();

  const handleClick = () => {
    navigate("/");
  }

  return (
    <header className="fixed top-0 left-0 right-0 h-[12vh] bg-white shadow-md flex items-center justify-between px-8 overflow-hidden">
      <h1 className="text-4xl font-bold text-indigo-600 cursor-pointer" onClick={handleClick}>Library Management</h1>
      <nav className="space-x-6">
        <Link to="/books" className="text-gray-700 hover:text-indigo-600 font-medium">Books</Link>
        <Link to="/members" className="text-gray-700 hover:text-indigo-600 font-medium">Members</Link>
        <Link to="/issues" className="text-gray-700 hover:text-indigo-600 font-medium">Issue/Return</Link>
        <Link to="/reports" className="text-gray-700 hover:text-indigo-600 font-medium">Reports</Link>
      </nav>
    </header>
  )
}
