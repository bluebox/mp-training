import React from 'react'
import { useParams } from 'react-router-dom'

const DynamicRoute = () => {
    const{id}=useParams()
  return (
    <div className='flex justify-center items-center min-h-screen'>
        <h1 className='text-2xl font-bold'>this is the query param you given {id}</h1>
    </div>
  )
}

export default DynamicRoute
