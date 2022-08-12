let pos = 0;
let up_array=[0,0,0,0,0,0];
let down_array=[0,0,0,0,0,0]
let timer=0;
let d = new Date();
let starttime = d.getTime();
let m_up=0;
let m_down=0;
let movement=0;
let direction=2;
let pos_array=[239,240,480,720,960,1200]

function myMove(f) {
direction_decider(f);

if(movement==0){
 
    if(sum_of_array(up_array)>0){
        console.log("down");
        movement=1;
        md(8,up_array,down_array);
    }
    if(sum_of_array(down_array)>0){
        movement=1;
        console.log("down");
        md(9,down_array,up_array);
    }
} 
  }



  function md(d,down_array,up_array){
    direction=d;
    console.log(d)
        setTimeout(() => {
            timer=0;
      let id = null;
      const elem = document.getElementById("animate");   
    
    if(direction==9){
    pos1=pos+240;
    }
    else  if(direction==8){
    pos1=pos-240;
    }
     
     id =  setInterval(frame, 5);
    
      function frame() {
       
        if (pos == pos1) {
           
            if(down_array[pos/240]==1){
                console.log("first")
                down_array[pos/240]=0;
                if(sum_of_array(down_array)>0){
                    timer=3000;
                     md(direction,down_array,up_array);
                }
            }
           
            else if(down_array[pos/240]==1) 
    
            {  console.log("second")
      
                down_array[pos/240]=0;
                timer=3000;
                 md(direction,down_array,up_array);;
            }
    
    
            
        else if(sum_of_array(down_array)>0){
            console.log("third");
         md(direction,down_array,up_array);
        }
    
    
          if (sum_of_array(down_array)==0 && sum_of_array(up_array)==0){
            movement=0;
            direction=0;
           clearInterval(id);
           
            console.log(sum_of_array(down_array),sum_of_array(up_array),"fourth");
           
        }

          
          if (sum_of_array(down_array)==0 && sum_of_array(up_array)>0){
            console.log("fifth")
            timer=3000;

          if(direction==9){
          md(8,up_array,down_array);}
          else if (direction==9){
          
          md(9,down_array,up_array);
          }
          }

      

          
        } 
        else {
         
            if(direction==9){
            pos++;}
            else  if(direction==8){
            pos--;}
          elem.style.top = pos + "px"; 
         
        
      }
      }
    }, timer);
    
    }



function sum_of_array(arr)

{ console.log("in sum");
    sum=0
    for (let i = 0; i < 6; i++) {
        sum += arr[i];
    }
    return sum;
}





function direction_decider( request_floor){
    console.log("dd");
floor_pos=pos_array[5-request_floor%10];



if (request_floor==0|| request_floor==80 ||request_floor==90){
    if(pos<1200 ) {
    if(request_floor==80 || request_floor==0){
        console.log(pos)
        console.log("lkehfs;gvnemrvb")
        down_array[5]=1
    }}

} 



else if(request_floor==95|| request_floor==5) {
     
    if(pos>239){
        if( request_floor==95 ||  request_floor==5 ){
        
        up_array[0]=1
    }
}    
}


  else  if(pos!=floor_pos){
        if(request_floor<90 && request_floor >80){
            if(pos<floor_pos){
                down_array[5-request_floor%10]=1
                }
    
               else {
                up_array[5-request_floor%10]=1
               }
        }
        if(request_floor >90){

            if(pos<floor_pos){
            down_array[5-request_floor%10]=1
            }

           else {
            up_array[5-request_floor%10]=1
           }

           if(request_floor==request_floor){

           }

        }
        if(request_floor <10){
            if(direction==8){
                if(pos>floor_pos){
                    up_array[5-request_floor%10]=1;
        
                }
                else{
                    down_array[5-request_floor%10]=1;
                }
            }

            else{
                if(pos<240){
                    down_array[5-request_floor%10]=1;
        
                }
                else{
                    up_array[5-request_floor%10]=1;
                }
            }
        }
    }


}