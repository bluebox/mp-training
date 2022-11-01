import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FacilityService } from 'src/app/modules/facilities/services/facility.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-viewfacilities',
  templateUrl: './viewfacilities.component.html',
  styleUrls: ['./viewfacilities.component.css'],
})
export class ViewfacilitiesComponent implements OnInit {
  facilities: any;
  AllSlots:any;
  displayedColumns: string[] = ['facility_name', 'facility_email', 'facility_location','addsport','deletefacility'];
  constructor(private service: AdminService,private router: Router,private facilityService: FacilityService) {}

  ngOnInit(): void {
    
    this.getSearchedFacilities(0)
    this.getAllSports()
   
  }
  page: number = 1;
  pageItems : any;
  totalPages : any
  sports:any
  selectedSportsList:any[]=[];
  text:string='';
  allChecked:boolean = false;
  search() {
    this.getSearchedFacilities(0);
  }

  getAllSports() {
    this.facilityService.getSports().subscribe((data) => {
      this.sports = data;
      console.log(data);
    });
  }

  checkSport(id: any) {
    return this.selectedSportsList.includes(id);
  }
  sportid(id: any) {
    if (this.selectedSportsList.includes(id)) {
      this.selectedSportsList.splice(this.selectedSportsList.indexOf(id), 1);
    } else {
      this.selectedSportsList.push(id);
    }
    console.log(this.selectedSportsList);
    this.getSearchedFacilities(0);
  }

  getSearchedFacilities(num:any): void {
    this.facilityService
      .getFacilitiescontainSport(this.text, this.selectedSportsList.toString(),this.page + num)
      .subscribe((data:any) => {
        console.log(data)
        this.facilities = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
        
      });
  }
  allfilter(): void{
    this.allChecked = true;
    this.selectedSportsList = [];
    this.getSearchedFacilities(0);


  }

  deleteFacility(fid: any): void {
    console.log(fid)
    this.service.deleteFacility(fid).subscribe(
      (data) => {
        console.log(data);
        this.getSearchedFacilities(0)
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
