import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-fcontract-details',
  templateUrl: './fcontract-details.component.html',
  styleUrls: ['./fcontract-details.component.css']
})
export class FcontractDetailsComponent implements OnInit {

  contractDetails: any;

  constructor(private service : ServiceService) { }

  ngOnInit(): void {
    this.service.getContractOfFreelancer(this.id_parse.client_id).subscribe((data: any) => { this.contractDetails    = data; console.log(this.contractDetails);
     }); 
  }
  id  : any = window.sessionStorage.getItem('fuser')
  id_parse = JSON.parse(this.id)

}
