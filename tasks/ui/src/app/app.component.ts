import { Component, OnInit } from '@angular/core';
import { ServicesService } from './services.service';
import { Facility, Complaint, Employee } from './Facility';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  [x: string]: any;
  title = 'facilityui';

  data: Facility | undefined;
  name1 :string ='';
  user:Employee | undefined;
  emitteddata(str:string){
    console.log(str);
    this.name1=str;
    
  }

  constructor(private service: ServicesService) {
    var userdata=sessionStorage.getItem("userdetails")
    // let user= JSON.parse(userdata)
    console.log(userdata);
    console.log(typeof(userdata));
 
  }

  








  fun1() {

    let ele = document.getElementById("getemp_id") as HTMLInputElement;
    let id = ele.value;
     this.service.getFacility(Number(id)).subscribe(data=>{this.data=data;console.log(this.data);
     })


    console.log(this.data);
    
  }


  // const locationsSubscription = locations.subscribe({
  //   next(position) {
  //     console.log('Current Position: ', position);
  //   },
  //   error(msg) {
  //     console.log('Error Getting Location: ', msg);
  //   }
  // });
  
  // console.log(this.service.getFacility(Number(id)).subscribe((data)=>this.data=data));



  createcomplaint() {
    let complaintdiv = document.getElementById("complaintdiv")
    if (complaintdiv != null) {
      complaintdiv.style.display = 'block';

    }
  }

  raisecomplaint() {
    let emp_id = document.getElementById("emp_id") as HTMLInputElement;
    let device_id = document.getElementById("device_id") as HTMLInputElement;
    let descp = document.getElementById('descp') as HTMLInputElement;
    console.log(device_id.value + emp_id.value)
    // let complaint: Complaint | undefined;
    // complaint = {
    //   "emp_id": Number(emp_id.value),
    //   "device_id": Number(device_id.value),
    //   "comp_desc": descp.value
    // }
    // // console.log(complaint);
    
    // console.log(emp_id.value);



    // this.service.postComplaint(complaint)
  }


  ngOnInit(): void {

  }
}
