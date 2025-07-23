import { createContext, useState } from 'react'
import './index.css'
import ClassBasedExample from './components/ClassBasedExample'
import FunctionBasedExample from './components/FunctionBasedExample'
import { ThemeContext } from './components/ThemeContext'

function App() {
  return (
    <ThemeContext.Provider value={{msg:"this is from the useContext"}}>
    <div className='p-4'>
     <ClassBasedExample  value="Class Based Component"/>
     <FunctionBasedExample value="this is a prop"/>
    </div>
    </ThemeContext.Provider>
  )
}

export default App
