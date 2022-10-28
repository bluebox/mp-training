let a= []
let a1=[]
let a2=[]
let pos=0
let pos1=0
let pos2=0
for (var i = 0; i < 8; i++) {a[i] = 0;}
for (var i = 0; i < 8; i++) {a1[i] = 0;}
for (var i = 0; i < 8; i++) {a2[i] = 0;}
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
, 7000);

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
, 5000); 

setInterval(() => {
    for(let i=0;i<8;i++){
        if(a2[i]==1){
            fun2(i)
            a2[i]=0
            pos2=i;
            break;
        }          
    }     
}
, 5000);



const fun = (ele) => {
    document.getElementById("lift").style.marginTop=Math.abs(4-ele)*20+'vh'
    setTimeout(()=>{ document.getElementById("lift").setAttribute('src',"elevator1.gif");
},2000)
setTimeout(()=>{ document.getElementById("lift").setAttribute('src',"lift2.png");
},5000)

   
}
const fun1 = (ele) => {
    document.getElementById("lift1").style.marginTop=Math.abs(4-ele)*20+'vh'
    setTimeout(()=>{ document.getElementById("lift1").setAttribute('src',"elevator1.gif");
},2000)
setTimeout(()=>{ document.getElementById("lift1").setAttribute('src',"lift2.png");
},5000)
}

const fun2 = (ele) => {
    document.getElementById("lift2").style.marginTop=Math.abs(4-ele)*20+'vh'
    setTimeout(()=>{ document.getElementById("lift2").setAttribute('src',"elevator1.gif");
},2000)
setTimeout(()=>{ document.getElementById("lift2").setAttribute('src',"lift2.png");
},5000)


}
function arr_insert(floor_num){
    a[floor_num]=1
}
function arr_insert1(floor_num){
    a1[floor_num]=1
}
function arr_insert3(floor_num){
    a2[floor_num]=1
}
function arr_insert2(floor_num)
{
    if(Math.abs(pos-floor_num)>Math.abs(pos1-floor_num)&&Math.abs(pos2-floor_num)>Math.abs(pos1-floor_num))
    {
    a1[floor_num]=1
    }
    else if(Math.abs(pos-floor_num)>Math.abs(pos2-floor_num)){
    a2[floor_num]=1
    }
    else{
    a[floor_num]=1

    }
}