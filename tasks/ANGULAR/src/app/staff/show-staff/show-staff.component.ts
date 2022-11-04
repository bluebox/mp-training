import { Component, Input, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-show-staff',
  templateUrl: './show-staff.component.html',
  styleUrls: ['./show-staff.component.css']
})
export class ShowStaffComponent implements OnInit {

  constructor(private service : SharedService) { }
  @Input() staf:any;
  staff_id:number|undefined;
  staff_designation:string="";
  staff_name:string="";
  staff_phone:number|undefined;
  airport_id:number|undefined;
  airlines_name:string=""
  ngOnInit(): void {
    this.staff_id=this.staf.staff_id;
    this.staff_designation=this.staf.staff_designation;
    this.staff_name=this.staf.staff_name;
    this.staff_phone=this.staf.staff_phone;
    this.airport_id=this.staf.airport_id;
    this.airlines_name=this.staf.airlines_name
  }


  adddemo(){
    var val={
      staff_id:this.staf.staff_id,
      staff_designation:this.staf.staff_designation,
      staff_name:this.staf.staff_name,
      staff_phone:this.staf.staff_phone,
      airport_id:this.staf.airport_id,
      airlines_name:this.staf.airlines_name};

      this.service.addstaff(val).subscribe(res=>{
        alert(res.toString());
      });
    }

      updatedemo(){
        var val={
          staff_id:this.staf.staff_id,
      staff_designation:this.staf.staff_designation,
      staff_name:this.staf.staff_name,
      staff_phone:this.staf.staff_phone,
      airport_id:this.staf.airport_id,
      airlines_name:this.staf.airlines_name};


          this.service.updatestaff(val).subscribe(res=>{
            alert(res.toString());
          });

        }

      }

