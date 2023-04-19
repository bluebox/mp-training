import axios from "axios";

export const createProduct = async(product, setSuccessCreate, setErrorCreate) =>{
    const userInfo = JSON.parse(localStorage.getItem('userInfo')) 
    try{
        const config = {
            headers : {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.post(
            `http://127.0.0.1:8000/api/product/create/`,
            product,
            config
        )
        setSuccessCreate(true)
    }catch(err){
        setErrorCreate(
            err.response && err.response.data.detail?
            err.response.data.detail: err.message
        )
    }
}

export const updateProduct = async(product, setLoadingUpdate, setSuccessUpdate, setErrorUpdate) => {
    const userInfo = JSON.parse(localStorage.getItem('userInfo')) 
    try{
        setLoadingUpdate(true)
        const config = {
            headers : {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/product/update/${product.id}/`,
            product,
            config
        )
        setLoadingUpdate(false)
        setSuccessUpdate(true)
    }catch(err){
        setErrorUpdate(
            err.response && err.response.data.detail?
            err.response.data.detail: err.message
        )    
    }
}

export const deleteProduct = async(id, setSuccessDelete, setErrorDelete) =>{
    const userInfo = JSON.parse(localStorage.getItem('userInfo')) 
    try{
        const config = {
            headers : {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.delete(
            `http://127.0.0.1:8000/api/product/delete/${id}`,
            config
        )
        setSuccessDelete(true)
    }catch(err){
        setErrorDelete(
            err.response && err.resposne.data.detail?
            err.response.data.detail: err.message
        )
    }
}