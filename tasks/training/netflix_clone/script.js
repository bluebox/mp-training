var toggles=new Array(false,false,false,false,false);
const show_ans=function(element,index){
    if(!toggles[index-1]){
        document.getElementById("ans"+index).style.display="block";
        document.getElementById("s"+index).innerHTML="x";

        toggles[index-1]=true;
    }
    else{
        document.getElementById("ans"+index).style.display="none";
        document.getElementById("s"+index).innerHTML="+";
        toggles[index-1]=false;
    }
}

const openSign=()=>{window.open("signin.html","_self");}