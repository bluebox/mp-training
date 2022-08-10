up_arr=[];
down_arr=[];


up=true;



function move(num){
   document.querySelector(".lift").style.bottom =152*num +"px";
}

let temp=0;
setInterval(function(){
    if(up){
        // // console.log("up");
        // let set_up = new Set(up_arr)
        // up_arr = Array.from(set_up)
        
        up_arr.sort()
        move(up_arr[0])

        temp=up_arr.shift();
        if(up_arr.length==0)
        {
            up=false;
        }
    }
    else{
        // let set_down = new Set(up_arr)
        // down_arr = Array.from(set_down)

        down_arr.sort();
        down_arr.reverse();
        move(down_arr[0]);

        temp=down_arr.shift();
        if(down_arr.length==0);{
            up=true;
        }

    }
    console.log("up",up_arr);
    console.log("down",down_arr);
    console.log("temp" ,temp)
},3000)

function addfloor_up(floor){
    if(floor>=temp){
        up_arr.push(floor)
    }
    else{
        down_arr.push(floor)
    }
   
}
function addfloor_down(floor){
    if(floor>=temp){
        down_arr.push(floor)
    }
    else{
        up_arr.push(floor)
    }
}

function add_floor_lift(floor){
    if(floor > temp){
        up_arr.push(floor);
    }
    else{
        down_arr.push(floor);
    }
}
  