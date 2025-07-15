import '../../App.css';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';

function AdminHome() {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    
    useEffect(() => {
        checkAuth();
    }, []);

    const checkAuth = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/user/check-auth');
            if (response.status === 200) {
                const userInfoResponse = await axios.get('http://localhost:8080/api/user/user-info');
                const userData = userInfoResponse.data;
                if (userData.role !== 'ADMIN') {
                    navigate('/');
                    return;
                }
                setUser(userData);
            } else {
                navigate('/');
            }
        } catch (error) {
            navigate('/');
        }
    };
    
    const gotoHome = async () => {
        try {
            await axios.post('http://localhost:8080/api/user/logout');
            localStorage.removeItem('userName');
      localStorage.removeItem('userRole');
      localStorage.removeItem('userId');
        } catch (error) {
            console.error('Logout error:', error);
        }
        navigate("/");
    };

    const handleAddQuestion = () => {
        navigate('/admin/add-question');
    };

    const handleShowQuestions = () => {
        navigate('/admin/questions');
    };

    const handleShowUsers = () => {
        navigate('/admin/all-users');
    };

    const handleGameConfig = () => {
        navigate('/admin/configurations');
    };

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <div className="admindiv">
            <h1 className="admindivhead">Hi, <span>{user.username}</span> Welcome to KBC Home</h1>
            <div className="admindiv1">
                <div className="operationsdiv">
                    <h2>Questions &#128203;</h2>
                    <span className="spanlinks">
                        <a onClick={handleAddQuestion} style={{cursor: 'pointer'}}>Add Question</a> | 
                        <a onClick={handleShowQuestions} style={{cursor: 'pointer'}}> Show All Questions</a>
                    </span>
                </div>
                <div className="admindiv2">
                    <div className="operationsdiv">
                        <h2>Users 👤</h2>
                        <a className="alink" onClick={handleShowUsers} style={{cursor: 'pointer'}}>Show All Users</a>
                    </div>
                    <div className="operationsdiv">
                        <h2>Game Configurations &#9881;</h2>
                        <span className="spanlinks">
                            <a onClick={handleGameConfig} style={{cursor: 'pointer'}}>Configure &#128221;</a>
                        </span>
                    </div>
                </div>
            </div>
            <button className="adminlogoutbtn" onClick={gotoHome}>Logout</button>
        </div>
    );
}

export default AdminHome;
