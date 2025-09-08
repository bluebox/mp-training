import { useState } from 'react'
import {  useNavigate } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/issues"

export default function ReturnBook({ goBack }) {
  const [form, setForm] = useState({ bookId: '', memberId: '', returnedTo: '' })
  const [message, setMessage] = useState(null)
  const navigate = useNavigate();

  const handleResponse = async (res) => {
    const text = await res.text() 
    let data
    try { 
      data = text ? JSON.parse(text) : {} 
    } catch { 
      data = {} 
    }

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
    setMessage(null)
    
    const validationError = validateForm()
    if (validationError) {
      setError(validationError)
      return
    }

    try {
      const res = await fetch(`${BASE_URL}/return`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          bookId: Number(form.bookId),
          memberId: Number(form.memberId),
          returnedTo: form.returnedTo
        })
      })
      await handleResponse(res)
      setMessage('Book returned successfully')
      setForm({ bookId: '', memberId: '', returnedTo: '' })
      Navigate("/issues")
    } catch (err) {
      setMessage(err.message)
    }
  }

  return (
    <div>
      <h3>Return Book</h3>
      <form onSubmit={submit}>
        <div>
          <label>Book ID:</label><br/>
          <input 
            // type="number" 
            value={form.bookId} 
            onChange={e => setForm({ ...form, bookId: e.target.value })} 
            required 
          />
        </div><br/>
        <div>
          <label>Member ID:</label><br/>
          <input 
            // type="number" 
            value={form.memberId} 
            onChange={e => setForm({ ...form, memberId: e.target.value })} 
            required 
          />
        </div><br/>
        <div>
          <label>Returned To:</label><br/>
          <input 
            type="text" 
            value={form.returnedTo} 
            onChange={e => setForm({ ...form, returnedTo: e.target.value })} 
            required 
          />
        </div><br/>
        <button type="submit">Return</button>
        <button type="button" onClick={goBack} style={{ marginLeft: 8 }}>Back</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  )
}
