import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.scss']
})
export class ChangepasswordComponent implements OnInit {
changepassword() {
throw new Error('Method not implemented.');
}
change!: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
