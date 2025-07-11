import React from 'react'
import { Outlet } from 'react-router-dom'

const RootLayout = () => {
  return (
    <div>
       <div>
            <div style={{minHeight:"100vh"}}>
                <Outlet/>
            </div>

        </div>
    </div>
  )
}

export default RootLayout
