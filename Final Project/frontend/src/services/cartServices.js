import axios from 'axios';

export const addToCart = async(product_id, qty, setSuccessUpdate) => { 
    try{
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/cart/add/',
            {
                "product": product_id,
                "qty": qty
            },
            config
        )
        setSuccessUpdate(true)
    }catch(err){
        console.log(err.response.data.detail)
    }
    
}

export const updateCart = async(product_id, qty, setSuccess) => { 
    console.log(qty)
    try{
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/cart/update/${product_id}/`,
            {
                "qty": qty
            },
            config
        )
        if(setSuccess != null){
            setSuccess(true)
        }
    }catch(err){
        console.log(err)
    }
}

export const removeFromCart = async(product_id, setSuccess) => { 
    try{
        console.log('hello')
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.delete(
            `http://127.0.0.1:8000/api/cart/delete/${product_id}`,
            config
        )
        setSuccess(true)
    }catch(err){
        console.log(err.response.data.detail)
    }
}