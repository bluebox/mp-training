import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CloseDialogComponent } from '../close-dialog/close-dialog.component';
import { EmployeeServiceService } from 'src/app/services/employee-service/employee-service.service';


@Component({
  selector: 'app-display-employee',
  templateUrl: './display-employee.component.html',
  styleUrls: ['./display-employee.component.css']
})
export class DisplayEmployeeComponent implements OnInit {
  employee_id : string =''
  employeeDetails : any
  UserDetails : any

  getEmployeeMethod(){
    this.http.getEmployee(this.employee_id).subscribe({
      next: (data: any) => {
        this.employeeDetails = data["employee_details"]
        this.UserDetails = data["user_details"]
        console.log(data);
        
      },
      error: (err) => {
        console.log(err.error.detail);
      }
    })
  }
  constructor(private router : Router,private actRouter: ActivatedRoute, private http: EmployeeServiceService, public dialog: MatDialog) {
    this.actRouter.params.subscribe(data => {
      this.employee_id = data['employee_id']
    })
   }

  ngOnInit(): void {
    this.getEmployeeMethod()
  }

  openDialog(id: any) {
    let dialogRef = this.dialog.open(CloseDialogComponent)

    dialogRef.afterClosed().subscribe(result => {
      if (result == 'true') {
        this.http.deletEemployee(id).subscribe({
          next: (res) => {
            console.log(res);
            this.router.navigate(['admin/dislplay-employees'])
          }
        })
      }
    })
  }
  updateEmployee(employee_id: string) {
    this.router.navigate(['admin/update-employee/', employee_id])
  }
}
