import React from "react";
import { Link, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
const baseUrl = 'http://127.0.0.1:8000/api/search-courses/';
function Search() {
    const [courseData, setCourseData] = useState([]);
    const [nextPage, setNextPage] = useState();
    const [prevPage, setPrevPage] = useState();
    const { searchData } = useParams();
    useEffect(() => {
        if (courseData.length === 0) {
            try {
                axios.get(baseUrl + searchData + '/')
                    .then((res) => {
                        setNextPage(res.data.next);
                        setPrevPage(res.data.previous);
                        setCourseData(res.data.results);
                    });
            }
            catch (error) {
                console.log(error);
            }
        }
    }, [courseData]);
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
            <h3 className="pb-1 mb-2">Searched For<span className="text-primary ms-2">{searchData}</span></h3>
            <div className="row">
                {courseData && courseData.map((course, index) =>
                    <div className="col-md-3 mb-4 ">
                        <div className="card">
                            <Link to={`/detail/${course.id}`}><img src={course.image} width="200" height="200" alt={course.title} className="card-img-top" /></Link>
                            <div className="card-body">
                                <h5 className="card-title"><Link to={`/detail/${course.id}`}>{course.title}</Link></h5>
                            </div>
                        </div>
                    </div>
                )}
                {courseData.length === 0 &&
                    <h2 className="text-danger">No Courses Found</h2>
                }
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

export default Search;