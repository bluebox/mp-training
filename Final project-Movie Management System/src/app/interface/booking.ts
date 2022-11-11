
export class BookingInterface{
    Booking_id: number
    User_id: number
    Movie_id: number
    Theatre_id:number
    Hall_id: number
    Date:Date
    Selected_seats:string
    T_price:number
    Payment_Status:string
    Payment_mode:string
  
    constructor(
        Booking_id: number,
        User_id: number,
        Movie_id: number,
        Theatre_id:number,
        Hall_id: number,
        Date:Date,
        Selected_seats:string,
        T_price:number,
        Payment_status:string,
        Payment_mode:string){
            this.Booking_id=Booking_id
            this.User_id=User_id
            this.Movie_id= Movie_id
            this.Theatre_id=Theatre_id
            this.Hall_id=Hall_id
            this.Date=Date
            this.Selected_seats=Selected_seats
            this.T_price=T_price
            this.Payment_Status=Payment_status
            this.Payment_mode=Payment_mode
    }
}