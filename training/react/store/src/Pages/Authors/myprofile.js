import React, { useEffect, useState } from "react";
import { data, useLocation, useNavigate } from "react-router-dom";
import './authors.css';


function MyProfileAuthors() {
  const location = useLocation();
  const navigate = useNavigate();
  const username = location.state
  const [data, setData] = useState({});
  const [state, setState] = useState("Job details")
  const [isExpanded, setisExpanded] = useState(false);
  const [clicked,setClicked] = useState(false);
  const [showProfilePic, setShowProfilePic] = useState(false);
  const loadProfileData = async () => {
    try {

      const response = await fetch("http://127.0.0.1:8000/store/CustomUserView/" + username)
      const data = await response.json();

      setData(data);

    } catch (error) {
      console.error("Error loading profile data:", error);
    }

  };

  useEffect(() => {
    loadProfileData();
  }, [username]);

  const handleLogOut = (e) => {
    const confirmation = window.confirm("Are you sure you want to log out?");
    if (!confirmation) {
      e.preventDefault();
      return;
    }
   const response= fetch("http://127.0.1:8000/store/logout/"+localStorage.getItem('id'))
  navigate("/")
  }






  return (<>   <nav className="nav-link">
    <button className='nav-button' onClick={() => navigate('/AuthorsPage', { state: username })}>Home</button>
    
      <button className='nav-button' onClick={() => navigate('/AuthorRegisterPage',{state:{'username':username}})}>Edit profile</button>
      <button className='nav-button' style={{marginLeft: 'auto' }}  onClick={handleLogOut}>LogOut</button>
  </nav>
      <div className="my-profile" id="test_requests">
        <div>
          <img src="/image.jpg" alt="Profile" onClick={()=>setShowProfilePic(true)} className="profile-image" />
          <h1 style={{ textAlign: 'center' }}>{username}</h1></div>
        <div className="my-profile-details">

          <p><strong>Name:</strong> {data.name}</p>
          <p><strong>Age:</strong> {data.age}</p>
          <p><strong>Email:</strong> {data.email}</p>

          <p><strong>role:</strong> {data.role}</p>
          <p><strong>Gender:</strong> {data.gender}</p>
          <p><strong>Address:</strong> {data.address}</p>

        </div >
        <button title="Edit" className="edit-profile-button" onClick={() => navigate('/AuthorRegisterPage', { state: { 'username': username ,'from':'author' } })}>✏️
          
        </button>

      </div>
      <div className="my-profile-bottom-list" >
        <div>
         <button className="my-profile-bottom-list-button" onClick={() => setState('Job details')}><a href="#edit_requests" data-bs-toggle="tab">Job details</a></button>
         {(state === 'Job details') ? <h1 style={{ margin: '0px' }}>___________</h1> : <></>  }
         </div>
         <div>
         <button className="my-profile-bottom-list-button" onClick={() => setState('Name Change Request')}><a href="#edit_requests" data-bs-toggle="tab">Name Change Request</a></button>
         {(state === 'Name Change Request')? <h1 style={{ margin:'0px'}}>___________</h1> : <></>}
         </div>
         <div>
         <button className="my-profile-bottom-list-button" onClick={() => setState('My documents')}> <a href="#edit_requests" data-bs-toggle="tab">My documents</a></button>
        {(state === 'My documents') ? <h1 style={{ margin: '0px' }}>___________</h1> : <></>}
         </div><div>
          <button className="my-profile-bottom-list-button" onClick={() => setState('Personal Details')}> <a href="#edit_requests" data-bs-toggle="tab">Personal Details</a></button>
 {(state === 'Personal Details') ? <h1 style={{ margin: '0px' }}>___________</h1> : <></>}
 </div> </div>
  <div className="my-profile">
   {state === "Job details" && 
        <div className="my-profile-bottom-list-content">
          <h2>Job Details</h2>
          <p><strong>Your role</strong> {data.role}</p>
        </div>
}
     {(state === "Name Change Request") && (
        <div className="my-profile-bottom-list-content">
          <h2>Name Change Request</h2>
          <p><strong>Name Change Request</strong> {data.role}</p>
        </div>
      )}
       {(state === "My documents") && (
        <div className="my-profile-bottom-list-content">
          <h2>My documents</h2>
          <p><strong>My documents</strong> {data.role}</p>
        </div>
      )}
       {(state === "Personal Details") && (
        <div className="my-profile-bottom-list-content">
          <h2>Personal Details</h2>
          <p><strong>Personal Details</strong> {data.role}</p>
        </div>
      )}</div>

  <div className="my-profile" style={{flexDirection: 'column' }}>
 <button title='click to expand/collase' style={{textAlign:'left', cursor:'pointer'}} onClick={()=>setisExpanded(!isExpanded) } aria-expanded={isExpanded} aria-controls="expandable-content" >
  {isExpanded ? '▼ Collapse' : ' ▶ Expand'}
</button><br/>

<div id="expandable-content" hidden={!isExpanded} >
  <br/>
  <p>the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended
  the content is shown only when it is extended</p>
</div>
    </div> 

{ <div onClick={() =>setClicked(true)} className="my-profile">
  <p9 style={{testAlign:'center'}}>{data.name}</p9>

  </div>}
  {showProfilePic && <div className="Clicked-outside" style={{zIndex: '1000'}}>
   <div >
    <img src="/image.jpg" alt="Profile" />
    <button title='Close' onClick={() => setShowProfilePic(false)} className="Close-button-onImage" >X</button>
    </div></div>}
  {clicked && <div className="Clicked-outside">
    <div className="Clicked-inside">
      <button title='Close' onClick={() => setClicked(false)} className="Close-button" >X</button>
    <p>Clicked!</p>
    <p><strong>Name:</strong> {data.name}</p>
          <p><strong>Age:</strong> {data.age}</p>
          <p><strong>Email:</strong> {data.email}</p>

          <p><strong>role:</strong> {data.role}</p>
          <p><strong>Gender:</strong> {data.gender}</p>
          <p><strong>Address:</strong> {data.address}</p>
    </div>
  </div>}
   
  </>
  );
} export default MyProfileAuthors;