let homePage = document.getElementById("home-page")
let exploreBtn = document.getElementById("explore-more")
let itemsSection = document.getElementById("item-section")

let refrigeratorContainer = document.getElementById("refrigerator-container")
let refrigeratorSection = document.getElementById("refrigerator-section")

let televisionContainer = document.getElementById("television-container")
let televisionSection = document.getElementById("television-section")


let acContainer = document.getElementById("AC-Container")
let airConditionersection = document.getElementById("AC-section")


let refrigeratorBtn = document.getElementById("refri-button")
let tvBtn = document.getElementById("tv-button")
let acBtn = document.getElementById("ac-button")
let home = document.getElementById("home-button")

exploreBtn.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.add("d-inline")

}

refrigeratorContainer.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.remove("d-inline")
    refrigeratorSection.classList.add("d-inline")
}

televisionContainer.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.remove("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.add("d-inline")

}
acContainer.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.remove("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.remove("d-inline")
    airConditionersection.classList.add("d-inline")
}

refrigeratorBtn.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.add("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.remove("d-inline")
    airConditionersection.classList.remove("d-inline")

}
tvBtn.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.add("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.remove("d-inline")
    airConditionersection.classList.remove("d-inline")

}
acBtn.onclick = function(){
    homePage.classList.add("d-none")
    itemsSection.classList.add("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.remove("d-inline")
    airConditionersection.classList.remove("d-inline")

}
home.onclick = function(){
    homePage.classList.add("d-inline")
    itemsSection.classList.remove("d-inline")
    refrigeratorSection.classList.remove("d-inline")
    televisionSection.classList.remove("d-inline")
    airConditionersection.classList.remove("d-inline")
}








let imgArray = ["./images/A.C's/haier 1 ton 3 star.jpeg","./images/A.C's/haier 1.4 ton 3 star.jpeg",          "./images/A.C's/Lloyd 5 star.jpeg","./images/A.C's/lloyd 1 ton 3 star.webp",      "./images/A.C's/samsung 1.5 ton last .webp"];
let halfNameArray = ["Haier 1 Ton 3 Star Split AC - White", "Haier 1.4 Ton 3 Star Split Inverter AC - White", "Lloyd . 1.5 Ton 5 Star Split Inverter with PM 2.5 Filter AC - White", "Lloyd 1 Ton 3 Star Split Inverter AC - White", "SAMSUNG 1.5 Ton 5 Star Split Inverter AC with Wi-fi Connect - White"]
let fullNameArray = ["Haier 1 Ton 3 Star Split AC - White  (HSU12T-TMB3B(FS)/(HS12T-TMB3B(FS)/(HU12-3B(FS), Copper Condenser)", "Haier 1.4 Ton 3 Star Split Inverter AC - White  (HS17Q-TQB3B(INV), Copper Condenser)", "Lloyd . 1.5 Ton 5 Star Split Inverter with PM 2.5 Filter AC - White  (GLS18I56WGEL, Copper Condenser)", "Lloyd 1.2 Ton 5 Star Split Inverter AC - White  (GLS15I5FWSEL, Copper Condenser)", "SAMSUNG 1.5 Ton 5 Star Split Inverter AC with Wi-fi Connect - White  (AR18BY5YATZ, Copper Condenser)"]
let ratingArray = ["4.5", "4.1", "4.3", "4.7", "5.0", "4.4"]
let reviewsArray = ["52 Ratings & 10 Reviews", "14 Ratings & 3 Reviews", "26 Ratings & 1 Reviews", "411 Ratings & 64 Reviews", "20 Ratings & 0 Reviews"];
let priceArray = ["₹27,294", "₹35,990", "₹42,900", "₹33,999", "₹47,803"];
let totalpriceArray =["₹54,490", "₹64,990", "₹60,900", "₹56,999", "₹55,500"]
let discountArray = ["49% off", "44% off", "29% off", "40% off", "13 off"];
let extraperArray = ["Extra ₹1685 off", "Extra ₹2500 off", "Extra ₹3000 off", "Extra ₹1000 off", "Extra ₹1550 off"];
let spanArray1 = ["704 W", "1060 W", "778.89 kWh", "918.32 kWh", "1380 W"];
let spanArray2 = ["26 dB", "29 dB", "32 dB", "59 dB"];
let spanArray3 = ["R-32", "R-35", "R-64", "R-32", "R-58"];
let spanArray4 = ["86.5 cm x 29 cm x 20 cm", "91 cm x 31 cm x 21 cm", "86.5 cm x 29 cm x 20 cm", "91 cm x 31 cm x 21 cm", "105.5 cm x 29.9 cm x 21.5 cm"];
let spanArray5 = ["70 cm x 54.4 cm x 20 cm", "86 cm x 55 cm x 34.5 cm","70 cm x 54.4 cm x 20 cm", "86 cm x 55 cm x 34.5 cm", "79 cm x 54.8 cm x 28.5 cm"];

console.log(ratingArray[-2]);

let shortName = document.getElementById("short-name")
let image = document.getElementById("imageid")
let fullName = document.getElementById("full-name")
let rating = document.getElementById("rating")
let reviews = document.getElementById("reviews")
let price = document.getElementById("price")
let totalPrice = document.getElementById("total-price")
let percentage = document.getElementById("percentage")
let extraPercentage = document.getElementById("Extra-percentage")
let span1 = document.getElementById("watts1")
let span2 = document.getElementById("dessibels2")
let span3 = document.getElementById("refrigerator-model3")
let span4 = document.getElementById("height")
let span5= document.getElementById("width")

let previousBtn = document.getElementById("prev")
let slideShow = document.getElementById("slide-show")
let nextBtn = document.getElementById("nxt")

let count = 0

nextBtn.onclick = function(){
    if (count < fullNameArray.length){
    shortName.textContent = halfNameArray[count]
    image.src = imgArray[count]
    fullName.textContent = fullNameArray[count]
    rating.textContent = ratingArray[count]
    reviews.textContent = reviewsArray[count]
    price.textContent = priceArray[count]
    totalPrice.textContent = totalpriceArray[count]
    percentage.textContent = discountArray[count]
    extraPercentage.textContent = extraperArray[count]
    span1.textContent = spanArray1[count]
    span2.textContent = spanArray2[count]
    span3.textContent = spanArray3[count]
    span4.textContent = spanArray4[count]
    span5.textContent = spanArray5[count]
      count = count+1

    }
    else{
        count = 0
    }
}


previousBtn.onclick = function(){

    if (count > 0){
        count = count-1   
    shortName.textContent = halfNameArray[count]
    image.src = imgArray[count]
    fullName.textContent = fullNameArray[count]
    rating.textContent = ratingArray[count]
    reviews.textContent = reviewsArray[count]
    price.textContent = priceArray[count]
    totalPrice.textContent = totalpriceArray[count]
    percentage.textContent = discountArray[count]
    extraPercentage.textContent = extraperArray[count]
    span1.textContent = spanArray1[count]
    span2.textContent = spanArray2[count]
    span3.textContent = spanArray3[count]
    span4.textContent = spanArray4[count]
    span5.textContent = spanArray5[count]
    }
    else{
        count = fullNameArray.length-1
    }

}
let id 
    
slideShow.onclick = function(){
    count = 0
    slideShow.disabled = true
    
        id = setInterval(() => {
            if (count < imgArray.length){
            shortName.textContent = halfNameArray[count]
            image.src = imgArray[count]
            fullName.textContent = fullNameArray[count]
            rating.textContent = ratingArray[count]
            reviews.textContent = reviewsArray[count]
            price.textContent = priceArray[count]
            totalPrice.textContent = totalpriceArray[count]
            percentage.textContent = discountArray[count]
            extraPercentage.textContent = extraperArray[count]
            span1.textContent = spanArray1[count]
            span2.textContent = spanArray2[count]
            span3.textContent = spanArray3[count]
            span4.textContent = spanArray4[count]
            span5.textContent = spanArray5[count]
            count = count+1


            }

            else{
                count = 0
                    shortName.textContent = "Blue Star 1.2 Ton 3 Star Split Inverter AC"
                    image.src = "./images/A.C's/blue star 1.2 ton 3 star.jpeg"
                    fullName.textContent = "Blue Star 1.2 Ton 3 Star Split Inverter AC - White  (IC315YATU, Copper Condenser)"
                    rating.textContent = "4.3"
                    reviews.textContent = "186 Ratings & 15 Reviews"
                    price.textContent = "₹32,990"
                    totalPrice.textContent = "₹52000"
                    percentage.textContent = "36% off"
                    extraPercentage.textContent = "Extra ₹1000 off"
                    span1.textContent = "940 W"
                    span2.textContent = "50 db"
                    span3.textContent = "R-32"
                    span4.textContent = "84.5 cm x 30 cm x 19.3 cm"
                    span5.textContent = "85.8 cm x 53.6 cm x 32.7 cm"
                    clearInterval(id)
                slideShow.disabled = false
                
            }
        
        }, 2000);
    }
    


    

