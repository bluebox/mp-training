import { useEffect, useState } from 'react'

export default function OverdueBooks({ goBack }) {
  const [records, setRecords] = useState([])
  const [error, setError] = useState(null)

  const fetchOverdueBooks = async () => {
    setError(null)
    try {
      const res = await fetch('http://localhost:8080/api/reports/overdue-books')
      const text = await res.text()
      let data
      try { data = text ? JSON.parse(text) : [] } catch { data = [] }
      if (!res.ok) throw new Error(data.message || text || 'Failed to fetch overdue books')
      setRecords(data)
    } catch (err) {
      setError(err.message)
    }
  }

  useEffect(() => { fetchOverdueBooks() }, [])

  return (
    <div>
      <h3>Overdue Books</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Book ID</th>
            <th>Return/Due Date</th>
            <th>Book Title</th>
            <th>Member Name</th>
          </tr>
        </thead>
        <tbody>
          {records.map(r => (
            <tr key={r.bookId + r.memberName}>
              <td>{r.bookId}</td>
              <td>{r.returnDate || ''}</td>
              <td>{r.bookTitle || ''}</td>
              <td>{r.memberName || ''}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={goBack}>Back</button>
    </div>
  )
}
