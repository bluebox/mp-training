import './App.css';
// import MyTable from './mytable/MyTable';
// import Counter from './counter/Counter';
import Create from './create/Create';

import { Route, Routes } from 'react-router-dom';
import Home from './home/Home';
import StudentData from './studentdata/StudentData';
import Login from './login/Login';

function NavBar() {
  function handleLogout(){
    localStorage.setItem('isLogin',JSON.stringify(false));
    alert('logged out');
    window.location.reload();
  }
  return (
    <nav className='navbar'>
        
        <a href="/">Home </a>
        <a href="/create">Create</a>
        <a href='/data'>Data</a>
        <a href='/' onClick={handleLogout}>logout</a>
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
       <Route path="/create" element={<Create/>} />
       <Route path='/data' element={<StudentData />} />
       <Route path="*" element={<h2>404 Not Found</h2>} />
     </Routes>
    
    </div>
  );
}


// function App(){
//   return <Create/>
// }

export default App;
