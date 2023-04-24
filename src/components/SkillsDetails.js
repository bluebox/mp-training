import Table from 'react-bootstrap/Table';

function Skills({skills}) {


  return (
    <>
    <Table striped="columns my-3" >
        <h3>Skill Set</h3>
        <tbody>
            {
              skills.map((skill, index) => (
                  <tr>
                      <th>Skill {index + 1}</th>
                      <td>{skill.skill_name}</td>
                  </tr>
              ))
          }
          
        </tbody>
        
    </Table> 
    </>
  );
}

export default Skills;

