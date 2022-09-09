var current=0;
var upd=true;
var uarr=new Array();
var darr=new Array();

const addKey=function(element){

    if(element>current && upd){
        uarr.push(element);
    }else{
        darr.push(element);
    }
    // const data=element.value;
    // const dir=data.charAt(1);
    // const floor=parseInt(data);
    // if(upd && floor>current && dir=='u'){
    //     uarr[i]=floor;
    //     i++;
    // }else if(!upd && floor<current && dir=='d'){
    //     darr[j]=floor;
    //     j++;
    // }
    // uarr.sort();
    // darr.sort();
    // darr.reverse();
    // move(floor,dir);
};

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
 }


async function move(key,dir)
{

    
    // if(dir=='u'){
    //         if(key>current){
    //             for(var i=1;i<=100;i++){
    //                 document.getElementById("elevator").style.top=(6-current)*100-(key-current)*i +"px";
    //                 upd=true;
    //                 await sleep(50);
    //             }
    //             current=key;
    //             console.log(current+"is current now");
    //         }else{
    //             for(var i=1;i<=100;i++){
    //                 document.getElementById("elevator").style.top=(6-current)*100+(current-key)*i +"px";
                    
    //                 upd=true;
    //                 await sleep(50);
    //             }
    //             current=key;
    //             console.log(current+"is current now");
    //         }
    // }else{
    //     document.getElementById("elevator").style.top=600-key*100+"px";
    //     current=key;
    //     upd=false;
    // }
}

function runElevator(){

}
