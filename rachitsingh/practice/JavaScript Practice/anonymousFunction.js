//1. Anonymous function used as callbacks

setTimeout(function() {
    console.log("I am back after 3 seconds.");
}, 3000);


//2. Anonymous function used in Array methods (forEach, map)

let numbers = [1,2,3];
numbers.forEach((num)=> {
    console.log(2*num);
})

