import { useEffect, useState } from 'react'

export default function BooksPerCategory({ goBack }) {
  const [data, setData] = useState({})
  const [error, setError] = useState(null)

  const fetchBooksPerCategory = async () => {
    setError(null)
    try {
      const res = await fetch('http://localhost:8080/api/reports/books-per-category')
      const text = await res.text()
      let result
      try { result = text ? JSON.parse(text) : {} } catch { result = {} }
      if (!res.ok) throw new Error(result.message || text || 'Failed to fetch books per category')
      setData(result)
    } catch (err) {
      setError(err.message)
    }
  }

  useEffect(() => { fetchBooksPerCategory() }, [])

  const entries = Object.entries(data)

  return (
    <div>
      <h3>Books Per Category</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>Category</th>
            <th>Count</th>
          </tr>
        </thead>
        <tbody>
          {entries.map(([cat, count]) => (
            <tr key={cat}>
              <td>{cat}</td>
              <td>{count}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={goBack}>Back</button>
    </div>
  )
}
