import React, {useState, useEffect} from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { LinkContainer } from 'react-router-bootstrap';
import { Table, Button, Row, Col } from 'react-bootstrap';
import Message from '../components/Message';
import Loader from '../components/Loader';
import { listProducts } from '../actions/productActions';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { PRODUCT_CREATE_RESET } from '../constants/productConstants';
import Paginate from '../components/Paginate';
import {deleteProduct} from '../services/productServices';
function ProductListScreen() {
  const {id} = useParams()
  const {error, products, loading, page, pages} = useSelector(state=>state.productList)
  const {userInfo} = useSelector(state=>state.userLogin)
  
  const dispatch = useDispatch()
  const navigate = useNavigate()
  
  const [successDelete, setSuccessDelete] = useState(false)
  const [errorDelete, setErrorDelete] = useState('')

  const [searchParams, setSearchParams] = useSearchParams()
  let keyword = searchParams.get('keyword')
  let keywordParam = 'keyword='+keyword
  let pageParam = 'page=' + searchParams.get('page')

  const deleteHandler = (id) =>{
    if (window.confirm('Are you sure you want to delete this product?')){
      deleteProduct(id, setSuccessDelete, setErrorDelete)
    }
  }

  const createProductHandler = () =>{
    navigate('/admin/product/create')
  }


  useEffect(()=>{
    if ( !userInfo.isAdmin){
      navigate('/login')
    }
    dispatch(listProducts(keywordParam, pageParam))
  }, [dispatch, navigate, successDelete, userInfo, keywordParam, pageParam])

  return (
    <div>
    <Row className='align-items-center'>
        <Col>
            <h1>Products</h1>
        </Col>
        <Col sm={3}>
            <Button className='my-3' onClick={createProductHandler}><i className='fas fa-plus'></i> Create Product</Button>
        </Col>
    </Row>
    {loading?(
      <Loader/>
    ) : error ? (
      <Message variant='danger'>{error}</Message>
    ) : (
      <Table striped bordered hover responsive className='table-sm'>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Brand</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>{product.category}</td>
              <td>{product.brand}</td>
              <td>
                <LinkContainer to={`/admin/product/${product.id}`}>
                  <Button className="btn-sm" variant='light'>
                    <i className='fas fa-edit'></i>
                  </Button>
                </LinkContainer>
                <Button variant='danger' className='btn-sm' onClick={()=> deleteHandler(product.id)}>
                  <i className='fas fa-trash'></i>
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    )}
      {!loading && !error && <Paginate keyword={keyword} page={page} pages={pages} isAdmin={true}/>}
    </div>
  )
}

export default ProductListScreen


