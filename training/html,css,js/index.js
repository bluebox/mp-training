function nextInLine(arr,item)
{
  arr.push(item)
  return arr.shift();
}
var test=[1,2,3,4,5]

console.log("Before : "+JSON.stringify(test))
console.log(nextInLine(test,6))
console.log("After :"+JSON.stringify(test))

var myGlobal=10;
function fun1()
{
  oopsGlobal=5;
}
function fun2()
{
var surya="surya"
  var output =""
  if(typeof myGlobal!= undefined)
  {
    output+="myglobal :"+myGlobal
  }
  if(typeof oopsGlobal!=undefined)
  output +="oopsglobal:"+oopsGlobal;

  console.log(output);
  
}
// if()
fun1()
fun2()


console.log(3==3)

console.log(3===3)

console.log(3===3)

console.log(3===  '3')

console.log(3!==  '3')
var x=3
if(x)
{
    console.log("true")
}

switch(x)
{
    case 1:
        console.log("1");
        break;
    case 2:
        console.log("2");
        break;
    case 3:
        console.log("3");
        break;
    case undefined:
        console.log("undefined");
        break; 
    default:
        console.log("default");
        break;
    
}



var me={
    "name":"camper",
    "age":4,
    "friends":["surya","sai"]
}

console.log(me.name)
console.log(me["age"])

console.log(me.hasOwnProperty("name"))
console.log(me["name"])
var myMusic=[
    {
        "artist":"sai",
        "title":"song1",
        "release_year":2018,
        "formats":["cd","vinyl","cassette"],
        "gold":true
    }
    ,
    {
        "artist":"sai",
        "title":"song2",
        "release_year":2018,
        "formats":["cd","vinyl","cassette"],
        "gold":true
    }
]

var storage={
    "car":{
        "inside":{
            "glovebox":"gun"
        }
    }
}
console.log(storage.car.inside)

console.log(myMusic[0].formats[1])


var collectionCopy=JSON.parse(JSON.stringify(myMusic))
function updateRecords(id,prop,value)
{
    if(value==="")
        {
            delete myMusic[id][prop]
        }
    else
    (
        myMusic[id][prop]=value
    )
    return myMusic 
}

function checkScope()
{
    "use strict";
    var i ="function scope";
    if(true)
    {
        var i="block scope";
        console.log("block scope i",i);
    }
    console.log("function scope i",i);
    return i;
}
checkScope()


const s=[5,7,2]
Object.freeze(s)
s[0]=2;

console.log(s)

var magic=()=>new Date();
console.log(magic())
numberArray=[4,5,6,7,8]
numberArray=numberArray.filter(x=> Number.isInteger(x)).map(x=>x+x)
console.log(numberArray)

var s1=[...numberArray]
console.log(s1)
  