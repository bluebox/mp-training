import mensfootwear from "./mensfootwear.json" assert {type:"json"}

let location=window.location.href;
console.log(location);
let id=location[location.length-1]
console.log(id)
let imagepath=mensfootwear[id].name;

document.getElementById("class").src="images/footwear/"+imagepath+".jpg";