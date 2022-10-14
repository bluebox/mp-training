import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-viewfacilities',
  templateUrl: './viewfacilities.component.html',
  styleUrls: ['./viewfacilities.component.css'],
})
export class ViewfacilitiesComponent implements OnInit {
  facilities: any;
  AllSlots:any
  constructor(private service: AdminService,private router: Router) {}

  ngOnInit(): void {
    
    this.getFacilities()
   
  }
  getFacilities(): any {
    this.service.getFacilities().subscribe((data) => {
      this.facilities = data;
      console.log(this.facilities);
    });

  }


  deleteFacility(fid: any): void {
    console.log(fid)
    this.service.deleteFacility(fid).subscribe(
      (data) => {
        console.log(data);
        this.getFacilities()
      },
      (error) => {
        console.log(error);
      }
    );
  }
  addSports(fid:any): void {
    this.router.navigate(['admin/home/addsports',fid])
  }
  
}
