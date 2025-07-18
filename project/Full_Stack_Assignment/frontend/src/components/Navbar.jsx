import React from "react";
import { useAuth } from "../context/AuthContext";
import {
  Nav,
  Navbar as BootstrapNavbar,
  Container,
  Button,
} from "react-bootstrap";
import { Link } from "react-router";

const Navbar = () => {
  const { isAuthenticated, currentUser, logout } = useAuth();

  return (
    <BootstrapNavbar bg="dark" variant="dark" expand="lg" className="mb-3">
      <Container>
        <BootstrapNavbar.Brand as={Link} to="/">
          E-Commerce App
        </BootstrapNavbar.Brand>
        <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
        <BootstrapNavbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">
              Home
            </Nav.Link>
            {isAuthenticated && currentUser?.role === "ROLE_MANAGER" && (
              <>
                <Nav.Link as={Link} to="/product-approval">
                  Product dashboard
                </Nav.Link>
                <Nav.Link as={Link} to="/category/dashboard">
                  Category dashboard
                </Nav.Link>
                <Nav.Link as={Link} to="/categorymapping/dashboard">
                  Category Mapping dashboard
                </Nav.Link>
              </>
            )}

            {isAuthenticated && currentUser?.role === "ROLE_CATEGORY_EXE" && (
              <>
                <Nav.Link as={Link} to="/category/dashboard">
                  Category dashboard
                </Nav.Link>
                <Nav.Link as={Link} to="/categorymapping/dashboard">
                  Category Mapping dashboard
                </Nav.Link>
              </>
            )}

            {isAuthenticated && currentUser?.role === "ROLE_PRODUCT_EXE" && (
              <>
                <Nav.Link as={Link} to="/product-requests">
                  Product dashboard
                </Nav.Link>
              </>
            )}

            <>
              <Nav.Link as={Link} to="/orders">
                Orders
              </Nav.Link>
            </>
          </Nav>

          <Nav>
            {!isAuthenticated ? (
              <>
                <Nav.Link as={Link} to="/login">
                  Login
                </Nav.Link>
                <Nav.Link as={Link} to="/signup">
                  Sign Up
                </Nav.Link>
              </>
            ) : (
              <>
                <BootstrapNavbar.Text className="me-3">
                  Welcome, {currentUser?.fullName || currentUser?.username}
                </BootstrapNavbar.Text>

                {isAuthenticated && (
                  <Nav.Link
                    as={Link}
                    to="/cart"
                    className="position-relative me-3"
                  >
                    <i
                      className="bi bi-cart"
                      style={{ fontSize: "1.25rem" }}
                    ></i>
                  </Nav.Link>
                )}
                <Button variant="outline-light" size="sm" onClick={logout}>
                  Logout
                </Button>
              </>
            )}
          </Nav>
        </BootstrapNavbar.Collapse>
      </Container>
    </BootstrapNavbar>
  );
};

export default Navbar;
