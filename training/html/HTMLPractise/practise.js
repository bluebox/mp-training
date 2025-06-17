let num=10;
{
    num=num-1;
}
function counter(){
    let num=1000;
   return function dec(){
        num=num-1;
        console.log(num)
    }
}

const dec= counter();
dec();
dec();
dec();
dec();
dec();
console.log(num)

let arr=[23,43,23,12,45,7,87,897,65,645,45,342,32,3767,88];
var i=0;
arr.sort((a,b)=> a-b)
for (let ele of arr)
{
    console.log(ele);
}
