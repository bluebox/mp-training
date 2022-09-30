import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.scss']
})
export class AddCustomerComponent implements OnInit {
  firstname: string = '';
  terms: boolean = false;
  constructor(private userService: UsersService) { }
  description: string = '';
  customerType: string = '';
  ngOnInit(): void {


  }
  addCustomer(formValue: NgForm) {
    console.log(formValue.value)
    const postBody = {
      title: formValue.value.firstname,
      body: formValue.value.description

    };
    this.userService.addUser(postBody).subscribe(data => {
      console.log(data)
    }, (err) => {
      console.log("unable to post the data", err)
    })
  }
  resetForm(form: NgForm) {
    // form.reset();
    form.resetForm();
  }
  loadValues(form1: NgForm) {
    let userDetails = {
      firstname: 'ABC',
      terms: false,
      customerType: '1',
      description: "nvbhjdbviunk"
    }
    form1.setValue(userDetails);
  }
}
