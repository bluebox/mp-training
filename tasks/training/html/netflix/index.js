array1=["","Netflix is a streaming service that offers a wide variety of award-winning TV shows, movies, anime, documentaries and more – on thousands of internet-connected devices.","Watch Netflix on your smartphone, tablet, Smart TV, laptop, or streaming device, all for one fixed monthly fee. Plans range from ₹ 149 to ₹ 649 a month. No extra costs, no contracts.","Watch anywhere, anytime. Sign in with your Netflix account to watch instantly on the web at netflix.com from your personal computer or on any internet-connected device that offers the Netflix app, including smart TVs, smartphones, tablets, streaming media players and game consoles."]
var boolarray=[0,0,0,0];




function display1(num){

if(boolarray[num]==0){


let ele=document.querySelector(".an"+num);

console.log(ele);
let p1=document.createElement("p");
p1.setAttribute("class","p"+num);
console.log(array1[num]);
p1.innerText=array1[num];
ele.appendChild(p1);
boolarray[num]=1;
}
else{
    
    let ele=document.querySelector(".an"+num);
    let p1=document.getElementsByClassName("p"+num);
     boolarray[num]=0;
    // ele.remove(p1);
    ele.innerHTML = "";



}

}
function fun(){
    window.location="http://127.0.0.1:5500/loginpage.html";
}








