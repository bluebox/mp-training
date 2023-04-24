import {createStore, combineReducers, applyMiddleware} from 'redux'
import thunk from 'redux-thunk';
import {composeWithDevTools} from 'redux-devtools-extension'
import { jobPostsListReducer , jobPostDetailsReducer, addJobPost, editJobPost} from './reducers/postsReducers';
import { cartReducer, addCartItems, getCartItems, deleteCartItem } from './reducers/jobCartReducers';
import {userLoginReducer, userRegister, userDetailsReducer, userUpdateProfileReducer, getAppliedCandidatesForAPost} from './reducers/userReducers';
import {companyLoginReducer, companyRegister, companyDetailsReducer, companyUpdateProfileReducer, companyDetailProfileReducer, companyProfileUpdateReducer, companyListReducer, companiesListByFilter, companiesListByFilterByCompany} from './reducers/companyReducers';
import { addAddress, getAddress, editAddress, deleteAddress } from './reducers/addressReducers';
import { addExperience, getExperience, editExperience, deleteExperience } from './reducers/experienceReducers';
import { addEducation, getEducation, editEducation, deleteEducation} from './reducers/educationReducers';
import { companyJobPostsListReducer, companyJobPostDetailsReducer, appliedCandaidatesDetails} from './reducers/companyPostsReducers';
import { addSkill, getSkills, deleteSkill, getSkillsList} from './reducers/skillsReducers';


const reducer = combineReducers({
    addPost: addJobPost,
    editPost: editJobPost,
    jobPostsList: jobPostsListReducer,
    companyJobPostsList: companyJobPostsListReducer,
    jobPostDetails: jobPostDetailsReducer,
    companyJobPostDetails: companyJobPostDetailsReducer,
    jobCart: cartReducer,
    appliedCandidatesForCompany:getAppliedCandidatesForAPost,
    userLogin: userLoginReducer,
    userRegister: userRegister,
    userDetails: userDetailsReducer,
    userUpdateProfile: userUpdateProfileReducer,
    companyLogin:companyLoginReducer,
    companyRegister: companyRegister,
    companyDetails: companyDetailsReducer,
    companyUpdateProfile: companyUpdateProfileReducer,
    companyDetailedProfile: companyDetailProfileReducer,
    companyDetailsProfileUpdate: companyProfileUpdateReducer,
    userAddress: addAddress,
    getUserAddress: getAddress,
    userExperience: addExperience,
    getUserExperience: getExperience,
    userEducation: addEducation,
    getUserEducation: getEducation,
    userJobs: addCartItems,
    getUserJobs: getCartItems,
    deleteApplication: deleteCartItem,
    updateAddress: editAddress,
    delAddress: deleteAddress,
    updateEducation: editEducation,
    delEducation: deleteEducation,
    updateExperience: editExperience,
    delExperience: deleteExperience,
    getUserSkills: getSkills,
    addSkill: addSkill,
    delSkill: deleteSkill,
    getSkillsList: getSkillsList,
    appliedCandaidatesDetails: appliedCandaidatesDetails,
    companyList: companyListReducer,
    companiesListByFilter: companiesListByFilter,
    companiesListByLocation: companiesListByFilterByCompany,
})

    
const cartItemsFromStorage = localStorage.getItem('jobCartItems') ? 
                JSON.parse(localStorage.getItem('jobCartItems')) : [];


const userInfoFromStorage = localStorage.getItem('userInfo') ? 
                JSON.parse(localStorage.getItem('userInfo')) : null;

const companyInfoFromStorage = localStorage.getItem('companyInfo') ?
                JSON.parse(localStorage.getItem('companyInfo')) : null;

    
const initalState = {
    jobCart:{cartItems: cartItemsFromStorage},
    userLogin:{userInfo: userInfoFromStorage},
    companyLogin:{companyInfo: companyInfoFromStorage}
}

const middleware = [thunk]

const store = createStore(reducer, initalState, 
    composeWithDevTools(applyMiddleware(...middleware)))

export default store 