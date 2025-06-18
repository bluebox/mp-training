const person = {
    name:"Renu",
    age:21,
    company:"Medplus",
    id:25,
    gender : "male",
    greet:function(){
        if(this.gender == "male"){
            return "Welcom Mr."+this.name;
        }
        else{
            return "Welcome Mrs."+this.name;
        }
    }
};

console.log("Person Object")
console.log(person);
console.log("Person Details")
console.log(person.greet());
console.log("Name : "+person.name)
console.log("ID : "+person.id)
console.log("Age : "+person.age)
console.log("Gender : "+person.gender)
console.log("Company : "+person.company)

// Checking if a Property Exists

console.log("salary in person : "+("salary" in person));
console.log("person has name property : "+person.hasOwnProperty("name"));


// Object 2

function hello(){
    return "Hello, "
}

const car = new Object();

car.model="XUV 700"
car.name="Mahendra"
car.price=800000
car.owner="Ram"
car.greet=hello

console.log("Car Details")
// console.log(car)
console.log(car.greet()+car.owner+"!")
console.log("You are purchased "+car.name+" "+car.model+" for Rs."+car.price)


