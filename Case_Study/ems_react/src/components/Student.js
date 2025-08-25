import React, { useEffect, useState } from "react";
import "./Student.css";
import Cookies from "js-cookie";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function Student() {
  const navigate=useNavigate();
  const [activeTab, setActiveTab] = useState("view");
  const accessToken = Cookies.get("jwt_token");
  const [events, setEvents] = useState([]);
  const [registrations, setRegistrations] = useState([]);
    const [feedbacks, setFeedbacks] = useState([]);
const [rating, setRating] = useState(0);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get("http://localhost:8090/Admin/viewevents", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        setEvents(response.data);
      } catch (err) {
        console.error("Error fetching events", err);
      }
    };

    fetchEvents();
  }, [accessToken]);

 
    const fetchRegistrations = async () => {
      try {
        
        const response = await axios.get(`http://localhost:8090/student/viewregevents`,{
          headers: {
            Authorization: `Bearer ${accessToken}`,
          }, params: {
        userid: localStorage.getItem('userid'),  
      },
        });
        setRegistrations(response.data);
      } catch (err) {
        console.error("Error fetching registrations", err);
      }
    };

   useEffect(() => {
  const load = async () => {
    await fetchRegistrations();
  };
  load();
}, []);

  const handleRegister = async (e) => {
    e.preventDefault();
    
    const eventid = e.target.eventid.value;
    console.log(eventid,localStorage.getItem('role').toUpperCase());
    
    try {
      const response = await axios.post(
        "http://localhost:8090/api/Registration/eventregister",
        {
          userid:localStorage.getItem('userid'),
          eventid:eventid,
          usertype:localStorage.getItem('role')
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      alert("Event registered successfully");
          await fetchRegistrations();

      setActiveTab("my");
    } catch (err) {
      alert("Registration failed: " + (err.response?.data || err.message));
    }
  };

  const handleCancelRegistration = async(id) => {
     try {
      const response = await axios.post(
        "http://localhost:8090/api/Registration/cancelregister",
        {
          userid:localStorage.getItem('userid'),
          eventid:id,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      alert("Registration cancelled  successfully");
      setActiveTab("my");
    } catch (err) {
      alert("Registration failed: " + (err.response?.data || err.message));
    }
    setRegistrations(registrations.filter((r) => r.id !== id));
  };
 const handleLogout = () => {
          localStorage.removeItem('role');
          localStorage.removeItem('userid');
        Cookies.remove('jwt_token'); 

        navigate('/');
    };

  const handleFeedback = async (e) => {
  e.preventDefault();

  const eventId = parseInt(e.target.eventid.value);
  const event = events.find((ev) => ev.eventid === eventId); // this is your selected event

  console.log(
    localStorage.getItem("userid") +
      eventId +
      e.target.feedback.value +
      rating
  );

  try {
    const response = await axios.post(
      "http://localhost:8090/student/feedback",
      {
        userId: localStorage.getItem("userid"),
        eventId: eventId,
        description: e.target.feedback.value,
        rating: rating,
      },
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }
    );

    alert("Feedback submitted successfully");
    setActiveTab("my");
  } catch (err) {
    alert("Feedback failed: " + (err.response?.data || err.message));
  }

  const fb = {
    eventId: event.eventid,
    eventName: event.eventname,
    studentName: localStorage.getItem("userid"),
    feedback: e.target.feedback.value,
    rating: rating,
  };

  setFeedbacks([...feedbacks, fb]);
  e.target.reset();
  setRating(0); 
};


  return (
    <div className="student">
      <nav className="student-navbar">
        <h2>Student Dashboard</h2>
        <ul>
          <li onClick={() => setActiveTab("view")}>View All Events</li>
          <li onClick={() => setActiveTab("my")}>My Registrations</li>
          <li onClick={() => setActiveTab("register")}>Register for Event</li>
          <li onClick={() => setActiveTab("cancel")}>Cancel Registration</li>
          <li onClick={() => setActiveTab("feedback")}>Feedback</li>
          <li onClick={handleLogout}>Logout</li>

        </ul>
      </nav>

      <div className="student-content">
        {activeTab === "view" && (
          <div>
            <h3>All Events</h3>
            <table>
              <thead>
                <tr>
                  <th>Event ID</th>
                  <th>Name</th>
                  <th>DateTime</th>
                  <th>Venue</th>
                  <th>Category</th>
                  <th>Status</th>
                  <th>Created By</th>
                  <th>Created Time</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {events.map((item) => (
                  <tr key={item.eventid}>
                    <td>{item.eventid}</td>
                    <td>{item.eventname}</td>
                    <td>{item.eventdatetime}</td>
                    <td>{item.eventvenue}</td>
                    <td>{item.eventcategory}</td>
                    <td>{item.status}</td>
                    <td>{item.created_by}</td>
                    <td>{item.created_time}</td>
                    <td>{item.eventDescription}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {activeTab === "my" && (
          <div>
            <h3>My Registered Events</h3>
            
            

             <table>
              <thead>
                <tr>
                  <th>Event ID</th>
                  <th>Name</th>
                  <th>DateTime</th>
                  <th>Venue</th>
                  <th>Category</th>
                  <th>Status</th>
                  <th>Created By</th>
                  <th>Created Time</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {registrations.map((item) => (
                  <tr key={item.eventid}>
                    <td>{item.eventid}</td>
                    <td>{item.eventname}</td>
                    <td>{item.eventdatetime}</td>
                    <td>{item.eventvenue}</td>
                    <td>{item.eventcategory}</td>
                    <td>{item.status}</td>
                    <td>{item.created_by}</td>
                    <td>{item.created_time}</td>
                    <td>{item.eventDescription}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {activeTab === "register" && (
          <form className="student-form" onSubmit={handleRegister}>
            <h3>Register for Event</h3>
            <input type="number" name="eventid" placeholder="Event ID" required />
            <button type="submit">Register</button>
          </form>
        )}

        {activeTab === "cancel" && (
          <div>
            <h3>Cancel Registration</h3>
            {registrations.length === 0 ? (
              <p>No registrations found.</p>
            ) : (
               <div>
            
            {registrations.map((ev) => (
              <div key={ev.eventid} className="cancel-card">
                <span >{ev.eventname} - {ev.eventdatetime}</span>
                <button onClick={() => handleCancelRegistration(ev.eventid)}>Cancel</button>
              </div>
            ))}
          </div>
            )}
          </div>
        )} 







          {activeTab === "feedback" && (
          <div>
            <form className="student-form" onSubmit={handleFeedback}>
              <h3>Submit Feedback</h3>
              <select name="eventid" required>
                <option value="">Select Event</option>
                {registrations.map((ev) => (
                  <option key={ev.eventid} value={ev.eventid}>
                    {ev.eventname}
                  </option>
                ))}
              </select>
              <textarea name="feedback" placeholder="Your Feedback" required></textarea>
              <div className="rating">
  <label>Rating:</label>
  {[1, 2, 3, 4, 5].map((star) => (
    <span
      key={star}
      style={{
        cursor: "pointer",
        color: star <= rating ? "gold" : "gray",
        fontSize: "24px",
      }}
      onClick={() => setRating(star)}
    >
      ★
    </span>
  ))}
</div>

              
              <button type="submit">Submit Feedback</button>

            </form>

            <h3>Submitted Feedback</h3>
            {feedbacks.length === 0 ? (
              <p>No feedback yet.</p>
            ) : (
              <table className="feedback-table">
                <thead>
                  <tr>
                    <th>Event ID</th>
                    <th>Event Name</th>
                    <th>Student Name</th>
                    <th>Feedback</th>
                  </tr>
                   </thead>
                <tbody>
                  {feedbacks.map((fb) => (
                    <tr key={fb.id}>
                      <td>{fb.eventId}</td>
                      <td>{fb.eventName}</td>
                      <td>{fb.studentName}</td>
                      <td>{fb.feedback}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}
          </div>
        )}


      </div>
    </div>
  );
}

export default Student;
