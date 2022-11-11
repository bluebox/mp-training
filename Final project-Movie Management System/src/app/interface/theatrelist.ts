import { Time } from "@angular/common"

export class TheatrelistInterface{
   Theatre_name:string
   Theatre_loc:string
   Hall_no:number
   startTime:Time
   T_No_Of_Seats:number  
    constructor(
        Theatre_name:string,
        Theatre_loc:string,
        Hall_no:number,
        startTime:Time,
        T_No_Of_Seats:number
        ){
            this.Theatre_name=Theatre_name
            this.Theatre_loc=Theatre_loc
            this.Hall_no=Hall_no
            this.startTime=startTime
            this.T_No_Of_Seats=T_No_Of_Seats
    }
}