import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const Questions = () => {
  const navigate = useNavigate();
  const [questions, setQuestions] = useState([]);
  const [empty, setEmpty] = useState();

    useEffect(()=>{
      axios.get("http://localhost:8080/api/questions/getAllQuestions")
      .then((response)=> {
        if (Array.isArray(response.data)) {
          setQuestions(response.data);
        } else if (response.data && typeof response.data === 'object') {
          setQuestions(Object.values(response.data));
        } else {
          setQuestions([]);
        }
      })
      .catch((error) => {
        console.error(error);
        setQuestions([]);
      })
  },[]);

 useEffect(() => {
    setEmpty(questions.length === 0);
  }, [questions]);

  const handleBackToHome = () => {
    navigate('/admin/home');
  };

  return (
    <div className="questionsdiv">
      {empty && <div>
        <h2>No Questions Available</h2>
        <p>Please add questions to the database.</p>
      </div>}
      {!empty && 
      <div className="questionsdiv">
        <h2>All Questions</h2>
      <table border="1" cellPadding="8" style={{ borderCollapse: "collapse" }}>
        <thead>
          <tr>
            <th>Id</th>
            <th>Question</th>
            <th>Options - [ First Option is Correct ]</th>
            <th>Level</th>
            <th>Status</th>
            {/* <th>Created At</th>
            <th>Created By</th>
            <th>Modified At</th>
            <th>Modified By</th> */}
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
        {questions.map((each)=>(
             <tr key={each.questionId} 
             className={each.status === "INACTIVE" ? "inactive" : ""}>
            <td>
              {each.questionId}
            </td>
            <td>
              {each.question}
            </td>
            <td>
              {each.option1} | {each.option2} | {each.option3} | {each.option4}
            </td>
            <td>
              {each.level}
            </td>
            <td>
              {each.status}
            </td>
            {/* <td>
              {each.createdAt}
            </td>
            <td>
              {each.createdBy}
            </td>
            <td>
              {each.modifiedAt}
            </td>
            <td>
              {each.modifiedBy}
            </td> */}
            <td className="questionedit" id={each.questionId} onClick={() => {
                navigate('/admin/update-question', { state: { question: each } });
            }}>
              &#9998; Edit
            </td>
          </tr>
          )
        )
        }
        </tbody>
      </table>
    </div>}  
      <a onClick={handleBackToHome} style={{cursor: 'pointer'}}>Back to home</a>
    </div>
  );
};

export default Questions;
