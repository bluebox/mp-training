import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-staff-shifts',
  templateUrl: './staff-shifts.component.html',
  styleUrls: ['./staff-shifts.component.css']
})
export class StaffShiftsComponent implements OnInit {

  constructor(private service:SharedService) { }
  staff_shifts_list:any;
  ngOnInit(): void {
    this.refrestaffshiftslist()
  }






  refrestaffshiftslist()
  {


    this.service.getstaff_shifts_list().subscribe(data=>
      {
        this.staff_shifts_list=data
        console.log(data);

      });
  }







}
