import React, { useContext, useEffect } from 'react'
import {Container, Navbar, Nav, NavDropdown} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from '../actions/userActions';
import SearchBox from './SearchBox';
import { UsernameContext } from '../App';

function Header() {
  const userLogin = useSelector(state=>state.userLogin)
  const {userInfo} = userLogin
  const dispatch = useDispatch()
  const logoutHandler = () => {
    setUsername('')
    dispatch(logout())
  }
  const [username, setUsername] = useContext(UsernameContext)
  return (
    
    <header>
    <Navbar bg="info" variant='dark' expand="lg" collapseOnSelect>
        <LinkContainer to="/">
        <Navbar.Brand className="ms-5 me-0 pe-0">NextStore</Navbar.Brand>
        </LinkContainer>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="m-auto"
            style={{ maxHeight: '100px', display: "flex"}}
            navbarScroll
          >
             <SearchBox/>

          </Nav>
          <Nav>
          {(!userInfo || (userInfo && !userInfo.isAdmin))&&
            <LinkContainer to="/cart">
              
            <Nav.Link><i className='fas fa-shopping-cart'></i>Cart</Nav.Link>
          </LinkContainer>
          }
          {userInfo && userInfo.username ? (
              <NavDropdown 
                title={username?username:userInfo.first_name?userInfo.first_name + " " + userInfo.last_name:userInfo.username} id='username'>
                <LinkContainer to='/profile'>
                  <NavDropdown.Item>
                    Profile
                  </NavDropdown.Item>
                  </LinkContainer>
                  <NavDropdown.Item onClick={logoutHandler}>Logout</NavDropdown.Item>
              </NavDropdown>
            ): (
              <LinkContainer to="/login" className="me-2">
              <Nav.Link><i className="fas fa-user"></i>Login</Nav.Link>
              </LinkContainer>
            )}

            {userInfo && userInfo.isAdmin && (
              <NavDropdown title='Actions' id='adminMenu'>
                <LinkContainer to='/admin/userlist'>
                  <NavDropdown.Item>
                    Users
                  </NavDropdown.Item>
                  </LinkContainer>
                  <LinkContainer to='/admin/productlist'>
                  <NavDropdown.Item>
                    Products
                  </NavDropdown.Item>
                  </LinkContainer>
                  <LinkContainer to='/admin/orders'>
                  <NavDropdown.Item>
                    Orders
                  </NavDropdown.Item>
                  </LinkContainer>
              </NavDropdown>
            )}
          </Nav>
        </Navbar.Collapse>
    </Navbar>


    
    </header>
  )
}

export default Header