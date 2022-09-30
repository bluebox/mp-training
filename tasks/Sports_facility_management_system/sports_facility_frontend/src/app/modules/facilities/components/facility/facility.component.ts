import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facility } from 'src/interfaces/facility';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css']
})
export class FacilityComponent implements OnInit {
  facilities: Facility[]=[];
  facility!: Facility;

  constructor(private facilityService: FacilityService,private router: Router) { }

  ngOnInit(): void {
    this.facilityService.getFacilities().subscribe(data => this.facilities = data);

  }

  openFacility(id: number): void {
    this.router.navigate(['facilities/hyderabad',String(id)])

  }

}
