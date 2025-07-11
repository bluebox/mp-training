import React from 'react';
import LoginPage from './LoginPage';
import './App.css';
import BasicPdf from './pdf/BasicPdf';

function App() {
  const user = { name: "Bheem", customerId:2 };
  return (
    <div className="App">
      <BasicPdf user={user}/>
    </div>
  );
}

export default App;
// function App() {
//   return <LoginPage />;
// }
// export default App;