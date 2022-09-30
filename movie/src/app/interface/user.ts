
export class UserInterface{
    User_id:number
    User_name:string
    User_phone:string
    User_email:string
    Password:string
    User_DOB:Date
    User_Address:string
    
    constructor(User_id:number,User_name:string,
        User_phone:string,
        User_email:string,
        Password:string,
        User_DOB:Date,
        User_Address:string){
            this.User_id=User_id
            this.User_name=User_name
            this.User_phone=User_phone
            this.User_email=User_email
            this.Password=Password
            this.User_DOB=User_DOB
            this.User_Address=User_Address         
       
    }
}