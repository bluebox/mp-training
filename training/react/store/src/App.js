import './App.css';
import AdminHomePage from './Pages/adminpages/AdminHomePage';
import { BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom';
import CustomerRegisterPage from './forms/customerRegistrationForm';
import AuthorRegisterPage from './forms/AuthorsRegistrationPage';
import BookAddingForm from './forms/bookForm'
import AuthorLogInpage from './forms/authorLoginPage';
import BooksPage from './Pages/adminpages/booksPage'
import CustomerLogInpage from "./forms/customerLogInpage"
import CustomerPage from "./Pages/Customer/customersPage"
import MyOrders from "./Pages/Customer/myOrders"
import AuthorsPage from './Pages/Authors/AuthorsPage';
import Handlebookedit from './Pages/Authors/handileEdit';
import Adminlogin from './forms/adminloginform';
import CustomerRelated from './Pages/adminpages/CustomerRelate'
import Authorrelatedpage from './Pages/adminpages/Authorrelatedpage'
import Protect from './protectedroots'
import MyProfile from './Pages/Customer/myprofile';
import Practice from './practice';
import MyProfileAuthors from './Pages/Authors/myprofile';
function App() {
  return (
    <BrowserRouter>
      
        <Routes>
          <Route path="/" element={< Adminlogin  />} />
          <Route path="/CustomerLogInpage" element={<CustomerLogInpage  />}/>
          <Route path="/AuthorLogInpage" element={<AuthorLogInpage  />}/>

          <Route path="/AdminHomePage" element={< AdminHomePage  />} />
          <Route path='/CustomerRelated' element={<CustomerRelated />} />
          <Route path='/BooksPage' element={<BooksPage />} />
          <Route path='/Authorrelatedpage' element={<Authorrelatedpage />} />


          {/* <Route path="/CustomerPage" element={<Protect><CustomerPage  /></Protect>}/>
          <Route path="/MyOrders" element={<Protect><MyOrders  /></Protect>}/>
          <Route path="/MyProfile" element={<Protect><MyProfile  /></Protect>}/> */}
          
          <Route path="/CustomerPage" element={<CustomerPage  />}/>
          <Route path="/MyOrders" element={<MyOrders  />}/>
          <Route path="/MyProfile" element={<MyProfile  />}/>
          

          

          <Route path="/AuthorsPage" element={<AuthorsPage  />}/>
          <Route path="/Handlebookedit" element={<Handlebookedit  />}/>
          <Route path="/MyProfileAuthors" element={<MyProfileAuthors  />}/>

          <Route path="/BookAddingForm" element={<BookAddingForm  />}/>
          <Route path='/CustomerRegisterPage' element={<CustomerRegisterPage />} />
          <Route path="/AuthorRegisterPage" element={<AuthorRegisterPage  />}/>
          
          <Route path="/Practice" element={<Practice  />}/>
          <Route path="/*" element={<Adminlogin  />}/>
          
        
          
     </Routes>
    </BrowserRouter>
  );
}

export default App;



