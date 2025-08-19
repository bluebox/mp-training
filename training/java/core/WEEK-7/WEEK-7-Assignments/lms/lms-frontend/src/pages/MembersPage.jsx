import { useEffect, useState } from "react"
import api from "../api/axiosConfig"
import Navbar from "../components/Navbar"

export default function MembersPage() {
  const [members, setMembers] = useState([])
  const [genders, setGenders] = useState([])
  const [modalOpen, setModalOpen] = useState(false)
  const [editMember, setEditMember] = useState(null)
  const [form, setForm] = useState({ name: "", email: "", mobile: "", gender: "", address: "" })
  const [errors, setErrors] = useState({})

  const fetchMembers = async () => {
    try {
      const res = await api.get("/members")
      setMembers(res.data.data || res.data)
    } catch (err) {
      console.error(err)
    }
  }

  const fetchGenders = async () => {
    try {
      const res = await api.get("/members/genders")
      setGenders(res.data.data || res.data)
    } catch (err) {
      console.error(err)
    }
  }

  useEffect(() => {
    fetchMembers()
    fetchGenders()
  }, [])

  const openAddModal = () => {
    setEditMember(null)
    setForm({ name: "", email: "", mobile: "", gender: null, address: "" })
    setErrors({})
    setModalOpen(true)
  }

  const openEditModal = (member) => {
    setEditMember(member)
    setForm(member ? member :{
      name: "",
      email: "",
      mobile: "",
      gender: null,
      address: ""
    })
    setErrors({})
    setModalOpen(true)
  }

  const handleChange = (e) => {
    if (e.target.value==="default") {
      setForm({ ...form, [e.target.name]: null })
    } else {
      setForm({ ...form, [e.target.name]: e.target.value })
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    console.log("Form Data:", form);
    if (editMember) {
      await api.put(`/members/${editMember.memberId}`, form)
        .then((res) => {
          console.log(res);
          alert(res.data.message);
          fetchMembers();
          setModalOpen(false);
        })
        .catch((err) => {
          console.log(err);
          alert(err.response.data.message || err.response.data.messages[0] || "Failed to add book");
        });
    } else {
      await api.post("/members", form)
        .then((res) => {
          alert(res.data.message);
          fetchMembers();
          setModalOpen(false);
        })
        .catch((err) => {
          alert(err.response.data.message || err.response.data.messages[0] || "Failed to add book");
        });
    }
    fetchMembers()
  }

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this member?")) {
      try {
        await api.delete(`/members/${id}`)
        fetchMembers()
      } catch (err) {
        console.error(err)
      }
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 w-[100vw]">
      <Navbar />
      <main className="pt-20 px-8">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-semibold text-gray-800">Members ({members.length})</h2>
          <button onClick={openAddModal} className="bg-indigo-600 text-indigo-600 px-4 py-2 rounded hover:bg-indigo-700">
            Add Member
          </button>
        </div>

        <div className="overflow-x-auto bg-white rounded-xl shadow">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Name</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Email</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Mobile</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Gender</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Address</th>
                <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {members.map((member) => (
                <tr key={member.id}>
                  <td className="px-6 py-4">{member.name}</td>
                  <td className="px-6 py-4">{member.email}</td>
                  <td className="px-6 py-4">{member.mobile}</td>
                  <td className="px-6 py-4">{member.gender}</td>
                  <td className="px-6 py-4">{member.address}</td>
                  <td className="px-6 py-4 space-x-2">
                    <button onClick={() => openEditModal(member)} className="text-indigo-600 hover:underline">Edit</button>
                    <button onClick={() => handleDelete(member.memberId)} className="text-red-600 hover:underline">Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Modal */}
        {modalOpen && (
          <div className="fixed inset-0 bg-black/35 bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white rounded-xl w-96 p-6 relative">
              <h3 className="text-xl font-semibold mb-4">{editMember ? "Update Member" : "Add Member"}</h3>
              {errors.general && <p className="text-red-600 mb-2">{errors.general}</p>}
              <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700">Name</label>
                  <input
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.name && <p className="text-red-600 text-sm">{errors.name}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Email</label>
                  <input
                    name="email"
                    type="email"
                    value={form.email}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.email && <p className="text-red-600 text-sm">{errors.email}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Mobile</label>
                  <input
                    name="mobile"
                    value={form.mobile}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.mobile && <p className="text-red-600 text-sm">{errors.mobile}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Gender</label>
                  <select
                    name="gender"
                    value={form.gender}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  >
                    <option value="default">Select Gender</option>
                    {genders.map((g) => (
                      <option key={g} value={g}>{g}</option>
                    ))}
                  </select>
                  {errors.gender && <p className="text-red-600 text-sm">{errors.gender}</p>}
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700">Address</label>
                  <input
                    name="address"
                    value={form.address}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded px-3 py-2"
                  />
                  {errors.address && <p className="text-red-600 text-sm">{errors.address}</p>}
                </div>

                <div className="flex justify-end space-x-2">
                  <button type="button" onClick={() => setModalOpen(false)} className="px-4 py-2 rounded bg-gray-300 hover:bg-gray-400">Cancel</button>
                  <button type="submit" className="px-4 py-2 rounded bg-indigo-600 text-indigo-600 hover:bg-indigo-700" onClick={e => handleSubmit(e)}>{editMember ? "Update" : "Add"}</button>
                </div>
              </form>
            </div>
          </div>
        )}
      </main>
    </div>
  )
}
