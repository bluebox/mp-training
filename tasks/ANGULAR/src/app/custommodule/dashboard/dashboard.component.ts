import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  input:string="router task"


  constructor(private fb:FormBuilder, private route:Router, private sharedsrv:SharedService) {

   }

  ngOnInit(): void {
    //  this.route.params.subscribe(data=>{
    // this.recieved = data['text']

  // })
  
  }





 senddata()
 {
  this.route.navigate(['./home',this.input]);
 }

}
