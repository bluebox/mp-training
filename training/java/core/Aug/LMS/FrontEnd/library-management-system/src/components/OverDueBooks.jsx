import { useEffect, useState } from "react";
import {  overDueBooks } from "../services/reportService";
import ReportsTable from "./ReportsTable";
import Swal from "sweetalert2";

function OverDueBooks (){

    const [data, setData] = useState([]);
  

  const columns = [
    { header: "Issue ID", accessor: "id" },
    { header: "Book ID", accessor: "bookId" },
    { header: "Book Title", accessor: "bookTitle" },
    { header: "Member ID", accessor: "memberId" },
    { header: "Member Name", accessor: "memberName" },
    { header: "Issue Date", accessor: "issueDate" },
  ];

  useEffect(() => {
 
     fetchData()
  }, []);

  const fetchData = async () => {
      try {
        const res = await overDueBooks();
        if (res.success) {
          setData(res.data);
        }
      } catch (err) {
        errorShow(err.response.data.message)
      }
    };

    const errorShow = (message) => {
        Swal.fire({
          icon: "error",
          text: message,
          timer: 2000,
          showConfirmButton: false
        })
      }

  return (
    <div>
      <h3>Over Due Books Report</h3>
      <ReportsTable columns={columns} data={data} />
    </div>
  );

}
export default OverDueBooks;