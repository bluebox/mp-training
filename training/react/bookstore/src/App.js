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
function App() {
  return (
    <BrowserRouter>
      
        <Routes>
          <Route path="/" element={< Adminlogin  />} />
          <Route path="/CustomerLogInpage" element={<CustomerLogInpage  />}/>
          <Route path="/AuthorLogInpage" element={<AuthorLogInpage  />}/>

          <Route path="/AdminHomePage" element={<Protect>< AdminHomePage  /></Protect>} />
          <Route path='/CustomerRelated' element={<Protect><CustomerRelated /></Protect>} />
          <Route path='/BooksPage' element={<Protect><BooksPage /></Protect>} />
          <Route path='/Authorrelatedpage' element={<Protect><Authorrelatedpage /></Protect>} />


          <Route path="/CustomerPage" element={<Protect><CustomerPage  /></Protect>}/>
          <Route path="/MyOrders" element={<Protect><MyOrders  /></Protect>}/>

          

          <Route path="/AuthorsPage" element={<Protect><AuthorsPage  /></Protect>}/>
          <Route path="/Handlebookedit" element={<Protect><Handlebookedit  /></Protect>}/>

          <Route path="/BookAddingForm" element={<BookAddingForm  />}/>
          <Route path='/CustomerRegisterPage' element={<CustomerRegisterPage />} />
          <Route path="/AuthorRegisterPage" element={<AuthorRegisterPage  />}/>
          

          <Route path="/*" element={<Adminlogin  />}/>
          
        
          
     </Routes>
    </BrowserRouter>
  );
}

export default App;



