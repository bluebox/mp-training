var x=document.getElementById("count");
var y=document.getElementById("fill");
y.innerHTML="";
function show(){
    console.log(x.value);
    y.append(x.value+" is found");
}