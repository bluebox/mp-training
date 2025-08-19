import { NavLink, Outlet } from "react-router-dom";

function Reports() {
    return (
        <>
            <h2 className="p-3">📊 Reports Page</h2>
            <nav>
                <NavLink to="activeIssuedBooks" className="btn btn-outline-primary me-2"> Issued Books</NavLink>
                <NavLink to="bookCategoryCount" className="btn btn-outline-primary me-2">Category Wise Books Count</NavLink>
                <NavLink to="overDueBooks" className="btn btn-outline-primary">Over Due Books</NavLink>
            </nav>
             <Outlet />
            <hr />
        </>
    )
}
export default Reports;