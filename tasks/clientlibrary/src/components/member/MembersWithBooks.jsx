import React, { useState, useEffect } from "react";
import MemberWithBook from "./MemberWithBook";

const MembersWithBooks = () => {
  const [membersWithBooks, setMembersWithBooks] = useState([]);

  useEffect(() => {
    const fetchMembersWithBooks = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/members/with-active-books",
          {
            method: "GET",
            credentials: "include",
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch members with books");
        }
        const responseData = await response.json();
        setMembersWithBooks(responseData.data);
      } catch (error) {
        console.error("Error fetching members with books:", error);
      }
    };
    fetchMembersWithBooks();
  }, []);

  return (
    <div>
      <h2 className="my-5">Members With Active Issued Books</h2>
      <div>
        <table>
          <thead>
            <tr>
              <th className="px-4 py-3">Member ID</th>
              <th className="px-4 py-3">Member Name</th>
              <th className="px-4 py-3">Book ID</th>
              <th className="px-4 py-3">Book Name</th>
              <th className="px-4 py-3">Mobile</th>
              <th className="px-4 py-3">Address</th>
              <th className="px-4 py-3">Issue Date</th>
            </tr>
          </thead>
          <tbody>
            {membersWithBooks && membersWithBooks.length > 0 ? (
              membersWithBooks.map((dto, idx) => (
                <MemberWithBook key={idx} dto={dto} />
              ))
            ) : (
              <tr>
                <td colSpan="7">No members with active issued books found.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MembersWithBooks;
