import React, { useState, useEffect } from 'react';
import { reportService } from '../../services/reportService';

const BookPopularityReport = () => {
  const [bookStats, setBookStats] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filter, setFilter] = useState('all');

  useEffect(() => {
    fetchBookPopularityReport();
  }, []);

  const fetchBookPopularityReport = async () => {
    try {
      setLoading(true);
      const response = await reportService.getBookPopularityReport();
      setBookStats(response.data.data);
      setError('');
    } catch (err) {
      setError('Failed to load book popularity report');
    } finally {
      setLoading(false);
    }
  };

  const getFilteredBooks = () => {
    switch(filter) {
      case 'popular':
        return bookStats.filter(book => book.totalIssues > 2).slice(0, 20);
      case 'unused':
        return bookStats.filter(book => book.totalIssues === 0);
      default:
        return bookStats;
    }
  };

  const handleExportCSV = () => {
    const filteredData = getFilteredBooks();
    reportService.exportToCSV(filteredData, `book_popularity_report_${filter}`);
  };

  if (loading) {
    return (
      <div className="card shadow mb-4">
        <div className="card-body text-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  const filteredBooks = getFilteredBooks();

  return (
    <div className="card shadow mb-4">
      <div className="card-header py-3">
        <h6 className="m-0 font-weight-bold text-white">📚 Book Popularity Report</h6>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        
        <div className="row mb-3">
          <div className="col-md-6">
            <select 
              className="form-select"
              value={filter}
              onChange={(e) => setFilter(e.target.value)}
            >
              <option value="all">All Books</option>
              <option value="popular">Most Popular (Top 20)</option>
              <option value="unused">Unused Books</option>
            </select>
          </div>
          <div className="col-md-6 text-end">
            <button className="btn btn-success btn-sm" onClick={handleExportCSV}>
              📥 Export CSV
            </button>
          </div>
        </div>

        <div className="row mb-4">
          <div className="col-md-4">
            <div className="card bg-info text-white">
              <div className="card-body text-center">
                <div className="h5">{bookStats.length}</div>
                <div>Total Books</div>
              </div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="card bg-success text-white">
              <div className="card-body text-center">
                <div className="h5">{bookStats.filter(book => book.totalIssues > 0).length}</div>
                <div>Books Issued</div>
              </div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="card bg-warning text-white">
              <div className="card-body text-center">
                <div className="h5">{bookStats.filter(book => book.totalIssues === 0).length}</div>
                <div>Never Issued</div>
              </div>
            </div>
          </div>
        </div>

        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead>
              <tr>
                <th>Rank</th>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Total Issues</th>
                <th>Currently Issued</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredBooks.map((book, index) => (
                <tr key={book.bookId}>
                <td>
                  {filter === 'popular' ? (
                  <span className="badge bg-primary">{index + 1}</span>
                  ) : (
                  <span>{index + 1}</span>
                  )}
                </td>
                  <td>{book.bookId}</td>
                  <td>{book.title}</td>
                  <td>{book.author}</td>
                  <td>
                    <span className="badge bg-light text-dark">{book.category}</span>
                  </td>
                  <td>
                    <span className="badge bg-primary">{book.totalIssues}</span>
                  </td>
                  <td>
                    <span className="badge bg-info">{book.currentlyIssued}</span>
                  </td>
                  <td>
                    <span className={`badge ${book.available ? 'bg-success' : 'bg-danger'}`}>
                      {book.available ? 'Available' : 'Issued'}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default BookPopularityReport;
