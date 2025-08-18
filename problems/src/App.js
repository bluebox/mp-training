import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Home';
import BookHome from './BookHome';
import MembersHome from './MembersHome';
import IssuerecordHome from './IssueRecordHome';
import RepotsHome from './RepotsHome';
import ViewBooks from './ViewBooks';
import AddBook from './AddBook';
import UpdateBook from './UpdateBook';
import ViewMembers from './ViewMembers';
import AddMember from './AddMember';
import UpdateMember from './UpdateMember';
import ViewIssues from './ViewIssues';
import CreateIssue from './CreateIssue';
import Return  from './Return';
import OverdueBooks  from './OverdueBooks';
import BooksPerCategory   from './BooksPerCategory';
import ActiveBookMembers from './ActiveBookMembers';

function App() {
  return (
   <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Bookhome" element={<BookHome />} />
        <Route path="/Bookhome/viewbooks" element={<ViewBooks />} />
        <Route path="/Bookhome/AddBook" element={<AddBook />} />
        <Route path="/Bookhome/UpdateBook" element={<UpdateBook />} />
          <Route path="/Memberhome" element={<MembersHome />} />
          <Route path="/Memberhome/AddMember" element={<AddMember />} />
          <Route path="/Memberhome/ViewMembers" element={<ViewMembers />} />
          <Route path="/Memberhome/UpdateMember" element={<UpdateMember />} />
            <Route path="/IssueRecordshome" element={<IssuerecordHome />} />
             <Route path="/IssueRecordshome/ViewIssues" element={<ViewIssues />} />
              <Route path="/IssueRecordshome/CreateIssue" element={<CreateIssue />} />
               <Route path="/IssueRecordshome/Return" element={<Return />} />
              <Route path="/Reportshome" element={<RepotsHome />} />  
                <Route path="/Reportshome/OverdueBooks" element={<OverdueBooks />} />  
                <Route path="/Reportshome/BooksPerCategory" element={<BooksPerCategory />} />  
                <Route path="/Reportshome/ActiveBookMembers" element={<ActiveBookMembers />} />   
      </Routes>
    </Router>
  );
}

export default App;
