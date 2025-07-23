import { useState } from 'react'
import FormDisplay from './components/FormDisplay'
import Configurations from './components/Configurations'
import User from './components/User'

import reactLogo from './assets/react.svg'
import './App.css'
import Counter from './components/test'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <FormDisplay/>
    {/* <Counter/>   */}
    <Configurations/>
    </>

  );
}

export default App
