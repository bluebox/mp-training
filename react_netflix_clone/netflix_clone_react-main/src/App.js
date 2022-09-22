// import logo from './logo.svg';
import './App.css';
import Signin from "./components/Signin"
import {Routes,Route,BrowserRouter} from "react-router-dom"
import Videopage from './Videopage';
import Homepage from "./components/Homepage"
import ProductPage from './components/ProductPage';

function App() {
  return (
    <>
    <BrowserRouter>
    <Routes>
    <Route exact path='/' element={< Homepage />}></Route>
    <Route exact path='/signin' element={< Signin />}></Route>
    <Route exact path='/videos' element={< Videopage />}></Route>
    <Route exact path='/about/:head/:img/:overview' element={<ProductPage />}></Route>
    
</Routes>
</BrowserRouter>

    
    </>
  );
}

export default App;
