function func(num){
    var docu=document.querySelector(num);
    if (docu.classList.contains("closed")){
        docu.classList.add("active");
        docu.classList.remove("closed");
        
        docu.style.display="block";
    }
    else if (docu.classList.contains("active")){
        docu.classList.add("closed")
        docu.classList.remove("active")
        docu.style.display="none";
    }
}


function langChange(){
    if (document.getElementById("language").value=="hindi"){
        document.getElementById('h2').innerHTML="अनलिमिटेड फ़िल्में, ,<br> टीवी शो और बहुत कुछ.";
        document.getElementById("p1").innerHTML="जहां चाहें देखें. जब चाहें कैंसल करें. देखने के लिए तैयार हैं? <br> अपनी मेंबरशिप बनाने या रीस्टार्ट करने के लिए अपना ईमेल एड्रेस डालें."
        console.log("working hindi");
    }
    else if (document.getElementById('language').value=="english"){
        document.getElementById('h2').innerHTML="Unlimited movies, TV  <br> shows and more.";
        document.getElementById('p1').innerHTML='Watch anywhere. Cancel anytime. <br> Ready to watch? Enter your email to create or restart your membership.';
        console.log("working");
    }
}