import { useState } from 'react'
import './App.css'
import Home from './components/Home'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Navbar from './components/Navbar'
import Create from './components/Create'
import {EditContext} from './components/EditContext'
import NotFound from './components/NotFound'
import DynamicRoute from './components/DynamicRoute'

function App() {
  const [EditData, setEditData] = useState([])
  document.title='React-router-dom'
  return (
    <EditContext.Provider value={{ EditData, setEditData }}>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/create' element={<Create />} />
          <Route path='/:id' element={<DynamicRoute/>}/>
           <Route path='*' element={<NotFound/>}/>
        </Routes>
      </Router>
    </EditContext.Provider>
  )
}

export default App
