import { useState, useEffect } from "react";
import {
  fetchAllManagers,
  fetchEmployees,
  fetchExpenses,
  fetchTotalExpense,
  fetchTotalExpensePerCategory,
} from "../../service/adminService";
import { fetchCategories } from "../../service/expenseService";
import {
  Chart as ChartJS,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";
import { downloadExcel, DownloadTableExcel } from 'react-export-table-to-excel';


const AdminDashboard = () => {
  const [expenses, setExpenses] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [managers, setManagers] = useState([]);
  const [categories, setCategories] = useState([]);
  const [totalExpense, setTotalExpense] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const [expensePercategory, setExpenseForCategory] = useState([]);

  const [filters, setFilters] = useState({
    employeeId: "",
    managerId: "",
    categoryId: "",
    month: new Date().getMonth() + 1,
  });

  const today = new Date();
  const monthAndYear = today.toLocaleString("default", {
    month: "long",
    year: "numeric",
  });

  useEffect(() => {
    const loadData = async () => {
      try {
        const managersData = await fetchAllManagers();
        setManagers(managersData);

        const employeesData = await fetchEmployees();
        setEmployees(employeesData);

        const categoriesData = await fetchCategories();
        setCategories(categoriesData);

        const totalExpenseData = await fetchTotalExpense();
        totalExpense!==null ? setTotalExpense(totalExpenseData.totalExpense) : setTotalExpense(0);

        const expensePerCategoryData = await fetchTotalExpensePerCategory();
        setExpenseForCategory(expensePerCategoryData);
      } catch (e) {
        alert(e);
      } finally {
        setIsLoading(false);
      }
    };
    loadData();
    loadExpenses(filters);
  }, []);

  useEffect(() => {
    loadExpenses();
  }, [filters]);

  const handleDownloadReport = ()=>{
    const header = ["Employee","Category","Amount","Date","Status","Description","Manager"]
    downloadExcel({
      fileName: "Expense Report",
      sheet: "react-export-table-to-excel",
      tablePayload: {
        header,
        body: expenses
      },
    });

  }

  const loadExpenses = async () => {
    fetchExpenses(filters)
      .then((res) => setExpenses(res))
      .catch((err) => console.log(err));
  };

  const handleFilterChange = (event) => {
    const { name, value } = event.target;
    setFilters((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  ChartJS.register(BarElement, CategoryScale, LinearScale, Tooltip, Legend);

  const categoryLabels = categories.map((category) => category.name);
  const approvedAmounts = expensePercategory.map(
    (category) => category.totalExpense
  );

  const barData = {
    labels: categoryLabels,
    datasets: [
      {
        label: "Approved Amount",
        data: approvedAmounts,
        backgroundColor: "rgba(54, 162, 235, 0.6)",
        borderColor: "rgba(2, 154, 255, 1)",
        borderWidth: 1,
      },
    ],
  };

  const barOptions = {
    responsive: true,
    plugins: {
      legend: { display: false },
      tooltip: {
        callbacks: {
          label: (context) => `₹${context.parsed.y.toLocaleString()}`,
        },
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          callback: (value) => `₹${value.toLocaleString()}`,
        },
      },
    },
  };

  if (isLoading) return <div>loading...</div>;

  return (
    <div className="container mt-4">
      <h3 className="mb-4">Expense Reports</h3>

      <div className="row mb-4">
        <div className="col-md-6 mb-3 mb-md-0">
          <div className="card h-100">
            <div className="card-body text-center d-flex flex-column justify-content-center">
              <h4>Total EXPENSE for {monthAndYear}</h4>
              <h2>₹{totalExpense.toLocaleString()}</h2>
            </div>
          </div>
        </div>

        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div className="card w-100">
            <div className="card-header text-center">
              <h5>Amount Approved Per Category</h5>
            </div>
            <div className="card-body">
              <Bar data={barData} options={barOptions} />
            </div>
          </div>
        </div>
      </div>

      <div className="card mb-4">
        <div className="card-body">
          <div className="row mb-3">
            <div className="col-md-2">
              <select
                className="form-select"
                name="employeeId"
                value={filters.employeeId}
                onChange={handleFilterChange}
              >
                <option value="">All Employees</option>
                {employees.map((emp) => (
                  <option key={emp.id} value={emp.id}>
                    {emp.username}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-2">
              <select
                className="form-select"
                name="managerId"
                value={filters.managerId}
                onChange={handleFilterChange}
              >
                <option value="">All Managers</option>
                {managers.map((manager) => (
                  <option key={manager.id} value={manager.id}>
                    {manager.username}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-2">
              <select
                className="form-select"
                name="categoryId"
                value={filters.categoryId}
                onChange={handleFilterChange}
              >
                <option value="">All Categories</option>
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-2">
              <select
                className="form-select"
                name="month"
                value={filters.month}
                onChange={handleFilterChange}
              >
                {Array.from({ length: 12 }, (_, i) => (
                  <option key={i + 1} value={i + 1}>
                    {new Date(0, i).toLocaleString("default", {
                      month: "long",
                    })}
                  </option>
                ))}
              </select>
            </div>
            <div className="col-md-2">
              <button className="btn btn-primary" onClick={handleDownloadReport}>Download Report</button>
            </div>
          </div>

          <div className="table-responsive">
            <table className="table table-striped table-bordered">
              <thead className="table-dark">
                <tr>
                  <th>Employee</th>
                  <th>Category</th>
                  <th>Amount</th>
                  <th>Date</th>
                  <th>Status</th>
                  <th>Description</th>
                  <th>Manager</th>
                </tr>
              </thead>
              <tbody>
                {expenses.map((expense) => (
                  <tr key={expense.id}>
                    <td>{expense.employeeName}</td>
                    <td>{expense.categoryName}</td>
                    <td>₹{expense.amount}</td>
                    <td>{new Date(expense.date).toLocaleDateString()}</td>
                    <td>
                      <span
                        className={`badge ${
                          expense.status === "APPROVED"
                            ? "bg-success"
                            : expense.status === "REJECTED"
                            ? "bg-danger"
                            : "bg-warning"
                        }`}
                      >
                        {expense.status}
                      </span>
                    </td>
                    <td>{expense.description}</td>
                    <td>
                      {
                        managers.find((m) => m.id === expense.managerId)
                          ?.username
                      }
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
