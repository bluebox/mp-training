import React from 'react';

const StatsCards = ({ data }) => {
  return (
    <div className="row">
      <div className="col-lg-3 col-md-6 mb-4">
        <div className="card bg-primary text-white">
          <div className="card-body">
            <div className="d-flex align-items-center">
              <div className="me-3">
                <div className="fs-1">📚</div>
              </div>
              <div>
                <div className="fs-2 fw-bold">{data?.totalBooks || 0}</div>
                <div className="small">Total Books</div>
                <div className="small text-light">
                  {data?.availableBooks || 0} available
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div className="col-lg-3 col-md-6 mb-4">
        <div className="card bg-success text-white">
          <div className="card-body">
            <div className="d-flex align-items-center">
              <div className="me-3">
                <div className="fs-1">👥</div>
              </div>
              <div>
                <div className="fs-2 fw-bold">{data?.totalMembers || 0}</div>
                <div className="small">Active Members</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div className="col-lg-3 col-md-6 mb-4">
        <div className="card bg-info text-white">
          <div className="card-body">
            <div className="d-flex align-items-center">
              <div className="me-3">
                <div className="fs-1">📖</div>
              </div>
              <div>
                <div className="fs-2 fw-bold">{data?.activeIssues || 0}</div>
                <div className="small">Books Issued</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div className="col-lg-3 col-md-6 mb-4">
        <div className="card bg-warning text-dark">
          <div className="card-body">
            <div className="d-flex align-items-center">
              <div className="me-3">
                <div className="fs-1">⚠️</div>
              </div>
              <div>
                <div className="fs-2 fw-bold">{data?.overdueBooks || 0}</div>
                <div className="small">Overdue Books</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StatsCards;
