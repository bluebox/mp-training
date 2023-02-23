import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const baseUrl = 'http://127.0.0.1:8000/api/allcourse/?result=4';
const baseUrl2 = 'http://127.0.0.1:8000/api/get-reviews/';
function Home() {
    const [courseData, setCourseData] = useState([]);
    const [popularCourseData, setPopularCourseData] = useState([]);
    const [testimonialData, setTestimonialData] = useState([]);
    useEffect(() => {
        document.title = 'Home Page';
        try {
            axios.get(baseUrl)
                .then((res) => {
                    setCourseData(res.data.results);
                });
        }
        catch (error) {
            console.log(error);
        }
        // fetch student rating
        try {
            axios.get(baseUrl2)
                .then((res) => {
                    setTestimonialData(res.data);
                });
        }
        catch (error) {
            console.log(error);
        }

    }, []);
    return (
        <div className="container mt-4">
            {/*Latest Courses*/}
            <h3 className="pb-1 mb-2">Latest Courses <Link to="/all-courses" className="btn btn-primary float-end mb-10">See All</Link></h3>
            <div className="row mb-4">
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
            {/* Student Testimonial*/}
            <h3 className="pb-1 mb-2 mt-5">Student Testimonial</h3>
            <div id="carouselExampleIndicators" className="carousel slide bg-dark text-white py-5" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    {testimonialData && testimonialData.map((row,index) => 
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to={index} className={index === 0 ? 'active' : ""}></button>
                    )}
                </div>
                <div className="carousel-inner">
                    {testimonialData && testimonialData.map((row, index) => 
                    <div className={index === 0 ? 'carousel-item text-center active': 'carousel-item text-center'}>
                        <figure class="text-center">
                            <blockquote class="blockquote">
                                <p>{row.review}</p>
                            </blockquote>
                            <figcaption class="blockquote-footer">
                                <b>Course - </b>{row.course.title} <cite title="Source Title"><b>/ Review By :- </b> {row.student.first_name}</cite>
                            </figcaption>
                        </figure>
                    </div>
                    )}
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
            {/*End Student Testimonial */}
        </div>
    )
}

export default Home;