import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {AddDocComponent} from './add-doc/add-doc.component';
import {DoctorService} from 'src/app/services/doctor.service';


@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})

export class DoctorComponent implements OnInit {

  title = 'doctor';
  displayedColumns: string[] = ['doc_id', 'first_name','last_name', 'specialization', 'contact', 'email', 'address','action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private dialog:MatDialog, private api : DoctorService){

  }
  ngOnInit(): void{
    this.getAllDoctor();
  }

  openDialog() {
    this.dialog.open(AddDocComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllDoctor();
      }

    })
  }

  getAllDoctor(){
    this.api.getDoctor()
    .subscribe({
      next:(res)=>{
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error:()=>{
        alert("Error Occured")
      }
    })
  }

  editDoctor(row:any){
    this.dialog.open(AddDocComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllDoctor();
      }
    })
  }

  deleteDoctor(id:string){
    this.api.deleteDoctor(id)
    .subscribe({
      next:(res)=>{
        alert("Doctor Deleted Successfully!")
        this.getAllDoctor();
      },
      error:()=>{
        alert("Error While Deleting the Doctor!")
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