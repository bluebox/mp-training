import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-submissions',
  templateUrl: './submissions.component.html',
  styleUrls: ['./submissions.component.css']
})
export class SubmissionsComponent implements OnInit {
  allSubmissions: any;
  subscription!: Subscription

  constructor(private route: ActivatedRoute, private http: HttpClient, private service: RegisterService) { 
    // this.service.getAllSubmissions().subscribe((data) => {
    //   console.log(data);
    //   this.allSubmissions = data;
    // })
   }

  ngOnInit(): void {
    this.subscription = this.service.getAllSubmissions().subscribe((data) => {
      console.log(data);
      this.allSubmissions = data;
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
