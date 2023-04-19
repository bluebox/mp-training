import React, {useState, useEffect} from 'react'
import { detailProducts } from '../actions/productActions' 
import {Link, useNavigate} from 'react-router-dom'
import {Form, Button} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import { useParams } from 'react-router-dom'
import {updateProduct} from '../services/productServices'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function ProductEditScreen() {

    const {id} = useParams()

    const [imageFile, setImageFile] = useState({})
    const [image, setImage] = useState('')
    const [uploading, setUploading] = useState(false)
    const [errorUpdate, setErrorUpdate] = useState('')
    const [successUpdate, setSuccessUpdate] = useState(false)
    const [loadingUpdate, setLoadingUpdate] = useState('')
    const navigate = useNavigate()

    const dispatch = useDispatch()


    const {userInfo} = useSelector(state=>state.userLogin)

    const productDetails= useSelector(state => state.productDetails)
    const {loading, error, product} = productDetails

    const formik = useFormik({
        initialValues:{
            name: product && product.name,
            brand: product && product.brand,
            price: product && product.email,
            category: product && product.category,
            description: product && product.description,
            countInStock: product && product.countInStock,
        },
        onSubmit:values=>{
            if(imageFile == {}){
                updateProduct({
                    id: product.id, 'name': values.name, 'price': values.price, 'category': values.category,'brand': values.brand, 'description':values.description, 'countInStock': values.countInStock
                }, setLoadingUpdate, setSuccessUpdate, setErrorUpdate)
            }
            updateProduct({
                id: product.id, 'name': values.name, 'price': values.price, 'category': values.category,'brand': values.brand, 'description':values.description, 'countInStock': values.countInStock, image:imageFile
            }, setLoadingUpdate, setSuccessUpdate, setErrorUpdate)
        },
        validationSchema: Yup.object({
            name: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .max(255, "Name should be less than 255 characters.")
              .required("Name is required"),
            price: Yup.number()
              .min(0, "Price must be 3 characters or more.")
              .required("Price is required"),
            brand: Yup.string()
              .min(3, "Brand must be 3 characters or more.")
              .max(255, "Brand should be less than 255 characters.")
              .required("Brand is required"),
            category: Yup.string()
              .min(3, "Category must be 3 characters or more.")
              .max(255, "Category should be less than 255 characters.")
              .required("Category is required"),
            description: Yup.string()
              .min(3, "description must be 3 characters or more.")
              .max(255, "description should be less than 255 characters.")
              .required("Description is required"),
            countInStock: Yup.number()
              .moreThan(0, "Count in stock cannot be negative")
              .required("countInStock is required"),
            
          }),
 
    })



    
    useEffect(() =>{
        if(successUpdate){
            dispatch(detailProducts(id))
            navigate(-1)
        }else{
            if(!product.name  || product.id != id ){
                dispatch(detailProducts(id))
            }else{
                formik.initialValues.name = product.name
                formik.initialValues.price = product.price
                formik.initialValues.category = product.category
                formik.initialValues.brand = product.brand
                formik.initialValues.description = product.description
                formik.initialValues.countInStock = product.countInStock
                setImage(product.image)
            }
        }
    }, [dispatch, product, id, navigate, successUpdate, uploading])


    
  return (
    <div>
        <Link to='/admin/productlist'>
            Go Back
        </Link>
        {loadingUpdate && <Loader/>}
        {errorUpdate && <Message variant='danger'>{errorUpdate}</Message>}
        {loading
        ? (
            <Loader/>
        ): error
        ? (
            <Message variant='danger'>{error}</Message>
        ):(
            <FormContainer>
                <h1>Edit Product</h1>

                <Form onSubmit={formik.handleSubmit}>
                    <Form.Group controlId='name' className="form-child">
                        <Form.Label>Product name
                        <span className='d-block form-error'>
                        {(formik.touched.name && formik.errors.name)
                        && formik.errors.name}
                    </span>
                        </Form.Label>
                        <Form.Control
                            type='text'
                            placeholder='enter name'
                            value = {formik.values.name}
                            onChange = {formik.handleChange}
                        >
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='brand' className="form-child">
                        <Form.Label>Brand
                        <span className='d-block form-error'>
                        {(formik.touched.brand && formik.errors.brand)
                        && formik.errors.brand}
                    </span>
                        </Form.Label>
                        <Form.Control
                            type='text'
                            placeholder='enter brand'
                            value = {formik.values.brand}
                            onChange = {formik.handleChange}
                        >                    
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='price' className="form-child">
                        <Form.Label>Price
                        <span className='d-block form-error'>
                        {(formik.touched.price && formik.errors.price)
                        && formik.errors.price}
                    </span>
                        </Form.Label>
                        <Form.Control
                            type='number'
                            step='0.01'
                            placeholder='enter price'
                            value = {formik.values.price}
                            onChange = {formik.handleChange}
                        >                    
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='category' className="form-child">
                        <Form.Label>Category
                        <span className='d-block form-error'>
                        {(formik.touched.category && formik.errors.category)
                        && formik.errors.category}
                    </span>
                        </Form.Label>
                        <Form.Control
                            type='text'
                            placeholder='Category'
                            value = {formik.values.category}
                            onChange = {formik.handleChange}
                        >                    
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='countInStock' className="form-child">
                        <Form.Label>Count in Stock
                        <span className='d-block form-error'>
                        {(formik.touched.countInStock && formik.errors.countInStock)
                        && formik.errors.countInStock}
                    </span>
                        </Form.Label>
                        <Form.Control
                            type='number'
                            placeholder='enter Count in Stock'
                            value = {formik.values.countInStock}
                            onChange = {formik.handleChange}
                        >                    
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='description' className="form-child">
                        <Form.Label>Description
                        <span className='d-block form-error'>
                        {(formik.touched.description && formik.errors.description)
                        && formik.errors.description}
                    </span>
                        </Form.Label>
                        <Form.Control
                            as='textarea'
                            type='text'
                            placeholder='Enter description'
                            value = {formik.values.description}
                            onChange = {formik.handleChange}
                        >                    
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId='Image' className="form-child">
                        <Form.Label>Image <code>current:{image} </code></Form.Label>
                        <Form.Control
                            type='file'
                            onChange = {(e)=>setImageFile(e.target.files[0])}
                        >
                        </Form.Control>
                    </Form.Group>

                    <Button type='submit' variant='danger' className="form-child">Update</Button>
                </Form>
                
            </FormContainer>
        )}
    </div>    
  )
}

export default ProductEditScreen