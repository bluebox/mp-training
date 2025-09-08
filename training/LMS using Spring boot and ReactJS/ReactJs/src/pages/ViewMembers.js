import { useEffect, useState } from "react";

export default function ViewMembers() {
  const [members, setMembers] = useState([])
  const [records, setRecords] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [selected, setSelected] = useState([])
  const [currentPage, setCurrentPage] = useState(1);
  const [searchTerm, setSearchTerm] = useState("");
  const [searchField, setSearchField] = useState("all");
  const pageSize = 5;

  const toggleSelect = (id) => {
    setSelected((prev) => {
      const updated = [...prev];
      const index = updated.indexOf(id)

      if (index !== -1) {
        updated.splice(index, 1)
      } else {
        updated.push(id);
      }
      return updated;
    });
  };
  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };
  useEffect(() => {
    const load = async () => {
      try {
        const responses = await Promise.all([
          fetch("http://localhost:8080/api/issues/viewIssuedRecords"),
          fetch("http://localhost:8080/api/members/viewMembers"),
        ]);
        const bad = responses.find((r) => !r.ok);
        if (bad) {
          const text = await bad.text();
          throw new Error(`Request failed (${bad.status}): ${text.slice(0, 1000)}`);
        }
        const parsed = await Promise.all(responses.map((r) => r.json()));
        const [recordsData, membersData] = parsed;
        
        setRecords(recordsData);
        console.log(recordsData);
        console.log(membersData);
        setMembers(membersData);
        setLoading(false);
      } catch (err) {
        console.error(err);
        setError(err.message || String(err));
        alert(err.message);
        setLoading(false);
      }
    };
    load();
  }, []);

  const handleBatchDelete = async () => {
    if (selected.length === 0) {
      alert('select members to delete')
      return
    }
    if (!window.confirm(`Delete ${selected.length} members?`))
      return;
    try {
      const response = await fetch(
        "http://localhost:8080/api/members/batchDeleteMembers",
        {
          method: 'POST',
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(selected),
        }
      )
      if (!response.ok) {
        const errorText = await response.text();
        alert(errorText)
        return
      }
      setMembers((prev) => prev.filter((member) => !selected.includes(member.memberID)))
      setSelected([])
      alert("Selected members deleted successfully!")
    }
    catch (err) {
      alert(err.message)
    }
  }

  const hasTakenBook = (memberId) => {
    return records.filter(record => record.memberId === memberId && record.status === 'ISSUED').length > 0;
  }

  const filteredMembers = members.filter((member) => {
    if (!searchTerm.trim()) return true;
    const term = searchTerm.toLowerCase();
    if (searchField === "all") {
      return Object.values(member).some((value) =>
        value && String(value).toLowerCase().includes(term)
      );
    } else {
      const value = member[searchField];
      return value && String(value).toLowerCase().includes(term);
    }
  });

  const totalPages = Math.ceil(filteredMembers.length / pageSize);
  const paginatedMembers = filteredMembers.slice((currentPage - 1) * pageSize, currentPage * pageSize);
  const formatDateTime = (dateTime) => {
    if (!dateTime) return ''
    dateTime = String(dateTime)
    dateTime = dateTime.replace('T', '\n')
    return dateTime;
  }
  if (loading) return <p>Loading...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div>
      <h1>Members</h1>
      <div style={{ marginBottom: "10px" }}>
        <input
          type="text"
          placeholder="Search members..."
          value={searchTerm}
          onChange={e => {
            setSearchTerm(e.target.value);
            setCurrentPage(1);
          }}
          style={{ width: "300px", padding: "5px" }}
        />
        <select
          value={searchField}
          onChange={e => setSearchField(e.target.value)}
          style={{ marginLeft: "10px", padding: "5px" }}
        >
          <option value="all">All Fields</option>
          <option value="memberID">ID</option>
          <option value="name">Name</option>
          <option value="email">Email</option>
          <option value="phoneNumber">Phone</option>
          <option value="gender">Gender</option>
          <option value="address">Address</option>
          <option value="createdAt">Created At</option>
          <option value="createdBy">Created By</option>
          <option value="updatedAt">Updated At</option>
          <option value="updatedBy">Updated By</option>
        </select>
      </div>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Has active issue?</th>
            <th>Created At</th>
            <th>Created By</th>
            <th>Updated At</th>
            <th>Updated By</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {paginatedMembers.map((member) => (
            <tr key={member.memberID}>
              <td>
                <input
                  type="checkbox"
                  checked={selected.includes(member.memberID)}
                  onChange={() => toggleSelect(member.memberID)}
                />
              </td>
              <td>{member.memberID}</td>
              <td>{member.name}</td>
              <td>{member.email}</td>
              <td>{member.phoneNumber}</td>
              <td>{capitalizeFirst(member.gender)}</td>
              <td>{member.address}</td>
              <td>{hasTakenBook(member.memberID) ? "Yes" : "No"}</td>
              <td>{formatDateTime(member.createdAt)}</td>
              <td>{capitalizeFirst(member.createdBy)}</td>
              <td>{formatDateTime(member.updatedAt)}</td>
              <td>{capitalizeFirst(member.updatedBy)}</td>
              <th>
                <button className="update-button"
                  onClick={() => window.location.href = `/updateMember/${member.memberID}`}>
                  Update
                </button>
              </th>
            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ margin: "10px 0", display: "flex", justifyContent: "center", alignItems: "center" }}>
        <button
          type="button"
          className="pagination-btn"
          onClick={() => setCurrentPage((p) => Math.max(1, p - 1))}
          disabled={currentPage === 1}
          style={{ marginRight: "10px" }}
        >
          Prev
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          type="button"
          className="pagination-btn"
          onClick={() => setCurrentPage((p) => Math.min(totalPages, p + 1))}
          disabled={currentPage === totalPages}
          style={{ marginLeft: "10px" }}
        >
          Next
        </button>
      </div>

      <div style={{ margin: '10px' }}>
        <button
          onClick={handleBatchDelete}
          disabled={selected.length === 0}
        >
          Delete Selected
        </button>
        <button
          type="button"
          onClick={() => (window.location.href = "/")}
          style={{ marginLeft: '10px' }}
        >
          Back to Main Menu
        </button>
      </div>
    </div>
  );
}
