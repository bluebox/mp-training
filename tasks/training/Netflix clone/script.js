
var myIndex=0;
carousel();


function carousel(){
    var i;
    var x=document.getElementsByClassName("imageSlides");
    for (i=0;i<x.length;i++){
        x[i].style.display="none"
    }
    myIndex++
    if(myIndex>x.length){
        myIndex=1
    }
    x[myIndex-1].style.display="block";
    setTimeout(carousel,5000);

}



function onClick() {
    window.open("page1.html");
  }

function openPage(){
    window.open("signin.html","_self");
}