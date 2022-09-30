
floorup=[]
floordown=[]
flag=true
liftcov=0
let ele=document.querySelectorAll(".inlift");
var ele1=document.querySelectorAll(".outfloor");
    // console.log(ele1[10-num])
// console.log(ele[]);
setInterval(() => {
 if(flag)
  {
    floorup.sort()
    liftmove(floorup[0])
    liftcov=floorup.shift()
    // let ele=document.querySelectorAll(".inlift");
    // ele[6-liftcov].style.backgroundColor="white"
    if(floorup.length==0)
    {
        flag=false;
    }
  }
  else
  {
    floordown.sort()
    floordown.reverse()
    liftmove(floordown[0])
    liftcov=floordown.shift()
    // let ele=document.querySelectorAll(".inlift");
    // ele[5-liftcov].style.backgroundColor="white"
    if(floordown.length==0)
    {
        flag=true;
    }
  }

},2500)

function liftmove(num)
{
    document.querySelector(".lift").style.bottom=num*100+"px"
}

function addfloorin(num)
{
    // let ele=document.querySelectorAll(".inlift");
    let ele=document.querySelectorAll(".inlift");
    ele[5-num].style.backgroundColor="green"
    if(num > liftcov)
        floorup.push(num)
    else
       floordown.push(num);

    console.log(floorup)
    console.log(floordown)
}

function addfloorout(num)
{
    var ele=document.querySelectorAll(".outfloor");
    // console.log(ele[5-num][0])
    // ele[10-num*2].style.backgroundColor="green"
    if(num >= liftcov)
    {
    //  ele[11-(num*2 - 1)].style.backgroundColor="green"
        floorup.push(num)
       
    }
    else
    {
        // ele[10-num*2].style.backgroundColor="green"
       floordown.push(num)
    //    ele[5-num].style.backgroundColor="green"
    }

    
       console.log(floorup)
       console.log(floordown)

}
