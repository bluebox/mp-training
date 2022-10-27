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
  totalPages  = 0;
  page = 1;
  ngOnInit(): void {
    this.getProposals(0,'') 
  }
  data : any = localStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)
  // deleteProposal(proprosal_id : any){
  //   this.service.deleteProposal(proprosal_id ).subscribe(data => console.log(this.data), err => console.log(err))
  // }
  deleteProposal(proprosal_id : any ){ 
    if (confirm("Are you sure you want to delete this proposal?")){
      this.service.deleteProposal(proprosal_id).subscribe(data => {
        alert('deleted successfully');
        window.location.reload();
      }, err => console.log(err));
    }
  }

  getProposals(page : number,value : any){
    this.service.getFreelancerProposals(this.page + page,this.parse_data.id,value).subscribe( (data: any ) => {
      this.recevied_data = data.pageItems
      this.page=data.page
      this.totalPages = data.totalPages
      console.log(this.recevied_data);
      // window.sessionStorage.setItem('freelancer_proposals', JSON.stringify(this.recevied_data))
    })
  }
  sorting : string='';
  sortby(value : any){
    this.sorting  = value.target.value
    this.getProposals(0,this.sorting)
  }
}
