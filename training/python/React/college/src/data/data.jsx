import { Link} from 'react-router-dom';

export default function Data(){


    return <div>
        <Link to='/studentsdata'>StudentsData   </Link>
        
        <br />

        <Link to="/departmentsdata">departmentsdata   </Link>
        
        <br />
        <Link to="/coursesdata">coursesdata   </Link>

        <br />
        
        <Link to="/teachersdata">teachersdata   </Link>
    </div>
}