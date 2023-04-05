import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import MSidebar from "./MSidebar";
import Swal from "sweetalert2";

const baseUrl = 'http://127.0.0.1:8000/api/all_topic_videos/';
const baseUrl1 = 'http://127.0.0.1:8000/api/topic/'
function TopicVideos() {
    const [topicData, setTopicData] = useState([]);
    const [totaTopic, setTotalTopic] = useState(0);
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    let { course_id } = useParams();
    useEffect(() => {
        if (topicData.length === 0) {
            try {
                axios.get(baseUrl + course_id, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setTotalTopic(res.data.length)
                        setTopicData(res.data);
                    }).catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) { // Unauthorized - access token expired
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    // Update the access token in localStorage or state
                                    localStorage.setItem('accessToken', newAccessToken);
                                    // Make the original request again with the new access token
                                    axios.get(baseUrl + course_id, {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setTotalTopic(res.data.length)
                                            setTopicData(res.data);
                                        })
                                        .catch((error) => {
                                            console.log(error.response);
                                        });
                                })
                                .catch((error) => {
                                    if (error.response.status === 401 && error.response.data.code === 'token_not_valid') { // Unauthorized - refresh token expired
                                        window.location.href = '/user-logout';
                                    }
                                });
                        }
                        else {
                            console.log(error);
                        }
                    });
            }
            catch (error) {
                console.log(error);
            }
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
                    axios.delete(baseUrl1 + topic_id, {
                        headers: { 'Authorization': `Bearer ${accessToken}` }
                    })
                        .then(() => {
                            Swal.fire('success', 'Data has been deleted')
                            try {
                                axios.get(baseUrl + course_id, {
                                    headers: { 'Authorization': `Bearer ${accessToken}` }
                                })
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
                                                    <Link to={`/master_edit_video/` + topic.id} className="btn btn-sm active btn-success ms-1">
                                                        <i class="bi bi-pencil-square"></i>
                                                    </Link>
                                                    <button onClick={() => handleDeleteClick(topic.id)} className="btn btn-sm active btn-danger ms-1">
                                                        <i class="bi bi-trash3"></i>
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