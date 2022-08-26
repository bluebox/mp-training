const array = ["iphone 11", "iphone 13", "iphone xr", "iphone 6", "samsung note 5", "samsung a12", "nothing", "vivo", "oppo"];
var currPageNumber = 1;

function loadPage() {
    const middle = document.querySelector(".middle");
    middle.innerHTML = "";
    for (let i = 0; i < 24; i ++) {
        const card = document.createElement("div");
        const innercard = document.createElement("div");
        const image = document.createElement("img");
        image.src = "images/mobiles/apple-iphone-11-1.jpg";
        const name = document.createElement("div");
        name.innerHTML = array[Math.floor(Math.random() * 9)]
        console.log(array[Math.floor(Math.random() * 9)])
        const priceandrating = document.createElement("div");
        const price = document.createElement("p");
        price.innerHTML = Math.floor(Math.random() * (1000 - 100) + 100) + "$";
        const rating = document.createElement("div");
        const stars = Math.floor(Math.random() * 5 + 1);
        for (let i = 0; i < stars; i ++) {
            const star = document.createElement("span");
            star.setAttribute("class", "fa fa-star checked");
            rating.appendChild(star);
        }
        for (let i = 0; i < 5 - stars; i ++) {
            const star = document.createElement("span");
            star.setAttribute("class", "fa fa-star");
            rating.appendChild(star);
        }
        // rating.innerHTML = Math.floor(Math.random() * 5 + 1) + "stars";
        priceandrating.appendChild(price);
        priceandrating.appendChild(rating);
        card.setAttribute("id", i + 1);
        innercard.appendChild(image);
        innercard.appendChild(name);
        innercard.appendChild(priceandrating);
        innercard.setAttribute("class", "innercard");
        card.setAttribute("class", "card");
        name.setAttribute("class", "name");
        priceandrating.setAttribute("class", "priceandrating");
        image.setAttribute("class", "cardimg");
        card.appendChild(innercard);
        middle.appendChild(card);
    }
    middle.setAttribute("class", "middle");
}

const firstPage = document.querySelector(".number");
if (firstPage.innerHTML == "1") {
    console.log("yes");
    loadPage()
}

function prev() {
    if (currPageNumber == 1) {
        alert("no more previous pages");
    }
    else {
        currPageNumber -= 1;
        const pageNode = document.querySelector(".number");
        pageNode.innerHTML = currPageNumber;
        loadPage()
    }
}

function next() {
    if (currPageNumber == 10) {
        alert("no more next pages");
    }
    else {
        currPageNumber += 1;
        const pageNode = document.querySelector(".number");
        pageNode.innerHTML = currPageNumber;
        loadPage()
    }
}


function filterapply() {
    const radios = document.querySelectorAll(".sortradio");
    console.log(radios);
    if (radios[0].checked) {
        console.log("name");
        sortByName();
    }
    else if (radios[1].checked) {
        console.log("price");
        sortByPrice();
    }
    else if (radios[3].checked) {
        console.log("rating");
        sortByRating();
    }
}
    // sortByName();
    // sortByRating();
    // sortByPrice();

function clearfilter() {
    const radios = document.querySelectorAll(".sortradio");
    for (let i = 0; i < radios.length; i ++) {
        if (radios[i].checked) {
            radios[i].checked = false;
        }
    }
}

function sortByPrice() {  
    var middle = document.querySelector(".middle");  
    var cards = document.getElementsByClassName("card");
    var arr = [];
    for (let i = 0; i < cards.length; i ++) {
        arr.push([cards[i].childNodes[2].childNodes[0].innerHTML, cards[i].id]);
        arr[i][0] = parseInt(arr[i][0].substring(0, arr[i][0].length - 1));
    }
    arr.sort(sortFunction)
    function sortFunction(a, b) {
        if (a[0] === b[0]) {
            return 0;
        }
        else {
            return (a[0] < b[0]) ? -1 : 1;
        }
    }
    for (let i = 0; i < arr.length; i ++) {
        for (let j = 0; j < cards.length; j ++) {
            if (cards[j].id == arr[i][1]) {
                console.log(cards[j].id, arr[i][1]);
                console.log(cards[j]);
                middle.appendChild(cards[j]);
            }
        }
    }
}

function sortByName() {
    var middle = document.querySelector(".middle");  
    var cards = document.getElementsByClassName("card");
    var arr = [];
    for (let i = 0; i < cards.length; i ++) {
        arr.push([cards[i].childNodes[1].innerHTML, cards[i].id]);
    }
    arr.sort(sortFunction)
    function sortFunction(a, b) {
        if (a[0] === b[0]) {
            return 0;
        }
        else {
            return (a[0] < b[0]) ? -1 : 1;
        }
    }
    for (let i = 0; i < arr.length; i ++) {
        for (let j = 0; j < cards.length; j ++) {
            if (cards[j].id == arr[i][1]) {
                console.log(cards[j].id, arr[i][1]);
                console.log(cards[j]);
                middle.appendChild(cards[j]);
            }
        }
    }
}

function sortByRating() {
    var middle = document.querySelector(".middle");  
    var cards = document.getElementsByClassName("card");
    var arr = [];
    for (let i = 0; i < cards.length; i ++) {
        arr.push([cards[i].childNodes[2].childNodes[1].childNodes, cards[i].id]);
        var node = cards[i].childNodes[2].childNodes[1].childNodes;
        var count = 0;
        for (let j = 0; j < node.length; j ++) {
            if (node[j].getAttribute("class") == "fa fa-star checked")  {
                count += 1;
            }
        }
        arr[i][0] = parseInt(count);
    }
    arr.sort(sortFunction)
    function sortFunction(a, b) {
        if (a[0] === b[0]) {
            return 0;
        }
        else {
            return (a[0] < b[0]) ? -1 : 1;
        }
    }
    for (let i = 0; i < arr.length; i ++) {
        for (let j = 0; j < cards.length; j ++) {
            if (cards[j].id == arr[i][1]) {
                console.log(cards[j].id, arr[i][1]);
                console.log(cards[j]);
                middle.appendChild(cards[j]);
            }
        }
    }  
}
