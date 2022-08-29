import React from 'react'

function Person({person}) {
  return (
    <div><h2>Hi i am {person.name}. my id is {person.id}. my skill is {person.skill}</h2></div>
  )
}

export default Person