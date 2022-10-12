import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Facility } from 'src/interfaces/facility';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-particularsport',
  templateUrl: './particularsport.component.html',
  styleUrls: ['./particularsport.component.css']
})
export class ParticularsportComponent implements OnInit {
  facilities: any;
  sport:any;
  sid:any;
  constructor(private router: Router,private arouter: ActivatedRoute,private facilityService: FacilityService) { }

  ngOnInit(): void {
    
    this.arouter.params.subscribe(data => {
      this.sid = data['id']
      console.log(data)
      this.facilityService.getFacilitiescontainSport(this.sid).subscribe(data => {
        this.sport=data,this.facilities=this.sport[0].facility})
  
    })
    
  
  }
  openFacility(id: number): void {
    this.router.navigate(['facilities/hyderabad',String(id)])

  }



}
