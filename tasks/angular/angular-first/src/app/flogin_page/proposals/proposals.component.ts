import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-proposals',
  templateUrl: './proposals.component.html',
  styleUrls: ['./proposals.component.css']
})
export class ProposalsComponent implements OnInit {
  recevied_data : any;
  constructor(private service : ServiceService) { }

  ngOnInit(): void {

    
    this.service.getFreelancerProposals(this.parse_data.id).subscribe( (data: any ) => {
      this.recevied_data = data
      console.log(this.recevied_data);
      window.sessionStorage.setItem('freelancer_proposals', JSON.stringify(this.recevied_data))
    })
  }
  data : any = localStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)



}
