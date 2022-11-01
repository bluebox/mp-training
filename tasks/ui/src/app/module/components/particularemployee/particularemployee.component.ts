import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-particularemployee',
  templateUrl: './particularemployee.component.html',
  styleUrls: ['./particularemployee.component.css']
})
export class ParticularemployeeComponent implements OnInit {

  emp_name : any
  data : any
   constructor(private service : ServicesService, private arouter : ActivatedRoute) {
      this.arouter.params.subscribe(data =>
      this.emp_name=data['emp_id'] )
      this.service.getEmployee(this.emp_name).subscribe(data=>{this.data=data;console.log(this.data);
      })
    }
    deleteaccount(){
      this.service.deleteaccount(this.data.emp_id).subscribe(data => console.log(data)
      )
    }

  ngOnInit(): void {
  }

}
