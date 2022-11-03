import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {
clientList=[
  {clientId:10, fname:'shyam',lname:'kumar'},
  {clientId:11, fname:'sai',lname:'kumar'},
  {clientId:12, fname:'rajesh',lname:'kumar'},
  {clientId:13, fname:'ram',lname:'kumar'},
  {clientId:14, fname:'ravi',lname:'kumar'},
  {clientId:15, fname:'harshi',lname:'kumari'},
  {clientId:16, fname:'varsha',lname:'kumari'}
]
  constructor() { }

  ngOnInit(): void {
  }

}
