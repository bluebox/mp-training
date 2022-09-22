import React, { useState,useEffect } from 'react'
import axios from '../axios'
import SimpleBackdrop from './Spinner';
import "../styles/banner.css"
import Boot from './Boot';

// function sleep(ms) {
//   return new Promise(resolve => setTimeout(resolve, ms));
// }

function Banner({fetchUrl}) {
    const [movies,setMovies]=useState([]);
    const [des,setdes]=useState("");
    const [loader,setloader]=useState(false);
    const BASE_URL="https://image.tmdb.org/t/p/original";
    const [load,setload] = useState(true);
    useEffect(() => {
        async function loadMovie(){
            const res=await axios.get(fetchUrl);
            let randomint = Math.floor((Math.random() * 15) + 1);
            setMovies(res.data.results[randomint])
            setloader(true)
            setdes(res.data.results[randomint].overview.slice(0,100)+"...");
        }
        loadMovie();
        setInterval(() =>loadMovie(), 3000);
      }, [])
      setTimeout(() => {
        setload(false);
      }, 3000);
    
  return (
    <>
   
    {loader?    <section className='mainbanner' style={{
      backgroundImage: `url(${BASE_URL}${movies.backdrop_path})`,
      backgroundRepeat: "no-repeat"
    }}>
        {/* </style> */}
        {/* <img src={`${BASE_URL}${movies.backdrop_path}`} alt=''/> */}
        <h2 id="orginalname">{movies.original_name}</h2>
        <h4 id="overview">{des}</h4>
       <div className='buttons'>
        <button>Play Now</button>
        <button>Watch Later</button>
        </div>
    </section>: <section className='mainbannerload'>
      <Boot/>
      </section>}
    </>
  )
}

export default Banner