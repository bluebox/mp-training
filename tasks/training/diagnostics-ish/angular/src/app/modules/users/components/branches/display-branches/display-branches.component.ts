import { Component, OnInit, AfterViewInit,ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CloseDialogComponent } from '../../close-dialog/close-dialog.component';
import { BranchesService } from 'src/app/services/branches-service/branches.service';
import { Router } from '@angular/router';

export interface BranchData{
  branch_id:string,
  branch_name : string,
  location : string
}


@Component({
  selector: 'app-display-branches',
  templateUrl: './display-branches.component.html',
  styleUrls: ['./display-branches.component.css']
})
export class DisplayBranchesComponent implements AfterViewInit,OnInit {
  branches: any
  dataSource: MatTableDataSource<BranchData>;
  displayedColumns: string[] = [
    "branch id",
    "branch name",
    "location",
    'update',
    'delete'
  ];
  searchText !: string
  searchedBranches : any


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor( private router : Router,public dialog: MatDialog , private branchService : BranchesService) { 
    this.dataSource = new MatTableDataSource(this.branches);
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

  ngOnInit(): void {
    this.getBranches()
  }

  // UDF
  getBranches(){
    this.branchService.getBranches().subscribe({
      next:(data:any)=>{
        this.branches = data
        this.dataSource.data = this.branches
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  updateBranch(branch_id:string){
    this.router.navigate(['admin/update-branch', branch_id])
  }

  openDialog(id: any) {
    let dialogRef = this.dialog.open(CloseDialogComponent)

    dialogRef.afterClosed().subscribe(result => {
      if (result == 'true') {
        this.branchService.deleteBranch(id).subscribe({
          next: (res) => {
            console.log(res);
            this.getBranches()
          }
        })
      }
    })
  }
  onSearchTextEntered(searchValue: string) {
    // console.log(searchValue);
    if (searchValue.length > 0) {
      this.searchText = searchValue
      this.branchService.getSearchedBranches(this.searchText).subscribe({
        next: (data: any) => {
          this.searchedBranches = data.branches;
          this.dataSource.data = this.searchedBranches;
        },
        error: (err:any) => {
          console.log(err);
        },
      });
    }
    else if (searchValue.length == 0) {
      this.dataSource.data = this.branches

  }


}

}