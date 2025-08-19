import { useEffect, useState } from "react";
import ReportsTable from "./ReportsTable";
import { bookCategoryCount } from "../services/reportService";

function BookCategoryCount (){

    const [data, setData] = useState([]);
  

  const columns = [
    { header: "Category", accessor: "category" },
    { header: "Count", accessor: "count" }
     
  ];

  useEffect(() => {
 
     fetchData()
  }, []);

  const fetchData = async () => {
      try {
        const res = await bookCategoryCount();
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
      <h3>Category Wise Books Count Report</h3>
      <ReportsTable columns={columns} data={data} />
    </div>
  );

}
export default BookCategoryCount;