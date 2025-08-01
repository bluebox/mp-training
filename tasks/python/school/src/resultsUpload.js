import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLSUBJECTS, ALLCLASSES, RESULTS, TEACHERSUBJECTSTUDENTS, STUDENT, ALLSTUDENTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ResultsUpload({ userId }) {
    const [teacherMap, setTeacherMap] = useState([]);
    const [allSubjects, setAllSubjects] = useState([]);
    const [allClasses, setAllClasses] = useState([]);
    const [studentDetails, setStudentDetails] = useState({});  

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

            const teacherClassIds = [...new Set(mapRes.map(item => item.class_id))];
            const filteredClasses = classRes.filter(cls => teacherClassIds.includes(cls.id));
            const allStudentIds = [...new Set(mapRes.flatMap(entry => entry.students))];
            const res = await customAXIOS(ALLSTUDENTS,null,'get',null,navigate)
            
            // const studentDetailPromises = allStudentIds.map(id =>{
                
            // }
                // customAXIOS(ALLSTUDENTS, {id:id}, "get", null, navigate)
            // );
            const studentData = allStudentIds
                .map(id => res.find(item => item.id === id))
                .filter(Boolean);
            const studentMap = {};
            studentData.forEach(student => {
                studentMap[student.id] = student;
            });

            setTeacherMap(mapRes);
            setAllSubjects(subjectRes);
            setAllClasses(filteredClasses); 
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
            setSelectedClassId("")
            setSelectedSubjectId("")
            setSelectedStudentId("")
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
