import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/YearlyAttendance.css";
import api from "../api/api";

export default function YearlyAttendance() {
  const [report, setReport] = useState([]);
  const [year, setYear] = useState(new Date().getFullYear());
  const[currentYear] = useState(year);

  useEffect(() => {
    fetchReport(year);
  }, [year]);

  const fetchReport = async (selectedYear) => {
    try {
      const response = await api.get(
        `/admin/getReport`,
        {
          params: { year: selectedYear },
          withCredentials: true,
        }
      );
      setReport(response.data);
    } catch (error) {
      console.error("Error fetching report", error);
    }
  };

  const getMonthName = (monthNum) => {
    return new Date(2000, monthNum - 1, 1).toLocaleString("default", {
      month: "long",
    });
  };

  return (
    <div className="yearly-attendance-container">
      <h2 className="yearly-attendance-heading">
        Yearly Attendance Report ({year})
      </h2>

      <div className="year-selector">
        <label className="mr-2">Select Year:</label>
        <input
          type="number"
          value={year} max={currentYear}
          onChange={(e) => setYear(e.target.value)}
          className="border px-2 py-1 rounded"
        />
      </div>

      {report.length === 0 ? (
        <p>No data available</p>
      ) : (
        <div className="report-scroll-container">
          {report.map((employeeData, idx) => {
            const employeeId = Object.keys(employeeData)[0];
            const monthlyList = employeeData[employeeId];

            const monthlyAttendance = {};
            monthlyList.forEach((item) => {
              const month = Object.keys(item)[0];
              const days = Object.values(item)[0];
              monthlyAttendance[month] = days;
            });

            return (
              <div
                key={idx}
                className="employee-report-card"
              >
                <h3>Employee ID: {employeeId}</h3>
                <table className="attendance-table">
                  <thead>
                    <tr>
                      <th>Month</th>
                      <th>Working Days</th>
                    </tr>
                  </thead>
                  <tbody>
                    {Object.entries(monthlyAttendance).map(([month, days]) => (
                      <tr key={month}>
                        <td>{getMonthName(month)}</td>
                        <td className="text-center">{days}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
}