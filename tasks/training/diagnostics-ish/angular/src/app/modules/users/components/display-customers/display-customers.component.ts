import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { HttpService } from 'src/app/services/http-service/http.service';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatAccordion } from '@angular/material/expansion';

export interface CustomersData{
  customer_id:string,
  username:string
}

@Component({
  selector: 'app-display-customers',
  templateUrl: './display-customers.component.html',
  styleUrls: ['./display-customers.component.css']
})
export class DisplayCustomersComponent implements OnInit {
  customers:any
  dataSource: MatTableDataSource<CustomersData>;
  displayedColumns: string[] = [
    'customer id',
    'username',
    'view'
  ];
  searchText !: string
  searchedCustomers: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(private http: HttpService, public dialog: MatDialog) {
    this.dataSource = new MatTableDataSource(this.customers);
   }

   getCustomers(){
    this.http.getCustomers().subscribe({
      next:(data:any)=>{
        this.customers = JSON.parse(data.customers)
        this.dataSource.data = this.customers
      },
      error:(err)=>{
        alert(err.error.details)
      }
    })
   }

  ngOnInit(): void {
    this.getCustomers()
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

  // search
  onSearchTextEntered(searchValue: string){
    // console.log(searchValue);
    if (searchValue.length >0) {
      this.searchText = searchValue
      this.http.getSearchedCustomers(this.searchText).subscribe({
        next: (data: any) => {
          this.searchedCustomers = data.customers;
          this.searchedCustomers = JSON.parse(this.searchedCustomers);
          this.dataSource.data = this.searchedCustomers;
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    else if (searchValue.length == 0) {
      this.dataSource.data = this.customers;
    }
    
  }
}
