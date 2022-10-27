import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-ccontract-details',
  templateUrl: './ccontract-details.component.html',
  styleUrls: ['./ccontract-details.component.css']
})


export class CcontractDetailsComponent implements OnInit {
  feedback = new FormControl;
  feedbackDetails: any;
  feedbackform!: FormGroup;
  
  display(jobs: any) {
    this.service.getclientFeedback(jobs.contract_id).subscribe((feedback: any) => {
      // this.feedbackDetails = feedback;
      for (let i = 0; i < this.contractDetails.length; i++) {
        if (this.contractDetails[i].contract_id == jobs.contract_id){
          this.contractDetails[i].feedback = feedback;

        } } }
      , (err: any) => { jobs.flag = !jobs.flag; });


  }
  display1(jobs: any) {
    jobs.flag = !jobs.flag;
  }
  feedbackSumbit(contract_id: any) {
    this.feedbackform = this.fb.group({
      'contract_id': contract_id,
      'rating': this.currentRate,
      'feedback': this.feedback,

    });

    this.service.clientFeedback(this.feedbackform.value).subscribe((feedback: any) => { console.log(feedback); });

  }
  ratechange(arg0: number) {
    this.currentRate = arg0;
  }
  closeResult = '';
  contractDetails: any;
  payment_details!: any;
  change_project_details!: FormGroup
  demoForm!: FormGroup;
  currentRate =3;
  constructor(private service: ServiceService, private modalService: NgbModal, private fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.demoform();
    this.service.getContractOfClient(this.client_id_parse.client_id).subscribe((data: any) => {
      this.contractDetails = data; console.log(this.contractDetails);
      sessionStorage.setItem('contractDetails', JSON.stringify(this.contractDetails));
      for (let i = 0; i < this.contractDetails.length; i++) {
        this.contractDetails[i].flag = false; this.contractDetails[i].feedback = ''
      }
      console.log(this.contractDetails);


    });
  }
  client_id: any = window.sessionStorage.getItem('cuser')
  client_id_parse = JSON.parse(this.client_id)
  sendPaymentData = { 'freelancer_id': 0, 'contract_id': 0, 'Tax': 0, 'earned': 0 };

  open(content: any, jobs: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      // console.log(this.demoForm.value);
      this.closeResult = `${result}`;
      this.contractAmountValue();
      this.formdetails();
      this.sendPaymentData['contract_id'] = jobs.contract_id;
      var amount: number = this.change_project_details.value.total_payment;
      amount = amount - amount * 0.06
      this.sendPaymentData['Tax'] = amount * 0.01;
      this.sendPaymentData['earned'] = amount * 0.99;
      // this.demoForm.patchValue({
      //   'emp_proposal_id': this.contractAmount.get('emp_proposal_id'),
      //   'project_status': new FormControl('', Validators.required)
      // })

      this.demoForm = this.fb.group({
        'contract_amount': jobs.contract_amount,
        'client_id': jobs.client_id,
        'emp_proposal_id': jobs.emp_proposal_id,
        'project_deadline': this.demoForm.value.project_deadline,
        'project_status': this.demoForm.value.project_status
      });

      console.log(this.change_project_details.value);
      this.service.updateContractDetails(this.closeResult, this.demoForm.value).subscribe((result: any) => { console.log(result); });
      if (this.demoForm.value.project_status === "completed") {

        this.service.freelancerpayment(jobs.emp_proposal_id).subscribe((result: any) => {
          this.sendPaymentData['freelancer_id'] = result.freelancer_id;
          console.log(this.sendPaymentData);
          location.reload();

          this.service.newfreelancerpaymentresult(this.sendPaymentData).subscribe((result: any) => console.log(result));

        });

        this.service.clientPayment(this.change_project_details.value).subscribe((data: any) => {
          this.payment_details = data;
          console.log(this.payment_details);
        });
      }
      else { console.log('errors'); }
    });
  }

  demoform(): void {
    this.demoForm = this.fb.group({
      // 'emp_proposal_id': this.contractAmount.get('emp_proposal_id'),
      'project_deadline': new FormControl('', Validators.required),
      'project_status': new FormControl('', Validators.required)
    });
  }
  formdetails(): void {
    this.change_project_details = this.fb.group({
      'contract_id': this.closeResult,
      'handling_fee': this.contractAmount.get('handling_fee'),
      'tax': this.contractAmount.get('tax'),
      'total_payment': this.contractAmount.get('total_payment'),
    })
  }

  contractAmount = new Map();
  contractAmountValue(): void {
    for (let i = 0; i < this.contractDetails.length; i++) {
      this.contractDetails[i].flag = false;
      if (this.contractDetails[i].contract_id == this.closeResult) {
        const emp_proposal_id = this.contractDetails[i].emp_proposal_id
        console.log(this.contractDetails[i].contract_amount);
        let amount = this.contractDetails[i].contract_amount
        this.contractAmount.set('handling_fee', amount * 0.01)
        this.contractAmount.set('tax', amount * 0.05)
        this.contractAmount.set('total_payment', (amount) * 0.06 + amount)
        this.contractAmount.set('emp_proposal_id', emp_proposal_id)
      }
    }
  }

}
