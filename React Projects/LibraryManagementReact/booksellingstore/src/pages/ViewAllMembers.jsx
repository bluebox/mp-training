import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ViewAllMembers() {
  const [members, setMembers] = useState([]);
  const [search, setSearch] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const delay = setTimeout(() => {
      if (search.trim() === '') {
        fetchMembers();
      } else {
        fetchSearchMembers(search);
      }
    }, 300);

    return () => clearTimeout(delay);
  }, [search]);

  const fetchMembers = () => {
    fetch('http://localhost:8080/members/list')
      .then((res) => res.json())
      .then((data) => setMembers(data))
      .catch((err) => console.error('Error fetching members:', err));
  };

  const fetchSearchMembers = (query) => {
    fetch(`http://localhost:8080/members/search?name=${encodeURIComponent(query)}`)
      .then((res) => res.json())
      .then((data) => setMembers(data))
      .catch((err) => console.error('Error searching members:', err));
  };

  const handleEdit = (member) => {
    navigate('/add-member', { state: { member, isEdit: true } });
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Members</h2>

      <input
        type="text"
        placeholder="Search by Name..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ padding: '8px', width: '300px', marginBottom: '20px' }}
      />

      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {members.length > 0 ? (
            members.map((member) => (
              <tr key={member.memberId}>
                <td>{member.memberId}</td>
                <td>{member.name}</td>
                <td>{member.email}</td>
                <td>{member.mobile}</td>
                <td>{member.age}</td>
                <td>{member.gender}</td>
                <td>{member.address}</td>
                <td>
                  <button onClick={() => handleEdit(member)}>Edit</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="8" style={{ textAlign: 'center' }}>No members found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewAllMembers;
