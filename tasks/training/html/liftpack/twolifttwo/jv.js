let a= []
let a1=[]
let pos=0
let pos1=0
for (var i = 0; i < 8; i++) {a[i] = 0;}
for (var i = 0; i < 8; i++) {a1[i] = 0;}
setInterval(() => {
        for(let i=0;i<8;i++){
            if(a[i]==1){
                fun(i)
                a[i]=0
                pos=i;
                break;
            }          
        }     
    }
, 2000);

setInterval(() => {
    for(let i=0;i<8;i++){
        if(a1[i]==1){
            fun1(i)
            a1[i]=0
            pos1=i;
            break;
        }          
    }     
}
, 2000);  

const fun = (ele) => {
    document.getElementById("lift").style.marginTop=Math.abs(4-ele)*20+'vh'
}
const fun1 = (ele) => {
    document.getElementById("lift1").style.marginTop=Math.abs(4-ele)*20+'vh'
}
function arr_insert(floor_num){
    a[floor_num]=1
}
function arr_insert1(floor_num){
    a1[floor_num]=1
}
function arr_insert2(floor_num)
{
    if(Math.abs(pos-floor_num)>Math.abs(pos1-floor_num))
    {
    a1[floor_num]=1
    }
    else{
    a[floor_num]=1

    }
}