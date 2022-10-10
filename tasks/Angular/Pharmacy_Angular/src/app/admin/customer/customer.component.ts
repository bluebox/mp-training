import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {CustomerService} from "src/app/services/customer.service"
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {AddCusComponent} from 'src/app/admin/customer/add-cus/add-cus.component';





@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  title = 'Customer';
  displayedColumns: string[] = ['cust_id', 'first_name','last_name','contact', 'email', 'address','action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private api : CustomerService, private dialog:MatDialog,) { }

  ngOnInit(): void {
    this.getAllCustomer();
  }

  openDialog() {
    this.dialog.open(AddCusComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllCustomer();
      }

    })
  }

  getAllCustomer(){
    this.api.getCustomer()
    .subscribe({
      next:(res)=>{
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error:(err)=>{
        alert("Error Occured")
      }
    })
  }

  editCustomer(row:any){
    this.dialog.open(AddCusComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllCustomer();
      }
    })
  }

  deleteCustomer(id:string){
    this.api.deleteCustomer(id)
    .subscribe({
      next:(res)=>{
        alert("Customer Deleted Successfully!")
        this.getAllCustomer();
      },
      error:()=>{
        alert("Error While Deleting the Product!")
      }
    })
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}


