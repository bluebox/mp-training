
var up_array = [0,0,0,0,0];
 var down_array = [0,0,0,0,0];


function onclick1(i){
    up_array[i]=1;

}

setInterval(()=>{if ((up_array[0]+up_array[1]+up_array[2]+up_array[3]+up_array[4])!=0){
    setTimeout(elevator,100,up_array,0);
}
},2000);
function elevator(up_array,i){
    let ele = document.querySelector(".ele");

    if(up_array[i]==1){
        ele.style.marginTop=(4-i)*300 + "px";
        up_array[i]=0;
        i=i+1;
        var elev= setInterval(elevator,2000,up_array,i);
        console.log(i+" "+"floor");

    }
    else{
        i=i+1;
        var elev= setInterval(elevator,2000,up_array,i);
        console.log(i+" "+"floor else");
    }
    if (i>4){
        clearInterval(elev);
    }
}

function down_elevator(down_array,j){
    let ele = document.querySelector(".ele");

    if(up_array[j]==1){
        ele.style.marginTop=(4-i)*300 + "px";
        up_array[j]=0;
        j=j-1;
        var elev= setInterval(elevator,2000,up_array,j);
        console.log(j+" "+"floor");

    }
    else{
        j=j+1;
        var elev= setInterval(elevator,2000,up_array,j);
        console.log(j+" "+"floor else");
    }
    if (j<0){
        clearInterval(elev);
    }
}

