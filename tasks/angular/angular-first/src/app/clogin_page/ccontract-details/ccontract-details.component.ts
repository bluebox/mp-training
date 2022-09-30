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
  closeResult = '';
  contractDetails: any;
  payment_details! :any;
  change_project_details!: FormGroup
  constructor(private service: ServiceService, private modalService: NgbModal, private fb: FormBuilder) {

  }

  ngOnInit(): void {

    this.service.getContractOfClient(this.client_id_parse.client_id).subscribe((data: any) => {
      this.contractDetails = data; console.log(this.contractDetails);sessionStorage.setItem('contractDetails', JSON.stringify(this.contractDetails));
    });
  }
  client_id: any = window.sessionStorage.getItem('cuser')
  client_id_parse = JSON.parse(this.client_id)


  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `${result}`;
      this.contractAmountValue();
      this.change_project_details = this.fb.group({
        'contract_id': this.closeResult,
        'handling_fee':this.contractAmount.get('handling_fee'),
        'tax':this.contractAmount.get('tax'),
        'total_payment':this.contractAmount.get('total_payment'),
        'project_deadline': new FormControl('', Validators.required),
        'project_status': new FormControl('', Validators.required),
      })
      console.log(this.change_project_details.value);
      
      if (this.change_project_details.value.project_status === 'Completed') {  
      this.service.clientPayment(this.change_project_details.value).subscribe((data: any) => { this.payment_details = data; console.log(this.payment_details); }); }
      else { console.log('errors');}
    });
  }

  contractAmount = new Map();
  contractAmountValue() : void {
    for (let i = 0; i < this.contractDetails.length; i++) {
      if (this.contractDetails[i].contract_id == this.closeResult) {
        console.log(this.contractDetails[i].contract_amount);
        let amount = this.contractDetails[i].contract_amount
        this.contractAmount.set('handling_fee',amount*0.01)
        this.contractAmount.set('tax',amount*0.05)
        this.contractAmount.set('total_payment',(amount)*0.06 + amount)
      }
      

    }
  }

}
