import React, {useState, useEffect, useContext} from 'react'
import Product from '../components/Product'
import {Row, Col} from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import {listProducts} from '../actions/productActions'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useSearchParams } from 'react-router-dom'
import Paginate from '../components/Paginate'
import NoSearchResult from '../components/NoSearchResult'
import { SearchKeywordContext } from '../App'

function HomeScreen() {

  const [searchKeyword, setSearchKeyword] = useContext(SearchKeywordContext)

  const dispatch = useDispatch()
  const productList = useSelector(state => state.productList)
  const {error, loading, products, page, pages} = productList
  const [searchParams, setSearchParams] = useSearchParams()
  let keyword = searchParams.get('keyword')
  let keywordParam = 'keyword='+ keyword
  let pageParam = 'page=' + searchParams.get('page')

  useEffect(() =>{
      dispatch(listProducts(keywordParam,pageParam))  
      if(!keyword || keyword == 'null'){
        setSearchKeyword('')
      } 
  }, [dispatch, searchParams])
  return (
    <div>
        {!keyword || keyword == "null"?<h1>Latest Products</h1>:<h3>Search related to '{keyword}'</h3>}
        {loading? <Loader/>
          : error? <Message variant='danger' children={error}/>
          : products.length == 0?
            <NoSearchResult/>
          :
          <div>
          <Row>
          {products.map(product =>(
              <Col sm={12} md={6} lg={4} xl={3} key={product.id}>
                  <Product product={product}/>
              </Col>
          ))}
      </Row>
      </div>
      }
      {!loading && <Paginate page={page} pages={pages} keyword={keyword} />}
        
    </div>
  )
}

export default HomeScreen