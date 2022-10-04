import { Component, OnInit } from '@angular/core';
import { Emp } from 'src/app/data';
import { DataserveService } from 'src/app/dataserve.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  constructor(private ds:DataserveService) { }

  data:any

  ngOnInit(): void {
    this.ds.getDetails().subscribe(val=>console.log(val));
    this.data=
{
    "cust_id": 6,
    "uname": "jkhbfjd",
    "f_name": "sdfgsdg",
    "l_name": "efrwetfrg",
    "mail": "sgsdr@kjnf",
    "password": "sfargeg"
}
     
    
    
  }

fun(){
  // this.data = this.ds.getDetailsnew();
  this.ds.postCustDetailsnew(this.data).subscribe(newdata =>console.log("success"));
  
  

}


}
