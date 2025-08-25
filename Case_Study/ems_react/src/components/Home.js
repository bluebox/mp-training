import React from "react";
import { Link } from "react-router-dom";
import "./Home.css";

function Home() {
  return (
    <div className="home-page">
      {/* Navbar */}
      <nav className="navbar">
        <div className="logo"> Event Management</div>
        <ul className="nav-links">
          <li><Link to="/">Home</Link></li>
          {/* <li><Link to="/events">Events</Link></li> */}
          {/* <li><Link to="/Students">students</Link></li> */}
          {/* <li><Link to="/Faculty">Faculty</Link></li> */}
          {/* <li><Link to="/Admin">Admin</Link></li> */}

          <li><Link to="/login">Login</Link></li>
          <li><Link to="/register">Register</Link></li>
        </ul>
      </nav>

      {/* Hero Section */}
      <header className="hero">
        <h1>Welcome to the Event Management System 🎊</h1>
        <p>
          Our Event Management System helps organize, manage, and promote events seamlessly.  
          From student workshops to faculty conferences, cultural fests to exhibitions —  
          everything is handled in one platform with ease and style!
        </p>
      </header>

      {/* Events Section */}
      <section id="events" className="events">
        <h2>Upcoming Events</h2>
        <div className="event-cards">
          <div className="event-card">
  <div className="event-content">
    <h3>Tech Workshop</h3>
    <p>Learn the latest web development technologies.</p>
    <span>Date: Dec 15 | Time: 10:00 AM | Location: Main Hall</span>
  </div>
</div>


          {/* <div className="event-card" style={{ backgroundImage: "url('https://i.pinimg.com/1200x/80/ac/38/80ac38ef9581be900c6d86235735e786.jpg')" }}>
    <div className="event-content">
      <h3>Sports Day</h3>
      <p>Participate in various sports activities.</p>
      <span>Date: Dec 20 | Time: 9:00 AM | Location: Sports Complex</span>
    </div>
  </div> */}

  {/* Art Exhibition */}
  <div className="event-card" style={{ backgroundImage: "url('https://i.pinimg.com/1200x/23/b9/88/23b988e3690ad6774a26bdd7d91dfcbf.jpg')" }}>
    <div className="event-content">
      <h3>Art Exhibition</h3>
      <p>Explore contemporary art forms.</p>
      <span>Date: Dec 18 | Time: 2:00 PM | Location: Art Gallery</span>
    </div>
  </div>

  {/* Cultural Fest */}
  <div className="event-card" style={{ backgroundImage: "url('https://i.pinimg.com/736x/96/74/bf/9674bf16632f18cba4fe4b9fec975481.jpg')" }}>
    <div className="event-content">
      <h3>Cultural Fest</h3>
      <p>Celebrate music, dance, and traditions.</p>
      <span>Date: Dec 22 | Time: 5:00 PM | Location: Open Grounds</span>
    </div>
  </div>

</div>
      </section>
    </div>
  );
}

export default Home;