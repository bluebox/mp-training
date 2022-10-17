import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-editservice',
  templateUrl: './editservice.component.html',
  styleUrls: ['./editservice.component.css']
})
export class EditserviceComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  id:any;

  errorMessage : string = ""
  formNotValid : boolean = false;
  constructor(private http:HttpserviceService,private router:ActivatedRoute,private route:Router) { }
  editServiceForm : FormGroup= new FormGroup({
    service_id : new FormControl("",Validators.required),
    service_name : new FormControl("",[Validators.required,Validators.maxLength(50)]),
    Amount_to_be_paid : new FormControl("",Validators.required)
  })
  ngOnInit(): void {
     console.log(this.router.snapshot.params.id);
     this.http.getCurrentService(this.router.snapshot.params.id).subscribe((result:any) => {
      this.editServiceForm = new FormGroup({
        service_id : new FormControl(result['service_id'],Validators.required),
        service_name : new FormControl(result['service_name'],[Validators.required,Validators.maxLength(50)]),
        Amount_to_be_paid : new FormControl(result['Amount_to_be_paid'],Validators.required)
      })
     });
  }



  updateService(){
    console.log(this.editServiceForm.value)
    this.http.updateServices(this.router.snapshot.params.id,this.editServiceForm.value).subscribe((result)=>{
      console.log(result);alert("updated successfully");this.route.navigate(['admin/services'])
    })

  }

  cancel(){
    this.route.navigate(['admin/services']);
  }
}
