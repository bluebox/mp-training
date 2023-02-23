import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import MSidebar from "./MSidebar";
import Swal from "sweetalert2";

const baseUrl = 'http://127.0.0.1:8000/api/all_topic_videos/';
const baseUrl1 = 'http://127.0.0.1:8000/api/topic/'
function TopicVideos() {
    const [topicData, setTopicData] = useState([]);
    const [totaTopic, setTotalTopic] = useState(0);
    let { course_id } = useParams();
    useEffect(() => {
        try {
            axios.get(baseUrl + course_id)
                .then((res) => {
                    setTotalTopic(res.data.length)
                    setTopicData(res.data);
                });
        }
        catch (error) {
            console.log(error);
        }
    }, []);
    const handleDeleteClick = (topic_id) => {
        Swal.fire({
            title: 'Confirm',
            text: 'Are you sure you want to delete the video',
            icon: 'question',
            iconColor: 'red',
            confirmButtonText: 'Continue',
            showCancelButton: true
        }).then((result) => {
            if (result.isConfirmed) {
                try {
                    axios.delete(baseUrl1 + topic_id)
                        .then((result) => {
                            Swal.fire('success', 'Data has been deleted')
                            try {
                                axios.get(baseUrl + course_id)
                                    .then((res) => {
                                        setTotalTopic(res.data.length)
                                        setTopicData(res.data);
                                    });
                            }
                            catch (error) {
                                console.log(error);
                            }
                        });

                } catch (err) {
                    Swal.fire('Data has not been deleted !!');
                }
            } else {
                Swal.fire('Data has not been deleted');
            }
        });
    }

    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">All Topic Videos - ({totaTopic})</div>
                        <div className="card-body">
                            {topicData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Video Link</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {topicData.map((topic, index) =>

                                            <tr>
                                                <td>{topic.title}</td>
                                                <td><a href={topic.url}>{topic.url}</a></td>
                                                <td>
                                                    <button onClick={() => handleDeleteClick(topic.id)} className="btn btn-sm active btn-danger ms-1">
                                                        Delete
                                                    </button>
                                                </td>
                                            </tr>

                                        )}
                                    </tbody>
                                </table>
                            }
                            {topicData.length === 0 &&
                                <h2 className="text-danger">There is/are no video(s) added yet</h2>
                            }
                        </div>
                    </div>
                </section>
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
            </div>
        </div>
    )
}

export default TopicVideos;