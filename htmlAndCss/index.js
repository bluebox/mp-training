var person={
    name:"Anand",
    age:22
}
for(let x in person){
  console.log(person[x]);
}
const getData = async (a, b) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(a + b); 
    }, 1000); 
  });
};
const array = ["sdlfjsd", "sldjsldf", "sdjflsjf"];

for (let i of array) {
  console.log(i);
}
getData(4,5).then(result=>console.log(result))



const val=new Promise((req,res)=>{
    req("enter a value");
});
val.then(result=>console.log(result)
)
var cnt=0;
const vals=setInterval(()=>{
    console.log("jai shree ram"),
    cnt++;
    if(cnt==2)clearInterval(vals);
},1000)
setTimeout(()=>{
   clearInterval(vals);
},5000)