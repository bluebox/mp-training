import { useState, useEffect } from 'react'  
import { useNavigate } from "react-router-dom";

const BASE_URL = "http://localhost:8080/api/members"
const ENUM_URL = "http://localhost:8080/api/enums"

export default function AddMember({ goBack }) {
  const navigate = useNavigate();
  const initialForm = {
    name: '',
    email: '',
    mobile: '',
    gender: '',
    address: '',
    createdBy: ''
  }

  const [form, setForm] = useState(initialForm)
  const [genders, setGenders] = useState([])
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    const fetchEnums = async () => {
      try {
        const res = await fetch(ENUM_URL)
        if (!res.ok) throw new Error("Failed to fetch enums")
        const data = await res.json()

        console.log("Enums Response:", data)

        const enums = data.enums || {}
        const genderData = enums.Gender || []

        setGenders(genderData)
        setForm(f => ({
          ...f,
          gender: genderData.length > 0 ? genderData[0].name : ''
        }))
      } catch (err) {
        console.error(err)
        setError('Failed to fetch enum values')
      }
    }

    fetchEnums()
  }, [])

  const handleResponse = async (res) => {
    if (!res.ok) {
      let errData
      try { 
        errData = await res.json() 
      } catch { 
        throw new Error("Unexpected server error") 
      }
      throw new Error(`${errData.message} [${errData.error}] at ${errData.path}`)
    }

    const text = await res.text()
    try { return JSON.parse(text) } 
    catch { return text }
  }

  const validateForm = () => {
    if (!form.name.trim()) return "Name cannot be empty from ui validations"
    if (form.name.length > 35) return "Name cannot exceed 35 characters from ui validations"

    if (!form.email.trim()) return "Email cannot be empty  from ui validations"
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    if (!emailRegex.test(form.email)) return "Invalid email format  from ui validations"

    if (!form.mobile.trim()) return "Mobile cannot be empty from ui validations"
    const mobileRegex = /^\d{10}$/
    if (!mobileRegex.test(form.mobile)) return "Mobile must be 10 digits from ui validations"

    if (!form.address.trim()) return "Address cannot be empty from ui validations"
    if (form.address.length > 50) return "Address cannot exceed 50 characters from ui validations"

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
      const res = await fetch(`${BASE_URL}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form)
      })
      const data = await handleResponse(res)
      alert(typeof data === "string" ? data : "Member added successfully")
      setForm(f => ({ ...initialForm, gender: genders[0]?.name || '' }))
      navigate("/members");
    } catch (e) {
      setError(e.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      <h3>Add Member</h3>
      {error && <div style={{ color: 'red', marginBottom: 12 }}>{error}</div>}
      <form onSubmit={submit}>
        <div>
          <label>Name:</label><br/>
          <input value={form.name} onChange={e=>setForm({...form, name:e.target.value})} />
        </div><br/>
        <div>
          <label>Email:</label><br/>
          <input value={form.email} onChange={e=>setForm({...form, email:e.target.value})} />
        </div><br/>
        <div>
          <label>Mobile:</label><br/>
          <input value={form.mobile} onChange={e=>setForm({...form, mobile:e.target.value})} />
        </div><br/>
        <div>
          <label>Gender:</label><br/>
          <select value={form.gender} onChange={e=>setForm({...form, gender:e.target.value})}>
            {genders.map(g => (
              <option key={g.name} value={g.name}>{g.name}</option>
            ))}
          </select>
        </div><br/>
        <div>
          <label>Address:</label><br/>
          <textarea value={form.address} onChange={e=>setForm({...form, address:e.target.value})} />
        </div><br/>
        <div>
          <label>Created By:</label><br/>
          <input value={form.createdBy} onChange={e=>setForm({...form, createdBy:e.target.value})} />
        </div><br/>
        <button type="submit" disabled={loading}>
          {loading ? "Submitting..." : "Submit"}
        </button>
        <button type="button" onClick={goBack} style={{ marginLeft: 8 }}>Back</button>
      </form>
    </div>
  )
}
