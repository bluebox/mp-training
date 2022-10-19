
export class BookingInterface{
    Booking_id: number
    User_id: number
    Movie_id: number
    Theatre_id:number
    Hall_id: number
    Date:Date
    Selected_seats:string
  
    constructor(
        Booking_id: number,
        User_id: number,
        Movie_id: number,
        Theatre_id:number,
        Hall_id: number,
        Date:Date,
        Selected_seats:string){
            this.Booking_id=Booking_id
            this.User_id=User_id
            this.Movie_id= Movie_id
            this.Theatre_id=Theatre_id
            this.Hall_id=Hall_id
            this.Date=Date
            this.Selected_seats=Selected_seats
           
       
    }
}