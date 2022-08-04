console.log("hari")

function validate()
{
    let username=document.getElementById("phone").value;
    let password=document.getElementById("password").value;
    
    if (username=="admin" && password=="admin")

    {
      
        window.location.href = "index.html"
    }
        

    else
    {
        console.log(" not register user")
    }
}


function check(a){
     if (a==1) {
        window.location.href = "mobile/mobilePhones.html"
    }
    else if (a==2) {
        window.location.href = "shoes.html"
    }
    else if(a==4){
    window.location.href = "./clothes/pages/categories/clothing.html"
    }
    
}

