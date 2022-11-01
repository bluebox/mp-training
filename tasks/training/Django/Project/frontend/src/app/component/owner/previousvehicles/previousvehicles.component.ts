import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-previousvehicles',
  templateUrl: './previousvehicles.component.html',
  styleUrls: ['./previousvehicles.component.css'],
})
export class PreviousvehiclesComponent implements OnInit {
  veh_no: any = window.sessionStorage.getItem('vehicle_no');
  vehicle = JSON.parse(this.veh_no);
  closeResult!: string;
  price!: FormGroup;

  resp: any;
  getOwner: any = window.sessionStorage.getItem('owner_id');
  owner = JSON.parse(this.getOwner);
  constructor(
    private service: GeneralService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getOwnerVehicle();

    this.price = new FormGroup({
      price_day: new FormControl(''),
    });
  }

  getOwnerVehicle() {
    this.service.getOwnerVehicle().subscribe(
      (data) => {
        this.resp = data;
        window.sessionStorage.setItem('vehicle_no', JSON.stringify(data));
      },
      (err) => alert('failed to load data')
    );
  }
  getVehicleNumber() {}
  deleteVehicle(id: any) {
    if (confirm('This Vehicle will be deleted!! are you sure?')) {
      this.service.deleteVehicle(id).subscribe((data) => {
        this.ngOnInit();
      });
    }
  }

  viewVehicleDetails(id: any) {}
  response: any;

  open(content: any, rent_id: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        this.service
          .updateVehiclePrice(result, this.price.value)
          .subscribe((data) => {
            this.response = data;
            console.log(this.price.value);
            console.log(data);
            alert(this.response.msg);
            this.ngOnInit();
          });
      });
  }
  respon: any;

  changeVehicleStatus(id: any) {
    this.service.changeStatus(id).subscribe((data) => {
      (this.respon = data);
      this.ngOnInit();
    });
  }
}
