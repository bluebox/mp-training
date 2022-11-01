import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpServiceService } from '../../http-service.service';

@Component({
  selector: 'app-welcome-admin',
  templateUrl: './welcome-admin.component.html',
  styleUrls: ['./welcome-admin.component.css']
})
export class WelcomeAdminComponent implements OnInit {
  countSubscription!: Subscription;
  counts:any
  constructor(private http : HttpServiceService) { }

  ngOnInit(): void {
    this.countSubscription= this.http.getTotalCounts().subscribe({
      next:(data)=>{
        this.counts = data
      },
      error:(err)=>{
        console.log(err);
        
      }
    })
  }
  ngOnDestroy(){
    this.countSubscription.unsubscribe()
}
}
