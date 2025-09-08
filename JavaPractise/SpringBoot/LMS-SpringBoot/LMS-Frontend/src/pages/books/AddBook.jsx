import { useState, useEffect } from 'react'

export default function AddBook({ goBack }) {
  const initialForm = {
    title: '',
    author: '',
    category: '',
    status: '',
    availability: '',
    createdBy: ''
  }

  const [form, setForm] = useState(initialForm)
  const [categories, setCategories] = useState([])
  const [statuses, setStatuses] = useState([])
  const [availabilityStatuses, setAvailabilityStatuses] = useState([])
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)

  useEffect(() => {
  const fetchEnums = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/enums')
      const data = await res.json()

      console.log("Enums Response:", data)

      const enums = data.enums || {}
      const catData = enums.Category || []
      const statData = enums.Status || []
      const availData = enums.AvailabilityStatus || []

      setCategories(catData)
      setStatuses(statData)
      setAvailabilityStatuses(availData)

      setForm(f => ({
        ...f,
        category: catData.length > 0 ? catData[0].name : '',
        status: statData.length > 0 ? statData[0].name : '',
        availability: availData.length > 0 ? availData[0].name : ''
      }))
    } catch (err) {
      console.error(err)
      setError('Failed to fetch enum values')
    }
  }

  fetchEnums()
}, [])


  const validateForm = () => {
    if (!form.title.trim()) return "Title cannot be empty"
    if (form.title.length > 35) return "Title cannot exceed 35 characters"
    if (!form.author.trim()) return "Author cannot be empty"
    if (form.author.length > 35) return "Author cannot exceed 35 characters"
    if (!form.createdBy.trim()) return "CreatedBy cannot be empty"
    if (form.createdBy.length > 35) return "CreatedBy cannot exceed 35 characters"
    return null
  }

  const submit = async (e) => {
    e.preventDefault()
    setError(null)
    const validationError = validateForm()
    if (validationError) {
      setError(validationError)
      return
    }

    try {
      setLoading(true)
      const res = await fetch("http://localhost:8080/api/books/addbook", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form)
      })

      if (!res.ok) {
        let errMsg = "Something went wrong"
        try {
          const errData = await res.json()
          errMsg = errData.message || errMsg
        } catch {
          errMsg = await res.text()
        }
        setError(errMsg)
        return
      }

      const msg = await res.text()
      alert(msg)
      setForm(initialForm)
    } catch (err) {
      console.error(err)
      setError("Failed to connect to server")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={{ maxWidth: 600 }}>
      <h3>Add Book</h3>
      {error && <div style={{ color: 'red', marginBottom: 12 }}>{error}</div>}
      <form onSubmit={submit}>
        <div>
          <label>Title:</label><br />
          <input
            value={form.title}
            onChange={e => setForm({ ...form, title: e.target.value })}
          />
        </div><br />

        <div>
          <label>Author:</label><br />
          <input
            value={form.author}
            onChange={e => setForm({ ...form, author: e.target.value })}
          />
        </div><br />

        <div>
          <label>Category:</label><br />
          <select
            value={form.category}
            onChange={e => setForm({ ...form, category: e.target.value })}
          >
            {categories.map(c => (
              <option key={c.name} value={c.name}>{c.name}</option>
            ))}
          </select>
        </div><br />

        <div>
          <label>Status:</label><br />
          <select
            value={form.status}
            onChange={e => setForm({ ...form, status: e.target.value })}
          >
            {statuses.map(s => (
              <option key={s.name} value={s.name}>{s.name}</option>
            ))}
          </select>
        </div><br />

        <div>
          <label>Availability:</label><br />
          <select
            value={form.availability}
            onChange={e => setForm({ ...form, availability: e.target.value })}
          >
            {availabilityStatuses.map(a => (
              <option key={a.name} value={a.name}>{a.name}</option>
            ))}
          </select>
        </div><br />

        <div>
          <label>Created By:</label><br />
          <input
            value={form.createdBy}
            onChange={e => setForm({ ...form, createdBy: e.target.value })}
          />
        </div><br />

        <button type="submit" disabled={loading}>
          {loading ? "Submitting..." : "Submit"}
        </button>
        <button type="button" onClick={goBack} style={{ marginLeft: 8 }}>
          Back
        </button>
      </form>
    </div>
  )
}
