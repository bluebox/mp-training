let posone = 0;
let up_array_one=[0,0,0,0,0,0,0,0,0,0,0];
let down_array_one=[0,0,0,0,0,0,0,0,0,0,0];
let directionone=8;
let timer=0;
let d = new Date();
let starttime = d.getTime();
let m_up=0;
let m_down=0;
let movementone=0;

let up_array_two=[0,0,0,0,0,0,0,0,0,0,0];
let down_array_two=[0,0,0,0,0,0,0,0,0,0,0];
let up_array_three=[0,0,0,0,0,0,0,0,0,0,0];
let down_array_three=[0,0,0,0,0,0,0,0,0,0,0];



function myMoveone(floor){
    console.log("hiii")
    if (directionone==8){
        if(posone<(floor+1)*100){
            down_array_one[floor]=1;
        }
        else{
           up_array_one[floor]=1;
        }
    }
    else{
        if(posone>floor*100){
            up_array_one[floor]=1;
        }
        else{
            down_array_one[floor]=1;
        }

    }
    if(movementone==0){
        movementone=1;
        console.log("in mone111")
        if(sum_of_array(down_array_one)!=0){
            console.log("in mone")
            down()
        }
        else if(sum_of_array(up_array_one)!=0){
            down()
        }
        
    }
    console.log(down_array_one)
    console.log(sum_of_array(down_array_one))
    
    
}

function sum_of_array(array)

{
    sum=0
    for (let i = 0; i < 12; i++) {
        sum +=array[i];
    }
    return sum;
}



function down(){
   console.log("in down" );
   console.log(down_array_one)
   console.log(sum_of_array(down_array_one))
    setTimeout(() => {
        timer=0;
  let id = null;
  const elem = document.getElementById("animate1");   


posmove=posone+100

  
  clearInterval(id);
  id = setInterval(frame, 5);

  function frame() {
   
    if (posone == posmove) {
       console.log("yes")
       if(down_array_one[(posone/100)-1]==1){
       
        down_array_one[(posone/100)-1]=0;
        clearInterval(id);
       }
       else if(sum_of_array(down_array_one)!=0){
        clearInterval(id);
        down()
       }
       
    } 
    else {
     
      posone++; 
      elem.style.top = posone + "px"; 
     
    
  }
  }
}, timer);

}


