import { Component, Input, OnInit, } from '@angular/core';
import { SharedService } from '../../shared.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-edit-demo',
  templateUrl: './edit-demo.component.html',
  styleUrls: ['./edit-demo.component.css']
})
export class EditDemoComponent implements OnInit {

  // demoform:any=FormGroup;
  constructor(private service:SharedService) { }
   @Input() dem:any;
 airport_id:number|undefined;
 airport_name:string="";
 airport_owner:string="";
 city:string="";


 ngOnInit(): void {

  // this.demoform = new FormGroup({
  //   airport_id: new FormControl(''),
  //   airport_name: new FormControl(''),
  //   airport_owner: new FormControl(''),
  //   city: new FormControl(''),

  // });
  this.airport_id=this.dem.airport_id;
  this.airport_name=this.dem.airport_name;
  this.airport_owner=this.dem.airport_owner;
  this.city = this.dem.city;

   }
adddemo(){
var val={airport_id:this.airport_id,
  airport_name:this.airport_name,
  airport_owner:this.airport_owner,
  city:this.city};

  this.service.addDemolist(val).subscribe(res=>{
    alert(res.toString());
  });
}

updatedemo(){
  var val={airport_id:this.airport_id,
    airport_name:this.airport_name,
    airport_owner:this.airport_owner,
    city:this.city};
  //  console.log(val)
    this.service.updateDemolist(val).subscribe(res=>{
      alert(res.toString());
    });
  }
}


