import React, { useEffect, useState } from 'react';
import { api } from '../api/api';
import { useNavigate } from 'react-router-dom';
import './StudentDashboard.css';

export default function StudentDashboard() {
  const navigate = useNavigate();
  const [events, setEvents] = useState([]);
  const [activeEvents,setActiveEvents]=useState([]);
  const [registeredEvents, setRegisteredEvents] = useState([]);
  const [cancelledEvents, setCancelledEvents] = useState([]);
  const [attendedEvents, setAttendedEvents] = useState([]);
  const [notAttendedEvents, setNotAttendeEvents] = useState([]);
  const [selectedSection, setSelectedSection] = useState(null);
  const [feedbackEventId, setFeedbackEventId] = useState(null);
  const [feedbackText, setFeedbackText] = useState('');
  const [rating, setRating] = useState(5);

  const userId = localStorage.getItem('userId');
  const role = localStorage.getItem('role');

  useEffect(() => {
    if (!userId || role !== 'USER') {
      alert('Unauthorized access');
      navigate('/');
    } else {
      loadEvents();
    }
  }, [userId, role, navigate]);

  const loadEvents = async () => {
    try {
      const res = await api.get('eventCreation/events');
      setEvents(res.data);
    } catch {
      alert('Failed to load events');
    }
  };

   const fetchActiveEvents = async () => {
    try {
      const res = await api.get('/eventCreation/activeEvents');
      setActiveEvents(res.data);
    } catch {
      alert('Failed to load active events');
    }
   }

   const fetchRegisteredEvents = async (userId) => {
    try {
      const res = await api.get(`/eventCreation/eventsStatus?userId=${userId}&status=${"R"}`);
      setRegisteredEvents(res.data);
    } catch {
      alert('Failed to load registered events');
    }
  };

const fetchCancelledEvents = async (userId) => {
    try {
      const res = await api.get(`eventCreation/eventsStatus?userId=${userId}&status=${"C"}`);
      setCancelledEvents(res.data);
    } catch {
      alert('Failed to load cancelled events');
    }
  };

  const fetchAttendedEvents = async (userId) => {
    try {
      const res = await api.get(`/eventCreation/eventsStatus?userId=${userId}&status=${"A"}`);
      setAttendedEvents(res.data);
    } catch {
      alert('Failed to load attendad events');
    }
  };

  const fetchNotAttendedEvents = async (userId) => {
    try {
      const res = await api.get(`/eventCreation/eventsStatus?userId=${userId}&status=${"N"}`);
      setNotAttendeEvents(res.data);
    } catch {
      alert('Failed to load not attendad events');
    }
  };



  const register = async (eventId) => {
    try {
      await api.post('/eventRegistration/register', {
        userId: Number(userId),
        eventId,
        registration_status: 'R',
        registeredBy: Number(userId),
        registeredAt: new Date().toISOString(),
      });
      alert('Registered successfully');
    } catch {
      alert('Registration failed');
    }
  };

  const cancel = async (eventId) => {
    try {
      await api.put('/eventRegistration/update', {
        userId: Number(userId),
        eventId,
        registration_status: 'C',
        updatedBy: Number(userId),
        updatedAt: new Date().toISOString(),
      });
      alert('Registration cancelled');
    } catch {
      alert('Cancellation failed');
    }
  };


function logout() {
  localStorage.removeItem('role');
  localStorage.removeItem('user_id');

  console.log("Logged out. Items removed from localStorage.");

  window.location.href = '/';
}


  const submitFeedback = async () => {
    if (!feedbackText.trim()) {
      alert('Please write some feedback.');
      return;
    }

    try {
      await api.post('/feedback/add', {
        userId: Number(userId),
        eventId: feedbackEventId,
        rating: Number(rating),
        feedback: feedbackText,
      });
      alert('Feedback submitted');
      setFeedbackEventId(null);
      setFeedbackText('');
      setRating(5);
    } catch {
      alert('Feedback submission failed');
    }
  };

  return (
    <div className="student-dashboard">
      <h1>Welcome Student, User ID: {userId} 
        <span><button onClick={logout} className='btn btn-danger'>Logout</button>
        </span>
      </h1>
      <div className="tabs">
        <button onClick={() => { setSelectedSection('events'); loadEvents(); }}>View All Events</button>
        <button onClick={() => { setSelectedSection('activeEvents'); fetchActiveEvents()}}>Active Events</button>
        <button onClick={() => { setSelectedSection('registeredEvents'); fetchRegisteredEvents(userId)}}>Registered Events</button>
        <button onClick={() => { setSelectedSection('attendedEvents'); fetchAttendedEvents(userId)}}>Attended Events</button>
        <button onClick={() => {setSelectedSection('cancelledEvents'); fetchCancelledEvents(userId)}}>Cancelled Events</button>
        <button onClick={() => {setSelectedSection('notAttendedEvents'); fetchNotAttendedEvents(userId)}}>Not Attended Events</button>
      </div>

      {/* ALL EVENTS */}
      {selectedSection === 'events' && (
        <div>
          <h3>All Events</h3>
          {events.length === 0 ? (
              <p>No events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Organizer</th>
                <th>Start</th>
                <th>End</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {events.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{e.eventOrganizer}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                  <td>{e.event_status === 'A' ? 'ACTIVE' : e.event_status === 'C' ? 'CANCELLED' : 'FINISHED'}</td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

       {/* Active EVENTS */}
      {selectedSection === 'activeEvents' && (
        <div>
          <h3>Active Events</h3>
          {activeEvents.length === 0 ? (
              <p>No Active events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start</th>
                <th>End</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {activeEvents.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                  <td>{e.event_status === 'A' ? 'ACTIVE' : e.event_status === 'C' ? 'CANCELLED' : 'FINISHED'}</td>
                  <td>
                    <button onClick={() => register(e.eventId)}>Register</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

      {/* Registered Events */}
      {selectedSection === 'registeredEvents' && (
        <div>
          <h3>Registred Events</h3>
          {registeredEvents.length === 0 ? (
              <p>No registered events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start</th>
                <th>End</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {registeredEvents.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                   <td>
                      <button className='cancelBtn' onClick={() => cancel(e.eventId)}>Cancel</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

      {/* Cancelled Events */}
      {selectedSection === 'cancelledEvents' && (
        <div>
          <h3>Cancelled Events</h3>
          {cancelledEvents.length === 0 ? (
              <p>No user cancelled events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start</th>
                <th>End</th>
              </tr>
            </thead>
            <tbody>
              {cancelledEvents.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

      {/* Attended Events*/}

      {selectedSection === 'attendedEvents' && (
        <div>
          <h3>Attended Events</h3>
          {attendedEvents.length === 0 ? (
              <p>No Attended events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start</th>
                <th>End</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {attendedEvents.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                  <td>
                    <button className='feedbackBtn' onClick={() => setFeedbackEventId(e.eventId)}>Give Feedback</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

      {/* Not Attended Events */}

      {selectedSection === 'notAttendedEvents' && (
        <div>
          <h3>Not Attended Events</h3>
          {notAttendedEvents.length === 0 ? (
              <p>No events available</p>
            ):( 
            <table className="data-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start</th>
                <th>End</th>
              </tr>
            </thead>
            <tbody>
              {notAttendedEvents.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                  <td>{e.name}</td>
                  <td>{e.venue}</td>
                  <td>{new Date(e.startDate).toLocaleString()}</td>
                  <td>{new Date(e.endDate).toLocaleString()}</td>
                </tr>
              ))}
            </tbody>
          </table>)
          }
        </div>
      )}

       {feedbackEventId && (
        <div className="feedback-form">
          <h3>Feedback for Event ID: {feedbackEventId}</h3>
          <textarea
            placeholder="Write your feedback..."
            value={feedbackText}
            onChange={(e) => setFeedbackText(e.target.value)}
          ></textarea>
          <div className="rating-input">
            <label>Rating (1–10):</label>
            <input
              type="number"
              min="1"
              max="10"
              value={rating}
              onChange={(e) => setRating(e.target.value)}
            />
          </div>
          <button onClick={submitFeedback}>Submit</button>
          <button onClick={() => setFeedbackEventId(null)} className="cancel-btn">
            Cancel
          </button>
        </div>
      )}
      
    </div>
  );
}
















// import React, { useEffect, useState } from 'react';
// import { api } from '../api/api';
// import { useNavigate } from 'react-router-dom';
// import './StudentDashboard.css';

// export default function StudentDashboard() {
//   const navigate = useNavigate();
//   const [events, setEvents] = useState([]);
//   const [registeredEvents, setRegisteredEvents] = useState([]);
//   const [feedbackEventId, setFeedbackEventId] = useState(null);
//   const [feedbackText, setFeedbackText] = useState('');
//   const [rating, setRating] = useState(5);

//   const user_id = localStorage.getItem('user_id');
//   const role = localStorage.getItem('role');

//   useEffect(() => {
//     if (!user_id || role !== 'USER') {
//       alert('Unauthorized access');
//       navigate('/');
//     } else {
//       loadEvents();
//       loadRegisteredEvents(user_id);
//     }
//   }, [user_id, role, navigate]);

//   const loadEvents = async () => {
//     try {
//       const res = await api.get('/eventCreation/all');
//       setEvents(res.data);
//     } catch {
//       alert('Failed to load events');
//     }
//   };

//    const loadRegisteredEvents = async (user_id) => {
//     try {
//       const res = await api.get(`/eventRegistration/registeredEvents?user_id=${user_id}`);
//       setRegisteredEvents(res.data);
//     } catch {
//       alert('Failed to load registered events');
//     }
//   };

//   const register = async (event_id) => {
//     try {
//       await api.post('/eventRegistration/register', {
//         user_id: Number(user_id),
//         event_id,
//         registration_status: 'R',
//         registered_by: Number(user_id),
//         registered_at: new Date().toISOString(),
//       });
//       alert('Registered successfully');
//     } catch {
//       alert('Registration failed');
//     }
//   };

//   const cancel = async (event_id) => {
//     try {
//       await api.put('/eventRegistration/update', {
//         user_id: Number(user_id),
//         event_id,
//         registration_status: 'C',
//         updated_by: Number(user_id),
//         updated_at: new Date().toISOString(),
//       });
//       alert('Registration cancelled');
//     } catch {
//       alert('Cancellation failed');
//     }
//   };

//   const submitFeedback = async () => {
//     if (!feedbackText.trim()) {
//       alert('Please write some feedback.');
//       return;
//     }

//     try {
//       await api.post('/feedback/add', {
//         user_id: Number(user_id),
//         event_id: feedbackEventId,
//         rating: Number(rating),
//         feedback: feedbackText,
//       });
//       alert('Feedback submitted');
//       setFeedbackEventId(null);
//       setFeedbackText('');
//       setRating(5);
//     } catch {
//       alert('Feedback submission failed');
//     }
//   };

//   return (
//     <div className="student-dashboard">
//       <h2>Student Dashboard</h2>
//       <p>Welcome, User ID: {user_id}</p>

//       <div className="allEvents">
//             <h3>Available Events</h3>
//             {events.length === 0 ? (
//               <p>No events available</p>
//             ) : (
//               <table className="event-table">
//                 <thead>
//                   <tr>
//                     <th>ID</th>
//                     <th>Name</th>
//                     <th>Venue</th>
//                     <th>Start</th>
//                     <th>End</th>
//                     <th>Actions</th>
//                   </tr>
//                 </thead>
//                 <tbody>
//                   {events.map((e) => (
//                     <tr key={e.event_id}>
//                       <td>{e.event_id}</td>
//                       <td>{e.name}</td>
//                       <td>{e.venue}</td>
//                       <td>{new Date(e.start_date).toLocaleString()}</td>
//                       <td>{new Date(e.end_date).toLocaleString()}</td>
//                       <td>
//                         <button className='registerBtn' onClick={() => register(e.event_id)}>Register</button>
//                         {/* <button className='cancelBtn' onClick={() => cancel(e.event_id)}>Cancel</button> */}
//                         {/* <button className='feedbackBtn' onClick={() => setFeedbackEventId(e.event_id)}>Give Feedback</button> */}
//                       </td>
//                     </tr>
//                   ))}
//                 </tbody>
//               </table>
//             )}

//             {feedbackEventId && (
//               <div className="feedback-form">
//                 <h3>Feedback for Event ID: {feedbackEventId}</h3>
//                 <textarea
//                   placeholder="Write your feedback..."
//                   value={feedbackText}
//                   onChange={(e) => setFeedbackText(e.target.value)}
//                 ></textarea>
//                 <div className="rating-input">
//                   <label>Rating (1–10):</label>
//                   <input
//                     type="number"
//                     min="1"
//                     max="10"
//                     value={rating}
//                     onChange={(e) => setRating(e.target.value)}
//                   />
//                 </div>
//                 <button onClick={submitFeedback}>Submit</button>
//                 <button onClick={() => setFeedbackEventId(null)} className="cancel-btn">
//                   Cancel
//                 </button>
//               </div>
//             )}
//       </div>
// {/* USER Registered Events */}
//       <div className="userRegisteredEvents">
//       <h3>Registered Events</h3>
//       {registeredEvents.length === 0 ? (
//         <p>No registered events available</p>
//       ) : (
//         <table className="event-table">
//           <thead>
//             <tr>
//               <th>User ID</th>
//               <th>Event ID</th>
//               <th>Registration Status</th>
//               <th>Registered By</th>
//               <th>Registred At</th>
//               <th>Actions</th>
//             </tr>
//           </thead>
//           <tbody>
//             {registeredEvents.map((e) => (
//               <tr key={e.user_id}>
//                 <td>{e.user_id}</td>
//                 <td>{e.event_id}</td>
//                 <td>{e.registration_status}</td>
//                 <td>{e.registered_by}</td>
//                 <td>{e.registered_at}</td>
//                 <td>
//                   <button className='cancelBtn' onClick={() => cancel(e.event_id)}>Cancel</button>
//                   <button className='feedbackBtn' onClick={() => setFeedbackEventId(e.event_id)}>Give Feedback</button>
//                 </td>
//               </tr>
//             ))}
//           </tbody>
//         </table>
//       )}

//       {feedbackEventId && (
//         <div className="feedback-form">
//           <h3>Feedback for Event ID: {feedbackEventId}</h3>
//           <textarea
//             placeholder="Write your feedback..."
//             value={feedbackText}
//             onChange={(e) => setFeedbackText(e.target.value)}
//           ></textarea>
//           <div className="rating-input">
//             <label>Rating (1–10):</label>
//             <input
//               type="number"
//               min="1"
//               max="10"
//               value={rating}
//               onChange={(e) => setRating(e.target.value)}
//             />
//           </div>
//           <button onClick={submitFeedback}>Submit</button>
//           <button onClick={() => setFeedbackEventId(null)} className="cancel-btn">
//             Cancel
//           </button>
//         </div>
//       )}
//       </div>
//     </div>
//   );
// }