var a;
{
    a = 10;
}
// console.log(a==="10");
console.log(a);

while(1){
    let a = 10;
    if(a<15){
        break;
    }
    a+=1;

}
console.log(a);

let b = 23;
{
    let b = 19;
}
console.log(b);

// var c=10;
// let c=3;

const arr = [1,2,3,4];
// arr=[]; TypeError
console.log(arr[1]);
arr[1] = "MedPlus";
console.log(arr);


var x = 5;
x |= 1;
console.log(x);


// var a ;
// a = a ?? 4;
// console.log(a); not supported in this verson

console.log("vivo" === 'vivo');

a = {
        firstName:"Jhonny",
        lastName:"Test",
        age:10,
    };

console.log(typeof a);


console.log(typeof x);
console.log(typeof b);
console.log(typeof z);
console.log(typeof a["lastName"]);
 

var x = product(6,3);
console.log(x);


function product(a,b){
    return a * b;
}

console.log(product("a",3));

console.log(typeof product());


function print(){
    var c = 10;
    console.log(c);
    
}
// console.log(c);


a = {
    firstName:"Jhonny",
    lastName:"Test",
    age:10,
};

console.log(a.firstName);

a.city = "tadepalligudem";

console.log(a);


var person = {
    firstName: "Jhonny",
    lastName: "Test",
    age: 21,
    address: {
        doorNum:"41-41-41",
        street: "vivekananda street",
        city: "Tadepalligudem",
        fullname: function(){
            return this.firstName + this.lastName;
        },
    }
}
delete person.address.fullname;
console.log(person);

// Object.entries(person).forEach(([key, value]) => {
//   console.log(key, value);
// });

text="";
for(let x in person){
    if(typeof person[x] == "object"){
        for(let y in person[x]){
            text += "\n"+ person[x][y];
        }
    }
    else{
        text += person[x] + " ";
    }
}

console.log(text);

console.log("......................")
console.log(Object.keys(person));
console.log(Object.values(person));
console.log(Object.entries(person));
