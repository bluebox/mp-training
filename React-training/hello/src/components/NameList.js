import React from 'react'
import Person from './Person'
function NameList() {
    // const names = ['bruce' , 'Clark', 'Diana']

    const person = [
        {
            id:1,
            name:'Diana',
            skill:'react'
        },
        {
            id:12,
            name:'Bruce',
            skill:'angular'
        },
        {
            id:3,
            name:'Clark',
            skill:'vue'
        }
    ]
    // const nameList = names.map(name =><h2>{name}</h2>)
    const personList = person.map(person => <Person key={person.id} person={person} />)
  return (
    // <div>{nameList}</div>
    /* // <div>{names[0]}</div>
    // <div>{names[1]}</div>
    // <div>{names[2]}</div>
    // </> */
    <div>{personList}</div>
  )
}

export default NameList