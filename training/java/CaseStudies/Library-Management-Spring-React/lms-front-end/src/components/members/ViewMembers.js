import React, { useState, useEffect } from 'react';
import './ViewMembers.css';
import EditMember from './EditMember';

const ViewMembers = () => {
  const [members, setMembers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingMember, setEditingMember] = useState(null);

  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async () => {
    try {
      const response = await fetch('http://localhost:8080/getallmembers');
      if (!response.ok) {
        throw new Error('Failed to fetch members');
      }
      const data = await response.json();
      setMembers(data);
    } catch (error) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const filteredMembers = members.filter(member =>
    member.member_Name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    member.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
    member.mobile_No.includes(searchTerm)
  );

  const handleEdit = (member) => {
    setEditingMember(member);
  };

  const handleUpdate = async (updatedMember) => {
    try {
      setMembers(prevMembers => 
        prevMembers.map(member => 
          member.member_Id === updatedMember.member_Id ? updatedMember : member
        )
      );
      await fetchMembers();
    } catch (error) {
      setError('Failed to update member list');
    }
  };

  if (isLoading) return <div className="loading">Loading members...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="content-page">
      <h2>View All Members</h2>
      
      <div className="search-container">
        <input
          type="text"
          placeholder="Search by name, email, or mobile number..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Mobile</th>
              <th>Gender</th>
              <th>Address</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredMembers.map(member => (
              <tr key={member.member_Id}>
                <td>{member.member_Id}</td>
                <td>{member.member_Name}</td>
                <td>{member.email}</td>
                <td>{member.mobile_No}</td>
                <td>{member.gender}</td>
                <td>{member.address}</td>
                <td>
                  <button 
                    className="action-btn edit"
                    onClick={() => handleEdit(member)}
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {editingMember && (
        <EditMember
          member={editingMember}
          onClose={() => setEditingMember(null)}
          onUpdate={handleUpdate}
        />
      )}
    </div>
  );
};

export default ViewMembers;
