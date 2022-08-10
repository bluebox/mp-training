
let count=0
let imgarr=[
    {
        names : "cart",
        img : "cart.svg"

    },
    {
        names : "home",
        img : "home.svg"

    },
    {
        names : "category",
        img : "category.svg"
    },
    {
        names : "login",
        img : "login.svg"
    },
    {
        names : "cart",
        img : "cart.svg"

    },
    {
        names : "home",
        img : "home.svg"

    },
    {
        names : "category",
        img : "category.svg"
    },
    {
        names : "login",
        img : "login.svg"
    },
    





    {
        names : "bg",
        img : "background.jpg"

    },
    {
        names : "bag",
        img : "bag.png"

    },
    {
        names : "elect",
        img : "electronics.jpg"
    },
    {
        names : "tick",
        img : "tick.svg"
    },
    {
        names : "msw",
        img : "menshoewear.jpg"

    },
    {
        names : "mob",
        img : "mobiles.jpg"

    },
    {
        names : "about",
        img : "about.svg"
    },
    {
        names : "shoping",
        img : "shopping.jpg"
    },


    {
        names : "bg",
        img : "background.jpg"

    },
    {
        names : "bag",
        img : "bag.png"

    },
    {
        names : "elect",
        img : "electronics.jpg"
    },
    {
        names : "tick",
        img : "tick.svg"
    },
    {
        names : "msw",
        img : "menshoewear.jpg"

    },
    {
        names : "mob",
        img : "mobiles.jpg"

    },
    {
        names : "about",
        img : "about.svg"
    },
    {
        names : "shoping",
        img : "shopping.jpg"
    }
    
]
imgarr.sort(() => 0.5-Math.random());
let gridele = document.getElementById("grid");


function board(){
    for(let i=0;i<24;i++)
    {
    let bar=document.createElement("img");
    bar.setAttribute("src","blank.svg");
    bar.setAttribute("id",i);
    bar.style.width="200px";
    bar.style.height="200px";
    gridele.appendChild(bar);
    bar.addEventListener("click",flip)
    }

}

board()
let blank_id = []
let imagename = []




function flip(){
let chosen = document.getElementById( this.getAttribute('id'))
chosen.setAttribute('src',imgarr[this.getAttribute('id')].img)
blank_id.push(this.getAttribute('id'))
imagename.push(imgarr[this.getAttribute('id')].names)
if(imagename.length==2)
{
    checkformatch()
}
}
function checkformatch()
{
    let chose = document.getElementById( blank_id[0])
    let chos = document.getElementById( blank_id[1])

    if(blank_id[0]!=blank_id[1] && imagename[0]==imagename[1])
    {
        chose.setAttribute('src',"white.png")
        chose.style.width="200px";
        chose.style.height="200px";
        chos.setAttribute('src',"white.png")
        chos.style.width="200px";
        chos.style.height="200px";
        chos.removeEventListener("click",flip)
        chose.removeEventListener("click",flip)
        count+=1
        document.getElementById("result").innerHTML = count
        
        if(count==4)
        {
            document.getElementById("result").innerHTML="you won!"
        }
    
    }
    else{
        setTimeout(()=>{chos.setAttribute('src',"blank.svg")
                        chose.setAttribute('src',"blank.svg")}
                        ,1000)
        

    }
blank_id = []
imagename = []
}


