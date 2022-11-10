
function dropdown(num){
    let doc=document.querySelector(num);
    if(doc.classList.contains("para")){
        doc.classList.add("active");
        doc.classList.remove("para")
        doc.style.display="block";
    }
    else if(doc.classList.contains("active")){
        doc.classList.add("para")
        doc.classList.remove("active")

        doc.style.display="none";
    }
}
