// let array=["puma","nike","adidas","crocs","highlander","wrogn","hrx"]
import mensfootwear from "./mensfootwear.json" assert {type:"json"}
let grid=document.querySelector(".grid_container");

function loadpage(){
    
    for (let i = 1; i < 7; i ++) {
        const card = document.createElement("div");
        const image = document.createElement("img");
        const name = document.createElement("div");
        let addto=document.createElement("img");
        addto.src="images/footwear/addtocartlogo.png";
        addto.style.width="50px";
        let fname=mensfootwear[i].name;
        let source1="images/footwear/"+fname+".jpg";
        name.innerHTML=fname;
        image.src=source1;
        const price = document.createElement("p");
        price.innerHTML=mensfootwear[i].price+"&#x20B9";
        // price.innerHTML = Math.floor(Math.random() * (1000 - 100) + 100) + "$";
        card.appendChild(image)
        card.appendChild(name)
        card.appendChild(price)
        card.appendChild(addto);
        grid.appendChild(card);
        // card.onclick("location.href='#';")
        card.addEventListener('click', (event) => {
            location.href ="http://127.0.0.1:5501/shoesfull.html?id="+i;



            
          });
        card.setAttribute("class","card");
        image.setAttribute("class","image1");
   }     
   
}
loadpage();