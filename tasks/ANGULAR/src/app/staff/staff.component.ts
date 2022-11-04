import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {
  staffList:any=[]
  modaltitle:string='';
  Activeaddcom:boolean=false;
  flag:boolean=false;
  staf:any=[];
  constructor(private service:SharedService,private route:Router) { }

  ngOnInit(): void {
    this.refreshstaffList()
  }

  addclick(){
    this.staf={
      staff_id:0,
      staff_designation:"",
      staff_name:"",
      staff_phone:0,
      airport_id:0,
      airlines_name:""

    };
   this.modaltitle="Add staff";
   this.Activeaddcom=true;
  }
  closeclick()
 {
   this.Activeaddcom=false;
   this.refreshstaffList();
 }

 editclick(val:any)
 {
  this.staf=val;
  this.Activeaddcom=true;
 }

 deleteclick(item:any)
 {
   if(confirm('are you sure??'))
   {
   this.service.deletetstaff(item.staff_id).subscribe(data=>{
      alert(data.toString());
      this.refreshstaffList();

     });


   }
 }

 refreshstaffList(){
  this.service.getstafflist().subscribe(data=>{
   this.staffList=data;});

  }
onsubmit()
{
  this.route.navigate(['staff-shifts']);
}

}
