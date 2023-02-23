import axios from "axios";


export function getInstructors(){
    return axios.get('http://127.0.0.1:8000/api/teacher/')
    .then(response => response.data)
}

export function addInstructor(teacher){
    return axios.post('http://127.0.0.1:8000/api/teacher/',{
        id:null,
        first_name:teacher.first_name.value,
        last_name:teacher.last_name.value,
        email:teacher.email.value,
        password:teacher.password.value,
        qualification:teacher.qualification.value,
        designation:teacher.designation.value,
        mobile_no:teacher.mobile_no.value,
        skills:teacher.skills.value,
    },{headers:{
        'content-type':'multipart/form-data'
    }})
    .then(response => response.data)
}

export function updateInstructor(teacher, insId){
    return axios.put('http://127.0.0.1:8000/api/teacher/'+ insId + '/',{
        id:null,
        first_name:teacher.first_name.value,
        last_name:teacher.last_name.value,
        email:teacher.email.value,
        password:teacher.password.value,
        qualification:teacher.qualification.value,
        designation:teacher.designation.value,
        mobile_no:teacher.mobile_no.value,
        skills:teacher.skills.value,
    },{headers:{
        'content-type':'multipart/form-data'
    }})
    .then(response => response.data)
}

export function updateStudent(student, insId){
    return axios.put('http://127.0.0.1:8000/api/student/'+ insId + '/',{
        id:null,
        first_name:student.first_name.value,
        last_name:student.last_name.value,
        email:student.email.value,
        password:student.password.value,
        qualification:student.qualification.value,
        interested_category:student.interested_category.value,
        mobile_no:student.mobile_no.value,
        address:student.address.value,
    },{headers:{
        'content-type':'multipart/form-data'
    }})
    .then(response => response.data)
}
export function addStudent(student){
    return axios.post('http://127.0.0.1:8000/api/student/post/',{
        id:null,
        first_name:student.first_name.value,
        last_name:student.last_name.value,
        email:student.email.value,
        password:student.password.value,
        qualification:student.qualification.value,
        interested_category:student.interested_category.value,
        mobile_no:student.mobile_no.value,
        address:student.address.value,
    },{headers:{
        'content-type':'multipart/form-data'
    }})
    .then(response => response.data)
}

export function addCourse(course){
    const teacherId=localStorage.getItem('teacherId');
    return axios.post("http://127.0.0.1:8000/api/course/",
    {
        id:null,
        title:course.title.value,
        description:course.description.value,
        teacher:teacherId,
        subcategory:course.subcategory.value,
        language:course.language.value,
        image:course.picture,
    })
    .then(response => response.data)
}

export function addTopic(topic, course_id){
    
    return axios.post("http://127.0.0.1:8000/api/topic/",
    {
        id:null,
        title:topic.title.value,
        course:course_id,
        url:topic.url.value
    })
    .then(response => response.data)
}


export function addReview(rating, course_id, student_id){
    
    return axios.post("http://127.0.0.1:8000/api/course-rating-post/",
    {
        id:null,
        course:course_id,
        student: student_id,
        rating:rating.rating.value,
        review: rating.review.value
    })
    .then(response => response)
}

export function studentEnrollCourse(student_id, course_id){
    return axios.post("http://127.0.0.1:8000/api/enroll_course/",
    {
        id:null,
        course:course_id,
        student:student_id,
    })
    .then(response => response.data)
}

export function studentFavouriteCourse(student_id, course_id){
    return axios.post("http://127.0.0.1:8000/api/add-favorite-course/",
    {
        course:course_id,
        student:student_id,
        status:true
    })
    .then(response => response)
}


export function studentRemoveFavouriteCourse(student_id, course_id){
    return axios.get('http://127.0.0.1:8000/api/remove-favorite-course/'+ student_id+'/'+course_id)
    .then(response => response)

}