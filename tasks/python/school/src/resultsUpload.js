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
            const res = await customAXIOS(
                TEACHERSUBJECTSTUDENTS,
                { id: userId },
                "get",
                null,
                navigate
            );
            setStudentsMap(res);

            const allSubjects = await customAXIOS(SUBJECTS, null, "get", null, navigate);
            const subjectIds = Object.keys(res);
            const filteredSubjects = allSubjects.filter((s) =>
                subjectIds.includes(String(s.id))
            );
            setAvailableSubjects(filteredSubjects);
            } catch (error) {
            console.error("Error fetching data", error);
            }
        };

        if (userId) fetchData();
        console.log(studentsMap);
    }, [userId, navigate]);


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

        const matchedEntry = studentsForSubject.find(
            (entry) => Object.keys(entry)[0] === selectedStudentId
        );
        const classId = matchedEntry ? Object.values(matchedEntry)[0] : -1;

        const payload = {
            student: selectedStudentId,
            subject: selectedSubjectId,
            grade: Number(grade),
            percentage: Number(percentage),
            Class: classId,
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
                                <option key={Object.keys(student)[0]} value={Object.keys(student)[0]}>
                                    {console.log("student:",Object.keys(student)[0])}
                                    {student.username || `Student ${Object.keys(student)[0]-10}`}
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
