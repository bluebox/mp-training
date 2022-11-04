let finalId
let htmlDiv
let subDiv
let subId
let objectList = [{
    numberId: 1,
    content:"Netflix is ​​a streaming service that lets you enjoy a variety of award-winning TV shows, movies, anime, documentaries on thousands of Internet-connected devices.You can watch as much as you want, whenever you want, without any ads – all for a very low monthly fee. There's always something new to discover and new TV shows and movies added every week!"
},
{
    numberId: 2,
    content:"Watch Netflix on your smartphone, tablet, smart TV, laptop, or streaming device for a fixed monthly fee. Plans from ₹ 149 to ₹ 649 per month. No extra prices or contracts."    
},
{
    numberId: 3,
    content: "Watch anywhere, anytime from your personal computer or on any Internet-connected device that offers the Netflix app, including smart TVs, smartphones, tablets, streaming media players and game consoles, on the web at netflix.com Sign in to account.You can also download your favorite shows from the iOS, Android or Windows 10 apps. Use Downloads to watch on the go and without an internet connection. Take Netflix with you wherever you go."
},
{
    numberId: 4,
    content: "Netflix is ​​flexible. There are no annoying contracts and no bindings. You can easily cancel your account online in two clicks. There are no cancellation fees – start or close your account at any time."
},
{
    numberId: 5,
    content: "Netflix's huge library includes feature films, documentaries, TV shows, anime, award-winning Netflix Originals and more. Watch as much as you want, anytime."
},
{
    numberId: 6,
    content: "Your membership includes the Netflix Kids Experience. Kids enjoy family TV shows and movies their own way, but you can control the content they watch.Children's profiles have PIN-protected parental controls that allow you to block children from viewing content with maturity ratings and block titles you don't want children to see."
}];
let same = true
function contentContainer(number){
    for (let each of objectList){
        if (each.numberId === number){
            if (same === true){
                subId = "sub-div" + number
                subDiv = document.getElementById(subId)
                
                subDiv.classList.add("sub-div")
                subDiv.textContent = each.content
                same  = false
                
            
            }
            else{
                subDiv.textContent = ""
                subDiv.classList.remove("sub-div")
                same = true
                break
                
            }
            

        }
        else{
            continue
        }
    }
    
}
let mailContainer = document.getElementById("mail-container")
let emailId = document.getElementById("email-id")
let startBtn = document.getElementById("start-btn")

let passwordContainer = document.getElementById("password-container")
let passwordInput1 = document.getElementById("pass1")
let passwordInput2 = document.getElementById("pass2")
let registerBtn = document.getElementById("register-btn")
let errorMsg =document.getElementById("error-msg")
let registration  = document.getElementById("register")

let count = 1
let sting = localStorage.getItem("Details")
console.log(sting)
let loginDetails =[]
// loginDetails = localStorage.getItem("Details")



let emailId2 = document.getElementById("emailid");
let password = document.getElementById("passwords")
let emailMsg = document.getElementById("email-msg")
let passMsg = document.getElementById("password-msg")
let signBtn = document.getElementById("sign-in-button")
let loginPage = document.getElementById("login-Page")

let mainInterface = document.getElementById("main-interface")

function newObject(emailId, password){
    return{
        Email:emailId,
        Password: password
    }
}
startBtn.onclick = function(){
    mailContainer.classList.remove("d-inline")
    if (emailId.value === ""){
        alert("Enter email id")
    }
    else{
        emailid = emailId.value
        mailContainer.classList.add("d-none")
        passwordContainer.classList.add("d-inline")
        // console.log(obj)
    }
   

}
registerBtn.onclick = function(){
   
    if (passwordInput1.value === ""){
        alert("Enter password")
    }
    else if (passwordInput1.value === passwordInput2.value && passwordInput1.value != ""){
        let number = passwordInput2.value
        let obj = newObject(emailId.value, number)
        // console.log(obj)
        let url = "http://localhost:3000/users"
        // let options = {
        //     method: "POST",
        //     headers:{
        //         "content-Type": "application/json"
        //     },
        //     body:JSON.stringify(obj)
        // };
        fetch(url, {
            method: "POST",
            headers:{
                "content-Type": "application/json"
            },
            body:JSON.stringify(obj)
        })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.log(err))
        // .then(function(response){
        //     console.log(response.json());
        // })
        // .then(funtion(jsonData){
        //     console.log(jsonData)
        // });
        // loginDetails.push(obj)
        // // console.log(loginDetails)
        // localStorage.setItem("Details", JSON.stringify(loginDetails))
        errorMsg.textContent = ""
        passwordContainer.classList.remove("d-inline")
        registration.textContent = "Registration Successful"
        setTimeout(() => {
            registration.textContent = ""
            emailId.value = ""
            passwordInput1.value =""
            passwordInput2.value =""
            mailContainer.classList.add("d-inline")
            console.log(loginDetails)
            

        }, 3000);

    }
    else{
        errorMsg.textContent = "password doesn't match"
    }
}
 

let signInBtn = document.getElementById("sign-in-btn")
let signOutBtn = document.getElementById("sign-out-btn")
let wholeContainer = document.getElementById("whole-containers")
let VideoPage = document.getElementById("main-video-page")













signBtn.onclick = function(){
    let url = "http://localhost:3000/users"
    let options = {
        method: "GET"
    }
    fetch(url, options)
    .then(res => res.json())
    .then(data => {
        for(let each of data){
            if (emailId2.value === each.Email){
                emailMsg.textContent = ""
                if (password.value === each.Password){
                    passMsg.textContent = ""
                    emailId2.value = ""
                    password.value = ""
        
                    // write code here
                    mainInterface.classList.add("d-inline")
                    loginPage.classList.remove("d-inline")
                }
                else if (password.value === ""){
                    passMsg.textContent = "Please enter password"
                }
                else{
                    passMsg.textContent = "Enter valid password"
                }
            }
            else if (emailId2.value === ""){
                emailMsg.textContent = "Please enter email id"
            }
            else{
                emailMsg.textContent = "This mail doesn't exist"
            }
        
        }
    })
    
}
let index = 0
let nameOfMovie = document.getElementById("movie-name")
let movieStory = document.getElementById("summary")
let movieImgage = document.getElementById("series-img")
let videoPlayBtn = document.getElementById("video-play-button")
let mainVideoPage = document.getElementById("main-video-page")
let iFrame = document.getElementById("I-frame")
let backBtn = document.getElementById("back-btn")
let moviesObjects = [{
    movieId: 0,
    movieName: "STRANGER THINGS",
    story:"When a small boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one straight little girl",
    movieImg:"https://images2.alphacoders.com/879/thumbbig-879599.webp"
},
{
    movieId: 1,
    movieName: "MANIFEST",
    story:"Manifest is an American supernatural drama television series created by Jeff Rake that premiered on September 24, 2018, on NBC. It centers on the passengers and crew of a commercial airliner who suddenly reappear after being presumed dead for five and a half years.",
    movieImg:"https://images.hdqwalls.com/wallpapers/manifest-2020-0z.jpg"
},
{
    movieId: 2,
    movieName: "RESIDENT EVIL",
    story:" A vast underground genetics laboratory operated by the powerful Umbrella Corporation. There, a deadly virus has been unleashed, killing the lab's personnel and resurrecting them as the evil Un-dead. The team has just three hours to shut down the lab's supercomputer and close the facility before the virus threatens to overrun the Earth.",
    movieImg:"https://occ.a.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABRxcLJNaM60V7qTsVgfKE4-Fx0TUsd8yzDo85r2hEhbFmcWaq-P62B4sDkoPEBbbi4E2cGXyQW3jRdjGLkI6-ZuLPw-PTM4q56JQfB7N0BMwtCOEj4oxPNnBD2ZPC7Tuz_h3Cw.jpg?r=c03"
},
{
    movieId: 3,
    movieName: "MAJOR",
    story:"The life of Maj. Sandeep Unnikrishnan, who fought against terrorists and was killed in action during the 2008 attacks at the Taj Palace Hotel in Mumbai, India.",
    movieImg:"https://images.hindustantimes.com/img/2022/05/09/1600x900/major_trailer_1652103898671_1652103898810.png"
},
{
    movieId: 4,
    movieName: "BHOOL BHULAIYAA 2",
    story:"When strangers Reet and Ruhan cross paths, their journey leads to an abandoned mansion and a dreaded spirit who has been trapped for 18 years.",
    movieImg:"https://www.bollywoodhungama.com/wp-content/uploads/2022/04/Bhool-Bhulaiyaa-2-6.jpg"
},
{
    movieId:5,
    movieName: "BATMAN 2: The dark knight",
    story:"With the help of allies Lt. Jim Gordon (Gary Oldman) and DA Harvey Dent (Aaron Eckhart), Batman (Christian Bale) has been able to keep a tight lid on crime in Gotham City. But when a vile young criminal calling himself the Joker (Heath Ledger) suddenly throws the town into chaos, the caped Crusader begins to tread a fine line between heroism and vigilantism.",
    movieImg:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0EstVIRNdkgohYfvj4yslqIPt7G3yGFFKNw&usqp=CAU"
},
{
    movieId:6,
    movieName: "TERMINATOR-3",
    story:"Now 25, Connor (Nick Stahl) lives with no record of his existence -- no way he can be traced by Skynet. Out of the shadows of the future steps the T-X (Kristanna Loken), Skynet's most sophisticated cyborg killing machine yet. Now Connor's only hope for survival is the Terminator.",
    movieImg:"https://www.slantmagazine.com/wp-content/uploads/2003/07/terminator3.jpg"
},
{
    movieId:7,
    movieName: "JACK THE GIANT SLAYER",
    story:"When young farmhand Jack (Nicholas Hoult) unwittingly opens a portal between his realm and a race of giants, it rekindles an ancient war. Roaming Earth for the first time in centuries, the fearsome giants seek to reclaim the land they lost long ago. ",
    movieImg:"https://dlylovell.files.wordpress.com/2013/03/jack-the-giant-slayer-movie-poster-wallpapers_36108_1680x1050.jpg"
},
{
    movieId:8,
    movieName: "AVATAR",
    story:"A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
    movieImg:"https://wallpaperaccess.com/full/1199976.jpg"
},
{
    movieId:9,
    movieName: "AVENGERS INFINITY WAR",
    story:"Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality.",
    movieImg:"https://images.hdqwalls.com/wallpapers/avengers-infinity-war-2018-movie-x8.jpg"
},
{
    movieId:10,
    movieName: "SPIDERMAN",
    story:"Spider-Man centers on student Peter Parker (Tobey Maguire) who, after being bitten by a genetically-altered spider, gains superhuman strength and the spider-like ability to cling to any surface.",
    movieImg:"https://images2.alphacoders.com/280/280472.jpg"
}]

let videosList = [ "b9EkMc79ZSU","ScHuEYxcG7E","dFTj_EFLYao", "hewaloVB6JQ", "WTOnkYlb7Rg", "mqqft2x_Aa4", "hSZkU9Yyp0w", "VvmsjoxayAM", "0sJeBiUCIt4", "vHEvLccTxlg", "r_W6mXqzJNU"]

// 
function moviefunction(id){
    
    for (let each of moviesObjects){
    if (id === each.movieId){
        index = id
        console.log(videosList[index])
        nameOfMovie.textContent = each.movieName;
        movieStory.textContent = each.story;
        movieImgage.src = each.movieImg
    }
}
}
videoPlayBtn.onclick = function(){
    mainVideoPage.classList.add("d-inline")
    mainInterface.classList.remove("d-inline")
    let link = "https://www.youtube.com/embed/" + videosList[index]+ "?rel=0"
    iFrame.src = link

    

}
backBtn.onclick = function(){3
    mainInterface.classList.add("d-inline")
    mainVideoPage.classList.remove("d-inline")

}
let check = true
signInBtn.onclick = function(){
    if (check === true){
    wholeContainer.classList.add("d-none")
    loginPage.classList.add("d-inline")
    check = false
    }
    else{
        wholeContainer.classList.remove("d-inline")
        loginPage.classList.add("d-inline")
        check = true
    }
    // let loginCredentials = localStorage.getItem("Details")
    // console.log(loginCredentials)
}
signOutBtn.onclick = function(){
    wholeContainer.classList.add("d-inline")
    mainInterface.classList.remove("d-inline")
    
}