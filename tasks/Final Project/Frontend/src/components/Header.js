import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Navbar, Nav, Container, NavDropdown} from 'react-bootstrap'
// row is to included in above
import { LinkContainer } from 'react-router-bootstrap'
import { logout } from '../actions/userActions'
import SearchBox from './SearchBox'

function Header() {

    const userLogin = useSelector(state => state.userLogin)
    const { userInfo } = userLogin
    const dispatch = useDispatch()

    const logoutHandler = () => (
      dispatch(logout())
    )

  return (
    <header>
      <Navbar bg="danger" variant='dark' expand="lg" collapseOnSelect>
      <Container>
        <LinkContainer to='/'>
          <Navbar.Brand href="/">BookWorld</Navbar.Brand>
        </LinkContainer>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <SearchBox />
          <Nav className="mr-auto">
            { userInfo && 
            <LinkContainer to='/wishlist'>
              <Nav.Link href="/wishlist"><i className="fas fa-archive"></i>Wishlist</Nav.Link>
            </LinkContainer>
            }

              {userInfo ? (
                <NavDropdown title={userInfo.name} id='username'>
                    <LinkContainer to='/profile'>
                      <NavDropdown.Item>Profile</NavDropdown.Item>
                    </LinkContainer>

                    <NavDropdown.Item onClick={logoutHandler}>Logout</NavDropdown.Item>
                </NavDropdown>
              ): (
                <LinkContainer to='/login'>
            <Nav.Link href="/login"><i className="fas fa-user"></i>Login</Nav.Link> 
            </LinkContainer>
              )}

              {userInfo && userInfo.isAdmin && (
                  <NavDropdown title='Admin' id='adminmenue'>
                    <LinkContainer to='/admin/userlist'>
                      <NavDropdown.Item>Users</NavDropdown.Item>
                    </LinkContainer>

                    <LinkContainer to='/admin/movielist'>
                      <NavDropdown.Item>Movies</NavDropdown.Item>
                    </LinkContainer>

                    <LinkContainer to='/admin/bookinglist'>
                      <NavDropdown.Item>Booking</NavDropdown.Item>
                    </LinkContainer>
              </NavDropdown>
              )}
            

            
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
    </header>
  )
}

export default Header
