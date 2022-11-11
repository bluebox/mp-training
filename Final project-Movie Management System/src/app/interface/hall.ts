import { Time } from "@angular/common"

export class HallInterface{
    Hall_id:number
    Hall_no:number
    Date:Date
    Theatre_id:number
    rows:number
    cols:number
    baseprice:number
    
    constructor(Hall_id:number,
        Hall_no:number,
        Date:Date,
        Theatre_id:number,
        rows:number,
        cols:number,
    baseprice:number
    ){
            this.Hall_id=Hall_id
            this.Hall_no=Hall_no
            this.Date=Date
            this.Theatre_id=Theatre_id            
            this.rows=rows
            this.cols=cols
            this.baseprice=baseprice
       
    }
}