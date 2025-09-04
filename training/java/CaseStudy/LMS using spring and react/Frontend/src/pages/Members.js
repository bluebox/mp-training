import React, { useState } from 'react';
import MemberForm from '../components/members/MemberForm';
import MemberList from '../components/members/MemberList';

const Members = () => {
  const [showForm, setShowForm] = useState(false);
  const [memberToEdit, setMemberToEdit] = useState(null);
  const [refreshKey, setRefreshKey] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const [genderFilter, setGenderFilter] = useState('ALL');

  const genderOptions = ['Male', 'Female', 'Other'];

  const handleMemberSaved = () => {
    setShowForm(false);
    setMemberToEdit(null);
    setRefreshKey(prev => prev + 1);
  };

  const handleEditMember = (member) => {
    setMemberToEdit(member);
    setShowForm(true);
  };

  const handleCancelEdit = () => {
    setShowForm(false);
    setMemberToEdit(null);
  };

  window.onEditMember = handleEditMember;

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1>Members Management</h1>
        <button
          className="btn btn-success"
          onClick={() => setShowForm(true)}
          disabled={showForm}
        >
          Add New Member
        </button>
      </div>

      <div className="row mb-4">
        <div className="col-md-8">
          <input
            type="text"
            className="form-control"
            placeholder="Search by name, email, mobile, or member ID..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="col-md-4">
          <select
            className="form-control"
            value={genderFilter}
            onChange={(e) => setGenderFilter(e.target.value)}
          >
            <option value="ALL">All Genders</option>
            {genderOptions.map(gender => (
              <option key={gender} value={gender}>
                {gender}
              </option>
            ))}
          </select>
        </div>
      </div>

      {showForm && (
        <div className="mb-4">
          <MemberForm 
            memberToEdit={memberToEdit}
            onMemberSaved={handleMemberSaved}
            onCancel={handleCancelEdit}
          />
        </div>
      )}

      <MemberList 
        key={refreshKey}
        searchTerm={searchTerm}
        genderFilter={genderFilter}
        onEdit={handleEditMember}
      />
    </div>
  );
};

export default Members;
