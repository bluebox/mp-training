import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const EventById = () => {
   const [events,setevents] = useState([])
    const [error,setError] = useState("")
     useEffect(() => {
        axios.get("http://localhost:8080/api/eventCreation/event")
            .then((response) => {
                setevents(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    if (error) return <h3 style={{color : 'red'}}>Error : {error}</h3>;
    
    return (
    <div>
      <h1>All Events</h1>

      <table className='table'>
        <thead>
            <tr>
                <th>Event ID</th>
                <th>Title</th>
                <th>Satrt Date</th>
                <th>End Date</th>
                <th>Venue</th>
                <th>Organizer</th>
                <th>Event Capacity</th>
                <th>Participant Count</th>
                <th>Status</th>
                <th>Created By</th>
                <th>Created At</th>
                <th>Updated By</th>
                <th>Updated At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
                {                  
                    events.map((event)=>(
                        <tr>
                        <td key={event.event_id}>{event.event_id}</td>
                        <td>{event.name}</td>
                        <td>{event.start_date}</td>
                        <td>{event.end_date}</td>
                        <td>{event.venue}</td>
                        <td>{event.event_organization}</td>
                        <td>{event.event_capacity}</td>
                        <td>{event.participant_count}</td>
                        <td>{event.event_status}</td>
                        <th>{event.created_by}</th>
                        <th>{event.created_at}</th>
                        <td>{event.updated_by}</td>
                        <td>{event.updated_at}</td>
                        <td><Link className="btn btn-dark" to="/updateEvent">Edit</Link> <Link className="btn btn-dark">Cancle</Link></td>
                        </tr>
                    ))
                }
         </tbody>
      </table>  
      <Link className="btn btn-dark" to="/">Go to Home</Link>
    </div>
  )
}

export default EventById
