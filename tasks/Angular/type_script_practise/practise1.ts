// function sayHelloWorld(): void {
//      console.log("\n");
//      console.log("*** Hello World from Type Script code. ***");
//      console.log("\n");
//    }
   
//    sayHelloWorld(); 



//  let age: number = 12;
//  alert(age)
//  age=15
//  alert(age)
// //  age= "hello"; we cant change the type explictly
//  alert(age)

//  let name1: String | number;
//  name1 = "hello"
//  alert(name1)
//  name1 = 9
//  alert(name1)


 //array
//  let names: number[];
//  names=[1,2,3,4]
//  alert(names)

 // 'any' for all data types 



 //struct of object
//  let person:{
//   name:String;
//   age: number;
//  };

//  person={
//   name:"venky",
//   age:22
//  }
//  alert(person)
//  alert(person.name)
//  alert(person.age)



//Type inference = ts automatically takest the type of 
//varable at the time of initialization of value

// var course = 123

// course = 666
// alert(course)

//type defining
//  type person ={
//   name:String;
//   age:number;
//  }
//  let Person :person[];
//  Person = [{
//   name:"hello",
//   age:33
//  },{
//   name:"hello",
//   age:33
//  }]
//  alert(Person[0].name)



// Function and types


// function add(a:number, b:string):String{
//   return a+b;
// }
// alert(add(3, "hello"))
 

 //classes and methods
 class student{
  firstName :String;
  lastName :String;
  age :number;
  course :String[];
  
  constructor(firstName :String, lastName :String, age:number, course :String[]){
    this.firstName =firstName ;
    this.lastName =firstName ;
    this.age= age;
    this.course =course;
  }
}

const stu = new student('venky','galibe',22,['py','dj']);
alert(stu.firstName)

//interface same as java has methods without def and variables without values

// this methods are redeclared in object

// implements for interface implementattion the 







