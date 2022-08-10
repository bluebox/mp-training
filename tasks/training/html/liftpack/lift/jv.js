let a= []
for (var i = 0; i < 5; i++) {a[i] = 0;}
let up = true;
let temp = 0;
setInterval(() => {
    if(up){
        console.log("up");
        for(let i=0;i<5;i++){
            if(a[i]==1){
                fun(i)
                a[i]=0
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
                break;
            }        
        }
       if(a[0]+a[1]+a[2]+a[3]+a[4] == 0)
            up = true;
    }
}, 2500);
const fun = (ele) => {
    document.getElementById("lift").style.marginTop=(4-ele)*20+'vh'
}
function arr_insert(floor_num){
    a[floor_num]=1
}



