import { useEffect, useState } from "react"
import api from "../api/axiosConfig"
import Navbar from "../components/Navbar"

export default function Dashboard() {
  const [stats, setStats] = useState({
    books: 0,
    members: 0,
    activeIssues: 0,
    overdue: 0,
  })

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const [booksRes, membersRes, issuesRes, overdueRes] = await Promise.all([
          api.get("/books"),
          api.get("/members"),
          api.get("/reports/activeIssuedBooks"),
          api.get("/reports/overdueBooks"),
        ])

        setStats({
          books: booksRes.data.data ? booksRes.data.data.length : booksRes.data.length,
          members: membersRes.data.data ? membersRes.data.data.length : membersRes.data.length,
          activeIssues: issuesRes.data.length,
          overdue: overdueRes.data.length,
        })
      } catch (err) {
        console.error("Error fetching dashboard stats", err)
      }
    }

    fetchStats()
  }, [])

  return (
    <div className="min-h-screen bg-gray-100 w-[100vw]">
      <Navbar />
      <main className="pt-20 px-8">
        <h2 className="text-2xl font-semibold text-gray-800 mb-6">Dashboard</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          <div className="bg-white p-6 rounded-2xl shadow text-center">
            <h3 className="text-gray-500 text-sm">Total Books</h3>
            <p className="text-2xl font-bold text-indigo-600">{stats.books}</p>
          </div>
          <div className="bg-white p-6 rounded-2xl shadow text-center">
            <h3 className="text-gray-500 text-sm">Total Members</h3>
            <p className="text-2xl font-bold text-indigo-600">{stats.members}</p>
          </div>
          <div className="bg-white p-6 rounded-2xl shadow text-center">
            <h3 className="text-gray-500 text-sm">Active Issues</h3>
            <p className="text-2xl font-bold text-indigo-600">{stats.activeIssues}</p>
          </div>
          <div className="bg-white p-6 rounded-2xl shadow text-center">
            <h3 className="text-gray-500 text-sm">Overdue</h3>
            <p className="text-2xl font-bold text-red-500">{stats.overdue}</p>
          </div>
        </div>
        <div className="mt-8 bg-white p-6 rounded-2xl shadow">
          <h2 className="text-lg font-semibold text-gray-800 mb-4">Welcome to the Library Management System</h2>
          <p className="text-gray-600 leading-relaxed">
            Manage books, members, issue and return records, and track reports all in one place with an easy-to-use interface.
          </p>
        </div>
      </main>
    </div>
  )
}
