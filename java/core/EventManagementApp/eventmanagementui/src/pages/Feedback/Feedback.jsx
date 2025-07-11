import axios from "axios";
import { useState } from "react";

function Feedback() {
    const [formData, setFormData] = useState({
        user_id: null,
        event_id: "",
        rating: "",
        feedback: ""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
        event.preventDefault();
        console.log("Feedback data : ", formData);
        try {
            const result = await axios.post("http://localhost:8080/api/feedback/add",formData
                 
            );
            setResponseMessage("Feedback Submitted Successfull!");
            
        }
        catch (error) {
            console.log("Error : ", error.response);
            setResponseMessage("Feedback Submission Failed!");
        }
    };

        return (
            <>
                <form onSubmit={handleClick}>
                    <label for="user_id">User_Id:</label>
                    <br></br>
                    <input type="text" id="user_id" name="user_id" value={formData.user_id} onChange={handleChange}
                        placeholder="Enter your User_Id" required />
                    <br></br>
                    <label for="event_id">
                        Event_Id:
                    </label>
                    <br></br>
                    <input type="text" id="event_id" name="event_id" value={formData.event_id} onChange={handleChange}
                        placeholder="Enter your Event_ID" required />
                    <br></br>
                    <label for="rating">
                        Rating:
                    </label>
                    <br></br>
                    <input type="text" id="rating" name="rating" value={formData.rating} onChange={handleChange}
                        placeholder="Enter The Rating" required />
                    <br></br>
                    <label for="">feedback</label>
                    <br></br>
                    <input type="text-box" id="feedback" name="feedback" value={formData.feedback} onChange={handleChange}
                        placeholder="Enter the Feedback" required />
                    <br></br>


                    <div class="wrap">
                        <button type="submit">
                            submit
                        </button>
                    </div>
                </form>
            </>
        )

}

export default Feedback;