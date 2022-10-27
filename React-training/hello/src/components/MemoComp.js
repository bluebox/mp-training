import React from 'react'

function MemoComp({name}) {
    console.log('Rendring memo')
  return (
    <div>{name}</div>
  )
}

export default React.memo(MemoComp)