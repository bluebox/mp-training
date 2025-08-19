import React, { useState, useEffect } from 'react';
import './Analytics.css';

const Analytics = () => {
  const [overdueBooks, setOverdueBooks] = useState([]);
  const [categoryStats, setCategoryStats] = useState([]);
  const [memberStats, setMemberStats] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchAnalytics();
  }, []);

  const fetchAnalytics = async () => {
    try {
      setIsLoading(true);
      // Fetch overdue books
      const overdueResponse = await fetch('http://localhost:8080/overdue-books');
      if (!overdueResponse.ok) throw new Error('Failed to fetch overdue books');
      const overdueData = await overdueResponse.json();
      setOverdueBooks(overdueData);

      // Fetch category statistics
      const categoryResponse = await fetch('http://localhost:8080/category-stats');
      if (!categoryResponse.ok) throw new Error('Failed to fetch category stats');
      const categoryData = await categoryResponse.json();
      setCategoryStats(categoryData);

      // Fetch member statistics
      const memberResponse = await fetch('http://localhost:8080/member-stats');
      if (!memberResponse.ok) throw new Error('Failed to fetch member stats');
      const memberData = await memberResponse.json();
      setMemberStats(memberData);

    } catch (error) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  if (isLoading) return <div className="loading">Loading analytics...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="reports-container">
      <div className="report-section">
        <h2>Overdue Books Report</h2>
        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>Issue ID</th>
                <th>Book ID</th>
                <th>Title</th>
                <th>Member</th>
                <th>Due Date</th>
              </tr>
            </thead>
            <tbody>
              {overdueBooks.map(book => (
                <tr key={book.issueId}>
                  <td>{book.issueId}</td>
                  <td>{book.bookId}</td>
                  <td>{book.title}</td>
                  <td>{book.memberName}</td>
                  <td>{book.overDueDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="report-section">
        <h2>Books by Category</h2>
        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>Category</th>
                <th>Total Books</th>
              </tr>
            </thead>
            <tbody>
              {Object.entries(categoryStats).map(([category, count]) => (
                <tr key={category}>
                  <td>{category}</td>
                  <td>{count}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="report-section">
        <h2>Member Statistics</h2>
        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>Member ID</th>
                <th>Name</th>
                <th>Books Issued</th>
              </tr>
            </thead>
            <tbody>
              {memberStats.map(member => (
                <tr key={member.memberId}>
                  <td>{member.memberId}</td>
                  <td>{member.name}</td>
                  <td>{member.booksActiveString}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Analytics;
