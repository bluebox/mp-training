import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

function AddEvent() {
    const [formData, setFormData] = useState({
        event_id: null,
        name:"",
        start_date:"",
        end_date:"",
        venue:"",
        event_organization:"",
		event_capacity:"",
        event_status:"",
        created_by:null
    });
    const [responseMessage, setResponseMessage] = useState("");
    

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
    event.preventDefault();
    console.log("Event Creation data : ", formData);  
    try {
        await axios.post("http://localhost:8080/api/eventCreation/createEvent", formData,);
        setResponseMessage("Event created successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Event failed to create!");
    }
    };

    return (
        <div>
            <h1>Create Event</h1>
            {responseMessage && <h2>{responseMessage}</h2>}
            <form onSubmit={handleClick}>
                <label>Event Id</label><br />
                <input type="number" name="event_id" id="" value={formData.event_id} onChange={handleChange} placeholder="enter number" required /><br /><br />

                <label>Name</label><br />
                <input type="text" name="name" id="" value={formData.name} onChange={handleChange} required /><br /><br />

                <label>Start Date</label><br />
                <input type="datetime-local" name="start_date" id="" value={formData.start_date} onChange={handleChange} required /><br /><br />

                <label>End Date</label><br />
                <input type="datetime-local" name="end_date" id="" value={formData.end_date} onChange={handleChange} required /><br /><br />

                 <label>Venue</label><br />
                <input type="text" name="venue" id="" value={formData.venue} onChange={handleChange} required /><br /><br /> 

                <label>Organizer</label><br />
                <input type="text" name="event_organization" id="" value={formData.event_organization} onChange={handleChange} required /><br /><br /> 

                <label>Event Capacity</label><br />
                <input type="number" name="event_capacity" id="" value={formData.event_capacity} onChange={handleChange} placeholder="enter number" required /><br /><br /> 

                <label>Event Status</label><br />
                <label>Active</label>
                <input type="radio" name="event_status" value="ACTIVE" onChange={handleChange} required /> <br />
                <label>Finished</label>
                <input type="radio" name="event_status" value="FINISHED" onChange={handleChange} required /> <br />
                
                <label>Created By</label><br />
                <input type="number" name="created_by" id="" value={formData.created_by} onChange={handleChange} placeholder="enter number" required /><br /><br /> 

                <button type="submit" className="btn btn-success">Submit</button>
                <button type="reset" className="btn btn-info ms-3">Reset</button>
                <Link className="btn btn-dark ms-3" to="/">Home</Link>
            </form>             
        </div>
    );
}

export default AddEvent;
