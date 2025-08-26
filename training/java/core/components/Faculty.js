import React, { useEffect,useState } from "react";
import "./Faculty.css";
import Cookies from "js-cookie";
import axios from "axios";

function Faculty() {
  const [activeTab, setActiveTab] = useState("view");
    const [regstudents,setregstudents]=useState([]);
    const accessToken = Cookies.get("jwt_token");
    const [attendance,setattendance]=useState("false");
        const [presentattd,setpresentattd]=useState("");
    // const [absentattd,setabsentattd]=useState("");

  const [events, setEvents] = useState([  ]);
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
  }, []);




 
    const fetchRegStudents = async (e) => {
        e.preventDefault();
      try {
        const response = await axios.get("http://localhost:8090/api/Registration/viewregstudents", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },params: {
        eventid: e.target.eventid.value,  
      }
        });
        setregstudents(response.data);
        console.log("res",response.data);
      } catch (err) {
        console.error("Error fetching events", err);
      }
    };


 
  const [meetings, setMeetings] = useState([]);

    const handleattendence=async(e)=>{

         e.preventDefault();
      try {
        const response = await axios.post("http://localhost:8090/faculty/attendence",{

            
            userid:e.target.userid.value,
            eventid:e.target.eventid.value,
            attendence:e.target.presence.value,
            facultyid:localStorage.getItem('userid'),
            usertype:e.target.usertype.value
        }, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          }
        });
        setregstudents(response.data);
        console.log("res",response.data);
      } catch (err) {
        console.error("Error fetching events", err);
      }
        
    }
  const toggleAttendance = (eventId, studentName) => {
    setEvents(events.map(ev => {
      if (ev.id === eventId) {
        return {
          ...ev,
          registeredStudents: ev.registeredStudents.map(stu =>
            stu.name === studentName
              ? { ...stu, status: stu.status === "Present" ? "Absent" : "Present" }
              : stu
          )
        };
      }
      return ev;
    }));
  };
const handleRegister = async (e) => {
    e.preventDefault();
    
    const eventid = e.target.eventid.value;
    
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
      setActiveTab("my");
    } catch (err) {
      alert("Registration failed: " + (err.response?.data || err.message));
    }
  };
  
  const handleApprove = async(eventId) => {
    const event = events.find((e) => e.eventid === eventId);
    if (event) {
      setEvents(events.map((ev) => (ev.eventid === eventId ? { ...ev, eventstatus: "approved" } : ev)));
    }
     try {
      const response = await axios.post(
        "http://localhost:8090/faculty/approveevent",
        {
          userid:localStorage.getItem('userid'),
          eventid:eventId,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      alert("Event registered successfully");
      setActiveTab("my");
    } catch (err) {
      alert("Registration failed: " + (err.response?.data || err.message));
    }

    
  };

  return (
    <div className="faculty">
      <nav className="faculty-navbar">
        <h2>Faculty Dashboard</h2>
        <ul>
          <li onClick={() => setActiveTab("view")}>View Events</li>
          <li onClick={() => setActiveTab("registered")}>Registered Students</li>
          <li onClick={() => setActiveTab("schedule")}>Schedule Meeting</li>
          <li onClick={() => setActiveTab("event registration")}>Event registration</li>
        <li onClick={() => setActiveTab("attendance")}>Attendance</li>

        </ul>
      </nav>

      <div className="faculty-content">
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

        {activeTab === "registered" && (
<div>
            <form onSubmit={fetchRegStudents}>
                <label htmlFor="eventid">Event id</label>
                <input type="number" name="eventid" placeholder="enter event id"/>
                <input type="submit" />
            </form>
            {regstudents.length===0 && (<div>No Registered Students Found</div>)}
           {regstudents.length>0 && (<div>
            <h3>All Registered Students</h3>
            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Mobile Number</th>
                </tr>
              </thead>
              <tbody>
                {regstudents.map((item) => (
                  <tr key={item.email}>
                    <td>{item.userName}</td>
                    <td>{item.email}</td>
                    <td>{item.mobileNumber}</td>
                    <td></td>
                  </tr>

                ))}
              </tbody>
            </table>  </div>)}
          </div>
            
          
            
            
            
            
            /* <div>
            <h3>Registered Students & Attendance</h3>
            {events.map((ev) => (
              <div key={ev.id} className="registered-block">
                <h4>{ev.name} ({ev.date})</h4>
                {ev.registeredStudents.length === 0 ? (
                  <p>No students registered.</p>
                ) : (
                  <table border="1" className="faculty-table">
                    <thead>
                      <tr>
                        <th>Student Name</th>
                        <th>Status</th>
                        <th>Toggle</th>
                      </tr>
                    </thead>
                    <tbody>
                      {ev.registeredStudents.map((stu, idx) => (
                        <tr key={idx}>
                          <td>{stu.name}</td>
                          <td>{stu.status}</td>
                          <td>
                            <input
                              type="checkbox"
                              checked={stu.status === "Present"}
                              onChange={() => toggleAttendance(ev.id, stu.name)}
                            />
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                )}
              </div>
            ))}
          </div> */
        )}

        {/* SCHEDULE MEETING */}
        {activeTab === "schedule" && (
          <div>
            <h3>Pending Events for Approval</h3>
            {console.log(events)}
            {events.filter((ev) => ev.eventstatus === "PENDING").length === 0 ? (
              <p>No pending events.</p>
            ) : (
              <table border="1" className="faculty-table">
                <thead>
                  <tr>
                    <th>Event Name</th>
                    <th>Date</th>
                    <th>Location</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {events
                    .filter((ev) => ev.eventstatus === "PENDING")
                    .map((ev) => (
                      <tr key={ev.eventid}>
                        <td>{ev.eventname}</td>
                        <td>{ev.eventdatetime}</td>
                        <td>{ev.eventvenue}</td>
                        <td>
                          <button onClick={() => handleApprove(ev.eventid)}>Approve</button>
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            )}
          </div>
        )}

        {/* MY MEETINGS */}
        {activeTab === "event registration" && (
          <form className="faculty-form" onSubmit={handleRegister}>
            <h3>Register for Event</h3>
            <input type="number" name="eventid" placeholder="Event ID" required />
            <button type="submit">Register</button>
          </form>
        )}

         {activeTab === "attendance" && (
          <div>
            <form onSubmit={fetchRegStudents}>
                <label htmlFor="eventid">Event id</label>
                <input type="number" name="eventid" placeholder="enter event id"/>
                <input type="submit" />
            </form>
            {regstudents.length===0 && (<div>No Registered Students Found</div>)}
           {regstudents.length>0 && (<div>
            <h3>All Registered Students</h3>
            <table>
              <thead>
                <tr>
                <th>Id</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Mobile Number</th>
                </tr>
              </thead>
              <tbody>
                {regstudents.map((item) => (
                  <tr key={item.email}>
                  <td>{item.userId}</td>
                    <td>{item.userName}</td>
                    <td>{item.email}</td>
                    <td>{item.mobileNumber}</td>
                    </tr>

                ))}
              </tbody>
            </table> 
            
            <form onSubmit={handleattendence}>

            
            <label htmlFor=""> userid</label>
                <input type="text" name="userid"  placeholder="userid"/>
            <label htmlFor="">eventid</label>

                <input type="text" name="eventid" placeholder="eventid"/> 
            <input type="radio" value="absent" name="presence"/>:Absent
            <input type="radio" value="present" name="presence"/>:Present
            <br></br>
            <input type="radio" value="Student" name="usertype"/>:Student
            <input type="radio" value="Faculty" name="usertype"/>:Faculty
    
                <input type="submit"/>
            </form> </div>)}
          </div>
            
        )}
      </div>
    </div>
  );
}

export default Faculty;
