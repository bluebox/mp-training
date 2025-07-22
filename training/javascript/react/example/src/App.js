import logo from './logo.svg';
import './App.css';
import Header from './components/header'


function FirstComponent(){
  return(
    <div style={{color:"red"}}>
      <p>Hello world</p>
    </div>
  );
}
function Company(){
  return(
    <div>
      MEDPUS
    </div>
  )
}

function Body(){
  return(
    <div>
      Madhapur, Hyderabad.
    </div>
  )
}

function App() {
  return (
    <div>
      < Header/>
      <FirstComponent />
      <Company />
      <Body />
    </div>
  );
}

export default App;
