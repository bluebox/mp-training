import { GeneralService } from './../../../general.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  ownerData: any

  constructor(private service : GeneralService, private route: Router) {

    this.service.getOwnerProfileDetails().subscribe(data=>{
      let dataString= JSON.stringify(data);
      this.ownerData= JSON.parse(dataString);
      if(this.ownerData){
       this.ownerGroup.get('name')?.setValue(this.ownerData.name);
       this.ownerGroup.get('password')?.setValue(this.ownerData.password);
       this.ownerGroup.get('email')?.setValue(this.ownerData.email);
       this.ownerGroup.get('contact_no')?.setValue(this.ownerData.contact_no);
       this.ownerGroup.get('address')?.setValue(this.ownerData.address);

      }
    })
  }


  ownerGroup = new FormGroup({
    name: new FormControl('', Validators.minLength(4)),
    contact_no : new  FormControl('', Validators.required),
    email : new FormControl('', Validators.email),
    password : new FormControl('', Validators.minLength(8)),
    address : new FormControl('', Validators.maxLength(100), )

  }
  )
  ngOnInit(): void {
  }

  updateOwnerProfile(){
    console.log('updateCustomerDetails')
    this.service.updateOwnerProfile(this.ownerGroup.value).subscribe((data : any) =>{
      let datastring = JSON.stringify(data);
      this.ownerData = JSON.parse(datastring);
      alert('Updated successfully'),
      window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.route.navigate(['o-profile'])  } )
  }


  // update(updateData:any){
  //   if (updateData == 'email'){
  //    let email= prompt('Enter new email', this.data.email)
  //     if (email != null && email.endsWith('.com')){
  //       this.updatedEmail = email
  //       let owner = {
  //         "owner_id" : this.data.owner_id,
  //         "name" : this.data.name ,
  //         "email" : this.updatedEmail ,
  //         "contact_no" : this.data.contact_no ,
  //         "address" : this.data.address ,
  //         "password" : this.data.password ,
  //       }
  //       //  console.log(this.data.name)

  //       let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
  //         alert('email updated successfully'),
  //         window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //       )
  //     }
  //   }
  //  else if (updateData == 'number'){
  //     let number= prompt('Enter new contact number', this.data.contact_no)
  //      if (number != null && number.length>8){
  //        this.updatedContact = number
  //        let owner = {
  //          "owner_id" : this.data.owner_id,
  //          "name" : this.data.name ,
  //          "email" : this.data.email ,
  //          "contact_no" : this.updatedContact,
  //          "address" : this.data.address ,
  //          "password" : this.data.password ,
  //        }
  //        //  console.log(this.data.name)

  //        let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
  //          alert('Number updated successfully'),
  //          window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //        )
  //      }
  //    }
  //    else if (updateData == 'address'){
  //     let address= prompt('Enter new Address', this.data.address)
  //      if ( address!= null && address.length>5){
  //        this.updatedAddress = address
  //        let owner = {
  //          "owner_id" : this.data.owner_id,
  //          "name" : this.data.name ,
  //          "email" : this.data.email ,
  //          "contact_no" : this.data.contact_no,
  //          "address" : this.updatedAddress ,
  //          "password" : this.data.password ,
  //        }
  //        //  console.log(this.data.name)

  //        let post=this.service.updateOwnerProfile(owner).subscribe((data : any) =>{
  //          alert('Address updated successfully'),
  //          window.sessionStorage.setItem('owner_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //        )
  //      }
  //    }
  //  }
}
