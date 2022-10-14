import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  clients :any;
  displayedColumns :string[]=['id','client__id','username','first_name',"last_name","email","client__Client_contact_number"];
  constructor(private http:HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getClients().subscribe((data) =>{this.clients = data ;console.log(data)});
  }
  
}
