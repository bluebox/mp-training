const arr = [3,6,4,2,8,9];

console.log("for loop");
for(let i=0;i<arr.length;i++){
    console.log(arr[i]);
}

console.log("For in loop")
for(let i in arr){
    console.log(arr[i]);
    // console.log(i) it print index value
}

const fruits = ["apple","mango","grapes","cherry","banana"];
console.log("For of loop")
for(const fruit of fruits){
    console.log(fruit);
}