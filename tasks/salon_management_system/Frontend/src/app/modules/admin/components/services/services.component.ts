import { Component, OnInit } from '@angular/core';
import { HttpserviceService } from 'src/app/httpservice.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  services: any;
  displayedColumns :string[]=['service_id','service_name','Amount_to_be_paid'];

  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getServices().subscribe((data) =>{this.services = data ;console.log(data)});
  }

}
