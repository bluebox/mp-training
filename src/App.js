import Header from './components/Header';
import Footer from './components/Footer';
import {Container} from 'react-bootstrap';
import HomeScreen from './screens/HomeScreen';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import JobPostScreen from './screens/JobPostScreen';
import MyJobsScreen from './screens/MyJobsScreen';
import LoginScreen from './screens/LoginScreen';
import RegisterScreen from './screens/RegisterScreen';
import UserProfileScreen from './screens/UserProfileScreen';
import AddressScreen from './screens/AddressScreen'
import ExperienceScreen from './screens/ExperienceScreen'
import EducationScreen from './screens/EducationScreen'
import EmployerRegisterScreen from './screens/EmployerRegisterScreen'
import CompanyHomeScreen from './screens/CompanyHomeScreen';
import CompanyLoginScreen from './screens/CompanyLoginScreen'
import CompanyJobPostScreen from './screens/CompanyJobPostScreen'
import SkillsScreen from './screens/SkillsScreen';
import CandidateDetailsScreen from './screens/CandidateDetailsScreen';
import CompanyProfileScreen from './screens/CompanyProfileScreen'
import CompanyDetailedProfileScreen from './screens/CompanyDetailedProfileScreen'
import NewAddressForm from './forms/NewAddressForm';
import NewEducationForm from './forms/NewEducationForm';
import NewExperienceForm from './forms/NewExperienceForm';
import NewJobPostForm from './forms/NewJobPostForm';
import NewEditAddressForm from './editForms/NewEditAddressForm';
import NewEditEducationForm from './editForms/NewEditEducationForm';
import NewEditExperienceForm from './editForms/NewEditExperienceForm';
import NewEditCompanyJobPost from './editForms/NewEditCompanyJobPost';
import FilterByCompanyScreen from './screens/FilterByCompanyScreen';
import FilterByLocationScreen from './screens/FilterByLocationScreen';

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <div>
      <main className='py-3'>
        <>
          <Routes>
            <Route path="/" element={<HomeScreen/>}/>
            <Route path="/home-screen" element={<CompanyHomeScreen/>}/>
            <Route path="/login" element={<LoginScreen/>}/>
            <Route path="/login-employer" element={<CompanyLoginScreen/>}/>
            <Route path="/register" element={<RegisterScreen/>}/>
            <Route path="/profile" element={<UserProfileScreen/>}/>
            <Route path="/company-detailed-profile" element={<CompanyDetailedProfileScreen/>}/>
            <Route path="/company-profile" element={<CompanyProfileScreen/>}/>
            <Route path="/address" element={<AddressScreen/>}/>
            <Route path="/addAddress" element={<NewAddressForm/>}/>
            <Route path="/editAddress" element={<NewEditAddressForm/>}/>
            <Route path="/experience" element={<ExperienceScreen/>}/>
            <Route path="/addExperience" element={<NewExperienceForm/>}/>
            <Route path="/editExperience" element={<NewEditExperienceForm/>}/>
            <Route path="/education" element={<EducationScreen/>}/>
            <Route path="/addEducation" element={<NewEducationForm/>}/>
            <Route path="/editEducation" element={<NewEditEducationForm/>}/>
            <Route path="/add-job-post" element={<NewJobPostForm/>}/>
            <Route path="/edit-job-post" element={<NewEditCompanyJobPost/>}/>
            <Route path="/register-employer" element={<EmployerRegisterScreen/>}/>
            <Route path="/post/:id" element={<JobPostScreen/>}/>
            <Route path="/company-post/:id" element={<CompanyJobPostScreen/>}/>
            <Route path="/job-cart" element={<MyJobsScreen/>}/>
            <Route path="/skills" element={<SkillsScreen/>}/>
            <Route path="/candidate-details" element={<CandidateDetailsScreen/>} />
            <Route path="/filter" element={<FilterByCompanyScreen/>} />
            <Route path="/filtering" element={<FilterByLocationScreen/>} />
          </Routes>
          </>
      </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
