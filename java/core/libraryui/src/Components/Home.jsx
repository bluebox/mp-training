import { Link } from "react-router-dom";

function Home(){
    return(
        <div>
            <Link className="btn btn-dark mt-5" to="/addMember">Add Member</Link><br /><br />
            <Link className="btn btn-dark" to="/updateMember">Update Member</Link><br /><br />
            <Link className="btn btn-dark" to="/addBook">Add Book</Link><br /><br />
            <Link className="btn btn-dark" to="/updateBook">Update Book</Link><br /><br />
            <Link className="btn btn-dark" to="/issueBook">Issue Book</Link><br /><br />
            <Link className="btn btn-dark" to="/returnBook">Return Book</Link><br /><br />
            <Link className="btn btn-dark" to="/getBooks">Get Books</Link><br /><br />
            <Link className="btn btn-dark" to="/getMembers">Get Members</Link><br /><br />
            <Link className="btn btn-dark" to="/getIssuedRecords">Get Issued Records</Link>
        </div>
    )
}

export default Home;