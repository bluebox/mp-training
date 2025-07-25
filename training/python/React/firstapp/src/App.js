import './App.css';
// import MyTable from './mytable/MyTable';
// import Counter from './counter/Counter';
import Create from './create/Create';
import { Route, Routes ,Link} from 'react-router-dom';
import Home from './home/Home';
import StudentData from './studentdata/StudentData';
import Login from './login/Login';
import store from './redux_store/store.js';
import { Provider } from 'react-redux';

function NavBar() {
  const handleLogout =(e) =>{
    e.preventDefault();
    localStorage.setItem('isLogin',JSON.stringify(false));
    alert('logged out');
    window.location.reload();
  }
  return (
    <nav className='navbar'>
        
        <Link to="/" >Home </Link>

        <Link to="/create">Create</Link>

        <Link to='/data'>Data</Link>

        <Link to='/' onClick={handleLogout}>logout</Link>
    </nav>
  );
}

function App() {
  const data = JSON.parse(localStorage.getItem('isLogin'));
  if(!data){
    return <Login/>
  }
  return (
    <div className="App">
      <NavBar />
      <Routes>
       <Route path="/" element={<Home />} />
       <Route path='/login' element={<Login />} />
       <Route path="/create" element={<Provider store={store}><Create/></Provider>} />
       <Route path='/data' element={<Provider store={store}><StudentData /></Provider>} />
       <Route path="*" element={<h2>404 Not Found</h2>} />
     </Routes>
    
    </div>
  );
}


// function App(){
//   return <Create/>
// }

export default App;
