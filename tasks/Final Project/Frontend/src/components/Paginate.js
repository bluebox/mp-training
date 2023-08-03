import React from 'react'
import { Pagination } from 'react-bootstrap'
import { Link } from 'react-router-dom'

function Paginate({ pages, page, keyword='', isAdmin=false}) {

    console.log('KEYWORD:', keyword)
    return (pages > 1 && (
        <div>
            {[...Array(pages).keys()].map((x) => (
                <div style={{display:"inline"}} key={x+1}>
                <Link
                    key={x + 1}
                    to={`/?keyword=${keyword}&page=${x + 1}`}
                >
                {x +1}
                </Link>
                </div>
        ))}
        </div>
  )
  )
}

export default Paginate
