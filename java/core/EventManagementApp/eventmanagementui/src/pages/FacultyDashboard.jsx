import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import './FacultyDashboard.css';

export default function FacultyDashboard() {
  const navigate = useNavigate();
  const [selectedSection, setSelectedSection] = useState(null);
  const [events, setEvents] = useState([]);
  const [feedbacks, setFeedbacks] = useState([]);
  const [users, setUsers] = useState([]);
  const [eventIdInput, setEventIdInput] = useState('');

  const userId = localStorage.getItem('userId');
  const role = localStorage.getItem('role');

  useEffect(() => {
    if (!userId || role !== 'FACULTY') {
      alert('Unauthorized access');
      navigate('/');
    }
  }, [userId, role, navigate]);

  const loadEvents = async () => {
    try {
      const res = await api.get('/eventCreation/events');
      setEvents(res.data);
    } catch {
      alert('Failed to load events');
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

  const pdf = async (eventId) => {
     try {
      await api.get(`http://localhost:8080/api/pdf/feedback?eventId=${eventId}`);
      alert('PDF downloaded successfully');
    } catch {
      alert('PDF download failed!');
    }
  }
 const registeredPdf = async (eventId) => {
     try {
      await api.get(`http://localhost:8080/api/pdf/absenties?eventId=${eventId}`);
      alert('PDF downloaded successfully');
    } catch {
      alert('PDF download failed!');
    }
  }

  const absentiesPdf = async (eventId) => {
     try {
      await api.get(`http://localhost:8080/api/pdf/absenties?eventId=${eventId}`);
      alert('PDF downloaded successfully');
    } catch {
      alert('PDF download failed!');
    }
  }

    const cancelledPdf = async (eventId) => {
     try {
      await api.get(`http://localhost:8080/api/pdf/cancelled?eventId=${eventId}`);
      alert('PDF downloaded successfully');
    } catch {
      alert('PDF download failed!');
    }
  }

    const attendedPdf = async (eventId) => {
     try {
      await api.get(`http://localhost:8080/api/pdf/attended?eventId=${eventId}`);
      alert('PDF downloaded successfully');
    } catch {
      alert('PDF download failed!');
    }
  }
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

  const fetchFeedbacks = async () => {
    try {
      const res = await api.get(`/feedback/feedbacks?eventId=${eventIdInput}`);
      setFeedbacks(res.data);
    } catch {
      alert('Failed to fetch feedbacks');
    }
  };

  const fetchUsers = async (type) => {
    try {
      let url = '';
      if (type === 'attendants') url = `/eventRegistration/users?eventId=${eventIdInput}&status=${"A"}`;
      else if (type === 'registered') url = `/eventRegistration/users?eventId=${eventIdInput}&status=${"R"}`;
      else if (type === 'absentees') url = `/eventRegistration/users?eventId=${eventIdInput}&status=${"N"}`;
      else url = `/eventRegistration/users?eventId=${eventIdInput}&status=${"C"}`;
      const res = await api.get(url);
      setUsers(res.data);
    } catch {
      alert('Failed to fetch users');
    }
  };

  function logout() {
  localStorage.removeItem('role');
  localStorage.removeItem('user_id');

  console.log("Logged out. Items removed from localStorage.");

  window.location.href = '/';
}

  return (
    <div className="faculty-dashboard">
      <h1>Welcome Faculty, User ID: {userId} 
        <span><button onClick={logout} className='btn btn-danger'>Logout</button>
        </span>
      </h1>

      <div className="tabs">
        <button onClick={() => { setSelectedSection('events'); loadEvents(); }}>Events</button>
        <button onClick={() => setSelectedSection('registered')}>Event Registered Members</button>
        <button onClick={() => setSelectedSection('feedback')}>Feedbacks</button>
        <button onClick={() => setSelectedSection('attendants')}>Attendants</button>
        <button onClick={() => setSelectedSection('cancelled')}>Cancelled / Absentees</button>
        <button onClick={logout} className='btn btn-danger'>Logout</button>
      </div>

      {selectedSection === 'events' && (
        <div>
          <h3>All Events</h3>
          <table className="data-table">
            <thead>
              <tr>
                 <th>ID</th>
                <th>Name</th>
                <th>Venue</th>
                <th>Start_Date</th>
                <th>End_Date</th>
                <th>Organizer</th>
                <th>Capacity</th>
                {/* <th>Participants</th> */}
                <th>Status</th>
                <th>Created By</th>
                <th>Created At</th>
                <th>Updated By</th>
                <th>Updated At</th>
              </tr>
            </thead>
            <tbody>
              {events.map((e) => (
                <tr key={e.eventId}>
                  <td>{e.eventId}</td>
                      <td>{e.name}</td>
                      <td>{e.venue}</td>
                      <td>{e.startDate}</td>
                      <td>{e.endDate}</td>
                      <td>{e.eventOrganizer}</td>
                      <td>{e.eventCapacity}</td>
                      {/* <td>{e.participantCount}</td> */}
                      <td>{e.event_status === 'A' ? 'ACTIVE' : e.event_status === 'C' ? 'CANCELLED' : 'FINISHED'}</td>
                      <td>{e.createdBy}</td>
                      <td>{e.createdAt}</td>
                      <td>{e.updatedBy}</td>
                      <td>{e.updatedAt}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {selectedSection === 'feedback' && (
        <div>
          <h3>View Feedbacks Event wise</h3>
          <input
            type="number"
            placeholder="Enter Event ID"
            value={eventIdInput}
            onChange={(e) => setEventIdInput(e.target.value)}
          />
          <button onClick={fetchFeedbacks}>Get Feedback</button>

          <table className="data-table">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Feedback</th>
                <th>Rating</th>
              </tr>
            </thead>
            <tbody>
              {feedbacks.map((f, idx) => (
                <tr key={idx}>
                  <td>{f.userId}</td>
                  <td>{f.feedback}</td>
                  <td>{f.rating}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <button onClick={() => pdf(eventIdInput)}>Print PDF</button>
        </div>
      )}

      {selectedSection === 'registered' && (
        <div>
          <h3>Event wise Registered Members</h3>
          <input
            type="number"
            placeholder="Enter Event ID"
            value={eventIdInput}
            onChange={(e) => setEventIdInput(e.target.value)}
          />
          <button onClick={() => fetchUsers('registered')}>Get Registered Members</button>

          <table className="data-table">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u, idx) => (
                <tr key={idx}>
                  <td>{u.userId}</td>
                  <td>{u.name}</td>
                  <td>{u.email}</td>
                  <td>
                    <button onClick={() => fetchUsers('attendants')} className='btn btn-success'>Present</button>
                    <button onClick={() => fetchUsers('absentees')} className='btn btn-danger'>Absent</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          {/* <button onClick={() => registeredPdf(eventIdInput)}>Print PDF</button> */}
        </div>
      )}

      {selectedSection === 'attendants' && (
        <div>
          <h3>Event wise Attendants</h3>
          <input
            type="number"
            placeholder="Enter Event ID"
            value={eventIdInput}
            onChange={(e) => setEventIdInput(e.target.value)}
          />
          <button onClick={() => fetchUsers('attendants')}>Get Attendants</button>

          <table className="data-table">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u, idx) => (
                <tr key={idx}>
                  <td>{u.userId}</td>
                  <td>{u.name}</td>
                  <td>{u.email}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {/* <button onClick={() => attendedPdf(eventIdInput)}>Print PDF</button> */}
        </div>
      )}

      {selectedSection === 'cancelled' && (
        <div>
          <h3>Event wise Cancelled & Absentees</h3>
          <input
            type="number"
            placeholder="Enter Event ID"
            value={eventIdInput}
            onChange={(e) => setEventIdInput(e.target.value)}
          />
          <div className="tabs">
            <button onClick={() => fetchUsers('absentees')}>See Event Absentees</button>
            <button onClick={() => fetchUsers('cancelled')}>See Event Cancelled</button>
          </div>

          <table className="data-table">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u, idx) => (
                <tr key={idx}>
                  <td>{u.userId}</td>
                  <td>{u.name}</td>
                  <td>{u.email}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {/* <button onClick={() => cancelledPdf(eventIdInput)}>Print Cancelled PDF</button> */}
          {/* <button onClick={() => absentiesPdf(eventIdInput)}>Print Absenties PDF</button> */}
        </div>
      )}
    </div>
  );
}






