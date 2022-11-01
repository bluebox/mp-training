import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import { Complaint } from '../Facility'
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent implements OnInit {
  complaints: any
  userdetails: any
  displaypopup = false
  devices: any | undefined
  assignedemployee: any
  complaintstatus: any
  yettocomplete: boolean = false
  completed: boolean = false
  bool1: boolean = false
  closeResult: string | undefined;
  list: any
  temp: any;
  page = 1
  iscompleted: string = ''
  currenttext: string = ''
  constructor(private service: ServicesService) {
    this.userdetails = sessionStorage.getItem('userdetails')
    let user = JSON.parse(this.userdetails)
    this.service.getComplaintSearch(user.emp_id, '', this.page, this.iscompleted).subscribe(data => {
      this.complaints = data
      this.list = this.complaints.pageItems
      console.log(data);
      this.temp = this.complaints.pageItems
    })
  }


  delete(id: any) {
    this.service.detetecomplaint(id).subscribe(
      (data) => {
        this.search()
        console.log(data);

      },
      (err) => console.log(err))


  }

  search() {
    this.userdetails = sessionStorage.getItem('userdetails')
    let user = JSON.parse(this.userdetails)
    this.service.getComplaintSearch(user.emp_id, this.currenttext, this.page, this.iscompleted).subscribe(data => {
      this.complaints = data;
      console.log(this.complaints.pageItems);

      this.temp = this.complaints.pageItems
      this.list = this.temp
    })
  }
  next(num: number) {
    console.log(num + "cuttent page" + this.page);
    if (num == 1) {
      this.page++;
    }
    if (num == -1) {
      this.page--;
    }
    this.userdetails = sessionStorage.getItem('userdetails')
    let user = JSON.parse(this.userdetails)
    this.service.getComplaintSearch(user.emp_id, this.currenttext, this.page, this.iscompleted).subscribe(data => {
      this.complaints = data;
      this.temp = this.complaints.pageItems
      this.list = this.temp
    })

  }

  ngOnInit(): void {

  }

}
