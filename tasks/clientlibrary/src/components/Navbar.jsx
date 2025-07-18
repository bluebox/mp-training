import React from "react";

const Navbar = () => {
  return (
    <>
      <div className="flex justify-between px-6 py-6 bg-gray-800 text-white sticky top-0">
        <h1>Library management</h1>
        <div>
          <ul className="flex gap-5">
            <li>
              <a href="/">Home</a>
            </li>
            <li>
              <a href="/books">Books</a>
            </li>
            <li>
              <a href="/members">Members</a>
            </li>
            <li>
              <a href="/issues">Issues</a>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};

export default Navbar;
