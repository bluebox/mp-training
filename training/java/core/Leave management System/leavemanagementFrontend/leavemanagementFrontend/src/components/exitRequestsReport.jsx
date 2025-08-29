import React, { useState, useEffect } from 'react';
import { listEmployeesExitRequests } from './scripts/AdminService';
import api from '../api/api';
import '../styles/ExitRequestsReport.css';
import axios from 'axios';
const ExitRequestsReport = () => {
    const [exitRequests, setExitRequests] = useState([]);
    const [refreshTrigger, setRefreshTrigger] = useState(0);

    useEffect(() => {
        listEmployeesExitRequests().then((response) => {
            setExitRequests(response.data);
        }).catch(error => {
            console.error(error);
        });
    }, [refreshTrigger]);

    const handleAction = async (id, empId,  exitDate, action, requestSubmittedAt,remarks) => {
        try {
            const requestData = {
                requestId: id,
                employeeId:empId,
                exitDate:exitDate,
                requestStatus: action,
                requestSubmittedAt:requestSubmittedAt,
                remarks:remarks,
                adminRemarks: ""
            };

            if (action === "REJECTED") {
                const reason = prompt("Enter rejection reason:");
                if (!reason) return;
                requestData.adminRemarks = reason;
            }

            await await api.put(`/exit-requests/update`, requestData,{withCredentials: true});
            setRefreshTrigger(prev => prev + 1);
        } catch (err) {
            console.error("Error updating leave:", err);
        }
    };

    return (
        <div className='container'>
            <h2 className='text-center'>Exit Requests</h2>
            <div className='table-responsive'>
                <table>
                    <thead>
                        <tr>
                            <th>Employee ID</th>
                            <th>Exit Date</th>
                            <th>Applied On</th>
                            <th>Employee Remarks</th>
                            <th>Status</th>
                            <th>Admin Remarks</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Object.entries(exitRequests).map(([employee, requests]) =>
                            requests.map((exitRequest, index) => (
                                <tr key={exitRequest.requestId}>
                                    {index === 0 && (
                                        <td rowSpan={requests.length}>{exitRequest.employeeId}</td>
                                    )}
                                    <td>{exitRequest.exitDate}</td>
                                    <td>{exitRequest.requestSubmittedAt}</td>
                                    <td>{exitRequest.remarks}</td>
                                    <td>
                                        <span className={`status-${exitRequest.requestStatus.toLowerCase()}`}>
                                            {exitRequest.requestStatus}
                                        </span>
                                    </td>
                                    <td>{exitRequest.adminRemarks || "No Remarks"}</td>
                                    <td>
                                        {exitRequest.requestStatus === 'PENDING' ? (
                                            <>
                                                <button className='buttoncss'
                                                    onClick={() => handleAction(exitRequest.requestId, exitRequest.employeeId,exitRequest.exitDate,"ACCEPTED",exitRequest.requestSubmittedAt,exitRequest.remarks)}
                                                >
                                                    Approve
                                                </button>
                                                <button className='buttoncss'
                                                    onClick={() => handleAction(exitRequest.requestId, exitRequest.employeeId,exitRequest.exitDate, "REJECTED",exitRequest.requestSubmittedAt,exitRequest.remarks)}
                                                >
                                                    Reject
                                                </button>
                                            </>
                                        ) : (
                                            '-'
                                        )}
                                    </td>
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default ExitRequestsReport;