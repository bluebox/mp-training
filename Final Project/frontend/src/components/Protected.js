import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate } from 'react-router-dom'
function Protected({ children }) {
  const {userInfo} = useSelector(state => state.userLogin)

  if (userInfo && userInfo.username) {
    return children
  }
  return <Navigate to="/login" replace />
}
export default Protected