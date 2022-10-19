import { Time } from "@angular/common"

export class HallInterface{
    Hall_id:number
    Hall_no:number
    T_No_Of_Seats:number
    Date:Date
    startTime:Time
    EndTime:Time
    Theatre_id:number
    Movie_id:number
    rows:number
    cols:number
    baseprice:number
    
    constructor(Hall_id:number,
        Hall_no:number,
        T_No_Of_Seats:number,
        Date:Date,
        startTime:Time,
        EndTime:Time,
        Theatre_id:number,
        Movie_id:number,
        rows:number,
        cols:number,
    baseprice:number
    ){
            this.Hall_id=Hall_id
            this.Hall_no=Hall_no
            this.T_No_Of_Seats= T_No_Of_Seats;
            this.Date=Date
            this.startTime= startTime
            this.EndTime= EndTime
            this.Theatre_id=Theatre_id            
            this.Movie_id= Movie_id
            this.rows=rows
            this.cols=cols
            this.baseprice=baseprice
       
    }
}