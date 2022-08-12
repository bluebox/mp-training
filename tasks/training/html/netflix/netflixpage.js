
function loadpage(){
    let originals=document.querySelector(".originals");
    let trendings=document.querySelector(".trendings");
    let toprated=document.querySelector(".toprated");
    for (let i=1;i<11;i++){
        let imagediv=document.createElement("div");
        let img1=document.createElement("img");
        img1.src="./images/"+i+".jpg";
        imagediv.appendChild(img1);
        originals.appendChild(imagediv);
        imagediv.setAttribute("class","imagediv");
        img1.setAttribute("class","img1");
        // img1.onclick = click1
        imagediv.addEventListener("click",function(){  window.location="http://127.0.0.1:5500/prodectpage.html?id="+i; })

     }
}

function click1(i) {
    window.location="http://127.0.0.1:5500/prodectpage.html?id="+i;
    
};

function loadpage2(){
    // let originals=document.querySelector(".originals");
    let trending=document.querySelector(".trending");
    let toprated=document.querySelector(".toprated");
    for (i=10;i>0;i--){
        let imagediv=document.createElement("div");
        let img1=document.createElement("img");
        img1.src="./images/"+i+".jpg";
        imagediv.appendChild(img1);
        trending.appendChild(imagediv);
        imagediv.setAttribute("class","imagediv");
        img1.setAttribute("class","img1");

    }
}
function loadpage3(){
    // let originals=document.querySelector(".originals");
    let trending=document.querySelector(".trending");
    let toprated=document.querySelector(".toprated");
    for (i=10;i>0;i--){
        let imagediv=document.createElement("div");
        let img1=document.createElement("img");
        img1.src="./images/"+i+".jpg";
        imagediv.appendChild(img1);
        toprated.appendChild(imagediv);
        imagediv.setAttribute("class","imagediv");
        img1.setAttribute("class","img1");

    }
}
loadpage();
loadpage2();
loadpage3();
// const scrol=document.getElementById("scroll");
//         const x=window.scrollX;
//         function scroll(){
//             scrol.scrollRight=scrol.scrollRight+5

//             scrolling = window.setTimeout(function() {
//             scroll();
//         }, 100);
//         }

//         function stop() {
//         window.clearTimeout(scrolling);
//     }