import { useState, useEffect } from 'react';
import axios from 'axios';
import DisplayQuestion from './DisplayQuestion';
import Level from './Level';
import '../../App.css';

function DisplayGame() {
  const [level, setLevel] = useState(null);
  const [questions, setQuestions] = useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [timer, setTimer] = useState(60);
  const [score, setScore] = useState(0);
  const [quizOver, setQuizOver] = useState(false);
  const [showResultPopup, setShowResultPopup] = useState(false);
  const [selectedOption, setSelectedOption] = useState(null);
  const [numOfQuestions, setNumOfQuestions] = useState();
  const [errorMessage, setErrorMessage] = useState(null);
  const [optionColor, setOptionColor] = useState(null);

  const fetchGameConfigDetails = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/game-config/get-questions-and-timer');
      setNumOfQuestions(response.data.noQuestions);
      setTimer(response.data.timeAllocated);
    } catch (error) {
      setErrorMessage('Error while fetching game configuration details.');
      console.error(error);
    }
  };

  const fetchQuestions = async (level, numOfQuestions) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/questions/${level}/${numOfQuestions}`);
      if(response.data==="Questions List is Empty"){
        setErrorMessage("Not Enough Questions in this level to display.");
      }else{
      setQuestions(response.data);
      }
    } catch (error) {
      setErrorMessage('Error while fetching Questions.');
      console.error(error);
    }
  };

  

  const handleLevel = async (selectedLevel) => {
    setLevel(selectedLevel);
    await fetchGameConfigDetails();
  };

  useEffect(() => {
    if (level && numOfQuestions) {
      fetchQuestions(level, numOfQuestions);
    }
  }, [level, numOfQuestions]);

  const handleNextClick = () => {
    if (!selectedOption) return;

    const currentQuestion = questions[currentQuestionIndex];
    const questionId = currentQuestion.questionId;

    axios.get(`http://localhost:8080/api/questions/checkAnswer/${questionId}/${selectedOption}`)
      .then((response) => {
        const isCorrect = response.data;

        if (isCorrect) {
          setOptionColor('green');

          setTimeout(() => {
            const updatedScore = score + 1;

            if (currentQuestionIndex + 1 >= questions.length) {
              gameEnd(updatedScore);
            } else {
              setScore(updatedScore);
              setCurrentQuestionIndex(prev => prev + 1);
              setSelectedOption(null);
              setOptionColor(null);

              axios.get('http://localhost:8080/api/game-config/get-questions-and-timer')
                .then((response) => {
                  setTimer(response.data.timeAllocated);
                })
                .catch((error) => {
                  setErrorMessage('Error while updating timer.');
                  console.error(error);
                });
            }
          }, 1000);

        } else {
          setOptionColor('red');

          setTimeout(() => {
            gameEnd(score);
          }, 1000);
        }
      })
      .catch((error) => {
        setErrorMessage('Error while checking answer.');
        console.error(error);
      });
  };

  const handleExitClick = () => {
    if (!selectedOption) {
      gameEnd(score);
      return;
    }

    const currentQuestion = questions[currentQuestionIndex];
    const questionId = currentQuestion.questionId;

    axios.get(`http://localhost:8080/api/questions/checkAnswer/${questionId}/${selectedOption}`)
      .then((response) => {
        const isCorrect = response.data;
        if (isCorrect) {
          gameEnd(score + 1);
        }
        else {
          gameEnd(score);
        }
      })
      .catch((error) => {
        setErrorMessage('Error while exiting game.');
        console.error(error);
      });
  };

  const submitGameResult = async (finalScore) => {
    try {
          const response = await axios.get('http://localhost:8080/api/user/user-info');
      const userId = response.data.userId;
      console.log("UserId is ",userId);

      const postBody = {
        userId: userId,
        numOfQuestions: numOfQuestions,
        score: finalScore
      };

      await axios.post('http://localhost:8080/api/game/add', postBody);
      console.log("Game added to DB successfully.");
    } catch (error) {
    }
  };

  const gameEnd = async (finalScore) => {
    await submitGameResult(finalScore);
    setScore(finalScore);
    setQuizOver(true);
    setShowResultPopup(true);
  };

  useEffect(() => {
    let timerInterval;
    if (level && !quizOver) {
      timerInterval = setInterval(() => {
        setTimer((prev) => {
          if (prev <= 1) {
            clearInterval(timerInterval);
            gameEnd(score);
            return 0;
          }
          return prev - 1;
        });
      }, 1000);
    }
    return () => clearInterval(timerInterval);
  }, [level, quizOver, score]);

  return (
    <main-div>
      {!level ? (
        <Level handleLevel={handleLevel} />
      ) : (
        <game-div>
          {questions.length > 0 && !quizOver && (
            <DisplayQuestion
              sno={currentQuestionIndex}
              question={questions[currentQuestionIndex]}
              timer={timer}
              handleOptionSelect={setSelectedOption}
              selectedOption={selectedOption}
              handleNextClick={handleNextClick}
              handleExitClick={handleExitClick}
              optionColor={optionColor}
            />
          )}

          {errorMessage && (
            <div className="error-popup">
              <p>{errorMessage}</p>
              <button className="close-btn" onClick={() => { setErrorMessage(null); window.location.reload(); }}>Close</button>
            </div>
          )}

          {showResultPopup && (
            <div className="result-div">
              {score=== numOfQuestions &&<h3>Congratulations 🎉, You became Crorepathi!</h3>}
              {score < numOfQuestions &&<h3>Better Luck Next time 👍</h3>}

              <h3>Quiz Over!</h3>
              <div className="score-div">
              <p>You Scored : {score}/{numOfQuestions}</p>
              </div>
              <button className="button-m"onClick={() => (window.location.href = "../user/home")}> 
                Back to Home Page
              </button>
            </div>
          )}
        </game-div>
      )}
    </main-div>
  );
}

export default DisplayGame;