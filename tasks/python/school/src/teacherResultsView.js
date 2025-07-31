// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { RESULTS, TEACHERRESULTSVIEW, TEACHERSUBJECTSTUDENTS } from "./urls";
// import { Link, useNavigate } from "react-router-dom";

// function TeacherResultsView({ userId }) {
//     const [results, setResults] = useState([]);
//     const [subjects, setSubjects] = useState([]);
//     const [currentPage,setCurrentPage] = useState([]);
    
//     const navigator = useNavigate();


//     const fetchRes = async () => {
//         const data = [];
//         const subData = await customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, 'get', null, navigator);
//         const subjectkeys = Object.keys(subData || {});
//         setSubjects(subjectkeys);

//         for (const sub_id of subjectkeys) {
//             const result = await customAXIOS(TEACHERRESULTSVIEW, { id: userId, sub_id }, 'get', null, navigator);
//             data.push(...result);
//         }

//         setResults(data);
//     };

//     useEffect(() => {
//         if (userId) fetchRes();
//     }, [userId]);

//     const handleDelete = async (id) => {
//         try {
//             await customAXIOS(RESULTS + String(id) + "/", null, 'delete', null, navigator);
//             alert("Successfully deleted");
//             fetchRes();
//         } catch (err) {
//             console.error("Error occurred during deletion", err);
//             alert("Failed to delete");
//         }
//     };

//     return (
//         <div className="display-container">
//             <h2>Students</h2>
//             <table className="user-table">
//                 <thead>
//                     <tr>
//                         <th>Name</th>
//                         <th>ID</th>
//                         <th>Class</th>
//                         <th>Subject Name</th>
//                         <th>Percentage</th>
//                         <th>Grade</th>
//                         <th>Actions</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {results?.map((u) => (
//                         <tr key={u.id}>
//                             <td>{u.student__Name}</td>
//                             <td>{u.student__user_id}</td>
//                             <td>{u.Class}</td>
//                             <td>{u.subject__Name}</td>
//                             <td>{u.percentage}</td>
//                             <td>{u.grade}</td>
//                             <td>
//                                 <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
//                             </td>
//                         </tr>
//                     ))}
//                     {results.length === 0 && (
//                         <tr>
//                             <td colSpan="7" style={{ textAlign: "center" }}>No users found</td>
//                         </tr>
//                     )}
//                 </tbody>
//             </table>
//         </div>
//     );
// }

// export default TeacherResultsView;
// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { RESULTS, TEACHERRESULTSVIEW, TEACHERSUBJECTSTUDENTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function TeacherResultsView({ userId }) {
//     const [results, setResults] = useState([]);
//     const [subjects, setSubjects] = useState([]);
//     const [currentSubject, setCurrentSubject] = useState(null);
//     const [currentPage, setCurrentPage] = useState(1);
//     const [count, setCount] = useState(0);
//     const [pageSize] = useState(5);

//     const navigator = useNavigate();

//     const fetchSubjects = async () => {
//         const subData = await customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, 'get', null, navigator);
//         const keys = Object.keys(subData || {});
//         setSubjects(keys);
//         if (keys.length > 0) setCurrentSubject(keys[0]);
//     };

//     const fetchResults = async () => {
//         if (!currentSubject) return;

//         const response = await customAXIOS(
//             TEACHERRESULTSVIEW,
//             { id: userId, sub_id: currentSubject, page: currentPage },
//             'get',
//             null,
//             navigator
//         );

//         setResults(response.results || []);
//         setCount(response.count || 0);
//     };

//     useEffect(() => {
//         if (userId) fetchSubjects();
//     }, [userId]);

//     useEffect(() => {
//         fetchResults();
//     }, [currentSubject, currentPage]);

//     const handleDelete = async (id) => {
//         try {
//             await customAXIOS(`${RESULTS}${id}/`, null, 'delete', null, navigator);
//             alert("Successfully deleted");
//             fetchResults(); // re-fetch current page
//         } catch (err) {
//             console.error("Error occurred during deletion", err);
//             alert("Failed to delete");
//         }
//     };

//     const totalPages = Math.ceil(count / pageSize);

//     return (
//         <div className="display-container">
//             <h2>Students' Results</h2>

//             <div>
//                 <label>Select Subject: </label>
//                 <select
//                     value={currentSubject || ""}
//                     onChange={(e) => {
//                         setCurrentSubject(e.target.value);
//                         setCurrentPage(1);
//                     }}
//                 >
//                     {subjects.map((sub) => (
//                         <option key={sub} value={sub}>{sub}</option>
//                     ))}
//                 </select>
//             </div>

//             <table className="user-table">
//                 <thead>
//                     <tr>
//                         <th>Name</th>
//                         <th>ID</th>
//                         <th>Class</th>
//                         <th>Subject Name</th>
//                         <th>Percentage</th>
//                         <th>Grade</th>
//                         <th>Actions</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {results.map((u) => (
//                         <tr key={u.id}>
//                             <td>{u.student__Name}</td>
//                             <td>{u.student__user_id}</td>
//                             <td>{u.Class}</td>
//                             <td>{u.subject__Name}</td>
//                             <td>{u.percentage}</td>
//                             <td>{u.grade}</td>
//                             <td>
//                                 <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
//                             </td>
//                         </tr>
//                     ))}
//                     {results.length === 0 && (
//                         <tr>
//                             <td colSpan="7" style={{ textAlign: "center" }}>No results found</td>
//                         </tr>
//                     )}
//                 </tbody>
//             </table>

//             {totalPages > 1 && (
//                 <div className="pagination">
//                     {Array.from({ length: totalPages }, (_, i) => i + 1).map((pg) => (
//                         <button
//                             key={pg}
//                             onClick={() => setCurrentPage(pg)}
//                             className={pg === currentPage ? "active" : ""}
//                         >
//                             {pg}
//                         </button>
//                     ))}
//                 </div>
//             )}
//         </div>
//     );
// }

// export default TeacherResultsView;

// import React, { useEffect, useState } from "react";
// import customAXIOS from "./apis";
// import { RESULTS, TEACHERRESULTSVIEW, TEACHERSUBJECTSTUDENTS, SUBJECTS, ALLSUBJECTS } from "./urls";
// import { useNavigate } from "react-router-dom";

// function TeacherResultsView({ userId }) {
//     const [results, setResults] = useState([]);
//     const [subjectsMap, setSubjectsMap] = useState({}); // { subjectId: subjectName }
//     const [subjectIds, setSubjectIds] = useState([]);
//     const [currentSubject, setCurrentSubject] = useState(null);
//     const [currentPage, setCurrentPage] = useState(1);
//     const [count, setCount] = useState(0);
//     const [pageSize] = useState(5);

//     const navigator = useNavigate();

//     const fetchSubjects = async () => {
//         const subData = await customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, 'get', null, navigator);
//         const ids = Object.keys(subData || {});
//         setSubjectIds(ids);
//         if (ids.length > 0) setCurrentSubject(ids[0]);

//         const allSubjects = await customAXIOS(ALLSUBJECTS, null, 'get', null, navigator);
//         const map = {};
//         for (const s of allSubjects) {
//             map[s.id] = s.Name;
//         }
//         setSubjectsMap(map);
//         console.log("subject mapping::",map)
//     };

//     const fetchResults = async () => {
//         console.log("Current sub::", currentSubject)
//         if (!currentSubject) return;
//         const response = await customAXIOS(
//             TEACHERRESULTSVIEW,
//             { id: userId, sub_id: currentSubject, page: currentPage },
//             'get',
//             null,
//             navigator
//         );

//         setResults(response.results || []);
//         setCount(response.count || 0);
//         console.log("results res::",response)
//     };

//     useEffect(() => {
//         if (userId) fetchSubjects();
//     }, [userId]);

//     useEffect(() => {
//         fetchResults();
//     }, [currentSubject, currentPage]);

//     const handleDelete = async (id) => {
//         try {
//             await customAXIOS(RESULTS+String(id)+"/", null, 'delete', null, navigator);
//             alert("Successfully deleted");
//             fetchResults();
//         } catch (err) {
//             console.error("Error occurred during deletion", err);
//             alert("Failed to delete");
//         }
//     };

//     const totalPages = Math.ceil(count / pageSize);

//     return (
//         <div className="display-container">
//             <h2>Students' Results</h2>

//             <div>
//                 <label>Select Subject: </label>
//                 <select
//                     value={currentSubject || ""}
//                     onChange={(e) => {
//                         setCurrentSubject(e.target.value);
//                         setCurrentPage(1);
//                     }}
//                 >
//                     {subjectIds.map((id) => (
//                         <option key={id} value={id}>
//                             {subjectsMap[id] || `Subject ${id}`}
//                         </option>
//                     ))}
//                 </select>
//             </div>

//             <table className="user-table">
//                 <thead>
//                     <tr>
//                         <th>Name</th>
//                         <th>ID</th>
//                         <th>Class</th>
//                         <th>Subject Name</th>
//                         <th>Percentage</th>
//                         <th>Grade</th>
//                         <th>Actions</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {results?.map((u) => (
//                         <tr key={u.id}>
//                             <td>{u.student__Name}</td>
//                             <td>{u.student__user_id}</td>
//                             <td>{u.Class}</td>
//                             <td>{u.subject__Name}</td>
//                             <td>{u.percentage}</td>
//                             <td>{u.grade}</td>
//                             <td>
//                                 <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
//                             </td>
//                         </tr>
//                     ))}
//                     {results.length === 0 && (
//                         <tr>
//                             <td colSpan="7" style={{ textAlign: "center" }}>No results found</td>
//                         </tr>
//                     )}
//                 </tbody>
//             </table>

//             {totalPages > 1 && (
//                 <div className="pagination">
//                     {Array.from({ length: totalPages }, (_, i) => i + 1).map((pg) => (
//                         <button
//                             key={pg}
//                             onClick={() => setCurrentPage(pg)}
//                             className={pg === currentPage ? "active" : ""}
//                         >
//                             {pg}
//                         </button>
//                     ))}
//                 </div>
//             )}
//         </div>
//     );
// }

// export default TeacherResultsView;
import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import {
    RESULTS,
    TEACHERRESULTSVIEW,
    TEACHERSUBJECTSTUDENTS,
    ALLSUBJECTS
} from "./urls";
import { useNavigate } from "react-router-dom";

function TeacherResultsView({ userId }) {
    const [results, setResults] = useState([]);
    const [subjectIds, setSubjectIds] = useState([]);  // [7, 3, 5]
    const [subjectsMap, setSubjectsMap] = useState({}); // { 7: 'Math', 3: 'Science' }
    const [currentSubject, setCurrentSubject] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize] = useState(5);

    const navigate = useNavigate();

    const fetchSubjects = async () => {
        try {
            const mapRes = await customAXIOS(
                TEACHERSUBJECTSTUDENTS,
                { id: userId },
                'get',
                null,
                navigate
            );

            // Extract unique subject IDs the teacher is assigned to
            const uniqueSubjectIds = [
                ...new Set(mapRes.flatMap(entry => entry.subject_id))
            ];
            setSubjectIds(uniqueSubjectIds);
            if (uniqueSubjectIds.length > 0) {
                setCurrentSubject(uniqueSubjectIds[0]);
            }

            // Fetch all subject details
            const allSubjects = await customAXIOS(ALLSUBJECTS, null, 'get', null, navigate);
            const map = {};
            for (const s of allSubjects) {
                if (uniqueSubjectIds.includes(s.id)) {
                    map[s.id] = s.Name;
                }
            }
            setSubjectsMap(map);
        } catch (error) {
            console.error("Error fetching subject details:", error);
        }
    };

    const fetchResults = async () => {
        if (!currentSubject) return;

        try {
            const response = await customAXIOS(
                TEACHERRESULTSVIEW,
                {
                    id: userId,
                    sub_id: currentSubject,
                    page: currentPage
                },
                'get',
                null,
                navigate
            );

            setResults(response.results || []);
            setCount(response.count || 0);
        } catch (error) {
            console.error("Error fetching results:", error);
        }
    };

    useEffect(() => {
        if (userId) fetchSubjects();
    }, [userId]);

    useEffect(() => {
        fetchResults();
    }, [currentSubject, currentPage]);

    const handleDelete = async (id) => {
        try {
            await customAXIOS(`${RESULTS}${id}/`, null, 'delete', null, navigate);
            alert("Successfully deleted");
            fetchResults();
        } catch (err) {
            console.error("Error occurred during deletion", err);
            alert("Failed to delete");
        }
    };

    const totalPages = Math.ceil(count / pageSize);

    return (
        <div className="display-container">
            <h2>Students' Results</h2>

            <div>
                <label>Select Subject: </label>
                <select
                    value={currentSubject || ""}
                    onChange={(e) => {
                        setCurrentSubject(Number(e.target.value));
                        setCurrentPage(1);
                    }}
                >
                    {subjectIds.map((id) => (
                        <option key={id} value={id}>
                            {subjectsMap[id] || `Subject ${id}`}
                        </option>
                    ))}
                </select>
            </div>

            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>ID</th>
                        <th>Class</th>
                        <th>Subject</th>
                        <th>Percentage</th>
                        <th>Grade</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {results?.map((u) => (
                        <tr key={u.id}>
                            <td>{u.student__Name}</td>
                            <td>{u.student__user_id}</td>
                            <td>{u.Class}</td>
                            <td>{u.subject__Name}</td>
                            <td>{u.percentage}</td>
                            <td>{u.grade}</td>
                            <td>
                                <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {results.length === 0 && (
                        <tr>
                            <td colSpan="7" style={{ textAlign: "center" }}>No results found</td>
                        </tr>
                    )}
                </tbody>
            </table>

            {totalPages > 1 && (
                <div className="pagination">
                    {Array.from({ length: totalPages }, (_, i) => i + 1).map((pg) => (
                        <button
                            key={pg}
                            onClick={() => setCurrentPage(pg)}
                            className={pg === currentPage ? "active" : ""}
                        >
                            {pg}
                        </button>
                    ))}
                </div>
            )}
        </div>
    );
}

export default TeacherResultsView;
