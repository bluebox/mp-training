// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { SUBJECTS, TEACHERSUBJECTSTUDENTS, RESULTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function ResultsUpload({ userId }) {
//     const [studentsMap, setStudentsMap] = useState({});
//     const [availableSubjects, setAvailableSubjects] = useState([]);
//     const [selectedSubjectId, setSelectedSubjectId] = useState("");
//     const [studentsForSubject, setStudentsForSubject] = useState([]);
//     const [selectedStudentId, setSelectedStudentId] = useState("");
//     const [grade, setGrade] = useState("");
//     const [percentage, setPercentage] = useState("");
//     const navigate = useNavigate();

//     useEffect(() => {
//         const fetchData = async () => {
//             try {
//             const res = await customAXIOS(
//                 TEACHERSUBJECTSTUDENTS,
//                 { id: userId },
//                 "get",
//                 null,
//                 navigate
//             );
//             setStudentsMap(res);

//             const allSubjects = await customAXIOS(SUBJECTS, null, "get", null, navigate);
//             const subjectIds = Object.keys(res);
//             const filteredSubjects = allSubjects.filter((s) =>
//                 subjectIds.includes(String(s.id))
//             );
//             setAvailableSubjects(filteredSubjects);
//             } catch (error) {
//             console.error("Error fetching data", error);
//             }
//         };

//         if (userId) fetchData();
//         console.log(studentsMap);
//     }, [userId, navigate]);


//     const handleSubjectChange = (e) => {
//         const subjectId = e.target.value;
//         setSelectedSubjectId(subjectId);
//         setSelectedStudentId("");
//         setGrade("");
//         setPercentage("");
//         if (studentsMap[subjectId]) {
//             setStudentsForSubject(studentsMap[subjectId]);
//         } else {
//             setStudentsForSubject([]);
//         }
//     };

//     const handleStudentChange = (e) => {
//         setSelectedStudentId(e.target.value);
//         setGrade("");
//         setPercentage("");
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         if (!selectedSubjectId || !selectedStudentId || grade === "" || percentage === "") {
//             alert("Please fill all fields before submitting.");
//             return;
//         }

//         const matchedEntry = studentsForSubject.find(
//             (entry) => Object.keys(entry)[0] === selectedStudentId
//         );
//         const classId = matchedEntry ? Object.values(matchedEntry)[0] : -1;

//         const payload = {
//             student: selectedStudentId,
//             subject: selectedSubjectId,
//             grade: Number(grade),
//             percentage: Number(percentage),
//             Class: classId,
//         };

//         try {
//             await customAXIOS(RESULTS, null, "post", payload, navigate);
//             alert("Result uploaded successfully!");
//             setGrade("");
//             setPercentage("");
//         } catch (err) {
//             console.error("Upload failed", err);
//         }
//     };


//     return (
//         <div>
//             <h1>Upload Student Results</h1>
//             <form onSubmit={handleSubmit}>
//                 <div>
//                     <label>Select Subject:</label>
//                     <select value={selectedSubjectId} onChange={handleSubjectChange}>
//                         <option value="">-- Select a Subject --</option>
//                         {availableSubjects.map((sub) => (
//                             <option key={sub.id} value={sub.id}>
//                                 {sub.Name}
//                             </option>
//                         ))}
//                     </select>
//                 </div>

//                 {selectedSubjectId && (
//                     <div style={{ marginTop: "1rem" }}>
//                         <label>Select Student:</label>
//                         <select value={selectedStudentId} onChange={handleStudentChange}>
//                             <option value="">-- Select a Student --</option>
//                             {studentsForSubject.map((student) => (
//                                 <option key={Object.keys(student)[0]} value={Object.keys(student)[0]}>
//                                     {console.log("student:",Object.keys(student)[0])}
//                                     {student.username || `Student ${Object.keys(student)[0]-10}`}
//                                 </option>
//                             ))}
//                         </select>
//                     </div>
//                 )}

//                 {selectedStudentId && (
//                     <div style={{ marginTop: "1rem" }}>
//                         <label>
//                             Grade:
//                             <input
//                                 type="number"
//                                 min="0"
//                                 max="10"
//                                 value={grade}
//                                 onChange={(e) => setGrade(e.target.value)}
//                             />
//                         </label>
//                         <br />
//                         <label>
//                             Percentage:
//                             <input
//                                 type="number"
//                                 step="0.01"
//                                 value={percentage}
//                                 onChange={(e) => setPercentage(e.target.value)}
//                             />
//                         </label>
//                         <br />
//                         <button type="submit" className="submit-buttons">Submit Result</button>
//                     </div>
//                 )}
//             </form>
//         </div>
//     );
// }

// export default ResultsUpload;

// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { SUBJECTS, TEACHERSUBJECTSTUDENTS, RESULTS, ALLCLASSES, ALLSUBJECTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function ResultsUpload({ userId }) {
//     const [studentsMap, setStudentsMap] = useState({});
//     const [allSubjects, setAllSubjects] = useState([]);
//     const [availableClasses, setAvailableClasses] = useState([]);
//     const [selectedClassId, setSelectedClassId] = useState("");
//     const [availableSubjects, setAvailableSubjects] = useState([]);
//     const [selectedSubjectId, setSelectedSubjectId] = useState("");
//     const [studentsForSubject, setStudentsForSubject] = useState([]);
//     const [selectedStudentId, setSelectedStudentId] = useState("");
//     const [grade, setGrade] = useState("");
//     const [percentage, setPercentage] = useState("");
//     const navigate = useNavigate();

//     useEffect(() => {
//         const fetchData = async () => {
//             try {
//                 const [studentMapRes, subjectRes, classesRes] = await Promise.all([
//                     customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, "get", null, navigate),
//                     customAXIOS(ALLSUBJECTS, null, "get", null, navigate),
//                     customAXIOS(ALLCLASSES, null, "get", null, navigate),
//                 ]);
//                 const class_match_array = []
//                 const subject_match_array = []
//                 console.log(classesRes)
//                 console.log(subjectRes)
//                 for(let item of studentMapRes){
//                     for(let class_item of classesRes){
//                         if(item.class_id === class_item.id){
//                             class_match_array.push(class_item)
//                         }
//                     }
//                     for(let subject_item of subjectRes){
//                         if(item.subject_id.includes(subject_item.id) && !subject_match_array.includes(subject_item.id)){
//                             subject_match_array.push(subject_item)
//                         }
//                     }
//                 }
//                 setStudentsMap(studentMapRes);
//                 setAllSubjects(subject_match_array);
//                 setAvailableClasses(class_match_array);
//                 console.log("map:",studentMapRes)
//                 console.log("subjects:",subject_match_array)
//                 console.log("classesRes:",class_match_array)
//             } catch (error) {
//                 console.error("Error fetching data", error);
//             }
//         };

//         if (userId) fetchData();
//     }, [userId, navigate]);

//     const handleClassChange = (e) => {
//         const classId = e.target.value;
//         setSelectedClassId(classId);
//         setSelectedSubjectId("");
//         setSelectedStudentId("");
//         setGrade("");
//         setPercentage("");

//         // // Subjects available for this class
//         // const subjectIds = Object.keys(studentsMap).filter(subId =>
//         //     studentsMap[subId]?.some(s => Object.values(s)[0] === parseInt(classId))
//         // );

//         // const filteredSubjects = allSubjects.filter(sub => subjectIds.includes(String(sub.id)));
//         // setAvailableSubjects(filteredSubjects);
//         const filteredSubjects = []
//         for(let map_item of studentsMap){
//             if(map_item.Class_id === classId){
//                 filteredSubjects = map_item.subject_id
//             }
//         }
//         setAvailableSubjects(filteredSubjects)
//     };

//     const handleSubjectChange = (e) => {
//         const subjectId = e.target.value;
//         setSelectedSubjectId(subjectId);
//         setSelectedStudentId("");
//         setGrade("");
//         setPercentage("");

//         // const filteredStudents = studentsMap[subjectId]?.filter(s => {
//         //     const classId = Object.values(s)[0];
//         //     return classId === parseInt(selectedClassId);
//         // }) || [];
//         const filteredStudents = []
//         for(let map_item of studentsMap){
//             if(map_item.Class_id === selectedClassId){
//                 filteredStudents = map_item.students
//             }
//         }
//         setStudentsForSubject(filteredStudents);
//     };

//     const handleStudentChange = (e) => {
//         setSelectedStudentId(e.target.value);
//         setGrade("");
//         setPercentage("");
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         if (!selectedSubjectId || !selectedStudentId || grade === "" || percentage === "" || !selectedClassId) {
//             alert("Please fill all fields before submitting.");
//             return;
//         }

//         const payload = {
//             student: selectedStudentId,
//             subject: selectedSubjectId,
//             grade: Number(grade),
//             percentage: Number(percentage),
//             Class: selectedClassId,
//         };

//         try {
//             await customAXIOS(RESULTS, null, "post", payload, navigate);
//             alert("Result uploaded successfully!");
//             setGrade("");
//             setPercentage("");
//         } catch (err) {
//             console.error("Upload failed", err);
//         }
//     };

//     return (
//         <div>
//             <h1>Upload Student Results</h1>
//             <form onSubmit={handleSubmit}>
//                 <div>
//                     <label>Select Class:</label>
//                     <select value={selectedClassId} onChange={handleClassChange}>
//                         <option value="">-- Select Class --</option>
//                         {?.map(cls => (
//                             <option key={cls.id} value={cls.id}>
//                                 {cls.Class_id} - {cls.Section}
//                             </option>
//                         ))}
//                     </select>
//                 </div>

//                 {selectedClassId && (
//                     <div>
//                         <label>Select Subject:</label>
//                         <select value={selectedSubjectId} onChange={handleSubjectChange}>
//                             <option value="">-- Select Subject --</option>
//                             {availableSubjects.map((sub) => (
//                                 <option key={sub.id} value={sub.id}>
//                                     {sub.Name}
//                                 </option>
//                             ))}
//                         </select>
//                     </div>
//                 )}

//                 {selectedSubjectId && (
//                     <div>
//                         <label>Select Student:</label>
//                         <select value={selectedStudentId} onChange={handleStudentChange}>
//                             <option value="">-- Select Student --</option>
//                             {studentsForSubject.map((student) => {
//                                 const id = Object.keys(student)[0];
//                                 return (
//                                     <option key={id} value={id}>
//                                         Student {id}
//                                     </option>
//                                 );
//                             })}
//                         </select>
//                     </div>
//                 )}

//                 {selectedStudentId && (
//                     <div style={{ marginTop: "1rem" }}>
//                         <label>
//                             Grade:
//                             <input
//                                 type="number"
//                                 min="0"
//                                 max="10"
//                                 value={grade}
//                                 onChange={(e) => setGrade(e.target.value)}
//                             />
//                         </label>
//                         <br />
//                         <label>
//                             Percentage:
//                             <input
//                                 type="number"
//                                 step="0.01"
//                                 value={percentage}
//                                 onChange={(e) => setPercentage(e.target.value)}
//                             />
//                         </label>
//                         <br />
//                         <button type="submit" className="submit-buttons">Submit Result</button>
//                     </div>
//                 )}
//             </form>
//         </div>
//     );
// }

// export default ResultsUpload;

import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLSUBJECTS, ALLCLASSES, RESULTS, TEACHERSUBJECTSTUDENTS, STUDENT, ALLSTUDENTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ResultsUpload({ userId }) {
    const [teacherMap, setTeacherMap] = useState([]);
    const [allSubjects, setAllSubjects] = useState([]);
    const [allClasses, setAllClasses] = useState([]);
    const [studentDetails, setStudentDetails] = useState({});  // { studentId: { name, ... } }

    const [selectedClassId, setSelectedClassId] = useState("");
    const [availableSubjects, setAvailableSubjects] = useState([]);
    const [selectedSubjectId, setSelectedSubjectId] = useState("");
    const [studentsForSubject, setStudentsForSubject] = useState([]);
    const [selectedStudentId, setSelectedStudentId] = useState("");
    const [grade, setGrade] = useState("");
    const [percentage, setPercentage] = useState("");
    const navigate = useNavigate();

   useEffect(() => {
    const fetchData = async () => {
        try {
            const [mapRes, subjectRes, classRes] = await Promise.all([
                customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, "get", null, navigate),
                customAXIOS(ALLSUBJECTS, null, "get", null, navigate),
                customAXIOS(ALLCLASSES, null, "get", null, navigate),
            ]);

            // Extract only class IDs the teacher teaches
            const teacherClassIds = [...new Set(mapRes.map(item => item.class_id))];

            // Filter only classes taught by this teacher
            const filteredClasses = classRes.filter(cls => teacherClassIds.includes(cls.id));

            // Extract all student IDs to fetch details
            const allStudentIds = [...new Set(mapRes.flatMap(entry => entry.students))];
            const studentDetailPromises = allStudentIds.map(id =>
                customAXIOS(ALLSTUDENTS, {id:id}, "get", null, navigate)
            );
            const studentData = await Promise.all(studentDetailPromises);
            const studentMap = {};
            studentData.forEach(student => {
                studentMap[student.id] = student;
            });

            setTeacherMap(mapRes);
            setAllSubjects(subjectRes);
            setAllClasses(filteredClasses);  // only teacher’s classes
            setStudentDetails(studentMap);
        } catch (err) {
            console.error("Error fetching data", err);
        }
    };

    if (userId) fetchData();
}, [userId, navigate]);


    const handleClassChange = (e) => {
        const classId = Number(e.target.value);
        setSelectedClassId(classId);
        setSelectedSubjectId("");
        setSelectedStudentId("");
        setGrade("");
        setPercentage("");

        // Find matching subjects
        const entry = teacherMap.find(item => item.class_id === classId);
        if (entry) {
            const subjects = allSubjects.filter(sub => entry.subject_id.includes(sub.id));
            setAvailableSubjects(subjects);
        } else {
            setAvailableSubjects([]);
        }
    };

    const handleSubjectChange = (e) => {
        const subjectId = Number(e.target.value);
        setSelectedSubjectId(subjectId);
        setSelectedStudentId("");
        setGrade("");
        setPercentage("");

        const entry = teacherMap.find(item =>
            item.class_id === selectedClassId && item.subject_id.includes(subjectId)
        );
        if (entry) {
            setStudentsForSubject(entry.students);
        } else {
            setStudentsForSubject([]);
        }
    };

    const handleStudentChange = (e) => {
        setSelectedStudentId(e.target.value);
        setGrade("");
        setPercentage("");
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedClassId || !selectedSubjectId || !selectedStudentId || grade === "" || percentage === "") {
            alert("Please fill all fields before submitting.");
            return;
        }

        const payload = {
            student: selectedStudentId,
            subject: selectedSubjectId,
            grade: Number(grade),
            percentage: Number(percentage),
            Class: selectedClassId,
        };

        try {
            await customAXIOS(RESULTS, null, "post", payload, navigate);
            alert("Result uploaded successfully!");
            setGrade("");
            setPercentage("");
        } catch (err) {
            console.error("Upload failed", err);
        }
    };

    return (
        <div>
            <h1>Upload Student Results</h1>
            <form onSubmit={handleSubmit}>
                {/* Class Dropdown */}
                <div>
                    <label>Select Class:</label>
                    <select value={selectedClassId} onChange={handleClassChange}>
                        <option value="">-- Select Class --</option>
                        {allClasses.map(cls => (
                            <option key={cls.id} value={cls.id}>
                                {cls.Class_id} - {cls.Section}
                            </option>
                        ))}
                    </select>
                </div>

                {/* Subject Dropdown */}
                {selectedClassId && (
                    <div>
                        <label>Select Subject:</label>
                        <select value={selectedSubjectId} onChange={handleSubjectChange}>
                            <option value="">-- Select Subject --</option>
                            {availableSubjects.map(sub => (
                                <option key={sub.id} value={sub.id}>
                                    {sub.Name}
                                </option>
                            ))}
                        </select>
                    </div>
                )}

                {/* Student Dropdown */}
                {selectedSubjectId && (
                    <div>
                        <label>Select Student:</label>
                        <select value={selectedStudentId} onChange={handleStudentChange}>
                            <option value="">-- Select Student --</option>
                            {studentsForSubject.map(id => (
                                <option key={id} value={id}>
                                    {studentDetails[id]?.name || `Student ${id}`}
                                </option>
                            ))}
                        </select>
                    </div>
                )}

                {/* Grade and Percentage */}
                {selectedStudentId && (
                    <div style={{ marginTop: "1rem" }}>
                        <label>
                            Grade:
                            <input
                                type="number"
                                min="0"
                                max="10"
                                value={grade}
                                onChange={(e) => setGrade(e.target.value)}
                            />
                        </label>
                        <br />
                        <label>
                            Percentage:
                            <input
                                type="number"
                                step="0.01"
                                value={percentage}
                                onChange={(e) => setPercentage(e.target.value)}
                            />
                        </label>
                        <br />
                        <button type="submit" className="submit-buttons">Submit Result</button>
                    </div>
                )}
            </form>
        </div>
    );
}

export default ResultsUpload;
