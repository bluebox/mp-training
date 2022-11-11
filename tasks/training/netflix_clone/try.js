    // fetch('https://api.themoviedb.org/3/discover/tv?api_key=19f84e11932abbc79e6d83f82d6d1045&with_networks=213').then(response=> console.log(response.json()))
    // .then(data=>console.log(data))
    async function movie(url){
        let response= await fetch(url)
        let data= await response.json()
        console.log(data.results)
    }
    movie('https://api.themoviedb.org/3/discover/tv?api_key=19f84e11932abbc79e6d83f82d6d1045&with_networks=213')

   