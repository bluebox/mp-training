import React, { useState, useEffect } from 'react'
import { getOverdueBooks, getMemberStats, getBooksByCategory } from '../services/reportsService'
function Reports() {
    const [overduebooks, setOverdueBooks] = useState([])
    const [memberstats, setMemberStats] = useState([])
    const [bookbycategory, setBookByCategory] = useState([])
    useEffect(() => {
        getOverdueBooks().then((response) => {
            setOverdueBooks(response.data);
            console.log(overduebooks)
        }).catch(error => {
            console.error(error);
        })
        getMemberStats().then((response) => {
            setMemberStats(response.data);
            console.log(memberstats)
        }).catch(error => {
            console.error(error);
        })
        getBooksByCategory().then((response) => {
            setBookByCategory(response.data);
            console.log(bookbycategory)
        }).catch(error => {
            console.error(error);
        })
    }, [])

    return (
        <div>
            <div class="reports-container">
                <div class="report-section">
                    <h2>Overdue Books Report</h2>
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Issue ID</th>
                                    <th>Book ID</th>
                                    <th>Title</th>
                                    <th>Member</th>
                                    <th>Due Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    overduebooks.map(book =>
                                        <tr key={book.issueId}>
                                            <td>{book.issueId}</td>
                                            <td>{book.bookId}</td>
                                            <td>{book.title}</td>
                                            <td>{book.memberName}</td>
                                            <td>{book.overDueDate}</td>

                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="report-section">
                    <h2>Books by Category</h2>
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Total Books</th>
                                </tr>
                            </thead>
                            <tbody>
                                {Object.entries(bookbycategory).map(([category, count]) => (
                                    <tr key={category}>
                                        <td>{category}</td>
                                        <td>{count}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="report-section">
                    <h2>Member Statistics</h2>
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Member ID</th>
                                    <th>Name</th>
                                    <th>Books Issued</th>
                                </tr>
                            </thead>
                            <tbody>{
                                memberstats.map(stat =>
                                    <tr key={stat.memberId}>
                                        <td>{stat.memberId}</td>
                                        <td>{stat.name}</td>
                                        <td>{stat.booksActiveString}</td>
                                    </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Reports