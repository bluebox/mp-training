import React from "react";
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';

const ReportsHeader = () => {
  return (
    <>
      <Navbar bg="dark" expand="lg" variant="dark">
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="justify-content-end w-100">
            <Nav.Link href="/library/reports/overdueRecords">Overdue Books</Nav.Link>
            <Nav.Link href="/library/reports/categoryCount">Category Count</Nav.Link>
            <Nav.Link href="/library/reports/activeIssuedRecords">Active Issued</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </>
  );
};

export default ReportsHeader;
