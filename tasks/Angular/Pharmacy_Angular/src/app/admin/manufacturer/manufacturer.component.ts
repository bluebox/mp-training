import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {AddManComponent} from './add-man/add-man.component';
import {ManufacturerService} from 'src/app/services/manufacturer.service';


@Component({
  selector: 'app-manufacturer',
  templateUrl: './manufacturer.component.html',
  styleUrls: ['./manufacturer.component.css']
})
export class ManufacturerComponent implements OnInit {
  title = 'manufacturer';
  displayedColumns: string[] = ['company_id', 'company_name', 'contact', 'email', 'address','action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private dialog:MatDialog, private api : ManufacturerService){

  }
 
  ngOnInit(): void{
    this.getAllManufacturer();
  }

  openDialog() {
    this.dialog.open(AddManComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllManufacturer();
      }

    })
  }

  getAllManufacturer(){
    this.api.getManufacturer()
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

  editManufacturer(row:any){
    this.dialog.open(AddManComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllManufacturer();
      }
    })
  }

  deleteManufacturer(id:string){
    this.api.deleteManufacturer(id)
    .subscribe({
      next:(res)=>{
        alert("Manufacturer Deleted Successfully!")
        this.getAllManufacturer();
      },
      error:()=>{
        alert("Error While Deleting the manufcaturer!")
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

