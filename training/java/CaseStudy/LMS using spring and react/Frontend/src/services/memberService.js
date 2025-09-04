import api from './api';

export const memberService = {
  getAllMembers: () => api.get('/members'),
  
  createMember: (memberData) => {
    return api.post('/members', memberData);
  },
  
  updateMember: (memberId, memberData) => {
    return api.put(`/members/${memberId}`, memberData);
  },
  
  deleteMember: (memberId) => {
    return api.delete(`/members/${memberId}`);
  }
};
