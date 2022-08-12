let array=[0,0,0,0,0];
function fun(floor){
    array[floor]=1;
    console.log(array);
    color(0);
   
    
}

function color(num){
    let lift=document.getElementById("lift");
    // if(lift.style.marginTop=="600px"){
    //     num=5;
    // }
    if(array[num]==1){
        console.log("hello");
        let floor=4-num;
        lift.style.marginTop=floor*160+"px";
        console.log(lift.style.marginTop);
        array[num]=0;
    }
    setTimeout(color,3000,num+1);
    num=num-1;
    
}
function up(num){
array[num]=1;
}
  