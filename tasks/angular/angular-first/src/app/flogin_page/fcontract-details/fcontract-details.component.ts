import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-fcontract-details',
  templateUrl: './fcontract-details.component.html',
  styleUrls: ['./fcontract-details.component.css']
})
export class FcontractDetailsComponent implements OnInit {

  contractDetails: any;
  arr : any  = [] 
  getcontracts: any = window.sessionStorage.getItem('freelancer_proposals')
  getcontractsparse = JSON.parse(this.getcontracts);
  constructor(private service: ServiceService) { }
  ngOnInit(): void {
    for (let i = 0; i < this.getcontractsparse.length; i++) {
      if (this.getcontractsparse[i].proprosal_status == 'accepted') {
        this.service.getContractOfFreelancer(this.getcontractsparse[i].proprosal_id).subscribe(data => { this.arr.push(data); console.log(this.arr);
         });
     }
    }
  }

  
  id: any = window.sessionStorage.getItem('fuser')
  id_parse = JSON.parse(this.id)


}
