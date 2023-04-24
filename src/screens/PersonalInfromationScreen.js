import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { LinkContainer } from 'react-router-bootstrap'
import { Link } from 'react-router-dom'

function PersonalInfromationScreen() {
  return (
    <Container>
      <Navbar bg="light" variant="light">
        <Container>
          <Nav className="me-auto">
            <Link to="/address" className='personal-information'>Address</Link>
            <Link to="/experience" className='personal-information'>Experience</Link>
            <Link to="/education" className='personal-information'>Education</Link>
            <Link to="/skills" className='personal-information'>Skills</Link>
          </Nav>
        </Container>
      </Navbar>
    </Container>
  );
}

export default PersonalInfromationScreen;