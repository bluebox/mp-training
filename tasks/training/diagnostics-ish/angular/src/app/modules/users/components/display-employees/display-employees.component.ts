import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

// table
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatAccordion } from '@angular/material/expansion';
import { StaffServiceService } from 'src/app/services/staff-service/staff-service.service';

export interface CustomersData {
  staff_id: string,
  username: string
}

@Component({
  selector: 'app-display-employees',
  templateUrl: './display-employees.component.html',
  styleUrls: ['./display-employees.component.css']
})
export class DisplayEmployeesComponent implements OnInit {
  employees :any
  dataSource: MatTableDataSource<CustomersData>;
  displayedColumns: string[] = [
    'staff id',
    'username',
    'designation',
    'view'
  ];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(private http : StaffServiceService ) { 
    this.dataSource = new MatTableDataSource(this.employees);
  }

  getEmployees(){
    this.http.getEmployees().subscribe({
      next:(data:any)=>{
        this.employees = JSON.parse(data.staffs)
        this.dataSource.data = this.employees
        console.log(data);
        
      },
      error: (err) => {
        alert(err.error.details)
      }

    })
    
  }
  ngOnInit(): void {
    this.getEmployees()
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  // onSearchTextEntered(searchValue: string){
  //   // console.log(searchValue);
  //   if (searchValue.length >0) {
  //     this.searchText = searchValue
  //     this.http.getSearchedCustomers(this.searchText).subscribe({
  //       next: (data: any) => {
  //         this.searchedCustomers = data.customers;
  //         this.searchedCustomers = JSON.parse(this.searchedCustomers);
  //         this.dataSource.data = this.searchedCustomers;
  //       },
  //       error: (err) => {
  //         console.log(err);
  //       },
  //     });
  //   }
  //   else if (searchValue.length == 0) {
  //     this.dataSource.data = this.customers;
  //   }
    
  // }
}
