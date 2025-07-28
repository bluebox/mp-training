import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { STUDENTRESULTS } from "./urls";
import { useNavigate } from "react-router-dom";

function StudentResultsDashboard({ userId }) {
    const navigate = useNavigate();
    const [results, setResults] = useState([]);

    useEffect(() => {
        customAXIOS(STUDENTRESULTS, { id: userId }, 'get', null, navigate)
            .then((res) => {
                const data = res.map(item => ({
                    Name: item.Name,
                    SubjectName: item.results__subject_id__Name,
                    Grade: item.results__grade,
                    Percentage: item.results__percentage,
                }));
                setResults(data);
                console.log("Fetched results:", data);
            })
            .catch((err) => console.error("Error fetching results:", err));
    }, [userId, navigate]);

    return (
        <div className="display-container">
            <h2>Results</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Subject</th>
                        <th>Grade</th>
                        <th>Percentage</th>
                    </tr>
                </thead>
                <tbody>
                    {results.length > 0 ? (
                        results.map((r) => (
                            <tr key={`${userId}-${r.SubjectName}`}>
                                <td>{r.Name}</td>
                                <td>{r.SubjectName}</td>
                                <td>{r.Grade}</td>
                                <td>{r.Percentage}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: "center" }}>No results found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default StudentResultsDashboard;
