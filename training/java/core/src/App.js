import logo from './logo.svg';
import './App.css';
import BookComponent from './components/BookComponent.js'
import AddBookComponent from './components/AddBookcomponent.js';
import UpdateBookComponent from './components/UpdateBookcomponent.js';
import MemberComponent from './components/MemberComponent.js';
import AddMemberComponent from './components/AddMembercomponent.js';
import UpdateMemberComponent from './components/UpdateMembercomponent.js';
import IssueRecordsComponent from './components/IssueRecordsComponent.js';
import IssueBookComponent from './components/IssueBookComponent.js';
import ReturnBookComponent from './components/ReturnBookComponent.js';
import OverdueBookscomponent from './components/OverdueBookscomponent.js';
import IssueMembercomponent from './components/IssueMembercomponent.js';
import CountpercategoryComponent from './components/CountpercategoryComponent.js';
import Homepage from './components/Homepage.js';
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Reports from './components/Reports.js';
import Login from './components/Login.js';
import axios from 'axios';
import { useEffect, useState } from 'react';
function App() {
  // const[token,settoken]=useState('');

  // const handleSubmit = async () => {

  //   try {
  //     const response = await axios.get("http://localhost:8095/Login/csrftoken", { withCredentials: true });
  //     settoken(response.data.token);
  //   }
  //   catch(err){
  //     console.log("error in fetching token",err);

  //   }
  // }
  // useEffect(()=>{handleSubmit()},[])
 


  return (
    <div className="App">
        {/* <BookComponent />
        <AddBookComponent/>
        <UpdateBookComponent/> */}
        {/* <MemberComponent/>
        <AddMemberComponent/>
        <UpdateMemberComponent/> */}
        {/* <IssueRecordsComponent/>
        <IssueBookComponent/>
        <ReturnBookComponent/> */}
        {/* <OverdueBookscomponent/>
        <IssueMembercomponent/>
        <IssueBookComponent/>
        <CountpercategoryComponent/> */}
        


        
      <Routes>
        <Route path="/login" element={<Login />}/>
        <Route path="/" element={<Homepage />}/>
<Route path="/home" element={<Homepage />}/>
        <Route path="/addbook" element={<AddBookComponent />}/>
        <Route path="/updatebook" element={<UpdateBookComponent />}/>
        <Route path="/viewbooks" element={<BookComponent />} />
        <Route path='/addmember' element={<AddMemberComponent />}/>
        <Route path='/updatemember' element={<UpdateMemberComponent />}/>
        <Route path='/viewallmembers' element={<MemberComponent />}/>
        <Route path='/Reports' element={<Reports/>}/>
        <Route path='/issuerecords' element={<IssueRecordsComponent/>}/>
        <Route path='/issuebook' element={<IssueBookComponent />}/>
        <Route path='/returnbook' element={<ReturnBookComponent />}/>
        {/* <Route path="/" element={<Homepage token={token}/>}/> */}
        {/* <Route path="/home" element={<Homepage token={token}/>}/>
        <Route path="/addbook" element={<AddBookComponent token={token}/>}/>
        <Route path="/updatebook" element={<UpdateBookComponent token={token}/>}/>
        <Route path="/viewbooks" element={<BookComponent token={token}/>} />
        <Route path='/addmember' element={<AddMemberComponent token={token}/>}/>
        <Route path='/updatemember' element={<UpdateMemberComponent token={token}/>}/>
        <Route path='/viewallmembers' element={<MemberComponent token={token}/>}/>
        <Route path='/Reports' element={<Reports/>}/>
        <Route path='/issuerecords' element={<IssueRecordsComponent/>}/>
        <Route path='/issuebook' element={<IssueBookComponent token={token}/>}/>
        <Route path='/returnbook' element={<ReturnBookComponent token={token}/>}/> */}
        <Route path='/overduebooks' element={<OverdueBookscomponent/>}/>
        <Route path='/issuemembers' element={<IssueMembercomponent/>}/>
        <Route path='/countpercategory' element={<CountpercategoryComponent/>}/>

        

        
      </Routes>
      

        




    </div>
  );
}

export default App;
