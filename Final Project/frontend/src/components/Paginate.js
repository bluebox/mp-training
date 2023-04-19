import React from 'react'
import { Pagination } from 'react-bootstrap'
import { Link } from 'react-router-dom'
function Paginate({pages, page, keyword='', isAdmin=false}) {
  return (
    <>
            {pages > 1 && (
                <div className='pagination' style={{width:"150px"}}>
                    <div className={page - 1 < 1?'disabled-link':'link'}>
                        <Link
                            to={!isAdmin
                                ?`/?keyword=${keyword}&page=${page - 1}`
                                : `/admin/productlist?keyword=${keyword}&page=${page - 1}`
                            }
                            style={{textDecoration:"none", padding: "16px", color:'white', pointerEvents: page - 1 < 1? 'none': 'auto'}}
                        >&lt;&lt;
                        </Link>
                    </div>
                
                    <div className="active-link">
                        <Link
                            to={!isAdmin
                                ?`/?keyword=${keyword}&page=${page}`
                                : `/admin/productlist?keyword=${keyword}&page=${page}`
                            }
                            style={{textDecoration:"none", padding: "16px", color:'black', }}
                        >{page}
                        </Link>
                    </div>

                    <div className={page + 1 > pages?'disabled-link':'link'}>
                        <Link
                            to={!isAdmin
                                ?`/?keyword=${keyword}&page=${page + 1}`
                                : `/admin/productlist?keyword=${keyword}&page=${page + 1}`
                            }
                            style={{textDecoration:"none", padding: "16px", color:'white', pointerEvents: page+1>pages? 'none' : 'auto'}}
                        >&gt;&gt;
                        </Link>
                    </div>
                </div>
            )
            }
    </>
  )
}

export default Paginate