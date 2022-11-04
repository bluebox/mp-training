import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';
import { Router } from "@angular/router"

@Component({
  selector: 'app-customerrequest',
  templateUrl: './customerrequest.component.html',
  styleUrls: ['./customerrequest.component.css']
})
export class CustomerrequestComponent implements OnInit {
  email:any;
  obj:any;
  requestdata:any
  status:any;
  delete_id:any;
  data:any
  constructor(private userdata:UserserviceService, private http:HttpClient, private router:Router) {
    if (localStorage.getItem("customer_refresh_token")) {
      this.email =  this.userdata.userDetails()
    this.http.post("api/request_data/", this.email).subscribe(data =>{
          console.log(data);
          this.requestdata = data
          for (let i of this.requestdata){
            if (i.request_status === true){
              this.status = "Success"
            }
            else{
              this.status = "Pending"
            }
          }

    })
      this.router.navigate(['/cusdashBoard/customerrequest']);
    }
    else {
      this.router.navigate(['/home']);
    }
   }

  ngOnInit(): void {
  }
  requestForm = new FormGroup({
    request_type : new FormControl('')
  })
    

  
  sendRequest(){
    var result = confirm("Are you sure to send request");
if (result) {
  this.email =  this.userdata.userDetails()
  this.obj = {"request_type":this.requestForm.value.request_type, "email":this.email}
  this.http.post("api/cus_request/", this.obj).subscribe(data =>{
    console.log(data);
    this.requestdata  = data
    let strin = JSON.stringify(data)
    let parse = JSON.parse(strin)
    
    if (parse === "Already you have a card"){
      this.data = parse
      console.log(this.data);

    }
    else{
      this.data = ""
    }
  })
}
else{
  this.router.navigate(['/cusdashBoard/customerrequest']);

}
   

     
      
    

  }
  Delete(id:any){
    var result = confirm("Want to delete?");
if (result) {
  this.http.post("api/delete_request/", id).subscribe(res=>{
    console.log(res)
    this.email =  this.userdata.userDetails()
    this.http.post("api/request_data/", this.email).subscribe(data =>{
          console.log(data);
          this.requestdata = data
          for (let i of this.requestdata){
            if (i.request_status === true){
              this.status = "Success"
            }
            else{
              this.status = "Pending"
            }
          }

          window.location.href = '/cusdashBoard/customerrequest'

    })

  })
}
   
   

    
  }

}
