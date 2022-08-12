
// var current=0;
// var up_array = [];
// var set1 = new Set();
function sleep(m){
    return new Promise(resolve => setTimeout(resolve,m));
}

// function display(e){
//     set1.add(e);
    
//     up_array = Array.from(set1);
//     up_array.sort();
//     console.log(up_array)
//     if(up_array.length==1){
//         execute(e);
//     }
// }


// function execute(e){
//    while(up_array.length>0){
//     var i = up_array[0];
//     up_array.sort();
//     console.log(up_array);
//     if(e==0){
//         document.getElementById('lift1').style.marginTop=600 - (e*100)+"px";
//         document.getElementById('lift1').innerText="G";
//     }
//     else{
       
//         document.getElementById('lift1').style.marginTop=600 - (e*100)+"px";
//         document.getElementById('lift1').innerText=e;
//     }
//     console.log(up_array.splice(0,1));
    
//    }
// }














































// // async function display(e){
// //     set1.add(e);
// //     up_array = Array.from(set1)

// // //     up_array.push(e);
// //    await sleep(5000);
// //     console.log(up_array); 
// // }
  
//   function display(e){
   
//     set1.add(e);
//     up_array = Array.from(set1);


// //     up_array.push(e);
  
//     // up_array.sort();
//    if(up_array.length==1){
//     execute();
//    }
// }


//     // console.log(up_array)


//   async function execute(){  

//   while(up_array.length>0){
//     up_array.sort();
//     var x =up_array[0];
   
//     // console.log(up_array)
//     // var index = up_array.indexOf(x);
//     // console.log(index)
//     if(up_array[0]==   0){
//         //     document.getElementById('lift2').style.marginTop=600 - (e*100)+"px";
//         // document.getElementById('lift2').style.transitionDuration=5+"s";
//         // document.getElementById('lift2').innerText="G";
//         document.getElementById('lift1').style.marginTop=600 - (x*100)+"px";
//         document.getElementById('lift1').style.transitionDuration=6+"s";
//         document.getElementById('lift1').innerText="G";
      
//         up_array.splice(0,1);
//         set1.delete(x);
        
        
        
        
//         }
//         else{
//             for(var i=current;i<=x;i++){
//                 up_array.sort();
//                 if(up_array[0]>=i){
//                     await sleep(4000);
//                     up_array.splice(0,1);
//                     set1.delete(i); 
//                 document.getElementById('lift1').style.marginTop=600 - (i*100)+"px";
//                 document.getElementById('lift1').style.transitionDuration=6+"s";
//                 document.getElementById('lift1').innerText=i;
//                 }
//                 // document.getElementById('lift1').style.marginTop=600 - (i*100)+"px";
//                 // document.getElementById('lift1').style.transitionDuration=6+"s";
//                 // document.getElementById('lift1').innerText=i;
                                         
                      
//                     console.log(up_array) 
//                 //     up_array.splice(0,1);
//                 // set1.delete(i);    
                
                
        
//             }
//         // document.getElementById('lift1').style.marginTop=600 - (x*100)+"px";
//         // document.getElementById('lift1').style.transitionDuration=8+"s";
//         // document.getElementById('lift1').innerText=x;
//         // // await sleep(4000);
//         // up_array.splice(0,1);
//         // set1.delete(x);
//         // await sleep(3000)
        
//         }
//         // console.log(up_array.length)
       

//   }




   
    
// }
// // function display1(e){
// //     if(e>0){

// //     document.getElementById('lift2').style.marginTop=600 - (e*100)+"px";
// //     document.getElementById('lift2').style.transitionDuration=5+"s";
// //     document.getElementById('lift2').innerText=e;
// //     }
   
// // }





// srikar project


let a= []
for (var i = 0; i < 5; i++) {a[i] = 0;}
let a1= []
for (var i = 0; i < 5; i++) {a1[i] = 0;}
let a2= []
for (var i = 0; i < 5; i++) {a1[i] = 0;}
let up = true;
let temp = 0;
let up1 = true;
let temp1 = 0;
let up2 = true;
let temp2 = 0;
let pos=1
let pos1=1

setInterval(() => {
    if(up){
        console.log("up");
        for(let i=0;i<7;i++){
            if(a[i]==1){
                fun(i,1)
                a[i]=0
                pos=i

                break;
            }          
        }
        console.log(a);
        if(a[0]+a[1]+a[2]+a[3]+a[4] +a[5] + a[6] == 0)
            up = false;
    }else{
        console.log("down");
        for(let i=6;i>=0;i--){
            if(a[i]==1){
 
                fun(i,0)
                a[i]=0
                pos=i
                break;
            }        
        }
       if(a[0]+a[1]+a[2]+a[3]+a[4] +a[5] + a[6] == 0)
            up = true;
    }
}, 6000);


setInterval(() => {
    if(up1){
        console.log("up1");
        for(let i=0;i<7;i++){
            if(a1[i]==1){
                fun1(i,1)
                a1[i]=0
                pos1=i

                break;
            }          
        }
        console.log(a1);
        if(a1[0]+a1[1]+a1[2]+a1[3]+a1[4]+a1[5] + a1[6]  == 0)
            up1 = false;
    }else{
        console.log("down1");
        for(let i=6;i>=0;i--){
            if(a1[i]==1){
 
                fun1(i,0)
                a1[i]=0
                pos1=i

                break;
            }        
        }
       if(a1[0]+a1[1]+a1[2]+a1[3]+a1[4]+a1[5] + a1[6]  == 0)
            up1 = true;
    }
}, 6000);

setInterval(() => {
    if(up2){
        console.log("up2");
        for(let i=0;i<7;i++){
            if(a2[i]==1){
                fun2(i,1)
                a2[i]=0
                pos2=i

                break;
            }          
        }
        console.log(a2);
        if(a2[0]+a2[1]+a2[2]+a2[3]+a2[4] +a2[5] + a2[6] == 0)
            up2 = false;
    }else{
        console.log("down2");
        for(let i=6;i>=0;i--){
            if(a2[i]==1){
 
                fun2(i,0)
                a2[i]=0
                pos2=i
                break;
            }        
        }
       if(a2[0]+a2[1]+a2[2]+a2[3]+a2[4] +a2[5] + a2[6] == 0)
            up2 = true;
    }
}, 6000);

 async function fun(ele,x){
    
    
    document.getElementById('lift1').style.marginTop=600 - (ele*100)+"px";
    document.getElementById('lift1').innerText=ele;
    document.getElementById('floor_no').innerText=ele;
    if(x==1){
                   
        document.getElementById('position').innerHTML= "^";
    }
    else if(x==0){
    document.getElementById('position').innerText="V";
    }
    // await sleep(10000);
}
async function fun1(e,x){
    if(e==0){
                document.getElementById('lift2').style.marginTop=600 - (e*100)+"px";
                document.getElementById('lift2').innerText="G";
                document.getElementById('floor_no1').innerText=e;
                if(x==1){
                   
                    document.getElementById('position1').innerHTML= "^";
                }
                else if(x==0){
                document.getElementById('position1').innerText="V";
                }
            }
            else{
               
                document.getElementById('lift2').style.marginTop=600 - (e*100)+"px";
                document.getElementById('lift2').innerText=e;
                document.getElementById('floor_no1').innerText=e;
                if(x==1){
                   
                    document.getElementById('position1').innerHTML= "^";
                }
                else if(x==0){
                document.getElementById('position1').innerText="V";
                }
            }
    // document.getElementById('lift1').style.marginTop=600 - (ele*100)+"px";
    // document.getElementById('lift1').innerText=ele;
    // await sleep(10000);
}
async function fun2(e,x){
    if(e==0){
                document.getElementById('lift1').style.marginTop=600 - (e*100)+"px";
                document.getElementById('lift1').innerText="G";
                document.getElementById('floor_no').innerText=e;
                if(x==1){
                   
                    document.getElementById('position').innerHTML= "^";
                }
                else if(x==0){
                document.getElementById('position').innerText="V";
                }
            }
            else{
               
                document.getElementById('lift1').style.marginTop=600 - (e*100)+"px";
                document.getElementById('lift1').innerText=e;
                document.getElementById('floor_no').innerText=e;
                if(x==1){
                   
                    document.getElementById('position').innerHTML= "^";
                }
                else if(x==0){
                document.getElementById('position').innerText="V";
                }
            }
    // document.getElementById('lift1').style.marginTop=600 - (ele*100)+"px";
    // document.getElementById('lift1').innerText=ele;
    // await sleep(10000);
}
function display(floor_num) {
    a[floor_num] = 1
}
function display2(floor_num) {
    a1[floor_num] = 1
}

function display1(floor_num) {
    a2[floor_num] = 1
}


// function display1(floor_num){
//     if(Math.abs(pos-floor_num)>Math.abs(pos1-floor_num))
//     {
//     a1[floor_num]=1
//     }
//     else{
//     a[floor_num]=1

//     }
// }


