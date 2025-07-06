// import ReactDOM from 'react-dom/client';

// const books=[{bookId:1,title:"Monster",author:"Mdfdv",category:"Physicological",status:"A",availability:"A"},{bookId:2,title:"Mob 100",author:"One",category:"Comedy,Fantasy,Mystery",status:"A",availability:"A"}];s
function Book(props){
    return (
    <tr>
        <td>{props.sample.bookId}</td>
        <td>{props.sample.title}</td>
        <td>{props.sample.author}</td>
        <td>{props.sample.category}</td>
        <td>{props.sample.status}</td>
        <td>{props.sample.availability}</td>
    </tr>
    );
}

function ShowBooks(){
    return(
        <div>
            <table>
                <tr>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Availability</th>
                </tr>
                for(let i=0;i &lt localStorage.length;i++){
                    <Book sample={localStorage.getItem(localStorage.key(i))}/>
                }
            </table>
        </div>
    );
}

export default ShowBooks;
