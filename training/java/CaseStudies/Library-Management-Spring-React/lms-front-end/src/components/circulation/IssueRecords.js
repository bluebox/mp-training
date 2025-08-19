import React, { useState, useEffect } from 'react';
import './IssueRecords.css';

const IssueRecords = () => {
  const [records, setRecords] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterStatus, setFilterStatus] = useState('all');
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [books, setBooks] = useState({});
  const [members, setMembers] = useState({});

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      setIsLoading(true);
      // Fetch issue records
      const recordsResponse = await fetch('http://localhost:8080/issued-records');
      if (!recordsResponse.ok) throw new Error('Failed to fetch records');
      const recordsData = await recordsResponse.json();

      // Fetch books and members data
      const booksResponse = await fetch('http://localhost:8080/getallbooks');
      const membersResponse = await fetch('http://localhost:8080/getallmembers');
      
      if (!booksResponse.ok) throw new Error('Failed to fetch books');
      if (!membersResponse.ok) throw new Error('Failed to fetch members');

      const booksData = await booksResponse.json();
      const membersData = await membersResponse.json();

      // Create lookup objects
      const booksMap = booksData.reduce((acc, book) => {
        acc[book.book_Id] = book;
        return acc;
      }, {});

      const membersMap = membersData.reduce((acc, member) => {
        acc[member.member_Id] = member;
        return acc;
      }, {});

      setBooks(booksMap);
      setMembers(membersMap);
      setRecords(recordsData);
    } catch (error) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const getStatusLabel = (status) => {
    switch(status) {
      case 'I': return 'Issued';
      case 'R': return 'Returned';
      default: return status;
    }
  };

  const filteredRecords = records.filter(record => {
    const book = books[record.bookId] || {};
    const member = members[record.memberId] || {};
    
    const matchesSearch = 
      book.book_Title?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      member.member_Name?.toLowerCase().includes(searchTerm.toLowerCase());
    
    const matchesFilter = 
      filterStatus === 'all' || 
      (filterStatus === 'issued' && record.status === 'I') ||
      (filterStatus === 'returned' && record.status === 'R');

    return matchesSearch && matchesFilter;
  });

  if (isLoading) return <div className="loading">Loading records...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="content-page">
      <h2>Issue Records</h2>
      
      <div className="filters-container">
        <div className="search-container">
          <input
            type="text"
            placeholder="Search by book title or member name..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
        </div>

        <div className="status-filter">
          <select
            value={filterStatus}
            onChange={(e) => setFilterStatus(e.target.value)}
            className="filter-select"
          >
            <option value="all">All Status</option>
            <option value="issued">Issued</option>
            <option value="returned">Returned</option>
          </select>
        </div>
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th>Book Title</th>
              <th>Member Name</th>
              <th>Issue Date</th>
              <th>Return Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredRecords.map(record => (
              <tr key={record.issueId}>
                <td>{books[record.bookId]?.book_Title || 'Unknown Book'}</td>
                <td>{members[record.memberId]?.member_Name || 'Unknown Member'}</td>
                <td>{record.issueDate}</td>
                <td>{record.returnDate || '-'}</td>
                <td>
                  <span className={`status-badge ${record.status === 'I' ? 'issued' : 'returned'}`}>
                    {getStatusLabel(record.status)}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default IssueRecords;
