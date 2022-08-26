import React from 'react'
import { UserContext, ChannelContext } from '../App'
import ComponentF from './ComponentF'
function ComponentE() {
  return (
    <div>
        <ComponentF />
        {/* <UserContext.Consumer>
            {
                user => {
                    return (
                        <ChannelContext.Consumer>
                            {
                                channel =>{
                                    return <div>User context value {user} {channel}</div>
                                }
                         
                            }
                         </ChannelContext.Consumer>
                    )
                }
            }
        </UserContext.Consumer> */}
    </div>
  )
}

export default ComponentE