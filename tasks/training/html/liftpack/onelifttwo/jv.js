let a= []
for (var i = 0; i < 8; i++) {a[i] = 0;}
let temp = 0;
setInterval(() => {
        for(let i=0;i<8;i++){
            if(a[i]==1){
                fun(i)
                a[i]=0
                break;
            }          
        }     
    }
, 2500);
const fun = (ele) => {
    document.getElementById("lift").style.marginTop=Math.abs(4-ele)*20+'vh'
}
function arr_insert(floor_num){
    a[floor_num]=1
}