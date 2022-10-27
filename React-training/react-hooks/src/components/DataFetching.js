import React,{useState, useEffect} from 'react'
import axios from 'axios'
function DataFetching() {
    const [posts, setPosts] = useState({})
    const [id,setId] = useState(1)
    const [idFromButtonClick,setIdFromButtonClick] = useState(1)

    const handleClick = () =>{
        setIdFromButtonClick(id)
    }
    useEffect(()=>{
        axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`)
        .then(res => {
            console.log(res)
            setPosts(res.data)
            console.log(posts)
        })
        
        .catch(err =>{
            console.log(err)
        })
    },[idFromButtonClick])
  return (
    <div>
        <input type='text' value={id} onChange={e=>{
            setId(e.target.value)
        }} />
        <button type='button' onClick={handleClick}>Fetch Post</button>
        <ul>
            {
                posts.title
            }
            {/* {
                posts.map(post => <li key={post.id}>{post.title}</li>)
            } */}
        </ul>
    </div>
  )
}

export default DataFetching