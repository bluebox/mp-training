import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  constructor(private service:SharedService) { }
  scheduleList:any;
  scheduleform:any=FormGroup;
  ngOnInit(): void {

    this.scheduleform = new FormGroup({
      departure_time: new FormControl(''),
      arrival_time: new FormControl(''),

    });
    this.scheduleform.get('departure_time').patchValue(this.formatDate(new Date()));
    this.scheduleform.get('arrival_time').patchValue(this.formatDate(new Date()));
  }
  private formatDate(date: string | number | Date) {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    return [year, month, day].join('-');
  }


  onsubmit(val:any)
  {
    console.log(val);

     this.service.getschedule(val).subscribe(data=>
      {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        this.scheduleList=data
      });
     console.log(this.scheduleList);

  }






}
