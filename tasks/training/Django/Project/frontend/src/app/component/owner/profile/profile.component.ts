import { GeneralService } from './../../../general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private service : GeneralService) { }

  ngOnInit(): void {
  }

  resp : any = window.sessionStorage.getItem('owner_id')
  data = JSON.parse(this.resp)
  updatedEmail : string=''
  updatedContact : string=''
  updatedAddress : string=''


  update(updateData:any){
    if (updateData == 'email'){
     let email= prompt('Enter new email', this.data.email)
      if (email != null && email.endsWith('.com')){
        this.updatedEmail = email
        let owner = {
          "owner_id" : this.data.owner_id,
          "name" : this.data.name ,
          "email" : this.updatedEmail ,
          "contact_no" : this.data.contact_no ,
          "address" : this.data.address ,
          "password" : this.data.password ,
        }
        //  console.log(this.data.name)

        let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
          alert('email updated successfully'),
          window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

        )
      }
    }
   else if (updateData == 'number'){
      let number= prompt('Enter new contact number', this.data.contact_no)
       if (number != null && number.length>8){
         this.updatedContact = number
         let owner = {
           "owner_id" : this.data.owner_id,
           "name" : this.data.name ,
           "email" : this.data.email ,
           "contact_no" : this.updatedContact,
           "address" : this.data.address ,
           "password" : this.data.password ,
         }
         //  console.log(this.data.name)

         let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
           alert('Number updated successfully'),
           window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

         )
       }
     }
     else if (updateData == 'address'){
      let address= prompt('Enter new Address', this.data.address)
       if ( address!= null && address.length>5){
         this.updatedAddress = address
         let owner = {
           "owner_id" : this.data.owner_id,
           "name" : this.data.name ,
           "email" : this.data.email ,
           "contact_no" : this.data.contact_no,
           "address" : this.updatedAddress ,
           "password" : this.data.password ,
         }
         //  console.log(this.data.name)

         let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
           alert('Address updated successfully'),
           window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

         )
       }
     }
   }
}
