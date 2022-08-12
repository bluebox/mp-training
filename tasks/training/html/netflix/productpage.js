img1=document.querySelector(".img1");
img12=document.createElement("img");
let loc=window.location.href;
console.log(loc)
let path=loc[loc.length-1]
console.log(path);
img12.src="./images/"+path+".jpg";
img12.setAttribute("class","img12");
img1.appendChild(img12);
