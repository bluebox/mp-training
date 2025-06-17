// var b=10;
// function a(){
//     b+=1;
//     console.log(b);
// }
// a()
// switch(b){
//     case 10:
//         console.log("Hey");
//         break;
//     case 11:
//         console.log("hi")
//         break;
//     default:
//         console.log("bye")
//         break;
// }
// var obj={
//     "length":25,
//     "breadth":30,
//     "name":"Pen",
//     2:4
// }

// console.log(obj.hasOwnProperty("length"))


// data={
//     "2051":{
//         Name:"Kanishka",
//         age:22,
//         height:"5'12",
//         greet:function(){
//             console.log("Hey there");
//         }

//     }
// }
// console.log(data["2051"]);
// console.log(JSON.stringify(data["2051"]));
// console.log(JSON.parse(JSON.stringify(data["2051"])));
// var myArray=[];
// for(var i=0;i<10;i++){
//     myArray.push(i);
// }
// console.log(JSON.stringify(myArray));
// console.log(myArray)

const data=[
    {
        Name:"Kanishka",
        Age:22,
        ID:1097
    },
    {
        Name:"Uday",
        Age:21,
        ID:1197
    }
];

function ProfileLookUp(ID_find){
    for(var i=0;i<data.length;i++){
        if (data[i].ID==ID_find){
            return true;
        }
    }
    return false;
}
// console.log(ProfileLookUp(1196));

var n=Math.floor((Math.random()*100));
function randomRange(min_val, max_val){
    return Math.floor(Math.random()*(max_val-min_val+1))+min_val
}
console.log(data[0].Name);
data[0].Name="Hari";
console.log(data[0].Name);
Object.freeze(data[0]);
data[0].Name="shaik";
console.log(data[0].Name)
a=[1,-1,6,7,-2,-5,8]
console.log(a.filter(num => num>0 && Number.isInteger(num)).map(num => num*num))
function rest_test(...args){
    for(var i=0;i<args.length;i++){
        console.log(args[i])
    }
}
rest_test(1,2,"kanishka",2.78,);

//Template Literals
console.log(`Hi guys ${data[0].Name}"`);