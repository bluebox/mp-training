import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  employeeList: any;
  subscription!: Subscription
  deleteSubscription!: Subscription


  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.employeeList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.employeeList.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }

  ngOnInit(): void {
    this.dataService.getEmployeeList().subscribe(
      data => {
        this.employeeList = data;
        this.length = this.employeeList.length
        this.totalPages = Math.ceil(this.length/this.pageSize)
        this.pageItems = this.employeeList.slice(0, this.pageSize)
      },
      err => alert(err.error.detail)
    )

  }

  editEmployee(id: number) {
    this.route.navigate(['admin/employees/addEmployee', id])
  }

  deleteEmployee(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteEmployee(id).subscribe(
        data=>alert('Employee deleted successfully')
      )
    }
  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
    if(this.deleteSubscription){
      this.deleteSubscription.unsubscribe()
    }
  }

}
