import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
  function AddQuestion(){
    const navigate = useNavigate();
    const [success, setSuccess] = useState(0);
    const [question, setQuestion] = useState({
        questionText: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        level: ""

    });
    const handleSubmit = (e) => {
        e.preventDefault();
        const { questionText, optionA, optionB, optionC, optionD, level } = question;
      
        const questionData = {
            question:questionText,
            option1: optionA,
            option2: optionB,
            option3: optionC,
            option4: optionD,
            level: level,
            status: "ACTIVE",
            createdBy: "admin"
        };
        console.log('Submitting question:', questionData);
        axios.post('http://localhost:8080/api/questions/addQuestion', questionData)
        .then(response => {
            setSuccess(1);
            console.log('Success:', response.data);
             setQuestion({
            questionText: "",
            optionA: "",
            optionB: "",
            optionC: "",
            optionD: "",
            level: ""
        });
        })
        .catch(error => {
          setSuccess(-1);
            if (error.response && error.response.status === 400) {
              setSuccess(-2);
            }
            console.error('Errorrrr:', error);
        });
        setTimeout(() => {
            setSuccess(0);
        }, 3000);
    }


  const handleChange = (e) => {
  const { name, value } = e.target;

  const maxLength = name === "questionText" ? 250 : 50;

  const threeConsecSpecials = /[^a-zA-Z0-9\s]{3,}/;
  const threeConsecDigits = /\d{3,}/;
  const threeConsecSameLetters = /([a-zA-Z])\1{2,}/;
  const fourDigitGroup = /\b\d{4}\b/;

  if (
    value.length > maxLength ||
    threeConsecSpecials.test(value) ||
    threeConsecDigits.test(value) ||
    threeConsecSameLetters.test(value) ||
    fourDigitGroup.test(value)
  ) {
    return; 
  }

  setQuestion((prev) => ({
    ...prev,
    [name]: value,
  }));
};

const handleBack = () => {
    navigate('/admin/home');
};
   
    return(
        <div className="updatediv">
          {success === 1 && <div className="success">Question Added Successfully</div>}
          {success === -1 && <div className="error">Error Adding Question</div>}
          {success === -2 && <div className="error">Question Already Exists</div>}
      <br />
        <h3 >Add Question</h3>
      <form onSubmit={handleSubmit}>
        <div className="formdivs">
          <label htmlFor="id">Question : </label>
        <input
          name="questionText"
          onChange={handleChange}
          placeholder="Question Text"
          value={question.questionText}
        ></input>
        </div>
        <div className="formdivs">
          <label htmlFor="id">Option 1 [Correct Answer] : </label>
        <input
          name="optionA"
          onChange={handleChange}
          placeholder="Option A"
          value={question.optionA}
        />
        </div>
        <div className="formdivs">
            <label htmlFor="id">Option 2 : </label>
        <input
          name="optionB"
          onChange={handleChange}
          placeholder="Option B"
          value={question.optionB}
        />
        </div>
         <div className="formdivs">
           <label htmlFor="id">Option 3 : </label>
        <input
          name="optionC"
          onChange={handleChange}
          placeholder="Option C"
          value={question.optionC}
        />
         </div>
        <div className="formdivs">
          <label htmlFor="id">Option 4 : </label>
        <input
          name="optionD"
          onChange={handleChange}
          placeholder="Option D"
          value={question.optionD}
        />
        </div>
        <div className="formdivs">
           <label htmlFor="id">Level : </label>
        <select name="level" 
          value={question.level}
          onChange={handleChange } required>
            <option value="" disabled>
            -- Select Level --
          </option>
          <option value="EASY">EASY</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="HARD">HARD</option>
        </select>
         </div>
                
        <button type="submit"> Add Question</button>
      </form>
          <a onClick={handleBack} >Back</a>
      </div>
    )
    }
  export default AddQuestion;