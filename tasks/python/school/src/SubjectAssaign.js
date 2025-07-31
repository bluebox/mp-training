import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLCLASSES, ALLSUBJECTS, SUBJECTTEACHERCLASS, ALLTEACHERS } from "./urls";
import { useNavigate } from "react-router-dom";

function SubjectAssaign() {
    const [classes, setClasses] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [relations, setRelations] = useState([]);

    const [selectedSubject, setSelectedSubject] = useState("");
    const [selectedTeacher, setSelectedTeacher] = useState("");
    const [selectedClass, setSelectedClass] = useState("");

    const navigator = useNavigate();

    useEffect(() => {
        customAXIOS(ALLCLASSES, null, 'get', null, navigator)
            .then(res => setClasses(res))
            .catch(err => {
                alert("Error in fetching classes");
                console.log("Error:", err);
            });
    }, []);

    useEffect(() => {
        customAXIOS(ALLSUBJECTS, null, 'get', null, navigator)
            .then(res => setSubjects(res))
            .catch(err => {
                alert("Error in fetching subjects");
                console.log("Error:", err);
            });
    }, []);

    useEffect(() => {
        customAXIOS(ALLTEACHERS, null, 'get', null, navigator)
            .then(res => setTeachers(res))
            .catch(err => {
                alert("Error in fetching teachers");
                console.log("Error:", err);
            });
    }, []);

    useEffect(() => {
        customAXIOS(SUBJECTTEACHERCLASS, null, 'get', null, navigator)
            .then(res => setRelations(res.results))
            .catch(err => {
                alert("Error in fetching previous relations");
                console.log("Error:", err);
            });
    }, []);


    const getAvailableClasses = () => {
        const assigned = new Set(
            relations?.map(r => `${r.subject_id}_${r.class_id}`)
        );
        return classes?.filter(cls =>
            !assigned.has(`${selectedSubject}_${cls.Class_id}`)
        );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!selectedSubject || !selectedTeacher || !selectedClass) {
            alert("All fields are required.");
            return;
        }

        const postData = {
            subject: selectedSubject,
            teacher: selectedTeacher,
            rel_class: selectedClass
        };
        console.log(postData)
        customAXIOS(SUBJECTTEACHERCLASS, null, 'post', postData, navigator)
            .then(() => {
                alert("Subject assigned successfully!");
                setRelations([...relations, {
                    subject_id: selectedSubject,
                    class_id: selectedClass
                }]);
                setSelectedSubject("");
                setSelectedTeacher("");
                setSelectedClass("");
            })
            .catch(err => {
                alert("Failed to assign subject.");
                console.log("POST Error:", err);
                console.log("Error data:",postData)
            });
    };

    return (
        <div>
            <h1>Assign Subject to Teacher and Class</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Subject: </label>
                    <select
                        value={selectedSubject}
                        onChange={e => setSelectedSubject(e.target.value)}
                        required
                    >
                        <option value="">-- Select Subject --</option>
                        {subjects?.map(sub => (
                            <option key={sub.id} value={sub.id}>
                                {sub.Name}
                            </option>
                        ))}
                    </select>
                </div>

                <div>
                    <label>Teacher: </label>
                    <select
                        value={selectedTeacher}
                        onChange={e => setSelectedTeacher(e.target.value)}
                        required
                    >
                        <option value="">-- Select Teacher --</option>
                        {teachers?.map(teacher => (
                            <option key={teacher.user_id} value={teacher.user_id}>
                                {teacher.Name}
                            </option>
                        ))}
                    </select>
                </div>

                <div>
                    <label>Class: </label>
                    <select value={selectedClass} onChange={e => setSelectedClass(e.target.value)} required>
                        <option value="">-- Select Class --</option>
                        {getAvailableClasses()?.map(cls => (
                            <option key={cls.id} value={cls.id}>
                                {cls.Class_id} - {cls.Section}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit" className="submit-buttons">Assaign</button>
            </form>
        </div>
    );
}

export default SubjectAssaign;
