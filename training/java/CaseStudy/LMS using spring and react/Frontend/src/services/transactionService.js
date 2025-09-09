import api from './api';

export const transactionService = {
  

  returnBook: (issueId, actualReturnDate) =>
    api.put(`/issues/${issueId}/return`, { actualReturnDate }),

  getAllActiveIssues: () => api.get('/issues'),

  getIssuedBooksByMember: (memberId) => api.get(`/issues/member/${memberId}`),

  getAllTransactionHistory: () => api.get('/issues/history')
};
