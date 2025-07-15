import { useState, useEffect } from 'react';

function DisplayQuestion({
    sno,
    question,
    timer,
    handleOptionSelect,
    selectedOption,
    handleNextClick,
    handleExitClick,
    optionColor
}) {
    const [options, setOptions] = useState([]);

    useEffect(() => {
        setOptions([question.optionA, question.optionB, question.optionC, question.optionD]);
    }, [question]);

    return (
        <>
            <div className="timer-div">
                <strong>Timer: {timer}s</strong>
            </div>
            <br />
            <div id="question" className="question-div">
                
                <strong>{sno+1}. {question.question}</strong>
            </div>
            <br />
            <div className="option-grid">
                {options.map((option, index) => {
                    let className = 'option-btn';
                    if (selectedOption === option) {
                        if (optionColor === 'green') {
                            className += ' green';
                        } else if (optionColor === 'red') {
                            className += ' red';
                        } else {
                            className += ' selected';
                        }
                    }

                    return (
                        <button
                            key={index}
                            className={className}
                            onClick={() => handleOptionSelect(option)}
                            disabled={optionColor !== null}
                        >
                            {option}
                        </button>
                    );
                })}
            </div>
            <br />
            <div>
                <button
                    id="exit"
                    className="exit-btn"
                    style={{
                        position: 'fixed',
                    }}
                    onClick={handleExitClick}
                >
                    Exit Game
                </button>
            </div>

            <div>
                <button
                    id="next"
                    className="next-btn"
                    style={{
                        position: 'fixed',
                    }}
                    onClick={handleNextClick}
                >
                    Next - &gt;
                </button>
            </div>
        </>
    );
}

export default DisplayQuestion;