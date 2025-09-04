import React from 'react';

const PopularBooks = ({ books, loading }) => {
  if (loading) {
    return (
      <div className="card shadow mb-4">
        <div className="card-header py-3">
          <h6 className="m-0 font-weight-bold text-white">Most Popular Books</h6>
        </div>
        <div className="card-body">
          <div className="placeholder-glow">
            {[1,2,3,4,5].map(i => (
              <div key={i} className="d-flex align-items-center mb-3">
                <span className="placeholder col-4"></span>
                <span className="placeholder col-3 ms-2"></span>
                <span className="placeholder col-2 ms-2"></span>
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="card shadow mb-4">
      <div className="card-header py-3">
        <h6 className="m-0 font-weight-bold text-white">Most Popular Books</h6>
      </div>
      <div className="card-body">
        {books && books.length > 0 ? (
          <div>
            {books.map((book, index) => (
              <div key={book.bookId} className="d-flex align-items-center mb-3">
                <div className="mr-3">
                  <div className="icon-circle bg-primary">
                    <span className="text-white font-weight-bold">{index + 1}</span>
                  </div>
                </div>
                <div className="flex-grow-1">
                  <div className="small text-gray-500">{book.bookId}</div>
                  <div className="font-weight-bold">{book.title}</div>
                  <div className="text-xs text-gray-500">by {book.author}</div>
                </div>
                <div className="text-right">
                  <span className="badge bg-primary">{book.issueCount} issues</span>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <p className="text-muted text-center">No data available</p>
        )}
      </div>
    </div>
  );
};

export default PopularBooks;
