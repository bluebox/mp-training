import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Facility } from 'src/interfaces/facility';
import { Sports } from 'src/interfaces/sport';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-particularfacility',
  templateUrl: './particularfacility.component.html',
  styleUrls: ['./particularfacility.component.css']
})
export class ParticularfacilityComponent implements OnInit {
  id : string = ''
  facility !: Facility;
  sports : Sports[]=[];
  
  constructor(private router:Router, private arouter:ActivatedRoute, private facilityService: FacilityService) { 
  }

  ngOnInit(): void {
    this.arouter.params.subscribe(data => {this.id = data['id']
    console.log(data)

    this.facilityService.getFacility(this.id).subscribe(data => this.facility = data);

    this.facilityService.getSportsInFacility(this.id).subscribe(data => this.sports = data);
    
  })
  }
  openBooking(id: number): void {
    this.router.navigate(['facilities/booking',String(id)])

  }

}


