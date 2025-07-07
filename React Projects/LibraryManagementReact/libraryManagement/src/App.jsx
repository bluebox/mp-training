import { useState } from 'react'
import ReactDOM from "react-dom/client";
import {BrowserRouter , Routes , Route} from "react-router-dom";
import AddBookForm from './pages/AddBookForm'

import './App.css'
import Home from './pages/Home';
import ViewAllBooks from './pages/ViewAllBooks';
import ViewBookCategory from './pages/ViewBookCategory';
import AddMemberForm from './pages/AddMemberForm';
import ViewAllMembers from './pages/ViewAllMembers';
import IssueForm from './pages/IssueForm';
import ViewIssuedBooks from './pages/ViewIssuedBooks';
import ActiveIssuedBooks from './pages/ActiveIssuedBooks';
import ViewOverdueBook from './pages/ViewOverdueBook';
import ReturnBook from './pages/ReturnBook';

function App() {

  return (
    <BrowserRouter>
      <Routes>

        <Route path = "/" element={<Home />}>
            {/* <Route path="add" element={<AddBookForm />}/> */}
        </Route>

        <Route path="edit/:bookId" element={<AddBookForm isEditMode = {true}/>} />
        <Route path="edit-member/:memberId" element={<AddMemberForm isEditMode = {true}/>} />
        
        <Route path = "/books">
            <Route path="add" element={<AddBookForm />}/>
            <Route path="list" element={<ViewAllBooks />}/>
            <Route path="category" element={<ViewBookCategory />}/>
        </Route>

        <Route path = "/members">
            <Route path="add" element={<AddMemberForm />}/>

            <Route path="update" element={<AddMemberForm/>} />
            <Route path="list" element={<ViewAllMembers />}/>
        </Route>

        <Route path="/issues" >
          <Route path="issue" element = {<IssueForm />} />
          <Route path="return" element = {<ReturnBook />} />
          <Route path="list" element = {<ViewIssuedBooks/>} />
          <Route path="list/active" element = {<ActiveIssuedBooks />} />
          <Route path="overdue-books" element = {<ViewOverdueBook />} />
        </Route>

      </Routes>
    </BrowserRouter>
  )
}

export default App
