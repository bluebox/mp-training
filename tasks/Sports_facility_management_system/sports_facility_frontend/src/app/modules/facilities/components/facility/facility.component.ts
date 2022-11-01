import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facility } from 'src/interfaces/facility';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css'],
})
export class FacilityComponent implements OnInit {
  facilities: Facility[] = [];
  facility!: Facility;
  sports: any;
  text: string = '';
  selectedSportsList: any[] = [];
  cd: any;
  no_of_facilities_display: number =9;
  total_results!:number;

  constructor(
    private facilityService: FacilityService,
    private router: Router
  ) {}
 
  ngOnInit(): void {
    
    this.getAllSports();
    this.getSearchedFacilities()
  }
  allChecked = true;

  getrating(fid: any) {
    l
    return this.facilityService.getRatingsOfFacilities(fid).subscribe((data:any)=>

     {return data.ratings__avg} 
    )
  }
 

  openFacility(id: number): void {
    this.router.navigate(['facilities/hyderabad', String(id)]);
  }

  search() {
    this.no_of_facilities_display =9;
    this.getSearchedFacilities();
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
    this.no_of_facilities_display =9;
    if (this.selectedSportsList.includes(id)) {
      this.selectedSportsList.splice(this.selectedSportsList.indexOf(id), 1);
    } else {
      this.selectedSportsList.push(id);
    }
    console.log(this.selectedSportsList);
    this.getSearchedFacilities();
  }

  getSearchedFacilities(): void {
    this.facilityService
      .searchFacilityLoadmore(this.text, this.selectedSportsList.toString(),this.no_of_facilities_display)
      .subscribe((data:any) => {
        this.facilities = data.pageItems
        this.total_results=data.total_results  
        console.log(data)  
      });
  }
  allfilter(): void{
    this.allChecked = true;
    this.selectedSportsList = [];
    this.no_of_facilities_display =9;
    this.getSearchedFacilities();



  }
  loadmore(){
    this.no_of_facilities_display += 9
    this.getSearchedFacilities()
  }
}
