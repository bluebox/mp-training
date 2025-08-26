import React from "react";
import { useParams, Link } from "react-router-dom";
import { motion } from "framer-motion";
import "./EventDetails.css";

const events = [
  {
    id: 1,
    title: "Tech Workshop",
    description: "Learn the latest web development technologies.",
    date: "Dec 15",
    time: "10:00 AM",
    location: "Main Hall",
    image:
      "https://www.shutterstock.com/image-photo/diverse-school-children-students-build-260nw-2036186204.jpg",
  },
  {
    id: 2,
    title: "Sports Day",
    description: "Participate in various sports activities.",
    date: "Dec 20",
    time: "9:00 AM",
    location: "Sports Complex",
    image:
      "https://i.pinimg.com/1200x/80/ac/38/80ac38ef9581be900c6d86235735e786.jpg",
  },
  {
    id: 3,
    title: "Art Exhibition",
    description: "Explore contemporary art forms.",
    date: "Dec 18",
    time: "2:00 PM",
    location: "Art Gallery",
    image:
      "https://i.pinimg.com/1200x/23/b9/88/23b988e3690ad6774a26bdd7d91dfcbf.jpg",
  },
  {
    id: 4,
    title: "Cultural Fest",
    description: "A day full of music, dance and cultural performances.",
    date: "Dec 22",
    time: "5:00 PM",
    location: "Auditorium",
    image:
      "https://i.pinimg.com/736x/96/74/bf/9674bf16632f18cba4fe4b9fec975481.jpg",
  },
];

function EventDetails() {
  const { id } = useParams();
  const event = events.find((e) => e.id === parseInt(id));

  if (!event) return <h2>Event not found</h2>;

  return (
    <motion.div
      className="event-details"
      initial={{ opacity: 0, y: 40 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.6 }}
    >
      <img src={event.image} alt={event.title} className="detail-img" />
      <h2>{event.title}</h2>
      <p className="desc">{event.description}</p>
      <p>
        📅 <strong>Date:</strong> {event.date}
      </p>
      <p>
        ⏰ <strong>Time:</strong> {event.time}
      </p>
      <p>
        📍 <strong>Location:</strong> {event.location}
      </p>
      <Link to="/events" className="back-btn">
        ← Back to Events
      </Link>
    </motion.div>
  );
}

export default EventDetails;