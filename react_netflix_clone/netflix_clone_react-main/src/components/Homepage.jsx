import React from 'react'
import Homerow from "./Homeline";
import Footer from './Footer';

import { Link } from "react-router-dom";
import backgroundphoto from "../resources/netflix_logo.png"
// import logo from "../resources/netflix.avif"
import "../styles/homepage.css"


import photo1 from "../resources/homepage/home1.png"
import photo2 from "../resources/homepage/screen2.png"
import photo3 from "../resources/homepage/screen3.png"
import photo4 from "../resources/homepage/screen4.png"




const Homepage = () => {
  return (
    <>
      <div className="homefold1">
        <div className="heading">
          <img src={backgroundphoto} alt="" />
          <div className="buttons">
            <i className="fa fa-globe" aria-hidden="true"></i>
            <select name="cars" id="cars">
              <option value="volvo">English</option>
              <option value="saab"><i class="fa fa-globe" aria-hidden="true"></i>Hindi</option>
            </select>
            <button className='signin'><Link to="/signin">Sign In</Link></button>
          </div>
        </div>
        <div className="mainthings">
          <h2>
          Unlimited movies, TV<br/>shows and more.
          </h2>
          <h4>Watch anywhere. Cancel anytime.</h4>
          <p>Ready to watch? Enter your email to create or restart your membership.</p>
          <div className="inputmain">
            <input type="text" placeholder='Email Address'/>
           <Link to="../videos"> <button>Get Started ></button></Link>
          </div>
        </div>
      </div>
        <Homerow inpuhead="Enjoy on your TV." inputtext="Watch on smart TVs, PlayStation, Xbox, Chromecast, Apple TV, Blu-ray players and more." inputimg={photo1} check={true}/>
        <Homerow inpuhead="Download your shows to watch offline." inputtext="Save your favourites easily and always have something to watch." inputimg={photo2} check={false}/>
        <Homerow inpuhead="Watch everywhere." inputtext="Stream unlimited movies and TV shows on your phone, tablet, laptop, and TV." inputimg={photo3} check={true}/>
        <Homerow inpuhead="Create profiles for children." inputtext="Send children on adventures with their favourite characters in a space made just for them—free with your membership." inputimg={photo4} check={false}/>
        <Footer/>
    </>
  )
}

export default Homepage