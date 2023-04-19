import axios from 'axios';

export const createOrder = async(order, setOrder, setSuccess) => { 
    console.log(order)
    try{
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        console.log(userInfo["token"])
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/orders/add/',
            order,
            config
        )
        setOrder(data)
        setSuccess(true)
    }catch(err){
        console.log(err.response.data.detail)
        setSuccess(false)
    }
}


export const deliverOrder = async(order, setLoadingDeliver, setSuccessDeliver, setErrorDeliver) => {
    try {
        setLoadingDeliver(true)
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))

        const config = {
            headers: {
                'Content-type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }

        const { data } = await axios.put(
            `http://127.0.0.1:8000/api/orders/${order.id}/delivered/`,
            {},
            config
        )
        setLoadingDeliver(false)
        setSuccessDeliver(true)


    } catch (error) {
        setErrorDeliver(
            error.response && error.response.data.detail
            ? error.response.data.detail
            : error.message
        )
    }
}

export const payOrder = async(id, setSuccessPay, setErrorPay) => {
    try {
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }

        const { data } = await axios.put(
            `http://127.0.0.1:8000/api/orders/${id}/paid/`,
            {},
            config
        )
        setSuccessPay(true)


    } catch (error) {
        setErrorPay( error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        )
    }
}

export const saveShippingAddress = async(address) => {
    try{
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const { data } = await axios.post(
            `http://127.0.0.1:8000/api/orders/address/add/`,
            address,
            config
        )
        localStorage.setItem('shippingAddress', JSON.stringify(data))
    }catch (error){
        console.log(error)
    }
}