let a= []
for (var i = 0; i < 5; i++) {a[i] = 0;}
let a1= []
for (var i = 0; i < 5; i++) {a1[i] = 0;}
let up = true;
let temp = 0;
let up1 = true;
let temp1 = 0;
let pos=1
let pos1=1

setInterval(() => {
    if(up){
        console.log("up");
        for(let i=0;i<5;i++){
            if(a[i]==1){
                fun(i)
                a[i]=0
                pos=i

                break;
            }          
        }
        console.log(a);
        if(a[0]+a[1]+a[2]+a[3]+a[4] == 0)
            up = false;
    }else{
        console.log("down");
        for(let i=4;i>=0;i--){
            if(a[i]==1){
 
                fun(i)
                a[i]=0
                pos=i
                break;
            }        
        }
       if(a[0]+a[1]+a[2]+a[3]+a[4] == 0)
            up = true;
    }
}, 2500);


setInterval(() => {
    if(up1){
        console.log("up1");
        for(let i=0;i<5;i++){
            if(a1[i]==1){
                fun1(i)
                a1[i]=0
                pos1=i

                break;
            }          
        }
        console.log(a1);
        if(a1[0]+a1[1]+a1[2]+a1[3]+a1[4] == 0)
            up1 = false;
    }else{
        console.log("down1");
        for(let i=4;i>=0;i--){
            if(a1[i]==1){
 
                fun1(i)
                a1[i]=0
                pos1=i

                break;
            }        
        }
       if(a1[0]+a1[1]+a1[2]+a1[3]+a1[4] == 0)
            up1 = true;
    }
}, 2500);




const fun = (ele) => {
    document.getElementById("lift").style.marginTop=(4-ele)*20+'vh'

}
const fun1 = (ele) => {
    document.getElementById("lift1").style.marginTop=(4-ele)*20+'vh'

}
function arr_insert(floor_num){
    a[floor_num]=1
}
function arr_insert1(floor_num){
    a1[floor_num]=1
}

function arr_insert2(floor_num){
    if(Math.abs(pos-floor_num)>Math.abs(pos1-floor_num))
    {
    a1[floor_num]=1
    }
    else{
    a[floor_num]=1

    }
}




