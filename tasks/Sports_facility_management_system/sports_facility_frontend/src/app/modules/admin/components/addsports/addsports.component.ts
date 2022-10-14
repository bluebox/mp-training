import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, FormArray } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from 'src/app/modules/facilities/services/facility.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-addsports',
  templateUrl: './addsports.component.html',
  styleUrls: ['./addsports.component.css'],
})
export class AddsportsComponent implements OnInit {
  fid: any;
  sports: any;
  allSports: any;
  existingsportslist:any[]=[];
  addsportsid:any[]=[];
  AllSlots:any;


  // addsportsform:FormGroup = new FormGroup({
  //   sports_id_list: new FormControl('', [Validators.required]),
  //   cost_for_slot_list: new FormControl('', [Validators.required]),
  // })

  constructor(
    private facilityService: FacilityService,
    private arouter: ActivatedRoute,
    private fb:FormBuilder,
    private service: AdminService
  ) {
    this.addsportsform = this.fb.group({
      facility_id:[''],
      sportObj: this.fb.array([])
    });
  }

  addsportsform!: FormGroup;

  get sportObj() : FormArray {
    return this.addsportsform.get("sportObj") as FormArray
  }

  newSport(): FormGroup {
    return this.fb.group({
      sport_id: [''],
      cost_per_slot:['', Validators.required],
      sport_name:[''],
      slot_name:[''],
      slot_id:['']
    })
  }
 

  ngOnInit(): void {
    this.arouter.params.subscribe((data) => {
      this.fid = data['fid'];
      this.addsportsform.get('facility_id')?.setValue(this.fid)
      console.log(data);
      this.GetSportsInFacilities();
      this.get_all_sports();
      this.getSlots();
      // console.log(this.fid)
    });

    
  }

  GetSportsInFacilities(): void {
    this.facilityService.getSportsInFacility(this.fid).subscribe((data) => {
    
      // let existingsportslist:any[]=[];
      this.existingsportslist = data.map((ele: any) => ele.sport_id)
      this.sports = data;
      console.log(data);
      console.log(this.existingsportslist);
      
      // this.existingsportslist=
    });
  }
  get_all_sports(): void {
    this.facilityService.getSports().subscribe((data) => {
      
      console.log(data);
      this.allSports=data;
    });
  }

  addSportObjToArray(sport:any){
    let newsport = this.newSport()
    newsport.get('sport_id')?.setValue(sport.sport_id)
    newsport.get('sport_name')?.setValue(sport.sport_name)
    console.log(newsport.value);
    this.sportObj.push(newsport);
  }

  removeObj(i:number) {
    this.sportObj.removeAt(i);
  }

  editSports(id: number): void {}
  deletesport(sid: number): void {}

  getsportsid(id: number): void {
    const index = this.addsportsid.indexOf(id);
    if (index !== -1) {
      this.addsportsid.splice(index, 1);
    } else {
      this.addsportsid.push(id);
    }
    
  }
  submit!: boolean
  addsports(): void {
    this.submit = true
    console.log(this.addsportsform.value);
    if(this.addsportsform.valid) {
      console.log('success');
      this.service.addsports(this.addsportsform.value).subscribe(
        data => {
          console.log(data);
        }
      )
    }

  }
  getSlots(): void {
    this.service.getSlots().subscribe(
      (data)=>{this.AllSlots=data}
    )
  }
}
