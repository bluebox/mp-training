import React, { useState, useEffect } from 'react';
import { memberService } from '../../services/memberService';
import { transactionService } from '../../services/transactionService';

const MemberList = ({ searchTerm, genderFilter, onEdit }) => {
  const [members, setMembers] = useState([]);
  const [filteredMembers, setFilteredMembers] = useState([]);
  const [activeIssues, setActiveIssues] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMembers();
    fetchActiveIssues();
  }, []);

  const fetchActiveIssues = async () => {
    try {
      const response = await transactionService.getAllActiveIssues();
      setActiveIssues(response.data.data || []);
    } catch (err) {
      console.error('Failed to fetch active issues:', err);
      setActiveIssues([]);
    }
  };

  useEffect(() => {
    let filtered = members;

    if (searchTerm) {
      filtered = filtered.filter(member =>
        member.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        member.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
        member.mobile.includes(searchTerm) ||
        member.memberId.toString().includes(searchTerm)
      );
    }

    if (genderFilter && genderFilter !== 'ALL') {
      filtered = filtered.filter(member => member.gender === genderFilter);
    }

    setFilteredMembers(filtered);
  }, [members, searchTerm, genderFilter]);

  const fetchMembers = async () => {
    try {
      const response = await memberService.getAllMembers();
      const memberData = Array.isArray(response.data.data) ? response.data.data : [];
      setMembers(memberData);
      setFilteredMembers(memberData);
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch members');
      setMembers([]);
      setFilteredMembers([]);
      setLoading(false);
    }
  };

  const memberHasActiveBooks = (memberId) => {
    return activeIssues.some(issue => issue.memberId === memberId && !issue.returned);
  };

  const handleDelete = async (memberId, memberName) => {
    if (memberHasActiveBooks(memberId)) {
      alert(`Cannot delete ${memberName}. This member has active issued books that must be returned first.`);
      return;
    }

    if (window.confirm(`Are you sure you want to delete member "${memberName}"?`)) {
      try {
        await memberService.deleteMember(memberId);
        setMembers(members.filter(member => member.memberId !== memberId));
        alert('Member deleted successfully!');
        
        await fetchActiveIssues();
      } catch (err) {
        console.error('Delete error:', err.response?.data);
        
        if (err.response?.status === 400) {
          alert('Cannot delete this member. They have issued books that must be returned first.');
        } else if (err.response?.status === 500) {
          alert('Cannot delete this member. They may have issued books or other dependencies.');
        } else {
          alert(`Failed to delete member: ${err.response?.data?.message || err.message}`);
        }
      }
    }
  };

  if (loading) return <div className="text-center">Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;

  return (
    <div className="container mt-4">
      <h2>All Members ({filteredMembers.length})</h2>
      
      {filteredMembers.length === 0 ? (
        <div className="text-center mt-4">
          <p>No members found matching your criteria.</p>
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Member ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Gender</th>
                <th>Status</th>
                <th>Issued Books</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredMembers.map(member => {
                const hasActiveBooks = memberHasActiveBooks(member.memberId);
                const activeBookCount = activeIssues.filter(
                  issue => issue.memberId === member.memberId && !issue.returned
                ).length;
                
                return (
                  <tr key={member.memberId}>
                    <td>M{member.memberId.toString().padStart(4, '0')}</td>
                    <td>{member.name}</td>
                    <td>{member.email}</td>
                    <td>{member.mobile || 'N/A'}</td>
                    <td>
                      <span className={`badge ${member.gender === 'Male' ? 'bg-primary' : member.gender === 'Female' ? 'bg-danger' : 'bg-secondary'}`}>
                        {member.gender}
                      </span>
                    </td>
                    <td>
                      <span className={`badge ${member.active ? 'bg-success' : 'bg-danger'}`}>
                        {member.status}
                      </span>
                    </td>
                    <td>
                      {hasActiveBooks ? (
                        <span className="badge bg-warning text-dark">
                          {activeBookCount} Active
                        </span>
                      ) : (
                        <span className="badge bg-light text-dark">None</span>
                      )}
                    </td>
                    <td>
                      <button
                        className="btn btn-sm btn-outline-primary me-2"
                        onClick={() => onEdit(member)}
                        title="Edit Member"
                      >
                        Edit
                      </button>
                      
                      {hasActiveBooks ? (
                        <button
                          className="btn btn-sm btn-secondary"
                          disabled
                          title={`Cannot delete: ${member.name} has ${activeBookCount} active issued book(s)`}
                        >
                          Has Active Books
                        </button>
                      ) : (
                        <button
                          className="btn btn-sm btn-outline-danger"
                          onClick={() => handleDelete(member.memberId, member.name)}
                          title="Delete Member"
                        >
                          Delete
                        </button>
                      )}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default MemberList;
