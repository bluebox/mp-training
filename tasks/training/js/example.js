// num=Number(1);
// console.log('number is',num);

// const introducer =(name,shirt) =>{

//     person={
//         name:name,
//         shirt:shirt,
//         game:function (){

//             return this.shirt
//         }
//     }
//     return person.game()

// }
// document.write(introducer('adff','white'))
// const arr=[1,2,3]
// for(const i of arr)
// {
//   document.write(i)
//   console.log(i)
// }


// const ele=document.createElement('p')
// const eletag=document.createTextNode("this is now")
// ele.appendChild(eletag)
// console.log(ele);
// let ele1=document.querySelector("body")
// ele1.appendChild(ele)
// console.log(ele1)

// function myFunction()
// {
//     var x=document.getElementById("numb")
//     var text ="";
//     if(isNaN(x) || x<1 || x>10)
//     {
//         text = "Input not valid";
//     } 
//     else {
//       text = "Input OK";
//     }
//     document.getElementById("demo").innerHTML = text;
    
// }

// function freq(phrase)
// {
//     let fr={}
//   for(const letr of phrase)
//   {
//     if(letr in fr)
//       fr[letr]=fr[letr]+1
//     else 
//       fr[letr]=1
//   }

//     return fr
// }
// fq=freq("afhjkljsjsjdk")
// console.log(fq)

// let res=[1,2,3,4].map(number => number*2).join('$')
// console.log(res);

// const filter=(num,cond) =>{
//     let res=[]
//     for (const numr of num)
//     {
//         if(numr>cond)
//          res.push(numr)
//     }
//     return res
// }
// console.log(filter([1,3,4,5],3))
//  nums=[1,34,45,6]

// console.log(nums.filter(num =>num > 3))
// console.log(nums.reduce((a,b) => a+b))

let reddiv=document.getElementById("red");
reddiv.onclick =() => console.log("red")
let yediv=document.getElementById("yellow");
yediv.onclick =() => console.log("yellow")
let grediv=document.getElementById("green");
grediv.onclick =() => console.log("green")

const sqs=document.querySelectorAll(".colorsq")
sqs.forEach(sq => console.log(sq.value))

const timesclicked={'red':0,'yellow':0,'green':0}
sqs.forEach(sq => {
    sq.onclick= () =>
    {
        timesclicked[sq.value]+=1
        sq.innerText=timesclicked[sq.value]
    }

})
function clearScores()
{
    timesclicked.red=0
    timesclicked.yellow=0
    timesclicked.green=0
    sqs.forEach(sq => {
      sq.innerText=" "
    } )
}
const but=document.getElementById("clear-game")
but.onclick=() => clearScores()




