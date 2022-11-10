import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  tags!: any;

  constructor(public service: RegisterService) { 
    this.service.getTags().subscribe((data) => {
      console.log(data)
      this.tags = data;
    })
   }

  ngOnInit(): void {
  }

}
