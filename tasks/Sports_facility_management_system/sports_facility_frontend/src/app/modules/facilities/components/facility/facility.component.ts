import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import axios from 'axios';
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
  no_of_facilities_display: number = 9;
  total_results!: number;

  constructor(
    private facilityService: FacilityService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllSports();
    this.getSearchedFacilities();
    // let res = this.getrating(1);
    // console.log(JSON.stringify(this.getrating(1)));
    console.log(this.getrating(1));

    
  }
  allChecked = true;
  
  // getrating(fid: any) {
  //   let rating;
  //   return this.facilityService
  //     .getRatingsOfFacilities(fid)
  //     .subscribe((data) => {
  //       console.log(data);
  //       return data
  //     });
    
      
  // }
  
  getrating(fid: any) {

    console.log(fid);
    return fid
    
    // let data;
    // try{
    //   let res = await axios.get('http://127.0.0.1:8000/get-ratings-facility?fid='+fid)
    //   console.log(res.data);
    //   data = res.data

    //   let resParse = JSON.parse(res.data)
    //   let resData = JSON.stringify(res.data)
    //   return data;
    // }
    // catch(e){
      
    //   return 0
    // }
  }





  openFacility(id: number): void {
    this.router.navigate(['facilities/hyderabad', String(id)]);
  }

  search() {
    this.no_of_facilities_display = 9;
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
    this.no_of_facilities_display = 9;
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
      .searchFacilityLoadmore(
        this.text,
        this.selectedSportsList.toString(),
        this.no_of_facilities_display
      )
      .subscribe((data: any) => {
        this.facilities = data.pageItems;
        this.total_results = data.total_results;
        console.log(data);
      });
  }
  allfilter(): void {
    this.allChecked = true;
    this.selectedSportsList = [];
    this.no_of_facilities_display = 9;
    this.getSearchedFacilities();
  }
  loadmore() {
    this.no_of_facilities_display += 9;
    this.getSearchedFacilities();
  }
}
