import { useContext, useEffect, useState } from 'react'
import './App.css'
import Home from './components/Home'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Navbar from './components/Navbar'
import Create from './components/Create'
import { EditContext } from './components/EditContext'
import NotFound from './components/NotFound'
import DynamicRoute from './components/DynamicRoute'
import Login from './components/Login'
import ProtectedRoute from './components/ProtectedRoute'
import {AuthContext} from './components/AuthContext'

function App() {
  const [EditData, setEditData] = useState([])
  document.title = 'React-router-dom'
  const {isLoggined}=useContext(AuthContext)
  return (  
    <EditContext.Provider value={{ EditData, setEditData }}>
      <Router>
        {isLoggined && <Navbar />}
        <Routes>
          <Route path='/' element={<ProtectedRoute><Home /></ProtectedRoute>} />
          <Route path='/create' element={<ProtectedRoute><Create /></ProtectedRoute>} />
          <Route path='/:id' element={<DynamicRoute />} />
          <Route path='/login' element={<Login />} />
          <Route path='*' element={<NotFound />} />
        </Routes>
      </Router>
    </EditContext.Provider>
  )
}

export default App
