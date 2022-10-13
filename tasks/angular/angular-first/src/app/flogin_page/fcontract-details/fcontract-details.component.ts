import { HtmlTagDefinition } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
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
  feedbackform1: FormGroup<{ feedback: FormControl<string | null>; }>;
  constructor(private service: ServiceService,private fb : FormBuilder) {
    this.feedbackform1 = this.fb.group({
      'feedback' : new FormControl(''),

     });

  }
  ngOnInit(): void {
    for (let i = 0; i < this.getcontractsparse.length; i++) {
      if (this.getcontractsparse[i].proprosal_status == 'accepted') {
        this.service.getContractOfFreelancer(this.getcontractsparse[i].proprosal_id).subscribe(data => { this.arr.push(data); console.log(this.arr);
         });
     }
    }
  }

  feedbackDetails! : any;
  flag = false;
  display(contract_id : any){
    this.service.getFeedback(contract_id).subscribe((feedback: any) => { this.feedbackDetails=feedback }, (err: any) => {this.flag = !this.flag; });
    

  }
  display1(){
    this.flag = !this.flag;
  }
  id: any = window.sessionStorage.getItem('fuser')
  id_parse = JSON.parse(this.id)

  currentRate = 4;

  ratechange(currentRate : number){
    this.currentRate=currentRate;
    console.log(this.currentRate);
    
  }

  feedbackform! : FormGroup;
  feedback = new FormControl;
  feedbackSumbit(contract_id : any){
    this.feedbackform = this.fb.group({
      'contract_id': contract_id,
      'rating':   this.currentRate,
      'feedback' : this.feedback,

     });

    this.service.newFeedback(this.feedbackform.value).subscribe( (feedback : any ) => { console.log(feedback);});
     
  }
  

}
