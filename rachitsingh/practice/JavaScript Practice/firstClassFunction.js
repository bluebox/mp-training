var b = function (parameter1){
    console.log(parameter1);
}

function xyz(){}
//Function xyz() is passed to b() as an argument;
b(xyz); 

//1. Passing 1 function as an argument into another function.
function greetUser(greetingFunction, user){
    console.log("Welcome !");
    greetingFunction(user);
}

function sayHello(user){
    console.log("Hello, " + user);
}

function sayNamaskaram(user){
    console.log("Namaskaram, " + user);
}

greetUser(sayHello, "Rachit");
greetUser(sayNamaskaram, "Rachit");

//2. function returned from another function

function multiplier(factor){
    return function(number){
        return number * factor;
    }
}

const double = multiplier(2);
const triple = multiplier(3);

console.log(double(54));
console.log(triple(54));