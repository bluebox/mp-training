import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-emlployee',
  templateUrl: './add-emlployee.component.html',
  styleUrls: ['./add-emlployee.component.css']
})
export class AddEmlployeeComponent implements OnInit {

  routeSubscription!: Subscription;
  getEmployeeSubscription!: Subscription;
  editEmployeeSubscription!: Subscription;
  addEmployeeSubscription!: Subscription;
  imagesSubscription!: Subscription;
  imageUrl!: string | null



  constructor(private dataservice: DataService,
    private dataService: DataServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getEmployeeSubscription = this.dataservice.getEmployee(parseInt(res['id'])).subscribe(
          data=> {
          let employeeString = JSON.stringify(data)
          let employeeObj = JSON.parse(employeeString)
          this.EmployeeForm.get('name')?.setValue(employeeObj.name);
          this.EmployeeForm.get('email')?.setValue(employeeObj.email);
          this.EmployeeForm.get('mobile')?.setValue(employeeObj.mobile);
          this.EmployeeForm.get('image')?.setValue(employeeObj.image);
          this.imageUrl = employeeObj.image;
          this.EmployeeForm.get('address')?.setValue(employeeObj.address);
          this.EmployeeForm.get('salary')?.setValue(employeeObj.salary);
        },
        err => alert(err.error.detail)
      );
      }
    })
  }
  id!: number

  EmployeeForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    mobile : new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}')]),
    image : new FormControl(''),
    address : new FormControl('', [Validators.required]),
    salary : new FormControl('', [Validators.required]),
  })

  get formObj(){
    return this.EmployeeForm.controls
  }

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(data => {
      let dataString = JSON.stringify(data)
      this.imageUrl = JSON.parse(dataString)
    })
  }


  ngOnInit(): void {
  }

  addEmployeeObj() {
    if(this.EmployeeForm.valid){
      let employeeObj = {...this.EmployeeForm.value, image:this.imageUrl}
      if(this.id){
        this.editEmployeeSubscription = this.dataservice.editEmployee(employeeObj, this.id).subscribe(
          data=>{
          console.log(data)
          alert("employee profile updated successfully")
          this.router.navigate(['admin/employees/employeeList'])
        },
        err => alert(err.error.detail)
      )
      }else{
        this.addEmployeeSubscription = this.dataservice.addEmployee(employeeObj).subscribe(
          data=>{
          console.log(data)
          alert("employee profile added successfully")
          this.router.navigate(['admin/employees/employeeList'])
        },
        err => alert(err.error.detail)
      )
      }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getEmployeeSubscription){
      this.getEmployeeSubscription.unsubscribe()
    }
    if(this.editEmployeeSubscription){
      this.editEmployeeSubscription.unsubscribe()
    }
    if(this.addEmployeeSubscription){
      this.addEmployeeSubscription.unsubscribe()
    }
  }

}
