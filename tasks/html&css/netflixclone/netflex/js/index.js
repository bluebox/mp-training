function description(description_id , image_id ){
    document.getElementById("img1").src ="../images/plus.png";
    document.getElementById("img2").src ="../images/plus.png";
    document.getElementById("img3").src ="../images/plus.png";
    document.getElementById("img4").src ="../images/plus.png";
    document.getElementById("img5").src ="../images/plus.png";
    document.getElementById("img6").src ="../images/plus.png";
    document.getElementById("faq1d").style.display  ="none";
    document.getElementById("faq2d").style.display ="none";
    document.getElementById("faq3d").style.display  ="none"
    document.getElementById("faq4d").style.display  ="none"
    document.getElementById("faq5d").style.display  ="none"
    document.getElementById("faq6d").style.display ="none"

    
    
    
    // if(document.getElementById(description_id).style.display == "none"){
    document.getElementById(image_id).src="../images/close.png";
    document.getElementById(description_id).style.display = "block";
//     }
//     else{
//         document.getElementById(image_id).src="../images/plus.png";
//         document.getElementById(description_id).style.display = "none";
//     }
}