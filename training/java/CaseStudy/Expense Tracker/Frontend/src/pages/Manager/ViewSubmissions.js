import React, { useEffect, useState } from "react";
import { fetchTeamSubmissions, updateExpenseStatus } from "../../service/viewsubmissionsService";
import { downloadExcel } from "react-export-table-to-excel";

const ManagerSubmissions = () => {
  const [submissions, setSubmissions] = useState([]);
  const [remarksMap, setRemarksMap] = useState({});
  const [loading, setLoading] = useState(true);
  const [currentSubmissions, setCurrentSubmissions] = useState([]);
  const [statusFilter, setStatusFilter] = useState("");

  const [monthFilter, setMonthFilter] = useState(() => {
    const d = new Date();
    return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}`;
  });

  const [selectedIds, setSelectedIds] = useState([]);
  const managerId = localStorage.getItem("userId");

  useEffect(() => {
    const loadSubmissions = async () => {
      setLoading(true);
      try {
        const data = await fetchTeamSubmissions();
        setSubmissions(data);
        const initRemarks = {};
        data.forEach(item => {
          initRemarks[item.id] = item.remarks || "";
        });
        setRemarksMap(initRemarks);
      } catch (err) {
        alert(err.message);
      }
      setLoading(false);
    };

    loadSubmissions();
  }, []);

  const toMonthKey = (dateStr) => {
    if (!dateStr) return "";
    const d = new Date(dateStr);
    if (!Number.isNaN(d.getTime())) {
      return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}`;
    }
    return String(dateStr).slice(0, 7);
  };

  useEffect(() => {
    let filtered = submissions;

    if (statusFilter !== "") {
      filtered = filtered.filter((sub) => sub.status === statusFilter);
    }

    if (monthFilter) {
      filtered = filtered.filter((sub) => toMonthKey(sub.date) === monthFilter);
    }

    setCurrentSubmissions(filtered);
    setSelectedIds([]);
  }, [statusFilter, monthFilter, submissions]);

  const handleRemarksChange = (expenseId, value) => {
    setRemarksMap(prev => ({ ...prev, [expenseId]: value }));
  };

  const handleDownloadExcel = () => {
    const header = ["Expense Id", "Employee Id", "Category Id", "Date", "Status", "Amount", "Description", "Remark", "Employee Name", "Category", "receipt URL "];
    downloadExcel({
      fileName: "submissions",
      sheet: "Submissions",
      tablePayload: {
        header,
        body: currentSubmissions
      },
    });
  };

  const handleUpdateStatus = async (sub, status) => {
    try {
      const expense = sub;
      if (!expense) {
        alert("Expense not found!");
        return;
      }
      let remark = remarksMap[expense.id] || "";
      if (status === "REJECT" && remark.trim() === "") {
        alert("You cannot REJECT the Submission With out a REMARK.");
        return;
      }
      if (status === "APPROVE" && remark.trim() === "") {
        remark = "NO REMARK";
      }
      const updatedExpense = {
        ...expense,
        remarks: remark,
        managerId: parseInt(managerId),
      };
      const res = await updateExpenseStatus(expense.id, status, updatedExpense);

      alert(res.message);

      fetchTeamSubmissions()
        .then((data) => { setSubmissions(data); })
        .catch((e) => console.log(e));
    } catch (err) {
      alert(err.message);
    }
  };

  const handleSelect = (id) => {
    setSelectedIds(prev =>
      prev.includes(id) ? prev.filter(i => i !== id) : [...prev, id]
    );
  };

  const handleSelectAll = (e) => {
    if (e.target.checked) {
      setSelectedIds(currentSubmissions.filter(sub => sub.status === "PENDING").map(sub => sub.id));
    } else {
      setSelectedIds([]);
    }
  };

  const handleApproveSelected = async () => {
    if (selectedIds.length === 0) {
      alert("No submissions selected.");
      return;
    }
    for (const id of selectedIds) {
      const sub = currentSubmissions.find(s => s.id === id);
      if (sub) {
        let remark = remarksMap[sub.id] || "NO REMARK";
        const updatedExpense = {
          ...sub,
          remarks: remark,
          managerId: parseInt(managerId),
        };
        try {
          await updateExpenseStatus(sub.id, "APPROVE", updatedExpense);
        } catch (err) {
          alert(`Error approving submission ${sub.id}: ${err.message}`);
        }
      }
    }
    alert("Selected submissions approved.");
    fetchTeamSubmissions()
      .then((data) => { setSubmissions(data); })
      .catch((e) => console.log(e));
    setSelectedIds([]);
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="container mt-3">
      <h3>Team Expense Submissions</h3>
      <div className="row mb-3">
        <div className="col-md-3">
          <select
            className="form-select"
            name="Status"
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value)}
          >
            <option value="">Any Status</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
            <option value="PENDING">Pending</option>
          </select>
        </div>

        <div className="col-md-3">
          <input
            type="month"
            className="form-control"
            value={monthFilter}
            onChange={(e) => setMonthFilter(e.target.value)}
          />
        </div>

        <div className="col-md-2">
          <button className="btn btn-primary mb-3" onClick={handleDownloadExcel}>Download Report</button>
        </div>
        <div className="col-md-2">
          <button
            className="btn btn-success mb-3"
            onClick={handleApproveSelected}
            disabled={selectedIds.length === 0}
          >
            Approve Selected
          </button>
        </div>
      </div>

      <table className="table table-striped">
        <thead className="table-dark">
          <tr>
            <th>
              <input
                type="checkbox"
                checked={
                  currentSubmissions.filter(sub => sub.status === "PENDING").length > 0 &&
                  selectedIds.length === currentSubmissions.filter(sub => sub.status === "PENDING").length
                }
                onChange={handleSelectAll}
              />
            </th>
            <th>Description</th>
            <th>Category</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Status</th>
            <th>Employee</th>
            <th>Add Remarks</th>
            <th>Actions</th>
            <th>Receipt</th>
          </tr>
        </thead>
        <tbody>
          {currentSubmissions?.map(sub => (
            <tr key={sub.id}>
              <td>
                {sub.status === "PENDING" && (
                  <input
                    type="checkbox"
                    checked={selectedIds.includes(sub.id)}
                    onChange={() => handleSelect(sub.id)}
                  />
                )}
              </td>
              <td>{sub.description}</td>
              <td>{sub.categoryName || sub.categoryId}</td>
              <td>{sub.amount}</td>
              <td>{sub.date}</td>
              <td>
                <span className={`badge ${
                  sub.status === 'APPROVED' ? 'bg-success' :
                  sub.status === 'REJECTED' ? 'bg-danger' :
                  'bg-warning'
                }`}>
                  {sub.status}
                </span>
              </td>
              <td>{sub.employeeName || sub.employeeId}</td>
              <td>
                {sub.status === "PENDING" ? (
                  <input
                    type="text"
                    value={remarksMap[sub.id] || ""}
                    onChange={e => handleRemarksChange(sub.id, e.target.value)}
                    className="form-control"
                  />
                ) : (
                  sub.remarks
                )}
              </td>
              <td>
                {sub.status === "PENDING" ? (
                  <>
                    <button
                      onClick={() => handleUpdateStatus(sub, "APPROVE")}
                      className="btn btn-success btn-sm me-2"
                    >
                      Approve
                    </button>
                    <button
                      onClick={() => handleUpdateStatus(sub, "REJECT")}
                      className="btn btn-danger btn-sm"
                    >
                      Reject
                    </button>
                  </>
                ) : (
                  "-"
                )}
              </td>
              <td>
                {sub.receiptUrl === "" || sub.receiptUrl === null ? (
                  <span>No Reciept</span>
                ) : (
                  <a target="_blank" rel="noopener noreferrer" href={sub.receiptUrl}>Reciept</a>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ManagerSubmissions;
