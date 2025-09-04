import './App.css'
import AddBooks from './components/AddBooks'
import AddMember from './components/AddMember'
import FooterComponent from './components/FooterComponent'
import HearderComponent from './components/HearderComponent'
import { useNavigate } from 'react-router-dom'
import ListBooksComponent from './components/ListBooksComponent'
import {  Route, Routes } from 'react-router-dom'
import ListMembers from './components/ListMembers'
import UpdateBook from './components/UpdateBook'
import UpdateMember from './components/UpdateMember'
import IssueBooks from './components/IssueBooks'
import IssuedRecords from './components/IssuedRecords'
import Reports from './components/Reports'
import ReturnBook from './components/ReturnBook'
import MemberBooks from './components/MemberBooks'
import BookMembers from './components/BookMembers'
function App() {
  const navigate = useNavigate();
  const addMemberClick = () => {
    navigate(`/addnewmember`);
  };
  const addBookClick = () => {
    navigate(`/addnewbook`);
  };
  const getBooksClick = () => {
    navigate(`/books`);
  };
  const getMembersClick = () => {
    navigate(`/members`);
  };
  const issueBookClick = () => {
    navigate(`/issuebook`);
  }
  const issuedRecords = () => {
    navigate(`/getIssuedrecords`);
  }

  const reports = () => {
    navigate(`/viewReports`);
  }

  const returnBookClick = () => {
    navigate(`/returnbook`);
  }

  const memberBooks = () => {
    navigate(`/memberbooks`);
  }

  const bookMembers = () => {
    navigate(`/bookmembers`);
  }

  return (
    <>
      <div className='fixed-container' style={{overflowY: 'hidden' , overflowX: 'hidden'}}>
        <HearderComponent />
          <div className='row'>
            <div className='col-md-9' style={{ height: '80vh' , overflowY: 'auto' , overflowX: 'hidden'}}>
              <Routes>
                <Route path='/books' element={<ListBooksComponent />}></Route>
                <Route path='/addnewbook' element={<AddBooks />}></Route>
                <Route path='/addnewmember' element={<AddMember />}></Route>
                <Route path='/members' element={<ListMembers />}></Route>
                <Route path='/updatebook/:bookId' element={<UpdateBook />}></Route>
                <Route path='/updatemember/:id' element={<UpdateMember />}></Route>
                <Route path='/issuebook' element={<IssueBooks />}></Route>
                <Route path='/returnbook' element={<ReturnBook />}></Route>
                <Route path="/getIssuedrecords" element = {<IssuedRecords/>}></Route>
                <Route path="/viewReports" element = {<Reports/>}></Route>
                <Route path="/memberbooks" element = {<MemberBooks/>}></Route>
                <Route path="/bookmembers" element = {<BookMembers/>}></Route>
              </Routes>
            </div>
            <div className='col-md-2 d-grid gap-2 my-4'>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={addBookClick} >Add Book</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={getBooksClick} >View Books</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={addMemberClick}>Add Member</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={getMembersClick}>View Members</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={issueBookClick}>Issue Book</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={returnBookClick}>Return Book</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={issuedRecords}>Issued Records</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={reports}>View Reports</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={memberBooks}>Members Books</button>
              <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={bookMembers}>Book Members</button>
            </div>
          </div>
        <FooterComponent />
      </div>
    </>
  )
}

export default App
