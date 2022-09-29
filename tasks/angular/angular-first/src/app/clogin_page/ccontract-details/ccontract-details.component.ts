import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-ccontract-details',
  templateUrl: './ccontract-details.component.html',
  styleUrls: ['./ccontract-details.component.css']
})
export class CcontractDetailsComponent implements OnInit {

  contractDetails: any;

  constructor(private service : ServiceService) { }

  ngOnInit(): void {
    this.service.getContractOfClient(this.client_id_parse.client_id).subscribe((data: any) => { this.contractDetails    = data; console.log(this.contractDetails);
     }); 
  }
  client_id  : any = window.sessionStorage.getItem('cuser')
  client_id_parse = JSON.parse(this.client_id)

}
