import axios from "axios";
const accessToken = localStorage.getItem('accessToken');
const refreshToken = localStorage.getItem('refreshToken');

export function getInstructors() {
    return axios.get('http://127.0.0.1:8000/api/teacher/')
        .then(response => response.data)
}

export function addInstructor(teacher) {
    console.log(teacher.username.value);
    return axios.post('http://127.0.0.1:8000/api/teacher/register/', {
        username: teacher.username.value,
        first_name: teacher.first_name.value,
        last_name: teacher.last_name.value,
        email: teacher.email.value,
        password: teacher.password.value,
        qualification: teacher.qualification.value,
        designation: teacher.designation.value,
        mobile_no: teacher.mobile_no.value,
        skills: teacher.skills.value,
    }, {
        headers: {
            'content-type': 'multipart/form-data',
        }
    })
        .then(response => response.data)
}

export function updateInstructor(teacher, insId) {
    axios.put('http://127.0.0.1:8000/api/teacher/' + insId + '/', {
        username: teacher.username.value,
        first_name: teacher.first_name.value,
        last_name: teacher.last_name.value,
        email: teacher.email.value,
        password: teacher.password.value,
        qualification: teacher.qualification.value,
        designation: teacher.designation.value,
        mobile_no: teacher.mobile_no.value,
        skills: teacher.skills.value,
    }, {
        headers: {
            'content-type': 'multipart/form-data', 'Authorization': `Bearer ${accessToken}`
        }
    }).then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        return axios.put("http://127.0.0.1:8000/api/teacher/" + insId + '/', {
                            username: teacher.username.value,
                            first_name: teacher.first_name.value,
                            last_name: teacher.last_name.value,
                            email: teacher.email.value,
                            password: teacher.password.value,
                            qualification: teacher.qualification.value,
                            designation: teacher.designation.value,
                            mobile_no: teacher.mobile_no.value,
                            skills: teacher.skills.value,
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}
export function updateCourse(course, courseId, picture) {
    const config = {
        headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': `Bearer ${accessToken}`
        }
    }
    return axios.put('http://127.0.0.1:8000/api/course/' + courseId + '/', {
        id: null,
        title: course.title.value,
        description: course.description.value,
        language: course.language.value,
        image: picture,
    }, config)
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        return axios.post("http://127.0.0.1:8000/api/course/" + courseId + '/', {
                            title: course.title.value,
                            description: course.description.value,
                            language: course.language.value,
                            image: picture,
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}
export function updateTopic(topic, topicId) {
    return axios.put('http://127.0.0.1:8000/api/topic/' + topicId + '/', {
        title: topic.title.value,
        url: topic.url.value
    }, {
        headers: {
            'Authorization': `Bearer ${accessToken}`
        }
    })
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        return axios.post("http://127.0.0.1:8000/api/topic/" + topicId + '/', {
                            title: topic.title.value,
                            url: topic.url.value,
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}
export function updateStudent(student, insId) {
    return axios.put('http://127.0.0.1:8000/api/student/' + insId + '/', {
        username: student.username.value,
        first_name: student.first_name.value,
        last_name: student.last_name.value,
        email: student.email.value,
        password: student.password.value,
        qualification: student.qualification.value,
        interested_category: student.interested_category.value,
        mobile_no: student.mobile_no.value,
        address: student.address.value,
    }, {
        headers: {
            'Authorization': `Bearer ${accessToken}`
        }
    })
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        return axios.post("http://127.0.0.1:8000/api/student/" + insId + '/', {
                            username: student.username.value,
                            first_name: student.first_name.value,
                            last_name: student.last_name.value,
                            email: student.email.value,
                            password: student.password.value,
                            qualification: student.qualification.value,
                            interested_category: student.interested_category.value,
                            mobile_no: student.mobile_no.value,
                            address: student.address.value,
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}
export function addStudent(student) {
    return axios.post('http://127.0.0.1:8000/api/student/register/', {
        username: student.username.value,
        first_name: student.first_name.value,
        last_name: student.last_name.value,
        email: student.email.value,
        password: student.password.value,
        qualification: student.qualification.value,
        interested_category: student.interested_category.value,
        mobile_no: student.mobile_no.value,
        address: student.address.value,
    }, {
        headers: {
            'content-type': 'multipart/form-data'
        }
    })
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        return axios.post("http://127.0.0.1:8000/student/register/", {
                            headers: { 'Authorization': `Bearer ${newAccessToken}` }
                        }, {
                            username: student.username.value,
                            first_name: student.first_name.value,
                            last_name: student.last_name.value,
                            email: student.email.value,
                            password: student.password.value,
                            qualification: student.qualification.value,
                            interested_category: student.interested_category.value,
                            mobile_no: student.mobile_no.value,
                            address: student.address.value,
                        })
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}

export function addCourse(course, picture) {
    const config = {
        headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': `Bearer ${accessToken}`
        }
    }
    const userId = localStorage.getItem('teacherId');
    return axios.post("http://127.0.0.1:8000/api/course/",
        {
            title: course.title.value,
            description: course.description.value,
            teacher: userId,
            subcategory: course.subcategory.value,
            language: course.language.value,
            image: picture,
        }, config)
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        const config = {
                            headers: {
                                'Content-Type': 'multipart/form-data', 'Authorization': `Bearer ${newAccessToken}`
                            }
                        }
                        return axios.post("http://127.0.0.1:8000/api/course/", {
                            title: course.title.value,
                            description: course.description.value,
                            teacher: userId,
                            subcategory: course.subcategory.value,
                            language: course.language.value,
                            image: picture,
                        }, config)
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}

export function addTopic(topic, course_id) {
    const config = {
        headers: {
            'Authorization': `Bearer ${accessToken}`
        }
    }
    return axios.post("http://127.0.0.1:8000/api/topic/",
        {
            title: topic.title.value,
            course: course_id,
            url: topic.url.value
        }, config)
        .then(response => response.data)
        .catch((error) => {
            if (error.response.status === 401) {
                axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                    .then((response) => {
                        const newAccessToken = response.data.access;
                        localStorage.setItem('accessToken', newAccessToken);
                        const config = {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        }
                        return axios.post("http://127.0.0.1:8000/api/topic/",
                            {
                                title: topic.title.value,
                                course: course_id,
                                url: topic.url.value
                            }, config)
                            .then(response => response.data)
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch((error) => {
                        if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                            window.location.href = '/user-logout';
                        }
                    });
            }
        });
}


export function addReview(rating, course_id, student_id) {

    return axios.post("http://127.0.0.1:8000/api/course-rating-post/",
        {
            id: null,
            course: course_id,
            student: student_id,
            rating: rating.rating.value,
            review: rating.review.value
        })
        .then(response => response)
}

export function studentEnrollCourse(student_id, course_id) {
    return axios.post("http://127.0.0.1:8000/api/enroll_course/",
        {
            course: course_id,
            student: student_id,
        })
        .then(response => response.data)
}

export function studentFavouriteCourse(student_id, course_id) {
    return axios.post("http://127.0.0.1:8000/api/add-favorite-course/",
        {
            course: course_id,
            student: student_id,
            status: true
        })
        .then(response => response)
}


export function studentRemoveFavouriteCourse(student_id, course_id) {
    return axios.get('http://127.0.0.1:8000/api/remove-favorite-course/' + student_id + '/' + course_id)
        .then(response => response)

}