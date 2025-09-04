import React, { useState, useEffect } from 'react';
import { memberService } from '../../services/memberService';

const MemberForm = ({ memberToEdit, onMemberSaved, onCancel }) => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    mobile: '',
    gender: 'Male',
    address: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const isEditMode = !!memberToEdit;
  const genderOptions = ['Male', 'Female', 'Other'];

  useEffect(() => {
    if (memberToEdit) {
      setFormData({
        name: memberToEdit.name || '',
        email: memberToEdit.email || '',
        mobile: memberToEdit.mobile || '',
        gender: memberToEdit.gender || 'Male',
        address: memberToEdit.address || ''
      });
    }
  }, [memberToEdit]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.name || !formData.email) {
      setError('Name and Email are required');
      return;
    }

    setLoading(true);
    setError('');

    try {
      if (isEditMode) {
        await memberService.updateMember(memberToEdit.memberId, formData);
        alert('Member updated successfully!');
      } else {
        await memberService.createMember(formData);
        alert('Member added successfully!');
      }

      if (onMemberSaved) onMemberSaved();
      
      if (!isEditMode) {
        setFormData({ 
          name: '', 
          email: '', 
          mobile: '', 
          gender: 'Male', 
          address: '' 
        });
      }
    } catch (err) {
      console.error('Error saving member:', err.response?.data);
      setError(`Failed to ${isEditMode ? 'update' : 'add'} member: ${err.response?.data?.message || err.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h5>{isEditMode ? 'Edit Member' : 'Add New Member'}</h5>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label className="form-label">Name *</label>
              <input
                type="text"
                className="form-control"
                name="name"
                value={formData.name}
                onChange={handleChange}
                pattern="[A-Za-z ]+"
                title="Please enter a valid name (letters and spaces only)"
                required
              />
            </div>
            
            <div className="col-md-6 mb-3">
              <label className="form-label">Email *</label>
              <input
                type="email"
                className="form-control"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="row">
            <div className="col-md-6 mb-3">
              <label className="form-label">Mobile</label>
              <input
                type="tel"
                className="form-control"
                name="mobile"
                value={formData.mobile}
                onChange={handleChange}
                pattern="[0-9]{10}"
                title="Please enter a 10-digit mobile number"
              />
            </div>
            
            <div className="col-md-6 mb-3">
              <label className="form-label">Gender</label>
              <select
                className="form-control"
                name="gender"
                value={formData.gender}
                onChange={handleChange}
              >
                {genderOptions.map(gender => (
                  <option key={gender} value={gender}>{gender}</option>
                ))}
              </select>
            </div>
          </div>

          <div className="mb-3">
            <label className="form-label">Address</label>
            <textarea
              className="form-control"
              name="address"
              rows="3"
              value={formData.address}
              onChange={handleChange}
              placeholder="Enter full address"
              required
            />
          </div>

          <div className="d-flex gap-2">
            <button 
              type="submit" 
              className="btn btn-primary"
              disabled={loading}
            >
              {loading ? 'Saving...' : (isEditMode ? 'Update Member' : 'Add Member')}
            </button>
            
            {isEditMode && (
              <button 
                type="button" 
                className="btn btn-secondary"
                onClick={onCancel}
                disabled={loading}
              >
                Cancel
              </button>
            )}
          </div>
        </form>
      </div>
    </div>
  );
};

export default MemberForm;
