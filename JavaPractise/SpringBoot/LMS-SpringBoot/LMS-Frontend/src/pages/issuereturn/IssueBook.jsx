import { useState } from 'react'
import {  useNavigate } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/issues"

export default function IssueBook({ goBack }) {
  const [form, setForm] = useState({ bookId: '', memberId: '', issuedBy: '' })
  const [error, setError] = useState(null)
  const [success, setSuccess] = useState(null)
  const navigate = useNavigate();

  const handleResponse = async (res) => {
    const text = await res.text()
    let data
    try { data = text ? JSON.parse(text) : {} } 
    catch { data = {} }

    if (!res.ok) {
      throw new Error(`${data.message || text || "Error"} [${data.error || "UNKNOWN"}]`)
    }

    return data
  }

  const validateForm = () => {
    if (!form.bookId.trim()) return "Book ID cannot be empty";
    const bookIdNum = Number(form.bookId);
    if (bookIdNum <= 0) return "Book ID must be greater than 0";
    if (!String(form.memberId).trim()) return "Member ID cannot be empty";
    const memberIdNum = Number(form.memberId);
    if (memberIdNum <= 0) return "Member ID must be greater than 0";
    if (!form.issuedBy.trim()) return "Author cannot be empty from UI validations";
    if (form.issuedBy.length > 35) return "Author cannot exceed 35 characters from UI validations";
    return null;
  };

  const submit = async (e) => {
    e.preventDefault()
    setError(null)
    setSuccess(null)
    
    const validationError = validateForm()
    if (validationError) {
      setError(validationError)
      return
    }

    try {
      const res = await fetch(`${BASE_URL}/issue`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          bookId: Number(form.bookId),
          memberId: Number(form.memberId),
          issuedBy: form.issuedBy
        })
      })
      await handleResponse(res)
      setSuccess('Book issued successfully')
      setForm({ bookId: '', memberId: '', issuedBy: '' })

    } catch (err) {
      setError(err.message)
    }
  }

  return (
    <div>
      <h3>Issue Book</h3>
      {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}
      {success && <div style={{ color: 'green', marginBottom: '10px' }}>{success}</div>}
      <form onSubmit={submit}>
        <div>
          <label>Book ID:</label><br/>
          <input value={form.bookId} onChange={e=>setForm({...form, bookId:e.target.value})} required />
        </div><br/>
        <div>
          <label>Member ID:</label><br/>
          <input value={form.memberId} onChange={e=>setForm({...form, memberId:e.target.value})} required />
        </div><br/>
        <div>
          <label>Issued By:</label><br/>
          <input value={form.issuedBy} onChange={e=>setForm({...form, issuedBy:e.target.value})} required />
        </div><br/>
        <button type="submit">Issue</button>
        <button type="button" onClick={goBack} style={{ marginLeft: 8 }}>Back</button>
      </form>
    </div>
  )
}
