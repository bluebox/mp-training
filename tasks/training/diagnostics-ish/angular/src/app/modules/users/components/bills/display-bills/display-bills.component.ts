import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatAccordion } from '@angular/material/expansion';

import { BillsService } from 'src/app/services/bills-service/bills.service';
import { CloseDialogComponent } from '../../close-dialog/close-dialog.component';

export interface BillData{
  bill_id: string;
  appointment_id: string;
  customer_name: string;
  consultation_fee: string;
  test_fee: string;
  tax: string;
  total: string;
}

@Component({
  selector: 'app-display-bills',
  templateUrl: './display-bills.component.html',
  styleUrls: ['./display-bills.component.css']
})
export class DisplayBillsComponent implements AfterViewInit,OnInit {

  bills: any;
  dataSource: MatTableDataSource<BillData>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(private bills_service: BillsService, public dialog: MatDialog) {
    this.dataSource = new MatTableDataSource(this.bills);
    
  }
  getAllBills(){
    this.bills_service.getBills().subscribe({
      next: (data: any) => {
        this.bills = data;
        this.bills = JSON.parse(this.bills);
        this.dataSource.data = this.bills;
        console.log(this.dataSource);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  

  displayedColumns: string[] = [
    'bill id',
    'appointment id',
    'customer name',
    'consultation fee',
    'test fee',
    'tax',
    'total',
    'update',
    'delete',
  ];
  ngOnInit(): void {
    this.getAllBills()
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
  openDialog(id:any){
    let dialogRef = this.dialog.open(CloseDialogComponent)

    dialogRef.afterClosed().subscribe(result=>{
      console.log(result);
      if(result == 'true'){
        this.bills_service.deleteBill(parseInt(id)).subscribe({
          next : (res)=>{
            console.log(res);
            this.getAllBills()
          }
        })
      }
    })
  }
}
