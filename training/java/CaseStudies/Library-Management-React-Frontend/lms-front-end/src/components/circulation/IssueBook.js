import React, { useState, useEffect } from 'react';
import './IssueBook.css';

const IssueBook = () => {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [formData, setFormData] = useState({
    selectedMember: '',
    email: '',
    selectedBook: ''
  });
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

  useEffect(() => {
    fetchMembers();
    fetchBooks();
  }, []);

  const fetchMembers = async () => {
    try {
      const response = await fetch('http://localhost:8080/getallmembers');
      if (!response.ok) throw new Error('Failed to fetch members');
      const data = await response.json();
      setMembers(data);
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    }
  };

  const fetchBooks = async () => {
    try {
      const response = await fetch('http://localhost:8080/getallbooks');
      if (!response.ok) throw new Error('Failed to fetch books');
      const data = await response.json();
      setBooks(data.filter(book => book.book_Availability === 'A'));
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!formData.email) return;
    const regex = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$");
   
    try {
      if(!regex.test(formData.email) ){
        throw new Error('Email not valid!');
      } 
      setIsLoading(true);
      const response = await fetch(`http://localhost:8080/searchmember?email=${formData.email}`);
      const data = await response.json();
      
      if (!response.ok ) throw new Error(data.message);

      const member = data;
      setFormData(prev => ({
        ...prev,
        selectedMember: member.member_Id.toString()
      }));
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    } finally {
      setIsLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.selectedMember || !formData.selectedBook) {
      setMessage({ type: 'error', text: 'Please select both member and book' });
      return;
    }

    try {
      setIsLoading(true);
      const issueData = {
        issueId: 0,  // Will be set by backend
        bookId: parseInt(formData.selectedBook),
        memberId: parseInt(formData.selectedMember),
        status: 'I',
        issueDate: new Date().toISOString().split('T')[0], // Today's date in YYYY-MM-DD format
        returnDate: null
      };

      const response = await fetch('http://localhost:8080/issuebook', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(issueData)
      });

      if (!response.ok) throw new Error('Failed to issue book');
      
      setMessage({ type: 'success', text: 'Book issued successfully!' });
      setFormData({ selectedMember: '', email: '', selectedBook: '' });
      fetchBooks(); 
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="issue-book-container">
      <h2>Issue Book</h2>
      {message.text && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}
      
      <form onSubmit={handleSubmit} className="issue-book-form">
        <div className="form-group">
          <label htmlFor="selectedMember">Select Member</label>
          <select
            id="selectedMember"
            name="selectedMember"
            value={formData.selectedMember}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Choose a member</option>
            {members.map(member => (
              <option key={member.member_Id} value={member.member_Id}>
                {member.member_Name} - {member.email}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <div className="input-container">
            <label htmlFor="email">Or Enter Email</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="member@example.com"
            />
          </div>
          <button
            type="button"
            onClick={handleSearch}
            disabled={isLoading}
            className="search-btn"
          >
            Search
          </button>
        </div>

        <div className="form-group">
          <label htmlFor="selectedBook">Select Book</label>
          <select
            id="selectedBook"
            name="selectedBook"
            value={formData.selectedBook}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Choose a book</option>
            {books.map(book => (
              <option key={book.book_Id} value={book.book_Id}>
                {book.book_Title} - {book.book_Author}
              </option>
            ))}
          </select>
        </div>

        <div className="button-group">
          <button type="submit" disabled={isLoading} className="submit-btn">
            {isLoading ? 'Issuing...' : 'Issue Book'}
          </button>
          <button type="button" onClick={() => setFormData({ selectedMember: '', email: '', selectedBook: '' })} className="cancel-btn">
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default IssueBook;
