import { useEffect, useState } from 'react'

export default function ActiveIssuedMembers({ goBack }) {
  const [records, setRecords] = useState([])
  const [error, setError] = useState(null)

  const fetchActiveMembers = async () => {
    setError(null)
    try {
      const res = await fetch('http://localhost:8080/api/reports/members-active')
      const text = await res.text()
      let data
      try { data = text ? JSON.parse(text) : [] } catch { data = [] }
      if (!res.ok) throw new Error(data.message || text || 'Failed to fetch active members')
      setRecords(data)
    } catch (err) {
      setError(err.message)
    }
  }

  useEffect(() => { fetchActiveMembers() }, [])

  return (
    <div>
      <h3>Active Issued Members</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Book ID</th>
            <th>Book Title</th>
            <th>Issue Date</th>
          </tr>
        </thead>
        <tbody>
          {records.map(r => (
            <tr key={`${r.memberId}-${r.bookId}`}>
              <td>{r.memberId}</td>
              <td>{r.name}</td>
              <td>{r.email}</td>
              <td>{r.bookId}</td>
              <td>{r.bookTitle}</td>
              <td>{r.issueDate ? new Date(r.issueDate).toLocaleString() : ''}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={goBack}>Back</button>
    </div>
  )
}
