// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { SUBJECTS, TEACHERSUBJECTSTUDENTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function ResultsUpload({userId}){

//     const [students,setStudents] = useState(null)
//     const [subjects,setSubjects] = useState(null)
//     navigator = useNavigate()
//     useEffect(()=>{
//         const fetchData = async()=>{

//             result = await customAXIOS(TEACHERSUBJECTSTUDENTS,{id:userId},'get',null,navigator)
//             setStudents(Object.keys(result));
//             data = []
//             subjects = await customAXIOS(SUBJECTS,null,'get',null, navigator)
//             for(let i of Object.keys(result)){
//                 data.push(subjects[i])
//             }
//             setSubjects(data);
//         }
        
//     },[userId])

//     useEffect(()=>{
//         customAXIOS(SUBJECTS,null,'get',null,navigator)
//         .then(res=>{
//             setStudents(res)
//         })
//     },[])

//     return(
//         <div>
//             <h1>Results Upload</h1>
//             <form>
//                 <label>Subject
//                     <select name="subject">
//                         {subjects.map(sub=>{
//                             <option>sub</option>
//                         })}
//                     </select>
//                 </label>
//             </form>
//         </div>
//     )
// }

// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { SUBJECTS, TEACHERSUBJECTSTUDENTS, RESULTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function ResultsUpload({ userId }) {
//     const [studentsMap, setStudentsMap] = useState({});
//     const [availableSubjects, setAvailableSubjects] = useState([]); // {id, Name}
//     const [selectedSubjectId, setSelectedSubjectId] = useState("");
//     const [results, setResults] = useState({}); // {student_id: {grade, percentage}}

//     const navigate = useNavigate();

//     useEffect(() => {
//         const fetchData = async () => {
//             try {
//                 const subjectStudentMap = await customAXIOS(
//                     TEACHERSUBJECTSTUDENTS,
//                     { id: userId },
//                     "get",
//                     null,
//                     navigate
//                 );

//                 setStudentsMap(subjectStudentMap);

//                 const allSubjects = await customAXIOS(SUBJECTS, null, "get", null, navigate);
//                 const subjectIds = Object.keys(subjectStudentMap);
//                 const filteredSubjects = allSubjects.filter((s) =>
//                     subjectIds.includes(String(s.id))
//                 );
//                 setAvailableSubjects(filteredSubjects);
//             } catch (error) {
//                 console.error("Error fetching data", error);
//             }
//         };

//         if (userId) {
//             fetchData();
//         }
//     }, [userId]);

//     const handleSubjectChange = (e) => {
//         const id = e.target.value;
//         setSelectedSubjectId(id);
//         setResults({}); // Clear old results
//     };

//     const handleInputChange = (studentId, field, value) => {
//         setResults((prev) => ({
//             ...prev,
//             [studentId]: {
//                 ...prev[studentId],
//                 [field]: value,
//             },
//         }));
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         if (!selectedSubjectId) return;

//         const payload = Object.entries(results).map(([studentId, data]) => ({
//             student: studentId,
//             subject: selectedSubjectId,
//             grade: Number(data.grade),
//             percentage: Number(data.percentage),
//         }));

//         try {
//             for (let entry of payload) {
//                 await customAXIOS(RESULTS, null, "post", entry, navigate);
//             }
//             alert("Results uploaded successfully!");
//         } catch (err) {
//             console.error("Upload failed", err);
//         }
//     };

//     const students = studentsMap[selectedSubjectId] || [];

//     return (
//         <div>
//             <h1>Upload Results</h1>

//             <form onSubmit={handleSubmit}>
//                 <label>
//                     Select Subject:
//                     <select value={selectedSubjectId} onChange={handleSubjectChange}>
//                         <option value="">-- Select a Subject --</option>
//                         {availableSubjects.map((sub) => (
//                             <option key={sub.id} value={sub.id}>
//                                 {sub.Name}
//                             </option>
//                         ))}
//                     </select>
//                 </label>

//                 {selectedSubjectId && students.length > 0 && (
//                     <div style={{ marginTop: "1rem" }}>
//                         <h3>Enter Results:</h3>
//                         {students.map((s) => (
//                             <div key={s.user_id} style={{ marginBottom: "1rem" }}>
//                                 <strong>Student ID: {s.user_id}</strong>
//                                 <div>
//                                     <label>
//                                         Grade:{" "}
//                                         <input
//                                             type="number"
//                                             min="0"
//                                             max="10"
//                                             value={results[s.user_id]?.grade || ""}
//                                             onChange={(e) =>
//                                                 handleInputChange(s.user_id, "grade", e.target.value)
//                                             }
//                                         />
//                                     </label>
//                                 </div>
//                                 <div>
//                                     <label>
//                                         Percentage:{" "}
//                                         <input
//                                             type="number"
//                                             step="0.01"
//                                             value={results[s.user_id]?.percentage || ""}
//                                             onChange={(e) =>
//                                                 handleInputChange(s.user_id, "percentage", e.target.value)
//                                             }
//                                         />
//                                     </label>
//                                 </div>
//                             </div>
//                         ))}
//                         <button type="submit">Submit Results</button>
//                     </div>
//                 )}
//             </form>
//         </div>
//     );
// }

// export default ResultsUpload;

import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { SUBJECTS, TEACHERSUBJECTSTUDENTS, RESULTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ResultsUpload({ userId }) {
    const [studentsMap, setStudentsMap] = useState({});
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
                const subjectStudentMap = await customAXIOS(
                    TEACHERSUBJECTSTUDENTS,
                    { id: userId },
                    "get",
                    null,
                    navigate
                );
                setStudentsMap(subjectStudentMap);

                const allSubjects = await customAXIOS(SUBJECTS, null, "get", null, navigate);
                const subjectIds = Object.keys(subjectStudentMap);
                const filteredSubjects = allSubjects.filter((s) =>
                    subjectIds.includes(String(s.id))
                );
                setAvailableSubjects(filteredSubjects);
            } catch (error) {
                console.error("Error fetching data", error);
            }
        };

        if (userId) fetchData();
    }, [userId]);

    const handleSubjectChange = (e) => {
        const subjectId = e.target.value;
        setSelectedSubjectId(subjectId);
        setSelectedStudentId("");
        setGrade("");
        setPercentage("");
        if (studentsMap[subjectId]) {
            setStudentsForSubject(studentsMap[subjectId]);
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
        if (!selectedSubjectId || !selectedStudentId || grade === "" || percentage === "") {
            alert("Please fill all fields before submitting.");
            return;
        }

        const payload = {
            student: selectedStudentId,
            subject: selectedSubjectId,
            grade: Number(grade),
            percentage: Number(percentage),
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
                <div>
                    <label>Select Subject:</label>
                    <select value={selectedSubjectId} onChange={handleSubjectChange}>
                        <option value="">-- Select a Subject --</option>
                        {availableSubjects.map((sub) => (
                            <option key={sub.id} value={sub.id}>
                                {sub.Name}
                            </option>
                        ))}
                    </select>
                </div>

                {selectedSubjectId && (
                    <div style={{ marginTop: "1rem" }}>
                        <label>Select Student:</label>
                        <select value={selectedStudentId} onChange={handleStudentChange}>
                            <option value="">-- Select a Student --</option>
                            {studentsForSubject.map((student) => (
                                <option key={student.user_id} value={student.user_id}>
                                    {student.username || `Student ${student.user_id}`}
                                </option>
                            ))}
                        </select>
                    </div>
                )}

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
                        <button type="submit">Submit Result</button>
                    </div>
                )}
            </form>
        </div>
    );
}

export default ResultsUpload;
