import { Component, Inject, Input, NgIterable, OnInit } from '@angular/core';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AddDisComponent} from 'src/app/admin/distributor/add-dis/add-dis.component';
import {SearchComponent} from 'src/app/admin/distributor/search/search.component';
import {DistributorService} from "src/app/services/distributor.service"
 

interface Dist{
  dist_id:string;
  dist_name:string;
  contact:number;
  email:string;
  address:string;
}


@Component({
  selector: 'app-distributor',
  templateUrl: './distributor.component.html',
  styleUrls: ['./distributor.component.css']
})
export class DistributorComponent implements OnInit {
  title = 'distributor';
  displayedColumns: string[] = ['dist_id', 'dist_name', 'contact', 'email', 'address','action'];

  dataSource !: MatTableDataSource<any>;
  
  result : any;


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
        this.result = res;
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


  onSearchText(searchValue: string) {
    console.log(searchValue);
    this.api.getADist(searchValue).subscribe({
      next:(res)=>{
        this.result= res;
        console.log(this.result)
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


