import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-submissions',
  templateUrl: './submissions.component.html',
  styleUrls: ['./submissions.component.css']
})
export class SubmissionsComponent implements OnInit {
  allSubmissions: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private service: RegisterService) { 
    this.service.getAllSubmissions().subscribe((data) => {
      console.log(data);
      this.allSubmissions = data;
    })
   }

  ngOnInit(): void {
  }

}
