
const baseImg = "https://image.tmdb.org/t/p/original"
const baseUrl = "https://api.themoviedb.org/3"
const API_KEY = "e223518361e0a8c44e8d23fd5aa4d872";
const requests = {
fetchTrending: `/trending/all/week?api_key=${API_KEY}`,
fetchNetflixOriginals: `/discover/tv?api_key=${API_KEY}&with_networks=213`,
fetchTopRated: `/movie/top_rated?api_key=${API_KEY}&language=en-US`,
fetchActionMovies: `/discover/movie?api_key=${API_KEY}&language=en-US&with_genres=28`,
fetchComedyMovies: `/discover/movie?api_key=${API_KEY}&language=en-US&with_genres=35`,
fetchHorrorMovies: `/discover/movie?api_key=${API_KEY}&language=en-US&with_genres=27`,
fetchRomanceMovies: `/discover/movie?api_key=${API_KEY}&language=en-US&with_genres=10749`,
fetchDocumentaries: `/discover/movie?api_key=${API_KEY}&language=en-US&with_genres=99`,
};
async function getMovies(){
    let response = await fetch(`${baseUrl}${requests.fetchTopRated}`)

    let data = await response.json()
    console.log(data)
    return data;
}
function getData(){
getMovies().then(data =>{
    // console.log(data.results[0].poster_path)
data.results.map((datas)=>{
    // var div = document.createElement("div")
    // div.classList.add("imgcont")
    console.log(datas.title)
var img = document.createElement("img"); 

img.src = `${baseImg}${datas.poster_path}`; 
img.classList.add("img1")
var src = document.getElementById("block1"); 

src.appendChild(img); 
    // console.log(datas.title)
})
})
}
getData();

// Fetch Trending 
async function getMoviest(){
    let response = await fetch(`${baseUrl}${requests.fetchTrending}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatat(){
getMoviest().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block2"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatat();

// Fetch Trending 
async function getMoviesu(){
    let response = await fetch(`${baseUrl}${requests.fetchNetflixOriginals}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatau(){
getMoviesu().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block3"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatau();


// Fetch Trending 
async function getMoviesc(){
    let response = await fetch(`${baseUrl}${requests.fetchComedyMovies}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatac(){
getMoviesc().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block5"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatac();

// Fetch Trending 
async function getMoviesh(){
    let response = await fetch(`${baseUrl}${requests.fetchHorrorMovies}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatah(){
getMoviesh().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block6"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatah();

// Fetch Trending 
async function getMoviesa(){
    let response = await fetch(`${baseUrl}${requests.fetchActionMovies}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDataa(){
getMoviesa().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block7"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDataa();

// Fetch Trending 
async function getMoviesr(){
    let response = await fetch(`${baseUrl}${requests.fetchRomanceMovies}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatar(){
getMoviesr().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block8"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatar();

// Fetch Trending 
async function getMoviesd(){
    let response = await fetch(`${baseUrl}${requests.fetchDocumentaries}`)

    let data = await response.json()
    console.log(response)
    return data;
}
function getDatad(){
getMoviesd().then(data =>{console.log(data.results[0].poster_path)
data.results.map((datas)=>{
var img1 = document.createElement("img"); 

img1.src = `${baseImg}${datas.poster_path}`; 
img1.classList.add("img1")
var src = document.getElementById("block4"); 

src.appendChild(img1); 
    // console.log(datas.title)
})
})
}
getDatad();

// scroll effect

var button = document.getElementById('slide');
button.onclick = function () {
    var container = document.getElementById('container');
    sideScroll(container,'right',25,100,10);
};

var back = document.getElementById('slideBack');
back.onclick = function () {
    var container = document.getElementById('container');
    sideScroll(container,'left',25,100,10);
};

function sideScroll(element,direction,speed,distance,step){
    scrollAmount = 0;
    var slideTimer = setInterval(function(){
        if(direction == 'left'){
            element.scrollLeft -= step;
        } else {
            element.scrollLeft += step;
        }
        scrollAmount += step;
        if(scrollAmount >= distance){
            window.clearInterval(slideTimer);
        }
    }, speed);
}
