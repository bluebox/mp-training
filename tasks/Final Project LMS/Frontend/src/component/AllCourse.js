import React from "react";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
const baseUrl = 'http://127.0.0.1:8000/api/allcourse/';
function AllCourses() {
    const [courseData, setCourseData] = useState([]);
    const [nextPage, setNextPage] = useState();
    const [prevPage, setPrevPage] = useState();
    useEffect(() => {
        try {
            axios.get(baseUrl)
                .then((res) => {
                    setNextPage(res.data.next);
                    setPrevPage(res.data.previous);
                    setCourseData(res.data.results);
                });
        }
        catch (error) {
            console.log(error);
        }
    }, []);
    const handlePagination = (url) => {
        try {
            axios.get(url)
                .then((res) => {
                    setNextPage(res.data.next)
                    setPrevPage(res.data.previous)
                    setCourseData(res.data.results);
                });
        }
        catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="container mt-4">
            <h3 className="pb-1 mb-2">All Courses</h3>
            <div className="row">
                {courseData && courseData.map((course, index) =>
                    <div className="col-md-3 mb-4 ">
                        <div className="card">
                            <Link to={`/detail/${course.id}`}><img src={course.image} width="200" height="200" className="card-img-top" /></Link>
                            <div className="card-body">
                                <h5 className="card-title"><Link to={`/detail/${course.id}`}>{course.title}</Link></h5>
                            </div>
                        </div>
                    </div>
                )}
            </div>
            <nav aria-label="Page navigation example mt-5">
                <ul className="pagination justify-content-center">
                    {prevPage &&
                        <li className="page-item"><button className="page-link" onClick={() => handlePagination(prevPage)} ><i class="bi bi-arrow-bar-left" ></i> Previous</button></li>
                    }
                    
                    {nextPage &&
                        <li className="page-item"><button className="page-link" onClick={() => handlePagination(nextPage)}>Next <i class="bi bi-arrow-bar-right"></i></button></li>
                    }
                </ul>
            </nav>
        </div>
    )
}

export default AllCourses;