import React from "react";
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {
  return (
    <>
    <Navbar  bg="dark" expand="lg" variant="dark">
  <Navbar.Brand href="/library">Library Management System</Navbar.Brand>
  <Navbar.Toggle aria-controls="basic-navbar-nav" />
  <Navbar.Collapse id="basic-navbar-nav">
    <Nav className="justify-content-end w-100">
      <Nav.Link href="/library/books/view">Books</Nav.Link>
      <Nav.Link href="/library/members/view">Members</Nav.Link>
      <Nav.Link href="/library/issues/allIssues">Issue/Return</Nav.Link>
      <Nav.Link href="/library/reports">Reports</Nav.Link>
    </Nav>
  </Navbar.Collapse>
</Navbar>
  </>
  );
};

export default Header;
