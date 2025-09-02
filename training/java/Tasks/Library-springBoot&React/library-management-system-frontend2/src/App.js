import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import Header from "./components/Header";

import LibraryHome from "./components/LibraryHome";
import AddBook from "./components/AddBook";
import BookList from "./components/BookList";
import UpdateBook from "./components/UpdateBook";
import AddMember from "./components/AddMember";
import MemberList from "./components/MemberList";
import UpdateMember from "./components/UpdateMember";
import IssueRecord from "./components/IssueRecord";
import ReturnRecord from "./components/ReturnRecord";
import IssueList from "./components/IssueList";
import ReportsHome from "./components/ReportsHome";
import CategoryCountTable from "./components/CategoryCountTable";
import OverdueBooksTable from "./components/OverdueBooksTable";
import ActiveIssuedBooksTable from "./components/ActiveIssuedBooksTable";

function App() {
	return (
		<>
			<Header />
			<Routes>
				<Route path="/" element={<Navigate to="/library/books/view" replace />} />

				<Route path="/library" element={<LibraryHome />} />

				<Route path="/library/books/add" element={<AddBook />} />
				<Route path="/library/books/view" element={<BookList />} />
				<Route path="/library/books/update/:id" element={<UpdateBook />} />

				<Route path="/library/members/add" element={<AddMember />} />
				<Route path="/library/members/view" element={<MemberList />} />
				<Route path="/library/members/update/:id" element={<UpdateMember />} />

				<Route path="/library/issues/issue" element={<IssueRecord />} />
				<Route path="/library/issues/return" element={<ReturnRecord />} />
				<Route path="/library/issues/allIssues" element={<IssueList />} />

				<Route path="/library/reports" element={<ReportsHome />}>
					<Route index element={<OverdueBooksTable />} />
					<Route path="overdueRecords" element={<OverdueBooksTable />} />
					<Route path="categoryCount" element={<CategoryCountTable />} />
					<Route path="activeIssuedRecords" element={<ActiveIssuedBooksTable />} />
				</Route>
			</Routes>
		</>
	);
}

export default App;
