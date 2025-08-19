import { useEffect, useState } from "react"
import api from "../api/axiosConfig"
import Navbar from "../components/Navbar"

export default function ReportsPage() {
  const [activeTab, setActiveTab] = useState("activeIssues")
  const [activeIssues, setActiveIssues] = useState([])
  const [overdueIssues, setOverdueIssues] = useState([])
  const [bookSummary, setBookSummary] = useState([])

  useEffect(() => {
    fetchActiveIssues()
    fetchOverdueIssues()
    fetchBookSummary()
  }, [])

  const fetchActiveIssues = async () => {
    try {
      const res = await api.get("/reports/activeIssuedBooks")
      console.log(res.data)
      setActiveIssues(res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchOverdueIssues = async () => {
    try {
      const res = await api.get("/reports/overdueBooks")
      setOverdueIssues(res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchBookSummary = async () => {
    try {
      const res = await api.get("/reports/bookCategoryCount")
      console.log(res.data)
      setBookSummary(res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const renderTable = () => {
    if (activeTab === "activeIssues") {
      return (
        <div className="overflow-x-auto bg-white rounded-xl shadow mb-4">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Member ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Member Name</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Book ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Book Title</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue Date</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {activeIssues.map(issue => (
                <tr key={issue.issueId}>
                  <td className="px-6 py-4">{issue.issueId}</td>
                  <td className="px-6 py-4">{issue.memberId}</td>
                  <td className="px-6 py-4">{issue.memberName}</td>
                  <td className="px-6 py-4">{issue.bookId}</td>
                  <td className="px-6 py-4">{issue.bookTitle}</td>
                  <td className="px-6 py-4">{issue.issueDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )
    }

    if (activeTab === "overdue") {
      return (
        <div className="overflow-x-auto bg-white rounded-xl shadow mb-4">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Member ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Member Name</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Book ID</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Book Title</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Issue Date</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {overdueIssues.map(issue => (
                <tr key={issue.issueId}>
                  <td className="px-6 py-4">{issue.issueId}</td>
                  <td className="px-6 py-4">{issue.memberId}</td>
                  <td className="px-6 py-4">{issue.memberName}</td>
                  <td className="px-6 py-4">{issue.bookId}</td>
                  <td className="px-6 py-4">{issue.bookTitle}</td>
                  <td className="px-6 py-4">{issue.issueDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )
    }

    if (activeTab === "bookSummary") {
      return (
        <div className="overflow-x-auto bg-white rounded-xl shadow mb-4">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Category</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Count</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {bookSummary.map(book => (
                <tr key={book.category}>
                  <td className="px-6 py-4">{book.category}</td>
                  <td className="px-6 py-4">{book.count}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 w-[100vw]">
      <Navbar />
      <main className="pt-20 px-8">
        <h2 className="text-2xl font-semibold text-gray-800 mb-4">Reports</h2>
        <div className="flex space-x-4 mb-4">
          <button
            onClick={() => setActiveTab("activeIssues")}
            className={`px-4 py-2 rounded ${activeTab === "activeIssues" ? "bg-indigo-600 text-indigo-600" : "bg-gray-200 text-gray-700"}`}
          >
            Active Issues
          </button>
          <button
            onClick={() => setActiveTab("overdue")}
            className={`px-4 py-2 rounded ${activeTab === "overdue" ? "bg-indigo-600 text-indigo-600" : "bg-gray-200 text-gray-700"}`}
          >
            Overdue Books
          </button>
          <button
            onClick={() => setActiveTab("bookSummary")}
            className={`px-4 py-2 rounded ${activeTab === "bookSummary" ? "bg-indigo-600 text-indigo-600" : "bg-gray-200 text-gray-700"}`}
          >
            Book Summary
          </button>
        </div>

        {renderTable()}
      </main>
    </div>
  )
}
