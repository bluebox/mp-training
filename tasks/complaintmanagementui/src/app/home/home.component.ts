import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../Facility';
import { ServicesService } from '../services.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  facilities :any 
  devices : any 
  user:Employee | undefined;
  userid :any
  complaintform :FormGroup | undefined;
  constructor(private service:ServicesService) { 

  
    var userdata=sessionStorage.getItem("userdetails")
    // let user= JSON.parse(userdata)
    console.log(userdata);
    console.log(typeof(userdata));
    if (userdata){
     this.user = JSON.parse(userdata);
     let pic=document.getElementById('pic') as HTMLImageElement
   if(this.user)  this.userid=this.user.emp_id;
     if(this.user && pic){
      this.userid=this.user.emp_id
      pic.src="http://127.0.0.1:8000"+this.user.emp_pic
      console.log(pic.src);
      

     }
    }
    this.complaintform= new FormGroup({
      facility_id : new FormControl(),
      emp_id :  new FormControl(this.userid),
      device_id: new FormControl(),
      comp_desc :new FormControl()

    })
  
    this.devices=1
    this.service.getfacilities().subscribe(data => {this.facilities=data,console.log(data);
    })
    console.log(this.facilities);
    console.log(this.facilities);

    
    
   
  }
  displaycomplaint(){
    let ele= document.getElementById("complaintdiv") as HTMLDivElement
    ele.style.display="block"
  }
  onSubmit(formdata:any ){
    console.log(formdata);

    
  }
  submitcomplaint(){
    if(this.complaintform){
    console.log(this.complaintform.value);
    let  data=JSON.stringify(this.complaintform.value)
    this.service.postComplaint(data).subscribe(data=>console.log(data)
    )
      console.log(data);
      
    }
    
  }

  ngOnInit(): void {
  }

}
