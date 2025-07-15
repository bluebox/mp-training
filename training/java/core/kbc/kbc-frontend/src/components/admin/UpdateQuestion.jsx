import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

function UpdateQuestion() {
  const location = useLocation();
  const navigate = useNavigate();
  const question = location.state?.question;
  const [success, setSuccess] = useState(0);

  const [questionUpdate, setQuestionUpdate] = useState({
    questionId: question.questionId,
    questionText: question.question,
    optionA: question.option1,
    optionB: question.option2,
    optionC: question.option3,
    optionD: question.option4,
    level: question.level,
    status: question.status,
    createdBy: question.createdBy
  });

  const isUnchanged = () => {
    return (
      question.question === questionUpdate.questionText &&
      question.option1 === questionUpdate.optionA &&
      question.option2 === questionUpdate.optionB &&
      question.option3 === questionUpdate.optionC &&
      question.option4 === questionUpdate.optionD &&
      question.level === questionUpdate.level &&
      question.status === questionUpdate.status
    );
  };

  const hasRepeatedConsecutives = (str) => {
    return /(.)\1{2,}/.test(str);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (isUnchanged()) {
      setSuccess(-3); 
      setTimeout(() => setSuccess(0), 3000);
      return;
    }

    const {
      questionId, questionText, optionA, optionB, optionC, optionD, level, status, createdBy
    } = questionUpdate;

    const allFields = [questionText, optionA, optionB, optionC, optionD];
    for (let text of allFields) {
      if (hasRepeatedConsecutives(text)) {
        setSuccess(-4); 
        setTimeout(() => setSuccess(0), 3000);
        return;
      }
    }

    const questionData = {
      questionId,
      question: questionText,
      option1: optionA,
      option2: optionB,
      option3: optionC,
      option4: optionD,
      level,
      status,
      createdBy
    };

    axios.put('http://localhost:8080/api/questions/updateQuestion', questionData)
      .then(response => {
        setSuccess(1);

        question.question = questionUpdate.questionText;
        question.option1 = questionUpdate.optionA;
        question.option2 = questionUpdate.optionB;
        question.option3 = questionUpdate.optionC;
        question.option4 = questionUpdate.optionD;
        question.level = questionUpdate.level;
        question.status = questionUpdate.status;
      })
      .catch(error => {
        if (error.response && error.response.status === 400) {
          setSuccess(-2); 
        } else {
          setSuccess(-1); 
        }
      });

    setTimeout(() => setSuccess(0), 3000);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'questionText' && value.length > 250) return;
    if (['optionA', 'optionB', 'optionC', 'optionD'].includes(name) && value.length > 50) return;

    setQuestionUpdate((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleBack = () => {
    navigate('/admin/questions');
  };

  return (
    <div className="updatediv">
      {success === 1 && <div className="success">Question Updated Successfully</div>}
      {success === -1 && <div className="error">Error in Updating Question</div>}
      {success === -2 && <div className="error">Question Already Exists</div>}
      {success === -3 && <div className="error">No changes made to update</div>}
      {success === -4 && <div className="error">Consecutive repeated characters not allowed</div>}

      <h3>Update Question</h3>
      <form onSubmit={handleSubmit}>
        <div className="formdivs">
          <label>Question Id : </label>
          <input name="questionId" value={questionUpdate.questionId} readOnly />
        </div>
        <div className="formdivs">
          <label>Question : </label>
          <input
            name="questionText"
            value={questionUpdate.questionText}
            onChange={handleChange}
            placeholder="Question Text"
          />
        </div>
        <div className="formdivs">
          <label>Option 1: </label>
          <input
            name="optionA"
            value={questionUpdate.optionA}
            onChange={handleChange}
            placeholder="Option A"
          />
        </div>
        <div className="formdivs">
          <label>Option 2 : </label>
          <input
            name="optionB"
            value={questionUpdate.optionB}
            onChange={handleChange}
            placeholder="Option B"
          />
        </div>
        <div className="formdivs">
          <label>Option 3 : </label>
          <input
            name="optionC"
            value={questionUpdate.optionC}
            onChange={handleChange}
            placeholder="Option C"
          />
        </div>
        <div className="formdivs">
          <label>Option 4 : </label>
          <input
            name="optionD"
            value={questionUpdate.optionD}
            onChange={handleChange}
            placeholder="Option D"
          />
        </div>
        <div className="formdivs">
          <label>Level : </label>
          <select name="level" value={questionUpdate.level} onChange={handleChange}>
            <option value="EASY">EASY</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="HARD">HARD</option>
          </select>
        </div>
        <div className="formdivs">
          <label>Status : </label>
          <select name="status" value={questionUpdate.status} onChange={handleChange}>
            <option value="ACTIVE">ACTIVE</option>
            <option value="INACTIVE">INACTIVE</option>
          </select>
        </div>
        <button type="submit">Update</button>
      </form>
      <a onClick={handleBack} style={{cursor: 'pointer'}}>Back</a>
    </div>
  );
}

export default UpdateQuestion;
