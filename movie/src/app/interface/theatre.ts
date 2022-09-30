import { Time } from "@angular/common"

export class TheatreInterface{
    Theatre_id:number
    Theatre_name:string
    Theatre_location:string
    Theatre_screens:number
    
    constructor( Theatre_id:number,
        Theatre_name:string,
        Theatre_location:string,
        Theatre_screens:number){
            this.Theatre_id=Theatre_id            
            this.Theatre_name=Theatre_name
            this.Theatre_location=Theatre_location
            this.Theatre_screens=Theatre_screens
    }
}