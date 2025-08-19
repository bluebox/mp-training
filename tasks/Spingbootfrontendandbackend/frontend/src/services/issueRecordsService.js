import api from "../api/axios";

// Match the backend base path "/issue" (singular)
export const getIssueRecords = () => api.get("/issue/printAllIssueRecords");

export const issueBook = (bookId, memberId) =>
  api.post(`/issue/issuethebook/${bookId}/${memberId}`); // should be POST to match controller

export const returnBook = (bookId, memberId) =>
  api.post(`/issue/returnbook/${bookId}/${memberId}`);
