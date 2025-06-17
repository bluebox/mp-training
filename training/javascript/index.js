var b=10;
function a(){
    b+=1;
    console.log(b);
}
a()
switch(b){
    case 10:
        console.log("Hey");
        break;
    case 11:
        console.log("hi")
        break;
    default:
        console.log("bye")
        break;
}
var obj={
    "length":25,
    "breadth":30,
    "name":"Pen",
    2:4
}
console.log(obj.length);
console.log(obj[2]);