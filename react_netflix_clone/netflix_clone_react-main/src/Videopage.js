import React from 'react'
import Banner from './components/Banner';
import requests from './components/request';
import Row from './components/Row';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import SimpleBackdrop from "./components/Spinner"
import Popup from './components/Popup';
import { useState,useEffect } from 'react'
import { useCookies } from "react-cookie";

function Videopage() {
  const[login,setlogin] = useState();
  const [cookies,setcookies] = useCookies(['user_login']);
  const [load,setload] = useState(true);
  // setlogin(cookies.login);
  useEffect(() => {
    function checkcookie(){
      if(cookies.login == "false"){
        setlogin(true);
        setcookies("login","true")
        console.log("hello");
      }
      else{
        setlogin(false);
      }
      
    }
    checkcookie()
  }, [])
  
  console.log(login)
  
  setTimeout(() => {
    setload(false);
  }, 3000);
  return (
   <>
   {/* <SimpleBackdrop/> */}
    <Navbar/>
    <div className="App">
  <Banner fetchUrl={requests.fetchNetflixOriginals}/>
  {login?load? <Popup/>:<i></i>:<p></p>}

   <Row title="Netflix Originals" fetchUrl={requests.fetchNetflixOriginals} bigImg={false}/>
   <Row title="Action Movies" fetchUrl={requests.fetchActionMovies} bigImg={true}/>
   <Row title="Comedy Movies" fetchUrl={requests.fetchComedyMovies} bigImg={true}/>
   <Row title="Original Documentaries" fetchUrl={requests.fetchDocumentaries} bigImg={false} medImg={true}/>
   <Row title="Horror Movies" fetchUrl={requests.fetchHorrorMovies} bigImg={true} />
    </div>
    <Footer/>
   </>
  )
}

export default Videopage