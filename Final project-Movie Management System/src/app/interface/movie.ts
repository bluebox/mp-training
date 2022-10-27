
export class MovieInterface{
    Movie_id: number
    Movie_name: string
    Movie_lang: string
    Movie_details: string
    Movie_Release_date:Date
    Movie_category: string
    Movie_cast: string
    Movie_reviews:string
    Movie_poster:string
    
    constructor(Movie_id: number,
        Movie_name: string,
        Movie_lang: string,
        Movie_details: string,
        Movie_Release_date:Date,
        Movie_category: string,
        Movie_cast: string,
        Movie_poster:string,
        Movie_reviews:string){
            this.Movie_id= Movie_id
            this.Movie_name= Movie_name
            this.Movie_lang= Movie_lang
            this.Movie_details= Movie_details;
            this.Movie_Release_date=Movie_Release_date
            this.Movie_category= Movie_category
            this.Movie_cast= Movie_cast
            this.Movie_reviews=Movie_reviews  
            this.Movie_poster=Movie_poster          
       
    }
}