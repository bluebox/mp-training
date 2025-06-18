  let persons = [
            {id:1,name:"Jai",age:21,company:"Medplus"},
            {id:2,name:"Surya",age:23,company:"NCR"},
            {id:3,name:"Sai",age:25,company:"TCS"},
            {id:4,name:"Ram",age:33,company:"GL"}
        ]

// console.log("1.Get Data")
// console.log("2.Create New Person")
// console.log("3.Update Person Data")
// console.log("4.Delete Person Data")
// console.log("5.Exit")

// let option = prompt("enter your option : ")

// switch(option){
//     case 1:getData()
//     break;
//     case 2:createPerson()
//     break;
//     case 3:updatePersonData()
//     break;
//     case 4:deletePerson()
//     break;
//     case 5:break;
//     default : prompt("Invalid option.Try again...")
// }


console.log("Person's Data")
getData()
createPerson()
updatePersonData()
deletePerson()

console.log("Type of Persons : "+typeof(persons))

function getData(){   
    persons.map(person=>console.log(person.id+"\t\t"+person.name+"\t\t"+person.age+"\t\t"+person.company))
}

function createPerson(){
    let id=prompt("enter the ID to create : ")
    let name=prompt("enter the name : ")
    let age=prompt("enter the age : ")
    let company=prompt("enter the company : ")
    persons.push({id:id,name:name,age:age,company:company})
    console.log("After adding Person")
    getData()
}

function updatePersonData(){
    let id=prompt("enter the ID to upadte : ")
    let index = checkPerson(id)
    if(index!=-1){
        let name=prompt("enter the name : ")
        let age=prompt("enter the age : ")
        let company=prompt("enter the company : ")
        name!=""?persons[index].name=name : persons.name
        age!=""?persons[index].age=age : persons.age
        company!=""?persons[index].company=company : persons.company
        console.log("After updating Person")
        getData()
    }
    else{
        alert("The person with this ID "+id+" not exist..")
    }
}

function deletePerson(){
    let id=prompt("enter the ID to delete : ")
    let index = checkPerson(id)
    console.log("id is : "+id)
    console.log("index is : "+index)
    if(index!=-1){
        persons = persons.filter(person => person.id !== index+1);
        console.log("After Deleting Person")
        getData()
    }
    else{
        alert("The person with this ID "+id+" not exist..")
    }
}

function checkPerson(id){
    for(let i=0;i<persons.length;i++){
        if(persons[i].id == id)
            return i;
    }
    return -1;
}