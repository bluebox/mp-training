const val=document.querySelector("#button");
var msg="";
msg+=document.querySelector("#name").value+"\n";
msg+=document.querySelector("#email").value+"\n";
msg+=document.querySelector("#password").value;
val.addEventListener("click",(e)=>{
    e.preventDefault();
    alert(msg);
})