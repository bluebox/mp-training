import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { ServercomunicationService } from '../servercomunication.service';
export interface Task {
  name: string;
  completed: boolean;
  color: ThemePalette;
  subtasks?: Task[];

}
@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.css']
})
export class SlotsComponent implements OnInit {
[x: string]: any;

getDate(arg0: any) {
throw new Error('Method not implemented.');
}
getslotid(arg0: any) {
throw new Error('Method not implemented.');
}
slot: any;
submitBookingForm() {
throw new Error('Method not implemented.');
}

  id: string = ''; //facilityid
  idd!: number;
  form_appointment: FormGroup = new FormGroup({});
  doctor: any;
  doctors:any=[
    {name: "ayan", id:"1995"},
    {name: "ayan1", id:"1996"},
    {name: "ayan2", id:"1997"},
    {name: "ayan3", id:"1998"},
    {name: "ayan4", id:"1999"}
  ]
  slo:any=[
    {name: '10:30 am-11:00 am', completed: false, color: 'primary'},
    {name: '11:00 am-11:30am', completed: false, color: 'primary'},
    {name: '11:30 am-12:00pm', completed: false, color: 'primary'},
  ]
  // equipments!: any[];
  // sportid!: number;
  date: string = '';
  fsid!: number;
  datesarray: any[] = [{"date":"12/2/22"},{"date":"2/2/22"}];
  // datesarray: any[] = [];
  // slots: Slots[] = [];
  // bookeslots: Slots[] = [];
  bookeslots_ids: number[] = [];
  slots_id: number[] = [];
  // equipments_booked: any[] = [];
  // equipments_quantity: any[] = [];
  // bookingForm!: FormGroup;
  // user_id: number = 1;

  // bookingmsg: any;
  // fsdetails: any;
  // cost_per_slot: any;
  // total_cost_for_slots!: number;
  // total_cost_for_equipments: number = 0;
  // token_status: any;
  // user_verfied: boolean = false;
  constructor(private fb: FormBuilder,private api:ServercomunicationService) { }

  ngOnInit(): void {
    this['get_Doc_Data_by_Id'](this.id);

  }
  task: Task = {
    name: 'Morning Shifts',
    completed: false,
    color: 'warn',
    subtasks: [
      {name: '10:30 am-11:00 am', completed: false, color: 'primary'},
      {name: '11:00 am-11:30am', completed: false, color: 'primary'},
      {name: '11:30 am-12:00pm', completed: false, color: 'primary'},
    ],
  };

  allComplete: boolean = false;

  updateAllComplete() {
    this.allComplete = this.task.subtasks != null && this.task.subtasks.every(t => t.completed);
  }

  someComplete(): boolean {
    if (this.task.subtasks == null) {
      return false;
    }
    return this.task.subtasks.filter(t => t.completed).length > 0 && !this.allComplete;
  }

  setAll(completed: boolean) {
    this.allComplete = completed;
    if (this.task.subtasks == null) {
      return;
    }
    this.task.subtasks.forEach(t => (t.completed = completed));
  }
  dates(): void {
    for (let i = 0; i < 5; i++) {
      var simpilifiedDate: any[] = [];
      var date = new Date();
      date.setDate(date.getDate() + i);
      const month = date.toLocaleString('default', { month: 'long' });
      const day = date.getDate();
      const year = date.getFullYear();
      simpilifiedDate.push(day + ',' + month +','+ year);
      const weekday = date.toLocaleString('default', { weekday: 'long' });
      simpilifiedDate.push(weekday);
      this.datesarray.push(simpilifiedDate);
    }
  // getDate(date: any): void {
  //     this.date = date;
  //     console.log(this.date);
  //     if (this.sportid) {
  //       this.facilityService
  //         .getSlotsInSportFacility(this.fid, this.sportid)
  //         .subscribe((data) => (this.slots = data));
  //       this.facilityService
  //         .getBookedSlots(this.fsid, this.date)
  //         .subscribe((data) => {
  //           (this.bookeslots = data), this.getSlotids();
  //         });
  //     }
  //   }
//   getADoctorId(id: any): void {
//       this.sportid = id;

//       this.facilityService
//         .getFacilitySportId(this.fid, this.sportid)
//         .subscribe((data) => {
//           this.fsdetails = data;
//           this.fsid = this.fsdetails.facility_sport_id;
//           console.log(this.fsid);
//           this.cost_per_slot = this.fsdetails.cost_per_slot;
//           console.log(this.cost_per_slot);
//         });
//   }
// }
get_Doc_Data_by_Id(this.id)
{
  this.api.getADoctor(this.id).subscribe(
    (data)=>{
      console.log(data);
      this.doctor=data;
      console.log(this.doctor[0].id)
      // this.book.setDocData(data)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
// increasebtn(id: number): void {
//   this.equipments[id].count += 1;
//   this.costForEquipments(id);
//   // this.totalCostForEquipments()
// }
//   costForEquipments(id: number) {
//     throw new Error('Method not implemented.');
//   }

// reducebtn(id: number): void {
//   if (this.equipments[id].count > 0) {
//     this.equipments[id].count -= 1;
//     this.costForEquipments(id);
//     // this.totalCostForEquipments()
//   }
// }
}
}

function get_Doc_Data_by_Id(id: string) {
  throw new Error('Function not implemented.');
}

