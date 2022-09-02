import React from 'react'
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import "../styles/productpage.css"
import { useParams } from "react-router-dom";

function ProductPage() {
    const BASE_URL="https://image.tmdb.org/t/p/original";
    const { head, img ,overview} = useParams();
    console.log(head)
    console.log(img)
  return (
      <>
      <Navbar/>
    <div className="backfold1" style={{
      backgroundImage: `url("${BASE_URL}/${img}")`}}>
        <h1>{head}</h1>
        <div className="extrabuttons">
            <button>Play</button>
            <button>Add to Wishlist</button>
        </div>
    </div>
    <div className="backfold2">
        <div className="left">
        <div className='firstline'>

            <p>98% match</p>
            <p>2022</p>
            <p>U/A 16+</p>
            <p>4 seasons</p>
        </div>
<div className="secondline">
    <p>#4 in TV Shows Today</p>
</div>
<div className='paradetails'>
    {overview}...
    {/* Pathbreaking designer. Fashion Icon. (Somewhat) dutiful daughter. Masaba Gupta is living the dream. Now, if only she could get her love life  in order. */}
</div>
    </div>
 
    <div className="right">
        <p><span className="silver">Cast:</span>Winnora Ryder , David Harbour, Mille Bobby</p>
        <p><span className="silver">Generes:</span>Sci-Fi Tv , Teen Tv shows, Tv Horror</p>
        <p><span className="silver">This show is:</span> Omnious, Scary</p>
    </div>
    </div>
    <Footer/>
    </>
  )
}

export default ProductPage