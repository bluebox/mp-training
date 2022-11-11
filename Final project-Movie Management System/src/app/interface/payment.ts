
export class PaymentInterface{
    Booking_id:number
    PROMOCODE:string
    T_price:number
    Payment_status:string
    Payment_mode:string
    
    constructor( Booking_id:number,
        PROMOCODE:string,
        T_price:number,
        Payment_status:string,
        Payment_mode:string){
            this.Booking_id=Booking_id           
            this.PROMOCODE=PROMOCODE
            this.T_price=T_price
            this.Payment_status=Payment_status
            this.Payment_mode=Payment_mode
    }
}