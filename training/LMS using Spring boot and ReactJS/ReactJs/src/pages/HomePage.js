import React from 'react'

function HomePage() {
  return (
    <div>
      <h1>Library Management System</h1>
      <h2>Main Menu</h2>
      <div className="button-row">
        <form action="addBook" method="get">
          <button type="submit">Add Book</button>
        </form>
        <form action="viewBooks" method="get">
          <button type="submit">View Books</button>
        </form>
        <form action="addMember" method="get">
          <button type="submit">Add Member</button>
        </form>
        <form action="viewMembers" method="get">
          <button type="submit">View Members</button>
        </form>
      </div>

      <div className="button-row">
        <form action="issueBook" method="get">
          <button type="submit">Issue Book</button>
        </form>
        <form action="returnBook" method="get">
          <button type="submit">Return Book</button>
        </form>
        <form action="viewIssuedRecords" method="get">
          <button type="submit">View Issue Records</button>
        </form>
        <form action="reports" method="get">
          <button type="submit">Reports</button>
        </form>
      </div>
    </div>
  )
}

export default HomePage
