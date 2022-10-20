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
  data : any = localStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)
  contractDetails: any;
  arr: any = []
  // getcontracts: any = window.sessionStorage.getItem('freelancer_proposals')
  // getcontractsparse = JSON.parse(this.getcontracts);
  getcontractsparse! : any;
  feedbackform1: FormGroup<{ feedback: FormControl<string | null>; }>;
  constructor(private service: ServiceService, private fb: FormBuilder) {
    // console.log('constructor');

    
    this.feedbackform1 = this.fb.group({
      'feedback': new FormControl(''),

    });

  }
  ngOnInit(): void {
    this.service.getallFreelancerProposals(this.parse_data.id).subscribe( (data: any ) => {
      this.getcontractsparse = data;
      this.getContractOfFreelancer();
    })
    // console.log('ngOnInit');
    // console.log(this.getcontractsparse);
    // for (let i = 0; i < this.getcontractsparse.length; i++) {
    //   if (this.getcontractsparse[i].proprosal_status == 'accepted') {
    //     this.service.getContractOfFreelancer(this.getcontractsparse[i].proprosal_id).subscribe(data => { this.contractDetails= data;
    //       this.contractDetails.flag = false  ; this.contractDetails.feedback  = '';
    //       this.arr.push(this.contractDetails); console.log(this.arr);
    //     });
    //   }
    // }
    // this.getContractOfFreelancer()

    
    // console.log(this.arr);

  }

  getContractOfFreelancer(){
    for (let i = 0; i < this.getcontractsparse.length; i++) {
      if (this.getcontractsparse[i].proprosal_status == 'accepted') {
        this.service.getContractOfFreelancer(this.getcontractsparse[i].proprosal_id).subscribe(data => { this.contractDetails= data;
          this.contractDetails.flag = false  ; this.contractDetails.feedback  = '';
          this.arr.push(this.contractDetails); console.log(this.arr);
        });
      }
    }
  }

  display(jobs: any) {
    this.service.getFeedback(jobs.contract_id).subscribe((feedback: any) => {
      for (let i = 0; i < this.arr.length; i++) {
        if (this.arr[i].contract_id == jobs.contract_id) {
          this.arr[i].feedback = feedback;

        }
      }


    }, (err: any) => { jobs.flag = !jobs.flag; });


  }
  display1(jobs : any) {
    jobs.flag = !jobs.flag;
  }
  id: any = window.sessionStorage.getItem('fuser')
  id_parse = JSON.parse(this.id)

  currentRate = 4;

  ratechange(currentRate: number) {
    this.currentRate = currentRate;
    console.log(this.currentRate);

  }

  feedbackform!: FormGroup;
  feedback = new FormControl;
  feedbackSumbit(contract_id: any) {
    this.feedbackform = this.fb.group({
      'contract_id': contract_id,
      'rating': this.currentRate,
      'feedback': this.feedback,

    });

    this.service.newFeedback(this.feedbackform.value).subscribe((feedback: any) => { console.log(feedback); });

  }

  page =1;
  totalPages : any;


}
