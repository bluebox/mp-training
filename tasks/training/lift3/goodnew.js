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





function myMove(f) {






    if (f==0|| f==80 ||f==90){
        if(pos<1200 ) {
        if(f==80 || f==0){
            console.log(pos)
            console.log("lkehfs;gvnemrvb")
            down_array[5]=1
        }}

    } 
    else if (f==1|| f==81 ||f==91){
if(pos!=960){


        if(f==81){
              
            if(pos<960){
                down_array[4]=1
                }
    
               else {
                up_array[4]=1
               }
        }
        if(f==91){

              
            if(pos<960){
                down_array[4]=1
                }
    
               else {
                up_array[4]=1
               }
        }
      
        if(f==1){
            if(direction==8){
                if(pos>960){
                    up_array[4]=1;
        
                }
                else{
                    down_array[4]=1;
                }
            }

            else{
                if(pos<960){
                    down_array[4]=1;
        
                }
                else{
                    up_array[4]=1;
                }
            }
        }

        

    } 
   
          
    }
    else if (f==2|| f==82 ||f==92){
        if(pos!=720){
            console.log("alks;ghfasj;d ")
        if(f==82){
            if(pos<720){
                down_array[3]=1
                }
    
               else {
                up_array[3]=1
               }
        }
        if(f==92){
            

            if(pos<720){
                down_array[3]=1
                }
    
               else {
                up_array[3]=1
               }

        }
        if(f==2){
            if(direction==8){
                if(pos>720){
                    up_array[3]=1;
        
                }
                else{
                    down_array[3]=1;
                }
            }

            else{
                if(pos<720){
                    down_array[3]=1;
        
                }
                else{
                    up_array[3]=1;
                }
            }
        }
    }
         
    }
    else if (f==3|| f==83 ||f==93){
        if(pos!=480){
        if(f==83){
            if(pos<480){
                down_array[2]=1
                }
    
               else {
                up_array[2]=1
               }

        }
        if(f==93){
           

            if(pos<480){
                down_array[2]=1
                }
    
               else {
                up_array[2]=1
               }

        }
        if(f==3){
            if(direction==8){
                if(pos>480){
                    up_array[2]=1;
        
                }
                else{
                    down_array[2]=1;
                }
            }

            else{
                if(pos<480){
                    down_array[2]=1;
        
                }
                else{
                    up_array[2]=1;
                }
            }
        }
    }
          
    }
    else if (f==4 || f==84 ||f==94){
        if(pos!=240){
        if(f==84){
            if(pos<240){
                down_array[1]=1
                }
    
               else {
                up_array[1]=1
               }
        }
        if(f==94){

            if(pos<240){
            down_array[1]=1
            }

           else {
            up_array[1]=1
           }

           if(f==4){

           }

        }
        if(f==4){
            if(direction==8){
                if(pos>240){
                    up_array[1]=1;
        
                }
                else{
                    down_array[1]=1;
                }
            }

            else{
                if(pos<240){
                    down_array[1]=1;
        
                }
                else{
                    up_array[1]=1;
                }
            }
        }

        

    }
    }
    else {
     
        if(pos>239){
            if( f==95 ||  f==5 ){
            
            up_array[0]=1
        }
    }
        
    }


if(movement==0){
 
    if(sum_of_array_up()>0){
        movement=1;
        up();
    }
    if(sum_of_array_down()>0){
        movement=1;
        down();
    }

}












   
  }
           


  setTimeout(() => {
   
}, timer);





function down(){
direction=9;
    setTimeout(() => {
        timer=0;
  let id = null;
  const elem = document.getElementById("animate1");   


pos1=pos+240
console.log(sum_of_array_down());
console.log(up_array)
console.log(down_array)
console.log(pos/240)
  
  clearInterval(id);
  id = setInterval(frame, 5);

  function frame() {
    if (pos == pos1) {
       
        if(down_array[pos/240]==1){
            console.log("first")
            down_array[pos/240]=0;
            if(sum_of_array_down()>0){
                timer=3000;
                down();
            }
        }
       
        else if(down_array[pos/240]==1) 

        {  console.log("second")
  
            down_array[pos/240]=0;
            timer=3000;
            down();
        }


        
    else if(sum_of_array_down()>0){
        console.log("third")
    down()}


    
      if (sum_of_array_down()==0 && sum_of_array_up()==0){
        movement=0;
        direction=0;
      }
      clearInterval(id);
      if (sum_of_array_down()==0 && sum_of_array_up()>0){
        timer=3000;
        up()
      }
    } 
    else {
     
      pos++; 
      elem.style.top = pos + "px"; 
     
    
  }
  }
}, timer);

}




  function up(){
direction=8;
    setTimeout(() => {
        timer=0;
  let id = null;
  const elem = document.getElementById("animate1");   


pos1=pos-240
console.log(sum_of_array_down());
console.log(up_array)
console.log(down_array)
console.log(pos/240)
  
  clearInterval(id);
  id = setInterval(frame, 5);

  function frame() {
    if (pos == pos1) {
       
        if(up_array[pos/240]==1){
            console.log("first")
            up_array[pos/240]=0;
            if(sum_of_array_up()>0){
                timer=3000;
                up();
            }
        }
       
        else if(up_array[pos/240]==1) 

        {  console.log("second")
  
            up_array[pos/240]=0;
            timer=3000;
            up();
        }


        
    else if(sum_of_array_up()>0){
        console.log("third")
    up()}


    
      if (sum_of_array_down()==0 && sum_of_array_up()==0){
        movement=0;
        direction=0;
      }
      clearInterval(id);
      if (sum_of_array_down()>0 && sum_of_array_up()==0){
        timer=3000;
        down();
      }
    } 
    else {
     
      pos--; 
      elem.style.top = pos + "px"; 
     
    
  }
  }
}, timer);


}



function schedule(){

}



function sum_of_array_down()

{
    sum=0
    for (let i = 0; i < 6; i++) {
        sum += down_array[i];
    }
    return sum;
}


function sum_of_array_up()

{
    sum=0
    for (let i = 0; i < 6; i++) {
        sum += up_array[i];
    }
    return sum;
}
