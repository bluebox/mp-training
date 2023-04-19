import axios from "axios";

export const registerUser = async(username,email, password, first_name, last_name, setLoading, setSuccess, setError) =>{
    setLoading(true)
    const userInfo = JSON.parse(localStorage.getItem('userInfo'))
    
    try{
        const config = {
            headers: {
                'Content-Type': 'application/json'
            }
        }
        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/user/register/',
            {
                'username': username, 
                'email': email, 
                'password': password, 
                'first_name': first_name,
                'last_name': last_name
            },
            config
        )
        setLoading(false)
        setSuccess(true)
    }
    catch(err){
        setLoading(false)
        setError(
            err.response && err.response.data.detail?
            err.response.data.detail: err.message
        )
    }
}

export const deleteUser = async(id, setSuccessDelete, setErrorDelete) =>{
    const userInfo = JSON.parse(localStorage.getItem('userInfo'))

    try{
      const config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${userInfo.token}`
        }
      }
      
      const {data} = await axios.delete(
          `http://127.0.0.1:8000/api/users/delete/${id}/`,
          config
      )
      setSuccessDelete(true)
    }catch(err){
      setErrorDelete(
        err.response && err.response.data.detail?
        err.response.data.detail: err.message
      )
    }
}

export const updateUser = async(user, setLoadingUpdate, setSuccessUpdate, setErrorUpdate)=>{
    setLoadingUpdate(true)
    const userInfo = JSON.parse(localStorage.getItem('userInfo'))
    try{
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/users/update/${user.id}/`,
            user,
            config
        )
        setLoadingUpdate(false)
        setSuccessUpdate(true)
    }catch(err){
        setErrorUpdate(
            err.response || err.response.data.detail?
            err.response.data.detail: err.message
        )
    }
}


export const updateProfile = async(user_detail, setUsername, setSuccess, setErrorUpdate) =>{
    try{
        const userInfo = JSON.parse(localStorage.getItem('userInfo'))
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.put(
            'http://127.0.0.1:8000/api/user/profile/update/',
            user_detail,
            config
        )
        setUsername(data.first_name + " " + data.last_name)
        setSuccess(true)
    }catch(err){
        setErrorUpdate(
            err.response && err.response.data.detail?
            err.response.data.detail: err.message
        )
        setSuccess(false)
    }
}
