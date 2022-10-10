import { Component, Inject, Input, OnInit } from '@angular/core';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AddDisComponent} from 'src/app/admin/distributor/add-dis/add-dis.component';
import {DistributorService} from "src/app/services/distributor.service"
 


@Component({
  selector: 'app-distributor',
  templateUrl: './distributor.component.html',
  styleUrls: ['./distributor.component.css']
})
export class DistributorComponent implements OnInit {
  title = 'distributor';
  displayedColumns: string[] = ['dist_id', 'dist_name', 'contact', 'email', 'address','action'];

  dataSource !: MatTableDataSource<any>;
  
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
  constructor(private api:DistributorService, private dialog:MatDialog){

  }
 
  ngOnInit(): void{
    this.getAllDistributor();
  }

  openDialog() {
    this.dialog.open(AddDisComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllDistributor();
      }

    })
  }

  getAllDistributor(){
    this.api.getDistributor()
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

  editDistributor(row:any){
    this.dialog.open(AddDisComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllDistributor();
      }
    })
  }

  deleteDistributor(id:string){
    this.api.deleteDistributor(id)
    .subscribe({
      next:(res)=>{
        alert("Distributor Deleted Successfully!")
        this.getAllDistributor();
      },
      error:()=>{
        alert("Error While Deleting the Distributor!")
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


