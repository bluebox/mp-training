import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDatepicker} from '@angular/material/datepicker';
import {DrugService} from 'src/app/services/drug.service';
import { AddDrugComponent } from './add-drug/add-drug.component';




@Component({
  selector: 'app-drug',
  templateUrl: './drug.component.html',
  styleUrls: ['./drug.component.css']
})
export class DrugComponent implements OnInit {

  title = 'drug'
  displayedColumns: string[] = ['drug_id', 'drug_name', 'dist_name', 'company_name', 'mrp', 'discount', 'stock', 'mfg_date', 'action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private dialog:MatDialog, private api : DrugService){

  }
 
  ngOnInit(): void{
    this.getAllDrug();
  }

  openDialog() {
    this.dialog.open(AddDrugComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllDrug();
      }

    })
  }

  getAllDrug(){
    this.api.getDrug()
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

  editDrug(row:any){
    this.dialog.open(AddDrugComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllDrug();
      }
    })
  }

  deleteDrug(id:string){
    this.api.deleteDrug(id)
    .subscribe({
      next:(res)=>{
        alert("Drug Deleted Successfully!")
        this.getAllDrug();
      },
      error:()=>{
        alert("Error While Deleting!")
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