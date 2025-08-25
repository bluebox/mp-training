import React, { useEffect, useState } from "react";
import "./Admin.css";
import axios from "axios";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

function Admin() {
  const accessToken = Cookies.get("jwt_token");
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);
  const [activeTab, setActiveTab] = useState("view");
  const [activeReport, setActiveReport] = useState("feedback");
  const [data, setData] = useState([]);

  const fetchEvents = async () => {
    try {
      const response = await axios.get("http://localhost:8090/Admin/viewevents", {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      setEvents(response.data);
    } catch (err) {
      console.log("Error fetching events:", err);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);
  const handleLogout = () => {
          localStorage.removeItem('role');
          localStorage.removeItem('userid');
        Cookies.remove('jwt_token'); 

        navigate('/');
    };
  const handleAddEvent = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8090/Admin/addevent",
        {
          userid: localStorage.getItem("userid"),
          event: {
            eventid: e.target.eventid.value,
            eventname: e.target.eventname.value,
            eventdatetime: e.target.eventdatetime.value,
            eventvenue: e.target.eventvenue.value,
            eventcategory: e.target.eventcategory.value.toUpperCase(),
            eventstatus: e.target.eventstatus.value.toUpperCase(),
            eventDescription: e.target.eventdescription.value,
            created_by: "",
            created_time: "",
            status: e.target.status.value.toUpperCase(),
            approved_time: "",
            approved_by: "",
            modified_by: "",
            modified_at: "",
          },
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      alert("Event added successfully!");
      setActiveTab("view");
      fetchEvents();
    } catch (err) {
      alert("Add failed: " + (err.response?.data || err.message));
    }
  };

  const handleUpdateEvent = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8090/Admin/updateevent",
        {
          userid: localStorage.getItem("userid"),
          event: {
            eventid: e.target.eventid.value,
            eventname: e.target.eventname.value,
            eventdatetime: e.target.eventdatetime.value,
            eventvenue: e.target.eventvenue.value,
            eventcategory: e.target.eventcategory.value.toUpperCase(),
            eventstatus: e.target.eventstatus.value.toUpperCase(),
            eventDescription: e.target.eventdescription.value,
            created_by: "",
            created_time: "",
            status: e.target.status.value.toUpperCase(),
            approved_time: "",
            approved_by: "",
            modified_by: "",
            modified_at: "",
          },
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      alert("Event updated successfully!");
      setActiveTab("view");
      fetchEvents();
    } catch (err) {
      alert("Update failed: " + (err.response?.data || err.message));
    }
  };

  const handleCancelEvent = async (id) => {
    try {
      await axios.post(
        "http://localhost:8090/Admin/delete",
        id,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );
      alert("Event cancelled!");
      fetchEvents();
    } catch (err) {
      alert("Cancellation failed: " + (err.response?.data || err.message));
    }
  };

  useEffect(() => {
    const fetchReport = async () => {
      let url = "";

      switch (activeReport) {
        case "feedback":
          url = "http://localhost:8090/api/report/feedbacks";
          break;
        case "rating":
          url = "http://localhost:8090/api/report/average-ratings";
          break;
        case "eventwise":
          url = "http://localhost:8090/api/report/event-wise";
          break;
        case "cancelled":
          url = "http://localhost:8090/api/report/cancelled";
          break;
        default:
          return;
      }

      try {
        const response = await axios.get(url, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        setData(response.data);
      } catch (err) {
        console.log("Report fetch failed:", err);
        setData([]);
      }
    };

    if (activeTab === "reports") {
      fetchReport();
    }
  }, [activeTab, activeReport]);

  return (
    <div className="admin">
      <nav className="admin-navbar">
        <h2>Admin Dashboard</h2>
        <ul>
          <li onClick={() => setActiveTab("view")}>View All Events</li>
          <li onClick={() => setActiveTab("add")}>Add Event</li>
          <li onClick={() => setActiveTab("update")}>Update Event</li>
          <li onClick={() => setActiveTab("cancel")}>Cancel Event</li>
          <li onClick={() => setActiveTab("reports")}>Reports</li>
                    <li onClick={handleLogout}>Logout</li>

        </ul>
      </nav>

      <div className="admin-content">
        {/* View Events */}
        {activeTab === "view" && (
          <div>
            <h3>All Events</h3>
            <table>
              <thead>
                <tr>
                  <th>Event ID</th>
                  <th>Event Name</th>
                  <th>DateTime</th>
                  <th>Venue</th>
                  <th>Category</th>
                  <th>Status</th>
                  <th>Created By</th>
                  <th>Created Time</th>
                  <th>Description</th>
                  <th>Status</th>
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
                    <td>{item.eventstatus}</td>
                    <td>{item.created_by}</td>
                    <td>{item.created_time}</td>
                    <td>{item.eventDescription}</td>
                    <td>{item.status}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {/* Add Event */}
        {activeTab === "add" && (
          <form className="event-form" onSubmit={handleAddEvent}>
            <h3>Add Event</h3>
            <input type="number" name="eventid" placeholder="Event ID" required />
            <input type="text" name="eventname" placeholder="Event Name" required />
            <input type="date" name="eventdatetime" required />
            <input type="text" name="eventvenue" placeholder="Venue" required />
            <select name="eventcategory" required>
              <option value="">Select Category</option>
              <option value="GAMES">Games</option>
              <option value="WORKSHOPS">Workshops</option>
              <option value="SEMINARS">Seminars</option>
              <option value="CULTURALS">Culturals</option>
            </select>
            <input type="text" name="eventdescription" placeholder="Description" required />
            <input type="text" name="status" placeholder="Status" required />
            <input type="text" name="eventstatus" placeholder="Event Status" required />
            <button type="submit">Add Event</button>
          </form>
        )}

        {/* Update Event */}
        {activeTab === "update" && (
          <form className="event-form" onSubmit={handleUpdateEvent}>
            <h3>Update Event</h3>
            <input type="number" name="eventid" placeholder="Event ID" required />
            <input type="text" name="eventname" placeholder="Event Name" required />
            <input type="date" name="eventdatetime" required />
            <input type="text" name="eventvenue" placeholder="Venue" required />
            <select name="eventcategory" required>
              <option value="">Select Category</option>
              <option value="GAMES">Games</option>
              <option value="WORKSHOPS">Workshops</option>
              <option value="SEMINARS">Seminars</option>
              <option value="CULTURALS">Culturals</option>
            </select>
            <input type="text" name="eventdescription" placeholder="Description" required />
            <input type="text" name="status" placeholder="Status" required />
            <input type="text" name="eventstatus" placeholder="Event Status" required />
            <button type="submit">Update Event</button>
          </form>
        )}

        {/* Cancel Event */}
        {activeTab === "cancel" && (
          <div>
            <h3>Cancel Event</h3>
            {events
              .filter((ev) => ev.eventstatus?.toLowerCase() !== "cancelled")
              .map((ev) => (
                <div key={ev.eventid} className="cancel-card">
                  <span>{ev.eventname} - {ev.eventdatetime}</span>
                  <button onClick={() => handleCancelEvent(ev.eventid)}>Cancel</button>
                </div>
              ))}
          </div>
        )}

        {/* Reports */}
        {activeTab === "reports" && (
          <div className="reports-container">
            <h2>Reports Dashboard</h2>
            <div className="report-buttons">
              {["feedback", "rating", "eventwise", "cancelled"].map((type) => (
                <button
                  key={type}
                  className={`report-btn ${activeReport === type ? "active" : ""}`}
                  onClick={() => setActiveReport(type)}
                >
                  {type.charAt(0).toUpperCase() + type.slice(1)} Report
                </button>
              ))}
            </div>

            {/* Render table based on report */}
            {activeReport === "feedback" && (
              <ReportTable headers={["Event ID", "User ID", "Feedback", "Rating"]} data={data} keys={["eventId", "userId", "description", "rating"]} />
            )}
            {activeReport === "rating" && (
              <ReportTable headers={["Event ID", "Total Feedbacks", "Average Rating"]} data={data} keys={["eventId", "totalFeedbacks", "avgRating"]} />
            )}
            {activeReport === "eventwise" && (
              <ReportTable headers={["Event ID", "Event Name", "Total Registrations"]} data={data} keys={["eventId", "eventName", "totalRegistrations"]} />
            )}
            {activeReport === "cancelled" && (
              <ReportTable headers={["User ID", "Event ID", "Event Name", "Status", "Modified At"]} data={data} keys={["userId", "eventId", "eventName", "status", "modifiedAt"]} formatDateKey="modifiedAt" />
            )}
          </div>
        )}
      </div>
    </div>
  );
}

const ReportTable = ({ headers, data, keys, formatDateKey }) => (
  <table className="admin-table">
    <thead>
      <tr>
        {headers.map((h, idx) => <th key={idx}>{h}</th>)}
      </tr>
    </thead>
    <tbody>
      {data.map((row, idx) => (
        <tr key={idx}>
          {keys.map((k) => (
            <td key={k}>{formatDateKey === k ? new Date(row[k]).toLocaleString() : row[k]}</td>
          ))}
        </tr>
      ))}
    </tbody>
  </table>
);

export default Admin;
