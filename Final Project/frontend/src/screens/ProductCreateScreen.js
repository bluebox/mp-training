import React, {useState, useEffect} from 'react'
import {Link, useNavigate} from 'react-router-dom'
import {Form, Button, Row, Col} from 'react-bootstrap'
import {useDispatch} from 'react-redux'
import FormContainer from '../components/FormContainer'
import {createProduct} from '../services/productServices'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function ProductCreateScreen() {

    const [imageFile, setImageFile] = useState({})

    const navigate = useNavigate()

    const dispatch = useDispatch()
    const [successCreate, setSuccessCreate] = useState(false)
    const [errorCreate, setErrorCreate] = useState('')
    const [imageValidation, setImageValidation] = useState('')

    const formik = useFormik({
        initialValues:{
            name: '',
            brand: '',
            price: '',
            category: '',
            description: '',
            countInStock: 0,
        },
        
        validationSchema: Yup.object({
            name: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .max(255, "Name should be less than 255 characters.")
              .required("Name is required"),
            price: Yup.number()
              .moreThan(0, "Price must be 3 characters or more.")
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

          onSubmit:values=>{
            console.log(imageFile)
            if(imageFile == {}){

                setImageValidation('Image required')
            }else{
                createProduct({
                    'name':values.name, 'price': values.price, 'category': values.category, 'brand': values.brand, 'description': values.description, 'countInStock': values.countInStock, 'image':imageFile
                }, setSuccessCreate, setErrorCreate)
            }
            
        },
 
    })
    

    
    useEffect(() =>{
        if(successCreate){
            navigate(-1)
        }
    }, [dispatch, navigate, successCreate])


    

  return (
    <div>
        <Link to='/admin/productlist'>
            Go Back
        </Link>
        <FormContainer>
            <h1>Add Product</h1>

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
                        <Form.Label>Image 
                            <span className='d-block form-error'>
                                {imageValidation && imageValidation}
                            </span>
                        </Form.Label>
                        <Form.Control
                            type='file'
                            onChange = {(e)=>setImageFile(e.target.files[0])}
                        >
                        </Form.Control>
                    </Form.Group>

                    <Button type='submit' variant='danger' className="form-child">Add Product</Button>
                </Form>
            
        </FormContainer>
    </div>
               
  )
}

export default ProductCreateScreen