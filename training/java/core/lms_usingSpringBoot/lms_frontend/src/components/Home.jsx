import React, { useState } from 'react'
import ListBooksComponent from './ListBooksComponent'
import AddBooks from './AddBooks'
import AddMember from './AddMember';
import ListMembers from './ListMembers';
import UpdateBook from './UpdateBook';

const Home = () => {
    const [currentView, setCurrentView] = useState('home');
    const [bookIdToUpdate, setBookIdToUpdate] = useState(null);

    const handleUpdateBook = (bookId) => {
        setBookIdToUpdate(bookId);
        setCurrentView('updatebook'); 
    };
    const addNewBook = () => {
        setCurrentView('addbook');
    }
    const viewBooks = () => {
        setCurrentView('booksList')
    }
    const addNewMember = () => {
        setCurrentView('addmember');
    }
    const viewMembers = () => {
        setCurrentView('membersList')
    }


    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-9' style={{ height: '80vh' }}>
                    {currentView === 'booksList' && <ListBooksComponent onUpdateBook={handleUpdateBook}  />}
                    {currentView === 'addbook' && <AddBooks />}
                    {currentView === 'addmember' && <AddMember />}
                    {currentView === 'membersList' && <ListMembers />}
                    {currentView === 'updatebook' && <UpdateBook bookId={bookIdToUpdate} />}
                </div>
                <div className='col-md-2 d-grid gap-2 my-4'>
                    <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={addNewBook}>Add Book</button>
                    <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={viewBooks}>View Books</button>
                    <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={addNewMember}>Add Member</button>
                    <button className='btn text-white btn-lg' style={{ backgroundColor: '#4B0082' }} onClick={viewMembers}>View Members</button>
                </div>
            </div>
        </div>
    )
}

export default Home