let faq_main = document.getElementsByClassName("FAQ_title");
for(let i=0; i<faq_main.length;i++){
    faq_main[i].addEventListener("click",add_remove);
}
function add_remove(){
    if(this.childNodes[1].classList.contains("fa-plus")){
        this.childNodes[1].classList.remove("fa-plus");
        this.childNodes[1].classList.add("fa-times");   
    }
    else{
        this.childNodes[1].classList.remove("fa-times");   
        this.childNodes[1].classList.add("fa-plus");
           
    }
    let con = this.nextElementSibling;
    if(con.style.maxHeight){
        con.style.maxHeight=null
    }
    else{
        con.style.maxHeight=con.scrollHeight + "px"
    }
}