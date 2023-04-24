import React, { Fragment } from 'react'
import {Navbar, Nav, Container, NavDropdown, Button} from 'react-bootstrap';
import {Link, Navigate} from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logoutCompany } from '../actions/companyActions';
import { logout } from '../actions/userActions'
import {  useNavigate } from 'react-router-dom';
import SearchBox from './SearchBox'

function Header() {

  const userLogin = useSelector(state => state.userLogin)
  const companyLogin = useSelector(state => state.companyLogin)
  const {userInfo} = userLogin
  const {companyInfo} = companyLogin
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const userLogoutHandler = () => {
    navigate('/login')
    dispatch(logout())

  }

  const companyLogoutHandler = () => {
    navigate('/login')
    dispatch(logoutCompany())

  }

  const loginHandler = () => {
    navigate('/login')
  }

  const addJobPost = () => {
    navigate('/add-job-post')
  }
  return (
    <header>
    <Navbar bg="dark" variant='dark' collapseOnSelect style={{padding: '0rem'}}>
      <Container fluid className='d-flex justify-content-between'>
        <Fragment className='d-flex justify-content-between'>
        {companyInfo ? (
            <Link to= '/home-screen' className='link'>
            <h2 className='title'>Job Mart</h2>
          </Link>
        ):  (<Link to= '/' className='link'>
              <h2 className='title'>Job Mart</h2>
            </Link>)
        }

          {
            userInfo ? <SearchBox />
            : <></>
          }
          </Fragment>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav" className='justify-content-end'>
          

          <Nav style={{paddingRight: '1rem'}}>
    
            {userInfo ? (
              <>
              <Link to='/job-cart' className='link'>
                <div className='cart sizing'><i className='fas fa-briefcase'></i> My Jobs</div>
              </Link>
              <NavDropdown title= {<><i className='fas fa-user'></i> {userInfo.name}</>} id='username'>
                <NavDropdown.Item ><Link to='/profile' className='link-dropdown'><div>User Profile</div></Link></NavDropdown.Item>
                <NavDropdown.Item ><Link to='/address' className='link-dropdown'><div>Personal Information</div></Link></NavDropdown.Item>
              </NavDropdown>
              <button onClick={userLogoutHandler} className='button-component'>Logout</button>
              </>
            ):  companyInfo ? 
            (
              <>
              <NavDropdown title={companyInfo.name} id='username' style={{marginRight: '1rem'}}>
                <NavDropdown.Item ><Link to='/company-profile' className='link-dropdown'><div>Profile</div></Link></NavDropdown.Item>
                <NavDropdown.Item ><Link to='/company-detailed-profile' className='link-dropdown'><div>Company Details</div></Link></NavDropdown.Item>
              </NavDropdown>
              <button onClick={addJobPost} className='button-component' style={{marginRight: '1rem'}}>Add Job Post</button>
              <button onClick={companyLogoutHandler} className='button-component'>Logout</button>
              </>
            )
            : (
              <>
              <NavDropdown title='Register' id='employer' size='lg'>
                <NavDropdown.Item ><Link to='/register?redirect=/?' className='link-dropdown' ><div>For Job Seeker</div></Link></NavDropdown.Item>
                <NavDropdown.Item ><Link to='/register-employer?' className='link-dropdown' ><div>For Employer</div></Link></NavDropdown.Item>
              </NavDropdown>
              
              <button onClick={loginHandler} className='button-component'>Login</button>
              </>
            )}

          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
    </header>
  )
}

export default Header

