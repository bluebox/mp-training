import React,{useEffect, useState} from 'react'
import axios from '../axios.js';
import Boot from './Boot.jsx';
import "../styles/row.css"
import { Link } from 'react-router-dom';

// function sleep(ms) {
//   return new Promise(resolve => setTimeout(resolve, ms));
// }

function Row({title,fetchUrl,bigImg,medImg=false}) {
    const arr_y=[1,2,3,4,5,6,7,8,9];
    const [movies,setMovies] = useState([]);
    const [bootyes,setboot] = useState(false);
    const BASE_URL="https://image.tmdb.org/t/p/original";
    useEffect(()=>{
        async function fetchData(){
            // await sleep(3000);
            const request = await axios.get(fetchUrl).then(setboot(true));
            setMovies(request.data.results)
            console.log(request.data.results)
            return request;
        }
        try {
          
          fetchData();
        } catch (error) {
          console.log(error)
          fetchData();
        }
    },[fetchUrl]

    )
  
    return (
      <>

    <h2 id="ttle">{title}</h2>
    <div className='flex'>
      {bootyes?movies.map(movies=>(
    // "<p>hello</p>"
    <div className={`cards ${bigImg?"bigimg":`${medImg?"medImg":"nan"}`} `}>
    <img src={`${BASE_URL}${movies.poster_path}`} alt="image not found" id="cardsimg" />
    <Link to={`../about/${movies.original_name}${movies.poster_path}/${movies.overview.slice(0,100)}`} className='placebutton'>Play</Link>
    </div>
    // console.log(`${BASE_URL}${movies.poster_path}`)
  )
  ):arr_y.map(movies=>
    <div className={`cards ${bigImg?"bigimg":`${medImg?"medImg":"nan"}`} `}>
    <div className='imgalt'>
      <Boot/>
      </div>
    </div>
    )
}

</div>

</>
  )
}

export default Row;