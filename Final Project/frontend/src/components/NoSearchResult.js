import React from 'react'
import { Image } from 'react-bootstrap'
function NoSearchResult() {
  return (
    <div className='page-not-found'>
        <Image src='/images/bhalu_dance.gif' alt="bhalu dance" style={{borderRadius:"50%", margin:"24px", height:"50vh"}}></Image>
        <h3>No Search Results</h3>
    </div>
  )
}

export default NoSearchResult