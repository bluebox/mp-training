import { Link} from 'react-router-dom';

export default function Create(){

    return <div>

        <Link to="/createstudents">create Student  </Link>

        <br />
        <Link to='/createdepartments'>create departments   </Link>

        <br />
        <Link to='/createcourses'>create courses   </Link>

        <br />
        <Link to='/createteachers'>create teachers   </Link>
    </div>
}