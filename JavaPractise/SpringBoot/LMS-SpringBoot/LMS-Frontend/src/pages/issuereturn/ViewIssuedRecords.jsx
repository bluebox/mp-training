import { useEffect, useState } from 'react'

const BASE_URL = "http://localhost:8080/api/issues"

export default function ViewIssuedRecords({ goBack }) {
  const [records, setRecords] = useState([])
  const [error, setError] = useState(null)

  const handleResponse = async (res) => {
    const text = await res.text()
    let data
    try { data = text ? JSON.parse(text) : [] } 
    catch { data = [] }

    if (!res.ok) {
      throw new Error(`${data.message || text || "Error"} [${data.error || "UNKNOWN"}]`)
    }

    return data
  }

  const loadRecords = async () => {
    setError(null)
    try {
      const res = await fetch(`${BASE_URL}/all`)
      const data = await handleResponse(res)
      setRecords(data)
    } catch (err) {
      setError(err.message)
    }
  }

  useEffect(() => { loadRecords() }, [])

  return (
    <div>
      <h3>Issue Records</h3>
      {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Issue ID</th><th>Book ID</th><th>Book Title</th><th>Member ID</th><th>Member Name</th><th>Status</th>
            <th>Issue Date</th><th>Return Date</th><th>Issued By</th><th>Returned To</th>
          </tr>
        </thead>
        <tbody>
          {records.map(r => (
            <tr key={r.issueId}>
              <td>{r.issueId}</td>
              <td>{r.bookId}</td>
              <td>{r.bookTitle || ''}</td>
              <td>{r.memberId}</td>
              <td>{r.memberName || ''}</td>
              <td>{r.status}</td>
              <td>{r.issueDate || ''}</td>
              <td>{r.returnDate || ''}</td>
              <td>{r.issuedBy || ''}</td>
              <td>{r.returnedTo || ''}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={goBack}>Back</button>
    </div>
  )
}
