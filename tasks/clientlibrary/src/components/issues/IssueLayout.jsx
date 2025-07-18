import React from "react";
import { Outlet } from "react-router-dom";

const IssueLayout = () => {
  return (
    <div className="flex gap-4">
      <div className="w-1/4 p-4">
        <h2 className="text-2xl font-bold mb-4">Issue Management</h2>
        <p>
          <a href="/issues" className="text-blue-500 hover:underline">
            View All Issues
          </a>
        </p>
        <p>
          <a href="/issues/issue" className="text-blue-500 hover:underline">
            Issue Book
          </a>
        </p>
        <p>
          <a href="/issues/return" className="text-blue-500 hover:underline">
            Return Book
          </a>
        </p>
        <p>
          <a href="/issues/overdue" className="text-blue-500 hover:underline">
            Overdue Records
          </a>
        </p>
      </div>
      <Outlet />
    </div>
  );
};

export default IssueLayout;
