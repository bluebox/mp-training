import React from "react";
import { Outlet } from "react-router-dom";

const MemberLayout = () => {
  return (
    <div className="flex gap-4">
      <div className="w-1/4 p-4">
        <h2 className="text-2xl font-bold mb-4">Members</h2>
        <p>
          <a href="/members" className="text-blue-500 hover:underline">
            View All Members
          </a>
        </p>

        <p>
          <a href="/members/add" className="text-blue-500 hover:underline">
            Add Member
          </a>
        </p>

        <p>
          <a href="/members/update" className="text-blue-500 hover:underline">
            Update Member
          </a>
        </p>

        <p>
          <a href="/members/books" className="text-blue-500 hover:underline">
            Members Having Books
          </a>
        </p>
      </div>
      <Outlet />
    </div>
  );
};

export default MemberLayout;
