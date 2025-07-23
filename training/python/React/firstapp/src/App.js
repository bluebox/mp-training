import './App.css';
import Create from './create/Create';
// import MyTable from './mytable/MyTable';
// import Counter from './counter/Counter';
import { Route, Routes } from 'react-router-dom';
import Home from './home/Home';
import StudentData from './studentdata/StudentData';


function NavBar() {
  return (
    <nav className='navbar'>
        <a href="/">Home </a>
        <a href="/create">Create</a>
        <a href='/data'>Data</a>
    </nav>
  );
}

function App() {
  return (
    <div className="App">
      <NavBar />
      <Routes>
       <Route path="/" element={<Home />}>Home</Route>
       <Route path="/create" element={<Create/>}>Create</Route>
       <Route path='/data' element={<StudentData />} Data >/</Route>
       <Route path="*" element={<h2>404 Not Found</h2>} />
     </Routes>
    
    </div>
  );
}

export default App;
