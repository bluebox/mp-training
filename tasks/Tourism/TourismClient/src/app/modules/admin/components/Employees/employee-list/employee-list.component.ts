import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });

  constructor(private dataService: DataService,
    private route: Router
    ) { }
  employeeList: any;
  subscription!: Subscription
  deleteSubscription!: Subscription


  page: number = 1;
  pageItems : any;
  totalPages : any

  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getEmployeeList(this.page + num, searchText).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
      },
      err => {
        if(err.status == 404){
          alert(err.message)
        }
        else{
          alert(err.error.detail)
        }
      }
    )
  }

  ngOnInit(): void {
    this.getPageItems(0)
  }


  editEmployee(id: number) {
    this.route.navigate(['admin/employees/addEmployee', id])
  }

  deleteEmployee(id: number){
    if(confirm("Place will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteEmployee(id).subscribe(
        data=>alert('Employee deleted successfully'),
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
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
