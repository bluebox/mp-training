import React from 'react'
import { Link } from 'react-router-dom'

function Paginate({pages, page, keyword, isAdmin=false}) {
  
 
  return ( pages > 1 && (
    <div className='pagination'>
      {[...Array(pages).keys()].map((x) => (
        <Link
          to={`/?keyword=${keyword}&page=${x+ 1}`}
          className={x + 1 === page ? 'active actual-link': 'actual-link'}
        >{x + 1}</Link>
      ))}
    </div>
  )
  )
}

export default Paginate