/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './User.css';

function UserHome() {
  const navigate = useNavigate();
  const [showMenu, setShowMenu] = useState(false);
  const [games, setGames] = useState([]);
  const [avgScore, setAvgScore] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [user, setUser] = useState(null);

  useEffect(() => {
    checkAuth();
  },[]);

  const checkAuth = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/user/check-auth');
      if (response.status === 200) {
        const userInfoResponse = await axios.get('http://localhost:8080/api/user/user-info');
        const userData = userInfoResponse.data;
        if (userData.role !== 'USER') {
          navigate('/');
          return;
        }
        setUser(userData);
        fetchGameData(userData.userId);
      } else {
        navigate('/');
      }
    } catch (error) {
      navigate('/');
    }
  };

  const fetchGameData = async (userId) => {
    try {
      setLoading(true);
      setError('');
      
      const gamesResponse = await axios.get(`http://localhost:8080/api/game/viewGames/${userId}`);
      setGames(gamesResponse.data || []);
      
      const avgScoreResponse = await axios.get(`http://localhost:8080/api/game/avgScorePercentage/${userId}`);
      setAvgScore(avgScoreResponse.data || 0);
    } catch (err) {
      console.error('Error fetching game data:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleUserIconClick = () => {
    setShowMenu(prevShowMenu => !prevShowMenu);
  };
  
  const handleUserDetailsClick = () => {
    navigate('/user/details');
    setShowMenu(false); 
  };
  
  const handleLogoutClick = async () => {
    try {
      await axios.post('http://localhost:8080/api/user/logout');
      localStorage.removeItem('userName');
      localStorage.removeItem('userRole');
      localStorage.removeItem('userId');
    } catch (error) {
      console.error('Logout error:', error);
    }
    navigate("/");
    setShowMenu(false);
  }
  
  const handleStartGame = () => {
    if (user) {
      navigate('/game');
    } else {
      alert('Please log in to start the game.');
    }
  }

  // const formatDate = (dateString) => {
  //   if (!dateString) return '';
  //   try {
  //     const date = new Date(dateString);
  //     return date.toLocaleDateString();
  //   } catch (error) {
  //     return dateString;
  //   }
  // };

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <div className="header-container">
          <h1 className="title">Welcome to KBC, {user.username}!</h1>
          <div className="user-menu-container">
            <div className="user-icon" onClick={handleUserIconClick}>👤</div>
            {showMenu && (
              <div className="dropdown-menu">
                <div className="menu-item" onClick={handleUserDetailsClick}>User Details</div>
                <div className="menu-item" onClick={handleLogoutClick}>Logout</div>
              </div>
            )}
          </div>
      </div>

<button className="btn-start-game" type="button" onClick={handleStartGame}>Start Game</button>

<h3 className="game-history-title">Game History</h3>

{loading ? (
  <div className="loading-text">Loading game history...</div>
) : error ? (
  <div className="error-text">
    {error}
    <br />
    <small>User ID: {user.userId}</small>
  </div>
) : (
  <div className="history">
    <table className="games-table">
      <thead>
        <tr>
          <th>Game ID</th>
          <th>Total Questions</th>
          <th>Score</th>
          <th>Played At</th>
        </tr>
      </thead>
      <tbody>
        {games.length > 0 ? (
          games.map((game) => (
            <tr key={game.gameId}>
              <td>{game.gameId}</td>
              <td>{game.numOfQuestions}</td>
              <td>{game.score}</td>
              <td>{game.playedAt}</td>
            </tr>
          ))
        ) : (
          <tr>
            <td colSpan="4">No games played yet</td>
          </tr>
        )}
      </tbody>
    </table>
    <span className="game-stats">
      <p>Number of games played: {games.length}</p>
      <p>Average Score: {avgScore.toFixed(2)}%</p>
    </span>
  </div>
)}

    </>
  );
}

export default UserHome;