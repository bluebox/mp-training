import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  receivedData : string = ''

  constructor(private rroutee : ActivatedRoute) { }

  ngOnInit(): void {
    this.rroutee.params.subscribe(data => this.receivedData = data['id'])
  }
  
}
