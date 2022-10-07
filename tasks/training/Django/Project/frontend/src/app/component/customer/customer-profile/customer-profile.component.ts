import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {
  updatedMail : any
  updatedName : any

  updatedContact : any
  updatedAddress : any
  customerData: any
  constructor(private service : GeneralService) {

    this.service.getprofileDetails().subscribe(data=>{
      let dataString= JSON.stringify(data);
      this.customerData= JSON.parse(dataString);
      if(this.customerData){
       this.updatedName = this.customer_group.get('name')?.setValue(this.customerData.name);
       this.updatedName = this.customer_group.get('password')?.setValue(this.customerData.password);
       this.updatedMail = this.updatedMail= this.customer_group.get('email')?.setValue(this.customerData.email);
       this.customer_group.get('dl_no')?.setValue(this.customerData.dl_no);
       this.updatedContact = this.customer_group.get('contact_no')?.setValue(this.customerData.contact_no);
       this.updatedAddress = this.customer_group.get('address')?.setValue(this.customerData.address);

      }
    })
    // this.getCustomer()

  }

  customer_group = new FormGroup({
    name: new FormControl('', Validators.minLength(4)),
    contact_no : new  FormControl('', Validators.required),
    email : new FormControl('', Validators.email),
    dl_no : new FormControl('', Validators.required),
    password : new FormControl('', Validators.minLength(8)),
    address : new FormControl('', Validators.maxLength(100), )

  }
  )



  response : any

  getCustomer(){
    this.service.getCustomer(this.customer_group.value).subscribe((data : any ) =>{(this.response=data),
      window.sessionStorage.setItem('customer', JSON.stringify(data))})
  }

  ngOnInit(): void {
  }
  data_1 : any  = window.sessionStorage.getItem('customer_id')
  data = JSON.parse(this.data_1)

  register_customer(){

  }
  updateCustomerDetails(){
    console.log('updateCustomerDetails')
    this.service.updateCustomerProfile(this.customer_group.value).subscribe((data : any) =>{
      let datastring = JSON.stringify(data);
      this.customerData = JSON.parse(datastring);
      alert('Updated successfully'),
      window.sessionStorage.setItem('customer_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() } )
  }

  // updateCustomerDetails(updateData : any){
  //   console.log(this.data.customer_id)
  //   if (updateData == 'email'){
  //     let email= prompt('Enter new email', this.data.email)
  //      if (email != null && email.endsWith('.com')){
  //        this.updatedEmail = email
  //        let customer = {
  //          "customer_id" : this.data.customer_id,
  //          "name" : this.data.name ,
  //          "email" : this.updatedEmail ,
  //          "dl_no" : this.data.dl_no ,
  //          "contact_no" : this.data.contact_no ,
  //          "address" : this.data.address ,
  //          "password" : this.data.password ,
  //        }
  //        //  console.log(this.data.name)

  //        let post=this.service.updateCustomerProfile(customer).subscribe((data : any) =>{
  //          alert('email updated successfully'),
  //          window.sessionStorage.setItem('customer_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //        )
  //      }
  //    }
  //   else if (updateData == 'number'){
  //      let number= prompt('Enter new contact number', this.data.contact_no)
  //       if (number != null && number.length>8){
  //         this.updatedContact = number
  //         let customer = {
  //           "customer_id" : this.data.customer_id,
  //           "name" : this.data.name ,
  //           "email" : this.data.email ,
  //           "dl_no" : this.data.dl_no ,
  //           "contact_no" : this.updatedContact,
  //           "address" : this.data.address ,
  //           "password" : this.data.password ,
  //         }
  //         //  console.log(this.data.name)

  //         let post=this.service.updateCustomerProfile(customer).subscribe((data : any) =>{
  //           alert('Number updated successfully'),
  //           window.sessionStorage.setItem('customer_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //         )
  //       }
  //     }
  //     else if (updateData == 'address'){
  //      let address= prompt('Enter new Address', this.data.address)
  //       if ( address!= null && address.length>5){
  //         this.updatedAddress = address
  //         let customer = {
  //           "customer_id" : this.data.customer_id,
  //           "name" : this.data.name ,
  //           "email" : this.data.email ,
  //           "dl_no" : this.data.dl_no ,
  //           "contact_no" : this.data.contact_no,
  //           "address" : this.updatedAddress ,
  //           "password" : this.data.password ,
  //         }
  //         //  console.log(this.data.name)

  //         let post=this.service.updateCustomerProfile(customer).subscribe((data : any) =>{
  //           alert('Address updated successfully'),
  //           window.sessionStorage.setItem('customer_id', JSON.stringify(data)), this.ngOnInit(), window.location.reload() }

  //         )
  //       }
  //     }
  // }
}
